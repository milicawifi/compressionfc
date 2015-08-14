package ct.util.trie;

import java.util.*;
import java.util.Map.Entry;

import ct.util.Pair;
import ct.util.compacttrie.CompactNode;

public class Node {
	TreeMap<String, Node> children;
	private boolean isUsed;

	public Node() {
		children = new TreeMap<String, Node>();
		isUsed = false;
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
	
	@Override
	public boolean equals(Object obj) {
		Node that = (Node)obj;
		if (that == null) {
			return false;
		}
		if (this == that) {
			return true;
		}
		if (!children.keySet().equals(that.children.keySet())) {
			return false;
		}
		for (Map.Entry<String, Node> thisChild : children.entrySet()) {
			Node thatChildren = that.getChild(thisChild.getKey());
			if (!thisChild.getValue().equals(thatChildren)) {
				return false;
			}
		}
		return true;
	}

	public Entry<String, Node> firstUnusedChild() {
		for (Map.Entry<String, Node> child : children.entrySet()) {
			if (!child.getValue().isUsed()) {
				return child;
			}
		}
		return null;
	}

	public Entry<String, Node> getHeaviestChild() {
		int max = 0;
		Map.Entry<String, Node> heaviestChild = null;
		for (Map.Entry<String, Node> child : children.entrySet()) {
			if (child.getValue().isUsed()) {
				continue;
			}
			int childNumOfLeaves = child.getValue().numOfLeaves();
			if (max < childNumOfLeaves) {
				heaviestChild = child;
				max = childNumOfLeaves;
			}
		}
		return heaviestChild;
	}
	
	public void setUsed() {
		isUsed = true;
	}
	
	private boolean isUsed() {
		return isUsed ;
	}

	private int numOfLeaves() {
		if (children.isEmpty()) {
			return 1;
		}
		
		int sum = 0;
		for (Map.Entry<String, Node> child : children.entrySet()) {
			sum += child.getValue().numOfLeaves();
		}
		return sum;
	}
	
	public Pair<Character, CompactNode> compact(boolean primary) {
		StringBuilder sb = new StringBuilder();
		// Heaviest branch.
		Node t = this;
		Stack<Pair<Integer, Node>> branch = new Stack<Pair<Integer, Node>>();
		if (primary) {
			branch.push(new Pair<Integer, Node>(0, this));
		}
		while (true) {
			Map.Entry<String, Node> child = primary ? t.getHeaviestChild() : t.firstUnusedChild();
			if (child == null) {
				break;
			}
			Node childNode = child.getValue();
			branch.push(new Pair<Integer, Node>(child.getKey().length(), childNode));
			sb.append(child.getKey());
			if (childNode.numOfChildren() > 0) {
			  sb.append(childNode.numOfChildren()-1);
			}
			childNode.setUsed();
			t = childNode;
		}
		Character edge = null;
		
		if(!primary) {
			edge = sb.charAt(0);
			sb.deleteCharAt(0);
		}
		
		CompactNode compactNode = new CompactNode(sb.toString());
	    int suffix = -1;
		// Rest of the edges.
		while (!branch.isEmpty()) {
			Pair<Integer, Node> level = branch.pop();
			Node levelNode = level.second;
		    while (levelNode.hasUnusedChildren()) {
		    	compactNode.addChild(levelNode.compact(false), suffix);
		    }
		    suffix = level.first;
		}
		return new Pair<Character, CompactNode>(edge, compactNode);
	}
	
	private int numOfChildren() {
		return children.size();
	}

	private boolean hasUnusedChildren() {
		for (Map.Entry<String, Node> child : children.entrySet()) {
			if (!child.getValue().isUsed()) {
				return true; 
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Node> child : children.entrySet()) {
			sb.append(child.getKey() + " ");
		}
		return sb.toString();
	}
}
