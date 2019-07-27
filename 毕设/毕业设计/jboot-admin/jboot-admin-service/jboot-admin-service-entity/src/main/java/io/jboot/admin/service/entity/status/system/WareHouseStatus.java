package io.jboot.admin.service.entity.status.system;


import io.jboot.admin.base.common.BaseStatus;

/**
 * 仓库状态
 * @author Rlax
 *
 */
public class WareHouseStatus extends BaseStatus {

	
    public final static String OUT = "0";
    public final static String IN = "1";
    public final static String UNUSE = "2";
    
    public WareHouseStatus() {	
        add(OUT, "出库");
        add(IN, "入库");
        add(UNUSE, "报废");
        
    }

    private static WareHouseStatus me;

    public static WareHouseStatus me() {
        if (me == null) {
            me = new WareHouseStatus();
        }
        return me;
    }

}
