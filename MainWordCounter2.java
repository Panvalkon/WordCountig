import java.io.FileNotFoundException;

import prSimpleFilesWordCounting.WordCounter;

public class MainWordCounter2 {

	public static void main(String[] args) {
		WordCounter wc = new WordCounter();
		
		try {
			wc.includeAllFromFile("quijote.txt", "[ .,;:]+");
		} catch (FileNotFoundException e) {
			System.out.println("File is not found");
		}
		
		try {
			wc.presentWords("salida-quijote.txt");
		} catch (FileNotFoundException e) {
			System.out.println("File is not saved");
		}
	}
}
