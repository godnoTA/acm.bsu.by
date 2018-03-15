using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace hilllman
{
    class Node
    {
        public int Key;
        public Node Left;
        public Node Right;
        public int LeftChildren;
        public int RightChildren;
        public int DiffAbs;

        public Node(int key)
        {
            Key = key;
            Left = null;
            Right = null;
            LeftChildren = -1;
            RightChildren = -1;
            DiffAbs = -1;
        }
    }

    class Tree
    {
        Node Root;

        public void Insert(int key)
        {
            Root = InternalInsert(Root, key);
        }
        public Node Search(int key)
        {
            return InternalSearch(Root, key);
        }
        public void Delete(int key)
        {
            Root = InternalDelete(Root, key);
        }
        public Node SetMarks()
        {
            Node maxNode = new Node(Root.Key);
            InnerSetMarks(Root, ref maxNode);
            return maxNode;
        }
        public void LeftTraversal()
        {
            System.IO.StreamWriter output = new System.IO.StreamWriter("tst.out") { AutoFlush = true };
            InnerLeftTraversal(Root, ref output);
        }

        void InnerLeftTraversal(Node node, ref System.IO.StreamWriter output)
        {
            if (node == null)
                return;
            output.WriteLine(node.Key);
            InnerLeftTraversal(node.Left, ref output);
            InnerLeftTraversal(node.Right, ref output);
        }

        Node InternalSearch(Node node, int key)
        {
            if (node == null || node.Key == key)
                return node;
            else if (key < node.Key)
                return InternalSearch(node.Left, key);
            else
                return InternalSearch(node.Right, key);
        }
        Node InternalInsert(Node node, int key)
        {   
            if (node == null)
                return new Node(key);
            if (key < node.Key)
                node.Left = InternalInsert(node.Left, key);
            else if (key > node.Key)
                node.Right = InternalInsert(node.Right, key);
            return node;
        }
        Node InternalDelete(Node node, int key)
        {
            if (node == null)
                return null;

            if (key < node.Key)
            {
                node.Left = InternalDelete(node.Left, key);
                return node;
            }
            else if (key > node.Key)
            {
                node.Right = InternalDelete(node.Right, key);
                return node;
            }

            if (node.Left == null)
                return node.Right;
            else if (node.Right == null)
                return node.Left;
            else
            {
                int min_key = SearchMin(node.Right).Key;
                node.Key = min_key;
                node.Right = InternalDelete(node.Right, min_key);
                return node;
            }
        }
        Node SearchMin(Node node)
        {
            if (node.Left != null)
                return SearchMin(node.Left);
            else
                return node;
        }
        int diffAbs(Node node)
        {
            return Math.Abs(node.LeftChildren - node.RightChildren);
        }
        int InnerSetMarks(Node node, ref Node maxNode)
        {
            if (node == null)
                return 0;
            node.LeftChildren = InnerSetMarks(node.Left, ref maxNode);
            node.RightChildren = InnerSetMarks(node.Right, ref maxNode);
            node.DiffAbs = diffAbs(node);
            if (node.DiffAbs > maxNode.DiffAbs || (node.DiffAbs == maxNode.DiffAbs && node.Key > maxNode.Key))
                maxNode = node;
            return node.LeftChildren + node.RightChildren + 1;
        }
    }


    class Program
    {
        static void Main(string[] args)
        {
            System.IO.StreamReader input = new System.IO.StreamReader("tst.in");

            Tree tree = new Tree();
            string tmp;

            while ((tmp = input.ReadLine()) != null)
                tree.Insert(Int32.Parse(tmp));
            input.Close();

            tree.Delete(tree.SetMarks().Key);
            tree.LeftTraversal();
        }
    }
}
