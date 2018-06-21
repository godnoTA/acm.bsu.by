using System;
using System.IO;

namespace Algo2
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			StreamReader fo = new StreamReader ("input.txt");
			int[,] matr;
			int[] mas;
			int n = Convert.ToInt32 (fo.ReadLine ());

			matr = new int[n, n];
			mas = new int[n];

			String[] s;
		
			for (int i = 0; i < n; i++) {
				s = fo.ReadLine ().Split (' ');
				for (int j = 0; j < n; j++) {
					if (s [j] != "0") {
						mas [j] = i+1;
					}
				}
			}

			fo.Close ();
			FileStream fs = new FileStream ("output.txt", FileMode.OpenOrCreate);
			StreamWriter sw = new StreamWriter (fs);

			for (int i = 0; i < n; i++)
					sw.Write (mas [i]+" ");

			sw.Close ();
		}
	}
}
