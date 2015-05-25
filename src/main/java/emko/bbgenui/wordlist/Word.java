package emko.bbgenui.wordlist;

/**
 * Represents a single word which can be used in a board. Content is an actual
 * content to be shown on the board. Probability should be a positive, relative weight.
 */
public class Word {
	
	private String content;
	private long probability;
	
	/**
	 * Creates a new empty word with 0 probability
	 */
	public Word() {
		content = "";
		probability = 0;
	}

	/**
	 * Creates a new empty word
	 * @param content the actual content to be shown on the board
	 * @param probability the probability which the word will be shown on the board
	 */
	public Word(String content, long probability) {
		this.content = content;
		this.probability = probability;
	}

	/**
	 * @return the content of the word
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @param content the content of the word to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * @return the probability of selecting the word to a board
	 */
	public long getProbability() {
		return probability;
	}
	
	/**
	 * @param probability the probability of selecting the word to a board to set
	 */
	public void setProbability(long probability) {
		this.probability = probability;
	}
	
	

}
