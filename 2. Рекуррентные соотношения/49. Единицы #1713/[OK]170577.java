import java.io.*;
import java.util.*;

public class test {

	static BufferedWriter writer;
	public static void main(String[] args) throws IOException {
		Scanner in=new Scanner (new File("input.txt"));
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
		long rez1=0, rez2=0;
		ArrayList <Long>binA = new ArrayList <Long>();
		ArrayList <Long>binB = new ArrayList <Long>();
		ArrayList <Integer>bin1A = new ArrayList<>();
		ArrayList <Integer>bin1B = new ArrayList<>();
		int p=70;
		long A = in.nextLong();
		long B = in.nextLong();
		int n = in.nextInt();
		in.close();
		B++; 
		long b;   
        while(B !=0 ) {  
            b = (B)%2;  
            binB.add(b)  ;
            B = B/2;  
        } 
        while(A !=0 ) {  
            b = (A)%2;  
            binA.add(b)  ;
            A = A/2;  
        } 
     
        long masC[][]=new long [p][p];
		for(int i=0; i<p; i++){
			masC[i][0]=1;
			masC[i][i]=1;
			
		}
		for(int i=2; i<p; i++){
			for(int j=1; j<p; j++){
				masC[i][j]=masC[i-1][j-1]+masC[i-1][j];
			}
		}
        for(int i=binA.size()-1; i>=0; i--){
        	if(binA.get(i)==1){
        		bin1A.add(i);
        	}
        }
        for(int i=binB.size()-1; i>=0; i--){
        	if(binB.get(i)==1){
        		bin1B.add(i);
        	}
        }
		for(int i=0; i<bin1B.size(); i++){
			if(n>=i){
				rez1+=masC[bin1B.get(i)][n-i];
			}				
		}
		for(int i=0; i<bin1A.size(); i++){
			if(n>=i){
				rez2+=masC[bin1A.get(i)][n-i];
			}		
		}
		writer.write(Long.toString(rez1-rez2)+"\n");
		writer.flush();
	}
}
