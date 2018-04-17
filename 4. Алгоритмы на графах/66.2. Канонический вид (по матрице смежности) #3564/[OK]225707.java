import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Alg_11 {

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
			for(int i = 0; i < count; i++) {
				for(int j = 0; j < count; j++) {
					int k = Integer.parseInt(in.next());
					if(k == 1)
						parents[j] = i + 1;
				}
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
