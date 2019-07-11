package io.jboot.admin.controller.commodity;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import io.jboot.admin.base.common.RestResult;
import io.jboot.admin.base.exception.BusinessException;
import io.jboot.admin.base.interceptor.NotNullPara;
import io.jboot.admin.base.rest.datatable.DataTable;
import io.jboot.admin.base.web.base.BaseController;
import io.jboot.admin.service.api.CommodityInfoService;
import io.jboot.admin.service.api.SupplierInfoService;
import io.jboot.admin.service.entity.model.CommodityInfo;
import io.jboot.admin.service.entity.model.StaffInfo;
import io.jboot.admin.service.entity.model.SupplierInfo;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/commodity/info")
public class CommodityController extends BaseController {
	@JbootrpcService
	private CommodityInfoService dataService;
	
	@JbootrpcService
	private SupplierInfoService infoService ;
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
        Page<CommodityInfo> dataPage = dataService.findPage(pageNumber, pageSize);
        renderJson(new DataTable<CommodityInfo>(dataPage));
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
    	CommodityInfo data = getBean(CommodityInfo.class, "commodityInfo");
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
        CommodityInfo data = dataService.findById(id);
        setAttr("data", data).render("update.html");
    }

    /**
     * 修改提交
     */
    public void postUpdate() {
    	CommodityInfo data = getBean(CommodityInfo.class, "data");

        if (dataService.findById(data.getCommodityId()) == null) {
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
    public void  find() {
    	String id = getPara("id");
    	Record data = infoService.findByName(id);
    	
    	System.out.println(data+"================");
        setAttr("data", data).render("xiangqing.html");
    }
}