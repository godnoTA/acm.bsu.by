import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main{
public static void main(String[]args){
	File f = new File("input.txt");
	long n;
	try{
		Scanner sc = new Scanner(f);
		n = sc.nextLong();
		PrintWriter q = new PrintWriter(new File("output.txt"));
		StringBuilder sb = new StringBuilder();
		 
		String y="";
	     for (;n>0;){
	    	if (n%2!=0){
	    	    y+="1";
	    	}
	    	else {
	    		y+="0";
	    	}
	        n=n/2;      
	     }	   
	     StringBuffer x=(new StringBuffer(y)).reverse();
	     for(int i = x.length()-1; i>=0;--i)
			{
				if(x.charAt(i)=='1')
					q.println(x.length()-i-1);
			}
	     q.flush();
    }
	catch(Exception e) {
		System.err.println(e.getMessage());
		}
}
}