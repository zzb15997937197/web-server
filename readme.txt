本版本改动：

重构WebServer,使用线程池来管理用于处理客户端请求的线程。
这里应当利用反射机制，根据不同的请求找到对应的Servlet处理
类的名字，然后加载实例化这个类，之后调用其service方法来处
理该请求对应的业务。

而请求与其对应的Servlet名字可以作为配置内容。我们在ServerContext
中定义一个Map,其中key为请求路径，value为对应请求的业务处理类的
名字为XXXServlet.而请求与Servlet的类名可以用一个xml文件来进行
维护。 Map(请求路径，业务处理类(XXXServlet))

这样当ClientHandler在得到一个请求后，可以根据该请求找到对应的servlet

写一个servlet ,删除指定id的用户
比如 http://ip:port/day03/del?id=1


代码路线:
WebServer为启动类:
WebServer-->ClientHandler(接受请求并处理请求)-->HttpServletRequest(解析请求)-->HttpServletResponse(响应客户端)