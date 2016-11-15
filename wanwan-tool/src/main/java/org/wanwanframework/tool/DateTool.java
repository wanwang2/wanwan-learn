package org.wanwanframework.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("deprecation")
public class DateTool {

	public static final int 		     DAY_CALLCOUNT 		= 500; // 每天最大调用次数
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

	private static void deleteKey(String field, Date today) {
		String key = "UDM_" + SIMPLE_DATE_FORMAT.format(today) + "_" + field;
		System.out.println("key:" + key);
		today.setDate(today.getDate() - 1);
		key = "UDM_" + SIMPLE_DATE_FORMAT.format(today) + "_" + field;
		System.out.println("key:" + key);
	}
	
	public static void main(String[] args) {
		deleteKey("vv", new Date());
	}
}
