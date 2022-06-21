/*
 * Copyright(c) 2016 DongHong Inc.
 */
package com.jxstar.test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.Map;

import org.jxstar.dao.DaoParam;
import org.jxstar.service.BusinessObject;
import org.jxstar.util.MapUtil;
import org.jxstar.util.StringUtil;

/**
 * 测试数据。
 *
 * @author TonyTan
 * @version 1.0, 2016-11-26
 */
public class TestBO extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	public String getstr() {
		MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
		String str = "...........Full MEM Information..............\n";
		str += "...........Heap Memory Usage: " + memorymbean.getHeapMemoryUsage();
		str += "\n...........Non-Heap Memory Usage: " + memorymbean.getNonHeapMemoryUsage();
		return str;
	}
	
	//取未阅消息数量
	public String onGetNum() {
		int num = getNum();
		
		String str = StringUtil.strForJson(getstr());
		
		setReturnData("{\"num\":\"ff"+ num + str +"\"}");
		
		return _returnSuccess;
	}

	//取未阅消息数量
	public int getNum() {
		String sql = "select count(*) as cnt from oa_msg ";
		DaoParam param = _dao.createParam(sql);
		
		Map<String,String> mp = _dao.queryMap(param);
		return MapUtil.getInt(mp, "cnt", "0");
	}
	
}
