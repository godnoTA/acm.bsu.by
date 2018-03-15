using System;
 using System.IO;
using System.Linq;
using System.Text;

namespace Alg_common5
{
	class Program
	{
		public static int Hash(int x, int i, int m, int c)
		{
			int res = (x % m + c * i) % m;
			return res;
		}
		static void Main(string[] args)
		{
			int m, c, n;
			int?[] hashTable;
			int[] fromFile;
			int[] keys;
			using (StreamReader sr = new StreamReader("input.txt"))
			{
				string str = sr.ReadToEnd();
				fromFile = str.Split(new string[] { " ", "\r\n" }, StringSplitOptions.RemoveEmptyEntries).Select(item => int.Parse(item)).ToArray();
			}
			m = fromFile[0];
			c = fromFile[1];
			n = fromFile[2];
			keys = fromFile.Skip(3).Distinct().ToArray();
			int hash, count = 0;
			hashTable = new int?[m];
			for (int i = 0; i < keys.Count(); i++)
			{
				count = 0;
				do
				{
					hash = Hash(keys[i], count, m, c);
					count++;
				} while (hashTable[hash].HasValue);
				hashTable[hash] = keys[i];
			}
			StringBuilder result = new StringBuilder();

			for (int i = 0; i < m; i++)
			{
				result.Append(hashTable[i].HasValue ? hashTable[i].Value : -1);
				if (i != m - 1)
				{
					result.Append(" ");
				}
			}

			using (StreamWriter sw = new StreamWriter("output.txt"))
			{
				sw.WriteLine(result);
			}
		}
	}
}
