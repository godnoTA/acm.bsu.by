import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws IOException {
		String s1 = "input.txt";
		String s2 = "output.txt";
		BufferedReader buf1 = new BufferedReader(new FileReader(s1));
		BufferedWriter buf = new BufferedWriter(new FileWriter(s2));
		int number = Integer.parseInt(buf1.readLine());
		int[] heap = new int [number+1];
		int i = 1;
		Pattern p = Pattern.compile("-?[0-9]+");
		String str = buf1.readLine();
		Matcher m = p.matcher(str);
		while(m.find()){
			heap[i] = Integer.parseInt(str.substring(m.start(), m.end()));
			i++;
		}
		for(i = 1;i<=number+1;i++){
			if(2*i <= number){
				if(heap[i] > heap[2*i]){
					buf.write("No");
					buf.close();
					return;
				}
			}
			if(2*i+1 <= number){
				if(heap[i] > heap[2*i+1]){
					buf.write("No");
					buf.close();
					return;
				}
			}
		}
		buf.write("Yes");
		buf.close();
	}

}
