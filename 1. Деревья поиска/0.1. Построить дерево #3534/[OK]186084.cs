using System;
using System.IO;
using System.Collections;
using System.Collections.Generic;

namespace ConsoleApplication4
{

    class Program
    {
        static void Main(string[] args)
        {
            Tree tree = new Tree();
            StreamReader f = new StreamReader("input.txt");
            StreamWriter vi = new StreamWriter("output.txt");
            int k = Convert.ToInt32(f.ReadLine());
            Tree.Node root = new Tree.Node(k);    
            while (!f.EndOfStream)
            {
                int s = Convert.ToInt32(f.ReadLine());
                tree.MainInsert(root , s);            
            }
            //tree.wr();
            tree.OutFile(root , vi);
            vi.Close();
        }

        public class Tree
        {

            public class Node
            {
                public int key;
                public Node left;
                public Node right;
                public Node(int x)
                {
                    key = x;
                }
        }
            //public Node root;
            //public void Insert(double x)
            //{
            //    root = MainInsert(root, x);
            //}
            //public void wr()
            //{
            //    OutFile(root);
            //}
            //public void Sear1(double x)
            //{
            //    root = MainInsert(root, x);
            //}
            //public void Sear(double x ,Node node)
            //{
            //    if(node == null)
            //    {
            //        Insert(x);
            //    }
            //    if(x > node.key)
            //    {
            //        Sear(x,node.right);
            //    }
            //    if(x < node.key)
            //    {
            //        Sear(x, node.left);
            //    }
            //    //if(x == node.key)
            //    //{
                    
            //    //}
            //}
            public void OutFile(Node node , StreamWriter vi)
            {
                if (node != null)
                {
                    vi.WriteLine(node.key);
                    OutFile(node.left , vi);
                    OutFile(node.right, vi);
                }

            }

            public Node MainInsert(Node node, int x)
            {
                if (node == null)
                {
                    return new Node(x);
                }
                if (x < node.key)
                {
                    node.left = MainInsert(node.left, x);
                }
                else if (x > node.key)
                {
                    node.right = MainInsert(node.right, x);
                }
                return node;
            }

        }
    }
}