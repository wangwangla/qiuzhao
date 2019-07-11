package io.jboot.admin.service.entity.status.system;


import io.jboot.admin.base.common.BaseStatus;

/**
 * 系统资源状态类
 * @author Rlax
 *
 */
public class OrderStatus extends BaseStatus {

	
    public final static String UNUSED = "0";
    public final static String USED = "1";
    
    public OrderStatus() {	
        add(UNUSED, "自助下单");
        add(USED, "平台下单");
    }

    private static OrderStatus me;

    public static OrderStatus me() {
        if (me == null) {
            me = new OrderStatus();
        }
        return me;
    }

}
