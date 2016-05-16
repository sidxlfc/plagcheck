package algos.gautamdas.sidharth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

//class GFG {
public class LCSSAlgorithm {
	
	public int comparisons;
	public int resultCount;

	public LCSSAlgorithm(String mainFile,String patternFile)
	{
		try {
			comparisons=0;
			resultCount=0;
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

			//System.out.println("\nRunning LCSS");
			long startTimeAnalysis = System.nanoTime();
			for (int i = 0; i < textArray.length; i++) {
				for (int j = 0; j < bookArray.length; j++) {
					int max = findLCSS(textArray[i].split(" "), bookArray[j].split(" "));
					if (max > 5) {
						double Percentage=(max * 100 / textArray[i].split(" ").length);
						if(Percentage>=90){
							resultCount++;
							result +="\nLine: " + textArray[i] + " \nPercentage: "+ Percentage + "%\n";
						}
					}
				}
			}
			long endTimeAnalysis = System.nanoTime();
			double time = (endTimeAnalysis - startTimeAnalysis)/1000000;
			File file = new File("LCSSOutput.txt"); //Your file
			FileOutputStream fos = new FileOutputStream(file);
			PrintStream ps = new PrintStream(fos);
			System.setOut(ps);
			System.out.println("LCSS Output:");
			System.out.println(result);
			System.out.println("\nTotal time to Analyse the file using LCSS Algorithm: \n" + time + "ms");
			System.out.println("No of comparisons: "+comparisons);
			System.out.println("Plaigarized file percentage: "+(resultCount*100/textArray.length)+"%");
			fos.close();
		} catch (Exception e) {
			// Catch exception if any
			System.out.println("Error: " + e);
		}
	}
	
	public static void main (String[] args) {
	}
	
	public int findLCSS(String[] a1,String[] a2){
		try{
		int n=a1.length;
		int m=a2.length;
		int[][] matrix= new int[n+1][m+1];
		int max=0;
		//System.out.println();
		for (int i=0; i<=n; i++)
	    {
	    	//if(i>0)
	    	//	System.out.print(a1[i-1]+"\t");
	        for (int j=0; j<=m; j++)
	        {
	        	comparisons++;
	            if (i == 0 || j == 0){
	                matrix[i][j] = 0;
	            }
	            else if (a1[i-1].equals(a2[j-1])){
	            	matrix[i][j] = matrix[i-1][j-1] + 1;
	            	//System.out.print(matrix[i][j]);
	            }
	            else
	            	matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
	        
	            //if(i>0)
	            //	System.out.print(matrix[i][j] + "\t");
	            //else
	            //	System.out.print("\t"+matrix[i][j]);
	            max=matrix[i][j];
	        }
	        //System.out.println();
	    }
		return max;
		}
		catch(Exception ex){
			System.out.println(ex);
			return 0;
		}
	}
}