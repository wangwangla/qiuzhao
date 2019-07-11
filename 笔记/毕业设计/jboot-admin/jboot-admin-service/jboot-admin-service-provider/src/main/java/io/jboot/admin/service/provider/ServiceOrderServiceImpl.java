package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.Jboot;
import io.jboot.admin.base.common.CacheKey;
import io.jboot.admin.service.api.ServiceOrderService;
import io.jboot.admin.service.entity.model.InstitutionDetailInfo;
import io.jboot.admin.service.entity.model.ServiceOrder;
import io.jboot.service.JbootServiceBase;

import java.util.List;

import javax.inject.Singleton;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

@Bean
@Singleton
@JbootrpcService
public class ServiceOrderServiceImpl extends JbootServiceBase<ServiceOrder> implements ServiceOrderService {

	@Override
	public Page<ServiceOrder> findPage(int pageNumber, int pageSize) {
	    Columns columns = Columns.create();
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
    }

	@Override
    public void refreshCache() {
        Jboot.me().getCache().removeAll(CacheKey.CACHE_KEYVALUE);
    }

	@Override
	public Page<ServiceOrder> findByIDXX(String id) {
		// TODO Auto-generated method stub
		 Columns columns = Columns.create();
		 columns.eq("wd_name", id);
		 return DAO.paginateByColumns(1, 30, columns.getList());
	}
	/**
	 * 月
	 */
	@Override
	public List<Record> findByIDy(String id) {
		// TODO Auto-generated method stub
		return Db.find("select count(*) c, DATE_FORMAT(service_order_start_time,'%y年%m月') z from service_order where wd_name='"+id+"' group by DATE_FORMAT(service_order_start_time,'%y%m')");
	}
	/**
	 * 季度
	 */

	@Override
	public List<Record> findByIDjd(String id) {
		// TODO Auto-generated method stub
		return Db.find("select count(*) c,concat(FLOOR((DATE_FORMAT(service_order_start_time,'%m季度')-1)/3)+1,'季度') z from service_order where wd_name='"+id+"' group by FLOOR((DATE_FORMAT(service_order_start_time,'%m')-1)/3)+1");
	}
	
	/**
	 * 周
	 */
	@Override
	public List<Record> findByIDz(String id) {
		// TODO Auto-generated method stub
		return Db.find("select count(*) c,DATE_FORMAT(service_order_start_time,'%y年%u周') z  from service_order where wd_name='"+id+"' group by DATE_FORMAT(service_order_start_time,'%y%u')");
	}

	/**
	 * Nian
	 */
	
	@Override
	public List<Record> findByIDN(String id) {
		// TODO Auto-generated method stub
		return Db.find("select count(*) c,DATE_FORMAT( repair_date ,'%y年') z from institution_detail_info where institution_id='"+id+"' group by DATE_FORMAT( repair_date ,'%y')");
	}

	@Override
	public List<Record> findByIDxq(String institutionName) {
		// TODO Auto-generated method stub
		return Db.find("select count(*) c,wd_name z from service_order where service_order_institution='"+institutionName+"' group by wd_name");
	}
 

	@Override
	public Page<ServiceOrder> findByWdName(String id, String institutionName) {
		// TODO Auto-generated method stub
	    Columns columns = Columns.create();
	    if(StrKit.notBlank(id)) {
	    	columns.eq("wd_name", id);
	    	System.out.println(id+"=========");
	    }
	    if(StrKit.notBlank(institutionName)) {
	    	columns.eq("service_order_institution", institutionName);
	    	System.out.println(institutionName+"---------------");
	    }
        return DAO.paginateByColumns(1, 100, columns.getList());
	}

	@Override
	public List<Record> findByYearPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return Db.find("select count(*) c,DATE_FORMAT( repair_date ,'%y年') z from institution_detail_info  group by DATE_FORMAT( repair_date ,'%y')");
	}

	@Override
	public List<Record> findByJiDuPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return Db.find("select count(*) c,concat(FLOOR((DATE_FORMAT(service_order_start_time,'%m季度')-1)/3)+1,'季度') z from service_order group by FLOOR((DATE_FORMAT(service_order_start_time,'%m')-1)/3)+1");
	}

	@Override
	public List<Record> findByMonthPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return Db.find("select count(*) c, DATE_FORMAT(service_order_start_time,'%y%m') z from service_order group by DATE_FORMAT(service_order_start_time,'%y%m')");
	}

	@Override
	public List<Record> findByWeekPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return Db.find("select count(*) c,DATE_FORMAT(service_order_start_time,'%y%u') z  from service_order group by DATE_FORMAT(service_order_start_time,'%y%u')");
	}
 

 
}