import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class MyTree {

	public static void main(String[] args) throws FileNotFoundException {
	
	Scanner in=new Scanner(new File("input.txt"));
	
	int cont_of_node = in.nextInt();
	int[]mas=new int[cont_of_node];
	
	
	while(in.hasNextInt()){
		int a=in.nextInt();
		int b=in.nextInt();
		mas[b-1]=a;
	}
	in.close();
	
	File file1 = new File("output.txt"); 
	PrintWriter out = new PrintWriter(file1.getAbsoluteFile()); 
	
	for(int i=0;i<cont_of_node ;i++){
		out.print(mas[i]+" "); 
	}
	out.close();
}
}
