using System;
using System.IO;
namespace Heap
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			StreamReader fo = new StreamReader ("input.txt");
			FileStream fs = new FileStream ("output.txt", FileMode.OpenOrCreate);
			StreamWriter sw = new StreamWriter (fs);

			long n = Convert.ToInt64 (fo.ReadLine());
			long []mas = new long[n];

			String[] buff = fo.ReadLine ().Split ();
			fo.Close ();
			for (int i = 0; i < n; i++)
				mas [i] = Convert.ToInt64 (buff[i]);
			
			for (int i = 0; i < n; i++) {
				long left = 2 * i + 1;
				long right = 2 * i + 2;
				if (left < n) {
					if (mas [left] < mas [i]) {
						sw.WriteLine ("No");
						sw.Close ();
						return;
					}
				}
				if (right < n) {
					if (mas [right] < mas [i]) {
						sw.WriteLine ("No");
						sw.Close ();
						return;
					}
				}
			}
			sw.WriteLine ("Yes");
			sw.Close ();
		}
	}
}
