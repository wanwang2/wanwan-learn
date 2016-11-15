package org.wanwanframework.tool.ftp;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class UploadUtil {

	public static FTPClient ftpClient = new FTPClient();

	/**
	 * 上传连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean connect(String path, String addr, int port, String username, String password) throws Exception {
		boolean result = false;
		//ftpClient.setControlEncoding("UTF-8");
		//ftpClient.enterLocalPassiveMode();
		int reply;
		ftpClient.connect(addr, port);
		ftpClient.login(username, password);
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		reply = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftpClient.disconnect();
			return result;
		}
		ftpClient.changeWorkingDirectory(path);
		result = true;
		return result;
	}

	/**
	 * 文件上传
	 * 
	 * @param file
	 *            上传的文件或文件夹
	 * @throws Exception
	 */
	public static boolean upload(File file) throws Exception {
		boolean r = false;
		if (file.isDirectory()) {
			ftpClient.makeDirectory(new String(file.getName().getBytes("UTF-8"), "iso-8859-1"));
			ftpClient.changeWorkingDirectory(file.getName());
			String[] files = file.list();
			for (int i = 0; i < files.length; i++) {
				File file1 = new File(file.getPath() + "\\" + files[i]);
				if (file1.isDirectory()) {
					upload(file1);
					ftpClient.changeToParentDirectory();
				} else {
					File file2 = new File(file.getPath() + "\\" + files[i]);
					FileInputStream input = new FileInputStream(file2);
					r = ftpClient.storeFile(new String(file2.getName().getBytes("UTF-8"), "iso-8859-1"), input);
					System.out.println("storeFile:" + r);
					input.close();
				}
			}
		} else {
			File file2 = new File(file.getPath());
			FileInputStream input = new FileInputStream(file2);
			r = ftpClient.storeFile(file2.getName(), input);
			input.close();
		}
		return r;
	}

	public static void main(String[] args) throws Exception {
		UploadUtil.connect("data", "192.168.68.128", 21, "vv", "vv");
		File file = new File("e:/design/linux/upload");
		boolean r = UploadUtil.upload(file);
		System.out.println("r=" + r);
	}
}