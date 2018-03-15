using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication1
{
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
                left = null;
                right = null;
            } 

        }
        public Node root;
        public Tree() { }
        public void insert(int x)
        {
            Node parent = null;
            Node v = this.root;
            while (v != null)
            {
                parent = v;
                if (x < v.key)
                    v = v.left;
                else if (x > v.key)
                    v = v.right;
                else return;

            }
            Node w = new Node(x);
            if (parent == null)
                this.root = w;
            else if (x < parent.key)
                parent.left = w;
            else if (x > parent.key)
                parent.right = w;   
        }
        public static void detour(StreamWriter writer, Node l)
        {
           
            if (l != null) {
                writer.WriteLine(l.key);
                detour(writer, l.left);
                detour(writer, l.right);

            }
            
        }
    }
    class Program
    {
        static void Main(string[] args)
        {
            FileStream input = new FileStream("input.txt", FileMode.Open
, FileAccess.Read);
            FileStream output = new FileStream("output.txt", FileMode.Create
, FileAccess.ReadWrite);
            StreamReader reader = new StreamReader(input);
            Tree test=new Tree();
            string st = reader.ReadLine();
             while (st!=null)
            {
                test.insert(Convert.ToInt32(st));
                st = reader.ReadLine();
            }
            StreamWriter writer = new StreamWriter(output);
            Tree.detour(writer, test.root);
            writer.Close();
            input.Close();
            output.Close();


        }
        
    }
}
