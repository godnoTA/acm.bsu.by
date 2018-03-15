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
            //StreamReader prov = new StreamReader("input.txt");
            StreamWriter vi = new StreamWriter("output.txt");
            int num_del = Convert.ToInt32(f.ReadLine());
            f.ReadLine();
            //prov.ReadLine();
            //prov.ReadLine();
            //if (prov.ReadLine() == null)
            //{
            //    if (prov.ReadLine() == null)
            //    {
            //        Environment.Exit(0);
            //    }

            //}
            int kor = Convert.ToInt32(f.ReadLine());           
            Tree.Node root = new Tree.Node(kor);
            while (!f.EndOfStream)
            {
                int s = Convert.ToInt32(f.ReadLine());
                tree.MainInsert(root, s);
            }

            root = tree.Delet_Ver(root, num_del);        

            //tree.wr();
            tree.OutFile(root, vi);
            vi.Close();
            //Console.WriteLine(root.left.father.key);

        }

        public class Tree
        {
            public bool i = true;
            public class Node
            {
                public int father;
                public int key;
                public Node left;
                public Node right;
                public Node(int x)
                {
                    key = x;                 
                }
                public Node()
                {
                    
                }
                
            }
            public Node f;
           
            public void OutFile(Node node, StreamWriter vi)
            {
                if (node != null)
                {
                    if (node.father == 0)
                    {
                        vi.WriteLine(node.key);
                    }
                    OutFile(node.left, vi);
                    OutFile(node.right, vi);
                }

            }
            public int min_ver;
            
            public Node Delet_Ver(Node node, int x )
            {
                if (x < node.key && node.left != null)
                {
                    node.left = Delet_Ver(node.left, x );
                }
                else if (x > node.key && node.right != null)
                {
                    node.right = Delet_Ver(node.right, x);

                }
                if (node.key == x)
                {
                    if(node.left == null && node.right == null)
                    {
                        node.key = 0;
                        node.father = 1;
                    }
                    
                    if(node.right != null && node.left == null)
                    {
                        min_ver = node.right.key;
                        node.key = min_ver;
                        node.right.key = 0;
                        node.right.father = 1;
                    }
                    if (node.right != null && node.left != null) { 
                        min_ver = node.right.key;
                        FindMin(node.right);
                        node.key = min_ver;
                    }
                    //if (node.right != null )
                    //{
                    //min_ver = node.right.key;
                    //FindMin(node.right);                                                        
                    //node.key = min_ver;
                    //}
                    if (node.left != null && node.right == null) {
                        min_ver = node.left.key;
                        node.key = min_ver;
                        node.left.key = 0;
                        node.left.father = 1;
                    }      
                                        
                    

                }
                return node;
            }     
                      
            public Node FindMin(Node node )
            {
                if (node.left == null)
                {
                    min_ver = node.key;                    
                    node.key = 0;
                    node.father = 1;                    
                }else
                    FindMin(node.left);

                return node;
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