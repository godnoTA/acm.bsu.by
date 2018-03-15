using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Alg_common5
{
	class Program
	{
		static void Main(string[] args)
		{
			int n;
			int[] mas;
			using (StreamReader sr = new StreamReader("input.txt"))
			{
				string str;
				str = sr.ReadLine();
				n = int.Parse(str);
				mas = new int[n];
				str = sr.ReadLine();
				var numbers = str.Split(' ');
				for (int i = 0; i < n; i++)
				{

					mas[i] = int.Parse(numbers[i]);
				}
			}
			using (StreamWriter sw = new StreamWriter("output.txt"))
			{
				for (int i = 0; i < n/2; i++)
				{
					if (i != n / 2 - 1 || n % 2 == 1)
					{
						if (mas[i] > mas[2 * i + 1] || mas[i] > mas[2 * i + 2])
						{
							sw.WriteLine("No");
							return;
						}
					}
					else
					{
						if (mas[i] > mas[2 * i + 1])
						{
							sw.WriteLine("No");
							return;
						}
					}
				}
				sw.WriteLine("Yes");
			}
		}
	}
}
