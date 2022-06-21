/*
 * Copyright(c) 2017 DongHong Inc.
 */
package com.jxstar.test;

import org.jxstar.test.AbstractTest;

import com.jxstar.extend.WXQYSendBO;

/**
 * 企业微信消息测试。
 *
 * @author TonyTan
 * @version 1.0, 2017-10-23
 */
public class WXQYSendTest extends AbstractTest {
	
	public static void main(String[] args) {
		init("C:/D/docs/我的项目/jxstar2/SVNDB/40程序文件/app");
		
		WXQYSendBO sd = new WXQYSendBO();
		sd.send();
		System.out.println("............"+sd.getMessage());
	}

}
