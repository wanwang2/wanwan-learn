package org.wanwanframework.tool.relation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

	private BufferedWriter writer;
	private File file;

	public void execute(String content) throws IOException {
		BufferedWriter output = getWriter();
		output.write(content);
		output.close();
	}

	public BufferedWriter getWriter() throws IOException {
		return writer == null ? writer = new BufferedWriter(new FileWriter(file)) : writer;
	}

	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
		writer = null;
	}

}
