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
import io.jboot.admin.service.entity.model.InstitutionDetailInfo;
import io.jboot.admin.service.entity.model.InstitutionInfo;
import io.jboot.admin.service.entity.model.ServiceOrder;
import io.jboot.admin.service.entity.model.ServiceOrderEnclosure;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/service/statistics")
public class ServiceOrderStatistics extends BaseController {
	@JbootrpcService
	private ServiceOrderService dataService;
	
	   /**
     * index
     */
    public void index() {
        render("tongji.html");
    }

    /**
     * 表格数据
     */
    public void tableYearData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);
        List<Record> dataPage = dataService.findByYearPage(pageNumber, pageSize);
        renderJson(dataPage);
    }
    public void tableJiDuData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);
        List<Record> dataPage = dataService.findByJiDuPage(pageNumber, pageSize);
        renderJson(dataPage);
    }
    public void tableMonthData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);
        List<Record> dataPage = dataService.findByMonthPage(pageNumber, pageSize);
        renderJson(dataPage);
    }
    public void tableWeekData() {
        int pageNumber = getParaToInt("pageNumber", 1);
        int pageSize = getParaToInt("pageSize", 30);
        List<Record> dataPage = dataService.findByWeekPage(pageNumber, pageSize);
        renderJson(dataPage);
    }
    
    public void cache() {
        dataService.refreshCache();
        renderJson(RestResult.buildSuccess());
    }
}
