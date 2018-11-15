package com.sun.common.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import java.io.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FtpUtils2
 * @Author Alistair.Chow
 * @date 2018/10/22 16:36
 * @Version 1.0
 */
public class HndxFtpUtils {
	
    public static void main(String[] args){  
    	
//    	boolean flag=downloadFile("/song/2012645.jpg", "2012645.jpg", "F:");
//    	System.out.println(flag);
    	System.out.println("/////////////////////////完成");
   
    }

    /**
     * 日志对象
     **/
    private static final Logger LOGGER = LoggerFactory.getLogger(HndxFtpUtils.class);

    /**
     * FTP地址
     **/
    private String FTP_ADDRESS;

    /**
     * FTP端口
     **/
    private int FTP_PORT = 21;

    /**
     * FTP用户名
     **/
    private String FTP_USERNAME;

    /**
     * FTP密码
     **/
    private String FTP_PASSWORD;

    /**
     * 本地字符编码
     **/
    private static String localCharset = "GBK";

    /**
     * FTP协议里面，规定文件名编码为iso-8859-1
     **/
    private static String serverCharset = "ISO-8859-1";

    /**
     * UTF-8字符编码
     **/
    private static final String CHARSET_UTF8 = "UTF-8";

    /**
     * OPTS UTF8字符串常量
     **/
    private static final String OPTS_UTF8 = "OPTS UTF8";

    /**
     * 设置缓冲区大小4M
     **/
    private static final int BUFFER_SIZE = 1024 * 1024 * 4;

    /**
     * FTPClient对象
     **/
    private static FTPClient ftpClient = null;


    /*初始化ftp客户端*/
    private static void initFTPClient() {
    	if(null == ftpClient) {
    		ftpClient= new FTPClient();
    	}
    	try {
    	    ftpClient.connect("", 21);
   
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                closeConnect();
                LOGGER.error("FTP服务器连接失败");
            }
           if(!ftpClient.login("", "")) {
        	   LOGGER.error("FTP 登录失败");  
           }
    	    /*启用或禁用验证参与数据连接的远程主机与连接控制连接的主机相同的验证。*/
           ftpClient.setRemoteVerificationEnabled(false);
           ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
           ftpClient.setConnectTimeout(5*60*1000);
           ftpClient.setDefaultTimeout(5*60*1000);
           ftpClient.setDataTimeout(5*60*1000);
           // 设置编码：开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）
           if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
               localCharset = CHARSET_UTF8;
           }
           ftpClient.setBufferSize(BUFFER_SIZE);
           ftpClient.setControlEncoding(localCharset);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("FTP login  err");
		}
    
    }

    
    /** * 下载文件 * 
     * @param pathname FTP服务器文件目录 * 
     * @param filename 文件名称 * 
     * @param localpath 下载后的文件路径 * 
     * @return */
     public  boolean downloadFile(String ftpPathAndName, String renameFilename, String localpath){ 
    	 boolean flag= false;
         OutputStream os=null;
         try { 
             System.out.println("开始下载文件");
             initFTPClient(); 
             if(!checkExist(ftpPathAndName)) {
            	 LOGGER.error("文件不存在"); 
            	 return false;
             }
             File localFile = new File(localpath + "/" + renameFilename); 
             os = new FileOutputStream(localFile); 
             flag=   ftpClient.retrieveFile(ftpPathAndName, os);  
             System.out.println("下载文件成功");
         } catch (Exception e) { 
             System.out.println("下载文件失败");
             e.printStackTrace(); 
         } finally{ 
        	   IOUtils.closeQuietly(os);
               closeConnect();
         } 
         
         return flag;
     }


    /**
     * 本地文件上传到FTP服务器
     *
     * @param ftpPath  FTP服务器文件相对路径，例如：test/123
     * @param savePath 本地文件路径，例如：D:/test/123/test.txt
     * @param fileName 上传到FTP服务的文件名，例如：666.txt
     * @return boolean 成功返回true，否则返回false
     */
	public static boolean uploadLocalFile(String ftpPath, String savePath, String fileName) {
		initFTPClient();
        boolean flag = false;
        if (ftpClient != null) {
            File file = new File(savePath);
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                String path = changeEncoding(ftpPath);
                // 目录不存在，则递归创建
                if (!ftpClient.changeWorkingDirectory(path)) {
                    makeDirectorys(path);                 
                }
                // 设置被动模式，开通一个端口来传输数据
                //ftpClient.enterLocalPassiveMode();
                //检查文件如果不存在则上传
                if(checkExist(ftpPath+fileName)) {
                	LOGGER.info(ftpPath+fileName+"已存在,不用上传");
                	flag=true;
                }else {
                	// 上传文件
                	// flag = ftpClient.storeFile(new String(fileName.getBytes(localCharset), serverCharset), fis);
                    flag = ftpClient.storeFile(ftpPath+fileName,  fis);
                }
            } catch (Exception e) {
                LOGGER.error("本地文件上传FTP失败", e);
            } finally {
                IOUtils.closeQuietly(fis);
                closeConnect();
            }
        }
        return flag;
    }


    /**
     * 远程文件上传到FTP服务器
     *
     * @param ftpPath    FTP服务器文件相对路径，例如：test/123
     * @param remotePath 远程文件路径，例如：http://www.baidu.com/xxx/xxx.jpg
     * @param fileName   上传到FTP服务的文件名，例如：test.jpg
     * @return boolean 成功返回true，否则返回false
     */
    public static  boolean uploadRemoteFile(String ftpPath, String remotePath, String fileName) {
    	initFTPClient();
        boolean flag = false;
        if (ftpClient != null) {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = null;
            try {
                // 远程获取文件输入流
                HttpGet httpget = new HttpGet(remotePath);
                response = httpClient.execute(httpget);
                HttpEntity entity = response.getEntity();
                InputStream input = entity.getContent();
                ftpClient.setBufferSize(BUFFER_SIZE);
                // 设置编码：开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）
                if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
                    localCharset = CHARSET_UTF8;
                }
                ftpClient.setControlEncoding(localCharset);
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                String path = changeEncoding( ftpPath);
                // 目录不存在，则递归创建
                if (!ftpClient.changeWorkingDirectory(path)) {
                	 makeDirectorys(path);
                }
              
                // 上传文件
                flag = ftpClient.storeFile(new String(fileName.getBytes(localCharset), serverCharset), input);
            } catch (Exception e) {
                LOGGER.error("远程文件上传FTP失败", e);
            } finally {
                closeConnect();
                try {
                    httpClient.close();
                } catch (IOException e) {
                    LOGGER.error("关闭流失败", e);
                }
                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        LOGGER.error("关闭流失败", e);
                    }
                }
            }
        }
        return flag;
    }

    
   







    /**
     * 删除指定文件
     *
     * @param filePath 文件相对路径，例如：test/123/test.txt
     * @return 成功返回true，否则返回false
     */
    public boolean deleteFile(String filePath) {
        // 登录
    	initFTPClient();
        boolean flag = false;
        if (ftpClient != null) {
            try {
                String path = changeEncoding(filePath);
                flag = ftpClient.deleteFile(path);
            } catch (IOException e) {
                LOGGER.error("删除文件失败", e);
            } finally {
                closeConnect();
            }
        }
        return flag;
    }

    /**
     * 删除目录下所有文件
     *
     * @param dirPath 文件相对路径，例如：test/123
     * @return 成功返回true，否则返回false
     */
    public boolean deleteFiles(String dirPath) {
        // 登录
    	initFTPClient();
        boolean flag = false;
        if (ftpClient != null) {
            try {
                String[] fs = ftpClient.listNames(dirPath);
                // 判断该目录下是否有文件
                if (fs == null || fs.length == 0) {
                    LOGGER.error(dirPath + "该目录下没有文件");
                    return flag;
                }
                for (String ftpFile : fs) {
                    ftpClient.deleteFile(ftpFile);
                }
                flag = true;
            } catch (IOException e) {
                LOGGER.error("删除文件失败", e);
            } finally {
                closeConnect();
            }
        }
        return flag;
    }


    /**
     * 关闭FTP连接
     */
    private  static void closeConnect() {
      if (ftpClient != null && ftpClient.isConnected()) {
            try {
            	 ftpClient.disconnect(); //解除连接
            } catch (IOException e) {
                LOGGER.error("关闭FTP连接失败", e);
            }
        }
    }

    /**
     * FTP服务器路径编码转换
     *
     * @param ftpPath FTP服务器路径
     * @return String
     */
    private static String changeEncoding(String ftpPath) {
        String directory = null;
        try {
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
                localCharset = CHARSET_UTF8;
            }
            directory = new String(ftpPath.getBytes(localCharset), serverCharset);
        } catch (Exception e) {
            LOGGER.error("路径编码转换失败", e);
        }
        return directory;
    }



    /**创建目录*/
    public static boolean makeDirectorys(String ftpPat){
        try {         
        	initFTPClient();
            String[] arrString= ftpPat.split("/");
            System.out.println(JSONObject.toJSONString(arrString));
            StringBuffer sbf=new  StringBuffer("");
            List<String> strList =new ArrayList<String>();
            for (String  s:arrString) {
                if(!"".equals(s)){
                    strList.add(s);
                }
            }
            for (String  Str:strList) {
                sbf.append("/"+Str);
                System.out.println(JSONObject.toJSONString(sbf.toString()));
              boolean boo = ftpClient.changeWorkingDirectory(sbf.toString());
              LOGGER.info("结果"+boo);
                System.out.println(JSONObject.toJSONString(sbf.toString()));
               if (!boo){
            	   LOGGER.info("路径不存在,创建路径"+sbf.toString());
                   ftpClient.makeDirectory(sbf.toString());
               }else{
            	   LOGGER.info("路径存在,不用创建");
               }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
    /*检测文件是否存在:参数:/xx/xx.xx  */
    public static boolean checkExist(String ftpFilePath) {
    	initFTPClient();
    	boolean flag = false;
    	try {
    		InputStream inputStream = ftpClient.retrieveFileStream(ftpFilePath);
    		if(null != inputStream) {
    			return true;
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return flag ;
    	
    }
    

}
