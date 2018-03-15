using System;
using System.IO;

namespace tape
{
	class Program
	{
		static void Main(string[] args)
		{
			StreamReader file = new StreamReader("input.txt");

			int n = Convert.ToInt32(file.ReadLine());
			string[] buff = file.ReadLine().Split(' ');

			int[] diag = new int[n];
			long[,] matrix = new long[n, n];

			for (int i = 0; i < n; i++)
			{
				diag[i] = Convert.ToInt32(buff[i]);
				matrix[i, i] = diag[i];
			}

			for (int i = 1; i < n; i++)
			{
				for (int j = 0; j < n - i; j++)
				{
					matrix[j, j + i] = sum(diag, j, j + i) - min(matrix[j, j + i - 1], matrix[j + 1, j + i]);
				}
			}

			StreamWriter output = new StreamWriter("output.txt");
			output.WriteLine(matrix[0, n - 1]);
			output.Close();
		}

		static long min (long a, long b)
		{
			return a < b ? a : b;
		}

		static long sum(int[] array, int begin, int end)
		{
			long summ = 0;
			for (int i = begin; i <= end; i++)
			{
				summ += array[i];
			}

			return summ;
		}
	}
}