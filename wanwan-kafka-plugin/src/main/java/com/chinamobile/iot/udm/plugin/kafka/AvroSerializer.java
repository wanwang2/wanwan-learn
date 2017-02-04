package com.chinamobile.iot.udm.plugin.kafka;

import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by zhaofeng on 2016/12/9.
 */
public class AvroSerializer<T extends SpecificRecordBase> implements Serializer<T> {

    private final static Logger logger = LoggerFactory.getLogger(AvroSerializer.class);

    public void configure(Map<String, ?> configs, boolean isKey) {
    }


    public byte[] serialize(String topic, T data) {
        DatumWriter<T> datumWriter = new SpecificDatumWriter(data.getSchema());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BinaryEncoder binaryEncoder = EncoderFactory.get().directBinaryEncoder(outputStream, null);
        try {
            datumWriter.write(data, binaryEncoder);
            return outputStream.toByteArray();
        } catch (IOException e) {
            logger.error("error occured when serializing and the error message is : " + e.getMessage());
            throw new SerializationException(e.getMessage());
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void close() {

    }
}