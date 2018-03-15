import java.io.*;
import java.util.*;

public class Main{
	
	public static void main(String[]args){
		try {
		int size_of_matrix=0;
		String str_test = null;
		Scanner sc;
		sc = new Scanner(new File("input.txt"));
		size_of_matrix= sc.nextInt();
		String temp_mas[]= new String[size_of_matrix];
		int mas[]= new int[size_of_matrix+1];
		while (sc.hasNextLine()) {
			if(!((str_test=sc.nextLine()).isEmpty())){
				temp_mas = str_test.split(" ");
				for(int i = 1; i<=size_of_matrix;i++){
					mas[i]=Integer.parseInt(temp_mas[i-1]);
				}
			}
		}
		
		sc.close();
		boolean flag = true;
		FileWriter writeFile = new FileWriter(new File("output.txt"));
		
		for(int i = 1; 2*i+1<=size_of_matrix; i++){
			if(mas[i]<=mas[2*i]&&mas[i]<=mas[2*i+1]){
				continue;
			}
			else{
				flag=false;
				break;
			}
		}
		if((size_of_matrix)%2==0){
			if(!(mas[size_of_matrix]>=mas[size_of_matrix/2])){
				flag = false;
			}
		}
		if(flag)
			writeFile.write("Yes");
		else
			writeFile.write("No");
			writeFile.close();
		} catch (Exception e1) {}
	}

}
