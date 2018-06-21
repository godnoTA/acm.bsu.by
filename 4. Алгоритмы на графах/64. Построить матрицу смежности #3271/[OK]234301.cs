using System;
using System.IO;

namespace Algo
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			StreamReader fo = new StreamReader ("input.txt");
			int[,] matr;
			String[] s = fo.ReadLine ().Split (' ');
			int n = Convert.ToInt32 (s [0]);
			int m = Convert.ToInt32 (s [1]);
			matr = new int[n, n];
			int i, j;
			for (int k = 0; k < m; k++) {
				s = fo.ReadLine ().Split (' ');
				i = Convert.ToInt32 (s [0]);
				j = Convert.ToInt32 (s [1]);
				matr [i - 1, j - 1] = matr [j - 1, i - 1] = 1;
			}

			fo.Close ();
			FileStream fs = new FileStream ("output.txt", FileMode.OpenOrCreate);
			StreamWriter sw = new StreamWriter (fs);

			for (i = 0; i < n; i++){
				for (j = 0; j < n; j++) {
					sw.Write (matr [i, j]+" ");
				}
			sw.WriteLine ();
		}

			sw.Close ();
		}
	}
}
