import java.io.*;
import java.util.*;


public class Matr {

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(new FileReader("input.txt"))){
		int m=sc.nextInt();
		int c=sc.nextInt(); 
		int n=sc.nextInt();
		
		int []mas=new int[m];
		for(int i=0; i < m;i++){
			mas[i]=-1;
		}
		
		for(int j=0; j<n ;j++){
			
			int x=sc.nextInt(); 
			int b=1;
			for(int i=0; i < m;i++){
				if( mas[i]==x)
					b=0;
			}
			if(b==1){
				for(int i=0; i < m;i++){
					int y=(x % m + c*i) % m;
					if( mas[y] == -1 ){
						mas[y]=x;
						break;
					}
				}
			}
		}
		
		PrintWriter out = new PrintWriter(new File("output.txt"));
		
		for(int i = 0 ; i < m;i++){
			out.print(mas[i]);
			out.print(" ");
		}
		
		out.close();
		sc.close();
	}
		catch(Exception e){
        }	
	}

}