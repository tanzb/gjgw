/*
 * Copyright(c) 2017 DongHong Inc.
 */
package com.jxstar.weixin;

import org.jxstar.util.log.Log;

import com.jxstar.extend.WXPushBO;

/**
 * HTTP请求测试。
 *
 * @author TonyTan
 * @version 1.0, 2017-7-27
 */
public class WXHttpTest {
	
	//智维云
	//AppID : wx7c23243c71dfa6a2
	//AppSecret : a9a8db8e2c3c099df089e796722edca8
	//openid : "or_wvwbKfAhPc5kYRSaYrQ0rHkic","or_wvwacbdX2KcKo98ToTzseICu4"
	
	//Jxstar
	//AppID : wxac60d8a60fdaf715
	//AppSecret : 6917a64033f84d2fc8e0ba5c29c32b4a
	
	public static void main(String[] args) {
		Log.getInstance().init("c:/", "");
		
		//String token = "yIc4EBs0YiJfgPK54ARMWs6U6n_sbJD9ARctJH6XzXccPiiDVbqKn7ThrbXO3FcQSai1QEmKZouI-cMmmCfKD6ISs5BuuE_QQENQmC0HLwE5QElsFM3j82imeXZfRXeVTWEfAAAKNV";
		
		//System.out.println(UUID.randomUUID());
		
		//pay();
		token();
		//jsapi_ticket(token);
		//signature();
		//userInfo(token);
		
		//sendTextURL();
	}
	
	public static void pay() {
		String openid = "or_wvwbKfAhPc5kYRSaYrQ0rHkic";
		String trade_no = "20170003";
		
		WXPay pay = new WXPay();
		pay.prepay(openid, trade_no, "", "");
		
	}

	public static void token() {
		String appid = "wxac60d8a60fdaf715";
		String secret = "6917a64033f84d2fc8e0ba5c29c32b4a";
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
		
		String result = WXHttp.getRequest(url);
		System.out.println(".............result="+result);
	}
	
	public static void jsapi_ticket(String token) {
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+token+"&type=jsapi";
		
		String result = WXHttp.getRequest(url);
		System.out.println(".............result="+result);
	}
	
	public static void signature() {
		String ticket = "kgt8ON7yVITDhtdwci0qeQeMSiA2OzCKLrCZl-p_HbwOpDA-pu8F4mL1qZ0rKe8ZhhdvpNP7nyqM3i7siUYIHw";
		String noncestr = "sdfwrlx2p";
		String timestamp = Long.toString(System.currentTimeMillis()); 
		String url = "http://www.jxstar.com/weixin/spxg.html";
		
		String str = "jsapi_ticket="+ticket;
			   str += "&noncestr="+noncestr;
			   str += "&timestamp="+timestamp;
			   str += "&url="+url;
		System.out.println(".............param="+str);
			   
	    String result = WXPayUtil.HMACSHA256(str, "");
	    
	    System.out.println(".............signature="+result);
	}
	
	public static void listUser(String token) {
		String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+token+"&next_openid=";
		String result = WXHttp.getRequest(url);
		System.out.println(".............result="+result);
	}
	
	public static void userInfo(String token) {
		String openid = "or_wvwacbdX2KcKo98ToTzseICu4";
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+token+"&openid="+openid+"&lang=zh_CN";
		String result = WXHttp.getRequest(url);
		System.out.println(".............result="+result);
	}
	
	public static void sendText() {
		WXPushBO wm = new WXPushBO();
		
		String openID = "oCIC0wmof6DhaKCWoD88Q025Ezj8";
		String msg = "测试消息";
		wm.sendMessage(openID, msg);
	}
	
	public static void sendTextURL() {
		WXPushBO wm = new WXPushBO();
		
		String openID = "or_wvwbKfAhPc5kYRSaYrQ0rHkic";
		String title = "测试消息与链接";
		String msg = "测试消息测试消息测试消息测试消息测试消息测试消息"+
			"\r\n\r\n------------------------------------------\r\n详情 >>";
		String url = "http://www.jxstar.com/weixin/wxlic/lic_check.html?regid=jxstar-280-351";
		wm.sendMessageURL(openID, title, msg, url);
	}
}
