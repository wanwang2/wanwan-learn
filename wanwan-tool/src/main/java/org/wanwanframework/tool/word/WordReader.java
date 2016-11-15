package org.wanwanframework.tool.word;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

public class WordReader {

	public static final String path = "./src/main/resources/word/test-2007.docx";
	
	public static void main(String[] args) {
		try {
//			InputStream is = new FileInputStream(new File("2003.doc"));
//			WordExtractor ex = new WordExtractor(is);
//			String text2003 = ex.getText();
//			System.out.println(text2003);

			OPCPackage opcPackage = POIXMLDocument.openPackage(path);
			POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
			String text2007 = extractor.getText();
			System.out.println(text2007);
			extractor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
