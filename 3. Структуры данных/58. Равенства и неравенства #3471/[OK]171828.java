import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class test 
{
	static int mas[];
	static int size[];

	static void make_set (int key)
	{
		mas[key] = key;
		size[key] = 1;
	}
	static int find_set (int key) 
	{
		if (key == mas[key])
			return key;
		return mas[key] = find_set (mas[key]);
	}
	static void union_sets (int a, int b) 
	{
		a = find_set (a);
		b = find_set (b);
		if (a != b) 
		{
			if (size[a] < size[b])
			{
				int temp;
				temp=a;
				a=b;
				b=temp;
			}
			mas[b] = a;
			size[a] += size[b];
		}
	}
	public static void main(String[] args)throws FileNotFoundException
	{
		Scanner in = new Scanner (new File("equal-not-equal.in"));
		PrintWriter out = new PrintWriter(new File("equal-not-equal.out"));
		int n = in.nextInt();
		int m = in.nextInt();
		ArrayList<String> equals=new ArrayList<String>();
		ArrayList<String> notEquals=new ArrayList<String>();
		in.nextLine();
		mas = new int[n+1];
		size = new int[n+1];
		for(int i=0;i<m;i++)
		{
			String temp = in.nextLine();
			String[] s=temp.split(" ");
			
			int one=Integer.valueOf(s[0].substring(1));
			int two=Integer.valueOf(s[2].substring(1));
			if(s[1].equals("=="))
				equals.add(temp);
			else
			{
				if(n==1)
				{
					out.println("No");
					out.close();
					System.exit(0);
				}
				notEquals.add(temp);
			}
			make_set(one);
			make_set(two);
		}
		for(int j=0;j<equals.size();j++)
		{
			String[]s=equals.get(j).split(" ");
			int one=Integer.valueOf(s[0].substring(1));
			int two=Integer.valueOf(s[2].substring(1));
			union_sets(one,two);
		}
		for(int j=0;j<notEquals.size();j++)
		{
			String[]s=notEquals.get(j).split(" ");
			int one=Integer.valueOf(s[0].substring(1));
			int two=Integer.valueOf(s[2].substring(1));
			if(find_set(one)==find_set(two))
			{
				out.println("No");
				out.close();
				System.exit(0);
			}
		}
		out.println("Yes");
		out.close();
	}
}