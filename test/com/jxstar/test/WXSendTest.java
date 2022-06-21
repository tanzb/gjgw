/*
 * Copyright(c) 2017 DongHong Inc.
 */
package com.jxstar.test;

import org.jxstar.dao.BaseDao;
import org.jxstar.dao.DaoParam;
import org.jxstar.test.AbstractTest;
import org.jxstar.util.DateUtil;
import org.jxstar.util.key.KeyCreator;

import com.google.gson.JsonObject;

/**
 * 构建微信服务号消息。
 *
 * @author TonyTan
 * @version 1.0, 2019-10-23
 */
public class WXSendTest extends AbstractTest {
	
	public static void main(String[] args) {
		init("C:/D/docs/我的项目/jxstar2/SVNDB/40程序文件/app");
		
		insertMsg();
	}

	// 添加新的故障通知消息
	public static void insertMsg() {
		String bus_code = "GZ2019110001";
		String bus_name = "故障工单";
		
		String first = "您有一条新的故障消息！";
		String f1 = "FM300区域有故障，设备漏油。";//故障现象
		String f2 = DateUtil.getTodaySec(); //故障时间
		String remark = "请尽快到达现场处理。";
		String msg = getMessage(first, f1, f2, remark);
		
		String touser = "or_wvwbKfAhPc5kYRSaYrQ0rHkic";//微信ID
		String url = "";
		
		String template_id = "tY7D4cteb2CBu3wFhfaZDaOumT0v215DPvnHZw0Vs1I";//消息模板ID，可以在系统变量中设置好
		
		boolean ret = insertWxLog(template_id, bus_code, bus_name, msg, touser, url);
		System.out.println("............ret="+ret);
	}
	
	// 保存微信消息到日志表中
	private static boolean insertWxLog(String template_id, String bus_code, String bus_name, String msg, String touser, String url) {
		BaseDao _dao = BaseDao.getInstance();
		
		String msg_id = KeyCreator.getInstance().createKey("wx_message");
		String sql = "insert into wx_message(msg_state, msg_type, bus_code, bus_name, template_id, " +
				"msg_title, msg_desc, touser, write_date, plan_date, send_time, msg_id, return_code, return_msg, " +
				"msg_url, add_date, tenant_id) values(?, '0', ?, ?, ?,   ?, ?, ?, ?, ?, ?, ?, ?, ?,   ?, ?, ?)";
		DaoParam param = _dao.createParam(sql);
		param.addStringValue("0");
		param.addStringValue(bus_code);
		param.addStringValue(bus_name);
		param.addStringValue(template_id);
		
		param.addStringValue("");//模板消息没有标题
		param.addStringValue(msg);
		param.addStringValue(touser);
		param.addDateValue(DateUtil.getTodaySec());
		param.addDateValue(DateUtil.getTodaySec());
		param.addDateValue(DateUtil.getTodaySec());
		param.addStringValue(msg_id);
		param.addStringValue("");
		param.addStringValue("");
		
		param.addStringValue(url);
		param.addDateValue(DateUtil.getTodaySec());
		param.addStringValue(param.getCurrTenantId());
		
		return _dao.update(param);
	}
	
	/*消息模板的格式*/
	/*
		{{first.DATA}}
		故障现象：{{performance.DATA}}
		故障时间：{{time.DATA}}
		{{remark.DATA}}
	 */
	
	// 构建消息数据
	private static String getMessage(String first, String f1, String f2, String remark) {
		JsonObject jd1 = new JsonObject();
		jd1.addProperty("value", first);//摘要信息
		JsonObject jd2 = new JsonObject();
		jd2.addProperty("value", f1);//字段1
		JsonObject jd3 = new JsonObject();
		jd3.addProperty("value", f2);//字段2
		JsonObject jd4 = new JsonObject();
		jd4.addProperty("value", remark);//描述信息
		
		JsonObject jd = new JsonObject();
		jd.add("first", jd1);
		jd.add("performance", jd2);
		jd.add("time", jd3);
		jd.add("remark", jd4);
		
		return jd.toString();
	}

}
