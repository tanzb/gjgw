/*
 * Copyright(c) 2017 DongHong Inc.
 */
package com.jxstar.test;

import java.util.List;
import java.util.Map;

import org.jxstar.dao.BaseDao;
import org.jxstar.dao.DaoParam;
import org.jxstar.test.AbstractTest;

import com.jxstar.ui.GTreeSearch;
import com.jxstar.ui.GTreeUtil;

/**
 * 查询树形数据
 *
 * @author TonyTan
 * @version 1.0, 2017-4-6
 */
public class GTreeTest extends AbstractTest {
	
	public static void main(String[] args) {
		init("C:\\D\\docs\\我的项目\\jxstar2\\jxstar_demo\\SVNDB\\01app");
		
		String userId = "administrator";
		Map<String, String> mpTree = getTree();
		String whereSql = "part_name like ?";
		String whereType = "string";
		String whereValue = "%鼠标-E-884%";
		int _start = 0;
		int _limit = 20;
		
		GTreeSearch tu = new GTreeSearch();
		List<Map<String, Object>> lsData = tu.queryDataTree(
				userId, mpTree, whereSql, whereType, whereValue, _start, _limit);
		String json = GTreeUtil.list2json(lsData);
		int _total = tu.queryDataTotal(userId, mpTree, whereSql, whereType, whereValue);
		
		System.out.println(".........json="+json);
		System.out.println("........._total="+_total);
	}

	private static Map<String, String> getTree() {
		BaseDao _dao = BaseDao.getInstance();
		String sql = "select * from fun_tree where fun_id = 'mat_type3'";
		
		DaoParam param = _dao.createParam(sql);
		return _dao.queryMap(param);
	}
}
