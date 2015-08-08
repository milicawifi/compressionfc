package ct.util.trie;

import java.util.*;

public class Node {
	TreeMap<String, Node> children;

	public Node() {
		children = new TreeMap<String, Node>();
	}
	
	public Node getChild(String edge) {
		if (!children.containsKey(edge)) {
			children.put(edge, new Node());
		}
		return children.get(edge);
	}

	public void print(String whitespace) {
		whitespace = whitespace+" ";
		for (Map.Entry<String, Node> child : children.entrySet()){
			System.out.print(whitespace);
			System.out.println(child.getKey());
			child.getValue().print(whitespace);
		}
	}
}
