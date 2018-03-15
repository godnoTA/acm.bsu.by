using System.Collections.Generic;
using System.IO;

namespace chemistry
{
	class Program
	{
		static void Main(string[] args)
		{
			StreamReader reader = new StreamReader("in.txt");

			string[] buff = reader.ReadLine().Split(' ');
			int n = int.Parse(buff[0]);
			int m = int.Parse(buff[1]);

			int[,] matrix = new int[n, n];
			int[] elements = new int[m];

			Stack<int> vial = new Stack<int>();

			int countN = 0;

			while (countN < n)
			{
				buff = reader.ReadLine().Split(' ');

				for (int i = 0; i < n; i++)
					matrix[countN, i] = int.Parse(buff[i]);

				++countN;
			}

			buff = reader.ReadLine().Split(' ');

			vial.Push(int.Parse(buff[0]));
			for (int i = 1; i < m; i++)
			{
				int fir = vial.Peek();
				int sec = int.Parse(buff[i]);

				if (matrix[fir - 1, sec - 1] == 0)
				{
					vial.Push(sec);
					continue;
				}

				vial.Pop();
				vial.Push(matrix[fir - 1, sec - 1]);

				bool flag = true;
				while (flag)
				{
					if (vial.Count != 1)
					{
						sec = vial.Pop();
						fir = vial.Peek();

						if (matrix[fir - 1, sec - 1] != 0)
						{
							vial.Pop();
							vial.Push(matrix[fir - 1, sec - 1]);
						}
						else
						{
							vial.Push(sec);
							flag = false;
						}
					}
					else flag = false;
				}
			}

			StreamWriter writer = new StreamWriter("out.txt");

			writer.Write(vial.Peek());
			vial.Pop();

			foreach (int i in vial)
				writer.Write(" {0}", i);

			writer.Close();
		}
	}
}
