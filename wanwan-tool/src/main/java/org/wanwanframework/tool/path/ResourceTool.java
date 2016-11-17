package org.wanwanframework.tool.path;

import java.util.Map;

import org.wanwanframework.tool.Log;
import org.wanwanframework.tool.entity.util.LineTool;

public class ResourceTool {

	private static Map<String, String> properties = LineTool.getLine("./src/main/resources/sqlBean.txt");
	
	public static void main(String[] args) {
		Log.log(properties);
	}
}
