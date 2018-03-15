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
		int number = Integer.parseInt(buf1.readLine());
		int matrix [][] = new int [number+1][2];
		String str;
		Pattern p = Pattern.compile("[0-9]+");
		int k = 0;
		for(int i = 1; i < number+1;i++){
			str = buf1.readLine();
			Matcher m = p.matcher(str);
			while(m.find()){
				matrix[i][k] = Integer.parseInt(str.substring(m.start(), m.end()));
				k++;
			}
			k = 0;
		}
		if(number == 2){
			buf2.write(Integer.toString(matrix[1][0]*matrix[1][1]*matrix[2][1]));
			buf1.close();
			buf2.close();
			return;
		}
		int solutions [][] = new int [number+1][number+1];
		for(int i = 1; i < number;i++){
			solutions[i][i] = 0;
		}
		int l = 0;
		for(int i = 1; i <= number;i++){
			for(int j = 1;j <= number - i;j++){
				l = i + j;
				int kol = Integer.MAX_VALUE;
				for(k = j; k <= l - 1;k++){
				if(kol >solutions[j][k] + solutions[k+1][l] + matrix[j][0]*matrix[k][1]*matrix[l][1]){
					kol = solutions[j][k] + solutions[k+1][l] + matrix[j][0]*matrix[k][1]*matrix[l][1];
				}
				solutions[j][l]=kol;
			}
			}
		}
		buf2.write(Integer.toString(solutions[1][number]));
		buf1.close();
		buf2.close();
	}
}
