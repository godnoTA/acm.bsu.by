using System;
using System.IO;

namespace multiPsevdo
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			StreamReader fo = new StreamReader ("input.txt");
			String[] s;
			s = fo.ReadLine ().Split();
			int n = Convert.ToInt32 (s [0]);
			int m = Convert.ToInt32 (s [1]);

			bool just=true, multi=true, psevdo = true;
			bool[,]matrix=new bool[n,n];

			for (int i = 0; i < m; i++) {
				s = fo.ReadLine ().Split();
				int u = Convert.ToInt32 (s [0]);
				int v = Convert.ToInt32 (s [1]);

				if (u == v) {
					just = false;
					multi = false;
					psevdo = true;
					break;
				} else {
					if (matrix [u - 1, v - 1]) {
						just = false;
						multi = true;
						psevdo = true;
					}
					else
						matrix [u - 1, v - 1] = matrix [v - 1, u - 1] = true;
				}
			}

			fo.Close ();
			FileStream fs = new FileStream ("output.txt",FileMode.OpenOrCreate);
			StreamWriter sw = new StreamWriter (fs);

			if (just)
				sw.WriteLine ("Yes");
			else
				sw.WriteLine ("No");

			if (multi)
				sw.WriteLine ("Yes");
			else
				sw.WriteLine ("No");

			if (psevdo)
				sw.WriteLine ("Yes");
			else
				sw.WriteLine ("No");
			
			sw.Close ();
		}
	}
}
