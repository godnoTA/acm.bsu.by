import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Alg_I_4 {

	public static void main(String[] args) {
		File fis = new File("in.txt");
		File fos = new File("out.txt");
		Scanner in;
		FileWriter out;
		try {
			in = new Scanner(fis);
			out = new FileWriter(fos);
			int degree = Integer.parseInt(in.next());
			int count = (int)Math.pow(4, (degree));
			int steps = degree * 2;
			int size = (int) Math.sqrt(count);
			int[][] matr = new int[size][size];
			int h = 0;
			matr[0][0] = 1;
			for(int i = 1; i <= steps; i++) {
				if(i % 2 == 1) {
					if(i == 1)
						h = i;
					else
						h = 2 * h;
					int index = 2 * h;
					for(int j = 0; j < h; j++) {
						for(int k = 0; k < h; k++) {							
							if(j % 2 == k % 2)
								matr[index - j - 1][k] = matr[j][k] + (int)Math.pow(2, (steps + 1 - i)) - 1;
							else
								matr[index - j - 1][k] = matr[j][k] - (int)Math.pow(2, (steps + 1 - i)) + 1;
						}							
					}
				} else { // ???????
					for(int j = 0; j < (2 * h); j++) {
						for(int k = 0; k < h; k++) {	
							int index = (h + k - 1);
							if(j % 2 == index % 2)
								matr[j][h + k] = matr[j][h - k - 1] + (int)Math.pow(2, (steps + 1 - i)) - 1;
							else
								matr[j][h + k] = matr[j][h - k - 1] - (int)Math.pow(2, (steps + 1 - i)) + 1;
						}							
					}
				}
			}	
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					if(i == (size - 1) && j == (size - 1))
						out.write(Integer.toString(matr[i][j]));
					else
						out.write(matr[i][j] + " ");
				}
	//			out.write("\n");
			}
			out.close();
			in.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
