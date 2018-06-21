using System;
using System.IO;
namespace vn
{
	class MainClass
	{
		public static void Main (string[] args)
		{

			StreamReader fo = new StreamReader ("input.txt");
			String[] s;
			s = fo.ReadLine ().Split ();
			int n = Convert.ToInt32 (s [0]);
			int m = Convert.ToInt32 (s [1]);

			long[,] matr = new long[n, m];

			for (int i = 0; i < n; i++) {
				s = fo.ReadLine ().Split ();
				for (int j = 0; j < m; j++)
					matr [i, j] = Convert.ToInt64 (s [j]);
			}

			fo.Close ();

			if (m != 1) {
				for (int i = 1; i < n; i++) {
					for (int j = 0; j < m; j++) {
						if (j == 0) {
							matr [i, j] += Math.Min (matr [i - 1, j], matr [i - 1, j + 1]);
						} else {
							if (j == m - 1) {
								matr [i, j] += Math.Min (matr [i - 1, j], matr [i - 1, j - 1]);
							} else {
								matr [i, j] += Math.Min (matr [i - 1, j], Math.Min (matr [i - 1, j + 1], matr [i - 1, j - 1]));
							}
						}
					}
				}

				long min = matr [n - 1, 0];
				for (int j = 1; j < m; j++) {
					if (matr [n - 1, j] < min)
						min = matr [n - 1, j];
				}

				FileStream fs = new FileStream ("output.txt", FileMode.OpenOrCreate);
				StreamWriter sw = new StreamWriter (fs);

				sw.WriteLine (min);
				//Console.WriteLine (min);
				sw.Close ();
			} else {
				long min=0;
				for (int i = 0; i < n; i++) {
						min += matr [i, 0];
				}

				FileStream fs = new FileStream ("output.txt", FileMode.OpenOrCreate);
				StreamWriter sw = new StreamWriter (fs);

				sw.WriteLine (min);
				sw.Close ();
			}
		}
	}
}
