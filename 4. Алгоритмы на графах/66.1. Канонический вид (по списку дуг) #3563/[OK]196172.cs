using System.IO;

namespace канонический_вид_по_списку_дуг
{
	class Program
	{
		static void Main(string[] args)
		{
			StreamReader file = new StreamReader("input.txt");

			int n = int.Parse(file.ReadLine());

			int[] canonicalForm = new int[n];

			while(!file.EndOfStream)
			{
				string[] buff = file.ReadLine().Split(' ');

				int ind = int.Parse(buff[1]) - 1;
				int elem = int.Parse(buff[0]);

				canonicalForm[ind] = elem;
			}

			StreamWriter output = new StreamWriter("output.txt");

			foreach (int i in canonicalForm)
				output.Write(i + " ");

			output.Close();
		}
	}
}