/**
 * Ece K. Takmaz 1903673
 */

import java.io.*;
import java.util.*;

public class Converter {

	public static void featureConverter(ArrayList<String> wordlist) throws IOException{
		
		File fout = new File("convertedFeatures.txt");
		FileOutputStream out = new FileOutputStream(fout);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

		//conversion
		
		for(int i = 0; i<wordlist.size(); i++){
			
			int lengthOfLetter = wordlist.get(i).length();
			
			for(int a = 0; a < lengthOfLetter; a++){
				
				System.out.println("" + wordlist.get(i).substring(a,a+1));

				if(kalinSesli(wordlist.get(i).substring(a,a+1))){
					
					writer.write("1, ");
				}else if(inceSesli(wordlist.get(i).substring(a,a+1))){
					
					writer.write("2, ");
				}else if(sertSessiz(wordlist.get(i).substring(a,a+1))){
					
					writer.write("3, ");
				}else if(yumusakSessiz1(wordlist.get(i).substring(a,a+1))){
					
					writer.write("4, ");
				}else if(yumusakSessiz2(wordlist.get(i).substring(a,a+1))){
					
					writer.write("5, ");
				}
				
			}
			
			writer.write("\n");
			
			
		}
				
		writer.close();
	}
	
	public static boolean kalinSesli(String letter){
		
		boolean type = false; 
		
		if(letter.equals("a") ||  letter.equals("o")|| letter.equals("u")){
			
			type = true;
			
		}
		return type;
	}
	public static boolean inceSesli(String letter){

		boolean type = false; 
		
		if(letter.equals("e") || letter.equals("i")){
			
			type = true;
			
		}
		return type;
	}
	public static boolean sertSessiz(String letter){

		boolean type = false; 
		
		if(letter.equals("f") || letter.equals("s") || letter.equals("t")|| letter.equals("k")|| letter.equals("h")|| letter.equals("p")){
			
			type = true;
			
		}
		return type;
	}
	public static boolean yumusakSessiz1(String letter){

		boolean type = false; 
		
		if(letter.equals("b") || letter.equals("c") || letter.equals("d")|| letter.equals("g")){
			
			type = true;
			
		}
		return type;
	}
	
	public static boolean yumusakSessiz2(String letter){

		boolean type = false; 
		
		if(letter.equals("j") || letter.equals("l") || letter.equals("m")|| letter.equals("n")
			 || letter.equals("r") || letter.equals("v") || letter.equals("y")|| letter.equals("z")){
			
			type = true;
			
		}
		return type;
	}
	public static void main(String[] args) throws IOException {

		//arraylist of strings to keep the words
		ArrayList<String> words = new ArrayList<String>();
		 
		//open and start reading the data set
		File f = new File("loanwordsconv.dat");
        FileInputStream in = new FileInputStream(f);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        
       String line = "";

       // read all words
        while ((line = reader.readLine()) != null ) {
       	 
       	     	 words.add(line);
        	
        }
        
               
        featureConverter(words);
        reader.close();
	}

}
