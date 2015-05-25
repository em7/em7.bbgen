package emko.bbgenui.wordlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Generator for the lists of words to be used for creating the boards
 */
public class WordListGenerator {
	
	private List<Word> words;

	/**
	 * Creates a new instance of a generator for generating the lists of words to be used for creating the boards
	 * @param words full list of words to be created
	 */
	public WordListGenerator(List<Word> words) {
		this.words = words;
	}
	
	/**
	 * Generates a list with certain number of words, depending on their probability
	 * of occurrence. The wordCount to return must not be greater than number of source
	 * words, otherwise the operation will not be successful.
	 * 
	 * @param wordCount number of words to return
	 * @return operation status and word list if success
	 * @throws RuntimeException if generating algorithm fails
	 */
	public WordListGenerateResult GenerateWordListForBoard(int wordCount) {
		if (wordCount <= 0)
			return new WordListGenerateResult(WordListGeneratorStatus.SUCCESS, Collections.emptyList());
		else if (wordCount == words.size())
			return new WordListGenerateResult(WordListGeneratorStatus.SUCCESS, words);
		else if (wordCount > words.size())
			return new WordListGenerateResult(WordListGeneratorStatus.FAIL_NOT_ENOUGH_WORDS, Collections.emptyList());
		
		List<Word> chosenWords = chooseWords(wordCount);
		
		return new WordListGenerateResult(WordListGeneratorStatus.SUCCESS, Collections.unmodifiableList(chosenWords));
	}
	
	/**
	 * Chooses n words depending on their probability of occurrence
	 */
	private List<Word> chooseWords(int n) {
		long maxProb = words.stream().mapToLong(w -> w.getProbability()).sum();
		long currentMaxProb = maxProb;
		long chosenProbSum = 0;
		long chosenN = 0;
		Random rand = new Random();
		List<Word> chosen = new ArrayList<Word>(n);
		
		for (int i = 0; i < n; i++) {
			chosenN = (long)rand.nextDouble() * currentMaxProb;
			chosen.add(fetchChosenWord(chosenN, chosen));
			
			chosenProbSum = chosen.stream().mapToLong(w -> w.getProbability()).sum();
			currentMaxProb = maxProb - chosenProbSum;
		}
		
		return chosen;
	}

	/**
	 * Fetches the chosen word from probability
	 * @param chosenN chosen number
	 * @param alreadyChosen already chosen words to be skipped
	 * @return the chosen word
	 */
	private Word fetchChosenWord(long chosenN, List<Word> alreadyChosen) {
		long currentMaxProb = 0;
		for (Word word : words) {
			if (alreadyChosen.contains(word))
				continue;
			
			currentMaxProb += word.getProbability();
			if (chosenN <= currentMaxProb)
				return word;
		}
		
		throw new RuntimeException("Random number out of probability bound.");
	}

}
