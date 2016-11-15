//package org.wanwanframework.tool.ftp;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.ObjectMessage;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
///**
// * @author lironghai
// * @description 文件消息监听器
// */
//public class FileMessageListener implements MessageListener {
//
//	private static Logger logger = LoggerFactory.getLogger(FileMessageListener.class);
//
//	private String ftpUrl; // 目标ftp属性字符串
//	private String sourceDir = "E:\\design\\linux\\error"; // 压缩文件存放目录
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public void onMessage(Message message) {
//		if (message instanceof ObjectMessage) {
//			ObjectMessage objMessage = (ObjectMessage) message;
//			try {
//				if (objMessage.getObject() instanceof Map) {
//					Map<String, Object> map = (Map<String, Object>) objMessage.getObject();
//					if (map.get("fileName") != null) {
//						List<String> errorDataList = (List<String>) map.get("line");
//						String fileName = (String) map.get("fileName");
//						process(fileName, errorDataList);
//					}
//				}
//			} catch (JMSException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	/**
//	 * 处理一个文件的内容
//	 * 
//	 * @param fileName
//	 * @param list
//	 */
//	public void process(String fileName, List<String> list) {
//		String content = processList(list);
//		fileName = fileName.replace("BOSS2PBSS", "PBSS2BOSS");
//		String path = sourceDir + "/" + fileName;
//		createFile(path, sourceDir, content);
//		try {
//			GZipUtil.compress(path, true); // 压缩文件
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		upload(sourceDir);
//	}
//
//	private String processList(List<String> list) {
//		String content = "";
//		for (int i = 0; i < list.size(); i++) {
//			content += list.get(i) + "\r\n";
//		}
//		logger.debug("content:" + content);
//		return content;
//	}
//
//	/**
//	 * 创建一个文件，如果没有目录则创建一个目录:path = dir + file
//	 * 
//	 * @param file
//	 * @param dir
//	 * @param content
//	 * @throws IOException
//	 */
//	public void createFile(String path, String dir, String content) {
//		BufferedWriter output = null;
//		try {
//			File dirFile = new File(dir);
//			dirFile.mkdirs();
//			output = new BufferedWriter(new FileWriter(path));
//			output.write(content);
//			output.close();
//		} catch (IOException e) {
//			logger.error("error:" + e.getMessage());
//			if(output != null) {
//				try {
//					output.close();
//				} catch (IOException e1) { 
//					logger.error("error:" + e1.getMessage());
//				}
//			}
//		}
//	}
//	
//	/**
//	 * 上传文件
//	 * 
//	 * @param sourceDir
//	 */
//	public void upload(String sourceDir) {
//		String[] urlArray = this.ftpUrl.split("//");
//		String ip = urlArray[1];
//		String pathUsernamePassword = urlArray[2];
//		String[] params = pathUsernamePassword.split("\\?");
//		String path = "/" + params[0];
//		String[] nameAndPassword = params[1].split("&");
//		String name = nameAndPassword[0].split("=")[1];
//		String password = nameAndPassword[1].split("=")[1];
//
//		try {
//			UploadUtil.connect(path, ip, 21, name, password);
//			File file = new File(sourceDir);
//			boolean isUpload = UploadUtil.upload(file);
//			logger.debug("isUpload=" + isUpload);
//		} catch (Exception e) {
//			logger.error("error:" + e.getMessage());
//			try {
//				UploadUtil.ftpClient.disconnect();//close
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		}
//
//	}
//
//	public void setFtpUrl(String ftpUrl) {
//		this.ftpUrl = ftpUrl;
//	}
//
//	public void setSourceDir(String sourceDir) {
//		this.sourceDir = sourceDir;
//	}
//
//}
