import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("input.txt"));
		int N = scan.nextInt();
		int array[] = new int[N + 1];
		int num1, num2;
		for(int i = 1;i <= N - 1;i++){
			num1 = scan.nextInt();
			num2 = scan.nextInt();
			array[num2] = num1;
		}
		BufferedWriter buf = new BufferedWriter(new FileWriter("output.txt"));
		for(int i = 1;i <= N;i++){
			buf.write(Integer.toString(array[i]) + " ");
		}
		buf.close();
	}
}
