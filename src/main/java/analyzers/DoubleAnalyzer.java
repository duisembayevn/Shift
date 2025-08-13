package analyzers;

import java.util.ArrayList;
import java.util.Collections;

public class DoubleAnalyzer implements TypeAnalyzer {
	
	private ArrayList<Double> doubles;
	
	public DoubleAnalyzer(ArrayList<Double> doubles) {
		this.doubles = doubles;
		Collections.sort(doubles);
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
		String result = "Короткий анализ вещественных чисел";
		result += "\n- Все: " + doubles;
		result += "\n- Количество: " + doubles.size();
		return result;
	}

	private String fullAnalyze() {
		String result = "Полный анализ вещественных чисел";
		result += "\n- Все: " + doubles;
		result += "\n- Мин: " + doubles.getFirst(); 
		result += "\n- Макс: " + doubles.getLast();
		result += "\n- Сумма: " + sum();
		result += "\n- Среднее: " + average();
		return result;
	}
	
	private double sum() {
		double sum = 0;
		for (int i=0; i<doubles.size(); i++) {
			sum += doubles.get(i);
		}
		return sum;
	}
	
	private double average() {
		return sum() / doubles.size();
	}

	
}