package org.wanwanframework.tool.area;

import org.wanwanframework.tool.FileReader;

public class AreaTool {

	public static void main(String[] args) {
		String template = FileReader.load("./src/main/resources/area/area.xml");
		System.out.println("template:" + template);
		searchArea("list", template, new String[]{"000000000", "111111111"});
	}
	
	private static void searchArea(String node, String content, String[] words) {
		String keyStart = "<@>".replace("@", node);
		String keyEnd   = "</@>".replace("@", node);
		int start = content.indexOf(keyStart) + keyStart.length();
		int end   = content.indexOf(keyEnd);
		String word = content.substring(start, end);
		System.out.println("word:" + word);
		String wordReplaced = "";
		for (int i = 0; i < words.length; i++) {
			wordReplaced += word.replace("@localTime", words[i]);
		}
		content = content.substring(0, start) + wordReplaced + content.substring(end);
		System.out.println("content:" + content);
	}
}
