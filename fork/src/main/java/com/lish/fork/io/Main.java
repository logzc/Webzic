package com.lish.fork.io;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by lishuang on 2016/5/23.
 */
public class Main {

    public static void main(String[] args){
        copyFileDemo();

    }


    public static void copyFileDemo(){
        URL url= Thread.currentThread().getContextClassLoader().getResource("com/lish/fork/io/Demo_File_Copy.txt");

        if(url!=null){
            File testFile=new File(url.getPath());
            String dir=testFile.getParent();
            String fileName=testFile.getName();


            String destFileName=dir+File.separator+"Copy_Of_"+fileName;
            System.out.println(destFileName);
            File destFile=new File(destFileName);

            try {
                FileUtils.copyFile(testFile,destFile);

                System.out.println("Finish copying.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
