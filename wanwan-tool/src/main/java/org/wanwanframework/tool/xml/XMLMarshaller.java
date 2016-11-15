package org.wanwanframework.tool.xml;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ZengGaohua on 2016-07-20.
 */

public class XMLMarshaller {
    private static final Logger logger = LoggerFactory.getLogger(XMLMarshaller.class);

	public static String toXML(Object object, String contextPath) throws JAXBException {
        StringWriter writer  = new StringWriter();
        JAXBContext  context = JAXBContext.newInstance(contextPath);
        Marshaller   marshal = context.createMarshaller();

        marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化输出
        marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// 编码格式,默认为utf-8
        marshal.setProperty(Marshaller.JAXB_FRAGMENT, true);// 是否省略xml头信息
        marshal.setProperty("jaxb.encoding", "utf-8");
        marshal.marshal(object, writer);

        return new String(writer.getBuffer());
    }

    public static Object toObject(String xml, String contextPath) throws JAXBException {
        if(xml == null ||  xml.equals("")){
            return null;
        }

        JAXBContext  context      = JAXBContext.newInstance(contextPath);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        try {
            return unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
