using System;
using System.IO;
using System.Collections.Generic;

namespace MultyPlyOrder
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			StreamReader fo = new StreamReader ("input.txt");
			String[] s;
			int n = Convert.ToInt32 (fo.ReadLine ());

			int[] dims = new  int[n + 1];

			s = fo.ReadLine ().Split ();

			dims[0]=Convert.ToInt32 (s[0]);
			dims[1]=Convert.ToInt32 (s[1]);

			for (int i = 2; i < n+1; i++) {
				s = fo.ReadLine ().Split ();
				dims[i]=Convert.ToInt32 (s[1]);
			}

			fo.Close ();

			int[,] matrix = new int[dims.Length,dims.Length];



			int j;
			for (int l = 1; l < n; l++)
				for (int i = 0; i < n - l; i++) {
					j = i + l;
					matrix[i,j] = int.MaxValue;
					for (int k = i; k < j; k++) {

						int q = matrix[i,k] + matrix[k + 1,j] +
							dims[i] * dims[k + 1] * dims[j + 1];
						if (q < matrix[i,j]) {
							matrix[i,j] = q;
						}
					}
				}

			for (int i = 0; i < n+1; i++) {
				for (j = 0; j < n+1; j++)
					Console.Write (matrix[i,j]+" ");
				Console.WriteLine ();
			}

			FileStream fs = new FileStream ("output.txt", FileMode.OpenOrCreate);
			StreamWriter sw = new StreamWriter (fs);

			sw.WriteLine (matrix[0,n-1]);
			sw.Close ();
		}
	}
}
