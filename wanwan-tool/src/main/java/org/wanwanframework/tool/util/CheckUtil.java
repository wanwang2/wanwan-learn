package org.wanwanframework.tool.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验文件名
 * @author coco
 *
 */
public class CheckUtil {
	
	public static final String fileName = "BOSS2PBSS_UserStatusSync_100_20161102110605_9999.dat";
	public static final String regular  = "^BOSS2PBSS_UserStatusSync_\\d{3}_\\d{14}_\\d{4}.dat$";
	
	public static String check(String fileName) {
		fileName = fileName.replaceAll(regular, "#");
		return fileName;
	}
	
	/**
	 * 检查名字
	 * @param fileName
	 * @return
	 */
	public static boolean checkName(String fileName) {
		Pattern pattern = Pattern.compile(regular);
		Matcher matcher = pattern.matcher(fileName);
		String nodeTemplate = null;
		if (matcher.find()) {
			nodeTemplate = matcher.group(0);
			System.out.println("node.template: " + nodeTemplate);
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		String r = check(fileName);
		System.out.println("r:" + r);
		
		boolean rb = checkName(fileName);
		System.out.println("rb:" + rb);
	}
}