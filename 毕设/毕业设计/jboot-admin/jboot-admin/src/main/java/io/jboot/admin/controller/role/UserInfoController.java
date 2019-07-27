package io.jboot.admin.controller.role;

import com.jfinal.plugin.activerecord.Page;

import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.RoleService;
import io.jboot.admin.service.api.UserService;
import io.jboot.admin.service.entity.model.CommodityInfo;
import io.jboot.admin.service.entity.model.Role;
import io.jboot.admin.service.entity.model.User;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/user/info")
public class UserInfoController extends BaseController{
	@JbootrpcService
	private UserService userService;
	@JbootrpcService
	private RoleService roleService;
    /**
     * index
     */
    public void index() {
        render("main.html");
    }

    /**
     * 表格数据
     */
    public void tableData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);
        User user = new User();
        Page<User> dataPage = userService.findPage(user,pageNumber, pageSize);
        renderJson(new DataTable<User>(dataPage));
    }
    
    public void roleTableData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);
        Role role = new Role();
        Page<Role> dataPage = roleService.findPage(role,pageNumber, pageSize);
        renderJson(new DataTable<Role>(dataPage));
    }
    
}
