package app;

import java.util.ArrayList;

public class ParsedData {
	ArrayList<String> strings;
	ArrayList<Integer> integers;
	ArrayList<Double> doubles;
	
	public ParsedData(ArrayList<String> strings, ArrayList<Integer> integers, ArrayList<Double> doubles) {
		this.strings = strings;
		this.integers = integers;
		this.doubles = doubles;
	}
}
