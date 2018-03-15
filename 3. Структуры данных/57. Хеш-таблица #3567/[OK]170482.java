import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class HESH {

	public static void main(String[] args) throws IOException {
		Scanner in=new Scanner (new File("input.txt"));
		int cont_of_node=in.nextInt();
		int c=in.nextInt(); 
		int add_node=in.nextInt();
		
		int []mas=new int[cont_of_node];
		for(int i=0; i < cont_of_node;i++)
			mas[i]=-1;
		
		for(int j=0; j<add_node ;j++){
			
			int x=in.nextInt(); 
			boolean f=true;
			for(int i=0; i < cont_of_node;i++){
				if( mas[i]==x)
					f=false;
			}
			if(f==true){
				for(int i=0; i < cont_of_node;i++){
					int a=(x % cont_of_node + c*i) % cont_of_node;
					if( mas[a] == -1 ){
						mas[a]=x;
						break;
					}
				}
			}
		}
		in.close();
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
		
		for(int j = 0 ; j < cont_of_node;j++){
			out.write(mas[j]+" ");
		}
		out.flush();
		
	}

}