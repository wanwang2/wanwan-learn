package org.wanwanframework.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

	/**
	 * 加载属性文件
	 * @param file ./resource/template/test.properties
	 * @return
	 */
	public static String load(String file){
		String content = "";
		try {
			Scanner in = new Scanner(new File(file));
			content = readText(in);
			in.close();
			return content;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	public static String readText(Scanner in) {
		String content = "";
		while (in.hasNextLine()) {
			content = (content + in.nextLine() + "\r\n");
		}
		if (content.length() > 2) {// 当content读取到结果并长度大于2时，用于删除文件中的最后一个换行
			content = content.substring(0, content.length() - 2);
		}
		return content;
	}
	
	public static void main(String[] args) {
		String content = load("./src/main/resources/data/iboss-data-total.xml");
		System.out.println(content);
	}
}
