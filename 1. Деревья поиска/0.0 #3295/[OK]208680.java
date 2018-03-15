import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Al0 {

	public static void main(String[] args) {
		long sum = 0;
		LinkedList<Integer> treeKeys = read("input.txt");
		for(int tmp: treeKeys){
			sum += tmp;
		}
		writeToFile("output.txt", Long.toString(sum));
	}


	public static LinkedList<Integer> read(String fileName) {
		LinkedList<Integer> res = new LinkedList<>();
		File dir = new File(fileName);
		if (dir.getName().contains(".txt")) {
				res = workWithFile(dir);
						}
		return res;
	}
	
	public static LinkedList<Integer> workWithFile(File file) {
		LinkedList<Integer> treeKeys = new LinkedList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			try {
				String s;
				while ((s = in.readLine()) != null) {
					if(!contains(treeKeys, Integer.parseInt(s))){
						treeKeys.add(Integer.parseInt(s));
					}
				}
			} finally {
				in.close();
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		return treeKeys;
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
	
	public static boolean contains(LinkedList<Integer> li, int k){
		for(int tmp: li){
			if(k == tmp)
				return true;
		}
		return false;
	}
}