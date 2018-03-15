using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alg_lab3
{
	class Program
	{
		static int?[] cache;
		static int f(int i, int[] a)
		{
			int result;
			if (i == 1)
			{
				result = a[0];
			}
			else
			{
				if (i == 2)
				{
					result = -1;
				}
				else
				{
					int first = cache[i - 3].HasValue ? cache[i - 3].Value : f(i - 2, a);
					int second = i > 3 ? cache[i - 4].HasValue ? cache[i - 4].Value : f(i - 3, a) : -1;
					if (first == -1 && second == -1)
					{
						result = -1;
					}
					else
					{
						result = Math.Max(first, second) + a[i - 1];
						cache[i - 1] = result;
					}
				}
			}
			return result;
		}
		static void Main(string[] args)
		{
			int n;
			int[] a;
			using (StreamReader sr = new StreamReader("input.txt"))
			{
				string str = sr.ReadLine();
				n = int.Parse(str);
				cache = new int?[n];
				str = sr.ReadLine();
				a = str.Split(new string[] { " "}, StringSplitOptions.RemoveEmptyEntries).Select(item => int.Parse(item)).ToArray();
			}
			var result = f(n, a);
			using (StreamWriter sw = new StreamWriter("output.txt"))
			{
				sw.WriteLine(result);
			}
		}
	}
}
