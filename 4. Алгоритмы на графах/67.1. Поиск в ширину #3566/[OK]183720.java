import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Scanner;


public class BFS {

	public static void main(String[] args) throws IOException {
		Scanner in=new Scanner (new File("input.txt"));
		int cont_of_node=in.nextInt();
		
		int matr[][]=new int[cont_of_node][cont_of_node];
		int mas[]=new int[cont_of_node];
		
		for(int i=0;i<cont_of_node;i++){
			mas[i]=0;
			for(int j=0;j<cont_of_node;j++)
				matr[i][j]=in.nextInt();
		}
		int count=1;
		
		ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
		for(int k=0;k<cont_of_node;k++){
			if(mas[k]==0)
				dq.push(k);
			else
				continue;
		while(!dq.isEmpty()){
			int a=dq.pop();
			if(mas[a]!=0)
				continue;
			else{
			mas[a]=count;
			count++;
			for(int j=0;j<cont_of_node;j++){
				if(matr[a][j]==1 || mas[a]==0){
					dq.add(j);
				}
			}
			
			for(int i=0;i< cont_of_node;i++)
				System.out.print(mas[i]+" ");
			System.out.print("\n");
			}
		}
		}
		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
		for(int i=0;i< cont_of_node;i++){
			out.write(mas[i]+" ");
		}
		out.flush();

	}

}
