package org.wanwanframework.tool.combine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HashMapTool {

	/**
	 * map比较器
	 */
	private static Comparator<Map.Entry<String, String>> comparator = new Comparator<Map.Entry<String, String>>() {
		public int compare(Entry<String, String> o1, Entry<String, String> o2) {// 升序排序
			return o1.getKey().compareTo(o2.getKey());
		}
	};
	
	public static List<Map.Entry<String, String>> sort(Map<String, String> map) {
		List<Map.Entry<String, String>> mapList = new ArrayList<Map.Entry<String, String>>(map.entrySet());
		Collections.sort(mapList, comparator);
		return mapList;
	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("c", "11111");
		map.put("a", "aaaaa");
		map.put("b", "bbbbb");
		map.put("d", "ddddd");
		map.put("0", "zzzzz");

		List<Map.Entry<String, String>> mapList = sort(map);
		System.out.println("list:" + mapList);
	}
}