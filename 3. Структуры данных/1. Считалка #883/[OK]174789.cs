using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace Alg4
{
	class Program
	{
		static int GetLastRemoved(int n, int m)
		{
			var persons = Enumerable.Range(1, n).ToList();
			var orderOfDeleting = new List<int>();
			int index = 0;
			while (persons.Count != 0)
			{
				var next = (m + index - 1) % persons.Count;
				orderOfDeleting.Add(persons[next]);
				persons.RemoveAt(next);
				index = next;
			}
			return orderOfDeleting.Last();
		}

		static void Main(string[] args)
		{
			int[] numbers;
			using (StreamReader sr = new StreamReader("in.txt"))
			{
				var str = sr.ReadLine();
				numbers = str.Split(new string[] { " " }, StringSplitOptions.RemoveEmptyEntries).Select(item => int.Parse(item)).ToArray();

			}
			var N = numbers[0];
			var M = numbers[1];
			var L = numbers[2];
			var lastNumber = GetLastRemoved(N, M);
			var startNumber = 1 - lastNumber + L;
			if (startNumber < 1)
			{
				startNumber += N;
			}
			using (StreamWriter sw = new StreamWriter("out.txt"))
			{
				sw.WriteLine(lastNumber);
				sw.WriteLine(startNumber);
			}
		}
	}
}
