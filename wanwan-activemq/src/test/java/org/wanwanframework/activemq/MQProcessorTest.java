package org.wanwanframework.activemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "/spring/context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MQProcessorTest {
	
	@Autowired
	private MQProcessor mqProcessor;
	
	@Test
	public void testSend() {
		mqProcessor.send();
	}
}
