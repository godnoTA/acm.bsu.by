import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class kanon {

	public static void main(String[] args) throws IOException {
		try {
		Scanner r=new Scanner(new FileReader("input.txt"));
		int razmer=0;
		String size;
		String line;
		size=r.nextLine();
		razmer=Integer.parseInt(size);
		int mas[][]=new int[razmer][razmer];
		for(int i=0;i<razmer;i++)
		{
			line=r.nextLine();
			Scanner l=new Scanner(line);
			String [] strok = null;
				 while(l.hasNext()){
					 strok = l.nextLine().split(" ");}
				 for(int j=0;j<razmer;j++)
					 mas[i][j] = Integer.parseInt(strok[j]);}
		String output="";
		for(int j=0;j<razmer;j++)
		{
			int stolbec=0;
		for(int i=0;i<razmer;i++)
		{
			if(mas[i][j]==1)
				stolbec=i+1;
		}
		output=output+stolbec+" ";
		}
		FileWriter writer = new FileWriter("output.txt");
		writer.write(output);	
		writer.close();
	} 
	catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
	}
}
