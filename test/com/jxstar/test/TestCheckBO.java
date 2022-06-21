package com.jxstar.test;

import java.util.Map;

import org.jxstar.control.action.RequestContext;
import org.jxstar.dao.DaoParam;
import org.jxstar.service.BusinessObject;
import org.jxstar.util.resource.JsParam;

/**
 * 审批节点测试类。
 * 
 * @author cch
 * @version 1.0, 2017-3-25
 */
public class TestCheckBO extends BusinessObject {

	private static final long serialVersionUID = 1L;

	public String test(RequestContext req) {
		_log.showDebug("..............test:" + req.getRequestMap());
		
		return _returnSuccess;
	}
	
	/**
	 * 审批前判断
	 * 
	 * @param req
	 * @return
	 */
	public String beforeCheck(RequestContext req) {
		_log.showDebug("...........beforeCheck request param=" + req.getRequestMap());
		//审批意见类型
		String checkType = req.getRequestValue("check_type");
		String userId = req.getUserInfo().get("user_id");
		_log.showDebug("..............userId:" + userId);
		_log.showDebug("..............checkType:" + checkType);
		//Y -- 同意
		//N -- 不同意、否决终止
		//R -- 退回
		//E -- 退回编辑人
		
		//不同意就不执行下面的判断
		if (!checkType.equals("Y")) return _returnSuccess;
		
		
		String keyid = req.getRequestValue(JsParam.KEYID);
		String money = getMoney(keyid);
		_log.showDebug("..............beforeCheck money:" + money);
		
		if (Integer.parseInt(money) > 10000) {
			_log.showDebug("报销金额太大，需要开会决定");
			setMessage("报销金额太大，需要开会决定");
			return _returnFaild;
		}
		
		return _returnSuccess;
	}

	/**
	 * 审批后触发
	 * 
	 * @param req
	 * @return
	 */
	public String afterCheck(RequestContext req) {
		_log.showDebug("...........afterCheck request param=" + req.getRequestMap());
		
		//审批意见类型
		String checkType = req.getRequestValue("check_type");
		String userId = req.getUserInfo().get("user_id");
		_log.showDebug("..............userId:" + userId);
		_log.showDebug("..............checkType:" + checkType);
		//Y -- 同意
		//N -- 不同意、否决终止
		//R -- 退回
		//E -- 退回编辑人
		
		//不同意就不执行下面的处理
		if (!checkType.equals("Y")) return _returnSuccess;
		
		
		String funid = req.getRequestValue("funid");
		String keyid = req.getRequestValue(JsParam.KEYID);
		_log.showDebug("..............funid=" + funid + "; keyid="+keyid);
		
		int money = Integer.parseInt(getMoney(keyid));
		_log.showDebug("..............afterCheck money:" + money);
		
		money = money+100;// 加100
		if (!updateMoney(keyid, Integer.toString(money))) {
			setMessage("新增金额失败");
			return _returnFaild;
		}
		
		return _returnSuccess;
	}

	/**
	 * 审批后检查
	 * 
	 * @param req
	 * @return
	 */
	public String queryCheck(RequestContext req) {
		String keyid = req.getRequestValue(JsParam.KEYID);
		
		_log.showDebug("..............queryCheck money:" + getMoney(keyid));
		
		return _returnSuccess;
	}

	private String getMoney(String keyid) {
		String sql = "select in_money from store_in where in_id = ?";
		DaoParam param = _dao.createParam(sql);
		param.addStringValue(keyid);
		Map<String, String> map = _dao.queryMap(param);
		if (map.isEmpty()) {
			return "0";
		}
		return map.get("in_money");
	}

	private boolean updateMoney(String keyid, String money) {
		String sql = "update store_in set in_money = ?  where in_id = ?";
		DaoParam param = _dao.createParam(sql);
		param.addIntValue(money);
		param.addStringValue(keyid);
		return _dao.update(param);
	}

}
