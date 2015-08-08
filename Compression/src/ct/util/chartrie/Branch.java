package ct.util.chartrie;

import java.util.Stack;

public class Branch {
	private String[] edges;
	
	public Branch(Stack<String> branch) {
		edges = branch.toArray(new String[0]);
	}

	public String[] getEdges() {
		return edges;
	}
}
