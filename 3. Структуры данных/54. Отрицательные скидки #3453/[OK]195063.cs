using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace sales
{
	class Program
	{
		static void Main(string[] args)
		{
			StreamReader file = new StreamReader("input.txt");

			string[] buff = file.ReadLine().Split(' ');

			int n = int.Parse(buff[0]);
			int k = int.Parse(buff[1]);

			BinaryHeap heap = new BinaryHeap();

			buff = file.ReadLine().Split(' ');
			int a = int.Parse(buff[0]);
			int b = int.Parse(buff[1]);
			int l = a;
			int[] array = new int[k];

			for (int i = 0; i < k; i++)
			{
				array[i] = l;
				l += b;
			}

			heap.BuildHeap(array);

			while (!file.EndOfStream)
			{
				buff = file.ReadLine().Split(' ');

				a = int.Parse(buff[0]);
				b = int.Parse(buff[1]);
				l = a;

				for (int i = 0; i < k; i++)
				{
					if (l < heap.Root())
					{
						heap.NewElement(l);
						l += b;
					}
					else
						break;
				}
			}
			
			StreamWriter output = new StreamWriter("output.txt");
			
			output.WriteLine(heap.Sum());

			output.Close();
		}
	}

	public class BinaryHeap
	{
		private List<int> list;

		public int Root()
		{
			return list[0];
		}

		public int Size()
		{
			return list.Count();
		}

		public long Sum()
		{
			long sum = 0;
			foreach (int i in list)
				sum += i;
			return sum;
		}

		public void Add(int value)
		{
			list.Add(value);

			int r = Size() - 1;
			int parent = (r - 1) / 2;

			while (list[r] > list[parent] && r > 0)
			{
				int tmp = list[r];
				list[r] = list[parent];
				list[parent] = tmp;

				r = parent;
				parent = (r - 1) / 2;
			}
		}

		public void Screening (int i)
		{
			int lSon, rSon, son;

			for (; ; )
			{
				son = i;
				lSon = i * 2 + 1;
				rSon = i * 2 + 2;

				if (lSon < Size() && list[lSon] > list[son])
					son = lSon;

				if (rSon < Size() && list[rSon] > list[son])
					son = rSon;

				if (son == i)
					break;

				int tmp = list[i];
				list[i] = list[son];
				list[son] = tmp;
				i = son;
			}
		}

		public void BuildHeap (int[] sourceArray)
		{
			list = sourceArray.ToList();
			for (int i = (Size() - 1) / 2; i >= 0; i--)
			{
				Screening(i);
			}
		}

		public void NewElement (int i)
		{
			list[0] = i;
			Screening(0);
		}
	}
}