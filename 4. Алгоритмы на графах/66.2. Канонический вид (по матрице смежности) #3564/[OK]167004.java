import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class MyTree {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in=new Scanner(new File("input.txt"));
		
		int cont_of_node = in.nextInt();
		
		int[][]matr=new int[cont_of_node][cont_of_node];
		
		while(in.hasNextInt()){
			for(int i = 0 ; i < cont_of_node ;i++)
				for(int j=0 ;j<cont_of_node; j++)
					matr[i][j]=in.nextInt();
		}
		
		int[]mas=new int[cont_of_node];
		
		for(int i = 0 ; i < cont_of_node ;i++){
			for(int j=0 ;j<cont_of_node; j++){
				if (matr[j][i]==1){
					mas[i]=j+1;
				}
			}
		}
		
		String s="";
		
		for(int i = 0 ; i < cont_of_node ;i++)
			s+=(mas[i]+" ");
	

		 try(FileWriter writer = new FileWriter("output.txt", false))
	        {
	            writer.write(s);
	            writer.flush();
	        }
	        catch(IOException ex){
	             
	            System.out.println(ex.getMessage());
	        } 
	}

}
