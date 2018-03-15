using System.Collections.Generic;
using System.IO;

namespace канонический_вид_по_списку_дуг
{
	class Program
	{
		static void Main(string[] args)
		{
			StreamReader file = new StreamReader("input.txt");

			string[] buff = file.ReadLine().Split(' ');

			int n = int.Parse(buff[0]);
			int m = int.Parse(buff[1]);

			List<int>[] list = new List<int>[n];
			for (int i = 0; i < n; i++)
			{
				list[i] = new List<int>();
				list[i].Add(0);
			}

			while(!file.EndOfStream)
			{
				buff = file.ReadLine().Split(' ');
				int v = int.Parse(buff[0]);
				int w = int.Parse(buff[1]);

				++list[v - 1][0];
				list[v - 1].Add(w);
				++list[w - 1][0];
				list[w - 1].Add(v);
			}

			StreamWriter output = new StreamWriter("output.txt");

			for (int i = 0; i < n; i++)
			{
				foreach (int k in list[i])
					output.Write(k + " ");
				output.WriteLine();
			}

			output.Close();
		}
	}
}
