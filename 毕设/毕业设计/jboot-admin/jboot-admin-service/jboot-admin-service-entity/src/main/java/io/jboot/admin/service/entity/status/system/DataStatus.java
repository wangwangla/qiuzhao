package io.jboot.admin.service.entity.status.system;


import io.jboot.admin.base.common.BaseStatus;

/**
 * 系统资源状态类
 * @author Rlax
 *
 */
public class DataStatus extends BaseStatus {

	
    public final static String UNUSED = "0";
    public final static String USED = "1";
    
    public DataStatus() {	
        add(UNUSED, "银行");
        add(USED, "ATM");
    }

    private static DataStatus me;

    public static DataStatus me() {
        if (me == null) {
            me = new DataStatus();
        }
        return me;
    }

}
