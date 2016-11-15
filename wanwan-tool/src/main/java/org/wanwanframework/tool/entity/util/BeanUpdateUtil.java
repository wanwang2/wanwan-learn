package org.wanwanframework.tool.entity.util;

import java.util.HashMap;
import java.util.Map;

import org.wanwanframework.tool.FileReader;
import org.wanwanframework.tool.Log;

/**
 * update sql
 * @author coco
 *
 */
public class BeanUpdateUtil {

	private static Map<String, String> fieldMap = new HashMap<String, String>();
	private static final String lineSymbol = "\r\n";
	
	public static String getField(String table, String sql, String fieldTemplate, String lineTemplate) {
		String content = "";
		sql = sql.replace(lineSymbol, "");
		String[] fields = sql.split(","); 
		for(int i = 0; i < fields.length; i++) {
			content += LineTool.printSingle(fields[i].trim(), fieldTemplate);
		}
		content = content.substring(0, content.length() - 1);
		content = lineTemplate.replace("@content", content);
		fieldMap.put(table, content);
		return content;
	}

	public static String getWhere(String where) {
		where = where.replace("|", "= or ");
		where = where.replace("&", "= and ");
		where = where.replace("))", "= ))");
		where = where.replace("=", "=?");
		return where;
	}
	
	/**
	 * 获取替换字符映射表
	 * @param url
	 * @return
	 */
	public static Map<String, String> getMapping(String url) {
		Map<String, String> map = LineTool.getLine(url);
		return map;
	}
	
	/**
	 * 加载
	 * @param url ./src/main/resources/sqlBean.field
	 */
	public static void process(String url, String template, Map<String, String> mapping) {
		Map<String, String> map = LineTool.getLine(url);
		for (String key :map.keySet()) { 
			int tableIndex = key.indexOf("(");
			String table = key.substring(0, tableIndex);
			String where = key.substring(tableIndex + 1, key.length() - 1);
			String lineTemplate = "UPDATE " + table.trim() + " SET@content WHERE " + getWhere(where);
			getField(table, map.get(key), " @=?,", lineTemplate);
			getField(table + ".bean", map.get(key), "\t\tbean.get@(),\r\n", "\r\n@content"); 
		}
		
		for (String key: fieldMap.keySet()) {
			String realKey = mapping.get(key);
			if(realKey != null && realKey.length() > 0) {
				template = template.replace(realKey + ".bean", fieldMap.get(key + ".bean"));
				template = template.replace(realKey, fieldMap.get(key));
			}
		}
		Log.log("..." + template + "...");
	}
	
	public static void main(String[] args) {
		String template = FileReader.load("./src/main/resources/template/update.template");
		Map<String, String> mapping = getMapping("./src/main/resources/template/mapping.txt");
		process("./src/main/resources/sqlBean.field", template, mapping);
	}
}
