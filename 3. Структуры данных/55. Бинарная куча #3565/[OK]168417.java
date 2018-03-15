import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Kucha {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in=new Scanner(new File("input.txt"));
		
		int cont_of_node = in.nextInt();
		
		int[]mas=new int[cont_of_node+2];
		
		for(int i=1; i < cont_of_node+1 ;i++)
			mas[i]=in.nextInt();
		
		in.close();
		boolean flag=true;
		
		for(int i=1 ; i<cont_of_node+1 ; i++){
	
			if( 2*i > cont_of_node || (mas[2*i] >= mas[i] && mas[2*i+1]>=mas[i])||(mas[2*i] >= mas[i]&& 2*i+1 >cont_of_node))
				continue;
			
			else
			{
				flag=false;
				break;
			}
		}
		
		File file1 = new File("output.txt"); 
		PrintWriter out = new PrintWriter(file1.getAbsoluteFile()); 
		
		if(flag==false)
			out.print("No"); 
		else
			out.print("Yes"); 
		
		out.close();

	}

}
