package result_output;

public class FileOutputConfig {
	String prefix;
	String path;
	FileOutputStrategy strategy;
	
	public FileOutputConfig(String prefix, String path, FileOutputStrategy strategy) {
		this.prefix = prefix;
		this.path = path;
		this.strategy = strategy;
	}
}
