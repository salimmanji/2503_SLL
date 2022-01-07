import java.util.Scanner;

/**
 * COMP 2503 Winter 2020 Assignment 2 February 28, 2020
 * 
 * This program reads a text file and compiles a list of the words together the
 * frequency each word appears.
 *
 * Words from a standard list of stop words are not included.
 * 
 * @author Salim Manji
 */

public class A2 {

	private static final int NUM_WORDS_TO_PRINT = 10;

	private SLL<Token> wordList = new SLL<>(new Alpha());
	private SLL<Token> wordListAsc = new SLL<>(new FreqAsc());
	private SLL<Token> wordListDesc = new SLL<>(new FreqDesc());

	private String[] stopWords = { "a", "about", "all", "am", "an", "and", "any", "are", "as", "at", "be", "been",
			"but", "by", "can", "cannot", "could", "did", "do", "does", "else", "for", "from", "get", "got", "had",
			"has", "have", "he", "her", "hers", "him", "his", "how", "i", "if", "in", "into", "is", "it", "its", "like",
			"more", "me", "my", "no", "now", "not", "of", "on", "one", "or", "our", "out", "said", "say", "says", "she",
			"so", "some", "than", "that", "the", "their", "them", "then", "there", "these", "they", "this", "to", "too",
			"us", "upon", "was", "we", "were", "what", "with", "when", "where", "which", "while", "who", "whom", "why",
			"will", "you", "your" };

	private int totalWordCount = 0;

	private int stopWordCount = 0;

	private Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		A2 a2 = new A2();
		a2.run();
	}

	/**
	 * Run the program. Read the file, then print the results.
	 */
	public void run() {
		readFile();
		printResults();
	}

	/**
	 * Determines order of and writes the results for the output.txt file once the
	 * program has run its course after determining how many elements to print.
	 * 
	 */
	private void printResults() {
		int numUniqueWords = wordList.getSize();
		int loopEnd = Math.min(NUM_WORDS_TO_PRINT, numUniqueWords);

		System.out.println("Total Words: " + totalWordCount);
		System.out.println("Unique Words: " + numUniqueWords);
		System.out.println("Stop Words: " + stopWordCount);
		System.out.println();

		System.out.println("10 Most Frequent");
		populateList(wordListDesc, numUniqueWords);
		printQuery(wordListDesc, loopEnd);
		System.out.println();

		System.out.println("10 Least Frequent");
		populateList(wordListAsc, numUniqueWords);
		printQuery(wordListAsc, loopEnd);
		System.out.println();

		System.out.println("All");
		printQuery(wordList, numUniqueWords);
	}

	/**
	 * Read the input file and process it. Input is on standard input and is read
	 * one word at a time -- separated by whitespace.
	 * 
	 * All non alphabetic characters are stripped out and words are all converted to
	 * lower case.
	 * 
	 * Any non-stopword word is stored in the list of words and the number of
	 * occurrences is tracked.
	 * 
	 */
	private void readFile() {
		Scanner input = new Scanner(System.in);

		while (input.hasNext()) {
			String currentWord = getWord(input);

			if (isString(currentWord) && !isStopWord(currentWord)) {
				Token currentToken = new Token(currentWord);
				totalWordCount++;
				if (isUnique(currentToken)) {
					addNewWord(currentToken);
				} else {
					increaseTally(currentToken);
				}
			} else if (isString(currentWord) && isStopWord(currentWord)) {
				stopWordCount++;
				totalWordCount++;
			}
		}
		input.close();
	}
	
	/**
	 * Pulls the next word scanned from the input.txt file.
	 * @return The word to be scanned.
	 */
	private String getWord(Scanner input) {
		return input.next().trim().toLowerCase().replaceAll("[^a-z]", "");
	}

	/**
	 * When called, this method searches the singly linked list (SLL) wordList for
	 * the token containing the current string, then determines the index of that
	 * token and increases the frequency counter held by the object.
	 * 
	 * @param toIncrease is the Token created with the current word read by the
	 *                   scanner.
	 * 
	 */
	private void increaseTally(Token toIncrease) {
		Token curr = wordList.find(toIncrease);
		curr.increaseFrequency();
	}

	/**
	 * If a word has not yet been read in by the file reader, a new Token object is
	 * generated through the constructor call, then added to the SLL wordList.
	 * 
	 * @param toAdd is the current Token of the word being read in by the file
	 *              reader that needs to be added to the Array List.
	 * 
	 */
	private void addNewWord(Token toAdd) {
		wordList.add(toAdd);
	}

	/**
	 * Determines if the String read in by the file reader contains a value, or if
	 * it is empty.
	 * 
	 * @param curr is the current word being read in by the file reader that needs
	 *             to be checked.
	 * @return True if length is greater than 0, False if 0 chars or less.
	 * 
	 */
	private boolean isString(String curr) {
		return (curr.length() > 0);
	}

	/**
	 * Cycles through the SLL of stopWords.
	 * 
	 * @param curr is the current word being read in by the file reader that needs
	 *             to be checked.
	 * @return True if curr is on the list of stopWords, False if not on list of
	 *         stopWords.
	 * 
	 */
	private boolean isStopWord(String curr) {
		boolean stopWordFound = false;

		for (String word : stopWords) {
			if (word.equals(curr)) {
				stopWordFound = true;
				break;
			}
		}
		return stopWordFound;
	}

	/**
	 * Determines whether the current word has already been read in by the file
	 * reader and added to the SLL wordList.
	 * 
	 * @param toCheck is the current word being read in by the file reader that
	 *                needs to be checked.
	 * @return True if the word has not yet been read in and is unique, False if it
	 *         has already been read in and is not unique.
	 * 
	 */
	private boolean isUnique(Token toCheck) {
		return (wordList.find(toCheck) == null);
	}

	/**
	 * Confirms the wordList size is greater than zero to avoid a null pointer
	 * exception, then cycles through the SLL to output each word and the occurrence
	 * of that word to the output.txt file.
	 * 
	 * @param endIndex is the size of the SLL if less than 10 unique words are in
	 *                 wordList, 10 if the SLL more than 10 unique words are in SLL
	 *                 or the size of wordList when printing all (10 has been
	 *                 initialized to constant "NUM_WORDS_TO_PRINT").
	 */
	private void printQuery(SLL<Token> list, int endIndex) {
		if (endIndex > 0)
			list.printList(endIndex);
	}

	/**
	 * Uses the copy of the singly linked list (SLL) to create a new list that
	 * implements a comparator.
	 * 
	 * @param toPopulate an empty SLL to populate from a copy of wordList.
	 */
	private void populateList(SLL<Token> toPopulate, int endSignal) {
		for (int index = 0; index < endSignal; index++) {
			toPopulate.add(wordList.get(index));
		}
	}

}
