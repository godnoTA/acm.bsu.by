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
            StreamReader f = new StreamReader("in.txt");
            StreamWriter vi = new StreamWriter("out.txt");               
            int kor = Convert.ToInt32(f.ReadLine());
            Tree.Node root  = new Tree.Node(kor);
            
            while (!f.EndOfStream)
            {
                int s = Convert.ToInt32(f.ReadLine());
                tree.MainInsert(root, s);
            }
            root = tree.height(root);
            Tree.Node root1 = root;
            //root = tree.Delet_Ver(root, num_del);
            int num_del = tree.MSL(root , root1);
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
                public int MSL;
                public int hight ;
                public int deep;
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
            public int deep1 = -1;
            public int hight1 = 0;
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
            public int min_deep;
            public int min_ver;
            public int Max_MSL;
            public int del;
            public Node Delet_Ver(Node node, int x)
            {
                if (x < node.key && node.left != null)
                {
                    node.left = Delet_Ver(node.left, x);
                }
                else if (x > node.key && node.right != null)
                {
                    node.right = Delet_Ver(node.right, x);

                }
                if (node.key == x)
                {
                    if (node.left == null && node.right == null)
                    {
                        node.key = 0;
                        node.father = 1;
                    }

                    if (node.right != null && node.left == null)
                    {
                        min_ver = node.right.key;
                        node.key = min_ver;
                        node.right.key = 0;
                        node.right.father = 1;
                    }
                    if (node.right != null && node.left != null)
                    {
                        min_ver = node.right.key;
                        FindMin(node.right);
                        node.key = min_ver;
                    }
                    
                    if (node.left != null && node.right == null)
                    {
                        min_ver = node.left.key;
                        node.key = min_ver;
                        node.left.key = 0;
                        node.left.father = 1;
                    }



                }
                return node;
            }

            public Node FindMin(Node node)
            {
                if (node.left == null)
                {
                    min_ver = node.key;
                    node.key = 0;
                    node.father = 1;
                }
                else
                    FindMin(node.left);

                return node;
            }

            public void Deepp(Node node)
            {
                if (node != null)
                {
                    deep1 += 1;
                    node.deep = deep1;
                    Deepp(node.left);
                    Deepp(node.right);
                }
            }
            public int MSL(Node node , Node helpp)
            {
                if (node != null)
                {
                    if (node.left != null && node.right != null)
                    {
                        node.MSL = node.left.hight + node.right.hight + 2;
                        if(node.MSL > Max_MSL)
                        {
                            Max_MSL = node.MSL;
                            min_deep = Sravn_MSL(node, helpp);                           
                            del = node.key;
                        }
                        if (node.MSL == Max_MSL)
                        {
                            Sravn_MSL(node , helpp);
                            min_deep = (min_deep > node.deep) ? min_deep : node.deep;
                        }

                    }
                    if(node.left != null && node.right == null)
                    {
                        node.MSL = node.left.hight + 1;
                        if (node.MSL > Max_MSL)
                        {
                            Max_MSL = node.MSL;
                            min_deep = Sravn_MSL(node, helpp);
                            del = node.key;
                        }
                        if (node.MSL == Max_MSL)
                        {
                            Sravn_MSL(node, helpp);
                            min_deep = (min_deep > node.deep) ? min_deep : node.deep;
                        }
                    }
                    if (node.left == null && node.right != null)
                    {
                        node.MSL = node.right.hight + 1;
                        if (node.MSL > Max_MSL)
                        {
                            Max_MSL = node.MSL;
                            min_deep = Sravn_MSL(node, helpp);
                            del = node.key;
                        }
                        if (node.MSL == Max_MSL)
                        {
                            Sravn_MSL(node, helpp);
                            min_deep = (min_deep > node.deep) ? min_deep : node.deep;
                        }
                    }
                    MSL(node.left , helpp);
                    MSL(node.right , helpp);

                }
                return del;
            }

            public int Sravn_MSL(Node node , Node helpp ) // вычисление глубины 
            {
                if (helpp != null)
                {
                    if (helpp.key < node.key)
                    {
                        node.deep += 1;
                        Sravn_MSL(node, helpp.right);
                    }
                    else if (helpp.key > node.key)
                    {
                        node.deep += 1;
                        Sravn_MSL(node, helpp.left);
                    }


                }
                return node.deep;
            }

            public Node height(Node node)
            {
                
                if (node != null){

                    height(node.left);                    
                    height(node.right);
                    if (node.left == null && node.right == null)
                    {
                        node.hight = 0;
                    }
                    if(node.right != null && node.left != null)
                    {
                        node.hight = (node.left.hight > node.right.hight) ? node.left.hight : node.right.hight;
                        node.hight += 1;
                    }
                    if (node.left != null && node.right == null)
                    {
                        node.hight = node.left.hight + 1;
                    } 
                    if (node.left == null && node.right != null)
                    {
                        node.hight = node.right.hight + 1;
                    }
                   
                    //hight1++;
                    //node.hight = hight1;
                }
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
