package emko.bbgenui.wordlist;

import java.util.List;

/**
 * The result of operation for generating the list of words to be used
 * during construction of a game board
 */
public class WordListGenerateResult {
	public final WordListGeneratorStatus status;
	public final List<Word> wordList;
	
	/**
	 * Creates a new operation result
	 * @param status whether the operation completed successfully or not
	 * @param wordList the list of words to be used
	 */
	public WordListGenerateResult(WordListGeneratorStatus status,
			List<Word> wordList) {
		this.status = status;
		this.wordList = wordList;
	}
}
