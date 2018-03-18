import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Heap {

	public static boolean isHeap(long[] data) {
		boolean result = true;
		for(int i = 0; i < data.length; i++) {
			if((2*i + 1) > (data.length - 1))
				break;
			if((2*i + 1) <= (data.length - 1) && data[i] > data[2*i + 1]) {
				result = false;
				break;
			}
			if((2*i + 2) <= (data.length - 1) && data[i] > data[2*i + 2]) {
				result = false;
				break;
			}				
		}
		return result;
	}
	
	public static void main(String[] args) {
		File fis = new File("input.txt");
		File fos = new File("output.txt");
		Scanner in;
		FileWriter out;
		try {
			in = new Scanner(fis);
			out = new FileWriter(fos);
			int lenght = Integer.parseInt(in.nextLine());
			long[] data = new long[lenght];
			int i = 0;
			if( lenght != 0) {
				while(in.hasNext()) {
					data[i] = Long.parseLong(in.next());
					i++;
				}
				boolean result = isHeap(data);
				if( result) 
					out.write("Yes");
				else
					out.write("No");
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

