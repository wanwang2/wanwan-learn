package org.wanwanframework.tool.relation;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.wanwanframework.tool.Log;

public class Relation {

	/**
	 * 将一个文件名作为内容，写到那个文件里面去
	 * @param dir
	 */
	public static void makeFile(File dir) {
		String content = dir.getName();
		File file = new File(dir.getPath() + "/" + content);
		Writer writer = new Writer();
		writer.setFile(file);
		try {
			writer.execute(content);
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	private static FilenameFilter nameFilter = new FilenameFilter() {
		public boolean accept(File dir, String name) {
			File child = new File(dir + "/" + name);
			if(!child.isDirectory()){
				return false;
			} 
			return true;
		}
	};
	
	/**
	 * 读取文件夹
	 * @param root
	 */
	public static void readDir(File root) {
		String[] list = root.list(nameFilter);
		for(int i = 0; i < list.length; i++){
			System.out.println("list: " + root.getPath() + "\\" + list[i]);
			File node = new File(root.getPath() + "/" + list[i]);
			readDir(node);
		} 
	}
	
	/**
	 * 获取一个文件夹里的文件列表,如果文件夹名不是以点开头的继续遍历
	 * @param root
	 */
	public static void getDir(File root) {
		String[] dirList = root.list(nameFilter);
		for(int i = 0; i < dirList.length; i++){
			Log.log(root.getPath() + "\\" + dirList[i]);
			File node = new File(root.getPath() + "/" + dirList[i]);
			if(node.isDirectory() && !dirList[i].startsWith(".")){
				getDir(node);
			}
		} 
	}
	
	public static void main(String[] args) {
		File dir = new File("./src/main/resources");
		makeFile(dir); 
		File root = new File("E:/design/db");
		getDir(root);
	}
}
