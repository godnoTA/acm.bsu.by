import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		String s1 = "input.txt";
		String s2 = "output.txt";
		BufferedReader buf1 = new BufferedReader(new FileReader(s1));
		BufferedWriter buf2 = new BufferedWriter(new FileWriter(s2));
		long number = Long.parseLong(buf1.readLine());
		int i = 0;
		while(number > 0){
			if(number % 2 == 1){
				buf2.write(Long.toString(i));
				buf2.newLine();
				number /= 2;
				i++;
			}
			else{
				i++;
				number /= 2;
			}
		}
		buf1.close();
		buf2.close();
	}

}
