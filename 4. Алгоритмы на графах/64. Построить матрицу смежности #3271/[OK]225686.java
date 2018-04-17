import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Alg_8 {

	public static void main(String[] args) {
		File fis = new File("input.txt");
		File fos = new File("output.txt");
		Scanner in;
		FileWriter out;
		try {
			in = new Scanner(fis);
			out = new FileWriter(fos);
			int lenght = Integer.parseInt(in.next());
			int count = Integer.parseInt(in.next());
			int[][] matr = new int[lenght][lenght];
			if( lenght != 0) {
				for(int i = 0; i < count; i++) {
					int k = Integer.parseInt(in.next());
					int j = Integer.parseInt(in.next());
					matr[k - 1][j - 1] = 1;
					matr[j - 1][k - 1] = 1;
				}
				for(int i = 0; i <  lenght; i++) {
					for(int j = 0; j < lenght; j++) {
						out.write(matr[i][j] + " ");
					}
					out.write("\n");
				}
			} else {
				out.write("No");
			}			
			/*System.out.println("result:" + result);
			for(int j = 0; j < lenght; j++) {
				System.out.print(data[j] + " ");
			}	
			System.out.println();*/
			out.close();
			in.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
