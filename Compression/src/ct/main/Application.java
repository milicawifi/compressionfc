package ct.main;

import java.io.IOException;

import ct.util.DictFile;
import ct.util.chartrie.CharNode;
import ct.util.compacttrie.CompactTrie;
import ct.util.trie.Trie;

public class Application {

	private DictFile inputFile;
	private CompactTrie compactTrie;

	public Application(String inputFilePath) throws IOException {
		inputFile = new DictFile(inputFilePath);
	}

	private void compress() {
		CharNode charTrie = new CharNode();
		while (true) {
			String line = inputFile.nextLine();
			if (line == null) {
				break;
			}
			charTrie.addEntry(line.toLowerCase().toCharArray());
		}
		System.out.println("trie:");
		Trie trie = Trie.convert(charTrie);
		trie.print();
		System.out.println("compact trie:");
		compactTrie = trie.compact();
		compactTrie.print("");
	}

	public static void main(String[] args) {
		try {
			Application app = new Application("inputDict.txt");
			app.compress();		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
