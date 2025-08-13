package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileParser {
	
	private String file;
	
	public FileParser(String file) {
		this.file = file;
	}
		
	public ParsedData parse() {
		try {
			return parse(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private ParsedData parse(String file) throws IOException {
		ArrayList<String> strings = new ArrayList<>();
		ArrayList<Integer> integers = new ArrayList<>();
		ArrayList<Double> doubles = new ArrayList<>();
		
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader buffReader = new BufferedReader(fileReader);
			String line;
			
			while ((line = buffReader.readLine()) != null) {
				line = line.trim();
				
				if (line.isEmpty()) continue;
				
				if (isInteger(line)) {
					integers.add(Integer.parseInt(line));
				} else if (isDouble(line)) {
					doubles.add(Double.parseDouble(line));
				} else { 
					strings.add(line);
				}
			}
			
			buffReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Пожалуйста укажите полный путь к файлам");
			System.exit(0);
		}
		
		return new ParsedData(strings, integers, doubles);
	}
	
	private boolean isInteger(String line) {
		try {
			Integer.parseInt(line);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private boolean isDouble(String line) {
		try {
			Double.parseDouble(line);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}