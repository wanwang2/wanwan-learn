package com.chinamobile.iot.udm.plugin.kafka;

/**
 * Created by zhaofeng on 2016/12/9.
 */
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class AvroDeserializer<T extends SpecificRecordBase> implements Deserializer<T> {
    private final static Logger logger = LoggerFactory.getLogger(AvroSerializer.class);

    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    public T deserialize(String topic, byte[] data) {
        DatumReader<T> datumReader = new SpecificDatumReader(AvroRepository.getSchemaByTopic(topic));
        InputStream inputStream = new ByteArrayInputStream(data);
        BinaryDecoder binaryEncoder = DecoderFactory.get().directBinaryDecoder(inputStream, null);
        try {
            return datumReader.read(null, binaryEncoder);
        } catch (IOException e) {
            logger.error("error occured when deserializing and the error message is : " + e.getMessage());
            throw new SerializationException(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void close() {

    }
}