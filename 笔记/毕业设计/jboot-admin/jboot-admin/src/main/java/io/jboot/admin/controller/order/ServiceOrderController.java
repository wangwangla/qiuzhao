package io.jboot.admin.controller.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
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
import io.jboot.admin.service.api.ServiceOrderService;
import io.jboot.admin.service.entity.model.Enclosure;
import io.jboot.admin.service.entity.model.InstitutionDetailInfo;
import io.jboot.admin.service.entity.model.InstitutionInfo;
import io.jboot.admin.service.entity.model.ServiceOrder;
import io.jboot.admin.service.entity.model.ServiceOrderEnclosure;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;
import io.swagger.models.auth.In;

@RequestMapping("/service/order")
public class ServiceOrderController extends BaseController{
	@JbootrpcService
	private ServiceOrderService dataService;
	private static List<String> listImage = new ArrayList<String>();
	@JbootrpcService
	private InstitutionDetailInfoService institutionDetailInfoService;
	@JbootrpcService
	private ServiceOrderEnclosureService service;
	@JbootrpcService
	private InstitutionInfoService infoService;
	
	
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
        Page<ServiceOrder> dataPage = dataService.findPage(pageNumber, pageSize);
        renderJson(new DataTable<ServiceOrder>(dataPage));
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
    	ServiceOrder data = getBean(ServiceOrder.class, "serviceOrder");
        data.setServiceOrderStartTime(new Date());
        String id =  UUID.randomUUID().toString().substring(0, 20);
        data.setServiceOrderId(id);
        data.setOrderStatus("创建处理");
           
        Record record = institutionDetailInfoService.findByWD(data.getWdName());
        InstitutionInfo info = infoService.findById(record.get("institution_id"));
        Integer i = info.getInstitutionWdId();
        Integer wString = record.get("repair_num");
        wString++;
        InstitutionDetailInfo institutionDetailInfo = new InstitutionDetailInfo();
        institutionDetailInfo.setWdId(record.getStr("wd_id"));
        institutionDetailInfo.setRepairNum(wString);
        institutionDetailInfo.setRepairDate(new Date());
        institutionDetailInfoService.update(institutionDetailInfo);
        if (!dataService.save(data)) {
            throw new BusinessException("保存失败");
        }
        int ii = Integer.valueOf(i);
        ii++;
        info.setInstitutionWdId(ii);
        infoService.update(info);
        for(String s : listImage) {
          	 ServiceOrderEnclosure enclosure = new ServiceOrderEnclosure();
          	 enclosure.setOrderEnclosurePhoto(s);
          	 enclosure.setOrderEnclosureUpTime(id);
          	 service.save(enclosure);
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
        ServiceOrder data = dataService.findById(id);
        setAttr("data", data).render("update.html");
    }

    /**
     * 	抢单
     */
    @NotNullPara({"id"})
    public void appoint() {
        String id = getPara("id");
        ServiceOrder data = dataService.findById(id);
        setAttr("data", data).render("appoint.html");
    }

    public void postAppoint() {
    	ServiceOrder data = getBean(ServiceOrder.class, "data");
    	
        if (dataService.findById(data.getServiceOrderId()) == null) {
            throw new BusinessException("数据不存在");
        }

        if (!dataService.update(data)) {
            throw new BusinessException("修改失败");
        }
        renderJson(RestResult.buildSuccess());
    }
    /**
     * 修改提交
     */
    public void postUpdate() {
    	ServiceOrder data = getBean(ServiceOrder.class, "data");
    	data.setOrderWorkTime(new Date());
        if (dataService.findById(data.getServiceOrderId()) == null) {
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
        //找出网点的名字  
        //使用网点名字找出intitution
        //减去
        String institution = dataService.findById(id).getServiceOrderInstitution();
        Record info = infoService.findByInsititutionName(institution);
        InstitutionInfo inf = new InstitutionInfo();
        Integer count = info.get("institution_wd_id");
        int c = Integer.valueOf(count);
        c=--c;
        inf.setInstitutionWdId(c); 
        inf.setInstitutionId(info.get("institution_id"));
        infoService.update(inf);
        if (!dataService.deleteById(id)) {
            throw new BusinessException("删除失败");
        }
        renderJson(RestResult.buildSuccess());
    }

    public void cache() {
        dataService.refreshCache();
        renderJson(RestResult.buildSuccess());
    }
    /**
     * 上传
     */
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
   	 Page<ServiceOrderEnclosure> dataPage = service.findByCon(id); 
   	 renderJson(new DataTable<>(dataPage));
    }
    @NotNullPara({"id"})
    public void detail() {
        String id = getPara("id");
        Page<ServiceOrder> dataPage  = dataService.findByIDXX(id);
        renderJson(new DataTable<ServiceOrder>(dataPage));
    }
    @NotNullPara({"id"})
    public void detailview() {
    	String id = getPara("id");
    	setAttr("data", id).render("/template/NewFile.html");
    }
    
    @NotNullPara({"id"})
    public void wdetail() {
        String id = getPara("id");
        InstitutionInfo info = infoService.findById(id);
        List<Record> dataPage = dataService.findByIDxq(info.getInstitutionName());
        renderJson(dataPage);
    }
    
    @NotNullPara({"id"})
    public void findByWdName() {
    	String id = getPara("id");
    	Record info = institutionDetailInfoService.findByWD(id);
    	String iid = info.get("institution_id");
    	InstitutionInfo iinfo = infoService.findById(iid);
        Page<ServiceOrder> dataPage = dataService.findByWdName(id,iinfo.getInstitutionName());
        System.out.println(dataPage.toString());
        renderJson(new DataTable<ServiceOrder>(dataPage));
    }
}
