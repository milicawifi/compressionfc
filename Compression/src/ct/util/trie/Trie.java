package ct.util.trie;

import ct.util.chartrie.Branch;
import ct.util.chartrie.CharNode;

public class Trie {
	Node root;
	
	public Trie() {
		root = new Node();
	}
	
	public static Trie convert(CharNode charTrie) {
		Trie result = new Trie();
		for (Branch branch : charTrie.allBranches()) {
			result.addBranch(branch);
		}
		return result;
	}

	private void addBranch(Branch branch) {
		Node t = root;
		for (String edge : branch.getEdges()) {
			t = t.getChild(edge);
		}
	}

	public void print() {
		root.print("");
	}
}
