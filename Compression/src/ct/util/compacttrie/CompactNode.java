package ct.util.compacttrie;

import java.util.*;

import ct.util.Pair;
import ct.util.Triplet;

public class CompactNode {
	private final String label;
	private final ArrayList<Triplet<Character, Integer, CompactNode>> children;
	
	public CompactNode(String label) {
		this.label = label;
		children = new ArrayList<Triplet<Character, Integer, CompactNode>>();
	}

	public void addChild(Pair<Character, CompactNode> child, int backLength) {
		children.add(new Triplet<Character, Integer, CompactNode>(child.first, backLength, child.second));
	}

	public void print(String whitespaces) {
		System.out.println(label);
		for (Triplet<Character, Integer, CompactNode> child : children){
			System.out.print(whitespaces+child.second+ child.first);
			child.third.print(whitespaces+" ");
		}
	}

	public String serialize() {
		StringBuilder sb = new StringBuilder(label);
		
		return sb.toString();
	}
}
