import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) throws IOException {
		String s1 = "input.txt";
		String s2 = "output.txt";
		BufferedReader buf1 = new BufferedReader(new FileReader(s1));
		BufferedWriter buf2 = new BufferedWriter(new FileWriter(s2));
		int count = Integer.parseInt(buf1.readLine());
		if(count == 0){
			buf2.write("0");
			buf1.close();
			buf2.close();
			return;
		}
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		Pattern p = Pattern.compile("-?[0-9]+");
		String str = buf1.readLine();
		Matcher m = p.matcher(str);
		while(m.find()){
			numbers.add(Integer.parseInt(str.substring(m.start(), m.end())));
		}
		Comparator <Integer> cmp = new Comparator <Integer>(){
			@Override
			public int compare(Integer x, Integer y){
				return(Math.abs(x) > Math.abs(y))? 1:(Math.abs(x) < Math.abs(y))?-1:0;
			}
		};
		Collections.sort(numbers,cmp);
		int maxLength = 0;
		int j = 0;
		if(numbers.get(0) == 0){
			maxLength++;
			while(numbers.get(j)==0){
				j++;
				if(j==count){
					buf2.write("1");
					buf1.close();
					buf2.close();
					return;
				}
			}
		}
		int[] lengths = new int[count];
		lengths[j] = 1;
		for(int i = j+1;i<count;i++){
			lengths[i] = 1;
			for(int k = i-1; k >= j;k--){
				if(numbers.get(i) % numbers.get(k) == 0){
					lengths[i] = Math.max(lengths[i], 1 + lengths[k]);
				}
			}
		}
		int max = 0;
		for(int i = 0;i < count;i++){
			if(max < lengths[i]){
				max = lengths[i];
			}
		}
		maxLength += max;
		buf2.write(Integer.toString(maxLength));
		buf1.close();
		buf2.close();
	}
}
