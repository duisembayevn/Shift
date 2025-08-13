package analyzers;

import java.util.ArrayList;
import java.util.Comparator;

public class StringAnalyzer implements TypeAnalyzer {
	
	private ArrayList<String> strings;
	
	public StringAnalyzer(ArrayList<String> strings) {
		this.strings = strings;
		strings.sort(Comparator.comparingInt(String::length));
	}
	
	@Override
	public String makeAnalyze(AnalyzeStrategy strategy) {
		switch (strategy) {
		case Short:
			return shortAnalyze();
		case Full:
			return fullAnalyze();
		default:
			return null;
		}
	}

	private String shortAnalyze() {
		String result = "Короткий анализ строк";
		result += "\n- Все: " + strings;
		result += "\n- Количество: " + strings.size();
		return result;
	}

	private String fullAnalyze() {
		String result = "Полный анализ строк";
		result += "\n- Все: " + strings;
		result += "\n- Количество: " + strings.size(); 
		result += "\n- Мин: " + strings.getFirst();
		result += "\n- Макс: " + strings.getLast();
		return result;
	}
}