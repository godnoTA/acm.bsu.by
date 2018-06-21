using System;
using System.IO;

namespace DFS
{
	class MainClass
	{
		public static int I=1;
		public static int n;

		public static bool[]flags; 

		public static void recursion(int [,]matr,int []res,int i)
		{
			for(int j=0;j<n;j++)
				if (matr [i,j] == 1 && !flags[j]) {
					flags [j] = true;
					res [j] = I;
					I++;
					//Console.WriteLine (I+" - I");
					recursion (matr, res, j);
				}
		}

		public static void Main (string[] args)
		{
			StreamReader fo = new StreamReader ("input.txt");
			n = Convert.ToInt32 (fo.ReadLine());
			int [,]matr=new int[n,n];
			int []result=new int[n];
			flags=new bool[n];
			String[] s;
			for (int i = 0; i < n; i++) {
				s = fo.ReadLine ().Split ();
				for (int j = 0; j < n; j++)
					matr [i, j] = Convert.ToInt32 (s[j]);
			}
			fo.Close ();

			for (int i = 0; i < n; i++) {
				if (!flags [i]) {
					result [i] = I++;
					flags [i] = true;
					recursion (matr, result, i);
				}
			}
			fo.Close ();

			FileStream fs = new FileStream ("output.txt", FileMode.OpenOrCreate);
			StreamWriter sw = new StreamWriter (fs);

			foreach (int u in result){
				sw.Write (u+" ");
			}
			sw.Close ();
		}
	}
}
