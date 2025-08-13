package analyzers;

import java.util.ArrayList;
import java.util.Collections;

public class IntegerAnalyzer implements TypeAnalyzer {
	
	private ArrayList<Integer> integers;
	
	public IntegerAnalyzer(ArrayList<Integer> integers) {
		this.integers = integers;
		Collections.sort(integers);
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
		String result = "Короткий анализ целых чисел";
		result += "\n- Все: " + integers;
		result += "\n- Количество: " + integers.size();
		return result;
	}

	private String fullAnalyze() {
		String result = "Полный анализ целых чисел";
		result += "\n- Все: " + integers;
		result += "\n- Мин: " + integers.getFirst();
		result += "\n- Макс: " + integers.getLast(); 
		result += "\n- Сумма: " + sum();
		result += "\n- Среднее: " + average();
		return result;
	}
	
	private int sum() {
		int sum = 0;
		for (int i=0; i<integers.size(); i++) {
			sum += integers.get(i);
		}
		return sum;
	}
	
	private int average() {
		return sum() / integers.size();
	}
}