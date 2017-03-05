import java.util.List;

public interface Handler {
	
	void readFromFile(String seedFilePath);
	
	void processFile(List<String> lines);
	
	void doAction(String record);
	

}
