package org.wanwanframework.tool.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解析xml报文（文件）根节点
 */
public final class XMLParser {
    private final static Logger logger = LoggerFactory.getLogger(XMLParser.class);

    /**
     * 读取xml文件获得Document对象
     *
     * @param xmlFile xml文件
     * @return Document
     * @throws DocumentException xml异常
     */
    public static Document getDocument(File xmlFile) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document  doc    = reader.read(xmlFile);

        if (doc != null) {
            logger.debug("Receive xml from file:{}", doc.asXML());
        }

        return doc;
    }

    /**
     * 获取报文根节点
     *
     * @param xml 报文
     * @return 报文根节点Element
     */
    public static Element getRootElement(String xml) {
        Document doc = null;

        try {
            doc = XMLParser.getDocument(xml);
        } catch (UnsupportedEncodingException e) {
            logger.error("Xml charset is not supported", e);
        } catch (DocumentException e) {
            logger.error("Parse xml doc occurred error", e);
        }

        return doc != null ? doc.getRootElement() : null;
    }

    /**
     * 获取Document对象
     *
     * @param xml 报文
     * @return doc对象
     * @throws UnsupportedEncodingException 字符集异常
     * @throws DocumentException            获取dom异常
     */
    public static Document getDocument(String xml) throws UnsupportedEncodingException, DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
    }

}
