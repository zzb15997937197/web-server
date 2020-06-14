package com.webserver.demo;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RegDemo {
	/*
	 * 获取user.dat里面的信息
	 */
	public static void main(String[]args) throws IOException{
		RandomAccessFile raf
		   =new RandomAccessFile("user.dat","r");
		
		for(int i=0;i<raf.length()/100;i++) {
			//读用户名
			
			byte[]data=new byte[32];
			raf.read(data);
			String username=new String(data,"utf-8").trim();
			System.out.print("用户名为："+username);
			
			//读密码
			raf.read(data);
			String password=new String(data,"utf-8").trim();
			System.out.print("密码为："+password);
			
			//读昵称
			raf.read(data);
			String nickname=new String(data,"utf-8").trim();
			System.out.print("昵称为："+nickname);
			
			//读年龄
			raf.read(data);
			String age=new String(data,"utf-8").trim();//掩盖输入后面多余的空白
			System.out.print("年龄为："+age);
			raf.seek(100+100*i);//移动指针
			System.out.println();
		}
		raf.close();
	}
	

}
