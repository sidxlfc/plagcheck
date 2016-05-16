package algos.gautamdas.sidharth;

public class Main {
	public static void main(String args[]) {
		try {
			String mainFile = "Input/BookClean2300.txt";
			String patternFile = "Input/test.txt";
			System.out.println("All algorithms running. Please check output files.");
			//LCSSAlgorithm lcss = new LCSSAlgorithm(mainFile, patternFile);
			LCSSCharacter lcss1=new LCSSCharacter(mainFile, patternFile);
			KMPAlgorithm kmp = new KMPAlgorithm(mainFile, patternFile);
			NaiveStringSearchAlgorithm naive = new NaiveStringSearchAlgorithm(mainFile, patternFile);
			BoyerMooreAlgorithm boyermoore = new BoyerMooreAlgorithm(mainFile, patternFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
