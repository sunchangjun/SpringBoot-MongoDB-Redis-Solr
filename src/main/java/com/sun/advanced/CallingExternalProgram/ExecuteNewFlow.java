/**
 * 
 */
package com.sun.advanced.CallingExternalProgram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author sunchangjunn
 * 2018年11月23日上午10:27:33
 * 含有cd操作的方法示例

1. 问题背景

1.1 java程序运行在/home/lings目录下；

1.2 希望删除/home/test目录下的文件proxy.log；

1.3 调用上面的接口两次？

executeLinuxCmd("cd /home/test");
executeLinuxCmd("rm -fr /home/proxy.log");
是不行的！

1.4 这个接口的调用是单次事务型的，就是每次调用都是独立的事务或者说操作，没有关联的。

那这种“复杂”一点的操作流程怎么办呢？

1.5 方法a: 可以写一个独立的脚本，然后一次运行脚本，这样多复杂的逻辑都没问题。

1.6 方法b: 可以启动一个shell长连接，保持连接，发送多条命令，最后释放连接。
 */
public class ExecuteNewFlow {

	
	public void executeNewFlow() {
        Runtime run = Runtime.getRuntime();
        File wd = new File("/bin");
        System.out.println(wd);
        Process proc = null;
        try {
            proc = run.exec("/bin/bash", null, wd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (proc != null) {
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
            out.println("cd /home/test");
            out.println("pwd");
            out.println("rm -fr /home/proxy.log");
            out.println("exit");//这个命令必须执行，否则in流不结束。
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                proc.waitFor();
                in.close();
                out.close();
                proc.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
