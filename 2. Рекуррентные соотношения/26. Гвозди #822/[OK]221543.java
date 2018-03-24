import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Gvozdi implements Runnable {
	public static void main(String[] args) throws FileNotFoundException { 
		new Thread(null, new Gvozdi(), "", 64 * 1024 * 1024).start();
	}
		
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Scanner in;
		try {
			in = new Scanner(new FileInputStream("in.txt"));
			String str=in.nextLine();
			int N = Integer.parseInt(str); 
			float[] a = new float[N]; 
			int i=0;
			while(in.hasNext()) {
				str=in.nextLine();
				a[i]=Float.parseFloat(str);
				i++;
			}
			i=2;
			float L0, L1;
			if(N==2){
				L0=0;
				L1=a[1]-a[0];
			}
			else
				{
				L0=a[1]-a[0];
				L1=a[2]-a[0];
				}
			while(i<N-1) {
				float tmp=L0;
				L0=L1;
				if((L1+Math.abs(a[i+1]-a[i]))<=(tmp+Math.abs(a[i+1]-a[i])))
					L1=L1+Math.abs(a[i+1]-a[i]);
				else
					L1=tmp+Math.abs(a[i+1]-a[i]);
				//System.out.println(L0+"  "+L1);
				i++;
			}
			
			PrintStream out;
			try {
				out = new PrintStream(new FileOutputStream("out.txt"));
				out.printf("%.2f", L1); 
				System.out.printf("%.2f", L1); 
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

