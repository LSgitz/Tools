package ls.tools.proxy.interceptor;

import ls.tools.proxy.jdk.HelloWorld;
import ls.tools.proxy.jdk.HelloWorldImpl;

public class testInterceptorJdkProxy {

	public static void main(String[] args) {

//		HelloWorld proxy = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(),"proxy.interceptor.MyInterceptor");
		HelloWorld proxy = (HelloWorld) InterceptorJdkProxy.bind1(new HelloWorldImpl());
		proxy.sayHello();
	}

}
