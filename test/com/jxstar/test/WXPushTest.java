/*
 * Copyright(c) 2017 DongHong Inc.
 */
package com.jxstar.test;

import java.util.Map;

import org.jxstar.test.AbstractTest;
import org.jxstar.util.factory.FactoryUtil;

import com.jxstar.extend.WXPushBO;
import com.jxstar.extend.WXPushTempBO;

/**
 * 测试微信发送消息。
 *
 * @author TonyTan
 * @version 1.0, 2017-5-27
 */
public class WXPushTest  extends AbstractTest {
	
	public static void main(String[] args) {
		init("C:/D/docs/我的项目/jxstar2/SVNDB/40程序文件/app");
		
		WXPushTempBO push = new WXPushTempBO();
		String url = "http://www.jxstar.com/weixin/jxwx";
		String msg = "您有一个新的审批任务！";
		Map<String,String> mpdata = FactoryUtil.newMap();
		mpdata.put("pcode", "20");
		mpdata.put("pname", "入库审批");
		mpdata.put("check_user", "张三");
		mpdata.put("check_date", "2019-8-12 12:33");
		push.sendMessage("oCIC0ws-Gthz95YKiv79HfLtB9HY", url, msg, mpdata);
	}

	public static void wxpush() {
		WXPushBO push = new WXPushBO();
		
		//String[] ids = push.getAllOpenID();
		//for (String id : ids) {
		//	System.out.println("..............userid="+id);
		//}
		
		//String user = push.getUserName("oBW6_1YlZEwk8xnvP-TcPsssif1s");
		//System.out.println("..............user="+user);
		
		push.sendMessage("oBW6_1YlZEwk8xnvP-TcPsssif1s", "Tony, 测试消息！");
	}
}
