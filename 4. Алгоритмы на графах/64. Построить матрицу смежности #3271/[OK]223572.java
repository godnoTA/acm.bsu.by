import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class smej {

	public static void main(String[] args) throws IOException {
		try {
		Scanner r=new Scanner(new FileReader("input.txt"));
		int n=0;
		int m=0;
		String size;
		String line;
		size=r.nextLine();
		Scanner t=new Scanner(size);
		n=t.nextInt();
		m=t.nextInt();
		int mas[][]=new int[n][n];
		int i,j;
		for(int y=0;y<n;y++)
			for(int x=0;x<n;x++)
				mas[y][x]=0;
		for(int k=0;k<m;k++)
		{
			size=r.nextLine();
			Scanner f=new Scanner(size);
			i=f.nextInt();
			j=f.nextInt();
			mas[i-1][j-1]=1;
			mas[j-1][i-1]=1;
		}
		String output="";
		for(int y=0;y<n;y++)
		{
			for(int x=0;x<n;x++)
			{
				output=output+mas[y][x]+" ";
			}
			output=output+'\n';
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