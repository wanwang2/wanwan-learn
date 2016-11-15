package org.wanwanframework.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * 
 * @author coco
 *
 */
public class RegularTool {

	/**
	 * str = (a)(b)(c)(d)(e) regex = "\\(.*?\\)";
	 */
	private static final String circle_regex = "\\{[\\d\\D]*?\\}"; // 正则表达式:只支持一层嵌套

	/**
	 * 通过正则表达式处理
	 *
	 * @param source
	 */
	public static String[] processSymbol(String source, String regex) {
		String content = source.replaceAll(regex, "#");
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		String nodeTemplate = null;
		if (matcher.find()) {
			nodeTemplate = matcher.group(0);
			System.out.println("node.template: " + nodeTemplate);
		}
		System.out.println("replace: " + content);
		return new String[] { content, nodeTemplate };
	}

	public static void main(String[] args) {
		String[] r = processSymbol("vvv{vv}", circle_regex);
		for (int i = 0; i < r.length; i++) {
			System.out.println("r:" + r[i]);
		}
	}
}
