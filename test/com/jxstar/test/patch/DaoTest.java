/*
 * Copyright(c) 2016 DongHong Inc.
 */
package com.jxstar.test.patch;

import java.util.List;
import java.util.Map;

import org.jxstar.dao.BaseDao;
import org.jxstar.dao.DaoParam;
import org.jxstar.test.AbstractTest;


/**
 * 数据库测试。
 *
 * @author TonyTan
 * @version 1.0, 2016-9-7
 */
public class DaoTest extends AbstractTest {

	public static void main(String[] args) {
		//init("C:\\D\\tomcat6\\webapps\\jxstar_ee");
		init("C:\\D\\docs\\我的项目\\智维云\\SVNDB\\40程序文件\\app");
		BaseDao _dao = BaseDao.getInstance();
		String sql = "select * from fun_design where fun_id = 'sys_fun_base'";
		DaoParam param = _dao.createParam(sql);
		List<Map<String, String>> ls = _dao.query(param);
		
		System.out.println("------"+ls);
	}

}
