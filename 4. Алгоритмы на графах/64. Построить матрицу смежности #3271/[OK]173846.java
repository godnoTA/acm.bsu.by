import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws IOException {
		String s1 = "input.txt";
		String s2 = "output.txt";
		BufferedReader buf1 = new BufferedReader(new FileReader(s1));
		BufferedWriter buf2 = new BufferedWriter(new FileWriter(s2));
		Pattern p = Pattern.compile("[0-9]+");
		String str = buf1.readLine();
		Matcher m = p.matcher(str);
		m.find();
		int topNumber = Integer.parseInt(str.substring(m.start(), m.end()));
		m.find();
		int edgeNumber = Integer.parseInt(str.substring(m.start(), m.end()));
		int matrix[][] = new int[topNumber][topNumber];
		int a,b;
		for(int i = 0;i < edgeNumber;i++){
			str = buf1.readLine();
			m = p.matcher(str);
			m.find();
			a = Integer.parseInt(str.substring(m.start(), m.end()));
			m.find();
			b = Integer.parseInt(str.substring(m.start(), m.end()));
			matrix[a - 1][b - 1] = 1;
			matrix[b - 1][a - 1] = 1;
		}
		for(int i = 0;i < topNumber;i++){
			for(int j = 0;j < topNumber;j++){
				buf2.write(Integer.toString(matrix[i][j]) + " ");
			}
			buf2.newLine();
		}
		buf1.close();
		buf2.close();
	}

}
