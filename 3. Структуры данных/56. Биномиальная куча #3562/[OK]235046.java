import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
//import java.util.Stack;
import java.util.TreeSet;

public class Solution implements Runnable {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		new Thread(null, new Solution(), "", 64 * 1024 * 1024).start();

		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Scanner in;
		try {
			in = new Scanner(new FileInputStream("input.txt"));
		
		long n;
		n=in.nextLong();
		
		int k=0;
		TreeSet<Integer>mySet=new TreeSet<>();
		while(n!=0) {
			if(n%2==1) {
				mySet.add(k);
				n--;
				//k++;
			}
			else {
				if(n>2) {
				k++;
				n /=2;
				}
				else {
					k++;
					mySet.add(k);
					n=0;
				}
			}
		}
		/*while(n!=0) {
			k=0;
			while(Math.pow(2, k)<n) {
				
				k++;
			}
			if(Math.pow(2, k)!=n) 
			if(k!=0)
				k--;
			myStack.push(k);
			n-=Math.pow(2, k);
		}*/
		PrintStream out;
		try {
			out = new PrintStream(new FileOutputStream("output.txt"));
		
		/*while(!myStack.isEmpty()) {
			k=myStack.pop();
			out.println(k);
		}*/
			for(int s:mySet)
				out.println(s);
		in.close(); 
		out.close(); 
		System.exit(0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
		

}