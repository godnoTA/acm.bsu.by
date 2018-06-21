using System;
using System.IO;
using System.Collections.Generic;
namespace SpisokSmeg
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
			List<int> []mass=new List<int>[n];

			for(int i=0;i<n;i++)
				mass[i] = new List<int> ();

			int u, v;

			for (int i = 0; i < m; i++) {
				s = fo.ReadLine ().Split();

				u=Convert.ToInt32 (s [0]);
				v=Convert.ToInt32 (s [1]);

				mass[u-1].Add(v);
				mass[v-1].Add(u);
			}

			fo.Close ();
			FileStream fs = new FileStream ("output.txt", FileMode.OpenOrCreate);
			StreamWriter sw = new StreamWriter (fs);

			for (int i = 0; i < n; i++){
				sw.Write (mass[i].Count+" ");
					foreach (int y in mass[i])
						sw.Write (y + " ");
					sw.WriteLine ();
				}
			sw.Close ();
		}
	}
}
