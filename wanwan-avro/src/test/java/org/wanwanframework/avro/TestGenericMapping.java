package org.wanwanframework.avro;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.junit.Test;

import junit.framework.Assert;

public class TestGenericMapping {

	@Test
	public void test() throws IOException {
		// 将schema从StringPair.avsc文件中加载
		Schema.Parser parser = new Schema.Parser();
		Schema schema = parser.parse(getClass().getResourceAsStream("/StringPair.avsc"));

		GenericRecord record = new GenericData.Record(schema); // 根据schema创建一个record示例
		record.put("left", "L");
		record.put("right", "R");

		ByteArrayOutputStream out = writeData(schema, record);
		GenericRecord result = readData(schema, out);
		System.out.println("result:" + result);
		Assert.assertEquals("L", result.get("left").toString());
		Assert.assertEquals("R", result.get("right").toString());
	}

	private ByteArrayOutputStream writeData(Schema schema, GenericRecord record) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// DatumWriter可以将GenericRecord变成edncoder可以理解的类型
		DatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
		// encoder可以将数据写入流中，binaryEncoder第二个参数是重用的encoder，这里不重用，所用传空
		Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
		try {
			writer.write(record, encoder);
			encoder.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}

	/**
	 * 获取Record
	 * 
	 * @param schema
	 * @param out
	 * @return
	 */
	private GenericRecord readData(Schema schema, ByteArrayOutputStream out) {
		DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>(schema);
		Decoder decoder = DecoderFactory.get().binaryDecoder(out.toByteArray(), null);
		try {
			return reader.read(null, decoder);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
