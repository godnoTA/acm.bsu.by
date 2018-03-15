import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;


public class MyWay {

	public static void main(String[] args) throws IOException {
		Scanner in=new Scanner (new File("input.txt"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
		
		int count_str=in.nextInt();
		int count_stb=in.nextInt();
		int matr[][]=new int [count_str][count_stb];
		
		for(int i=0;i<count_str;i++){
			for(int j=0;j<count_stb;j++)
				matr[i][j]=in.nextInt();
		}
		
		int [][]mas_res=new int [count_str][count_stb];
		for(int i=0;i<count_stb;i++)
			mas_res[0][i]=matr[0][i];
		

		int min;
		for(int i=1;i<count_str;i++){
			if(count_stb!=1){
			for(int j=0;j<count_stb;j++){
				if(j==0 || j==count_stb-1){
					if(j==0)
						mas_res[i][j]=matr[i][j]+Math.min(mas_res[i-1][j], mas_res[i-1][j+1]);
					if(j==count_stb-1)
						mas_res[i][j]=matr[i][j]+Math.min(mas_res[i-1][j-1], mas_res[i-1][j]);
					}
				else{
					min=Math.min(mas_res[i-1][j], mas_res[i-1][j+1]);
					mas_res[i][j]=matr[i][j]+Math.min(mas_res[i-1][j-1], min);
				}
			}
			}
			else
				mas_res[i][0]=mas_res[i-1][0]+matr[i][0];
		}
		
		min=mas_res[count_str-1][0];
		for(int i=1;i<count_stb;i++)
			min=Math.min(mas_res[count_str-1][i],min);

		out.write(min+"");
		out.flush();

	}

}