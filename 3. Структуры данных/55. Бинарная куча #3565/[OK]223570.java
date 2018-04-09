import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class bin {

	static boolean check(int[] mas, int razmer)
	{
		boolean a=true;
		for(int i=razmer;i>1;i--)
			if(mas[i/2]>mas[i])
			a=false;
		return a;
	}
	
	public static void main(String[] args) throws IOException {
		try {
		Scanner r=new Scanner(new FileReader("input.txt"));
		int razmer=0;
		String size;
		String line;
		size=r.nextLine();
		razmer=Integer.parseInt(size);
		int mas[]=new int[razmer+1];
			line=r.nextLine();
			Scanner l=new Scanner(line);
			String [] strok = null;
				 while(l.hasNext()){
					 strok = l.nextLine().split(" ");}
				 for(int j=1;j<razmer+1;j++)
					 mas[j] = Integer.parseInt(strok[j-1]);
		String str;
		if(check(mas,razmer))
			str="Yes";
		else
			str="No";
		FileWriter writer = new FileWriter("output.txt");
		writer.write(str);	
		writer.close();
	} 
	catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
	}
}
