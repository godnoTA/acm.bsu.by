import java.io.*;
import java.util.*;

public class Var_1{
	
	public static void main(String[]args){
		try {
		int size_of_matrix=0, amount_of_edges= 0;
		String str_test = null;
		Scanner sc;
		sc = new Scanner(new File("input.txt"));
		size_of_matrix= sc.nextInt();
		ArrayList<Integer>[]mas = new ArrayList[size_of_matrix];
		for(int i =0; i< size_of_matrix; i++){
			mas[i] = new ArrayList<Integer>();
		}
		String temp_mas[]= new String[2];
		amount_of_edges= sc.nextInt();
		while (sc.hasNextLine()) {
			if(!((str_test=sc.nextLine()).isEmpty())){
				temp_mas = str_test.split(" ");
				mas[Integer.parseInt(temp_mas[0])-1].add(Integer.parseInt(temp_mas[1]));
				mas[Integer.parseInt(temp_mas[1])-1].add(Integer.parseInt(temp_mas[0]));
			}
		}
		sc.close();
		
		

		
		
		
		
			FileWriter writeFile = new FileWriter(new File("output.txt"));
			for(int i = 0; i<size_of_matrix; i++){
				if(mas[i].isEmpty()){
					writeFile.write(""+0+"\r\n");
				}
				else{
					writeFile.write(""+mas[i].size());
					for(int j:mas[i]){
						writeFile.write(" "+j);
					}
				}
				writeFile.write("\r\n");
			}

			writeFile.close();
		} catch (Exception e1) {}
	}

}
