package ct.util.trie;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import ct.util.chartrie.Branch;
import ct.util.chartrie.CharNode;
import ct.util.compacttrie.CompactTrie;

public class TrieTest {

	Trie trie;
	
	@Before
	public void setUp() {
		CharNode charTrie = new CharNode();
		charTrie.addEntry("three");
		charTrie.addEntry("trial");
		charTrie.addEntry("triangle");
		charTrie.addEntry("triangular");
		charTrie.addEntry("trie");
		charTrie.addEntry("triple");
		charTrie.addEntry("triplys");
		charTrie.addEntry("triplyfing");
		trie = Trie.convert(charTrie);
	}
	
	@Test
	public void testSerialize() {
		CompactTrie compactTrie = trie.compact();
		assertEquals("B:\thpeluys\nL:\tt1ri2a1ng1lelarl1e1fingree\nBP:\t(((((())))()()))", compactTrie.serialize());		
	}

}
