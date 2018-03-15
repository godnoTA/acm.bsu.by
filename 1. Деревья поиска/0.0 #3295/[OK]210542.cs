using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace ConsoleApplication1sdfh
{

    public class BinaryTree
    {
        public long? Data { get; private set; }
        public BinaryTree Left { get; set; }
        public BinaryTree Right { get; set; }
        public BinaryTree Parent { get; set; }

        /// <summary>
        /// Вставляем значение в дерево
        /// </summary>
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

        /// <summary>
        /// Вставляет значение в определённый узел дерева
        /// </summary>
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

        public BinaryTree Find(long data)
        {
            if (Data == data) return this;
            if (Data > data)
            {
                return Find(data, Left);
            }
            return Find(data, Right);
        }
        /// <summary>
        /// Ищет значение в определённом узле
        /// </summary>
        /// <param name="data">Значение для поиска</param>
        /// <param name="node">Узел для поиска</param>
        /// <returns></returns>
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

        public class BinaryTreeSum
        {
            public static long sum;
            static BinaryTreeSum() { sum = 0; }
            public static void TreeSumm(BinaryTree node)
            {
                if (node != null)
                {
                    if (node.Parent == null)
                    {
                        sum += (long)node.Data;
                    }
                    else
                    {
                        if (node.Parent.Left == node)
                        {
                            sum += (long)node.Data;
                        }

                        if (node.Parent.Right == node)
                        {
                            sum += (long)node.Data;
                        }
                    }
                    if (node.Left != null)
                    {
                        TreeSumm(node.Left);
                    }
                    if (node.Right != null)
                    {
                        TreeSumm(node.Right);
                    }
                }
            }
        }
        class Program
        {
            static void Main(string[] args)
            {
                var tree = new BinaryTree();
                string line;
                System.IO.StreamReader file = new System.IO.StreamReader("input.txt");
                while ((line = file.ReadLine()) != null)
                {
                    tree.Insert((long)Int32.Parse(line));
                }
                BinaryTreeSum.TreeSumm(tree);
                StreamWriter sw = new StreamWriter("output.txt");
                sw.WriteLine(BinaryTreeSum.sum);
                file.Close();
                sw.Close();
                Console.ReadLine();
            }
        }
    }
}
