/**
 * 
 */
package prSimpleFilesWordCounting;

/**
 * @author Illya Roz
 *
 */
public class WordInText {
	private String word;
	private int times;
	
	/**
	 * Create an object word that store passed parameter in String variable and sets variable times to 1
	 * @param word
	 */
	public WordInText(String word) {
		this.word = word.toUpperCase();
		this.times = 1;
	}
	
	/**
	 * Increment the number of of times this word appears in text.
	 */
	public void increment() {
		this.times++;
	}
	
	@Override
	public boolean equals (Object o) {
		return o instanceof WordInText && ((WordInText) o).word.equals(this.word); 
	}
	
	@Override
	public int hashCode() {
		return this.word.hashCode();
	}
	
	@Override
	public String toString() {
		return word + ": " + times;
	}
}
