package io.jboot.admin.controller.contract;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;

import ch.qos.logback.core.util.FileUtil;
import io.jboot.admin.base.common.RestResult;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.base.interceptor.NotNullPara;
import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.upload.UpLoadUtils;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.ContractService;
import io.jboot.admin.service.api.EnclosureService;
import io.jboot.admin.service.entity.model.Contract;
import io.jboot.admin.service.entity.model.Enclosure;
import io.jboot.admin.service.entity.status.system.DataStatus;
import io.jboot.admin.support.auth.AuthUtils;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/contract")
public class ContractController extends BaseController {
	@JbootrpcService
	private ContractService dataService;
	
	@JbootrpcService
	private EnclosureService enclosureService;
	
	private static List<String> listImage = new ArrayList<String>();
	
	
	   /**
  * index
  */
 public void index() {
     render("main.html");
 }


 public void upload() {
	 String id = getPara("id");
     setAttr("data", id).render("upload.html");
 }

 
 /**
  * 表格数据
  */
 public void tableData() {
	 String contractName = getPara("contractName");
	 Contract contract = new Contract();
	 contract.setContractName(contractName);
     int pageNumber = getParaToInt("pageNumber", 1);
     int pageSize = getParaToInt("pageSize", 30);
     Page<Contract> dataPage = dataService.findPage(contract,pageNumber, pageSize);
     renderJson(new DataTable<Contract>(dataPage));
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
 	Contract data = getBean(Contract.class, "contract");
 	
 	data.setContractStatus(DataStatus.me().desc(data.getContractStatus()));
 	data.setContractStatus("创建待执行");
 	data.setEnter(AuthUtils.getLoginUser().getName());
 	data.setEnterDate(new Date());
 	data.setContractId(id);
     if (!dataService.save(data)) {
         throw new BusinessException("保存失败");
     }
     for(String s : listImage) {
    	 Enclosure enclosure = new Enclosure();
    	 enclosure.setContractId(id);
    	 enclosure.setImagePath(s);
    	 enclosureService.save(enclosure);
     }
     listImage.clear();
     renderJson(RestResult.buildSuccess());
 }

 /**
  * update
  */
 @NotNullPara({"id"})
 public void update() {
     String id = getPara("id");
     Contract data = dataService.findById(id);
     setAttr("data", data).render("update.html");
 }

 /**
  * 修改提交
  */
 public void postUpdate() {
	 Contract data = getBean(Contract.class, "data");
	 
     if (dataService.findById(data.getContractId()) == null) {
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
	 
	 HashMap<String, UploadFile> HashMapFile = getUploadFilesMap();
	 UploadFile file = HashMapFile.get("file");
	 
	 listImage = UpLoadUtils.uploadFile(file, listImage);
	 renderJson(RestResult.buildSuccess());
 }
 
 
 public void hetong() {
	 String id  = getPara("id");
	 setAttr("data", id);
     render("hetong.html");
 }
 
 public void load() {
	 String id = getPara("id");
	 Page<Enclosure> dataPage = enclosureService.findByCon(id); 
	 renderJson(new DataTable<>(dataPage));
 }
}
