package org.wanwanframework.auto.db;

import java.util.ArrayList;
import java.util.Stack;

public class JsonParser {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		String content = "(1,2,(a,b),3,4,(a,b,(0,c,s,1000),(1,a,1200),(2,1400),(3,1600),(4,1800),n,m),((sd,df),(sw,gt))t,b)";
		Stack stack = new Stack();
		String str = "";
		ArrayList<String> array = new ArrayList<String>();
		for (char ch : content.toCharArray()) {
			if (ch != ')') {
				stack.push(ch);
			} else {
				str = ")";
				while (true) {
					char charz = (Character) stack.pop();
					str = charz + str;
					if (charz == '(') {
						array.add(str);
						str = "";
						break;
					}
				}
			}
		}
		System.out.println(array);
	}
}