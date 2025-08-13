package result_output;

public class ConsoleOutputProvider implements OutputProvider {

	private String[] result;
	
	public ConsoleOutputProvider(String[] result) {
		this.result = result;
	}

	@Override
	public void makeOutput() {
		for (int i=0; i<result.length; i++) {
			System.out.println(result[i]);
		}
	}
}
