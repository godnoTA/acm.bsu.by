import java.io.*;
import java.util.*;





public class Main {
	
	static public void main(String[]args){
		int size_of_matrix=0, amount_of_edges= 0;
		List<String> matrix_of_graph= new ArrayList<String>();
		String str_test = null;
		try {
			Scanner sc = new Scanner(new File("input.txt"));
			size_of_matrix= sc.nextInt();
			amount_of_edges= sc.nextInt();
			while (sc.hasNextLine()) {
				if(!((str_test=sc.nextLine()).isEmpty()))
					matrix_of_graph.add(str_test);
			}
			sc.close();
		} catch (FileNotFoundException e) {};
		
		String temp_mas[]= new String[2];
		int result [][] = new int[size_of_matrix][size_of_matrix];
		int coef_1, coef_2;
		
		for(int i = 0; i<amount_of_edges; i++){
			temp_mas = matrix_of_graph.get(i).split(" ");
			coef_1 = Integer.parseInt(temp_mas[0])-1;
			coef_2 = Integer.parseInt(temp_mas[1])-1;
			result[coef_1][coef_2] = 1;
			result[coef_2][coef_1] = 1;
			}
		
		
		try {
			FileWriter writeFile = new FileWriter(new File("output.txt"));
			for(int i = 0; i<size_of_matrix; i++){
				for(int j = 0; j<size_of_matrix-1; j++){
					writeFile.write(""+result[i][j]+" ");
				}
				writeFile.write(""+result[i][size_of_matrix-1]+"\r\n");
			}
			writeFile.close();
		} catch (IOException e) {}
		
	}

}
