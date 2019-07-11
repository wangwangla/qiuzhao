package kw.test.demo;

import kw.test.model.User03;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("partest")
public class ParDemo extends JbootController{
	public void test(){
		String userName = getPara("username");
		String password = getPara("password");
		User03 user03 = new User03();
		
		for(int i=17;i<26;i++)
		{
			user03.setName(userName);
			user03.setId(i);
			user03.save();
		}
	}
}
