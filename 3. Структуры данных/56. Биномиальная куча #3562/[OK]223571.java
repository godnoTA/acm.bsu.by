import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class bin {

	public static void main(String[] args) throws IOException {
		try {
		Scanner r=new Scanner(new FileReader("input.txt"));
		long n=0;
		String size;
		size=r.nextLine();
		n=Long.parseLong(size);
		 String str;
		String out="";
		str= Long.toString(n, 2);
		int j=0;
		for(int i=str.length()-1;i>=0;i--)
			{
			if(str.charAt(i)=='1')
				out=out+j+'\n';
			j++;
			}
		FileWriter writer = new FileWriter("output.txt");
		writer.write(out);	
		writer.close();
	} 
	catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
	}
}
