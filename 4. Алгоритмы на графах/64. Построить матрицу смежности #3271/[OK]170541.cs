using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alg_common8
{
	class Program
	{
		static void Main(string[] args)
		{
			int n, m;
			int[,] graph;
			using (StreamReader sr = new StreamReader("input.txt"))
			{
				string str = sr.ReadLine();
				var sizes = str.Split(' ').Select(x => int.Parse(x)).ToArray();
				n = sizes[0];
				m = sizes[1];
				graph = new int[n, n];
				for (int i = 0; i < n; i++)
				{
					for (int j = 0; j < n; j++)
					{
						graph[i, j] = 0;
					}
				}
				for (int i = 0; i < m; i++)
				{
					str = sr.ReadLine();
					var edge = str.Split(' ').Select(x => int.Parse(x)).ToArray();
					graph[edge[0] - 1, edge[1] - 1] = 1;
					graph[edge[1] - 1, edge[0] - 1] = 1;
				}
			}
			using (TextWriter tw = new StreamWriter("output.txt"))
			{
				for (int i = 0; i < n; i++)
				{
					for (int j = 0; j < n; j++)
					{
						tw.Write(graph[i, j] + " ");
					}
					tw.WriteLine();
				}
			}
		}
	}
}
