using System;
using System.Collections.Generic;
using System.IO;

namespace Alg_common4
{

	class Program
	{
		public static void CheckIfBinomialHeap(string s)
		{
			using (StreamWriter sw = new StreamWriter("output.txt"))
			{
				for(int i = s.Length - 1; i >= 0; i--) {

					 if (s[i] == '1')
					{
						sw.WriteLine(s.Length - i - 1);
					}
				}
			}
		}
		static void Main(string[] args)
		{
			long n;
			using (StreamReader sr = new StreamReader("input.txt"))
			{
				string str;
				str = sr.ReadLine();
				n = long.Parse(str);
			}
			string s = Convert.ToString(n, 2);
			CheckIfBinomialHeap(s);
		}
	}
}
