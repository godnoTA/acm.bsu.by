using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alg_common9
{
	class Program
	{
		static void InitGraph(int n, Dictionary<int, List<int>> graph)
		{
			for (int i = 0; i < n; i++)
			{
				graph.Add(i + 1, new List<int>());
			}
		}
		static void Main(string[] args)
		{
			int n, m;
			Dictionary<int, List<int>> graph = new Dictionary<int, List<int>>();
			using (StreamReader sr = new StreamReader("input.txt"))
			{
				string str = sr.ReadLine();
				var sizes = str.Split(' ').Select(x => int.Parse(x)).ToArray();
				n = sizes[0];
				m = sizes[1];
				InitGraph(n, graph);
				for (int i = 0; i < m; i++)
				{
					str = sr.ReadLine();
					var edge = str.Split(' ').Select(x => int.Parse(x)).ToArray();
					graph[edge[0]].Add(edge[1]);
					graph[edge[1]].Add(edge[0]);
				}
			}
			using (TextWriter tw = new StreamWriter("output.txt"))
			{
				for (int i = 0; i < n; i++)
				{
					tw.Write(graph[i + 1].Count + " ");
					for (int j = 0; j < graph[i + 1].Count; j++)
					{
						tw.Write(graph[i + 1][j] + " ");
					}
					tw.WriteLine();
				}
			}
		}
	}
}
