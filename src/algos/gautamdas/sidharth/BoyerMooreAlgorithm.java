package algos.gautamdas.sidharth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class BoyerMooreAlgorithm {

	public int comparisons;
	
	public BoyerMooreAlgorithm(String mainFile,String patternFile)
	{
		try {
			comparisons=0;
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

			//System.out.println("\nRunning BoyerMoores");
			long startTimeAnalysis = System.nanoTime();
			for (int i = 0; i < textArray.length; i++) {
				for (int j = 0; j < bookArray.length; j++) {
					result += BoyerMooreSearching(bookArray[j], textArray[i], j, mainFile);
				}
			}
			long endTimeAnalysis = System.nanoTime();
			double time = (endTimeAnalysis - startTimeAnalysis)/1000000;
			
			
			File file = new File("BMOutput.txt"); //Your file
			FileOutputStream fos = new FileOutputStream(file);
			PrintStream ps = new PrintStream(fos);
			System.setOut(ps);
			System.out.println("Boyer Moore Output:");
			System.out.println(result);
			System.out.println("\nTotal time to Analyse the file using Boyer Moore Algorithm: \n" + time + "ms");
			System.out.println("No of comparisons: "+comparisons);
			fos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	}
	
	public String BoyerMooreSearching(String text, String pattern, int lineno, String fileName) {
		int  patternlen, textlen;
		patternlen = pattern.length();
		textlen = text.length();
		String result="";
		int badChar[] = new int[patternlen];
		int goodSuffix[] = new int[patternlen];
		
		Map<Character, Integer> badCharacterShifts = preprocess_bc(pattern,patternlen);
		
		int j=0;
		while ( (j+(patternlen-1)) < textlen) {
            for (int i = patternlen - 1; i >= 0; i--) {
                int k = j + i;
                
                if (k >= textlen) {
                    break;
                }
                if (text.charAt(k) != pattern.charAt(i)) {
                	comparisons++;
                    Integer r = badCharacterShifts.get(text.charAt(k));
                    if (r == null) {
                        j = k + 1;
                    } else {
                        int shift = k - (j + r);
                        j += shift > 0 ? shift : 1;
                    }
                    break;
                } else if (i == 0) {
                	result="\n\nPattern: "+ pattern+"\nIn file: "+fileName+"\nFound at index " + j + " at line no. "+lineno;
                    j++;
                }
            }
        }
		return result;
	}
	
	public  Map<Character, Integer> preprocess_bc(String pattern, int patternlen) {
		Map<Character, Integer> badCharPreProcessArr = new HashMap<Character, Integer>();
        for (int i = pattern.length() - 1; i >= 0; i--) {
            char tmp = pattern.charAt(i);
            if (!badCharPreProcessArr.containsKey(tmp)) {
            	badCharPreProcessArr.put(tmp, i);
            }
        }
        return badCharPreProcessArr;
	}
}
