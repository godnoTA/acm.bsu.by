using System.Collections.Generic;
using System.IO;

namespace Alg_Lab5
{
	class Program
	{
		static void Main(string[] args)
		{
			int n;
			int command;
			bool result = false;
			string[] numbers;
			var dx = new int[] { 1, 1, 0, -1, -1, -1, 0, 1 };
			var dy = new int[] { 0, 1, 1, 1, 0, -1, -1, -1 };
			int x = 0, y = 0;
			Dictionary<int, HashSet<int>> steps = new Dictionary<int, HashSet<int>>();
			steps.Add(x, new HashSet<int>() { y });
			using (StreamReader sr = new StreamReader("in.txt"))
			{
				n = int.Parse(sr.ReadLine());
				numbers = sr.ReadLine().Split(' ');
			}
			for (int i = 0; i < n; i++)
			{
				command = int.Parse(numbers[i]);
				x += dx[command];
				y += dy[command];
				if (steps.ContainsKey(x))
				{
					if (steps[x].Contains(y))
					{
						result = true;
						break;
					}
					else
					{
						steps[x].Add(y);
					}
				}
				else
				{
					steps.Add(x, new HashSet<int>() { y });
				}

			}
			using (StreamWriter sw = new StreamWriter("out.txt"))
			{
				if (result)
				{
					sw.WriteLine("Yes");
				}
				else
				{
					sw.WriteLine("No");
				}
			}
		}
	}
}
