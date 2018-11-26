/**
 * 
 */
package com.sun.advanced.CallingExternalProgram;

import java.io.*;

/**
 * 
 * 
 * 
 * @author tyrone
 * 
 * 
 * 
 */
/*
 * 
 * 在网上常见的用java调用外部命令返回结果的方法
PRocess =runtime.exec(cmd) 
is = process.getInputStream(); 
isr=new InputStreamReader(is); 
br =new BufferedReader(isr); 
while( (line = br.readLine()) != null ) 
{ 
out.println(line); 
out.flush(); 

} 


*/
public class CMDExecute{

	/**
	 * 
	 * @param cmd
	 * 
	 * @return
	 * 
	 * @throws IOException
	 * 
	 */
	
	

	public synchronized String run(String[] cmd, String workDirectory) throws IOException {

		String line = null;
		String result = "";

		try {

			ProcessBuilder builder = new ProcessBuilder(cmd);

			//set working directory 
			if (workDirectory != null)

			builder.directory(new File(workDirectory));

			builder.redirectErrorStream(true);

			Process process = builder.start();

			InputStream in = process.getInputStream();

			byte[] re = new byte[1024];

			while (in.read(re) != -1) {

				System.out.println(new String(re));

				result = result + new String(re);

			}

			in.close();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return result;

	}

	/**
	 * 
	 * @param args=cvs log
	 * 
	 */
	public static void main(String[] args) {
		String result = null;

		CMDExecute cmdexe = new CMDExecute();

		try {

			result = cmdexe.run(args, "D: & dir");

			System.out.println(result);

		} catch (IOException ex) {
			ex.printStackTrace();

		}

	}

}