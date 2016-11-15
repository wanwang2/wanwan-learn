package org.wanwanframework.tool.util;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTool {

	private static AtomicInteger atomicInteger = new AtomicInteger();
	
	public static void process() {
		int vv = atomicInteger.get();
		System.out.println(vv);
		atomicInteger.set(5000);
		vv = atomicInteger.get();
		System.out.println(vv);
	}
	
	public static void main(String[] args) {
		AtomicIntegerTool.process();
	}
}
