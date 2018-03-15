import java.io.*;
import java.util.Scanner;

public class test {
	
	
	
	static int array[][]=new int [1000][1000];
	static boolean flag[][]=new boolean [1000][1000];
	static int n=0;
	static BufferedWriter writer;
	public static void main(String[] args) throws IOException {
		
		for(int i=0; i<1000; i++){
			for(int j=0; j<1000; j++){
				array[i][j]=0;
				flag[i][j]=true;
			}
		} 
		
		Scanner in=new Scanner (new File("input.txt"));
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
		while(in.hasNextInt()){
			int i = in.nextInt();
			int j = in.nextInt();
			int proc = in.nextInt();
			array[i-1][j-1]=proc;
			if(n<Math.max(i, j))
				n=Math.max(i, j);
		}
	    for(int p=0; p<n; p++){
	    	for(int i=0; i<n; i++){
				for(int j=0; j< n; j++){
					if (array[i][j]>50 && flag[i][j]==true && i!=j){
						flag[i][j]=false;
						for(int k=0; k<n; k++){
							array[i][k]+=array[j][k];
							if(array[i][k]>50){
								array[i][k]=51;
							}
						}
						
					}
				}
			}
		}
	    
	    
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if (array[i][j]>50 && i!=j){
					writer.write((i+1)+" "+(j+1)+"\r\n");
				}
			}
		}
		writer.flush();
		in.close();
	}
}