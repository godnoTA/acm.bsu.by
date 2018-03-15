import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Class {
	public static void main(String[] args) throws IOException {
		 File file = new File("input.txt");
		 File file1 = new File("output.txt");
		 BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
		 PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
		 String s;
		 int p;
		 long otvet=0;
		 ArrayList<Integer> arr=new ArrayList<Integer>();
		 while ((s = in.readLine()) != null){
			 p=Integer.parseInt(s);
			 if(!arr.contains(p)){
				 arr.add(p);
			 }
         }
		 for(int i=0;i<arr.size();i++){
			 otvet+=arr.get(i);
		 }
		 System.out.println("Otvet "+otvet);
		 out.println(otvet);
		 in.close();
		 out.close();
	}
}
