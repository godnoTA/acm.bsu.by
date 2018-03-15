import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class PhoneNumber {

	private static Map<Character, Character> alphabet = new TreeMap<Character, Character>();
	public static String number;
	public static int N;
	public static String[][] words;
	public static int kol = 0;
	
	public static String Analysing(){
		
		int j = 0;
		String str = "";
		int[] wordLast = new int [number.length()];
		int[] wordAmount = new int [number.length()];
		for (int i = 0; i < number.length(); i++) {
			wordLast[i] = wordAmount[i] = -1;
			wordAmount[i] = -1;
		}
		
		for (int m = 0; m < number.length(); m++)
		{
			for (int i = 0; i < N; i++)
			{
				int a = -2;
				if (m >= words[i][1].length() - 1)
				{
					if (!words[i][1].equals(number.substring(m - words[i][1].length() + 1, m + 1)))
						a = -1;
					else
						a = m;
				}
				
				if (a > -1)
				{
					if (wordLast[a] == -1)
						wordLast[a] = i;
					else
					{
						if (a - words[wordLast[a]][1].length() >= 0 && wordAmount[a -  words[wordLast[a]][1].length()] == -1)
							wordLast[a] = i;
						else
							if (a - words[i][1].length() >= 0 && wordAmount[a -  words[i][1].length()] == -1)
								break;
							else
								wordLast[a] = ((a - words[wordLast[a]][1].length() == -1 ||(a - words[wordLast[a]][1].length() >= 0 
								&& a - words[i][1].length() >= 0 
								&& wordAmount[a - words[wordLast[a]][1].length()] < wordAmount[a - words[i][1].length()])) 
								? (wordLast[a]) : (i)); 
						}
					
					if (wordLast[a] == i) { 
						if (a - words[i][1].length() == -1) { 
							j = 1; 
							wordAmount[a] = j; 
						} 
						else if (a - words[i][1].length() >= 0 && wordAmount[a - words[i][1].length()] != -1) { 
							j = wordAmount[a -words[i][1].length()]; 
							j++; 
							wordAmount[a] = j; 
						} 
					} 
				}
			}
		}

		for (int i = wordAmount.length - 1; i >= 0; i--) { 
			if (wordAmount[i] == -1)
				return "No solution";
			else if (((i - words[wordLast[i]][1].length()) == -1 || i == 0) && wordAmount[i] == 1) { 
				str = words[wordLast[i]][0] + str; 
				kol++;
				return str; 
			}else if (i - words[wordLast[i]][1].length() >= 0 && wordAmount[i] - 1 == (wordAmount[i - words[wordLast[i]][1].length()])) { 
				str = " " + (words[wordLast[i]][0]) + str; 
				i -= words[wordLast[i]][1].length() - 1; 
				kol++;
			}else { 
				return "No solution";
			} 
		}
		return str;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new FileInputStream("in.txt"));
		PrintStream out = new PrintStream(new FileOutputStream("out.txt"));
		
		alphabet.put('I', '1');
		alphabet.put('J', '1');
		alphabet.put('A', '2');
		alphabet.put('B', '2');
		alphabet.put('C', '2');
		alphabet.put('D', '3');
		alphabet.put('E', '3');
		alphabet.put('F', '3');
		alphabet.put('G', '4');
		alphabet.put('H', '4');
		alphabet.put('K', '5');
		alphabet.put('L', '5');
		alphabet.put('M', '6');
		alphabet.put('N', '6');
		alphabet.put('P', '7');
		alphabet.put('R', '7');
		alphabet.put('S', '7');
		alphabet.put('T', '8');
		alphabet.put('U', '8');
		alphabet.put('V', '8');
		alphabet.put('W', '9');
		alphabet.put('X', '9');
		alphabet.put('Y', '9');
		alphabet.put('O', '0');
		alphabet.put('Q', '0');
		alphabet.put('Z', '0');
		
		number = in.nextLine();
		N = Integer.parseInt(in.nextLine());
		words = new String[N][2];
		int i = 0;
		while (in.hasNextLine()) {
			words[i][0] = in.nextLine();
			words[i][1] = Conversion(words[i][0]);
			i++;
		}
		
		String result = PhoneNumber.Analysing();
		if (PhoneNumber.kol != 0)
			out.println(PhoneNumber.kol);
		out.println(result);
		
		in.close();
		out.close();
		System.exit(0);
	}
	
	public static String Conversion(String w) {
		int i = 0;
		String res = "";
		while(i < w.length()) {
			char ch = w.charAt(i);
			if (ch >= '0' && ch <= '9')
				res += ch;
			else
				res += alphabet.get(ch);
			i++;
		}
		return res;
	}

}
