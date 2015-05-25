package emko.bbgenui.wordlist;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * A bindable view model for the Word class
 */
public class WordViewModel {
	
	private Word word;
	
	private final StringProperty content;
	private final LongProperty probability;
	
	
//	private String content;
//	private long probability;

	/**
	 * Creates a bindable view model with empty word
	 */
	public WordViewModel() {
		word = new Word();
		content = new SimpleStringProperty();
		probability = new SimpleLongProperty();
		
		addPropertyChangeListeners();
	}
	
	/**
	 * Creates a bindable view model for certain word
	 * @param word
	 */
	public WordViewModel(Word word) {
		this.word = word;
		content = new SimpleStringProperty(word.getContent());
		probability = new SimpleLongProperty(word.getProbability());
		addPropertyChangeListeners();
	}

	private void addPropertyChangeListeners() {
		this.content.addListener((val, old, nw) -> word.setContent(nw));
		this.probability.addListener((val, old, nw) -> word.setProbability((long)nw));
	}
	
	/**
	 * Returns the underlying word bean, updated if any changes made 
	 * @return word bean
	 */
	public Word getWord() {
		return word;
	}

	public final StringProperty contentProperty() {
		return this.content;
	}

	public final java.lang.String getContent() {
		return this.contentProperty().get();
	}

	public final void setContent(final java.lang.String content) {
		this.contentProperty().set(content);
	}

	public final LongProperty probabilityProperty() {
		return this.probability;
	}

	public final long getProbability() {
		return this.probabilityProperty().get();
	}

	public final void setProbability(final long probability) {
		this.probabilityProperty().set(probability);
	}

}
