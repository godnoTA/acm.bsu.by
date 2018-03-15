using System;
using System.IO;

namespace BT
{
	class MainClass
	{
		public static void Main (string[] args)
		{
			StreamReader fo = new StreamReader ("input.txt");
			BinaryTree bt = new BinaryTree ();
			String s="";
			while((s=fo.ReadLine())!=null)
			{
				bt.Add (long.Parse(s));
			}

			fo.Close ();
			FileStream fs = new FileStream ("output.txt",FileMode.OpenOrCreate);
			StreamWriter sw = new StreamWriter (fs);

			Console.WriteLine (bt.sum );

			sw.WriteLine(bt.sum);
			sw.Close ();
		}

		class BinaryTree
		{
			class Node
			{
				public Node left{ get; set;}
				public Node right{ get; set;}
				public long key{ get; set;}

				public Node(long v)
				{
					key=v;
				}

			}

			Node root{ get; set;}
			public long sum{ get; set;}

			public BinaryTree()
			{
				sum=0;
			}

			private void AddRec(long v,Node r)
			{
				if (v > r.key) {
					if (r.right != null)
						AddRec (v, r.right);
					else {
						r.right = new Node (v);
						sum += v;
						return;
					}
				} 
				if (v < r.key) {
					if (r.left != null)
						AddRec (v, r.left);
					else {
						r.left = new Node (v);
						sum += v;
						return;
					}
				}
			}

			public void Add(long v)
			{
				if (root == null) {
					sum += v;
					root = new Node (v);
				} else
				{
					AddRec(v,root);
				}
			}
		}
	}
}
