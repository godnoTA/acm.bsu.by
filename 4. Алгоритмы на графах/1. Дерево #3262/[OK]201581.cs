using System.IO;

namespace Tree
{
	class Program
	{
		static int n;
		static bool[] visit;
		static int[,] matrix;
		static bool cycle;
		static int count;

		static void Main(string[] args)
		{
			StreamReader reader = new StreamReader("input.txt");

			n = int.Parse(reader.ReadLine());
			
			visit = new bool[n];
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

			dfs(0);

			StreamWriter writer = new StreamWriter("output.txt");

			if (!cycle && count == n)
				writer.WriteLine("Yes");
			else writer.WriteLine("No");
			writer.Close();
		}

		public static void dfs(int v)
		{
			++count;
			visit[v] = true;

			for (int i = 0; i < n; i++)
			{
				if (matrix[v, i] == 1)
				{
					if (!visit[i])
					{
						matrix[i, v] = 0;
						dfs(i);
					}
					else cycle = true;
				}
			}
		}
	}
}