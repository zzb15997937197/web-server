package com.webserver.core;

import java.io.File;
import java.security.KeyStore.Entry.Attribute;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ServerContext {
	//服务端使用的协议版本
	public static String protocol="HTTP1.1";
	
	//服务端使用的端口号
	public static  int port=8080;
	
	//服务端使用解析URI使用的字符集
	public static String URIEncoding="UTF-8";
	
	//服务端线程池中线程数量
	public static int maxThreads=150;
	/**
	 * 
	 * 解析conf/server.xml,将所有配置项用于
	 * 初始化ServerContext属性
	 */
	//请求路径与对应的servlet之间的关联
	private static Map<String,String> servletMapping=new HashMap<String,String>();
	static{//使用静态块初始化静态方法
		init();
		initServletMapping();
	}
	private static void initServletMapping() {
		try {
			SAXReader reader=new SAXReader();
			Document doc=reader.read(new File("conf/servlet.xml"));
			Element root=doc.getRootElement();
			@SuppressWarnings("unchecked")
			List <Element>servlet=root.elements("servlet");
			for(Element s:servlet) {
				String url=s.attributeValue("url");
				String className=s.attributeValue("className");
				servletMapping.put(url, className);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static  String getServletName(String uri) {
		System.out.println("servletMapping:"+servletMapping);
		return servletMapping.get(uri);
	}
	
	private static void init() {
		try {
			SAXReader reader=new SAXReader();
			Document read=reader.read(new File("conf/server.xml"));
			Element root=read.getRootElement();
			Element con=root.element("Connector");
			//获取connectionTimeout
			String CT= con.attribute("connectionTimeout").getValue();
			System.out.println("connectionTimeout:"+CT);
			//获取端口号
			int PORT=Integer.parseInt(con.attribute("port").getValue());
			port=PORT;
			//获取协议版本
			String pro=con.attribute("protocol").getValue();
			protocol=pro;
			String red=con.attribute("redirectPort").getValue();
			//获取最大线程数量
			int maxthreads=Integer.parseInt(con.attributeValue("maxThreads"));
			maxThreads=maxthreads;
			System.out.println(
			"port:"+port+" "+
			"protocol:"+pro+" "+
			"redirectPort:"+red+" "+
			"maxThread:"+maxthreads);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
