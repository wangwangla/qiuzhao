package io.jboot.admin.controller.staff;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

import io.jboot.admin.base.common.RestResult;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.base.interceptor.NotNullPara;
import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.upload.UpLoadUtils;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.ServiceOrderEnclosureService;
import io.jboot.admin.service.api.StaffInfoService;
import io.jboot.admin.service.entity.model.Data;
import io.jboot.admin.service.entity.model.Enclosure;
import io.jboot.admin.service.entity.model.ServiceOrderEnclosure;
import io.jboot.admin.service.entity.model.StaffInfo;
import io.jboot.admin.service.entity.status.system.DataStatus;
import io.jboot.admin.support.auth.AuthUtils;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/staff/info")
public class StaffController extends BaseController{
	@JbootrpcService
	private StaffInfoService dataService;
	@JbootrpcService
	private ServiceOrderEnclosureService service;
	
	private static List<String> listImage = new ArrayList<String>();
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
    	String name = getPara("name");
    	StaffInfo staffInfo = new StaffInfo();
    	staffInfo.setStaffName(name);
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);
        Page<StaffInfo> dataPage = dataService.findPage(staffInfo,pageNumber, pageSize);
        renderJson(new DataTable<StaffInfo>(dataPage));
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
        StaffInfo data = getBean(StaffInfo.class, "staffInfo");
        data.setStaffStatus("待命");
        data.setStaffId(id);
        for(String s : listImage) {
        	data.setStaffPhoto(s);
        }
        listImage.clear();
        if (!dataService.save(data)) {
            throw new BusinessException("保存失败");
        }
      
        renderJson(RestResult.buildSuccess());
    }

    /**
     * update
     */
    @NotNullPara({"id"})
    public void update() {
        String id = getPara("id");
        StaffInfo data = dataService.findById(id);
        setAttr("data", data).render("update.html");
    }
    
    
    @NotNullPara({"id"})
    public void find() {
        String id = getPara("id");
        StaffInfo data = dataService.findById(id);
        setAttr("data", data).render("xiangqing.html");
    }

    
    /**
     * 修改提交
     */
    public void postUpdate() {
    	StaffInfo data = getBean(StaffInfo.class, "data");

        if (dataService.findById(data.getStaffId()) == null) {
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
}
