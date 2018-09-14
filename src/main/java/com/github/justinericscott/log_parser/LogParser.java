package com.github.justinericscott.log_parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogParser {
	
	public List<String> parse(File file) {
		if (file != null && file.exists()) {
			try (Scanner scan = new Scanner(file)){
				List<String> list = new ArrayList<String>();
				while(scan.hasNextLine()) {
					String line = scan.nextLine();
					if (line != null && !line.isEmpty()) {
						if (line.contains("WARN") || line.contains("SEVERE")) {
							list.add(line);
						}
					}
				}
				if (!list.isEmpty()) {
					return list;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return null;
	}
}
