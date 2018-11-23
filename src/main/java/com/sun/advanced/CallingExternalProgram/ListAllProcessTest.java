/**
 * 
 */
package com.sun.advanced.CallingExternalProgram;


import java.io.BufferedReader;   
import java.io.IOException;   
import java.io.InputStreamReader;   

public class ListAllProcessTest {   

 //列出所有的进程信息   
 public static void main(String[] args) {   
     BufferedReader br=null;   
     try {   
         Process proc=Runtime.getRuntime().exec("tasklist");   
         br=new BufferedReader(new InputStreamReader(proc.getInputStream()));   
         @SuppressWarnings("unused")   
         String line=null;   
         System.out.println("打印所有正在运行的进程信息");   
         while((line=br.readLine())!=null){   
             System.out.println(br.readLine());   
         }   
     } catch (IOException e) {   
         e.printStackTrace();   
     }finally{   
         if(br!=null){   
             try {   
                 br.close();   
             } catch (Exception e) {   
                 e.printStackTrace();   
             }   
         }   
     }   
         

 }   

} 