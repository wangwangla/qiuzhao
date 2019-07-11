package kw.test.demo;

import io.jboot.web.controller.annotation.RequestMapping;

import com.jfinal.core.Controller;

@RequestMapping("/test")
public class ControllerTest extends Controller{
	
	public void show01()
	{
		 renderText("show01");
	}
	public void show02()
	{
		 renderText("show02");
	}
	
	
}
