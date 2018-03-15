import java.io.*;
import java.util.*;





public class Main {
	
	static public void main(String[]args){
		int capacity = 0;
		List<String> matrix_of_graph= new ArrayList<String>();
		String str_test = null;
		try {
			Scanner sc = new Scanner(new File("input.txt"));
			capacity = sc.nextInt();
			while (sc.hasNextLine()) {
				if(!((str_test=sc.nextLine()).isEmpty()))
					matrix_of_graph.add(str_test);
			}
			sc.close();
		} catch (FileNotFoundException e) {};
		int mas[][]= new int[capacity][capacity];
		String temp_mas[]= new String[capacity];
		int result [] = new int[capacity];
		for(int i = 0; i<capacity; i++){
			temp_mas = matrix_of_graph.get(i).split(" ");
			for(int j = 0; j<capacity; j++){
				mas[i][j] = Integer.parseInt(temp_mas[j]);
			}
		}
		for(int i = 0; i<capacity; i++){
			for(int j = 0; j<capacity; j++){
				if(mas[j][i]!=0){
					result[i]=j+1;
					break;
				}
				result[i]=0;
			}
		}
		
		try {
			FileWriter writeFile = new FileWriter(new File("output.txt"));
			for(int i = 0; i<capacity-1; i++){
				writeFile.write(""+result[i]+" ");
			}
			writeFile.write(""+result[capacity-1]);
			writeFile.close();
		} catch (IOException e) {}
		
	}

}
