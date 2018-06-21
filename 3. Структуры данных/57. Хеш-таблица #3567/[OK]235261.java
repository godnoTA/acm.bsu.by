import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Vector;

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
		
		int n, m, c;
		m=in.nextInt();
		c=in.nextInt();
		n=in.nextInt();
		//Vector<Integer>myV=new Vector<>();
		TreeSet<Integer>myV=new TreeSet<>();
		int[]table=new int[m];
		for(int i=0; i<m; i++)
			table[i]=-1;
		int v, f=0;
		int k=0, j=0;
		for(int i=0; i<n; i++) {
			v=in.nextInt();
			if(myV.add(v)==true) {
			k=(v%m)%m;
			j=0;
			while(table[k]!=-1) {
				j++;
				k=(v%m+c*j)%m;
			}
			table[k]=v;
			}
		}
		PrintStream out;
		try {
			out = new PrintStream(new FileOutputStream("output.txt"));
		
		for(int i=0; i<m; i++)
				out.print(table[i]+" ");
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