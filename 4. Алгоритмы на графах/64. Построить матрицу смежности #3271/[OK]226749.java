import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Matrix {

	public static void main(String[] args) throws FileNotFoundException {
		File fin = new File("input.txt");
		Scanner sc = new Scanner(fin);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int [][]mat = new int[n][n];
		while(sc.hasNextInt()){
			int i = sc.nextInt() - 1;
			int j = sc.nextInt() - 1;
			System.out.println(i + "\t" + j);
			mat[i][j] = 1;
			mat[j][i] = 1;
			System.out.println(mat[i][j]);
		}
		StringBuffer answ = new StringBuffer();
		for(int i[]: mat){
			for(int j : i){
				answ.append(j + " ");
			}
			answ.append("\r\n");
		}
		writeToFile("output.txt", answ.toString());
	}
	
	public static void writeToFile(String fileName, String text){
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

}
