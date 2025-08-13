package result_output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutputProvider implements OutputProvider {
	
	private String fileName;
	private FileOutputConfig config;
	private String outputResult;
	
	public FileOutputProvider(String fileName, FileOutputConfig config, String outputResult) {
		this.fileName = fileName;
		this.config = config;
		this.outputResult = outputResult;
	}
	
	@Override
	public void makeOutput() {
		File directory = new File(exportPath());
		if (!directory.exists()) {
			if (!directory.mkdir()) {
				System.out.println("Не смогли записать файл в: " + exportPath());
				System.exit(0);
			}
		}
		
		File file = new File(exportPath(), finalFileName());
		boolean append;
		
		if (config.strategy == FileOutputStrategy.Create) {
			append = false;
		} else {
			append = file.exists();
		}
		
		try {
			FileWriter fileWriter = new FileWriter(file, append);
			try (BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
				if (append) {
					bufferWriter.write("\n=========================\n");
				}
				bufferWriter.write(outputResult);
			}
		} catch (IOException e) {
			System.out.println("Не смогли записать файл в: " + exportPath());
			System.out.println("Ошибка: " + e.getLocalizedMessage());
			System.exit(0);
		}
	}
	    
	private String finalFileName() {
		if (config.prefix != null && !config.prefix.isEmpty()) {
			return config.prefix + "_" + fileName;
		} else {
			return fileName;
		}
	}
		
	private String exportPath() {
		if (config.path != null && !config.path.isEmpty()) {
			return config.path;
		} else {
			return "/";
		}
	}
}