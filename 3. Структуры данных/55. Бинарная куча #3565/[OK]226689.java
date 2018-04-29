import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Bin_Kucha {
	public static void main(String[] args) throws FileNotFoundException {
		File fin = new File("input.txt");
		Scanner sc = new Scanner(fin);
		int n = sc.nextInt();
		int[] mat = new int[n];
		System.out.println(n);
		for (int i = 0; i < mat.length; i++) {
			mat[i] = sc.nextInt();
		}
		boolean flag = true;
		int i = n / 2 - 1;
		if (n % 2 == 0) {
			if (mat[i] > mat[n - 1])
				flag = false;
			n = n - 2;
			i--;
		} else {
			n = n - 1;
			
		}
		for (; i >= 0; i--) {
			System.out.println(mat[i] + "\t" + mat[n] + "\t" + mat[n - 1]);
			if (mat[n] < mat[i] || mat[n - 1] < mat[i]) {
				flag = false;
				break;
			}
			n = n - 2;
		}
		System.out.println(flag);
		if(flag){
			writeToFile("output.txt", "Yes");
		} else{
			writeToFile("output.txt", "No");
		}
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
