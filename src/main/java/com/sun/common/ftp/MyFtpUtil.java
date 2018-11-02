package com.sun.common.ftp;

import com.alibaba.fastjson.JSONObject;
import com.sun.enums.FtpConstant;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FTP java操作类
 */
public class MyFtpUtil {
    private static Logger logger = LoggerFactory.getLogger(MyFtpUtil.class);

    private static final int BUFFER_SIZE = 1024 * 1024 * 4;

    /**
     * OPTS UTF8字符串常量
     **/
    private static final String OPTS_UTF8 = "OPTS UTF8";

    /**
     * 本地字符编码
     **/
    private static String localCharset = "GBK";

    /**
     * UTF-8字符编码
     **/
    private static final String CHARSET_UTF8 = "UTF-8";

    /**
     * FTP协议里面，规定文件名编码为iso-8859-1
     **/
    private static String serverCharset = "ISO-8859-1";

    public FTPClient ftpClient = null;
//    @Test
    public  void initFtpClient()   {
        try {
            ftpClient = new FTPClient();
            ftpClient.setControlEncoding("utf-8");
            ftpClient.setConnectTimeout(5*60*1000);
            ftpClient.setDefaultTimeout(5*60*1000);
            ftpClient.setDataTimeout(5*60*1000);
            /*连接*/
            ftpClient.connect(FtpConstant.ftp_144_host,21);
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            if(!FTPReply.isPositiveCompletion(replyCode)){
                Exception ee = new Exception("无法连接到 :" + FtpConstant.ftp_144_host);
                throw ee;
            }
            logger.info("成功连接FTP服务器:地址={},用户={}",FtpConstant.ftp_144_host,FtpConstant.ftp_144_user);
            /*登录*/
            boolean bool = ftpClient.login(FtpConstant.ftp_144_user,FtpConstant.ftp_144_password);
            if (!bool) {
                Exception ee = new Exception("登录失败,检查账号密码");
                throw ee;
            }
            logger.info("成功登录FTP服务器:地址={},用户={}",FtpConstant.ftp_144_host,FtpConstant.ftp_144_user);


        }catch (Exception e){
        e.printStackTrace();
        }


    }

    /**
     *
     * @param ftpPath  ftp上保存的目录
     * @param savePath 本地文件目录(绝对路径带文件地址)
     * @param fileName  保存别名
     */
    public  void uploadFie(String ftpPath, String savePath, String fileName){
        InputStream inputStream = null;
        try {
            File file= new File(savePath);
            if (null == ftpClient){
                initFtpClient();
            }
            inputStream = new FileInputStream(file);
            // 设置编码：开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
                localCharset = CHARSET_UTF8;
            }
            ftpClient.setControlEncoding(localCharset);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setBufferSize(BUFFER_SIZE);
            /*设置主动模式:不设置默认主动模式*/
            ftpClient.enterLocalActiveMode();
            /*启用或禁用验证参与数据连接的远程主机与连接控制连接的主机相同的验证。*/
            ftpClient.setRemoteVerificationEnabled(false);
            /* 设置被动模式，开通一个端口来传输数据*/
//           ftpClient.enterLocalPassiveMode();

            boolean boo= ftpClient.storeFile(new String(fileName.getBytes(localCharset), serverCharset),inputStream);
            logger.info(""+boo);
        }catch(Exception e){
        e.printStackTrace();
        }finally {
            try {
                ftpClient.disconnect(); //解除连接
                logger.info("关闭FTP连接");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public String printWorkingDirectory(){
        String currPath= "";
        if(null == ftpClient){
            initFtpClient();
        }
        try { currPath= ftpClient.printWorkingDirectory();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

        return currPath;
    }

    /**
     * 远程文件上传到FTP服务器
     *
     * @param ftpPath    FTP服务器文件相对路径，例如：test/123
     * @param remotePath 远程文件路径，例如：http://www.baidu.com/xxx/xxx.jpg
     * @param fileName   上传到FTP服务的文件名，例如：test.jpg
     * @return boolean 成功返回true，否则返回false
     * yes
     */
    public boolean uploadRemoteFile(String ftpPath, String remotePath, String fileName) {
        // 登录
        if(null == ftpClient){
            initFtpClient();
        }
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
                /*启用或禁用验证参与数据连接的远程主机与连接控制连接的主机相同的验证。*/
                ftpClient.setRemoteVerificationEnabled(false);
                String path = changeEncoding("/" + ftpPath);
                // 目录不存在，则递归创建
                if (!ftpClient.changeWorkingDirectory(path)) {
                    this.createDirectorys(path);
                }

                // 上传文件
                flag = ftpClient.storeFile(new String(fileName.getBytes(localCharset), serverCharset), input);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("远程文件上传FTP失败", e);
            } finally {

                try {
                    ftpClient.disconnect(); //解除连接
                    httpClient.close();
                    logger.info("关闭FTP连接");

                } catch (IOException e) {
                    logger.error("关闭流失败", e);
                }
                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        logger.error("关闭流失败", e);
                    }
                }
            }
        }
        return flag;
    }

    /**
     *      * 在服务器上递归创建目录
     *      *
     *      * @param dirPath 上传目录路径
     *      * @return
     *     
     */
    private void createDirectorys(String dirPath) {
        try {
            if (!dirPath.endsWith("/")) {
                dirPath += "/";
            }
            String directory = dirPath.substring(0, dirPath.lastIndexOf("/") + 1);
            ftpClient.makeDirectory("/");
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            }else{
                start = 0;
            }
            end = directory.indexOf("/", start);
            while(true) {
                String subDirectory = new String(dirPath.substring(start, end));
                if (!ftpClient.changeWorkingDirectory(subDirectory)) {
                    if (ftpClient.makeDirectory(subDirectory)) {
                        ftpClient.changeWorkingDirectory(subDirectory);
                    } else {
                        logger.info("创建目录失败");
                        return;
                    }
                }
                start = end + 1;
                end = directory.indexOf("/", start);
                //检查所有目录是否创建完毕  
                if (end <= start){
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("上传目录创建失败", e);
        }
    }


    /**
     * FTP服务器路径编码转换
     *
     * @param ftpPath FTP服务器路径
     * @return String
     */
    private  String changeEncoding(String ftpPath) {
        String directory = null;
        try {
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
                localCharset = CHARSET_UTF8;
            }
            directory = new String(ftpPath.getBytes(localCharset), serverCharset);
        } catch (Exception e) {
            logger.error("路径编码转换失败", e);
        }
        return directory;
    }

    /*删除ftp的文件*/
    public boolean  deleteFile(String pathname ){
        boolean bool =false;
        if(null == ftpClient){
            initFtpClient();
        }
        try {
            bool= ftpClient.deleteFile(pathname);
        logger.info(""+bool);
        }catch(Exception e){
            e.printStackTrace();
        }
    return bool;
    }



    public  void listRemoteFiles() {
        String regStr="aaa";
        if(null == ftpClient){
            initFtpClient();
        }
    /*启用或禁用验证参与数据连接的远程主机与连接控制连接的主机相同的验证。*/
    ftpClient.setRemoteVerificationEnabled(false);
        try {
            // FtpClient.changeWorkingDirectory(regStr);
            String files[] = ftpClient.listNames(regStr);
            if (files == null || files.length == 0)
                System.out.println("There has not any file!");
            else {
                for (int i = 0; i < files.length; i++ ) {
                    System.out.println(files[i]);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 列出Ftp服务器上的所有文件和目录
     *
     */
    public  void listRemoteAllFiles() {
        if(null == ftpClient){
            initFtpClient();
        }
        /*启用或禁用验证参与数据连接的远程主机与连接控制连接的主机相同的验证。*/
        ftpClient.setRemoteVerificationEnabled(false);
        try {
            String[] names = ftpClient.listNames();
            for (int i = 0; i < names.length; i++ ) {
                System.out.println(names[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*文件改名*/
    public  void renameFile(String oldFileName, String newFileName) {
        try {
            if(null == ftpClient){
                initFtpClient();
            }
            ftpClient.rename(oldFileName, newFileName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /*创建文件夹*/
    public  boolean makeDirectory(String dir) {
        if(null == ftpClient){
            initFtpClient();
        }
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                System.out.println("创建文件夹" +dir +" 成功");
            } else {
                System.out.println("创建文件夹 " +dir+ " 失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /*删除目录*/
    public boolean 	removeDirectory(String pathname){
        boolean flag =false;
        if(null == ftpClient){
            initFtpClient();
        }
        try {
            /*设置主动模式:不设置默认主动模式*/
            ftpClient.enterLocalActiveMode();
            /*启用或禁用验证参与数据连接的远程主机与连接控制连接的主机相同的验证。*/
            ftpClient.setRemoteVerificationEnabled(false);
            flag= ftpClient.removeDirectory(pathname) ;
        }catch(Exception e){
        e.printStackTrace();
        }
        return flag;
    }
    /*进入某个目录下*/
    public  boolean changeWorkingDirectory(String directory) {
        boolean flag = false;
        if(null == ftpClient){
            initFtpClient();
        }
        try {
            ftpClient.changeWorkingDirectory(directory);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    public void test() throws IOException {
//        renameFile("aaa.txt","bbb.txt");
//        listRemoteAllFiles();
//        makeDirectory("/sunchangjunn/sss");
//        changeWorkingDirectory("/");
//        listRemoteAllFiles();
//      boolean b=  downloadFile("/sunchangjunn/sss","err.txt","G:\\down");
//       logger.info("结果"+b);

//        createDirectorys("/sunchangjunn/ssa");
//        logger.info("当前路径:"+printWorkingDirectory());;

//      boolean bool=  isDirExist("/sunchangjunn/sss/ccc");
//        logger.info("结果"+bool);

//        FTPFile [] ftpFiles =listDirectories("/");
//        logger.info("结果"+ JSONObject.toJSONString(ftpFiles));
//

        makeDirectorys("/sunchangjunn/ccc/ddd");
    }

    /**创建目录*/
    public boolean makeDirectorys(String ftpPat){
        try {
            if(null == ftpClient){
                initFtpClient();
            }
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
                logger.info("结果"+boo);
                System.out.println(JSONObject.toJSONString(sbf.toString()));
               if (!boo){
                   logger.info("路径不存在,创建路径"+sbf.toString());
                   ftpClient.makeDirectory(sbf.toString());
               }else{
                   logger.info("路径存在,不用创建");
               }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }


    /** * 下载文件 *
     * @param pathname FTP服务器文件目录 *
     * @param filename 文件名称 *
     * @param localpath 下载后的文件路径 *
     * @return  yes
     *
     * */
    public  boolean downloadFile(String pathname, String filename, String localpath){
        boolean flag = true;
        OutputStream os=null;
        try {
            System.out.println("开始下载文件");
            initFtpClient();
            //切换FTP目录
            ftpClient.changeWorkingDirectory(pathname);
            /*启用或禁用验证参与数据连接的远程主机与连接控制连接的主机相同的验证。*/
            ftpClient.setRemoteVerificationEnabled(false);
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for(FTPFile file : ftpFiles){
                if(filename.equalsIgnoreCase(file.getName())){
                    File localFile = new File(localpath + "/" + file.getName());
                    os = new FileOutputStream(localFile);
                    flag= ftpClient.retrieveFile(file.getName(), os);
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

        return flag;
    }
    /*获取当前路径下的目录*/
    public void listDirectories() throws IOException {
        if(null == ftpClient){
            initFtpClient();
        }
        /*设置主动模式:不设置默认主动模式*/
        ftpClient.enterLocalActiveMode();
        /*启用或禁用验证参与数据连接的远程主机与连接控制连接的主机相同的验证。*/
        ftpClient.setRemoteVerificationEnabled(false);
        FTPFile [] ftpFiles = ftpClient.listDirectories();

    }
    /*获取指定路径下的目录*/
    public FTPFile [] listDirectories(String path) throws IOException {
        if(null == ftpClient){
            initFtpClient();
        }
        /*设置主动模式:不设置默认主动模式*/
        ftpClient.enterLocalActiveMode();
        /*启用或禁用验证参与数据连接的远程主机与连接控制连接的主机相同的验证。*/
        ftpClient.setRemoteVerificationEnabled(false);
        FTPFile [] ftpFiles = ftpClient.listDirectories(path);

        return ftpFiles;
    }
    /*检查路径存在:有路径则进入成功,失败则无此路径*/
    public  boolean isDirExist(String  ftpPath){

        boolean flag=false;
        try {
            if(null == ftpClient){
                initFtpClient();
            }
            flag =ftpClient.changeWorkingDirectory(ftpPath);
        }catch(Exception e){
            e.printStackTrace();
        }
    return flag;
    }



}
