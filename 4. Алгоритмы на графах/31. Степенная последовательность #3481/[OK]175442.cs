using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace Alg_Lab6
{
	class Program
	{
		static void Main(string[] args)
		{
			int n, m;
			List<int> powers;
			using (StreamReader sr = new StreamReader("input.txt"))
			{
				var numbers = sr.ReadLine().Split(' ');
				n = int.Parse(numbers[0]);
				m = int.Parse(numbers[1]);
				powers = Enumerable.Repeat(0, n).ToList();
				for(int i = 0; i < m; i++)
				{
					numbers = sr.ReadLine().Split(' ');
					powers[int.Parse(numbers[0]) - 1]++;
					powers[int.Parse(numbers[1]) - 1]++;
				}
			}
			powers.Sort((a, b) => b - a);
			using (StreamWriter sw = new StreamWriter("output.txt"))
			{
				for(int i = 0; i < n - 1; i++)
				{
					sw.Write(string.Format("{0} ", powers[i]));
				}
				sw.Write(powers[n - 1]);
			}
		}
	}
}
