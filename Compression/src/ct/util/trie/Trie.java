package ct.util.trie;


import ct.util.Pair;
import ct.util.chartrie.Branch;
import ct.util.chartrie.CharNode;
import ct.util.compacttrie.CompactNode;
import ct.util.compacttrie.CompactTrie;

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

	public void addBranch(Branch branch) {
		Node t = root;
		for (String edge : branch.getEdges()) {
			t = t.getChild(edge);
		}
	}

	public void print() {
		root.print("");
	}
	
	@Override
	public boolean equals(Object obj) {
		Trie that = (Trie)obj;
		if (that == null) {
			return false;
		}
		if (this == that) {
			return true;	
		}
		return root.equals(that.root);
	}

	public CompactTrie compact() {
		Pair<Character, CompactNode> compact = root.compact(true);
		assert(compact.first == null);
		return new CompactTrie(compact.second);
	}
}