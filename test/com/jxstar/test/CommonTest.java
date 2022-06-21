/*
 * Copyright(c) 2016 DongHong Inc.
 */
package com.jxstar.test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.jxstar.dao.BaseDao;
import org.jxstar.dao.DaoParam;
import org.jxstar.test.AbstractTest;
import org.jxstar.util.JsonUtil;
import org.jxstar.util.MapUtil;
import org.jxstar.util.StringUtil;
import org.jxstar.util.log.Log;

import com.google.gson.stream.JsonWriter;

/**
 * 公共测试类。
 *
 * @author TonyTan
 * @version 1.0, 2016-12-28
 */
public class CommonTest extends AbstractTest {
	private static Log _log = null;
	private static BaseDao _dao = null;
	
	public static void main(String[] args) {
		init("C:/D/docs/我的项目/jxstar2/SVNDB/40程序文件/app");
		_log = Log.getInstance();
		_dao = BaseDao.getInstance();

		query();
	}
	
	public static void query() {
		String sql = "select * from dm_fieldcfg where field_name = 'order_code'";
		DaoParam param = _dao.createParam(sql);
		Map<String,String> mp = _dao.queryMap(param);
		_log.showDebug(mp.toString());
	}

	public static void query1() {
		String sql = "select news_cont from oa_news where news_id = 'jxstar-531-1101'";
		DaoParam param = _dao.createParam(sql);
		Map<String,String> mp = _dao.queryMap(param);
		String str = MapUtil.getValue(mp, "news_cont");
		
		_log.showDebug("{\"news_cont\":\""+ StringUtil.strForJson(str) +"\"}");
		
		_log.showDebug("{\"news_cont\":"+ toJson(str) +"}");
		
		_log.showDebug(JsonUtil.map2json(mp));
	}
	
	public static String toJson(String value) {
	    try {
	      StringWriter stringWriter = new StringWriter();
	      JsonWriter jsonWriter = new JsonWriter(stringWriter);
	      jsonWriter.setLenient(true);
	      jsonWriter.value(value);
	      jsonWriter.close();
	      return stringWriter.toString();
	    } catch (IOException e) {
	      throw new AssertionError(e);
	    }
	}
}
