import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class BinomicalHeap {

	public static void main(String[] args) {
		File fis = new File("input.txt");
		File fos = new File("output.txt");
		Scanner in;
		FileWriter out;
		try {
			in = new Scanner(fis);
			out = new FileWriter(fos);
			long count = Long.parseLong(in.nextLine());
			if(count > 0) {
				String str = Long.toBinaryString(count);
		//		System.out.println(str);
				char[] result = str.toCharArray();
		//		System.out.println(result.length);
				for(int i = (result.length - 1); i > -1; i--) {
		//			System.out.println(result[i]);
					if(result[i] == '1')
						out.write(Integer.toString((result.length - i - 1)) +"\n");
				}
			} else 
				out.write("-1");
			out.close();
			in.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
