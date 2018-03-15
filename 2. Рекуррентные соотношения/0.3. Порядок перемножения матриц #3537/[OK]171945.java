import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Matrix {
	public static int Umnozenie(int[] p) {
		int n = p.length;
		int[][] matr = new int[n][n];
		
		for (int l = 2; l < n; l++) {
			for (int i = 1; i < n - l + 1; i++) {
				int j = i + l - 1;
				matr[i][j] = Integer.MAX_VALUE;
				for (int k = i; k <= j - 1; k++) {
					matr[i][j] = Math.min(matr[i][j], matr[i][k] + matr[k + 1][j] + p[i - 1] * p[k] * p[j]);
				}
			}
			/*for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					System.out.print(matr[i][j]+"\t");
				}
				System.out.println();
			}
			System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");*/
		}
		return matr[1][n-1]; 
	}
	
	public static void main(String[] args) throws IOException {
		File file = new File("input.txt");
		File file1 = new File("output.txt");
		BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
		PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
		String s;
		int len=Integer.parseInt(in.readLine());
		int[] mas=new int[len+1];
		int j=0;
		while ((s=in.readLine())!=null){
			String[] arr=s.split(" ");
			if(j==0){
				mas[j]=Integer.parseInt(arr[0]);
				mas[j+1]=Integer.parseInt(arr[1]);
				j+=2;
				continue;
			}
			mas[j]=Integer.parseInt(arr[1]);
			j++;
		}
		for(int i=0;i<mas.length;i++){
			System.out.print(mas[i]+" ");
		}
		System.out.println();
		//System.out.println(Umnozenie(mas));
		out.print(Umnozenie(mas));
		in.close();
		out.close();
	}
}
