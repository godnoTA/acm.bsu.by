
import java.io.*;
import java.util.*;

public class Matr {

	public static void main(String[] args) {
		
		try(Scanner sc = new Scanner(new FileReader("input.txt"))){
			int n=sc.nextInt();
			int mas []=new int[n+1];
			for(int i=1; i<=n; i++){
				mas[i]=sc.nextInt();
			}
			
			
			PrintWriter out = new PrintWriter(new File("output.txt")); 
			
			for(int i=1; i<=n/2; i++){
				if(2*i+1<=n){
					if(mas[i]>mas[2*i] || mas[i]>mas[2*i+1]){
						out.print("No");
						sc.close();
						out.close(); 
						return;
						
					}
				}
				else{
					if(mas[i]>mas[2*i] ){
						out.print("No"); 
						sc.close();
						out.close(); 
						return;
						
					}
					
					
				}
				
			}
			
			out.print("Yes");
			sc.close();
			out.close(); 
			
		}
		catch(Exception e){

        }
	}
		
}