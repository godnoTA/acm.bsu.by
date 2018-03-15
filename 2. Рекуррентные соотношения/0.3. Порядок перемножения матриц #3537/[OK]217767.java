import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main implements Runnable{
	static int count;
	static int [][] matrixes;
	static int D[][];
	static int m[];

	public static void workWithFile(File file) {
		try {
			Scanner in = new Scanner(file);
			try {
				String s;
				if(in.hasNextLine() != false)
					count = in.nextInt();
				System.out.println(count);
				matrixes = new int[count + 1][2];
				D = new int[count + 1][count + 1];
				for(int i = 1; i < count + 1; i++){
					matrixes[i][0] = in.nextInt();
					matrixes[i][1] = in.nextInt();
				}
			} finally {
				in.close();
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static void writeToFile(String fileName, String text) {
		File file = new File(fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(file.getAbsoluteFile());

			try {
				out.print(text);
			} finally {
				out.close();
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static void main(String[] args) {
		new Thread(null, new Main(), "", 64 * 1024 * 1024).start();		
	}
	
	public static int MatrixMultTD(int i,int j){
		if(D[i][j] == -1){
			if(i == j)
				D[i][j] = 0;
			else{
				for(int k = i; k <= j-1; k++){
					int l = MatrixMultTD(i, k);
					int r = MatrixMultTD(k+1,j);
					if(D[i][j] > (l + r + m[i-1]*m[k]*m[j]) || D[i][j] == -1){
						D[i][j] = l + r + m[i-1]*m[k]*m[j];
						System.out.println(D[i][j]);
					}
				}
			}
		}
		return D[i][j];
	}

	@Override
	public void run() {
		File file = new File("input.txt");
		workWithFile(file);
		for(int i = 0; i < D.length; i++){
			for(int j = 0; j < D[i].length; j++){
				D[i][j] = -1;
			}
		}
		m = new int[count + 1];
		m[0] = matrixes[1][0];
		for(int i = 1; i < matrixes.length; i++){
			m[i] = matrixes[i][1];
		}
		for(int i = 0; i < m.length; i++){
			System.out.print( m[i] + "\t");
		}
		System.out.println();
		int result = MatrixMultTD(1, matrixes.length -1);
		writeToFile("output.txt", Integer.toString(result));
		
	}
	

}
