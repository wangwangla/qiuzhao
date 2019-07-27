package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.Jboot;
import io.jboot.admin.base.common.CacheKey;
import io.jboot.admin.service.api.InstitutionDetailInfoService;
import io.jboot.admin.service.entity.model.InstitutionDetailInfo;
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
public class InstitutionDetailInfoServiceImpl extends JbootServiceBase<InstitutionDetailInfo> implements InstitutionDetailInfoService {



	@Override
	public Page<InstitutionDetailInfo> findPage(int pageNumber, int pageSize) {
	       Columns columns = Columns.create();
	        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
	    }

		@Override
	    public void refreshCache() {
	        Jboot.me().getCache().removeAll(CacheKey.CACHE_KEYVALUE);
	    }

		@Override
		public void delete(String institutionId) {
			// TODO Auto-generated method stub
			Db.delete("delete from institution_detail_info where institution_id='"+institutionId+"'");
		}

		@Override
		public Page<InstitutionDetailInfo> findByIDXX(String id) {
			// TODO Auto-generated method stub
			 Columns columns = Columns.create();
			 columns.eq("institution_id", id);
			 return DAO.paginateByColumns(1, 30, columns.getList());
		}

		@Override
		public Record findByWD(String wdName) {
			// TODO Auto-generated method stub
			return Db.findFirst("select *from institution_detail_info where wd_name='"+wdName+"'");
		}

	


		@Override
		public List<Record> findByInstituId(String id) {
			// TODO Auto-generated method stub
			return Db.find("select institution_id from institution_detail_info where institution_id='"+id+"'");
		}

	
}