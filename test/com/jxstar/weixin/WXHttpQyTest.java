/*
 * Copyright(c) 2017 DongHong Inc.
 */
package com.jxstar.weixin;

import org.jxstar.test.AbstractTest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jxstar.extend.WXQYPushBO;

/**
 * 企业号消息测试类。
 * 
 * @author TonyTan
 * @version 1.0, 2017-10-18
 */
public class WXHttpQyTest extends AbstractTest {

	public static void main(String[] args) {
		init("C:/D/docs/我的项目/jxstar2/SVNDB/40程序文件/app");
		// gettoken();
		
		//send();// 手工拷贝token到下面的代码中
		
		WXQYPushBO qypush = new WXQYPushBO();
		qypush.sendURL("tanzhibin", "消息标题", "消息内容", "");
	}

	// 获取取token
	public static void gettoken() {
		String corpid = "ww86f6247721b17eb0";
		String secret = "K_q2pq_YN0RlgjbaUMNv-b4gd-hC7YvsLv3PK_r_CmA";
		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="
				+ corpid + "&corpsecret=" + secret;

		String result = WXHttp.getRequest(url);
		System.out.println(".............result=" + result);
	}

	// 发送消息
	public static void send() {
		String token = "4hEeI94yJJOxJxZDF7r7d6Mdsdz1lx_9LWILKD5DpqDVjXn6mENu_unqhZ8E0lmjf1qw9mlUKFvVYZTgUzDB0nKC--7aJ6-Ork21ugMp5WfBcvtMGt2cTCFSsgpy_xM5Rk8A5tpAm7e7G-QDvinAfctK7kikeCiXjI0Ls1a-gE62kWzqCOphExrB0BMiRyauJEqoB2S1j-R8pCIzdFGFrw";
		String href = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="
				+ token;
		String param = newsJson();
		System.out.println(".............param=" + param);

		String result = WXHttp.postRequest(href, param);
		System.out.println(".............result=" + result);
	}

	// 构建消息对象
	public static String newsJson() {
		JsonObject jo = new JsonObject();
		jo.addProperty("touser", "tanzhibin");// tanzhibin 80499408
		jo.addProperty("toparty", "");
		jo.addProperty("totag", "");
		jo.addProperty("msgtype", "news");
		jo.addProperty("agentid", 1000002);// 1000002 1000161

		JsonObject sb = new JsonObject();
		sb.addProperty("title", "【催办SED-TL-001单】");
		sb.addProperty(
				"description",
				"【描述】: 133.01.002.63185(刀具可换头式方肩铣刀接柄，φ12，EH12-A11.7-SH-135),2.000EA,用于ITER屏蔽块,\r\r"
						+ "【单据编号】: 1000000616-0050\r\r"
						+ "【当前处理人】: 张天宝\r\r"
						+ "【处理环节】:  使用部门会签、指派\r\r"
						+ "【本环节开始时间】:  2018-11-20 11:22:47\r\r"
						+ "【停留时间】:  5.12小时\r\r");
		sb.addProperty("url",
				"http://www.jxstar.com/weixin/select.html?state=123");
		sb.addProperty("picurl", "");

		JsonObject sb1 = new JsonObject();
		sb1.addProperty("title", "二级物资协同采购消息2");
		sb1.addProperty("description", "提交人：李四 \r\r 单据内容：合同20181222效果");
		sb1.addProperty("url", "");
		sb1.addProperty("picurl", "");

		JsonArray sbs = new JsonArray();
		sbs.add(sb);
		// sbs.add(sb1);

		JsonObject jo1 = new JsonObject();
		jo1.add("articles", sbs);

		jo.add("news", jo1);

		return jo.toString();
	}
}
