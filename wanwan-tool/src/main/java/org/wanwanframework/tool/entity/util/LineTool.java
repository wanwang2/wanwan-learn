package org.wanwanframework.tool.entity.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.wanwanframework.tool.FileReader;
import org.wanwanframework.tool.Log;

/**
 * 行读取器:一行代表一对key-value
 * @author coco
 *
 */
public class LineTool {

	/**
	 * 首字母大写
	 * 
	 * @param srcStr
	 * @return
	 */
	public static String firstCharUpper(String srcStr) {
		return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);
	}

	/** 
	* 替换字符串并让它的下一个字母为大写 
	* @param srcStr 
	* @param org 
	* @param ob 
	* @return 
	*/
	public static String replaceUnderline(String srcStr, String org, String ob) {
		String newString = "";
		int first = 0;
		while (srcStr.indexOf(org) != -1) {
			first = srcStr.indexOf(org);
			if (first != srcStr.length()) {
				newString = newString + srcStr.substring(0, first) + ob;
				srcStr = srcStr.substring(first + org.length(), srcStr.length());
				srcStr = firstCharUpper(srcStr);
			}
		}
		newString = newString + srcStr;
		return newString;
	}
	
	/**
	 * 
	 * @param key
	 * @param template: " @=?,"
	 * @return
	 */
	public static String printSingle(String key, String template) {
		String result = template.replaceFirst("@", cutKey(key));
		return result;
	}
	
	/**
	 * 将字符key中间的短线"_"剪切掉
	 * @param key
	 * @return
	 */
	public static String cutKey(String key) {
		key = StringUtils.lowerCase(key);
		key = replaceUnderline(key, "_", "");
		key = firstCharUpper(key);
		return key;
	}
	
	/**
	 * 获得属性行
	 * @param url
	 * @return
	 */
	public static Map<String, String> getLine(String url) {
		String content = FileReader.load(url);
		Log.log(content);
		String[] line = content.split(";");
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < line.length; i++) {
			String[] keyValue = line[i].split(":");
			if(keyValue.length > 1)
				map.put(keyValue[0].trim(), keyValue[1].trim());
		}
		return map;
	}
}
