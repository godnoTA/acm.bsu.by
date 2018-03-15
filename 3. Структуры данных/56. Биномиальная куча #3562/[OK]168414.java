import java.io.BufferedWriter;
import java.io.*;
import java.util.*;

public class Kucha {

	static long fun(long i){
		long x=1;
		for(int j=0; j<i; j++){
			x *= 2;
		}
		return x;
	}
	
	static BufferedWriter writer;
	public static void main(String[] args) throws IOException {
		Scanner in=new Scanner (new File("input.txt"));
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
		ArrayList <Long> array = new ArrayList <Long>();
		long n=in.nextLong();
		long i=0;
		while(Math.pow(2, i)<n){
			i++;
		}
		if(n==0){
			writer.write(-1+"\r\n");
		}
		while(n!=0){
			long p=fun(i);
			if(n-p>=0){
				n-=p;
				array.add(i);
			}	
			i--;
		}
		Collections.sort(array, new Comparator<Long>() {
			public int compare(Long arg0, Long arg1) {
				if(arg0<arg1)
					return -1;
				else 
					return 1; 
			}
		});
		for(int j=0; j<array.size();j++){
			writer.write(array.get(j)+"\r\n");
			
		}
		in.close();
		writer.flush();
	}
}