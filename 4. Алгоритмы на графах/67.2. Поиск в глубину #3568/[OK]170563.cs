using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alg_common13
{
	class Program
	{
		static int[] StartDFS(int n, int[,] graph)
		{
			List<int> used = new List<int>();
			int[] marks = new int[n];
			int mark = 1;
			DFS(0, n, graph, used, marks, ref mark);
			for (int i = 0; i < n; i++)
			{
				if (marks[i] == 0)
				{
					DFS(i, n, graph, used, marks, ref mark);
				}
			}
			return marks;
		}


		static void DFS(int i, int n, int[,] graph, List<int> used, int[] marks, ref int mark)
		{
			used.Add(i);
			marks[i] = mark;
			mark++;
			for (int j = 0; j < n; j++)
			{
				if (graph[i, j] == 1 && !used.Exists(x => x == j))
				{
					used.Add(j);
					DFS(j, n, graph, used, marks, ref mark);

				}
			}
		}

		static void Main(string[] args)
		{
			int n;
			int[,] graph;
			using (StreamReader sr = new StreamReader("input.txt"))
			{
				string str = sr.ReadLine();
				n = int.Parse(str);
				graph = new int[n, n];
				for (int i = 0; i < n; i++)
				{
					str = sr.ReadLine();
					var edge = str.Split(' ').Select(x => int.Parse(x)).ToArray();
					for (int j = 0; j < n; j++)
					{
						graph[i, j] = edge[j];
					}
				}
			}
			var marks = StartDFS(n, graph);
			using (TextWriter tw = new StreamWriter("output.txt"))
			{
				for (int i = 0; i < n; i++)
				{
					tw.Write(marks[i] + " ");
				}
			}
		}
	}
}
