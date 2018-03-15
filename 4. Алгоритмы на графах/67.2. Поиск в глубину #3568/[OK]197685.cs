using System.IO;

namespace DFS
{
	class Program
	{
		static int n;
		static int[] path;
		static int[,] matrix;
		static int count;

		static void Main(string[] args)
		{
			StreamReader reader = new StreamReader("input.txt");

			n = int.Parse(reader.ReadLine());
			
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

			for (int i = 0; i < n; i++)
				if (path[i] == 0)
					dfs(i);

			StreamWriter writer = new StreamWriter("output.txt");

			foreach (int i in path)
				writer.Write(i + " ");

			writer.Close();
		}

		public static void dfs(int v)
		{
			++count;
			path[v] = count;
			
			for (int i = 0; i < n; i++)
				if (matrix[v, i] == 1 && path[i] == 0)
					dfs(i);
		}
	}
}
