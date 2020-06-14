package com.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.webserver.core.ServerContext;

public class HttpRequest {
	private Socket socket;
	private InputStream in;
	/*
	 * 请求对象 HttpRequest的每一个实例用于表示客户端发送过来的 一个具体的请求内容： 一个请求由三部分构成： 请求行、消息头，消息正文
	 */
	private String method;
	// 请求资源的方式
	private String url;
	// 请求资源路径
	private String protocol;

	// url中的请求部分 url中"?"左侧内容
	private String requestURI;
	// url中参数部分 url中“？”右侧内容
	private String queryString;

	// 存放用户信息,key为form表单里面的name属性，value为输入栏输入的值
	private Map<String, String> parameters = new HashMap<String, String>();

	// 请求的版本
	/*
	 * 消息头相关信息的定义
	 * 
	 */
	// 存放消息头的Map
	private Map<String, String> headers = new HashMap<String, String>();

	/*
	 * 消息正文相关信息的定义
	 */
	public HttpRequest(Socket socket) throws EmptyRequestException {
		/*
		 * 在构造方法里解析请求 1、解析请求行 2、解析消息头 3、解析消息正文 定义三个方法接收三个请求
		 */
		try {
			this.socket = socket;
			this.in = socket.getInputStream();
			parseRequestLine();// 解析请求行
			parseHeaders();// 解析消息头
			parseContent();// 解析消息正文
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 解析请求行
	private void parseRequestLine() throws EmptyRequestException {
		try {
			System.out.println("解析请求行...");
			String line = readLine();
			System.out.println("请求行：" + line);
			/*
			 * 解析请求行的步骤： 1、将请求内容按照空格拆分三个部分 2、分别将三部分内容设置到对应的属性上 method,url,protocol
			 * 
			 * 这里将来可能会抛出数组下标越界，原因在于Http协议中也有所提及， 允许客户端连接后发送空请求（实际就是什么都没发送过来）。
			 * 这时候解析请求拆分是不出三项。 后面遇到问题再解决
			 */
			String[] str = line.split("\\s");
			if (str.length < 3) {
				throw new EmptyRequestException();
			}

			method = str[0];
			url = str[1];
			// 进一步解析url
			parseUrl(url);
			protocol = str[2];
			System.out.println("method:" + method);
			System.out.println("url:" + url);
			System.out.println("protocol:" + protocol);
			System.out.println("请求行解析完毕！");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 进一步解析请求行
	public void parseUrl(String url) {
		/*
		 * 1：判断当前url里面是否有问号,url.contains("?")，是否含有参数部分 若没有参数部分，则直接将url赋值给requestURI,
		 * 含有问号才进行下面的操作 2：按照？将url拆分两部分 将？之前的内容设置到requestURI上 将？之后的内容设置到queryString上
		 * 3：将queryString内容进行进一步解析 遍历quertString按照“=”进行拆分的内容 首先按照"&"拆分出每一个参数，然后再将
		 * 每个参数按照“=”拆分，并Put到属性 parameters这个Map中 4、首先将url进行转码，将含有的%XX内容转换为对应的字符
		 * 
		 */
		try {
			this.url = URLDecoder.decode(url, ServerContext.URIEncoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (url.contains("?")) {
			String[] uri = url.split("\\?");
			requestURI = uri[0];
			if (uri.length > 1) {
				queryString = uri[1];
				parseParameters(queryString);
           }
		} else {
			this.requestURI = url;
		}
		System.out.println("requestURI:" + requestURI);
		System.out.println("queryString:" + queryString);
		System.out.println("parameters:" + parameters);
	}
	/*
	 * 解析参数部分 该内容格式为：name=value&name=value...
	 */
	private void parseParameters(String paraLine) {
		String[] para = paraLine.split("&");
		for (String map : para) {
			String[] arr = map.split("=");
			if (arr.length > 1) {
				/**
				 * 这里判断arr.length>1的原因是因为，如果在 表单中某个输入诓没有输入值，那么传递过来的 数据样子会是：
				 * /myweb/reg?username=&password=&nickname=&age=
				 * 
				 */
				parameters.put(arr[0], arr[1]);
			} else {
				parameters.put(arr[0], null);
			}
		}
       System.out.println("parameters:"+parameters);
	}

	// 解析消息头
	private void parseHeaders() {
		try {
			System.out.println("解析消息头...");
			// 用map方式来解析消息头
			/*
			 * 循环调用readline方法读取每行字符串 如果读取的字符串是空字符串则表示单独 读取到了CRLF，那么表示消息头部分读取 完毕，停止循环即可。
			 * 否则读取一行字符串后应当是一个消息头 的内容，接下来将该字符串按照：拆分 为两项，第一项是消息头的名字，第二项 为对应的值，存入到属性headers即可
			 */
			String line = null;
			while ((line = readLine()) != null) {
				String[] str = line.split(": ");
				System.out.println(str.length);
				// System.out.println("本行读取的内容为："+Arrays.toString(str));
				// headers.put(str[0], str[1]);发生下标越界
				if (str.length != 1) {
					for (int i = 0; i <= str.length; i++) {
						headers.put(str[0], str[1]);
					}
				} else {
					break;
				}
			}
			/*
			 * while(true){ String line=readLine(); if("".equals(line)){ break; }else{
			 * String []data=line.split(": "); headers.put(data[0],data[1]); } }
			 */
			System.out.println("消息头为：" + headers);
			Set<Entry<String, String>> header = headers.entrySet();
			for (Entry<String, String> entry : header) {
				System.out.println("key-value：" + entry);
			}
			System.out.println("消息头解析完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 解析消息正文
	private void parseContent() {
		System.out.println("解析消息正文...");
		/*
		 * form表单提交的方式为post的时候，表单提交的信息被包含到消息正文中
		 * post相比get方法来说，保护信息会更加安全，隐秘。因为get方法提交的
		 * 表单会在地址栏的url后面拼接上表单输入框里面的信息
		 * ?username= &password= &nickname=&age= 
		 */
		if (headers.containsKey("Content-Length")) {
			int length = Integer.parseInt(headers.get("Content-Length"));
			// 2、根据长度读取消息正文里面的数据
			try {
				byte[] data = new byte[length];
				in.read(data);// 读到消息正文内容
				// 3、根据消息头Content-Type判断正文内容
				String ContentType = headers.get("Content-Type");
				if ("application/x-www-form-urlencoded".equals(ContentType)) {
					System.out.println("开始解析post提交的form表单");
					// 将消息正文字节转换为字符串(原get请求地址栏"?"右侧内容)
					String line = new String(data, "ISO8859-1");// 得到内容
					System.out.println(line);
					// 打印结果为：username=%E5%BC%A0%E6%AD%A3%E5%85%B5&password=123456
					System.out.println("post提交的form表单解析完毕！");
					try {
						line = URLDecoder.decode(line, ServerContext.URIEncoding);

					} catch (Exception e) {
						e.printStackTrace();
					}
					//解析消息正文内容，将内容分割
					parseParameters(line);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		System.out.println("消息正文解析完毕！");
	}

	public String readLine() throws IOException {
		char a1 = 'a';// 上一次存放的字符
		char a2 = 'a';// 本次存放的字符
		int data = -1;
		StringBuilder sb = new StringBuilder();
		while ((data = in.read()) != -1) {
			a2 = (char) data;
			sb.append(a2);// 将本次的字符存放到StringBuilder里面
			if (a1 == 13 && a2 == 10) {
				break;
			}
			a1 = a2;// 将本次的赋值给a1
		}
		return sb.toString().trim();
	}

	// 获取请求行 method,url,protocol
	public String getMethod() {
		return method;
	}

	public String getUrl() {
		return url;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public String getQueryString() {
		return queryString;
	}

	/*
	 * 根据给定的参数获取对应的参数值 根据给定的username获取对应的值
	 */
	public String getParameter(String name) {
		return this.parameters.get(name);
	}

}
