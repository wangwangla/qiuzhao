package kw.test.demo;

import java.util.Arrays;

import javax.inject.Inject;

import com.jfinal.plugin.activerecord.Page;

import kw.test.model.User03;
import kw.test.service.User03Service;

import io.jboot.core.cache.annotation.Cacheable;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("pagetest")
public class PageController extends JbootController{
	@Inject
	private User03Service user03Service;
	@Cacheable(name = "myCache",key = "1")
	public void pagrtest01(){
		int page = getParaToInt("page", 1);
        Page<User03> userPage = (Page<User03>) user03Service.paginate(page, 10);
        renderText(Arrays.toString((userPage.getList()).toArray()));
	}
	@Cacheable(name = "myCache",key = "1")
	public void pagrtest02(){
		int page = getParaToInt("page", 2);
        Page<User03> userPage = (Page<User03>) user03Service.paginate(page, 10);
        renderText(Arrays.toString((userPage.getList()).toArray()));
	}
}
