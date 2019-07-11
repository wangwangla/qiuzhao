package io.jboot.admin.controller.institution;

import java.util.ArrayList;
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
import io.jboot.admin.service.api.InstitutionDetailInfoService;
import io.jboot.admin.service.api.InstitutionInfoService;
import io.jboot.admin.service.api.ServiceOrderEnclosureService;
import io.jboot.admin.service.api.StaffInfoService;
import io.jboot.admin.service.entity.model.InstitutionDetailInfo;
import io.jboot.admin.service.entity.model.InstitutionInfo;
import io.jboot.admin.service.entity.model.ServiceOrderEnclosure;
import io.jboot.admin.service.entity.model.StaffInfo;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/institution/info")
public class InstitutionController extends BaseController{
	@JbootrpcService
	private InstitutionInfoService dataService;
	private static List<String> listImage = new ArrayList<String>();
	@JbootrpcService
	private InstitutionDetailInfoService infoService;
	@JbootrpcService
	private ServiceOrderEnclosureService service;
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
    	InstitutionInfo info = new InstitutionInfo();
    	info.setInstitutionName(name);
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);
        Page<InstitutionInfo> dataPage = dataService.findPage(info,pageNumber, pageSize);
        renderJson(new DataTable<InstitutionInfo>(dataPage));
    }

    
    public void instiList() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);
        Page<InstitutionInfo> dataPage = dataService.findPage(pageNumber, pageSize);
        renderJson(new DataTable<InstitutionInfo>(dataPage));
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
    	InstitutionInfo data = getBean(InstitutionInfo.class, "institutionInfo");
    	for(String s : listImage) {
        	data.setInstitutionLogo(s);
        }
    	data.setInstitutionWdNum("0");
    	data.setInstitutionWdId(0);
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
        InstitutionInfo data = dataService.findById(id);
        setAttr("data", data).render("update.html");
    }

    /**
     * 修改提交
     */
    public void postUpdate() {
    	InstitutionInfo data = getBean(InstitutionInfo.class, "data");

        if (dataService.findById(data.getInstitutionId()) == null) {
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
        infoService.delete(id);
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
    private void find() {
		// TODO Auto-generated method stub
    	
	}
    
    
    
}
