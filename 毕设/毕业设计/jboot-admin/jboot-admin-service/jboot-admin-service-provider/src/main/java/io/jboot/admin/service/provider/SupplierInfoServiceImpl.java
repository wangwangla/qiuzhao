package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.Jboot;
import io.jboot.admin.base.common.CacheKey;
import io.jboot.admin.service.api.SupplierInfoService;
import io.jboot.admin.service.entity.model.CommodityInfo;
import io.jboot.admin.service.entity.model.SupplierInfo;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

@Bean
@Singleton
@JbootrpcService
public class SupplierInfoServiceImpl extends JbootServiceBase<SupplierInfo> implements SupplierInfoService {

	@Override
	public void refreshCache() {
		// TODO Auto-generated method stub
		  Jboot.me().getCache().removeAll(CacheKey.CACHE_KEYVALUE);
    }

	@Override
	public Page<SupplierInfo> findPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
        Columns columns = Columns.create();
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
    }

	@Override
	public Record findByName(String id) {
		// TODO Auto-generated method stub
        Record data = Db.find("select * from supplier_info where supplier_name = '"+id+"'").get(0);
        System.out.println(data+"===================");
        return data;
    }

}