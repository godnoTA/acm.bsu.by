import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Alg_9 {

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
			ArrayList<Integer>[] nodes = new ArrayList[lenght];
			for(int i = 0; i < lenght; i++) {
				nodes[i] = new ArrayList<Integer>();
			}
			for(int i = 0; i < count; i++) {
				int k = Integer.parseInt(in.next());
				int j = Integer.parseInt(in.next());
				nodes[k - 1].add(j);
				nodes[j - 1].add(k);
			}
			for(int i = 0; i <  lenght; i++) {
				out.write(nodes[i].size() + " ");
				for(int j = (nodes[i].size() - 1); j != (-1); j--) {
					out.write(nodes[i].get(j) + " ");
				}
				out.write("\n");
			}		
			out.close();
			in.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
