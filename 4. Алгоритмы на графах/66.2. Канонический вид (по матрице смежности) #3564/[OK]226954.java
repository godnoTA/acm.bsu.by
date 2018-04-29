import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Kanon {

	public static void main(String[] args) throws FileNotFoundException {
		File fin = new File("input.txt");
		Scanner sc = new Scanner(fin);
		int n;
		n = sc.nextInt();
		int[] kanon = new int[n];
		for (int i = 0; i < n; i++) {
			kanon[i] = 0;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int k;
				k = sc.nextInt();
				if (k == 1) {
					kanon[j] = i+1;
				}
			}
		}
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < n; i++) {
			out.append(kanon[i] + " ");
		}
		writeToFile("output.txt", out.toString());
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

}
