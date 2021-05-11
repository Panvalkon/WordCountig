/**
 * 
 */
package prSimpleFilesWordCounting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author Illya Roz.
 */
public class WordCounter {
	private final static int INITIAL_SIZE = 10;
	private int wordsNumber;
	private WordInText[] words;

	public WordCounter() {
		this(INITIAL_SIZE);
	}

	public WordCounter(int size) {
		words = new WordInText[size];
	}

	private int position(String word) {
		int pos = 0;
		while (pos < wordsNumber && !(word.toUpperCase().hashCode() == words[pos].hashCode())) {
			pos++;
		}
		return pos == wordsNumber ? -1 : pos;
	}

	protected void include(String word) {
		int pos = position(word);
		if (pos >= 0) {
			words[pos].increment();
		} else {
			if (words.length == wordsNumber) {
				words = Arrays.copyOf(words, words.length + INITIAL_SIZE);
			}
			words[wordsNumber] = new WordInText(word);
			wordsNumber++;
		}
	}

	private void includeAll(String lineOfWords, String del) {
		String[] arrOfWords = lineOfWords.split(del);
		for (String word : arrOfWords) {
			if (word != null && word != "") {
				include(word);
			}
		}
	}

	public void includeAll(String[] text, String del) {
		for (String line : text) {
			includeAll(line, del);
		}
	}

	public void includeAllFromFile(String filename, String del) throws FileNotFoundException {
		try (Scanner sc = new Scanner(new File(filename))) {
			readFile(sc, del);
		}
	}

	private void readFile(Scanner sc, String del) {
		while (sc.hasNextLine()) {
			includeAll(sc.nextLine(), del);
		}
	}

	public WordInText find(String word) {
		int pos = position(word);
		if (pos < 0) {
			throw new NoSuchElementException();
		}
		return words[pos];
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "[", "]");
		words = Arrays.copyOf(words, wordsNumber);
		for (WordInText s : words) {
			sj.add(s.toString());
		}
		return String.valueOf(sj);
	}

	public void presentWords(String filename) throws FileNotFoundException {
		try (PrintWriter pw = new PrintWriter(filename)) {
			presentWords(pw);
		}
	}

	public void presentWords(PrintWriter pw) {
		words = Arrays.copyOf(words, wordsNumber);
		for (WordInText s : words) {
			pw.println(s.toString());
		}
	}
}
