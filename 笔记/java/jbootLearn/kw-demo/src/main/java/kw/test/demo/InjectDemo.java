package kw.test.demo;

import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import kw.test.model.User03;
import kw.test.service.User03Service;

@RequestMapping("injecttest")
public class InjectDemo extends JbootController{
	@Inject
	private User03Service user03Service; 
	
	 public void users() {
	        // 这里用到了 userService 的查询方法
	        List<User03> users = user03Service.findAll();
	        renderText(Arrays.toString(users.toArray()));
	    }
}