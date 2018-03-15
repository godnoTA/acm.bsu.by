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
		static void Main(string[] args)
		{
			int n, m;
			int[] graph;
			using (StreamReader sr = new StreamReader("input.txt"))
			{
				string str = sr.ReadLine();
				n = int.Parse(str);
				graph = new int[n];
				for (int i = 0; i < n - 1; i++)
				{
					str = sr.ReadLine();
					var edge = str.Split(' ').Select(x => int.Parse(x)).ToArray();
					graph[edge[1] - 1] = edge[0];
				}
			}
			using (TextWriter tw = new StreamWriter("output.txt"))
			{
				for (int i = 0; i < n; i++)
				{
					tw.Write(graph[i] + " ");
				}
			}
		}
	}
}
