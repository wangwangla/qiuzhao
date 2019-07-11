package io.jboot.admin.controller.warehouse;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;

import io.jboot.admin.base.common.RestResult;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.base.interceptor.NotNullPara;
import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.upload.ExcelExportUtil;
import io.jboot.admin.base.upload.UpLoadUtils;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.EnclosureService;
import io.jboot.admin.service.api.WaregouseOutService;
import io.jboot.admin.service.api.WarehouseInfoService;
import io.jboot.admin.service.entity.model.Enclosure;
import io.jboot.admin.service.entity.model.WaregouseOut;
import io.jboot.admin.service.entity.model.WarehouseInfo;
import io.jboot.admin.support.auth.AuthUtils;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/warehouse/out")
public class WarehouseOutController extends BaseController {
	
	@JbootrpcService
	private WaregouseOutService dataService;
	private static List<String> listImage = new ArrayList<String>();
	@JbootrpcService
	private EnclosureService enclosureService;

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
	   String equipType = getPara("equipType");
	   String equipXinghao = getPara("equipXinghao");
	   String equipName = getPara("equipName");
	   System.out.println(equipType+"==="+equipXinghao+"------"+equipName);
	   WaregouseOut waregouseOut = new WaregouseOut();
	   waregouseOut.setEquipType(equipType);
	   waregouseOut.setEquipXinghao(equipXinghao);
	   waregouseOut.setEquipName(equipName);
       int pageNumber = getParaToInt("pageNumber", 1);
       int pageSize = getParaToInt("pageSize", 30);
       Page<WaregouseOut> dataPage = dataService.findPage(waregouseOut,pageNumber, pageSize);
       renderJson(new DataTable<WaregouseOut>(dataPage));
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
		String id =  UUID.randomUUID().toString().substring(0, 20);
	   WaregouseOut data = getBean(WaregouseOut.class, "warehouseout");
	   data.setEquipId(id);
	   data.setEquipLead(AuthUtils.getLoginUser().getName());
	   data.setEquipLqDate(new Date());
	     for(String s : listImage) {
	    	 Enclosure enclosure = new Enclosure();
	    	 enclosure.setContractId(id);
	    	 enclosure.setImagePath(s);
	    	 enclosureService.save(enclosure);
	     }
	     listImage.clear();
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
       WaregouseOut data = dataService.findById(id);
       setAttr("data", data).render("update.html");
   }

   /**
    * 修改提交
    */
   public void postUpdate() {
	   WaregouseOut data = getBean(WaregouseOut.class, "warehouseout");
	   
       if (dataService.findById(data.getEquipId()) == null) {
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
   
   public void postUpload() throws Exception{
	     String id = getPara("id");
	     if(id!=null)
	     {
	    	 enclosureService.deleteBywareId(id);
	     }
	     HashMap<String, UploadFile> HashMapFile = getUploadFilesMap();
    	 UploadFile file = HashMapFile.get("file");
    	 listImage = UpLoadUtils.uploadFile(file, listImage);
    	 System.out.println("============="+listImage);
    	 renderJson(RestResult.buildSuccess());
     }
   
   public void hetong() {
		 String id  = getPara("id");
		 setAttr("data", id);
	     render("/template/contract/hetong.html");
	 }
	 
	 public void load() {
		 String id = getPara("id");
		 Page<Enclosure> dataPage = enclosureService.findByCon(id); 
		 renderJson(new DataTable<>(dataPage));
	 }
	 public void export(){
		 List<Record> list = dataService.export();
		        Map<String, String> titleData = new HashMap<String, String>();//标题，后面用到
		        titleData.put("equip_id", "出单号");
		        titleData.put("equip_type", "设备类型");
		        titleData.put("equip_xinghao", "设备型号");
		        titleData.put("equip_name", "设备名称");
		        titleData.put("equip_in_date", "设备入库时间");
		        titleData.put("equip_status", "状态");
		        titleData.put("equip_lq_lead", "领取人");
		        titleData.put("equip_out_date", "设备领取时间");
		        titleData.put("equip_lead", "负责人");
		        File file = new File(ExcelExportUtil.getTitle());
		    file = ExcelExportUtil.saveFile(titleData, list, file);
		    renderNull();
		    
		}
	  public void tableYearData() {
	        List<Record> dataPage = dataService.findByIDN();
	        renderJson(dataPage);
	    }
	    public void tableJiDuData() {
	        List<Record> dataPage = dataService.findByIDjd();
	        renderJson(dataPage);
	    }
	    public void tableMonthData() {
	        List<Record> dataPage = dataService.findByIDy();
	        renderJson(dataPage);
	    }
	    public void tableWeekData() {
	        List<Record> dataPage = dataService.findByIDz();
	        renderJson(dataPage);
	    }
	 
}