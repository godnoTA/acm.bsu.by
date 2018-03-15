using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace Alg_Lab7
{
	class Program
	{
		static void Main(string[] args)
		{
			int n, m, x, y;
			bool isGraph = true, isPsevdoGraph = true, isMultiGraph = true;
			int[,] matrix;
			using (StreamReader sr = new StreamReader("input.txt"))
			{
				var numbers = sr.ReadLine().Split(' ');
				n = int.Parse(numbers[0]);
				m = int.Parse(numbers[1]);
				matrix = new int[n, n];
				for (int i = 0; i < m; i++)
				{
					numbers = sr.ReadLine().Split(' ');
					x = int.Parse(numbers[0]) - 1;
					y = int.Parse(numbers[1]) - 1;
					if (x == y)
					{
						isGraph = false;
						isMultiGraph = false;
						break;
					}
					else
					if (matrix[x, y] == 1 || matrix[y, x] == 1)
					{
						isGraph = false;
					}
					matrix[x, y] = 1;
					matrix[y, x] = 1;
				}
			}
			using (StreamWriter sw = new StreamWriter("output.txt"))
			{
				sw.WriteLine(isGraph ? "Yes" : "No");
				sw.WriteLine(isMultiGraph ? "Yes" : "No");
				sw.WriteLine(isPsevdoGraph ? "Yes" : "No");
			}
		}
	}
}