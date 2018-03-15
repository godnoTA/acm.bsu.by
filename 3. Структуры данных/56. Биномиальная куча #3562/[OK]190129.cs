using System;
using System.IO;

namespace binom_heap
{
	class Program
	{
		static void Main(string[] args)
		{
			StreamReader file = new StreamReader("input.txt");

			long n = long.Parse(file.ReadLine());

			string BinaryCode = Convert.ToString(n, 2);

			StreamWriter output = new StreamWriter("output.txt");

			for (int i = BinaryCode.Length - 1; i >= 0; i--)
			{
				if (BinaryCode[i] == '1')
					output.WriteLine(BinaryCode.Length - i - 1);

			}
			output.Close();
		}
	}
}
