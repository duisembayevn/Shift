package app;


import java.util.List;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;

import analyzers.*;
import result_output.*;

public class Main {
	public static void main(String[] args) {
		Options options = new Options();
		options.addOption("h", false, "Показывает все опции");
		options.addOption("o", true, "Задавать путь для результатов");
		options.addOption("p", true, "Задавать префикс имен выходных файлов");
		options.addOption("s", false, "Краткая статистика содержит только количество элементов, записанных в исходящие файлы.");
		options.addOption("f", false, "Полная статистика для чисел дополнительно содержит минимальное и максимальное значения, сумма и среднее. Полная статистика для строк, помимо их количества, содержит также размер самой короткой строки и самой длинной.");
		options.addOption("a", false, "Режим добавления в существующие файлы");
		
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.out.println("Ошибка разбора агрументов: " + e.getMessage());
			System.exit(1);
		}
		
		List<String> fileNames = cmd.getArgList();
		String prefix = cmd.getOptionValue("p");
		String exportPath = cmd.getOptionValue("o");
		AnalyzeStrategy analyzeStrategy = AnalyzeStrategy.Short;
		FileOutputStrategy fileOutputStrategy = FileOutputStrategy.Create;
		
		if (cmd.hasOption("h")) {
			showHelp(options);
			return;
		}
		
		if (cmd.hasOption("f")) {
			analyzeStrategy = AnalyzeStrategy.Full;
		}
		
		if (cmd.hasOption("a")) {
			fileOutputStrategy = FileOutputStrategy.Append;
		}
		
		FileOutputConfig outputConfig = new FileOutputConfig(
			prefix, 
			exportPath, 
			fileOutputStrategy
		);
		
		if (fileNames.isEmpty()) {
			System.out.println("Вы не указали путь к файлам =(");
			System.out.println("Например: /user/Desktop/dump.txt /user/Desktop/dump2.text");
			System.exit(0);
			return;
		}
		
		AnalyzerApp app = new AnalyzerApp(
			fileNames,
			analyzeStrategy,
			outputConfig
		);
		
		System.out.println("<-------- Настройки -------->");
		System.out.println("- Файлы для анализа: " + fileNames);
		System.out.println("- Префикс для файлов: " + prefix);
		System.out.println("- Путь для сохранения: " + exportPath);
		System.out.println("- Мод для выгрузки файлов: " + fileOutputStrategy);
		System.out.println("- Вид анализа: " + analyzeStrategy);
		System.out.println("<-------- Настройки -------->");
		app.analyze();
	}
	
	private static void showHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("Main", options);
	}
}
