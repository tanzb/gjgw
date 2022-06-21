package com.jxstar.test;

import org.jxstar.test.AbstractTest;

import com.jxstar.app.PushMsgBO;

public class PushMsgTest extends AbstractTest {


	public static void main(String[] args) throws Exception {
		init("C:/D/docs/我的项目/智维云/SVNDB/40程序文件/app");
		
		PushMsgBO bo = new PushMsgBO();
		bo.onPushTaskMsg("jxstar-923-7998", "0");
    }
}
