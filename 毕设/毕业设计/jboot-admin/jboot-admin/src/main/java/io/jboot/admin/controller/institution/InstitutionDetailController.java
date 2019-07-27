package io.jboot.admin.controller.institution;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import io.jboot.admin.base.common.RestResult;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.base.interceptor.NotNullPara;
import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.InstitutionDetailInfoService;
import io.jboot.admin.service.api.InstitutionInfoService;
import io.jboot.admin.service.api.ServiceOrderService;
import io.jboot.admin.service.entity.model.InstitutionDetailInfo;
import io.jboot.admin.service.entity.model.InstitutionInfo;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/institution/detail")
public class InstitutionDetailController extends BaseController{
	@JbootrpcService 
	private InstitutionDetailInfoService dataService;
	
	@JbootrpcService 
	private InstitutionInfoService infoService;
	@JbootrpcService
	private ServiceOrderService service;
	
	public void tongji() {
    	String id = getPara("id");
    	List<Record> lis = dataService.findByInstituId(id);
    	if(lis.size()!=0) {
    		Record r =  lis.get(0);
    		setAttr("data", r.get("wd_id")).render("tongji.html");
    	}else {
    		render("tongji.html");
    	}
    	
    }
	
    /**
     * index
     */
    public void index() {
    	String id = getPara("id");
        setAttr("data", id).render("main.html");
    }

    /**
     * 表格数据
     */
    public void tableData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);
        Page<InstitutionDetailInfo> dataPage = dataService.findPage(pageNumber, pageSize);
        renderJson(new DataTable<InstitutionDetailInfo>(dataPage));
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
    	InstitutionDetailInfo data = getBean(InstitutionDetailInfo.class, "data");
    	InstitutionInfo info= infoService.findById(data.getInstitutionId());
    	data.setRepairNum(0);
    	String i = info.getInstitutionWdNum();
    	
    	int ii = Integer.valueOf(i);
    	ii++;
    	data.setAddDate(new Date());
    	info.setInstitutionWdNum(String.valueOf(ii));
        if (!dataService.save(data)) {
            throw new BusinessException("保存失败");
        }
        infoService.update(info);
        renderJson(RestResult.buildSuccess());
    }

    /**
     * update
     */
    @NotNullPara({"id"})
    public void update() {
        String id = getPara("id");
        InstitutionDetailInfo data = dataService.findById(id);
        setAttr("data", data).render("update.html");
    }

    /**
     * 修改提交
     */
    public void postUpdate() {
    	InstitutionDetailInfo data = getBean(InstitutionDetailInfo.class, "data");

        if (dataService.findById(data.getWdId()) == null) {
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
    @NotNullPara({"id"})
    public void findByInstituId(){
    	String id = getPara("id");
    	
    	renderJson(dataService.findByInstituId(id));
    }
    
    public void cache() {
        dataService.refreshCache();
        renderJson(RestResult.buildSuccess());
    }
    @NotNullPara({"id"})
    public void detail() {
        String id = getPara("id");
        Page<InstitutionDetailInfo> dataPage = dataService.findByIDXX(id);
        renderJson(new DataTable<InstitutionDetailInfo>(dataPage));
    }
    
    @NotNullPara({"id"})
    public void detailview() {
    	String id = getPara("id");
    	setAttr("data", id).render("/template/main.html");
    }
    
    /**
     * 年
     */
    @NotNullPara({"id"})
    public void ndetail() {
        String id = getPara("id");
        List<Record> dataPage = service.findByIDN(id);
        renderJson(dataPage);
    }
    /**
     * 月
     */
    @NotNullPara({"id"})
    public void ydetail() {
        String id = getPara("id");
        List<Record> dataPage = service.findByIDy(id);
        renderJson(dataPage);
    }
    /**
     * 季度
     */
    @NotNullPara({"id"})
    public void jddetail() {
        String id = getPara("id");
        List<Record> dataPage = service.findByIDjd(id);
        renderJson(dataPage);
    }
    /**
     * 周
     */
    @NotNullPara({"id"})
    public void zdetail() {
        String id = getPara("id");
        List<Record> dataPage = service.findByIDz(id);
        System.out.println(dataPage.toString());
        renderJson(dataPage);
    }
    
    
    
}
