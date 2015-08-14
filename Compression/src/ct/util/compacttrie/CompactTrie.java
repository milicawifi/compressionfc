package ct.util.compacttrie;

public class CompactTrie {

	private CompactNode root;

	public CompactTrie(CompactNode root) {
		this.root = root;
	}

	public void print(String whitespaces) {
		root.print(whitespaces);
	}
	
	public String serialize() {
		return root.serialize();
	}
}
