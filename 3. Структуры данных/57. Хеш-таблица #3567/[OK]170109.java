import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Test_57_1 {
	static int[] array;
	static boolean Search(int x){
		for(int i = 0;i < array.length; i ++){
			if(array[i] == x)
				return true;
		}
		return false;
	}
	public static void main(String[] args){
		try
		{
			Scanner sc = new Scanner(new File("input.txt"));
			int m, c, N;
			m = sc.nextInt();
			c = sc.nextInt();
			N = sc.nextInt();
			array = new int[m];
			for(int i = 0;i < m;i ++)
				array[i] = -1;
			int i = 0;
			int count = 1;
			while(count <= N)
			{
				count ++;
				int num = sc.nextInt();
				i = 0;
				int idx = ((num % m) + c * i) % m;
				if(Search(num) == false){
					while(array[idx] != -1){
						i++;
						idx = ((num % m) + c * i) % m;

					}
					array[idx] = num;
					//i ++;
				}
			}
			sc.close();
			FileWriter out = new FileWriter("output.txt");
			for(int j = 0;j < m;j ++)
			{
				out.write(Integer.toString(array[j]) + " ");

			}
			out.close();
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
		}
		catch(IllegalArgumentException ex)
		{
			System.out.println(ex.getMessage());
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
}
