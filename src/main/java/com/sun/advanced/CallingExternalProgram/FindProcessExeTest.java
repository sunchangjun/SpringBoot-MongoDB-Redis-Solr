/**
 * 
 */
package com.sun.advanced.CallingExternalProgram;

import java.io.BufferedReader;   
import java.io.InputStreamReader;   
public class FindProcessExeTest   
{   
    public static void main(String []args){   
            
        if(findProcess("javaw.exe")){   
            System.out.println("------判断指定的进程是否在运行------");   
            System.out.println("QQ.exe该进程正在运行!");   
        }else{   
            System.out.println("------判断指定的进程是否在运行------");   
            System.out.println("QQ.exe该进程没有在运行!");   
        }   
   
    }   
    public static boolean findProcess(String processName){   
        BufferedReader br=null;   
        try{   
               
            //下面这句是列出含有processName的进程图像名   
            Process proc=Runtime.getRuntime().exec("tasklist /FI /"+"IMAGENAME eq "+processName+"/");   
            br=new BufferedReader(new InputStreamReader(proc.getInputStream()));   
            String line=null;   
            while((line=br.readLine())!=null){   
                //判断指定的进程是否在运行   
                if(line.contains(processName)){   
                    return true;   
                }   
            }   
                
            return false;   
        }catch(Exception e){   
            e.printStackTrace();   
            return false;   
        }finally{   
            if(br!=null){   
                try{   
                    br.close();   
                }catch(Exception ex){   
                }   
            }   
                
        }   
    }   
}