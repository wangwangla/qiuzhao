package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.Jboot;
import io.jboot.admin.base.common.CacheKey;
import io.jboot.admin.service.api.InstitutionInfoService;
import io.jboot.admin.service.entity.model.InstitutionInfo;
import io.jboot.admin.service.entity.model.StaffInfo;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

@Bean
@Singleton
@JbootrpcService
public class InstitutionInfoServiceImpl extends JbootServiceBase<InstitutionInfo> implements InstitutionInfoService {

	@Override
	public Page<InstitutionInfo> findPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
        Columns columns = Columns.create();
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
    }

	@Override
    public void refreshCache() {
        Jboot.me().getCache().removeAll(CacheKey.CACHE_KEYVALUE);
    }

	@Override
	public Page<InstitutionInfo> findPage(InstitutionInfo info, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		Columns columns = Columns.create();
		if(StrKit.notBlank(info.getInstitutionName())) {
			columns.eq("institution_name", info.getInstitutionName());
		}
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
	}

	@Override
	public Record findByInsititutionName(String institution) {
		// TODO Auto-generated method stub
		return Db.findFirst("select *from institution_info where institution_name = '"+institution+"'");
	}

}