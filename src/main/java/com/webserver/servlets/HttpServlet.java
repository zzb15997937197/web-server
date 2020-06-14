package com.webserver.servlets;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;

public abstract class HttpServlet {
	//作为超类，抽共性，规定所有Servlet具有相同的行为。
	/*
	 * 这里定义一个抽象方法service,要求所有的servlet
	 * 都必须含有该方法，用于处理业务。但是由于不同的
	 * Servlet处理的业务不同，该方法是抽象的。
	 * 同类里面的相同行为抽方法，不同类的相同方法，定义超类
	 */
	public abstract void service(HttpRequest request,HttpResponse reponse);

}
