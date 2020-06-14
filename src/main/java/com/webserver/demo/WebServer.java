package com.webserver.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.webserver.core.ClientHandler;
import com.webserver.core.ServerContext;

public class WebServer {
	private ExecutorService threadPool; 
	//用于管理处理客户端请求的线程
	private ServerSocket server;
	public WebServer() {
		try {
			server=new ServerSocket(ServerContext.port);//8088
			threadPool=Executors.newFixedThreadPool(ServerContext.maxThreads);
			System.out.println("服务器已启动！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void start() {
		//服务器的启动方法
		/**
		 * 176.217.14.194
		 * 循环接收客户端请求的工作暂时不启动。测试阶段
		 * 只连接一次 www.baidu.com
		 */
		try {
			while(true) {
			System.out.println("等待客户端连接....");
			Socket socket=server.accept();
			System.out.println("一个客户端连接了！");
			ClientHandler clienthandler=new ClientHandler(socket);
			threadPool.execute(clienthandler);
			}
		} catch (Exception e) {
		  e.printStackTrace();	
		}
	}
	
	public static void main(String[]args) {
		WebServer server=new WebServer();
		server.start();
	}
}
