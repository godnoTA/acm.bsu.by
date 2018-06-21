using System;
using System.IO;

namespace qwert
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			StreamReader fo = new StreamReader ("input.txt");
			int[] mas;
			int n = Convert.ToInt32 (fo.ReadLine ());

			mas = new int[n];

			for (int i = 0; i < n-1; i++) {
				String[] ss = fo.ReadLine ().Trim ().Split (' ');
				mas [Convert.ToInt32 (ss [1]) - 1] = Convert.ToInt32 (ss [0]);
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
