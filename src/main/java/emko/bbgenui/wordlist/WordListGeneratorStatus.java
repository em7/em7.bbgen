package emko.bbgenui.wordlist;

/**
 * The status of generating the word list
 */
public enum WordListGeneratorStatus {
	
	/**
	 * Operation completed successfully
	 */
	SUCCESS,
	
	/**
	 * There were too few words in the source word list
	 */
	FAIL_NOT_ENOUGH_WORDS,

}
