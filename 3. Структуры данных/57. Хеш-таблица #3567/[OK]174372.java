import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Test {
	/*
	public static int number_of_elements;  
	public static int size;
	public static int constanta;
	public static int count = 0;
	
	public static ArrayList<Integer> hashTable;
	*/
	public static int count = 0;
	public static int constanta;
	
	 public static int h(int x, int size, int constanta) { 
			return ((x%size) + constanta*(count))%size ;   
		}
	
	 public static void add(int key, int size, ArrayList<Integer> hashTable) { 
			    for (int j = 0; j < size; j++) {   
			    	int x = h(key, size, constanta);
					 if ((hashTable.get(x) == -1)||(hashTable.get(x) == key)){
					      hashTable.add(x,key);
					      hashTable.remove(x+1);
					      return;
					 }
					 else{
						 count++;
					 }
			    }
	 } 
	

	 
	public static void main(String[] args) throws IOException {
		 
		
		int number_of_elements;  
		int size;

		
		ArrayList<Integer> myHashTable;
		
		 Scanner fin = new Scanner(new File("input.txt"));
		 size = fin.nextInt();
		 constanta = fin.nextInt();
		 number_of_elements = fin.nextInt();
		 
		 myHashTable = new ArrayList<>(size);

		 for(int i = 0; i < size; i++){
		        myHashTable.add(i,-1);
		 }
		 
		 for(int i = 0; i < number_of_elements; i++){
			 count = 0; 
			 int k = fin.nextInt() ;
			 add(k, size, myHashTable);
		 }
	    fin.close();
	    
	    
	  try{
		  FileWriter fout;
		  fout = new FileWriter("output.txt");
		  for (int i = 0; i < size; i++){
			  fout.write(myHashTable.get(i)+" ");
		  }
		  fout.close();
	  }catch (Exception e) {}
	 }
}
