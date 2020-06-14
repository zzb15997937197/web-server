package com.webserver.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;

public class RegServlet extends HttpServlet {
	//运行在服务器端的小程序
	public void service(HttpRequest request,HttpResponse response) {
		System.out.println("开始处理注册......");
		/**
		 * 处理注册流程
		 * 1、通过request获取用户表单提交上来的注册用户信息
		 * 2、将该信息写入到user.dat文件中
		 * 3、设置response对象，将注册成功的页面响应给客户端
		 */

		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String nickname=request.getParameter("nickname");
		//NumberFormatException
		int age=Integer.parseInt(request.getParameter("age"));
		
		System.out.println(username+","+password+","+nickname+","+age);
		/*
		 * 2、将注册信息写入到user.dat文件中
		 * 每条记录占用100字节，其中，用户名，密码，昵称为字符串
		 * 各占用32字节，年龄为int值占用4个字节
		 * 
		 */
		try (RandomAccessFile raf=new RandomAccessFile("user.dat","rw");){
			  raf.seek(raf.length());
				byte[]data=username.getBytes("UTF-8");
				data=Arrays.copyOf(data, 32);
				raf.write(data);//将数据写入到文件中
				data=password.getBytes("UTF-8");
				data=Arrays.copyOf(data, 32);
				raf.write(data);
				data=nickname.getBytes("UTF-8");
				data=Arrays.copyOf(data, 32);
				raf.write(data);
				data=request.getParameter("age").getBytes("UTF-8");
				data=Arrays.copyOf(data, 4);
				raf.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		File file=new File("webapps/myweb/reg_success.html");
		response.setEntity(file);
	 
		
	
		
		/*user user=new user(username,password,nickname,age);
		lists.add(user);
		try {
			FileOutputStream fos=new FileOutputStream("user.dat");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(user);
			oos.close();
			FileInputStream fis=new FileInputStream("user.dat");
			ObjectInputStream ois=new ObjectInputStream(fis);
			try {
				user users=(user)ois.readObject();
				System.out.println(users);
				ois.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
						e.printStackTrace();
		}*/
		System.out.println("注册处理完毕！");
	}
	

}
