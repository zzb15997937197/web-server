package com.webserver.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.webserver.http.EmptyRequestException;
import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;
import com.webserver.servlets.HttpServlet;
import com.webserver.servlets.LoginServlet;
import com.webserver.servlets.RegServlet;

public class ClientHandler implements Runnable{
	/*
	 * 处理客户端请求
	 */
   private Socket socket;
   public ClientHandler(Socket socket) {
	   this.socket=socket;
	}
	public void run() {
		try {
			/*
			 * 1、准备工作（解析请求，创建实例对象）
			 * 2、处理请求
			 * 3、响应客户端
			 */
			HttpRequest request=new HttpRequest(socket);
			HttpResponse response=new HttpResponse(socket);
			/*
			 * 2、处理请求s
			 * 根据请求的路径，从webapps目录中找到对应的资源
			 * 
			 * 若资源存在则将该资源响应给客户端
			 * 若没有找到该资源则响应404页面给客户端
			 * 请求行：GET /myweb/reg?username=&password=&nickname=&age= HTTP/1.1
            requestURI:/myweb/reg
            queryString:username=&password=&nickname=&age=
            parameters:{password=null, nickname=null, age=null, username=null}
            method:GET
            url:/myweb/reg?username=&password=&nickname=&age=
            protocol:HTTP/1.1
            String url=request.url;
			 */
			String url=request.getRequestURI();
			//1、首先判断该请求是否请求一个业务处理，如果请求的是/myweb/reg请求的是一个业务
			System.out.println("url:"+url);
             //有多个servlet类，可以将url和servlet类名建立map联系
		 	String servletName=ServerContext.getServletName(url);
			//是否处理业务
			if(servletName!=null) {
				//用反射机制动态加载类
				System.out.println("利用反射加载类："+servletName);
				try {
				Class<?>cls=Class.forName(servletName);
				HttpServlet servlet=(HttpServlet)cls.newInstance();
					/*
					 * 扩展servlet
					 * 如果再来一个servlet，则在servlets包下加新的类
					 * 在conf/servlet.xml文件中添加对应的servlet属性url
					 * 和className。再实现该servlet的里面的service方法
					 * 
					 */
				servlet.service(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				//请求的为一个资源
				File file=new File("webapps"+url);
				//从浏览器地址栏输入地址后自动拼接
				//http://localhost:8088/myweb/index.html
				if(file.exists()) {
					System.out.println("该资源已找到！");
				//发送一个HTTP的响应给客户端
 			  //OutputStream os=socket.getOutputStream();
					response.setEntity(file);
				}else {
				  System.out.println("资源没找到！");
				  //响应404页面
				  response.setStatusCode(404);
				  response.setEntity(new File("webapps/root/404.html"));
				}
			}
			
			//3、响应客户端
			response.flush();
		}catch(EmptyRequestException e) {
			//空请求忽略即可，无需做任何处理
		}finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 读取一行字符串，结束是以连续读取到了CRLF符号为止
	 * 返回的字符串中不含有最后读取到的CRLF
	 * @param in
	 * @return
	 * @throws IOException
	 */
	
//	public String readLine(InputStream in) throws IOException{
//		char a1='a';
//		char a2='a';
//		StringBuilder builder=new StringBuilder();
//		int d=-1;
//		while((d=in.read())!=-1) {
//			a2=(char)d;
//			builder.append(a2);
//			if(a1==13&&a2==10) {
//				break;
//			}
//			a1=a2;
//		}
//		return builder.toString().trim();
//	}
}
