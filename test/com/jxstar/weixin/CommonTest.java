/*
 * Copyright(c) 2019 DongHong Inc.
 */
package com.jxstar.weixin;

import org.jxstar.test.AbstractTest;

import com.jxstar.extend.WXPushBO;
import com.jxstar.weixin.token.WXTokenM;

/**
 * 通用测试类
 *
 * @author TonyTan
 * @version 1.0, 2019-5-23
 */
public class CommonTest extends AbstractTest {

	public static void main(String[] args) {
		init("C:/D/docs/我的项目/jxstar2/SVNDB/40程序文件/app");
		
		sendwx();
	}

	public static void qytoken() {
		String _corpid = "ww86f6247721b17eb0";
		String _corpsecret = "K_q2pq_YN0RlgjbaUMNv-b4gd-hC7YvsLv3PK_r_CmA";
		String access_token = WXTokenM.getQyToken(_corpid, _corpsecret);
		System.out.println("------------access_token="+access_token);
	}
	
	public static void sendwx() {
		WXPushBO push = new WXPushBO();
		String openID = "oCIC0ws-Gthz95YKiv79HfLtB9HY";
		String msg = "测试微信消息！";
		push.sendMessage(openID, msg);
	}
}
