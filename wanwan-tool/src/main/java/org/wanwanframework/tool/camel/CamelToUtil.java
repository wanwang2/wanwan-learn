package org.wanwanframework.tool.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * 专门用来集成CamelTo的工具
 * @author coco
 *
 */
public class CamelToUtil {
	
	private static final String url = "127.0.0.1";
	private static final String FTPURL_DATA 		= "ftp://" + url + "/data?username=vv&password=vv";
	private static final String FTPURL_DATA2 		= "ftp://" + url + "/data/data2?username=vv&password=vv";

	/**
	 * file.path = file:/home/vv/source/item or
	 * file.path = file:E:/design/linux/error_coco
	 */
	public static void copyToFTP() {
		CamelContext context = new DefaultCamelContext();
		try {
			context.addRoutes(new RouteBuilder() {
				public void configure() {
					from("file:/home/vv/source/log")
					.to(FTPURL_DATA)
					.to(FTPURL_DATA2);
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

	public static void main(String args[]) {
		copyToFTP();
	}
}
