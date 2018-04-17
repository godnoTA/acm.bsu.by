import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Alg_10 {

	public static void main(String[] args) {
		File fis = new File("input.txt");
		File fos = new File("output.txt");
		Scanner in;
		FileWriter out;
		try {
			in = new Scanner(fis);
			out = new FileWriter(fos);
			int count = Integer.parseInt(in.next());
			int[] parents = new int[count];
			for(int i = 0; i < (count - 1); i++) {
				int k = Integer.parseInt(in.next());
				int j = Integer.parseInt(in.next());
				parents[j - 1] = k;
			}
			for(int i = 0; i <  count; i++) {
				out.write(parents[i] + " ");
			}
			out.close();
			in.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
