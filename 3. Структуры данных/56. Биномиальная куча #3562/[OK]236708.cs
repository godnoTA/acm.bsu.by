using System;
using System.IO;
using System.Collections.Generic;
namespace BinarHeap
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			StreamReader fo = new StreamReader ("input.txt");
			long n = Convert.ToInt64 (fo.ReadLine ());
			List<long>mass = new List<long>();
			fo.Close ();

			String s= (Convert.ToString(n,2));
			for (int i = s.Length-1; i >= 0; i--) {
				if (s[i] == '1')
					mass.Add(s.Length - 1 - i);
			}

			FileStream fs = new FileStream ("output.txt", FileMode.OpenOrCreate);
			StreamWriter sw = new StreamWriter (fs);

			foreach (long d in mass)
				sw.WriteLine (d);
			sw.Close ();
		}

	}
}
