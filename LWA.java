/**
 * Ece K. Takmaz 1903673
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class LWA implements ActionListener {

	private static JFrame window;
	private static JButton ruleText = new JButton("Rule-based from text");
	private static JButton naiveText = new JButton("Naive-bayes from text");
	private static JButton ruleWord = new JButton("Rule-based from input word");
	private static JLabel feedback = new JLabel("");
	private static JTextField inputWord = new JTextField(10);	
	private static JLabel outputWord = new JLabel("");
	
	public static void ruleBasedConverter(ArrayList<String> wordlist) throws IOException{
		
		File fout = new File("ruleconverted.txt");
		FileOutputStream out = new FileOutputStream(fout);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

		//add the rules here
	for(int i = 0; i<wordlist.size(); i++){
			
		String word = wordlist.get(i);
			int lengthOfLetter = wordlist.get(i).length();
			
			if(sertSessiz(word.substring(0,1)) 
					&& sertSessiz(word.substring(1,2)) 
					&& kalinSesli (word.substring(2,3)) ){
				
				
				word = "" + word.substring(0,1) + "ý" + word.substring(1,word.length()) + "\n";
			}
			if(sertSessiz(word.substring(0,1)) 
					&& sertSessiz(word.substring(1,2)) 
					&& inceSesli (word.substring(2,3)) ){
				
				
				word = "" + word.substring(0,1) + "i" + word.substring(1,word.length()) + "\n";
			}
			
			if(sertSessiz(word.substring(0,1)) 
					&& sertSessiz(word.substring(1,2)) 
					&& yumusakSessiz2 (word.substring(2,3)) ){
				
				
				word = "" + word.substring(0,1) + "ý" + word.substring(1,word.length()) + "\n";
			}
			
			if(sertSessiz(word.substring(0,1)) 
					&& yumusakSessiz2 (word.substring(1,2)) 
					&& inceSesli (word.substring(2,3)) ){
				
				word = "" + word.substring(0,1) + "i" + word.substring(1,word.length()) + "\n";
			}
			
			if(sertSessiz(word.substring(0,1)) 
					&& yumusakSessiz2 (word.substring(1,2)) 
					&& kalinSesli (word.substring(2,3)) ){
				
				word = "" + word.substring(0,1) + "ý" + word.substring(1,word.length()) + "\n";
			}
			
			
			if(sertSessiz(word.substring(0,1)) 
					&& sertSessiz(word.substring(1,2)) 
					&& yumusakSessiz1 (word.substring(2,3)) ){
				
				
				word = "" + word.substring(0,1) + "ý" + word.substring(1,word.length()) + "\n";
			}


			if(yumusakSessiz1(word.substring(word.length()-2,word.length()-1))){ 

				String letterEnd = word.substring(word.length()-2,word.length()-1);
				
				if(letterEnd.equals("b")){
					word = "" + word.substring(0,word.length()-2) + "p" + "\n";
			}else
				if(letterEnd.equals("c")){
					word = "" + word.substring(0,word.length()-2) + "ç" + "\n";
				}
				else
					if(letterEnd.equals("d")){
						word = "" + word.substring(0,word.length()-2) + "t" + "\n";
					}else 
					if(letterEnd.equals("g")){
						word = "" + word.substring(0,word.length()-2) + "k" + "\n";
					}
			}
			
			if(word.contains("tion")){
			
				word = "" + word.replace("tion", "syon");
				
			}
		if(yumusakSessiz2(word.substring(word.length()-1,word.length())) 
					&& sertSessiz(word.substring(word.length()-2,word.length()-1)) 
					&& inceSesli (word.substring(word.length()-3,word.length()-2)) ){
				
				word = "" + word.substring(0,word.length()-1) + "i" + word.substring(word.length()-1,word.length()) + "\n";
			}
		if(yumusakSessiz2(word.substring(word.length()-1,word.length())) 
				&& sertSessiz(word.substring(word.length()-2,word.length()-1)) 
				&& kalinSesli (word.substring(word.length()-3,word.length()-2)) ){
			
			word = "" + word.substring(0,word.length()-1) + "ý" + word.substring(word.length()-1,word.length()) + "\n";
		}
		
			
			
			writer.write(word);
		}
				
		writer.close();
	}
	
	public static String ruleBasedConverterWord(String word) {
		
		if(sertSessiz(word.substring(0,1)) 
					&& sertSessiz(word.substring(1,2)) 
					&& kalinSesli (word.substring(2,3)) ){
				
				
				word = "" + word.substring(0,1) + "ý" + word.substring(1,word.length()) + "\n";
			}
			if(sertSessiz(word.substring(0,1)) 
					&& sertSessiz(word.substring(1,2)) 
					&& inceSesli (word.substring(2,3)) ){
				
				
				word = "" + word.substring(0,1) + "i" + word.substring(1,word.length()) + "\n";
			}
			
			if(sertSessiz(word.substring(0,1)) 
					&& sertSessiz(word.substring(1,2)) 
					&& yumusakSessiz2 (word.substring(2,3)) ){
				
				
				word = "" + word.substring(0,1) + "ý" + word.substring(1,word.length()) + "\n";
			}
			
			if(sertSessiz(word.substring(0,1)) 
					&& yumusakSessiz2 (word.substring(1,2)) 
					&& inceSesli (word.substring(2,3)) ){
				
				word = "" + word.substring(0,1) + "i" + word.substring(1,word.length()) + "\n";
			}
			
			if(sertSessiz(word.substring(0,1)) 
					&& yumusakSessiz2 (word.substring(1,2)) 
					&& kalinSesli (word.substring(2,3)) ){
				
				word = "" + word.substring(0,1) + "ý" + word.substring(1,word.length()) + "\n";
			}
			
			
			if(sertSessiz(word.substring(0,1)) 
					&& sertSessiz(word.substring(1,2)) 
					&& yumusakSessiz1 (word.substring(2,3)) ){
				
				
				word = "" + word.substring(0,1) + "ý" + word.substring(1,word.length()) + "\n";
			}


			if(yumusakSessiz1(word.substring(word.length()-2,word.length()-1))){ 

				String letterEnd = word.substring(word.length()-2,word.length()-1);
				
				if(letterEnd.equals("b")){
					word = "" + word.substring(0,word.length()-2) + "p" + "\n";
			}else
				if(letterEnd.equals("c")){
					word = "" + word.substring(0,word.length()-2) + "ç" + "\n";
				}
				else
					if(letterEnd.equals("d")){
						word = "" + word.substring(0,word.length()-2) + "t" + "\n";
					}else 
					if(letterEnd.equals("g")){
						word = "" + word.substring(0,word.length()-2) + "k" + "\n";
					}
			}
			
			if(word.contains("tion")){
			
				word = "" + word.replace("tion", "syon");
				
			}
		if(yumusakSessiz2(word.substring(word.length()-1,word.length())) 
					&& sertSessiz(word.substring(word.length()-2,word.length()-1)) 
					&& inceSesli (word.substring(word.length()-3,word.length()-2)) ){
				
				word = "" + word.substring(0,word.length()-1) + "i" + word.substring(word.length()-1,word.length()) + "\n";
			}
		if(yumusakSessiz2(word.substring(word.length()-1,word.length())) 
				&& sertSessiz(word.substring(word.length()-2,word.length()-1)) 
				&& kalinSesli (word.substring(word.length()-3,word.length()-2)) ){
			
			word = "" + word.substring(0,word.length()-1) + "ý" + word.substring(word.length()-1,word.length()) + "\n";
		}
		
			
		return word;
	}

	public static boolean kalinSesli(String letter){
		
		boolean type = false; 
		
		if(letter.equals("a") ||  letter.equals("o")|| letter.equals("u")|| letter.equals("ý")){
			
			type = true;
			
		}
		return type;
	}
	public static boolean inceSesli(String letter){

		boolean type = false; 
		
		if(letter.equals("e") || letter.equals("i")|| letter.equals("ö")|| letter.equals("ü")){
			
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
	
    public static void main(String[] args){
		// TODO Auto-generated method stub
	
		JFrame.setDefaultLookAndFeelDecorated(true);
		window = new JFrame("Loanword Adaptation");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(300,300);
		window.setLayout(new FlowLayout());
		Container con = window.getContentPane();
		
		ruleText.addActionListener(new LWA());
		naiveText.addActionListener(new LWA());
		ruleWord.addActionListener(new LWA());
		
		con.add(ruleText);
		con.add(naiveText);
		con.add(ruleWord);

		con.add(inputWord);
		
		outputWord.setVisible(false);
		con.add(outputWord);
		
		con.add(feedback);
		
		window.setVisible(true);
				

	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource().equals(ruleText)){
			//arraylist of strings to keep the words
			ArrayList<String> words = new ArrayList<String>();
			 
			//open and start reading the data set
			File f = new File("loanwords.dat");
	        FileInputStream in;
			try {
				in = new FileInputStream(f);
			
	        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	        
	       String line = "";
	       
	       // read all words
	        while ((line = reader.readLine()) != null ) {
	       	 
	       	     	 words.add(line);
	        	
	        }
	        
	        for(int i=0; i<words.size(); i++){
	        System.out.println(words.get(i));
	        }
	            	 
	               
	        ruleBasedConverter(words);
	        reader.close();
	        feedback.setText("Rule-based adaptation from text is done");
	        
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource().equals(naiveText)){
			
		}else if(e.getSource().equals(ruleWord)){
			
			String word = inputWord.getText();

			if(!word.equals("")){
			String output = ruleBasedConverterWord(word);
			
			outputWord.setText(output);
			outputWord.setVisible(true);
			
			}
			
		}
	}

}
