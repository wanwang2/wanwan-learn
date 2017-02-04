package com.chinamobile.iot.udm.plugin.kafka;

import com.chinamobile.iot.udm.api.direct.pboss.UpMessageInfoAvroBean;

import org.apache.avro.Schema;
import org.apache.avro.specific.SpecificRecordBase;

/**
 * Created by zhaofeng on 2016/12/12.
 */
public class AvroRepository {

    private static final Object[][] avroTable = new Object[][]{
           
    };
    private static final Object[][] schemaTable = new Object[][]{
        {"q.pboss.message", UpMessageInfoAvroBean.getClassSchema()}
    };


    public static SpecificRecordBase getAvroObjByTopic(String topicName) {
        for (Object[] record : avroTable) {
            String key = (String) record[0];
            if (key.equals(topicName)) {
                return (SpecificRecordBase) record[1];
            }
        }
        return null;
    }

    public static Schema getSchemaByTopic(String topicName) {
        for (Object[] record : schemaTable) {
            String key = (String) record[0];
            if (key.equals(topicName)) {
                return (Schema) record[1];
            }
        }
        return null;
    }
}
