import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main implements Runnable{
	
	public static boolean isBin(long[] mas)
	{
		if(mas.length==1)
			return true;
		for(int i=0;i<=(mas.length-2)/2;i++)
		{
				if(mas[i]>mas[2*i+1])
					return false;
				if(mas[i]>mas[2*i+2])
						return false;
		}
		return true;
	}
	
	public static void main(String[] args)
	{
		new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		File f = new File("input.txt");
		int n;
		long[] bin;
		try
		{
		Scanner sc = new Scanner(f);
		n = sc.nextInt();
		if(n%2==0)
			n++;
		bin = new long[n];
		for(int i = 0; i < n; ++i)
		{ 
			if(sc.hasNextLong())
				bin[i]=sc.nextLong();
			else
				bin[i]=Long.MAX_VALUE;
		}
		PrintWriter q = new PrintWriter(new File("output.txt"));
		if(isBin(bin))
			q.print("Yes");
		else
			q.print("No");
		q.flush();
		}
		catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
}