package com.github.justinericscott.log_parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogParser {
	private File file;
	private List<String> lines = new ArrayList<String>();
	private List<String> tokens = new ArrayList<String>();
	private List<List<String>> processed = new ArrayList<List<String>>();

	public void run() {
		if (file != null && file.exists()) {
			parse();
			lines.forEach(line -> {
				process(line);
			});
		}
	}

	public void parse() {
		if (file != null && file.exists()) {
			try (Scanner scan = new Scanner(file)) {
				while (scan.hasNextLine()) {
					String line = scan.nextLine();
					if (line != null && !line.isEmpty()) {
						if (line.contains("WARN") || line.contains("SEVERE")) {
							lines.add(line);
						}
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void process(String str) {
		if (str != null && !str.isEmpty()) {
			try (Scanner scan = new Scanner(str)) {
				while (scan.hasNext()) {
					if (str.startsWith("[")) {
						scan.useDelimiter("] ");
					}
					if (str.startsWith("{")) {
						scan.useDelimiter("} ");
					}
					if (str.startsWith("(")) {
						scan.useDelimiter(") ");
					}
					String s = scan.next();
					System.err.println(s);
					tokens.add(s);
					scan.reset();
				}
				processed.add(tokens);
				tokens.clear();
			}
		}
	}

	public List<String> getLines() {
		return lines;
	}

	public List<List<String>> getProcessed() {
		return processed;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
