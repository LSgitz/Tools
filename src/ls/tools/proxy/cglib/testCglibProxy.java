package ls.tools.proxy.cglib;

public class testCglibProxy {

	public static void main(String[] args) {

		CglibProxyExample cpe = new CglibProxyExample();
		//
		CglibImpl proxy = (CglibImpl) cpe.getProxy(CglibImpl.class);
		//
		proxy.sayHello();
	}
}
