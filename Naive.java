/**
 * Ece K. Takmaz 1903673
 */
import java.io.*;
import java.util.*;

public class Naive {

	/**
	 * @param args
	 * @throws IOException 
	 */
	


public static void naiveClassifier(ArrayList<int[]> data,ArrayList<int[]> test, ArrayList<Integer> atts) throws IOException{
	
	double[] priorClassProb = new double[2];
	
	double count1 = 0;
	double count2 = 0;
	int indexOfClassification = (data.get(0).length) - 1;
			
	//find the number of classifications (1 or 2)
	for(int i = 0; i < data.size(); i++){
		
		if(data.get(i)[indexOfClassification] == 1){
			
			count1++;
			
		}else if (data.get(i)[indexOfClassification] == 2){

			count2++;
			
		}
	}
	double sum = count1 + count2;
/*	System.out.println("Count1 " + count1);

	System.out.println("Count2 " + count2);*/
	// for class 1
	priorClassProb[0] = count1 / sum;

	// for class 2
	priorClassProb[1] = count2 / sum;

	//Arraylist of integer arraylists to keep the possible values for each attribute
	ArrayList<ArrayList<Integer>>  values = new ArrayList<ArrayList<Integer>>();
	

	for(int i= 0; i < atts.size(); i++){
					
		ArrayList<Integer> subArr = new ArrayList<Integer>();
		
		values.add(subArr);	
		
	}
	
	for(int i= 0; i < atts.size(); i++){
		
		for(int j = 0; j < data.size(); j++){
					
		int possibleValue = data.get(j)[i];
	
		if(!values.get(i).contains(possibleValue)){
		
			values.get(i).add(possibleValue);
		
		}
		}
	}
	
	/*for(int i = 0; i < values.size(); i++){
		
		System.out.println("Values for " + (i+1) + " ");
		for(int a = 0; a < values.get(i).size(); a++){
			
			System.out.print("" + values.get(i).get(a)+ " ");
			
		}
		System.out.println();
	}
	*/
	
//counts arraylist of double arraylists holds the counts
//for every attributes' every possible value according to their classifications 1 or 2
ArrayList<ArrayList<Double>>  counts = new ArrayList<ArrayList<Double>>();

for(int i = 0; i < values.size(); i++){
	
	ArrayList<Double> subArr = new ArrayList<Double>();
	
	
	for(int v = 0; v < values.get(i).size(); v++){
		
		subArr.add((double) values.get(i).get(v));
	}
	
	counts.add(subArr);	
	
}
	


ArrayList<ArrayList<Double>>  counts2 = new ArrayList<ArrayList<Double>>();

for(int i = 0; i < values.size(); i++){
	
	ArrayList<Double> subArr = new ArrayList<Double>();
	
	
	for(int v = 0; v < values.get(i).size(); v++){
		
		subArr.add((double) values.get(i).get(v));
	}
	
	counts2.add(subArr);	
	
}


//here, setting the proper values for counts and counts2 according to their classifications 1 or 2
	
for(int i = 0; i < values.size(); i++){
		
		//System.out.println("Values for " + (i+1) + " ");
		for(int a = 0; a < values.get(i).size(); a++){
			int count = 0;
			int countTwo = 0;			
			for(int d = 0; d < data.size(); d++){
				int length = data.get(d).length;
				if((data.get(d)[i] == values.get(i).get(a)) && (data.get(d)[length - 1] == 1)){
					count++;
					
				}else if((data.get(d)[i] == values.get(i).get(a)) && (data.get(d)[length - 1] == 2)){
					countTwo++;
					
				}
			
					
			}			
			counts.get(i).set(a, (double)count);
			counts2.get(i).set(a, (double)countTwo);
			
			//System.out.println("count for att " + i +" value "+ values.get(i).get(a)+ " class 1 is "  + count);
			//System.out.print("" + values.get(i).get(a)+ " ");
			
		}
		//System.out.println();
}	
	
	//now we have the counts, we should set the post priori conditional probabilities 
	//such as, P(A1 = 1 | CLASS = 1)

//condprobs holds the probabilities for every attributes' every possible value given the classification is 1
// all P(Ax = vi | CLASS = 1) for attributes 
ArrayList<ArrayList<Double>>  condProbs = new ArrayList<ArrayList<Double>>();

for(int i = 0; i < values.size(); i++){
	
	ArrayList<Double> subArr = new ArrayList<Double>();
	
	
	for(int v = 0; v < values.get(i).size(); v++){
		
		subArr.add((double) values.get(i).get(v));
	}
	
	condProbs.add(subArr);	
	
}

for(int i = 0; i < condProbs.size(); i++){
	
	for(int j = 0; j < condProbs.get(i).size(); j++){
		
		
		condProbs.get(i).set(j, (double) (counts.get(i).get(j) / count1));
		
	}
}


//condprobs2 holds the probabilities for every attributes' every possible value given the classification is 2
//all P(Ax = vi | CLASS = 2) for attributes
ArrayList<ArrayList<Double>>  condProbs2 = new ArrayList<ArrayList<Double>>();


for(int i = 0; i < values.size(); i++){
	
	ArrayList<Double> subArr = new ArrayList<Double>();
	
	
	for(int v = 0; v < values.get(i).size(); v++){
		
		subArr.add((double) values.get(i).get(v));
	}
	
	condProbs2.add(subArr);	
	
}

for(int i = 0; i < condProbs2.size(); i++){
	
	for(int j = 0; j < condProbs2.get(i).size(); j++){
		
		
		condProbs2.get(i).set(j, (double) (counts2.get(i).get(j) / count2));
		
	}
}
// now we have both the priorprobs and condprobs

//this part is for predicting classification of the test data
// and finding the set error 
	
int incorrect = 0;
int correct = 0;

//calculate the posterior probabilities and predict classification

//write the outputs to the setError.txt file
File fout = new File("setError.txt");
FileOutputStream out = new FileOutputStream(fout);
BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

writer.write("Test set error\n");

//calculate probabilities for the test data and predict their classifications

for(int i = 0; i < test.size(); i++){

	double prob1 = priorClassProb[0];
	double prob2 = priorClassProb[1];	
	
	for(int a = 0; a < test.get(i).length; a++){
	
		if( a == test.get(i).length - 1){
			//in a case of equal probabilities, 1 will be the assigned class.
			
			
			int prediction = 0;
			if(prob1 >= prob2){
				
			//System.out.print("Prob 1 greater");

				prediction = 1;
			}else if (prob1 < prob2){
				

			//System.out.print("Prob 2 greater");

				prediction = 2;
			}
			/*System.out.print("Prob 1 " + prob1 + " Prob 2 " + prob2);*/

			writer.write("Class " + test.get(i)[a] + " Prediction " + prediction);
			if(test.get(i)[a] == prediction ) {
				
				correct++;
			}else if (test.get(i)[a] != prediction){
				
				incorrect++;
			}
		}else{
			
		if(values.get(a).contains(test.get(i)[a])){
		
			int ind = values.get(a).indexOf(test.get(i)[a]);
			if((counts.get(a).get(ind) > 0) && (counts2.get(a).get(ind) > 0) ){
				//both of the classifications exist
				/*
			prob1 = prob1 * condProbs.get(a).get(ind);
			prob2 = prob2 * condProbs2.get(a).get(ind); */
				
				
				//NEW PART ***
			prob1 = prob1 * ((counts.get(a).get(ind) + 1 )/ (count1 + values.get(a).size()));
			prob2 = prob2 * ((counts2.get(a).get(ind) + 1 )/ (count2+ values.get(a).size()));
			
			}else if((counts.get(a).get(ind) == 0) && (counts2.get(a).get(ind) > 0) )
			{
				//the value exists in the possible values for attributes arraylist
				//but there is only data classified as 2 for this value of the specific attribute
				//no data with the value classified as 1
				prob1 = prob1 * ( 1 / (values.get(a).size() + count1));
				
				/*
				prob2 = prob2 * condProbs2.get(a).get(ind); */
				
				//NEW PART ***
				prob2 = prob2 * ((counts2.get(a).get(ind) + 1 )/ (count2+ values.get(a).size()));
				
				
			}
			else if((counts.get(a).get(ind) > 0) && (counts2.get(a).get(ind) == 0) ){
				
				//the value exists in the possible value arraylist
				//but there is only data classified as 1 for this value of the specific attribute
				//no data with the value classified as 2
				
				/*
				prob1 = prob1 * condProbs.get(a).get(ind);*/
				
				//NEW PART ***
				prob1 = prob1 * ((counts.get(a).get(ind) + 1 )/ (count1 + values.get(a).size()));
				
				prob2 = prob2 * ( 1 / (values.get(a).size() + count2));
			}
		}else if (!values.get(a).contains(test.get(i)[a])){
			
			//the value does not exist in the possible values for attributes arraylist
			// laplace estimator against zero probs
		
			prob1 = prob1 * ( 1 / (values.get(a).size() + 1  + count1));
			prob2 = prob2 * ( 1 / (values.get(a).size() + 1 + count2));
			
		}
		
		writer.write("" + test.get(i)[a] + " ");
		}
	}
	writer.write("\n");
}

writer.write("Incorrect classifications: " + incorrect + "\n");
writer.write("Correct classifications: " + correct + "\n");
writer.write("Set error: " + (double)incorrect / (double)(incorrect + correct) + "\n");
writer.write("\n");


//this part is for predicting classification of the training data
// and finding the set error 


//calculate probabilities for the original training data and predict their classifications

//write the outputs to seterror.txt

writer.write("Training set error\n");
incorrect = 0;
correct = 0;
for(int i = 0; i < data.size(); i++){

	double prob1 = priorClassProb[0];
	double prob2 = priorClassProb[1];	
	
	for(int a = 0; a < data.get(i).length; a++){
	
		if( a == data.get(i).length - 1){
			//in a case of equal probabilities, 1 will be the assigned class.
			
			//System.out.print("Prob 1 " + prob1 + " Prob 2 " + prob2);

			int prediction = 0;
			if(prob1 >= prob2){
				
			//System.out.print("Prob 1 greater");

				prediction = 1;
			}else if (prob1 < prob2){
				

			//System.out.print("Prob 2 greater");

				prediction = 2;
			}
				/*

			System.out.print("Prob 1 " + prob1 + " Prob 2 " + prob2);*/
			writer.write("Class " + data.get(i)[a] + " Prediction " + prediction);
			if(data.get(i)[a] == prediction ) {
				
				correct++;
			}else if (data.get(i)[a] != prediction){
				
				incorrect++;
			}
		}else{

			if(values.get(a).contains(data.get(i)[a])){
			
				int ind = values.get(a).indexOf(data.get(i)[a]);
				if((counts.get(a).get(ind) > 0) && (counts2.get(a).get(ind) > 0) ){
					//both of the classifications exist
					/*
				prob1 = prob1 * condProbs.get(a).get(ind);
				prob2 = prob2 * condProbs2.get(a).get(ind); */
					
					
					//NEW PART ***
				prob1 = prob1 * ((counts.get(a).get(ind) + 1 )/ (count1 + values.get(a).size()));
				prob2 = prob2 * ((counts2.get(a).get(ind) + 1 )/ (count2+ values.get(a).size()));
				
				}else if((counts.get(a).get(ind) == 0) && (counts2.get(a).get(ind) > 0) )
				{
					//the value exists in the possible values for attributes arraylist
					//but there is only data classified as 2 for this value of the specific attribute
					//no data with the value classified as 1
					prob1 = prob1 * ( 1 / (values.get(a).size() + count1));
					
					/*
					prob2 = prob2 * condProbs2.get(a).get(ind); */
					
					//NEW PART ***
					prob2 = prob2 * ((counts2.get(a).get(ind) + 1 )/ (count2+ values.get(a).size()));
					
					
				}
				else if((counts.get(a).get(ind) > 0) && (counts2.get(a).get(ind) == 0) ){
					
					//the value exists in the possible value arraylist
					//but there is only data classified as 1 for this value of the specific attribute
					//no data with the value classified as 2
					
					/*
					prob1 = prob1 * condProbs.get(a).get(ind);*/
					
					//NEW PART ***
					prob1 = prob1 * ((counts.get(a).get(ind) + 1 )/ (count1 + values.get(a).size()));
					
					prob2 = prob2 * ( 1 / (values.get(a).size() + count2));
				}
			}else if (!values.get(a).contains(data.get(i)[a])){
				
				//the value does not exist in the possible values for attributes arraylist
				// laplace estimator against zero probs
			
				prob1 = prob1 * ( 1 / (values.get(a).size() + 1  + count1));
				prob2 = prob2 * ( 1 / (values.get(a).size() + 1 + count2));
				
			}
			
		
			writer.write("" + data.get(i)[a] + " ");
		}
	}
	writer.write("\n");
}

writer.write("Incorrect classifications: " + incorrect + "\n");
writer.write("Correct classifications: " + correct + "\n");
writer.write("Set error: " + (double)incorrect / (double)(incorrect + correct) + "\n");


writer.close();
}

	public static void main(String[] args) throws IOException {
		
		//arraylist of integer arrays, to keep the data set
		 ArrayList<int[]> lines = new ArrayList<int[]>();
		 
		 //open and start reading the training data set
		 File f = new File("loan-train.dat");
         FileInputStream in = new FileInputStream(f);
         BufferedReader reader = new BufferedReader(new InputStreamReader(in));
         
        
         String line = ""; // first line, defines the att. names such as A1, A2...
         								  // not necessary for computations
         
          int attCount = 0;
         
         // read one line
         while ((line = reader.readLine()) != null ) {
        	 
        	 //separate the line into tokens, numeric values that the attributes have
        	 StringTokenizer st = new StringTokenizer(line, ", ");
        	 	 
        	 attCount = st.countTokens();
        	 
        	 int [] attArray = new int[attCount];

        	 for(int i = 0; i < attCount; i++){
        		 
        		 int value = Integer.parseInt(st.nextToken());
        		 
        		 attArray[i] = value;
        		 
        	 }
        	 
        	 lines.add(attArray);
         	
         }
         
         // attributes arraylist to keep the unique attributes
         ArrayList<Integer> attributes = new ArrayList<Integer>();
         
         for(int a = 0; a < attCount - 1 ; a++){
        	 
        	 attributes.add(a+1);
         }
         
         
         // call the naiveLearner method with the initial data set and with all the attributes
         //read the test data
         
       //arraylist of integer arrays, to keep the data set
		 ArrayList<int[]> linestest = new ArrayList<int[]>();
		 
		 //open and start reading the training data set
		 File ftest = new File("loan-test.dat");
         FileInputStream intest = new FileInputStream(ftest);
         BufferedReader readertest = new BufferedReader(new InputStreamReader(intest));
         
        
         String linetest = ""; // first line, defines the att. names such as A1, A2...
         								  // not necessary for computations
         
          int attCountTest = 0;
         
         // read one line
         while ((linetest = readertest.readLine()) != null ) {
        	 
        	 //separate the line into tokens, numeric values that the attributes have
        	 StringTokenizer sttest = new StringTokenizer(linetest, ", ");
        	 	 
        	 attCountTest = sttest.countTokens();
        	 
        	 int [] attArrayt = new int[attCountTest];

        	 for(int i = 0; i < attCountTest; i++){
        		 
        		 int value = Integer.parseInt(sttest.nextToken());
        		 
        		 attArrayt[i] = value;
        		 
        	 }
        	 
        	 linestest.add(attArrayt);
         	
         }
         
         
         // call the classifier method with training and test data along with possible attributes
         naiveClassifier(lines, linestest, attributes);
          	 
                 
	
	}

}
