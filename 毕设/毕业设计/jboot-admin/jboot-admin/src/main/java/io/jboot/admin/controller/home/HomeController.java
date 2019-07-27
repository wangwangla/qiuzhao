package io.jboot.admin.controller.home;

import java.util.List;

import com.jfinal.plugin.activerecord.Record;

import io.jboot.admin.base.web.base.BaseController;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/home/info")
public class HomeController extends BaseController {
	public void index()
	{
        render("/template/welcome.html");
	}
}
