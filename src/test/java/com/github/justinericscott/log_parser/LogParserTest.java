package com.github.justinericscott.log_parser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.Test;

class LogParserTest {

	@Test
	void run() {
		File file = getFile("tomcat-stdOut.log");
		assertNotNull(file);
		assertTrue(file.exists());
		LogParser parser = new LogParser();
		List<String> list = parser.parse(file);
		assertNotNull(list);
		assertFalse(list.isEmpty());
		list.forEach(s -> {
			System.out.println(s);
		});
	}
	
	@Test
	public void runK() {
		File file = getFile("db_PA00001_2018-09-11_1937.log");
		assertNotNull(file);
		assertTrue(file.exists());
		LogParser parser = new LogParser();
		List<String> list = parser.parse(file);
		assertNotNull(list);
		assertFalse(list.isEmpty());
		list.forEach(s -> {
			System.out.println(s);
		});
	}
	
	private File getFile(String path) {
		try {
			URL url = ClassLoader.getSystemResource(path);
			File file = new File(url.toURI());
			if (file != null && file.exists()) {
				return file;
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
