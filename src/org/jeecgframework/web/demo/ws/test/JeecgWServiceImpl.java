package org.jeecgframework.web.demo.ws.test;

public class JeecgWServiceImpl implements JeecgWServiceI {

	public String say(String context) {
		// TODO Auto-generated method stub
		return "you say context:"+context;
	}

	public String sayHello() {
		// TODO Auto-generated method stub
		return "sayHello";
	}

	public void sayUI() {
		// TODO Auto-generated method stub
		System.out.println("UI");
	}

}
