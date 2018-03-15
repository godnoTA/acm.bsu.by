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

			bt.Left (bt.root, sw);

			sw.Close ();
		}

		class BinaryTree
		{
			public class Node
			{
				public Node left{ get; set;}
				public Node right{ get; set;}
				public long key{ get; set;}

				public Node(long v)
				{
					key=v;
				}

			}

			public Node root{ get; set;}

			private void AddRec(long v,Node r)
			{
				if (v > r.key) {
					if (r.right != null)
						AddRec (v, r.right);
					else {
						r.right = new Node (v);
						return;
					}
				} 
				if (v < r.key) {
					if (r.left != null)
						AddRec (v, r.left);
					else {
						r.left = new Node (v);
						return;
					}
				}
			}

			public void Add(long v)
			{
				if (root == null) {
					root = new Node (v);
				} else
				{
					AddRec(v,root);
				}
			}

			public void Left(Node r, StreamWriter sw)
			{
				if (r == null)
					return;
				sw.WriteLine (r.key);
				Left (r.left, sw);
				Left (r.right, sw);
			}

		}
	}
}