package ls.tools.proxy.jdk;

public class HelloWorldImpl implements HelloWorld {

	public void sayHello() {

		System.out.println("Hello World");
	}

	public void sayHello(String s1) {
		
		System.out.println("Hello World"+s1);
	}

}
