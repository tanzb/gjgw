/*
 * Copyright(c) 2019 DongHong Inc.
 */
package com.jxstar.test;

import org.jxstar.test.AbstractTest;

import com.jxstar.extend.SqlDdlBO;

/**
 * DDL SQL 执行测试！
 *
 * @author TonyTan
 * @version 1.0, 2019-9-9
 */
public class SqlDdlTest extends AbstractTest {

	public static void main(String[] args) {
		init("C:/D/docs/我的项目/jxstar2/SVNDB/40程序文件/app");

		test3();
	}

	public static void test1() {
		SqlDdlBO dml = new SqlDdlBO();
		
		String sql = "create index warn_user_aa on warn_user (USE_CLASS)#&#" +
				"create index warn_user_bb on warn_user (ADD_DATE)#&#" +
				"create index warn_user_cc on warn_user (ADD_USERID)#&#" +
				"alter table WARN_USER add aa varchar2(2)";
		String ret = dml.exesql(sql, null);
		_log.showDebug("--------------ret="+ret);
		_log.showDebug("--------------message="+dml.getMessage());
	}
	
	public static void test2() {
		SqlDdlBO dml = new SqlDdlBO();
		
		String sql = 
		  "create or replace procedure p_check_postuser(v_in in varchar2, v_out out varchar2) is\n"+
		  "  cursor mycursor is select post_id, count(*) as cnt from sys_post_user where tenant_id = v_in group by post_id;--定义表格数据对象 \n"+
		  "  myrow mycursor%rowtype;--定义行对象类型变量\n"+
		  "begin\n"+
		  "  v_out := '[';\n"+
		  "  --循环从一个表中取每行数据\n"+
		  "  for myrow in mycursor loop\n"+
		  "    exit when mycursor%notfound;--没有数据时退出循环\n"+
		  "    v_out := v_out||'[\"'||myrow.post_id||'\",'||myrow.cnt||'],';\n"+
		  "  end loop;\n"+
		  "  v_out := v_out||'[]]';\n"+
		  "end p_check_postuser;";
		String ret = dml.exesql(sql, null);
		_log.showDebug("--------------ret="+ret);
		_log.showDebug("--------------message="+dml.getMessage());
	}
	
	public static void test3() {
		SqlDdlBO dml = new SqlDdlBO();
		
		String sql = 
			"create or replace view v_dept as\n"+
			"select dept_id, dept_code, dept_name, dept_level, 'false' as checked, is_novalid, auditing, tenant_id from sys_dept";
		String ret = dml.exesql(sql, null);
		_log.showDebug("--------------ret="+ret);
		_log.showDebug("--------------message="+dml.getMessage());
	}
}
