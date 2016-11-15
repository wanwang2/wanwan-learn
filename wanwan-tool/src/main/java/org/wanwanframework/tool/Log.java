package org.wanwanframework.tool;

/**
 * 日志工具
 * @author coco
 *
 */
public class Log {

	public static final int level_error = 1;
	public static final int level_debug = 2;
	public static final int level_warn 	= 3;
	public static final int level_info 	= 4;
	
	public static int log_level 	= level_warn;
	public static String print_head = "log::";
	
	public static void error(Object log){
		logLevel(log, level_error);
	}
	
	public static void debug(Object log){
		logLevel(log, level_debug);
	}
	
	public static void warn(Object log){
		logLevel(log, level_warn);
	}
	
	public static void info(Object log){
		logLevel(log, level_info);
	}
	
	public static void logLevel(Object log, int level){
		if(log_level <= level){
			log(log);
		}
	}
	
	/**
	 * 输出日志
	 * @param param
	 * @param log
	 */
	public static void log(String printHead, String param, String log){
		logString(printHead + param + ":  " +log);
	}
	
	/**
	 * 输出日志
	 * @param param
	 * @param log
	 */
	public static void log(String param, String log){
		logString(print_head + param + ":  " +log);
	}
	
	/**
	 * 输出日志
	 * @param param
	 * @param log
	 */
	public static void log(String param, Object log){
		logString(print_head + param + ":  " +log);
	}
	
	/**
	 * 输出日志
	 * @param log
	 */
	public static void log(String log){
		logString(print_head + "  " + log);
	}
	
	/**
	 * 输出日志
	 * @param log
	 */
	public static void log(Object log){
		logString(print_head + "  " + log);
	}
	
	/**
	 * 输出日志
	 * @param log
	 */
	public static void logString(String log){
		System.out.println(log);
	}
}
