package org.wanwanframework.springboots;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DateSourceTest {

	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testDataSource() {
		System.out.println("datasource:" + dataSource);
		
	}
}
