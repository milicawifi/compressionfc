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
			System.out.print(whitespaces+child.first);
			child.third.print(whitespaces+" ");
		}
	}

	public String getB() {
		StringBuilder sb = new StringBuilder();
		for (int i = children.size()-1 ; i>=0; i--) {
			sb.append(children.get(i).first);
		}
		for (int i = 0; i <children.size(); i++) {
			sb.append(children.get(i).third.getB());
		}
		return sb.toString();
	}
	
	public String getBP() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < children.size(); i++) {
			sb.append("(");
		}
		sb.append(")");
		for (Triplet<Character, Integer, CompactNode> child: children) {
		  sb.append(child.third.getBP());
		}
		return sb.toString();
	}
	
	public String getL() {
		StringBuilder sb = new StringBuilder(label);
		for (Triplet<Character, Integer, CompactNode> child : children) {
			sb.append(child.third.getL());
		}
		return sb.toString();
	}
	
	public String serialize() {
		StringBuilder sb = new StringBuilder();
		sb.append("B:\t");
		sb.append(getB());
		sb.append("\nL:\t");
		sb.append(getL());
		sb.append("\nBP:\t(");
		sb.append(getBP());
		return sb.toString();
	}
}
