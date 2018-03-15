using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;

namespace matrix
{
	class Program
	{
		static void Main(string[] args)
		{
			StreamReader file = new StreamReader("input.txt");

			int n = Convert.ToInt32(file.ReadLine());
			int[,] size = new int[n, 2];
			int l = 0, k = 0;
			
			while (!file.EndOfStream)
			{
				string[] buff = file.ReadLine().Split(' ');
				size[l, 0] = int.Parse(buff[0]);
				size[l, 1] = int.Parse(buff[1]);
				++l;
			}
			
			int[,] matrix = new int[n, n];

			for (int i = 0; i < n; i++)
			{
				matrix[i, i] = 0;
			}

			for (int i = 1; i < n; i++)
			{
				for (int j = 0; j < n - i; j++)
				{
					List<int> tmp = new List<int>();
					for (k = j; k < j + i; k++)
					{
						tmp.Add(matrix[j, k] + matrix[k + 1, j + i] + size[j, 0] * size[k, 1] * size[j + i, 1]);
					}
						matrix[j, j + i] = tmp.Min();
				}
			}

			StreamWriter output = new StreamWriter("output.txt");
			output.WriteLine(matrix[0, n - 1]);
			output.Close();
		}
	}
}