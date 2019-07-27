package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.admin.service.api.EnclosureService;
import io.jboot.admin.service.entity.model.Data;
import io.jboot.admin.service.entity.model.Enclosure;
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
public class EnclosureServiceImpl extends JbootServiceBase<Enclosure> implements EnclosureService {

	
	@Override
	public Page<Enclosure> findByCon(String id) {
		Columns columns = Columns.create();
	    columns.like("contract_id", id);       
        return DAO.paginateByColumns(1, 40, columns.getList());
	}

	@Override
	public void deleteBywareId(String id) {
		// TODO Auto-generated method stub
		Db.delete("delete from enclosure where contract_id = '"+id+"'");
	}

	@Override
	public List<Record> export() {
		// TODO Auto-generated method stub
		return Db.find("select *from ");
	}
}