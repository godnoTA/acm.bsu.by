import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.PrintStream; 
import java.util.Arrays; 
import java.util.Scanner; 


public class IndTask { 
	public static void main(String[] args) throws FileNotFoundException { 
		Scanner in = new Scanner(new FileInputStream("input.txt")); 
		PrintStream out = new PrintStream(new FileOutputStream("output.txt")); 
		int N = in.nextInt(); 
		int[] a = new int[N]; 
		boolean flag = false; 
		int kol = 0; 
		while (in.hasNextInt()) 
		{ 
			int t = in.nextInt(); 
			if (t == 0) 
				flag = true; 
			else 
			{ 
				a[kol] = Math.abs(t); 
				kol++; 
				} 
			} 
		Arrays.sort(a, 0, kol); 
		int MAX_L = 0; 
		int[] z = new int[kol]; 
		for (int i = 0; i < kol; i++) 
		{ 
			int maxL = 0; 
			int j = 0; 
			while (j < i) 
			{ 
				if (a[i] % a[j] == 0) 
				{ 
					if (z[j] > maxL) 
						maxL = z[j]; 
					} 
				j++; 
				} 
			z[i] = maxL + 1; 
			if (z[i] > MAX_L) 
				MAX_L = z[i]; 
			} 
		if (flag) 
			MAX_L++; 
		out.println(MAX_L); 
		System.out.println(MAX_L); 
		in.close(); 
		out.close(); 
		System.exit(0); 
		} 
	}