package ct.util.trie;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import ct.util.chartrie.Branch;

public class TrieTest {

	Trie trie;
	
	@Before
	public void setUp() {
		trie = new Trie();
		Stack<String> edges = new Stack<String>();
		edges.push("ja");
		edges.push("nu");
		edges.push("ar");
		trie.addBranch(new Branch(edges));
	}
	
	@Test
	public void testSerialize() {
		
	}

}
