import java.util.Comparator;

/**
 * COMP 2503 Winter 2020 Assignment 2 February 28, 2020
 * 
 * Comparator class to order wordlist. When called, returns an int to determine
 * sort order, first by highest frequency to lowest, then alphabetically by
 * word.
 * 
 * @author Salim Manji
 * @param <T>
 *
 */

public class FreqAsc implements Comparator<Token> {

	/**
	 * Compares two Token objects to determine sort order, first by lowest frequency
	 * of the word count, then by reverse alphabetical ordering.
	 * 
	 * @param Two objects of type Token.
	 * @return -1 if token1 is greater than token2, +1 if token1 is less than
	 *         token2.
	 * 
	 */
	public int compare(Token token1, Token token2) {
		int result = token1.getFrequency() - token2.getFrequency();

		if (result == 0)
			result = token1.getWord().compareTo(token2.getWord());

		return result;
	}

}
