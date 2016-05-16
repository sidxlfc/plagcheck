package algos.gautamdas.sidharth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KMPAlgorithm {

	public int comparisons;

	public KMPAlgorithm(String mainFile, String patternFile) {
		try {
			comparisons = 0;
			String result = "";
			String text = "";
			String book = "";
			for (String line : Files.readAllLines(Paths.get(mainFile))) {
				line.trim();
				book += line;
			}
			for (String line : Files.readAllLines(Paths.get(patternFile))) {
				line.trim();
				text += line;
			}

			String textArray[] = text.split("\\.");
			String bookArray[] = book.split("\\.");

			//System.out.println("\nRunning KMP");
			long startTimeAnalysis = System.nanoTime();
			for (int i = 0; i < textArray.length; i++) {
				for (int j = 0; j < bookArray.length; j++) {
					result += findPatternOccurences(bookArray[j], textArray[i], j, mainFile);
				}
			}
			long endTimeAnalysis = System.nanoTime();
			double time = (endTimeAnalysis - startTimeAnalysis)/1000000;

			File file = new File("KMPOutput.txt"); // Your file
			FileOutputStream fos = new FileOutputStream(file);
			PrintStream ps = new PrintStream(fos);
			System.setOut(ps);
			System.out.println("KMP Output:");
			System.out.println(result);
			System.out.println("\nTotal time to Analyse the file using KMP Algorithm: \n" + time + "ms");
			System.out.println("No of comparisons: " + comparisons);
			fos.close();

		} catch (Exception e) {
			// Catch exception if any
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

	}

	public String findPatternOccurences(String s1, String pattern, int lineno, String fileName) {
		String result = "";
		int n = s1.length();
		int m = pattern.length();
		int lps[] = new int[m];
		lps = computeLPSArray(pattern, m);
		int j = 0;
		int i = 0;
		while (i < n) {
			if (pattern.charAt(j) == s1.charAt(i)) {
				comparisons++;
				i++;
				j++;
			}
			if (j == m) {
				result = "\nPattern: " + pattern + "\nIn file: " + fileName + "\nFound at index " + (i - j)
						+ " at line no. " + lineno + "\n";
				j = lps[j - 1];
			} else if (i < n && pattern.charAt(j) != s1.charAt(i)) {
				comparisons++;
				if (j != 0)
					j = lps[j - 1];
				else
					i++;
			}
		}
		return result;
	}

	public static int[] computeLPSArray(String pattern, int length) {
		int lps[] = new int[length];

		int len = 0;
		lps[0] = 0;
		int i = 1;
		while (i < length) {
			if (pattern.charAt(i) == pattern.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = lps[len - 1];
				} else {
					lps[i] = 0;
					i++;
				}
			}
		}
		return lps;

	}
}
