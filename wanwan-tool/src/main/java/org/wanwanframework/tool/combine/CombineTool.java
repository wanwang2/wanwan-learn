package org.wanwanframework.tool.combine;

/**
 * 生成类似hashcode的算法
 * generateKeyValue同级节点可以容纳99个不同元素
 * @author coco
 *
 */
public class CombineTool {

	public static int generateKeyValue(String key, String symbol) {
		int keyValue = 0;
		String[] keys = key.split(symbol);
		int bit = 0;
		for (int i = 0; i < keys.length; i++) {
			bit = keys.length - i - 1;
			keyValue += (Integer.parseInt(keys[i])) * Math.pow(100, bit);
		}
		return keyValue;
	}

	public static void main(String[] args) {
		int r = generateKeyValue("1.2.3.4", "\\.");
		System.out.println("r:" + r);
		r = generateKeyValue("1.12.3.4", "\\.");
		System.out.println("r:" + r);
	}
}
