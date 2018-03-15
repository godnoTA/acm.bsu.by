
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Test_55 {
	public static void main(String [] args) throws IOException{
		Scanner scan = new Scanner(new File("input.txt"));
		long N = scan.nextLong();
		scan.close();
		int i = 0;
		String bin = Long.toBinaryString(N);
		FileWriter out = new FileWriter("output.txt");
		boolean flag = false;
		while(i != bin.length())
		{
			if(bin.charAt(bin.length() - 1 - i) == '1')
			{
				out.write(Integer.toString(i) + "\n");
				flag = true;
			}
			i ++;
		}
		if(flag == false)
			out.write("-1\n");
		out.close();
	}
}
