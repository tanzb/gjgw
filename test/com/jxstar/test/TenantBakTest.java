/*
 * Copyright(c) 2018 DongHong Inc.
 */
package com.jxstar.test;

import org.jxstar.test.AbstractTest;
import org.jxstar.util.FileUtil;

import com.jxstar.patch.TenantBakBO;

/**
 * 备份租户数据。
 *
 * @author TonyTan
 * @version 1.0, 2018-11-8
 */
public class TenantBakTest extends AbstractTest {

	public static void main(String[] args) {
		init("C:/D/docs/我的项目/jxstar2/SVNDB/40程序文件/app");

		TenantBakBO bak = new TenantBakBO();
		String sql = bak.bakDataSQL("jxstar");
		FileUtil.saveFile("c:/tenant.sql", sql);
	}

}
