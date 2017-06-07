package org.jeecgframework.web.demo.ws.test;

public class JeecgWDemoServiceImpl implements JeecgWServiceI {

	public String say(String context) {
		// TODO Auto-generated method stub
		return "you say context demo:"+context;
	}

	public String sayHello() {
		// TODO Auto-generated method stub
		return "sayHello demo";
	}

	public void sayUI() {
		// TODO Auto-generated method stub
		System.out.println("UI demo");
	}

}
