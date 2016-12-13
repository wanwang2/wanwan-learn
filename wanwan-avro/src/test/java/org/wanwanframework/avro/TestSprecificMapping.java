package org.wanwanframework.avro;

import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Sprecific
 * @author coco
 *
 */
public class TestSprecificMapping {

	@Test
	public void test() throws IOException {
		// 因为已经生成StringPair的源代码，所以不再使用schema了，直接调用setter和getter即可
		StringPair pair = new StringPair();
		pair.setLeft("L");
		pair.setRight("R");

		ByteArrayOutputStream out = writeData(pair);
		StringPair readPair = readData(out);
		System.out.println("result:" + readPair);
		Assert.assertEquals("L", readPair.getLeft().toString());
		Assert.assertEquals("R", readPair.getRight().toString());
	}
	
	/**
	 * 写：写到一个流里面
	 * @param pair
	 * @return
	 */
	private ByteArrayOutputStream writeData(StringPair pair) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DatumWriter<StringPair> writer = new SpecificDatumWriter<StringPair>(StringPair.class);
		Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
		try {
			writer.write(pair, encoder);
			encoder.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}

	/**
	 * 读:从一个流里面读取
	 * @param out
	 * @return
	 */
	private StringPair readData(ByteArrayOutputStream out) {
		DatumReader<StringPair> reader = new SpecificDatumReader<StringPair>(StringPair.class);
		Decoder decoder = DecoderFactory.get().binaryDecoder(out.toByteArray(), null);
		try {
			return reader.read(null, decoder);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
