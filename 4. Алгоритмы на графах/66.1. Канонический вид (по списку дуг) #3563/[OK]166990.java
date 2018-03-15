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
		String temp_mas[]= new String[2];
		int result [] = new int[capacity];
		int coef, value;
		for(int i = 0; i<capacity-1; i++){
			temp_mas = matrix_of_graph.get(i).split(" ");
			value = Integer.parseInt(temp_mas[0]);
			coef = Integer.parseInt(temp_mas[1])-1;
			result[coef] = value;
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
