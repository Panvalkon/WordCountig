/**
 * 
 */
package prSimpleFilesWordCounting;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Illya Roz
 *
 */
public class WordCounterSig extends WordCounter {
	private static final int INITIAL_SIZE_NSW = 10;
	private int nonSigWordsNum;
	private String[] nonSignificant;

	public WordCounterSig(int size, String[] nonSig) {
		super(size);
		this.nonSignificant = toUpperCase(nonSig);
		this.nonSigWordsNum = nonSig.length;
	}

	public WordCounterSig(String[] nonSig) {
		super();
		this.nonSignificant = toUpperCase(nonSig);
		this.nonSigWordsNum = nonSig.length;
	}

	public WordCounterSig(String filNoSig, String del) throws IOException {
		super();
		this.nonSignificant = new String[INITIAL_SIZE_NSW];
		readNonSigFile(filNoSig, del);
	}

	public WordCounterSig(int n, String filNoSig, String del) throws IOException {
		super();
		this.nonSignificant = new String[n];
		readNonSigFile(filNoSig, del);
	}

	private void readNonSigFile(String filNoSig, String del) throws IOException {
		try (Scanner sc = new Scanner(new File(filNoSig))) {
			readNonSigWords(sc, del);
		}
	}

	@Override
	protected void include(String word) {
		int pos = findNoSid(word);
		if (pos < 0) {
			super.include(word);
		}
	}

	private void readNonSigWords(Scanner sc, String del) throws IOException {
		this.nonSigWordsNum = 0;
		while (sc.hasNextLine()) {
			try (Scanner sc2 = new Scanner(sc.nextLine())){
				sc2.useDelimiter(del);
				while(sc2.hasNext()) {
					if (this.nonSignificant.length == nonSigWordsNum) {
						nonSignificant = Arrays.copyOf(nonSignificant, nonSignificant.length * 2);
					}
					this.nonSignificant[nonSigWordsNum] = sc2.next().toUpperCase();
					nonSigWordsNum++;
				}
			}
		}
		nonSignificant = Arrays.copyOf(nonSignificant, nonSigWordsNum);
	}

	private int findNoSid(String word) {
		int pos = 0;
		while (pos < nonSignificant.length && !nonSignificant[pos].equalsIgnoreCase(word)) {
			pos++;
		}
		return pos == nonSignificant.length ? -1 : pos;
	}

	private void includeNoSig(String nonSigWord) {
		this.nonSignificant[nonSigWordsNum++] = nonSigWord;
	}

	private String[] toUpperCase(String[] nonSig) {
		for (int i = 0; i < nonSig.length; i++) {
			nonSig[i] = nonSig[i].toUpperCase();
		}
		return nonSig;
	}
}
