using System;
using System.Collections.Generic;
using System.Collections;
using System.IO;

namespace Mister_Huffman
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			StreamReader fo = new StreamReader ("huffman.in");
			long summ = 0;
			int n = Convert.ToInt32(fo.ReadLine ());

			long[] mass =new long[n]; 
			List<long> secondCollection = new List<long> ();

			String[] buff = fo.ReadLine ().Split (' ');

			for (int i = 0; i < n; i++) {
				mass[i]=(Convert.ToInt64(buff[i]));
			}
			fo.Close ();

			Array.Sort (mass);

			long a, b;
			int size = n;

			a = mass [0];
			b = mass [1];

			int I = 0, J = 0;

			long micro = a + b;
			summ += micro;
			secondCollection.Add(micro);

			I += 2;

			//foreach (long h in secondCollection)
			//	Console.WriteLine (h);

			while(secondCollection.Count!=n-1)
			{
				micro = 0;
				if (I < n) {
					for (int i = 0; i < 2; i++) {
						if (I == n) {
							micro += secondCollection [J];
							J++;
						} else {
							if (J == secondCollection.Count) {
								micro += mass [I];
								I++;
							}
							else{
								if (mass [I] < secondCollection [J]) {
									micro += mass [I];
									I++;
								} else {
									micro += secondCollection [J];
									J++;
								}
							}
						}
					}
					summ += micro;
					secondCollection.Add (micro);
				} else {
					micro = secondCollection [J] + secondCollection [J + 1];
					J+=2;

					summ += micro;
					secondCollection.Add (micro);
				}
			}

			FileStream fs = new FileStream ("huffman.out",FileMode.Create);
			StreamWriter sw = new StreamWriter (fs);
			sw.WriteLine (summ);
			//Console.WriteLine (summ);
			sw.Close ();
			fs.Close ();
		}
	}
}
