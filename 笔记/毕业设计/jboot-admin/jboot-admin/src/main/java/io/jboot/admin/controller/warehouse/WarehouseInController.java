package io.jboot.admin.controller.warehouse;

import com.jfinal.plugin.activerecord.Page;

import io.jboot.admin.base.common.RestResult;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.base.interceptor.NotNullPara;
import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.WaregouseIntoService;
import io.jboot.admin.service.api.WarehouseInfoService;
import io.jboot.admin.service.entity.model.WaregouseInto;
import io.jboot.admin.service.entity.model.WarehouseInfo;
import io.jboot.admin.support.auth.AuthUtils;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;


@RequestMapping("/warehouse/into")
public class WarehouseInController  extends BaseController {
	
	@JbootrpcService
	private WaregouseIntoService dataService;
	
	
	public void tongji() {
		render("tongji.html");
	}

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
	   WaregouseInto waregouseInto = new WaregouseInto();
	   String type = getPara("eqiupType");
	   String eqiupXinghao =getPara("eqiupXinghao");
	   String eqiupName = getPara("eqiupName");
	   System.out.println(type+"==========="+eqiupXinghao+"----------"+eqiupName);
	   waregouseInto.setEqiupType(type);
	   waregouseInto.setEqiupXinghao(eqiupXinghao);
	   waregouseInto.setEqiupName(eqiupName);
       int pageNumber = getParaToInt("pageNumber", 1);
       int pageSize = getParaToInt("pageSize", 30);
       Page<WaregouseInto> dataPage = dataService.findPage(waregouseInto,pageNumber, pageSize);
       renderJson(new DataTable<WaregouseInto>(dataPage));
   }

   /**
    * add
    */
   public void add() {
       render("add.html");
   }

   /**
    * 保存提交
    */
   public void postAdd() {
	   WaregouseInto data = getBean(WaregouseInto.class, "warehouseInto");
       data.setEquipLead(AuthUtils.getLoginUser().getName());
       data.setEquipStatus("设备入库");
       if (!dataService.save(data)) {
           throw new BusinessException("保存失败");
       }
       renderJson(RestResult.buildSuccess());
   }

   /**
    * updat
    */
   @NotNullPara({"id"})
   public void update() {
       String id = getPara("id");
       WaregouseInto data = dataService.findById(id);
       setAttr("data", data).render("update.html");
   }

   /**
    * 修改提交
    */
   public void postUpdate() {
	   WaregouseInto data = getBean(WaregouseInto.class, "warehouseInto");
	   System.out.println("============"+data+"============");
       if (dataService.findById(data.getEqiupId()) == null) {
           throw new BusinessException("数据不存在");
       }

       if (!dataService.update(data)) {
           throw new BusinessException("修改失败");
       }
       renderJson(RestResult.buildSuccess());
   }

   /**
    * 删除
    */
   @NotNullPara({"id"})
   public void delete() {
       String id = getPara("id");
       if (!dataService.deleteById(id)) {
           throw new BusinessException("删除失败");
       }

       renderJson(RestResult.buildSuccess());
   }

   public void cache() {
       dataService.refreshCache();
       renderJson(RestResult.buildSuccess());
   }
}