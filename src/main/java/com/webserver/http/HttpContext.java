package com.webserver.http;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class HttpContext {
	/**
	 * 状态代码与状态描述的关系
	 * key-""
	 * key:状态代码
	 * "":状态描述
	 * 1、状态代码与状态描述之间的哈希表
	 * 2、后缀名与对应的Content-Type的哈希表
	 */
	/*
	 * ASCII码中对应的换行符
	 */
	public static final int CR=13;
	public static final int LF=10;
	
	private static Map<Integer,String> statusCode_Reason_Mapping=
			 new HashMap<Integer,String>();
	private static Map<String,String> MimeMapping=new HashMap<String,String>();
	static {
		//用静态块调用初始化map的方法
		 initStatusCodeReasonMapping();
		 initMimeMapping();
	}
	/*
	 * 私有方法初始化静态代码和静态描述
	 */
	private static void initStatusCodeReasonMapping() {
		statusCode_Reason_Mapping.put(200, "OK");
		statusCode_Reason_Mapping.put(201, "Created");
		statusCode_Reason_Mapping.put(202, "Accepted");
		statusCode_Reason_Mapping.put(204, "Content");
		statusCode_Reason_Mapping.put(301, "Moved Permanently");
		statusCode_Reason_Mapping.put(302, "Moved Temporarily");
		statusCode_Reason_Mapping.put(304, "Not Modified");
		statusCode_Reason_Mapping.put(400, "Bad Request");
		statusCode_Reason_Mapping.put(401, "Unauthorized");
		statusCode_Reason_Mapping.put(403, "Forbidden");
		statusCode_Reason_Mapping.put(404, "Not Found");
		statusCode_Reason_Mapping.put(500, "Internal Server Error");
		statusCode_Reason_Mapping.put(501, "Not Implemented");
		statusCode_Reason_Mapping.put(502, "Bad Gateway");
		statusCode_Reason_Mapping.put(503, "Service Unavailable");
	}
	/*
	 *定义一个静态方法，根据状态代码获取对应的状态描述 
	 *
	 */
	public static String getStatusReason(int statusCode) {
		return statusCode_Reason_Mapping.get(statusCode);
	}
	
	private static void initMimeMapping() {/*
		//将后缀名与对应的Content-Type放入到哈希表中
		MimeMapping.put(".html","text/html");
		MimeMapping.put(".png", "image/png");
		MimeMapping.put(".jpg", "image/jpeg");
		MimeMapping.put(".css", "text/css");
		MimeMapping.put(".gif", "image/gif");
		MimeMapping.put(".js", "application/javascript");
	*/
		/*
		 * 通过解析conf/web.xml文件来完成初始化操作
		 * 
		 * 将web.xml文档中根据标签下所有名为：<mime-mapping>
		 * 的子标签解析出来
		 * 并将其对应的子标签：<extension>中间文本作为key和
		 * <mime-type>中间文本作为value来初始化mimeMapping这个
		 * Map
		 */
		try {
			SAXReader reader=new SAXReader();
			Document doc=reader.read(new File("conf/web.xml"));
			Element root=doc.getRootElement();
			List<Element> elelist=root.elements("mime-mapping");
			for(Element element:elelist) {
				String key=element.elementText("extension");
				String value=element.elementText("mime-type");
				MimeMapping.put(key, value);
			}
			Set<Entry<String,String>> entryset=MimeMapping.entrySet();
			for(Entry<String,String>entry:entryset){
				String key=entry.getKey();
				String value=entry.getValue();
				//System.out.println("key-value:"+key+"="+value);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getContentType(String suffix) {
		return MimeMapping.get(suffix);//根据所给定的后缀名(key)返回对应的value值
	}

	/*	public static void main(String[] args) {
		//用静态块初始化map，类以一加载后进行状态代码与状态描述的初始化
		String reason=getStatusReason(404);
		System.out.println(reason);
		String contenttype=getContentType(".css");
		System.out.println(contenttype);
	}*/
	/*public static void main(String[] args) {
		initMimeMapping();
		System.out.println(MimeMapping.size());
	}*/
}
