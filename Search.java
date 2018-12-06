//Victoria Severin
//1539768
//January 28, 2018
//Search.java
//Implements the binary Search and Merge Sort algorithms
//The methods are adapted to operate on String arrays rather than int arrays.
//Program will determine whether or not the target word is amongst the words in the input file, print a message to stfout stating whether or not the target was found. 

import java.io.*;
import java.util.Scanner; 

public class Search{
	public static void main(String[] args) throws IOException{ 
	// operate on String arrays rather than int arrays
	// altering the comparison of array elements	
	Scanner file = null;
	//PrintWriter out = null;
	String line = null;
	String[] token = null;
	int nLines = 0; int[] lineNumber = null;
	
	//check number of command line arguments is at least 2
	if(args.length <2){
		System.out.println("Usage: Search file target 1 [target 2 ..]");
		System.exit(1);
	}
	//open files
	//counts the number of lines in the file
	//nLines increases depending on the number of lines in the file
	file = new Scanner(new File(args[0]));
	while(file.hasNextLine()){
		nLines++;
		line = file.nextLine();
	} 
	
	//length of String
	//line number array
	token = new String[nLines];
	lineNumber = new int [nLines];
	file = new Scanner(new File(args[0]));

	for(int i =1; i<=lineNumber.length; i++){
		lineNumber[i-1] = i;
	}
	
	//word in String array
	for (int i = 0; file.hasNextLine(); i++){
		line = file.nextLine();
		token[i] = line;
	}
	
	//mergeSort given
	//the string array gets put in order through the mergeSort method below
	mergeSort(token, lineNumber, 0, token.length-1);
	
	//prints the target word and the number line it is on
	//through the binary method below 
	for(int i=1; i<args.length; i++){
		System.out.println(binary(token, lineNumber, 0, token.length-1, args[i]));
	}
	//close the file
	file.close();	
}
	
	/*the binary
	returns the original line number 
	-1 if the word is not found
	otherwise returns the new index
	s1.compareTo(s2) ==0 if and only if the Strings are identical 
	and s1.compareTo(s2)>0 if and only if s1>s2 in the same ordering, vice versa
	*/
	
	public static String binary(String[] word, int [] lineNumber, int p, int r, String tWord){
		int q; 
		if (p == r){
		return tWord + " not found";
		}
		else{
		q = (p+r)/2; //finds middle of the array
		if(word[q].compareTo(tWord)== 0){
			return tWord + " found on line " + lineNumber[q];
		}
		else if (word[q].compareTo(tWord)<0) {
			return binary(word, lineNumber, p, q, tWord);
		}
		else {
			return binary(word, lineNumber, q+1, r, tWord);
		}
		}
	}
	
	/* mergeSort: example given from the class page
	sorts the word array
	pass in an int array that keeps track of the line number for each of the
	words in the array being sorted
	*/
	static void mergeSort(String[] word, int[] lineNumber, int p, int r){
		int q;
		if (p<r){
		q = (p+r)/2;
		//System.out.println(p+ " "+q+" "+r);
		//merge sort but with word, lineNumber, p, and r
		mergeSort(word, lineNumber, p, q);
		mergeSort(word, lineNumber, q+1, r);
		merge(word, lineNumber, p, q, r);
		}
	}
	
	/*mergeSort passes the problem to merge where the real work is done
	sorts the array
	takes two arrays from mergeSort and changes the order of the array by
	putting them in order
	*/
	
	static void merge(String[] word, int[] lineNumber, int p, int q, int r){
	int n1= q-p+1;
	int n2 = r-q;
	int [] L = new int[n1];
	int [] R = new int [n2];
	String[] stringLeft = new String[n1];
	String [] stringRight = new String [n2];
	int i, j, k;
	
	for (i=0; i<n1; i++){
		stringLeft[i] = word [p+i];
		L[i] = lineNumber [p+i];
	}
	for (j=0; j<n2; j++) {
	stringRight[j] = word[q+j+1];
	R[j] = lineNumber[q+j+1];
	}
	i = 0; j=0;
	for (k=p; k<=r; k++) {
		if(i<n1 && j<n2){
			if(stringLeft[i].compareTo(stringRight[j])> 0) {
			word[k] = stringLeft[i];
			lineNumber[k] = L[i];
			i++;
			} 
			else {
			word[k] = stringRight[j];
			lineNumber[k] = R[j];
			j++;
			}
		}
	else if (i<n1) {
	word[k] = stringLeft[i];
	lineNumber[k] = L[i];
	i++;
	} else {// j<n2
		word[k] = stringRight[j];
		lineNumber[k] = R[j];
		j++;
	}		
	}
	}
	
}


