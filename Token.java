/**
 * COMP 2503 Winter 2020 Assignment 2 February 28, 2020
 * 
 * Token Class to instantiate Token object when a unique word is read in by the
 * Scanner. Implements Comparable interface.
 * 
 * @author Salim Manji
 *
 */
public class Token implements Comparable<Token> {

	private String word;
	private int frequency;

	/**
	 * Constructor to create a new Token object, taking in one String (the current
	 * word being read in by the file reader) and one int (the frequency of the
	 * word). All new words are initialized to one.
	 * 
	 * @param word is the current String read in by the file reader.
	 * 
	 */

	public Token(String word) {
		this.word = word;
		frequency = 1;
	}

	/**
	 * Finds and returns word (String) held by this Token object.
	 * 
	 * @return current word held by Token object.
	 * 
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Finds and returns the frequency count (int) held by this Token object.
	 * 
	 * @return number of times the current word has appeared in the input .txt file.
	 * 
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * When an an existing (not unique) word is read in by the file reader, this
	 * method will increase the count by one.
	 * 
	 */
	public void increaseFrequency() {
		frequency++;
	}

	/**
	 * Determines if the current word and the word being compare against are the
	 * same.
	 * 
	 */
	@Override
	public int compareTo(Token token2) {
		return this.getWord().compareTo(token2.getWord());
	}

	/**
	 * Determines if the current token is the same as the other token (same
	 * reference).
	 * 
	 * @param other is the token to compare to.
	 * @return True if this token is the same as the other Token, False if the
	 *         tokens are not the same.
	 * 
	 */
	public boolean equals(Token other) {
		return this.equals(other);
	}

	public String toString() {
		return this.word + " : " + this.frequency;
	}

}
