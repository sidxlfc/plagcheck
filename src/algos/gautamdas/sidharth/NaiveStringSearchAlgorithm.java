package algos.gautamdas.sidharth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NaiveStringSearchAlgorithm {


	public int comparisons;
	
	public NaiveStringSearchAlgorithm(String mainFile,String patternFile)
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
			//System.out.println("\nRunning Naive");
			long startTimeAnalysis = System.nanoTime();
			for (int i = 0; i < textArray.length; i++) {
				for (int j = 0; j < bookArray.length; j++) {
					result += returnStringOccurences(bookArray[j], textArray[i], j, mainFile);
				}
			}
			long endTimeAnalysis = System.nanoTime();
			double time = (endTimeAnalysis - startTimeAnalysis)/1000000;
			
			File file = new File("NaiveOutput.txt"); //Your file
			FileOutputStream fos = new FileOutputStream(file);
			PrintStream ps = new PrintStream(fos);
			System.setOut(ps);
			System.out.println("Naive Output:");
			System.out.println(result);
			System.out.println("\nTotal time to Analyse the file using Naive Algorithm: \n" + time + "ms");
			System.out.println("No of comparisons: "+comparisons);
			
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String args[])
	{
	}
	
	public String returnStringOccurences(String s,String pattern,int lineno,String fileName){
		String result="";
		for(int i=0;i<s.length()-pattern.length()+1;i++)//outer string
		{
			boolean match=false;
			for(int j=0;j<pattern.length();j++)//pattern
			{
	        	comparisons++;
				if(s.charAt(i+j)!=pattern.charAt(j)) {
					match=false;
				}
				else
					match=true;
			}
			if(match) {	
				result="\nPattern: "+ pattern+"\nIn file: "+fileName+"\nFound at index " + (i+1)+ " at line no. "+lineno+"\n";
			}
		}
		return result;	
	}
	
}
