import java.io.FileNotFoundException;
import java.io.PrintWriter;

import prSimpleFilesWordCounting.WordCounter;

public class MainWordCounter2 {

	public static void main(String[] args) {
		try {
			WordCounter wc = new WordCounter();
			wc.includeAllFromFile("quijote.txt", "[ .,;:]+");
			wc.presentWords("salida-quijote.txt");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
