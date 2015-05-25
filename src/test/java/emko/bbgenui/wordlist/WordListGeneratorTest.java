package emko.bbgenui.wordlist;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WordListGeneratorTest {
	
	private List<Word> words;

	@Before
	public void setUp() throws Exception {

		Random rand = new Random();		
		words = rand.ints(25, 1, 101).mapToObj(prob -> new Word("CNT", prob))
			.collect(Collectors.toList());
	}

	@Test
	public void testGenerateWordListForBoard_Bounds() {
		WordListGenerator generator = new WordListGenerator(words);
		
		WordListGenerateResult res25 = generator.GenerateWordListForBoard(25);
		assertTrue("status must be success when generating 25 words from 25", res25.status == WordListGeneratorStatus.SUCCESS);
		assertTrue("word list must not be null when generating 25 words from 25", res25.wordList != null);
		assertTrue("word list must contain 25 words when generating 25 words from 25", res25.wordList.size() == 25);
		
		WordListGenerateResult res0 = generator.GenerateWordListForBoard(0);
		assertTrue("status must be success when generating 0 words from 25", res0.status == WordListGeneratorStatus.SUCCESS);
		assertTrue("word list must not be null when generating 0 words from 25", res0.wordList != null);
		assertTrue("word list must contain 0 words when generating 0 words from 25", res0.wordList.size() == 0);
		
		WordListGenerateResult res26 = generator.GenerateWordListForBoard(26);
		assertTrue("status must be FAIL_NOT_ENOUGH_WORDS when generating 26 words from 25", res26.status == WordListGeneratorStatus.FAIL_NOT_ENOUGH_WORDS);
		assertTrue("word list must not be null when generating 26 words from 25", res26.wordList != null);
		assertTrue("word list must be empty when generating 26 words from 25", res26.wordList.size() == 0);
	}
	
	@Test
	public void testGenerateWordListForBoard_Success() {
		WordListGenerator generator = new WordListGenerator(words);
		
		WordListGenerateResult res = generator.GenerateWordListForBoard(10);
		assertTrue("status must be success when generating 10 words from 25", res.status == WordListGeneratorStatus.SUCCESS);
		
		List<Word> wordList = res.wordList;
		long distinctCount = wordList.stream().distinct().count();
		assertTrue("the words in a list must be distinct", wordList.size() == distinctCount);
	}
	

}
