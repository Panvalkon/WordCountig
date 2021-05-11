import prSimpleFilesWordCounting.WordInText;

public class MainWordInText {

	public static void main(String[] args) {
		WordInText w1 = new WordInText("gorra");
		WordInText w2 = new WordInText("gorra");
		w1.increment();
		System.out.printf("Word 1 = %s\n", w1.toString());
		System.out.printf("Word 2 = %s\n", w2.toString());
		System.out.printf("The words are %s\n", w1.equals(w2) ? "equal" : "not equal");
		

	}

}
