package com.webserver.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.webserver.core.ServerContext;

public class HttpResponse {
	/**
	 * 状态行相关信息的定义
	 */
	//状态代码
	private int statusCode=200;
	private String statusReason="OK";
	/**
	 * 响应头相关信息的定义
	 * Content-Type:解析数据
	 * Content-Length:
	 */
	private Map<String,String> headers=new HashMap<String,String>();
	
	/**
	 * 响应正文相关信息的定义
	 * 
	 */
	private File entity;
	private Socket socket;
	
	//通过socket获取响应流，用于给客户端发送响应内容
	private OutputStream os;
	
	public HttpResponse(Socket socket) {
		try {
			this.socket=socket;
			this.os=socket.getOutputStream();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * 将当前响应对象内容发送给客户端
	 */
	public void flush() {
		/**
		 * 1、发送状态行
		 * 2、发送响应头
		 * 3、发送响应正文
		 * 定义三个私有方法来完成状态行，响应头，响应正文
		 */
		sendStatus();
		sendHeaders();
		sendContent();
	}
	/*
	 * 向客户端发送一行字符串，该字符串发送后会单独发送CR,LF
	 */
	
	private void println(String line) {
		try {
			os.write(line.getBytes("ISO8859-1"));
			os.write(HttpContext.CR);
			os.write(HttpContext.LF);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendStatus() {
		//状态行 协议版本 状态代码 状态描述
		String line=ServerContext.protocol+" "+statusCode+" "+statusReason;
		try {
			println(line);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//发送响应头
	private void sendHeaders() {
		//发送响应头,Map中有几个响应头就发送几个
		try{
			/*String line="Content-Type: text/html";
			os.write(line.getBytes("ISO8859-1"));
			os.write(13);
			os.write(10);
			line="Content-Length: "+entity.length();
			os.write(line.getBytes("ISO8859-1"));
			os.write(13);
			os.write(10);*/
		   Set<Entry<String,String>> entrySet=headers.entrySet();
		   for(Entry<String,String>entry:entrySet) {
			   String key=entry.getKey();
			   String value=entry.getValue();
			   String line=key+": "+value;
			   println(line);
		   }
			println("");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//发送响应正文
	private void sendContent() {
	 //发送响应正文
	if(entity!=null) {
		try (FileInputStream fis=new FileInputStream(entity)
		){
			//将weaindex.html所有内容以字节的形式发送给客户端
			int len=-1;
			byte[]data=new byte[1024*10];
			while((len=fis.read(data))!=-1) {
				os.write(data,0,len);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
	
	/*
	 * 用set和get来设置和获取HttpResponse私有属性
	 */
	public File getEntity() {
		return entity;
	}
	public void setEntity(File entity) {
		this.entity = entity;
		/*
		 * 设置需要的响应实体资源文件
		 * 在设置的同时会自动添加两个响应头
		 * Content-Type与Content-Length
		 */
		this.headers.put("Content-Length", entity.length()+"");
		/*
		 * 设置Content-Type时，要先根据entity文件的后缀名
		 * 得到对应的值，然后根据后缀名调用HttpContext类里面的
		 * getContentType(后缀名)获取
		 */
		String fileName=entity.getName();
		int index=fileName.lastIndexOf('.')+1;
		String suffix=fileName.substring(index);
		String contenttype=HttpContext.getContentType(suffix);
		this.headers.put("Content-Type", contenttype);
	}

	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		this.statusReason=HttpContext.getStatusReason(statusCode);
	}
	public String getStatusReason() {
		return statusReason;
	}
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	/*
	 * 向当前响应中设置一个响应头信息
	 * (后期自行重构时，还会添加获取头，以及删除头的操作)
	 * 
	 */
	public void putHeader(String name,String value) {
		this.headers.put(name, value);
	}
	
	
	
	
}
