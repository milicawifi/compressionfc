package ct.util.chartrie;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class CharNode {
	
	private TreeMap<Character, CharNode> children;

	public CharNode() {
		children = new TreeMap<Character, CharNode>();
	}
	
	public CharNode getChild(Character label) {
		if (!children.containsKey(label)) {
			children.put(label, new CharNode());
		}
		return children.get(label);
	}

	public void print() {
		print("");
	}
	
	public void print(String whitespace) {
		whitespace = whitespace+" ";
		for (Character c : children.keySet()) {
			System.out.println(whitespace+c);
			children.get(c).print(whitespace);
		}
	}

	public void addEntry(char[] line) {
		CharNode t = this;
		for (int ind = 0; ind < line.length; ind++) {
			t = t.getChild(line[ind]);
		}
	}


	public ArrayList<Branch> allBranches() {
		ArrayList<Branch> result = new ArrayList<Branch>();
		allBranches("" /* currentPrefix */, new Stack<String>() /* branch */, result);
		return result;
	}
	
	private void allBranches(String currentPrefix, Stack<String> branch, ArrayList<Branch> result) {
		if (children.size() == 0) {
			branch.push(currentPrefix);
			result.add(new Branch(branch));
			branch.pop();
			return;
		}
		boolean branchAdded = false;
		if (children.size() > 1) {
			if (currentPrefix.length() > 0) {
				branch.push(currentPrefix);
				currentPrefix = "";
				branchAdded = true;
			}
		}
		for (Map.Entry<Character, CharNode> entry : children.entrySet()) {
			entry.getValue().allBranches(currentPrefix + entry.getKey(), branch, result);
		}
		if (branchAdded) {
			branch.pop();
		}
	}

}
