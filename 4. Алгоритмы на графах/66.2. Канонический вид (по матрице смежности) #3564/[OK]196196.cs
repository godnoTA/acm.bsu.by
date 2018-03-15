using System.IO;

namespace канонический_вид_по_списку_дуг
{
	class Program
	{
		static void Main(string[] args)
		{
			StreamReader file = new StreamReader("input.txt");

			int n = int.Parse(file.ReadLine());

			int[,] matirix = new int[n, n];
			int i = 1;
			int[] array = new int[n];

			while(!file.EndOfStream)
			{
				string[] buff = file.ReadLine().Split(' ');

				for (int j = 0; j < n; j++)
				{
					if (buff[j] == "1")
						array[j] = i;
				}
				++i;
			}

			StreamWriter output = new StreamWriter("output.txt");

			foreach (int l in array)
				output.Write(l + " ");

			output.Close();
		}
	}
}
