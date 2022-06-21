/*
 * Copyright(c) 2012 Donghong Inc.
 */
package com.jxstar.test.patch;

import org.jxstar.test.AbstractTest;

import com.jxstar.patch.DeleteSqlBO;

/**
 * 测试SQL语句导出。
 *
 * @author TonyTan
 * @version 1.0, 2012-3-15
 */
public class DeleteSqlTest extends AbstractTest {

	public static void main(String[] args) {
		DeleteSqlBO exp = new DeleteSqlBO();
		
		//删除功能的配置信息
		//String[] funIds = {"login", "queryevent", "sysevent"};
		//exp.funExpToFile(funIds);
		
		//删除模块的配置信息
		String[] moduleIds = {"10120001"};
		exp.modExpToFile(moduleIds);
	}

}
