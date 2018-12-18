/**
 * 
 */
package org.reco.media.music.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;

//import junit.framework.Test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.ibatis.cache.decorators.FifoCache;

/**
 * Alex Tang
 * 2018年6月12日
 * desc:
 */
public class FtpUtils {
    //ftp服务器地址
    public String hostName = "182.138.25.52";
    //ftp服务器端口号默认为21
    public Integer port = 21 ;
    //ftp登录账号
    public String userName = "wthx";
    //ftp登录密码
    public String passWord = "ftp_aA201801"; 
    
    public FTPClient ftpClient = null;
    
    FtpUtils(){}
    
    public FtpUtils(String hostName,Integer port,String userName,String passWord){
    	this.hostName = hostName;
    	this.port = port;
    	this.userName = userName;
    	this.passWord = passWord;
    }
    
    /**
     * 初始化ftp服务器
     */
    public void initFtpClient() {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("utf-8");
        ftpClient.setConnectTimeout(5*60*1000);
        ftpClient.setDefaultTimeout(5*60*1000);
        ftpClient.setDataTimeout(5*60*1000);
        try {
            System.out.println("connecting...ftp服务器:"+this.hostName+":"+this.port); 
            ftpClient.connect(hostName, port); //连接ftp服务器
            ftpClient.login(userName, passWord); //登录ftp服务器
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            if(!FTPReply.isPositiveCompletion(replyCode)){
                System.out.println("connect failed...ftp服务器:"+this.userName+":"+this.port); 
            }
            System.out.println("connect successfu...ftp服务器:"+this.passWord+":"+this.port); 
        }catch (MalformedURLException e) { 
           e.printStackTrace(); 
        }catch (IOException e) { 
           e.printStackTrace(); 
        } 
    }

    /**
    * 上传文件
    * @param pathname ftp服务保存地址
    * @param fileName 上传到ftp的文件名
    *  @param originfilename 待上传文件的名称（绝对地址） * 
    * @return
    */
    public boolean uploadFile(String pathName, String fileName,String originfilename) throws Exception{
        InputStream inputStream = null;
        try{
            System.out.println("开始上传文件");
            inputStream = new FileInputStream(new File(originfilename));
            initFtpClient();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setBufferSize(1024*1024*10);
            //检查上传路径是否存在 如果不存在返回false
            boolean flag = ftpClient.changeWorkingDirectory(pathName);
            if(!flag){
                //创建上传的路径  该方法只能创建一级目录
            	ftpClient.makeDirectory(pathName);
                //指定上传路径
            	ftpClient.changeWorkingDirectory(pathName);
            }
//          ftpClient.enterLocalActiveMode();    //主动模式
            ftpClient.enterLocalPassiveMode(); //被动模式
            ftpClient.storeFile(fileName, inputStream);
            System.out.println("上传文件成功");
            return true;
        }catch (Exception e) {
            System.out.println("上传文件失败" + fileName);
            e.printStackTrace();
            throw new Exception("上传文件失败" + fileName);
        }finally{
        	if(ftpClient != null) {
        		ftpClient.logout();
        		if(ftpClient.isConnected()){ 
        			try{
        				ftpClient.disconnect();
        			}catch(IOException e){
        				e.printStackTrace();
        			}
        		} 
        	}
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            } 
        }
    }
    //改变目录路径
    public boolean changeWorkingDirectory(String directory) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
              System.out.println("进入文件夹" + directory + " 成功！");
            } else {
                System.out.println("进入文件夹" + directory + " 失败！开始创建文件夹");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    public boolean CreateDirecroty(String remote) throws IOException {
        boolean success = true;
        if(StringUtils.isBlank(remote)||remote.equalsIgnoreCase("/")){
        	return true;
        }
        String directory = remote;
        if(!directory.endsWith("/")){
        	directory += "/";
        }
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(path)) {
                    if (makeDirectory(subDirectory)) {
                        changeWorkingDirectory(subDirectory);
                    } else {
                        System.out.println("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(subDirectory);
                    }
                } else {
                    changeWorkingDirectory(subDirectory);
                }
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    //判断ftp服务器文件是否存在    
    public boolean existFile(String path) throws IOException {
            boolean flag = false;
            FTPFile[] ftpFileArr = ftpClient.listFiles(path);
            if (ftpFileArr.length > 0) {
                flag = true;
            }
            return flag;
    }
    //创建目录
    public boolean makeDirectory(String dir) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                System.out.println("创建文件夹" + dir + " 成功！");

            } else {
                System.out.println("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    /** * 下载文件 * 
    * @param pathname FTP服务器文件目录 * 
    * @param filename 文件名称 * 
    * @param localpath 下载后的文件路径 * 
    * @return */
    public  void downloadFile(String pathname, String filename, String localpath){ 
        OutputStream os=null;
        try { 
            System.out.println("开始下载文件");
            initFtpClient();
            //切换FTP目录 
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(pathname); 
            FTPFile[] ftpFiles = ftpClient.listFiles(); 
            for(FTPFile file : ftpFiles){ 
                if(filename.equalsIgnoreCase(file.getName())){ 
                    File localFile = new File(localpath + "/" + file.getName()); 
                    os = new FileOutputStream(localFile); 
                    ftpClient.retrieveFile(file.getName(), os); 
                    os.close(); 
                } 
            } 
            ftpClient.logout(); 
            System.out.println("下载文件成功");
        } catch (Exception e) { 
            System.out.println("下载文件失败");
            e.printStackTrace(); 
        } finally{ 
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            } 
            if(null != os){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            } 
        } 
    }
    
    /** * 删除文件 * 
    * @param pathname FTP服务器保存目录 * 
    * @param filename 要删除的文件名称 * 
    * @return */ 
    public boolean deleteFile(String pathname, String filename){ 
        boolean flag = false; 
        try { 
            System.out.println("开始删除文件");
            initFtpClient();
            //切换FTP目录 
            ftpClient.changeWorkingDirectory(pathname); 
            ftpClient.dele(filename); 
            ftpClient.logout();
            flag = true; 
            System.out.println("删除文件成功");
        } catch (Exception e) { 
            System.out.println("删除文件失败");
            e.printStackTrace(); 
        } finally {
            if(ftpClient.isConnected()){ 
                try{
                    ftpClient.disconnect();
                }catch(IOException e){
                    e.printStackTrace();
                }
            } 
        }
        return flag; 
    }
    /**
     * 批量上传文件，支持断点续传
     * 该上传方法存在一定的问题，如果程序死掉之后，立即重启再继续上传的时候，appendFileStream方法会出现read time out读取超时的异常，导致程序无法执行
     * @param pathName FTP服务器存放文件路径
     * @param fileInfoMap 文件信息map对象，key值是需要上传的文件的名称，文件名必须带上后缀名，value值是需要上传的文件的全路径地址
     * @return
     */
    public boolean uploadFileBatch(String pathName,Map<String, String> fileInfoMap){
    	String fileName = "";
    	initFtpClient();
    	try {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置传输方式为二进制
			ftpClient.enterLocalPassiveMode(); //被动模式
			ftpClient.setBufferSize(1024*1024*50);//设置缓冲区
		} catch (IOException e2) {
			System.out.println("配置FTP服务器信息失败！");
			e2.printStackTrace();
		}
        try{
            System.out.println("开始上传文件");
            for (String key : fileInfoMap.keySet()) {
            	fileName = key;
            	File localFile = new File(fileInfoMap.get(key));
                //检查上传路径是否存在 如果不存在返回false
                boolean flag = ftpClient.changeWorkingDirectory(pathName);
                if(!flag){
                    //创建上传的路径  该方法只能创建一级目录
                	ftpClient.makeDirectory(pathName);
                    //指定上传路径
                	ftpClient.changeWorkingDirectory(pathName);
                }
                String fileAbsPath = pathName+"/"+fileName;
                //查找是否存在相同文件名的文件
                FTPFile[] files = ftpClient.listFiles(fileAbsPath);
                System.out.println("上传文件：............."+fileName);
                RandomAccessFile raf = new RandomAccessFile(localFile, "r");
        		// 设置显示上传进度的的相关变量
        		long step = localFile.length() / 100;
        		long process = 0;
        		long localreadbytes = 0L;
                if(files!=null&&files.length>0){
                	if(files[0].getSize()>localFile.length()){
                		System.out.println("服务器存在的文件比即将上传的文件大，不再进行上传，如要上传请对文件进行重命名！");
                		continue;
                	}else if(files[0].getSize()<localFile.length()){
                		// 断点续传,判断服务器文件是否大于0，如果是，则将指针移动到文件末尾
                		if (files[0].getSize() > 0) {
                			ftpClient.setRestartOffset(files[0].getSize());
                			//设置文件上传进度
                			process = files[0].getSize() / step;
                			raf.seek(files[0].getSize());
                			localreadbytes = files[0].getSize();
                		}
                	}else{
                		System.out.println("文件"+fileName+"已存在，将不再进行上传！");
                		continue;
                	}
                }
                OutputStream out = ftpClient.appendFileStream(fileName);
                byte[] bytes = new byte[1024];
        		int c;
        		while ((c = raf.read(bytes)) != -1) {
        			out.write(bytes, 0, c);
        			localreadbytes += c;
        			if (localreadbytes / step != process) {	
        				process = localreadbytes / step;
        				System.out.println("上传进度:" + process);
        			}
        		}
                out.flush();
        		raf.close();
        		out.close();
        		if(!ftpClient.completePendingCommand()){
        			System.out.println("上传文件失败，删除原有文件，并重新上传"+ files[0].getName());
				}
        		System.out.println("上传..."+fileName+"成功!");
			}
            System.out.println("上传文件完成!");
        }catch (Exception e) {
            System.out.println("上传文件失败" + fileName);
            e.printStackTrace();
            try {
				throw new Exception("上传文件失败" + fileName);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
            return false;
        }finally{
        	if(ftpClient != null) {
        		try {
					ftpClient.logout();
				} catch (IOException e1) {
					e1.printStackTrace();
					return false;
				}
        		if(ftpClient.isConnected()){ 
        			try{
        				ftpClient.disconnect();
        				return true;
        			}catch(IOException e){
        				e.printStackTrace();
        				return false;
        			}
        		} 
        	}
        }
		return false;
    }
    
    
    public static void main(String[] args) {
    	final String FTP_HOST = "183.251.62.117";
        final Integer FTP_PORT = 21;
        final String FTP_USER = "wthxftpuser";
        final String FTP_PWD = "wthx1234";
        
        FtpUtils ftp = new FtpUtils(FTP_HOST, FTP_PORT, FTP_USER, FTP_PWD);
        try {
           if(ftp.uploadFile("/song/", "bbb.mp4", "\\\\192.168.3.100\\qqmusic\\songs\\f\\f\\0a042cec178a45c486796a1d6fdeefc8.m4a")){
        	   System.out.println("成功");
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//			ftp.uploadFile("supertwj/kk", "apache-tomcat.zip", "E://apache-tomcat-8.0.51-windows-x64.zip");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        ftp.downloadFile("supertwj/kk", "apache-tomcat.zip", "F://");
//        ftp.deleteFile("supertwj/kk", "apache-tomcat.zip");
//        System.out.println("ok");
//    	Map<String, String> themap = new HashMap<>();
//    	themap.put("413.mp4", "F:/mv/413.mp4");
//    	themap.put("472.mp4", "F:/mv/472.mp4");
//    	themap.put("475.mp4", "F:/mv/475.mp4");
//    	themap.put("494.mp4", "F:/mv/494.mp4");
//    	ftp.uploadFileBatch("/mv", themap);
    	
    }
}
