package io.jboot.admin.controller.warehouse;

import com.jfinal.plugin.activerecord.Page;

import io.jboot.admin.base.common.RestResult;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.base.interceptor.NotNullPara;
import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.WarehouseInfoService;
import io.jboot.admin.service.entity.model.SupplierInfo;
import io.jboot.admin.service.entity.model.WarehouseInfo;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/warehouse/info")
public class WarehouseInfoController extends BaseController {
	
	@JbootrpcService
	private WarehouseInfoService dataService;

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
       Page<WarehouseInfo> dataPage = dataService.findPage(pageNumber, pageSize);
       renderJson(new DataTable<WarehouseInfo>(dataPage));
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
	   WarehouseInfo data = getBean(WarehouseInfo.class, "warehouseInfo");
       
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
       WarehouseInfo data = dataService.findById(id);
       setAttr("data", data).render("update.html");
   }

   /**
    * 修改提交
    */
   public void postUpdate() {
	   WarehouseInfo data = getBean(WarehouseInfo.class, "data");
	   
       if (dataService.findById(data.getWaregouseId()) == null) {
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