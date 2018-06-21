import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Vector;

public class task implements Runnable{

	public static void main(String[] args) throws FileNotFoundException { 
		new Thread(null, new task(), "", 64 * 1024 * 1024).start();
	}
		
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Scanner in;
		try {
			in = new Scanner(new FileInputStream("input.txt"));
			//String str=in.nextLine();
			int N = in.nextInt();
			/*int [][]matr=new int[N][2];
			for(int i=0; i<N; i++)
				for(int j=0; j<2; j++)
					matr[i][j]=in.nextInt();*/
			int[]p=new int[N+1];
			for(int i=0; i<N-1; i++) {
				p[i]=in.nextInt();
				in.nextInt();
			}
			p[N-1]=in.nextInt();
			p[N]=in.nextInt();
			int n = p.length - 1;
			int[][] dp = new int[n + 1][n + 1];
			
			for (int i = 1; i <= n; i++) {
				dp[i][i] = 0;
			}
			
			for (int l = 2; l <= n; l++) {
				for (int i = 1; i <= n - l + 1; i++) {
					int j = i + l - 1;
					dp[i][j] = Integer.MAX_VALUE;
					for (int k = i; k <= j - 1; k++) {
						dp[i][j] = Math.min(dp[i][j],
	                                                   dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j]);
					}
				}
			}
			/*
			int k=0, min=1, l=0;
			while(N>1) {
				min=matr[0][0]*matr[1][1];
				l=0;
				for(int i=1; i<N-1; i++) {
					if(min>matr[i][0]*matr[i+1][1]) {
						min=matr[i][0]*matr[i+1][1];
						//System.out.println(min+" min");
						l=i;
					}
					if(min==matr[i][0]*matr[i+1][1]) {
						if(matr[i][1]>matr[l][1])
							l=i;
					}
				}
				k+=min*matr[l][1];
				//System.out.println(k);
				N--;
				matr[l][1]=matr[l+1][1];
				for(int i=l+1; i<N; i++) {
						//matr[i-1][1]=matr[i][1];
						matr[i][0]=matr[i+1][0];
						matr[i][1]=matr[i+1][1];
					}
				}*/
			PrintStream out;
				out = new PrintStream(new FileOutputStream("output.txt"));
				out.print(dp[1][n]); 
				System.out.println(dp[1][n]); 
				in.close(); 
				out.close(); 
				System.exit(0); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		}

}
