/*
 * Copyright(c) 2014 DongHong Inc.
 */
package com.jxstar.test.patch;

import java.sql.Connection;

import org.jxstar.dao.pool.DataSourceConfig;
import org.jxstar.dao.pool.DataSourceConfigManager;
import org.jxstar.dao.pool.PooledConnection;
import org.jxstar.test.AbstractTest;

import com.jxstar.patch.CompDesign;

/**
 * 更新页面设计文件的工具类使用说明：
 * 1、先在静态变量中设置生产库的jdbc信息；
 * 2、在main方法中设置当前项目的缺省路径；
 * 3、如果是需要发更新包给对方，则执行方法：comp.compare();
 *    生成的新增设计记录sql在：c:/insert.sql
 *    生成的差异设计文件在：c:/tmpblob
 *    先执行insert.sql，然后打开功能设计器扩展操作“更新设计文件”，输入设计文件存放的路径，就可以更新生产库中；
 *    
 * 4、如果是可以直接连接生产库更新，则执行方法：comp.update();
 *    自动更新设计文件；
 *
 * @author TonyTan
 * @version 1.0, 2014-3-29
 */
public class CompDesignTest extends AbstractTest {
	private static String TO_DSNAME = "todefault";
	
	//生产库JDBC信息
	private static String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static String USE_CODE = "jxstar_ee";
	private static String USE_PASS = "jxstar_ee";
	private static String DBMS_TYPE = "oracle";
	private static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	
	//添加数据库信息
	public static boolean addds() {
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDataSourceName(TO_DSNAME);
		dsc.setSchemaName(USE_CODE);
		dsc.setDriverClass(DRIVER_NAME);
		dsc.setJdbcUrl(JDBC_URL);
		dsc.setUserName(USE_CODE);
		dsc.setPassWord(USE_PASS);
		dsc.setDbmsType(DBMS_TYPE);
		
		DataSourceConfigManager.getInstance().addDataSourceConfig(dsc);
		
		Connection con = PooledConnection.getInstance().getConnection(TO_DSNAME);
		return (con != null);
	}
	
	public static void main(String[] args) {
		init("C:\\D\\works\\jxstar\\jxstar-webapp\\src\\main\\webapp");
		if (!addds()) {
			System.out.println("添加数据源对象失败：" + TO_DSNAME);
			return;
		}
		
		CompDesign comp = new CompDesign();
		
		comp.compare();
		
		//comp.update();
	}


}
