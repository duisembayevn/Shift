package app;

import java.util.ArrayList;
import java.util.List;

import analyzers.*;
import result_output.*;

public class AnalyzerApp {
	
	private List<String> files;
	private AnalyzeStrategy analyzeStrategy;
	private FileOutputConfig outputConfig;
	
	public AnalyzerApp(
		List<String> files, 
		AnalyzeStrategy analyzeStrategy,
		FileOutputConfig outputConfig
	) {
		this.files = files;
		this.analyzeStrategy = analyzeStrategy;
		this.outputConfig = outputConfig;
	}
	
	public void analyze() {
		ParsedData totalData = prepareData();
		
		String stringResult = new StringAnalyzer(totalData.strings).makeAnalyze(analyzeStrategy);
		String doubleResult = new DoubleAnalyzer(totalData.doubles).makeAnalyze(analyzeStrategy);
		String integerResult = new IntegerAnalyzer(totalData.integers).makeAnalyze(analyzeStrategy);
		
		FileOutputProvider stringOutputProvider = new FileOutputProvider("string.txt", outputConfig, stringResult);
		FileOutputProvider doubleOutputProvider = new FileOutputProvider("double.txt", outputConfig, doubleResult);
		FileOutputProvider integerOutputProvider = new FileOutputProvider("integer.txt", outputConfig, integerResult);
		
		stringOutputProvider.makeOutput();
		doubleOutputProvider.makeOutput();
		integerOutputProvider.makeOutput();
		
		String[] consoleResult = new String[] { stringResult, doubleResult, integerResult };
		ConsoleOutputProvider consoleOutputProvider = new ConsoleOutputProvider(consoleResult);
		consoleOutputProvider.makeOutput();
	}
	
	private ParsedData prepareData() {
		ArrayList<String> strings = new ArrayList<>();
		ArrayList<Integer> integers = new ArrayList<>();
		ArrayList<Double> doubles = new ArrayList<>();
		
		for (int i = 0; i<files.size(); i++) {
			String file = files.get(i);
			ParsedData parsedData = new FileParser(file).parse();
			
			strings.addAll(parsedData.strings);
			integers.addAll(parsedData.integers);
			doubles.addAll(parsedData.doubles);
		}
		
		return new ParsedData(
			strings,
			integers,
			doubles
		);
	}
}