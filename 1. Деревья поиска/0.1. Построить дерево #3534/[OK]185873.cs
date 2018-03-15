using System;
using System.Collections.Generic;
using System.IO;

namespace ConsoleApp2
{
	class Program
	{
		static void Main(string[] args)
		{
			StreamReader file = new StreamReader("input.txt");

			Tree tree = new Tree();

			while (!file.EndOfStream)
			{
				tree.Insert(Convert.ToInt32(file.ReadLine()));
			}

			tree.Print(tree.root);

			foreach (var i in tree.buff)
			{
				Console.WriteLine(i);
			}

			StreamWriter output = new StreamWriter("output.txt");

			try
			{

			foreach(int i in tree.buff)
			{
				output.WriteLine(i);
			}
			}
			catch (IOException e)
			{
				Console.WriteLine(e.Message);
			}

			finally
			{
				if (output != null)
					output.Close();
			}

		}
	}

	class Tree
	{
		
		public class Item
		{
			
			public int info;
			public Item lSon, rSon, father;
			
			public Item(int x)
			{
				info = x;
				lSon = rSon = father = null;
			}
		}

	   public List <int> buff = new List <int> ();

		public Item root;

		public Tree()
		{
			root = null;
		}

		public void Print(Item t)
		{
			if (t != null)
			{
				buff.Add(t.info);

				Print(t.lSon);
				Print(t.rSon);
			}
		}

		private bool Find(int x, out Item p)
		{
			p = root;
			Item q = p;
			while (q != null)
			{
				p = q;
				if (q.info == x)
					return true;
				if (q.info < x)
					q = q.rSon;
				else
					q = q.lSon;
			}
			return false;
		}

		public bool Insert(int x)
		{
			Item r, p;
			if (root == null)
			{
				r = new Item(x);
				root = r;
				return true;
			}
			if (Find(x, out r))
				return false;
			p = new Item(x);
			p.father = r;
			if (r.info < x)
				r.rSon = p;
			else
				r.lSon = p;
			return true;
		}
	}
}
