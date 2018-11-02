/**
 * Alex Tang
 * 2018年3月29日
 * desc:
 */
package com.sun.common.ftp;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Alex Tang 2018年3月29日 desc:HttpUrlConnectionHelper
 */
public class UrlConnectionHelper {

	private static final int CONNECTION_TIMEOUT = 5000; // 建立连接超时时间 5s
	private static final int READ_TIMEOUT = 5000; // 数据传输超时时间 5s
	private static ExecutorService executor = Executors.newFixedThreadPool(1);
	private static final String newLine = "\r\n";// 换行符
	private static final String boundaryPrefix = "--"; // 分割线
	private static String BOUNDARY = "========7d4a6d158c9"; // 分隔符

	public static String requestGet(String strUrl) throws Exception {
		String result = null;
		URL url = new URL(strUrl);// 新建一个URL对象
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();// 打开一个HttpURLConnection连接
		urlConn.setConnectTimeout(CONNECTION_TIMEOUT);// 设置连接主机超时时间
		urlConn.setReadTimeout(READ_TIMEOUT);// 设置从主机读取数据超时
		urlConn.setUseCaches(true);// 设置是否使用缓存 默认是true
		urlConn.setRequestMethod("GET");// 设置为Post请求
		setConn(urlConn);// urlConn设置请求头信息
		urlConn.connect();// 开始连接
		if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {// 判断请求是否成功
			result = streamToString(urlConn.getInputStream());// 获取返回的数据
		} else {
			throw new Exception("HttpCode:" + urlConn.getResponseCode());
		}
		urlConn.disconnect();// 关闭连接
		return result;
	}

	public static String requestPost(String strUrl, String bodys)
			throws Exception {
		String result = null;
		// 1. 获取访问地址URL
		URL url = new URL(strUrl);
		// 2. 创建HttpURLConnection对象
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		/* 3. 设置请求参数等 */
		// 请求方式
		urlConn.setRequestMethod("POST");
		// 设置连接主机超时时间
		urlConn.setConnectTimeout(CONNECTION_TIMEOUT);
		// 设置从主机读取数据超时
		urlConn.setReadTimeout(READ_TIMEOUT);
		// urlConn设置请求头信息
		setConn(urlConn);
		// 设置是否输出
		urlConn.setDoOutput(true);
		// 设置是否读入
		urlConn.setDoInput(true);
		// 设置是否使用缓存
		urlConn.setUseCaches(false);
		// 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
		urlConn.setInstanceFollowRedirects(true);
		// 设置使用标准编码格式编码参数的名-值对
		// urlConn.setRequestProperty("Content-Type","application/json");
		// 连接
		urlConn.connect();
		/* 4. 处理输入输出 */
		// 写入参数到请求中
		OutputStream out = urlConn.getOutputStream();
		out.write(bodys.getBytes());
		out.flush();
		out.close();
		// 判断请求是否成功
		if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			// 获取返回的数据
			result = streamToString(urlConn.getInputStream());
		} else {
			throw new Exception("HttpCode:" + urlConn.getResponseCode());
		}
		// 5. 断开连接
		urlConn.disconnect();
		return result;
	}

	/**
	 * 文件上传
	 * 
	 * @param strUrl
	 * @param file
	 */
	public static String uploadFile(String strUrl, List<File> files)
			throws Exception {
		// 服务器的域名
		URL url = new URL(strUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置为POST情
		conn.setRequestMethod("POST");
		// 发送POST请求必须设置如下两行
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		// 设置请求头参数
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("Charsert", "UTF-8");
		conn.setRequestProperty("Content-Type",
				"multipart/form-data; boundary=" + BOUNDARY);
		OutputStream out = new DataOutputStream(conn.getOutputStream());
		// 设置文件
		for (File file : files) {
			setMultipartFiles(file, out);
		}

		// 定义最后数据分隔线，即--加上BOUNDARY再加上--。
		byte[] end_data = (newLine + boundaryPrefix + BOUNDARY + boundaryPrefix + newLine)
				.getBytes();
		// 写上结尾标识
		out.write(end_data);
		out.flush();
		out.close();

		// 定义BufferedReader输入流来读取URL的响应
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String result = "";
		String line = null;
		while ((line = reader.readLine()) != null) {
			if (line != null) {
				result += line;
			}
		}
		return result;
	}

	/**
	 * 设置upload文件
	 * 
	 * @param file
	 * @param out
	 * @throws Exception
	 */
	private static void setMultipartFiles(File file, OutputStream out)
			throws Exception {
		// 上传文件
		StringBuilder sb = new StringBuilder();
		sb.append(boundaryPrefix);
		sb.append(BOUNDARY);
		sb.append(newLine);
		// 文件参数,photo参数名可以随意修改
		sb.append("Content-Disposition: form-data;name=\"photo\";filename=\""
				+ file.getName() + "\"" + newLine);
		sb.append("Content-Type:application/octet-stream");
		// 参数头设置完以后需要两个换行，然后才是参数内容
		sb.append(newLine);
		sb.append(newLine);

		// 将参数头的数据写入到输出流中
		out.write(sb.toString().getBytes());

		// 数据输入流,用于读取文件数据
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		byte[] bufferOut = new byte[1024];
		int bytes = 0;
		// 每次读1KB数据,并且将文件数据写入到输出流中
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		// 最后添加换行
		out.write(newLine.getBytes());
		in.close();
	}

	/**
	 * 下载
	 * 
	 * @param address
	 * @param saveFile
	 * @throws Exception
	 */
	/*
	 * public static void httpDownload(String address, String saveFile) throws
	 * Exception { int byteread = 0; URL url = new URL(address);
	 * HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	 * setConn(conn); conn.setConnectTimeout(10 * 1000); conn.setReadTimeout(15
	 * * 1000); conn.connect(); InputStream inStream = conn.getInputStream();
	 * FileOutputStream fs = new FileOutputStream(saveFile); byte[] buffer = new
	 * byte[4096]; while ((byteread = inStream.read(buffer)) != -1) {
	 * Thread.sleep(8);//每8ms读4k fs.write(buffer, 0, byteread); fs.flush(); }
	 * fs.close(); inStream.close(); }
	 */

	/**
	 * 未下载完成的如果抛出异常则会删除临时文件
	 * @param address
	 * @param saveFile
	 * @return
	 * @throws Exception
	 */
	public static int httpDownload(String address, String saveFile)
			throws Exception {
		long start = System.currentTimeMillis();
		FileOutputStream fs = new FileOutputStream(saveFile);
		InputStream is = null;
		int total = 0;
		try {
			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			setConn(conn);
			conn.setConnectTimeout(10 * 1000);
			conn.setReadTimeout(15 * 1000);
			conn.connect();
			is = conn.getInputStream();
			byte[] buffer = new byte[1024 * 1024 * 10];
			DownloadCallable callable = new DownloadCallable(is, buffer);
			while (true) {
				Future<Integer> future = executor.submit(callable);
				int byteread = future.get(10, TimeUnit.SECONDS);// 10秒收不到数据,则判断为卡住了,退出
				if (byteread != -1) {
					fs.write(buffer, 0, byteread);
					fs.flush();
					total += byteread;
				} else {
					break;
				}
			}
			is.close();
			fs.close();
		} catch (Exception e) {
			if (is != null)
				is.close();
			fs.close();
			File file = new File(saveFile);
			if (file.exists()) {// 如果有未下载完成的文件就删除他
				file.delete();
			}
			throw e;
			//throw new Exception("文件下载失败");
		}
		long end = System.currentTimeMillis();
		// System.out.println("开始"+start+"  结束："+end);
		int actual = (int) (end - start);// 实际秒数
		// System.out.println("实际毫秒数:"+actual);
		// System.out.println(total);
		int expect = (total * 1000) / (500 * 1024);// 期望毫数
		// System.out.println("期望时间："+expect);
		return (expect - actual) >= 0 ? (expect - actual) : 0;
	}

	/**
	 * 带百分比输出的下载
	 * @param address
	 * @param filePath
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public static long downloadPercent(String address, String filePath, long size) throws Exception {
		long start = System.currentTimeMillis();
		FileOutputStream fs = new FileOutputStream(filePath);
		InputStream is = null;
		long total = 0;
		try {
			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			setConn(conn);
			conn.setConnectTimeout(10 * 1000);
			conn.setReadTimeout(15 * 1000);
			conn.connect();
			is = conn.getInputStream();
			byte[] buffer = new byte[1024 * 1024 * 10];
			DownloadCallable callable = new DownloadCallable(is, buffer);
			if(size==0){
				size = conn.getContentLengthLong();
			}
			int old_pct = 0;//百分比
			while (true) {
				Future<Integer> future = executor.submit(callable);
				int byteread = future.get(10, TimeUnit.SECONDS);// 10秒收不到数据,则判断为卡住了,退出
				if (byteread != -1) {
					fs.write(buffer, 0, byteread);
					fs.flush();
					total += byteread;
					int now_pct = (int)((total*100)/size);
					if((now_pct-old_pct)>4){
						old_pct = now_pct;
						System.out.print(old_pct+"% ");
					}
				} else {
					break;
				}
			}
			System.out.println("done!");
			is.close();
			fs.close();
		} catch (Exception e) {
			if (is != null)
				is.close();
			fs.close();
			File file = new File(filePath);
			if (file.exists()) {// 如果有未下载完成的文件就删除他
				file.delete();
			}
			throw e;
			//throw new Exception("文件下载失败");
		}
		long end = System.currentTimeMillis();
		return end-start;
	}
	
	private static class DownloadCallable implements Callable<Integer> {
		private InputStream is;
		private byte[] buffer;
		public DownloadCallable(InputStream is, byte[] buffer) {
			this.is = is;
			this.buffer = buffer;
		}
		@Override
		public Integer call() throws Exception {
			return is.read(buffer);
		}
	}

	/*
	 * 将输入流转换成字符串
	 * @param is 从网络获取的输入流
	 * @return
	 */
	public static String streamToString(InputStream is) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			baos.close();
			is.close();
			byte[] byteArray = baos.toByteArray();
			return new String(byteArray);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 设置头文件
	 */
	private static void setConn(HttpURLConnection httpUrl) {
		httpUrl.setRequestProperty(
				"User-agent",
				"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11 wthx");
		httpUrl.setRequestProperty(
				"Accept",
				"text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
		httpUrl.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.9,en;q=0.7");
		httpUrl.setRequestProperty("Accept-Charset",
				"gb2312,utf-8;q=0.7,*;q=0.7");
		httpUrl.setRequestProperty("Keep-Alive", "300");
		httpUrl.setRequestProperty("Connection", "keep-alive");
	}

	public static void main(String args[]) {
		String url = "http://125.65.241.13/vcloud1049.tc.qq.com/1049_M0131800003U6cFN0mxulK1001577856.f30.mp4?vkey=5876911AC55BAB6C440E59CC923A938C2E7338AE7D1C1F848D8256EB5CD08A1FC1B8692B3337AF4020A8C3F64C26A08A8549ABA75360ADC13322A59CCB14FDA29C576003FF8031CAA307C26E4A4C5472EA1B43664ECA0F73&stdfrom=0";
		try {
			UrlConnectionHelper.downloadPercent(url, "D:/fff.png", 35235331);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
