package org.wanwanframework.tool.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelFTPUtil {

	private static final String url = "127.0.0.1";
	private static final String FTPURL_127 		= "ftp://" + url + "/data?username=vv&password=vv";

	/**
	 * 必须要是带文件的目录，默认不往子目录遍历
	 * @throws Exception
	 */
	public static void copyFromFTP() throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
			public void configure() {
				from(FTPURL_127).to("file:d:/data/linux2");
			}
		});
		context.start();
		boolean loop = true;
		while (loop) {
			Thread.sleep(25000);
		}
		context.stop();
	}

	/**
	 * 从本地拷贝到远程FTP上面
	 * @throws Exception
	 */
	public static void copyToFTP() {
		CamelContext context = new DefaultCamelContext();
		try {
			context.addRoutes(new RouteBuilder() {
				public void configure() {
					from("file:/home/vv/source/item")
					.to(FTPURL_127);
				}
			});
			context.start();
			boolean loop = true;
			while (loop) {
				try {
					Thread.sleep(25000);
				} catch (InterruptedException e) { 
					e.printStackTrace();
				}
			}
			context.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		copyToFTP();
	}
}
