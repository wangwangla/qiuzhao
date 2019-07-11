package io.jboot.admin.service.entity.status.system;


import io.jboot.admin.base.common.BaseStatus;

/**
 * 系统资源状态类
 * @author Rlax
 *
 */
public class ContractStatus extends BaseStatus {

	
    public final static String CREATE = "0";
    public final static String WORKING = "1";
    public final static String FINSHED = "2";
    public final static String UNUSE = "3";
    public final static String WEIYUE = "4";
    
    
    public ContractStatus() {	
        add(CREATE, "创建待执行");
        add(WORKING, "正在执行");
        add(FINSHED, "执行完毕");
        add(UNUSE, "合同作废");
        add(WEIYUE, "合同违约");
        
    }

    private static ContractStatus me;

    public static ContractStatus me() {
        if (me == null) {
            me = new ContractStatus();
        }
        return me;
    }

}
