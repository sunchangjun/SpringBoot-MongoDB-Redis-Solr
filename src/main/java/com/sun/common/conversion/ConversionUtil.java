
package com.sun.common.conversion;

import java.io.*;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 转换工具类
 * @author sunchangjunn 2018年8月16日下午2:29:04
 */
public class ConversionUtil {

	public static byte[] fileToByte(File file){
		ByteArrayOutputStream bos=null;
		BufferedInputStream in=null;
		try{
			if(!file.exists()){
				throw new FileNotFoundException("file not exists");
			}
			bos=new ByteArrayOutputStream((int)file.length());
			in=new BufferedInputStream(new FileInputStream(file));
			int buf_size=1024;
			byte[] buffer=new byte[buf_size];
			int len=0;
			while(-1 != (len=in.read(buffer,0,buf_size))){
				bos.write(buffer,0,len);
			}
			return bos.toByteArray();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		} finally{
			try{
				if(in!=null){
					in.close();
				}
				if(bos!=null){
					bos.close();
				}
			} catch(Exception e){
				e.printStackTrace();
			}


		}

	}


	/**
	 * 根据byte数组，生成文件
	 * @param bfile 文件数组
	 * @param filePath 文件存放路径
	 * @param fileName 文件名称
	 */
	public static void byteToFile(byte[] bfile,String filePath,String fileName){
		BufferedOutputStream bos=null;
		FileOutputStream fos=null;
		File file=null;
		try{
			File dir=new File(filePath);
			if(!dir.exists() && !dir.isDirectory()){//判断文件目录是否存在
				dir.mkdirs();
			}
			file=new File(filePath+fileName);
			fos=new FileOutputStream(file);
			bos=new BufferedOutputStream(fos);
			bos.write(bfile);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally{
			try{
				if(bos != null){
					bos.close();
				}
				if(fos != null){
					fos.close();
				}
			} catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}


	/**
	 * javaBean转换为map
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> javaBeanToMap(Object javaBean) {
		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Object> map = oMapper.convertValue(javaBean, Map.class);
		return  map;
	}
	
	
	/**
	 * map转换为javaBean
	 * @param map
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("hiding")
	public static <T> T mapToJavaBean(Map<String,Object> map,Class<T> clazz ) {
		ObjectMapper oMapper = new ObjectMapper();
		return	oMapper.convertValue(map,clazz);
		
	}



	/**
	 * 将byte[]转换成file
	 * @param bytes
	 * @param fileURI
	 * @return
	 */
	public static File byteToFile(byte[] bytes,String fileURI){
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		File dir = null;
		try {
			System.out.println(fileURI.substring(0,fileURI.lastIndexOf("/")));
			dir = new File(fileURI.substring(0,fileURI.lastIndexOf("/")+1).replace("/", "\\"));
			if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
				dir.mkdirs();
			}
			file = new File(fileURI);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return file;
	}
	
	/**
	   * xml转换成JavaBean
	   * @param xml
	   * @param c
	   * @return
	   */
	  @SuppressWarnings("unchecked")
	  public static <T> T converyToJavaBean(String xml, Class<T> c) {
	    T t = null;
	    try {
	      JAXBContext context = JAXBContext.newInstance(c);
	      Unmarshaller unmarshaller = context.createUnmarshaller();
	      t = (T) unmarshaller.unmarshal(new StringReader(xml));
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return t;
	  }
}
