package org.wanwanframework.tool.entity.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.wanwanframework.tool.FileReader;
import org.wanwanframework.tool.Log;

/**
 * 提供从sql-create 语句到entity之间的转换
 * @author coco
 *
 */
public class EntityUtil {

	public static final String LINE_SYMBOL	= "\r\n";
	public static Map<String, String> typeMap;
	
	public static String getCreate(String sql) {
		int start 	= sql.indexOf("(");
		int end	  	= sql.lastIndexOf(")");
		String sub 	= sql.substring(start + 1, end);
		return sub;
	}
	
	public static Map<String, String> getType(String content) {
		Map<String, String> map = new HashMap<String, String>();
		String[] types = content.split(LINE_SYMBOL);
		for (int i = 0; i < types.length; i++) {
			String[] keyValue = types[i].split("\\s+");
			if(keyValue != null && keyValue.length == 2){
				map.put(keyValue[0].trim(), keyValue[1].trim());	
			}
		}
		return map;
	}
	
	public static Map<String, String> getField(String sql) {
		sql = sql.replace("\r\n", "");
		String[] fields = sql.split(",");
		Map<String, String> map = new HashMap<String, String>();
		for(int i = 0; i < fields.length; i++) {
			String fieldString = fields[i].trim();
			String[] keyValue = fieldString.split("\\s+");
			if(keyValue != null && keyValue.length == 2){
				map.put(keyValue[0].trim(), toType(keyValue[1].trim()));	
			}
		}
		return map;
	}
	
	public static String toType(String key) {
		String value = null;
		for(String mapKey : typeMap.keySet()) {
			if(key.indexOf(mapKey) >= 0){
				value = typeMap.get(mapKey);
			}
		}
		return value;
	}
	
	public static void process() {
		String type = FileReader.load("./src/main/resources/entity/entityType.txt");
		typeMap = getType(type);
		String content = FileReader.load("./src/main/resources/sql.txt");
		String sql = getCreate(content);
		Log.log(sql);
		Map<String, String> map = getField(sql);
		Log.log(map);
		printMap(map);
	}
	
	public static void printMap(Map<String, String> map) {
		Set<String> keySet = map.keySet();
		for (String key:keySet) {
			String value = map.get(key);
			printSingle(key, value);
		}
	}
	
	public static void printSingle(String key, String value) {
		key = StringUtils.lowerCase(key);
		key = replaceUnderlineAndfirstToUpper(key, "_", "");
		Log.print_head = "";
		Log.log("private " + value + " " + key + ";");
	}
	
	/**
	 * 首字母大写
	 * 
	 * @param srcStr
	 * @return
	 */
	public static String firstCharacterToUpper(String srcStr) {
		return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);
	}

	/** 
	* 替换字符串并让它的下一个字母为大写 
	* @param srcStr 
	* @param org 
	* @param ob 
	* @return 
	*/  
	public static String replaceUnderlineAndfirstToUpper(String srcStr, String org, String ob) {
		String newString = "";
		int first = 0;
		while (srcStr.indexOf(org) != -1) {
			first = srcStr.indexOf(org);
			if (first != srcStr.length()) {
				newString = newString + srcStr.substring(0, first) + ob;
				srcStr = srcStr.substring(first + org.length(), srcStr.length());
				srcStr = firstCharacterToUpper(srcStr);
			}
		}
		newString = newString + srcStr;
		return newString;
	}
	
	public static void main(String[] args) {
		process();
	}
}
