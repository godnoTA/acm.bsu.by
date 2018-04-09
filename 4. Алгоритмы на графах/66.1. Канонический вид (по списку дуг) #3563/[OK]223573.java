import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class kanon {

	public static void main(String[] args) throws IOException {
		try {
		Scanner r=new Scanner(new FileReader("input.txt"));
		int dim;
		dim=r.nextInt();
		int mas[]=new int[dim+1];
		int father;
		int son;
		while(r.hasNextInt())
		{
		father=r.nextInt();
		son=r.nextInt();
		mas[son]=father;
		}
		FileWriter writer = new FileWriter("output.txt");
		for(int o=1;o<=dim;o++)
			writer.write((mas[o])+" ");
		writer.close();
	} 
	catch (FileNotFoundException e) {
		
		e.printStackTrace();
	}
	}
}