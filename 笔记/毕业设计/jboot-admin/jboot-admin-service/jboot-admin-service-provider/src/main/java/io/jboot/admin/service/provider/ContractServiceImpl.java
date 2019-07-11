package io.jboot.admin.service.provider;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.db.model.Columns;
import io.jboot.Jboot;
import io.jboot.admin.base.common.CacheKey;
import io.jboot.admin.service.api.ContractService;
import io.jboot.admin.service.entity.model.Contract;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;

@Bean
@Singleton
@JbootrpcService
public class ContractServiceImpl extends JbootServiceBase<Contract> implements ContractService {

	@Override
	public Page<Contract> findPage(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
	       Columns columns = Columns.create();
	        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
	    }

		@Override
	    public void refreshCache() {
	        Jboot.me().getCache().removeAll(CacheKey.CACHE_KEYVALUE);
	    }

		@Override
		public Page<Contract> findPage(Contract contract, int pageNumber, int pageSize) {
			Columns columns = Columns.create();
			if(StrKit.notBlank(contract.getContractName())) {
				columns.eq("contract_name", contract.getContractName());
			}
	        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList());
		}
}