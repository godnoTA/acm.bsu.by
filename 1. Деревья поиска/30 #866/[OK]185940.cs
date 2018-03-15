using System;
using System.Collections.Generic;
using System.IO;

namespace ConsoleApp2
{
	class Program
	{
		static void Main(string[] args)
		{
			StreamReader file = new StreamReader("tst.in");

			Tree tree = new Tree();

			while (!file.EndOfStream)
			{
				tree.Insert(Convert.ToInt32(file.ReadLine()));
			}

			tree.Answers(tree.root);

			if (tree.buff2.Count % 2 != 0)
			{
			    int key;
				tree.buff2.Sort();

				key = tree.buff2[tree.buff2.Count / 2];

				tree.Delete(key);
			}

				tree.Print(tree.root);

				StreamWriter output = new StreamWriter("tst.out");

				foreach (int i in tree.buff)
				{
					output.WriteLine(i);
				}

				if (output != null)
					output.Close();
			
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
		public List<int> buff2 = new List<int>();

		public Item root;

		public Tree()
		{
			root = null;
		}

		public void Answers(Item t)
		{
			if (t != null)
			{
				if (Diff(t) == 2)
				{
					buff2.Add(t.info);
				}

				Answers(t.lSon);
				Answers(t.rSon);
			}
		}

		private int Height(Item t)
		{
			if (t == null)
				return 0;

			int right, left;

			if (t.rSon != null)
				right = Height(t.rSon);
			else right = -1;

			if (t.lSon != null)
				left = Height(t.lSon);
			else left = -1;
			
			int max = left > right ? left : right;
			
			return max + 1;
		}

		public int RHeight (Item t)
		{
			if (t.rSon != null)
				return Height(t.rSon);
			else return -1;
		}

		public int LHeight (Item t)
		{
			if (t.lSon != null)
				return Height(t.lSon);
			else return -1;
		}

		public int Diff(Item t)
		{
			return Math.Abs(RHeight(t) - LHeight(t));
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

		public void Print(Item t)
		{
			if (t != null)
			{
				buff.Add(t.info);

				Print(t.lSon);
				Print(t.rSon);
			}
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

		private void deleteItem(Item x)
		{
			if (x.father == null)
			{
				if (x.lSon != null)
				{
					root = x.lSon;
					x.lSon.father = null;
				}
				else
				{
					root = x.rSon;
					if (x.rSon != null)
						x.rSon.father = null;
				}
			}
			else
			{
				if (x.father.lSon == x)
				{
					if (x.lSon != null)
					{
						x.father.lSon = x.lSon;
						x.lSon.father = x.father;
					}
					else
					{
						x.father.lSon = x.rSon;
						if (x.rSon != null)
							x.rSon.father = x.father;
					}
				}
				else
				{
					if (x.lSon != null)
					{
						x.father.rSon = x.lSon;
						x.lSon.father = x.father;
					}
					else
					{
						x.father.rSon = x.rSon;
						if (x.rSon != null)
							x.rSon.father = x.father;
					}
				}
			}
			x.father = x.lSon = x.rSon = null;
		}

		public bool Delete(int x)
		{
			Item r, p;
			if (!Find(x, out r))
				return false;
			if ((r.lSon == null) || (r.rSon == null))
			{
				deleteItem(r);
				return true;
			}
			p = r.lSon;
			while (p.rSon != null)
				p = p.rSon;
			r.info = p.info;
			deleteItem(p);
			return true;
		}
	}
}
