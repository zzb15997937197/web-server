package com.webserver.servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;

public class LoginServlet extends HttpServlet{
	
	public void service(HttpRequest request,HttpResponse response) {
	  /*
       * 登录成功进入学子商城的首页，
       * 登录失败进入自己的首页
       * 
       */
		System.out.println("开始处理登录...");
		
		
		try (RandomAccessFile raf=new RandomAccessFile("user.dat","r");){
			String username=request.getParameter("username");
            System.out.println("username:"+username);
			String password=request.getParameter("password");
			System.out.println("password:"+password);
			boolean flag=false;
			for(int i=0;i<raf.length()/100;i++) {
				//读取第0-32个字节和32-64个字节与username 和password进行比较
				byte[]data=new byte[32];
				raf.read(data);
				String user_name=new String(data,"utf-8").trim();
				raf.read(data);
				String pass_word=new String(data,"utf-8").trim();
				if(username!=null&&password!=null) {
				if(username.equals(user_name)&&password.equals(pass_word)) {
					//登录成功
					flag=true;
					response.setEntity(new File("webapps/myweb/login_success.html"));
					System.out.println("登录成功！");
					break;
				}else {
					raf.seek(100+100*i);
					continue;
				}
			}else {
				System.out.println("用户名和密码为空！");
			}
			}
			if(!flag) {
				response.setEntity(new File("webapps/myweb/login_fail.html"));
				System.out.println("对不起，登录失败！输入不正确！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
