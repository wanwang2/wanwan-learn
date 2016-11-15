package org.wanwanframework.tool.xml;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jaxb2工具类
 * 
 * @author zhuc
 * @create 2013-3-29 下午2:40:14
 */
public class Jaxb2Util {

	private static final Logger logger 			= LoggerFactory.getLogger(Jaxb2Util.class);
	
	/**
	 * JavaBean转换成xml 默认编码UTF-8
	 * 
	 * @param obj
	 * @param
	 * @return
	 */
	public static String convertToXml(Object obj) {
		return convertToXml(obj, "UTF-8");
	}

	/**
	 * JavaBean转换成xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String convertToXml(Object obj, String encoding) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("convertToXml: obj to xml fail");
		}

		return result;
	}

	/**
	 * xml转换成JavaBean
	 * 
	 * @param xml
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T converyToJavaBean(String xml, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("converyToJavaBean: xml to javaBean fail");
		}

		return t;
	}

	public static String formatXML(String inputXML) {
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(new StringReader(inputXML));
		} catch (DocumentException e1) {
			e1.printStackTrace();
			logger.error("formatXML: document read fail");
		}

		String requestXML = null;
		XMLWriter writer = null;

		if (document != null) {
			try {
				StringWriter stringWriter = new StringWriter();
				OutputFormat format = new OutputFormat("	", true);
				writer = new XMLWriter(stringWriter, format);
				writer.write(document);
				writer.flush();
				requestXML = stringWriter.getBuffer().toString();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("formatXML: writer write fail");
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
					}
				}
			}
		}
		return requestXML;
	}
}