package com.sun.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

import org.apache.commons.lang3.StringUtils;

/**
 * 文件工具类
 *
 * @author alistair.chow
 * @date 2018-05-16
 */
public class FileUtils {
	private static final int BUFFER_SIZE = 1024 * 1024 * 10;
    /**
     * 判断文件是否存在，若不存在则创建
     * @param dir
     */
    public static void mkdirs(String dir){
        if(StringUtils.isEmpty(dir)){
            return;
        }

        File file = new File(dir);
        if(file.isDirectory()){
            return;
        }else {
            file.mkdirs();
        }
    }
    
    /**
     * 拷贝文件
     * @param source
     * @param dest
     * @throws IOException
     */
    public static void copyFile(String source, String dest) throws IOException {    
        InputStream input = null;    
        OutputStream output = null;    
        try {
               input = new FileInputStream(source);
               output = new FileOutputStream(dest);        
               byte[] buf = new byte[1024];        
               int bytesRead;        
               while ((bytesRead = input.read(buf)) > 0) {
                   output.write(buf, 0, bytesRead);
               }
        } finally {
            input.close();
            output.close();
        }
    }
    
    
	public void  copyFile(){
		InputStream io= null;
		OutputStream os =null;
		try {
			File file =new  File("\\\\192.168.3.100\\qqmusic\\songs\\b\\k\\e04792b79a234291ba7b50e40149e576.flac");
			 io= new  FileInputStream(file);
			 os = new  FileOutputStream(new  File("G:\\down\\"+file.getName()));
				
		    int len;
		    byte[] bs = new byte[1024];
	        // 开始读取
	        while ((len = io.read(bs)) != -1) {
	            os.write(bs, 0, len);
	        }
			  
			System.out.println(file.getName());
		} catch(Exception e){
			e.printStackTrace();
			
		}finally {
		    try {
                os.close();
                io.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}

	}
    
    /**
     * 拷贝文件（管道）
     * @param fromFile
     * @param toFile
     */
    public static void copyFileByChannel(String fromFile, String toFile) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannelInput = null;
        FileChannel fileChannelOutput = null;
        try {
            fileInputStream = new FileInputStream(fromFile);
            fileOutputStream = new FileOutputStream(toFile);
            //得到fileInputStream的文件通道
            fileChannelInput = fileInputStream.getChannel();
            //得到fileOutputStream的文件通道
            fileChannelOutput = fileOutputStream.getChannel();
            //将fileChannelInput通道的数据，写入到fileChannelOutput通道
//            fileChannelInput.transferTo(0, fileChannelInput.size(), fileChannelOutput);
            fileChannelOutput.transferFrom(fileChannelInput, 0, fileChannelInput.size());
            System.out.println(toFile + "复制完毕");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
                if (fileChannelInput != null)
                    fileChannelInput.close();
                if (fileOutputStream != null)
                    fileOutputStream.close();
                if (fileChannelOutput != null)
                    fileChannelOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
	 * 剪切文件
	 * @param oldFile
	 * @param newFile
	 * @return
	 * @throws IOException 
	 */
	public static void cutFile(String source, String dest) throws IOException {
		File oldFile = new File(source);
		File newFile = new File(dest);
		if(!newFile.exists()) {
			Files.copy(oldFile.toPath(), newFile.toPath());
		}
		Files.deleteIfExists(oldFile.toPath());
	}
	
	
    /**
     * 使用文件内存映射实现
     * @throws IOException
     */
    private static void coypBigFile(String sourceFile, String targetFile) throws IOException {
        FileInputStream fin = new FileInputStream(sourceFile);
        RandomAccessFile fout = new RandomAccessFile(targetFile, "rw");
        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();
        MappedByteBuffer mbbi = null;
    	MappedByteBuffer mbbo = null;
    	int size = 1024 * 1024;
    	for(long i = 0L; i < fcin.size(); i += size) {
    		if(i + size < fcin.size()) {
    			mbbi = fcin.map(FileChannel.MapMode.READ_ONLY, i,
    					size);
    			mbbo = fcout.map(FileChannel.MapMode.READ_WRITE, i,
    					size);
    		} else {
    			mbbi = fcin.map(FileChannel.MapMode.READ_ONLY, i,
    					fcin.size() - i);
    			mbbo = fcout.map(FileChannel.MapMode.READ_WRITE, i,
    					fcin.size() - i);
    		}
    		mbbo.put(mbbi);
    		mbbi.clear();
    		mbbo.clear();
    	}
    	
    	fin.close();
    	fout.close();
    }
}
