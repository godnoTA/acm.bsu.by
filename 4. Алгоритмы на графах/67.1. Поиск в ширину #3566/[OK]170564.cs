using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alg_common10
{
	class Program
	{

		static int[] BFS(int n, int[,] graph)
		{
			int mark = 1;
			List<int> used = new List<int>();
			int[] marks = new int[n];
			Queue<int> que = new Queue<int>();
			for (int i = 0; i < n; i++)
			{
				if (marks[i] == 0)
				{
					que.Enqueue(i);
					used.Add(i);
					while (que.Any())
					{
						var item = que.Dequeue();
						marks[item] = mark;
						mark++;
						for (int j = 0; j < n; j++)
						{
							if (graph[item, j] == 1 && !used.Exists(x => x == j))
							{
								que.Enqueue(j);
								used.Add(j);
							}
						}
					}
				}
			}
			return marks;
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
			var marks = BFS(n, graph);
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
