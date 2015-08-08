package ct.util;
import java.io.*;

public class DictFile {
	private BufferedReader reader;
	
	public DictFile(String inputFilePath) throws IOException{
		reader = new BufferedReader(new FileReader(new File(inputFilePath)));
	}

	public String nextLine() {
		try {
			return reader.readLine();
		} catch (IOException e){
			e.printStackTrace();
			return null;
		}
	}

}
