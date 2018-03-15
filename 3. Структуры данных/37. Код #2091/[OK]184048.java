import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader buf1 = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter buf2 = new BufferedWriter(new FileWriter("output.txt"));
		String str = buf1.readLine();
		StringTokenizer st = new StringTokenizer(str);
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int fullSquareNumber = 0;
		int setsSystem[] = new int [N + 1];
		int set1, set2;
		for(int i = 1;i <= N;i++){
			setsSystem[i] = -1;
		}
		int num1, num2;
		for(int i = 1;i <= R;i++){
			str = buf1.readLine();
			st = new StringTokenizer(str);
			num1 = Integer.parseInt(st.nextToken());
			num2 = Integer.parseInt(st.nextToken());
			set1 = find(setsSystem, num1);
			set2 = find(setsSystem, num2);
			if(set1 == set2){
				fullSquareNumber++;
			}
			else {
				if(setsSystem[set1] >= setsSystem[set2]){
					setsSystem[set2] += setsSystem[set1];
					setsSystem[set1] = set2;
				}
				if(setsSystem[set1] < setsSystem[set2]){
					setsSystem[set1] += setsSystem[set2];
					setsSystem[set2] = set1;
				}
			}
		}
		buf2.write(Integer.toString(fullSquareNumber));
		buf1.close();
		buf2.close();
	}
	static int find(int[] setsSystem, int num){
		int pos = 0;
		while(num > 0){
			pos = num;
			num = setsSystem[pos];
		}
		return pos;
	}
	static void link(int[] setsSystem, int num1, int num2){
		setsSystem[find(setsSystem,num2)] = find(setsSystem,num1);
	}
}
