package com.sun.util;



import com.jcraft.jsch.*;
import java.io.*;
 
public class ShellExec{
    private static final String USER="root";
    private static final String PASSWORD="Scj19890606";
    private static final String HOST="47.98.153.144";
    private static final int DEFAULT_SSH_PORT=22;
    
    public static void main(String[] args) {
    	exec("free -m");
	}
 
    public static  void exec(String command){
        try{
            JSch jsch=new JSch();
            Session session = jsch.getSession(USER,HOST,DEFAULT_SSH_PORT);
            session.setPassword(PASSWORD);
            //用户名和密码将通过UserInfo界面提供。
            session.setUserInfo(new MyUserInfo());
            session.connect();
            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);
            // 转发
            // channel.setXForwarding(true);
            //channel.setInputStream(System.in);
            channel.setInputStream(null);
            //channel.setOutputStream(System.out);
            //FileOutputStream fos=new FileOutputStream("/tmp/stderr");
            //((ChannelExec)channel).setErrStream(fos);
            ((ChannelExec)channel).setErrStream(System.err);
            InputStream in=channel.getInputStream();
            channel.connect();
 
            byte[] tmp=new byte[1024];
            while(true){
                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    System.out.print(new String(tmp, 0, i));
                }
                if(channel.isClosed()){
                    if(in.available()>0) continue;
                    System.out.println("exit-status: "+channel.getExitStatus());
                    break;
                }
                try{Thread.sleep(1000);}catch(Exception ee){}
            }
            channel.disconnect();
            session.disconnect();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    private static class MyUserInfo implements UserInfo{
        @Override
        public String getPassphrase() {
            System.out.println("getPassphrase");
            return null;
        }
        @Override
        public String getPassword() {
            System.out.println("getPassword");
            return null;
        }
        @Override
        public boolean promptPassword(String s) {
            System.out.println("promptPassword:"+s);
            return false;
        }
        @Override
        public boolean promptPassphrase(String s) {
            System.out.println("promptPassphrase:"+s);
            return false;
        }
        @Override
        public boolean promptYesNo(String s) {
            System.out.println("promptYesNo:"+s);
            return true;//notice here!
        }
        @Override
        public void showMessage(String s) {
            System.out.println("showMessage:"+s);
        }
    }
}