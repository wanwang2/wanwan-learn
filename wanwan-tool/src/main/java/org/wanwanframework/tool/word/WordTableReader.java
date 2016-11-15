package org.wanwanframework.tool.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

/**
 * word表格读取器
 * 
 * @author coco
 *
 */
public class WordTableReader {

	public void testWord() {
		try {
			FileInputStream in = new FileInputStream("D:\\sinye.doc");// 载入文档
			POIFSFileSystem pfs = new POIFSFileSystem(in);
			HWPFDocument hwpf = new HWPFDocument(pfs);
			Range range = hwpf.getRange();// 得到文档的读取范围
			TableIterator it = new TableIterator(range);
			// 迭代文档中的表格
			while (it.hasNext()) {
				Table tb = (Table) it.next();
				// 迭代行，默认从0开始
				for (int i = 0; i < tb.numRows(); i++) {
					TableRow tr = tb.getRow(i);
					// 迭代列，默认从0开始
					for (int j = 0; j < tr.numCells(); j++) {
						TableCell td = tr.getCell(j);// 取得单元格
						// 取得单元格的内容
						for (int k = 0; k < td.numParagraphs(); k++) {
							Paragraph para = td.getParagraph(k);
							String s = para.text();
							System.out.println(s);
						} // end for
					} // end for
				} // end for
			} // end while
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// end method

	public void testWord1() {
		try {
			// word 2003： 图片不会被读取
			InputStream is = new FileInputStream(new File("D:\\sinye.doc"));
			WordExtractor ex = new WordExtractor(is);
			String text2003 = ex.getText();
			System.out.println(text2003);
			// word 2007 图片不会被读取， 表格中的数据会被放在字符串的最后
			OPCPackage opcPackage = POIXMLDocument.openPackage("D:\\sinye.doc");
			POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
			String text2007 = extractor.getText();
			System.out.println(text2007);
			extractor.close();
			ex.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
