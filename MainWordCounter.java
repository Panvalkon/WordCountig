import prSimpleFilesWordCounting.WordCounter;

public class MainWordCounter {

	public static void main(String[] args) {
		String [] data = {
				"Esta es la primera frase del ejemplo",
				"y esta es la segunda frase"
				};
		WordCounter wc = new WordCounter(5);
		wc.includeAll(data, "[ ]");
		System.out.println(wc.toString());
	}

}
