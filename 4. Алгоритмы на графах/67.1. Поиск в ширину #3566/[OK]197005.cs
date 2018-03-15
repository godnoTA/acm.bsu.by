using System.Collections.Generic;
using System.IO;

namespace BFS
{
	class Program
	{
		static int n;
		static Queue<int> queue;
		static int[] path;
		static int[,] matrix;

		static void Main(string[] args)
		{
			StreamReader reader = new StreamReader("input.txt");

			n = int.Parse(reader.ReadLine());

			queue = new Queue<int>();
			path = new int[n];
			matrix = new int[n, n];
			string[] Buff;
			int l = 0;

			while (!reader.EndOfStream)
			{
				Buff = reader.ReadLine().Split(' ');

				for (int j = 0; j < n; j++)
					matrix[l, j] = int.Parse(Buff[j]);

				++l;
			}

			int p = 0;

			Bfs(0, 0);

			StreamWriter writer = new StreamWriter("output.txt");

			foreach (int i in path)
				writer.Write(i + " ");

			writer.Close();
		}

		public static void Bfs(int node, int count)
		{
			queue.Enqueue(node + 1);
			if (path[node] == 0)
				path[node] = ++count;

			while (queue.Count != 0)
			{
				int ind = queue.Dequeue() - 1;

				for (int i = 0; i < n; i++)
					if (matrix[ind, i] == 1 && path[i] == 0)
					{
						queue.Enqueue(i + 1);
						path[i] = count + 1;
						count = path[i];
					}
			}

			if (count != n)
				Bfs(node + 1, count);
		}
	}
}
