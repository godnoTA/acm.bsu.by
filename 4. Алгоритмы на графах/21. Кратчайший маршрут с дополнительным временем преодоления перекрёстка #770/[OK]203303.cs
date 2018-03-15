using System.IO;

namespace graph_ways
{
	class Program
	{
		static int N;
		static int M;
		static int q;
		static int[] adj;
		static int[] d;
		static int[,] matrix;
		static bool[] visit;

		static void Main(string[] args)
		{
			StreamReader reader = new StreamReader("input.txt");

			string[] buff = reader.ReadLine().Split(' ');

			N = int.Parse(buff[0]);
			M = int.Parse(buff[1]);
			matrix = new int[N, N];
			adj = new int[N];
			d = new int[N];
			for (int i = 0; i < N; i++)
				d[i] = 20000000;
			visit = new bool[N];

			int l = 0;
			while (l != M)
			{
				buff = reader.ReadLine().Split(' ');

				int v = int.Parse(buff[0]) - 1;
				int w = int.Parse(buff[1]) - 1;
				int time = int.Parse(buff[2]);

				matrix[v, w] = time + 1;
				matrix[w, v] = time + 1;
				++adj[v];
				++adj[w];
				++l;
			}

			buff = reader.ReadLine().Split(' ');

			int begin = int.Parse(buff[0]) - 1;
			int end = int.Parse(buff[1]) - 1;
			q = int.Parse(buff[2]);

			bool flag = false;
			if (begin > end && 1 < 0)
			{
				int tmp = begin;
				begin = end;
				end = tmp;
				flag = true;
			}

			FindWay(begin, end);
			if(flag)
			{
				d[end] = d[end] + adj[end] - adj[begin];
			}

			StreamWriter writer = new StreamWriter("output.txt");

			if (d[end] == 20000000)
				writer.Write("No");
			else
			{
				writer.WriteLine("Yes");
				writer.Write(d[end]);
			}

			writer.Close();
		}

		private static void FindWay(int a, int b)
		{
			d[a] = 0;

			for (int it = 0; it < N; it++)
			{
				if (visit[b])
					break;

				int v = -1;
				int distV = 20000000;

				for (int i = 0; i < N; i++)
					if (!visit[i] && d[i] < distV)
					{
						v = i;
						distV = d[i];
					}

					if (distV == 20000000)
						break;

				for (int i = 0; i < N; i++)
				{
					if (matrix[v, i] != 0 )
					{
						int distance = d[v] + matrix[v, i] - 1 + adj[v] * q;

						if(distance < d[i])
						{
							d[i] = distance;
						}
					}
				}

				visit[v] = true;
			}
		}
	}
}