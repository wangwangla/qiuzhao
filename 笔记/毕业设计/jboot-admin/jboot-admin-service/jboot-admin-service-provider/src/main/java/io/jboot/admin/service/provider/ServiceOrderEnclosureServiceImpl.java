package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.admin.service.api.ServiceOrderEnclosureService;
import io.jboot.admin.service.entity.model.ServiceOrderEnclosure;
import io.jboot.service.JbootServiceBase;

import java.util.List;

import javax.inject.Singleton;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

@Bean
@Singleton
@JbootrpcService
public class ServiceOrderEnclosureServiceImpl extends JbootServiceBase<ServiceOrderEnclosure> implements ServiceOrderEnclosureService {
	@Override
	public Page<ServiceOrderEnclosure> findByCon(String id) {
		Columns columns = Columns.create();
	    columns.like("order_enclosure_up_time", id);       
        return DAO.paginateByColumns(1, 40, columns.getList());
	}

	@Override
	public void deletePhotoId(String ar) {
		// TODO Auto-generated method stub
		Db.delete("delete from enclosure where contract_id  =  '"+ar+"'");
	}

	@Override
	public List<Record> findByIDz(String institutionName) {
		// TODO Auto-generated method stub
		return null;
	}
}