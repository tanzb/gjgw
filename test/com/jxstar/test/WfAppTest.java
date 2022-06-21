/**
 * 
 */
package com.jxstar.test;

import org.jxstar.control.action.RequestContext;
import org.jxstar.test.AbstractTest;

import com.jxstar.app.wf.WfTask;

/**
 * @author Kevin
 * 2016-4-26
 */
public class WfAppTest extends AbstractTest{

	public static void main(String[] args) {
		init("F:/15-智维云/40程序文件/app");
		
		WfTask t = new WfTask();
		RequestContext r = new RequestContext(null);
		r.setRequestValue("chk_funid", "asset_change");
		r.setRequestValue("chk_dataid", "jxstar8281");
		t.getOrderInfo(r);
		
		System.out.println("ORDER INFO JSON:"+t.getReturnData());
	}
}
