package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.Jboot;
import io.jboot.admin.base.common.CacheKey;
import io.jboot.admin.service.api.StaffInfoService;
import io.jboot.admin.service.entity.model.Data;
import io.jboot.admin.service.entity.model.StaffInfo;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;

@Bean
@Singleton
@JbootrpcService
public class StaffInfoServiceImpl extends JbootServiceBase<StaffInfo> implements StaffInfoService {

	@Override
	public Page<StaffInfo> findPage( int pageNumber, int pageSize) {
        Columns columns = Columns.create();
     
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
    }

	@Override
    public void refreshCache() {
        Jboot.me().getCache().removeAll(CacheKey.CACHE_KEYVALUE);
    }

	@Override
	public Page<StaffInfo> findPage(StaffInfo staffInfo, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		Columns columns = Columns.create();
	    if(StrKit.notBlank(staffInfo.getStaffName())) {
	    	columns.like("staff_name", staffInfo.getStaffName());
	    }
		return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
	}
}