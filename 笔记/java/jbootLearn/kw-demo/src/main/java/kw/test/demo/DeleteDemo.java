package kw.test.demo;

import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

import javax.inject.Inject;

import kw.test.service.User03Service;

@RequestMapping("del")
public class DeleteDemo extends JbootController {
	@Inject
	private User03Service userService ;
	public void delete(){
	    userService.deleteById(1);
	    renderText("删除成功");
	}
}
