using System.Collections.Generic;
using System.IO;

namespace Alg_common0
{
	public static class BinaryTreeExtensions
	{
		public static void Print(this BinaryTree node, StreamWriter output)
		{
			if (node != null)
			{
				if (node.Parent == null)
				{
					output.WriteLine(node.Data);
				}
				else
				{
					output.WriteLine(node.Data);
				}
				if (node.Left != null)
				{
					Print(node.Left, output);
				}
				if (node.Right != null)
				{
					Print(node.Right, output);
				}
			}
		}

		public static long Height(this BinaryTree binaryTree)
		{
			long height = -1;
			if (binaryTree != null)
			{
				height = 0;
				long leftHeight, rightHeight;
				leftHeight = Height(binaryTree.Left);
				rightHeight = Height(binaryTree.Right);
				if (leftHeight > rightHeight)
				{
					height = leftHeight + 1;
				}
				else
				{
					height = rightHeight + 1;
				}
			}
			return height;
		}
	}
	public enum BinSide
	{
		Left,
		Right
	}

	public class BinaryTree
	{

		public long? Data { get; private set; }
		public BinaryTree Left { get; set; }
		public BinaryTree Right { get; set; }
		public BinaryTree Parent { get; set; }

		public void Insert(long data)
		{
			if (Data == null || Data == data)
			{
				Data = data;
				return;
			}
			if (Data > data)
			{
				if (Left == null) Left = new BinaryTree();
				Insert(data, Left, this);
			}
			else
			{
				if (Right == null) Right = new BinaryTree();
				Insert(data, Right, this);
			}
		}

		private void Insert(long data, BinaryTree node, BinaryTree parent)
		{

			if (node.Data == null || node.Data == data)
			{
				node.Data = data;
				node.Parent = parent;
				return;
			}
			if (node.Data > data)
			{
				if (node.Left == null) node.Left = new BinaryTree();
				Insert(data, node.Left, node);
			}
			else
			{
				if (node.Right == null) node.Right = new BinaryTree();
				Insert(data, node.Right, node);
			}
		}

		private void Insert(BinaryTree data, BinaryTree node, BinaryTree parent)
		{

			if (node.Data == null || node.Data == data.Data)
			{
				node.Data = data.Data;
				node.Left = data.Left;
				node.Right = data.Right;
				node.Parent = parent;
				return;
			}
			if (node.Data > data.Data)
			{
				if (node.Left == null) node.Left = new BinaryTree();
				Insert(data, node.Left, node);
			}
			else
			{
				if (node.Right == null) node.Right = new BinaryTree();
				Insert(data, node.Right, node);
			}
		}

		private BinSide? MeForParent(BinaryTree node)
		{
			if (node.Parent == null) return null;
			if (node.Parent.Left == node) return BinSide.Left;
			if (node.Parent.Right == node) return BinSide.Right;
			return null;
		}

		public void Remove(BinaryTree node)
		{
			if (node == null) return;
			var me = MeForParent(node);
			if (node.Left == null && node.Right == null)
			{
				if (me == null)
				{
					node = null;
					return;
				}
				if (me == BinSide.Left)
				{
					node.Parent.Left = null;
				}
				else
				{
					node.Parent.Right = null;
				}
				return;
			}
			if (node.Left == null)
			{
				if (me == BinSide.Left)
				{
					node.Parent.Left = node.Right;
				}
				else
				{
					node.Parent.Right = node.Right;
				}

				node.Right.Parent = node.Parent;
				return;
			}
			if (node.Right == null)
			{
				if (me == BinSide.Left)
				{
					node.Parent.Left = node.Left;
				}
				else
				{
					node.Parent.Right = node.Left;
				}

				node.Left.Parent = node.Parent;
				return;
			}
			var smallestLeft = node.Right;
			while (smallestLeft.Left != null)
			{
				smallestLeft = smallestLeft.Left;
			}
			node.Data = smallestLeft.Data;
			Remove(smallestLeft);
		}

		public void Remove(long data)
		{
			var removeNode = Find(data);
			if (removeNode != null)
			{
				Remove(removeNode);
			}
		}

		public BinaryTree Find(long data)
		{
			if (Data == data) return this;
			if (Data > data)
			{
				return Find(data, Left);
			}
			return Find(data, Right);
		}

		public BinaryTree Find(long data, BinaryTree node)
		{
			if (node == null) return null;

			if (node.Data == data) return node;
			if (node.Data > data)
			{
				return Find(data, node.Left);
			}
			return Find(data, node.Right);
		}

		public void GetSumOfNodes(ref long sum)
		{
			if (Data != null)
			{
				sum += Data.Value;
				if (Left != null)
				{
					Left.GetSumOfNodes(ref sum);
				}
				if (Right != null)
				{
					Right.GetSumOfNodes(ref sum);
				}
			}
		}
	}

	class Program
	{
		static void Main(string[] args)
		{
			BinaryTree binaryTree = new BinaryTree();
			using (StreamReader sr = new StreamReader("input.txt"))
			{
				string str;
				while ((str = sr.ReadLine()) != null)
				{
					long data;
					bool tryParse = long.TryParse(str, out data);
					if (tryParse)
					{
						binaryTree.Insert(data);
					}
				}
			}
			long sum = 0;
			if (binaryTree.Data != null)
			{
				binaryTree.GetSumOfNodes(ref sum);
			}
			using (StreamWriter output = new StreamWriter("output.txt"))
			{
				output.WriteLine(sum);
			}
		}
	}
}
