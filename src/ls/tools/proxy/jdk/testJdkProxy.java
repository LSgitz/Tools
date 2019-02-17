package ls.tools.proxy.jdk;

public class testJdkProxy {

	public static void main(String[] args) {

		JdkProxyExample jdk = new JdkProxyExample();
		//�󶨹�ϵ����Ϊ���ڽӿ�HelloWorld�£����������������HelloWorld proxy
		HelloWorld proxy = (HelloWorld) jdk.bind(new HelloWorldImpl());
		//ע�⣬��ʱHelloWorld�����Ѿ���һ�������������������߼�����invoke��
		proxy.sayHello();
		proxy.sayHello("11");
	}

}
