package org.wanwanframework.tool.search;

import org.wanwanframework.tool.Log;

/**
 * 通过搜寻目标字符串，自动替换成需要的字符串，实现插入式代码自动化
 * @author coco
 *
 */
public class SearchTool {

	private static final String searchContent = 
			"private final Logger LOGGER = LoggerFactory.getLogger(AuditCollectorProcessor.class);";
	
	private static final String template = "getClass()"; // 替换成的目标字符串
	private static final String regular = "\\(.*?\\)"; // 寻找模式

	private static final String search(String conent, String regex, String word) {
		conent = conent.replaceAll(regex, "#");
		conent = conent.replaceAll("#", "(#)");
		conent = conent.replaceAll("#", word);
		return conent;
	}

	public static void main(String[] args) {
		String r = search(searchContent, regular, template);
		Log.log(r);
	}
}
