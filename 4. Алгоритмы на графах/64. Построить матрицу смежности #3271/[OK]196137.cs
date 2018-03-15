using System.IO;

namespace matrix_of_adjacency
{
	class Program
	{
		static void Main(string[] args)
		{
			StreamReader file = new StreamReader("input.txt");

			string[] buff = file.ReadLine().Split(' ');

			int n = int.Parse(buff[0]);
			int m = int.Parse(buff[1]);

			int[,] matrix = new int[n, n];
			int v = 0, w = 0;

			while (!file.EndOfStream)
			{
				buff = file.ReadLine().Split(' ');

				v = int.Parse(buff[0]);
				w = int.Parse(buff[1]);

				matrix[v - 1, w - 1] = 1;
				matrix[w - 1, v - 1] = 1;
			}

			StreamWriter output = new StreamWriter("output.txt");

			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
				{
					output.Write(matrix[i, j] + " ");
				}
				output.WriteLine();
			}

			output.Close();
		}
	}
}
