using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using ConsoleApplication1sdfh;

namespace ConsoleApplication1sdfh
{
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
        public BinaryTree FindMin(BinaryTree node)
        {
            BinaryTree OurMin = node.Right;
            if (OurMin.Left == null)
                return OurMin;
            while (OurMin.Left != null)
                OurMin = OurMin.Left;
            return OurMin;
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
        public long CountElements()
        {
            return CountElements(this);
        }
        /// <summary>
        /// Количество элементов в определённом узле
        /// </summary>
        /// <param name="node">Узел для подсчета</param>
        /// <returns></returns>
        private long CountElements(BinaryTree node)
        {
            long count = 1;
            if (node.Right != null)
            {
                count += CountElements(node.Right);
            }
            if (node.Left != null)
            {
                count += CountElements(node.Left);
            }
            return count;
        }

        private BinSide? MeForParent(BinaryTree node)
        {
            if (node.Parent == null) return null;
            if (node.Parent.Left == node) return BinSide.Left;
            if (node.Parent.Right == node) return BinSide.Right;
            return null;
        }
        public void Delete(BinaryTree OurKey)
        {
            if (OurKey == null) return;
            var me = MeForParent(OurKey);
            //Если у узла нет дочерних элементов, его можно смело удалять
            if (OurKey.Left == null && OurKey.Right == null)
            {
                if (me == BinSide.Left)
                {
                    OurKey.Parent.Left = null;
                }
                else
                {
                    OurKey.Parent.Right = null;
                }
                return;
            }
           

            if (OurKey.Left == null)
            {
                if (me == BinSide.Left)
                {
                    OurKey.Parent.Left = OurKey.Right;
                }
                else
                {
                    OurKey.Parent.Right = OurKey.Right;
                }

                OurKey.Right.Parent = OurKey.Parent;
                return;
            }


            if (OurKey.Right == null)
            {
                if (me == BinSide.Left)
                {
                    OurKey.Parent.Left = OurKey.Left;
                }
                else
                {
                    OurKey.Parent.Right = OurKey.Left;
                }

                OurKey.Left.Parent = OurKey.Parent;
                return;
            }

            BinaryTree ToChanged = FindMin(OurKey);
            OurKey.Data = ToChanged.Data;
            Delete(ToChanged);
        }
    }

    public class BinaryTreeSum
    {
        public static void TreeSumm(BinaryTree node, List<long> mas)
        {

            if (node != null)
            {
                if (node.Parent == null)
                {
                    mas.Add((long)node.Data);
                }
                else
                {
                    if (node.Parent.Left == node)
                    {
                        mas.Add((long)node.Data);
                    }

                    if (node.Parent.Right == node)
                    {
                        mas.Add((long)node.Data);
                    }
                }
                if (node.Left != null)
                {
                    TreeSumm(node.Left, mas);
                }
                if (node.Right != null)
                {
                    TreeSumm(node.Right, mas);
                }
            }
        }
    }
    public class TreeHeight
    {
        public static int Height(BinaryTree node)
        {
                if (node == null) return 0;
                //находим высоту правой и левой ветки, и из них берем максимальную
                //todo если дерево не бинарное, то для поиска макс ветки реализовать цикл
                return 1 + Math.Max(Height(node.Left), Height(node.Right));
            }
    }

    public class BinaryTreeTask
    {
       public static int i_Hight = 0;
        public static int count = 0, countRight=0;
        public static void TreeSumm(BinaryTree node, List <BinaryTree> mas,int Hight)
        {

            
            if (node != null)
            {
                if (i_Hight == Hight / 2)
                    mas.Add(node);
                if (node.Left != null)
                {
                    i_Hight++;
                    TreeSumm(node.Left, mas,Hight);
                }
                if (node.Right != null)
                {
                    i_Hight++;
                    TreeSumm(node.Right, mas,Hight);
                }
            }
            i_Hight--;
        }
        public static void SadMan(BinaryTree node, Dictionary<BinaryTree, int[]> mas)
        {
            int Left=0, Right=0;
            if (node.Left != null)
                CountItems(node.Left,ref Left);
            if (node.Right != null)
               CountItems(node.Right,ref Right);
            mas.Add(node, new int[] { Left, Right });
        }

        public static void CountItems(BinaryTree node, ref int count)
        {
            count++;
            if (node.Left != null)
                CountItems(node.Left, ref count);
            if (node.Right != null)
                CountItems(node.Right, ref count);
        }
    }

   
}

    
    class Program
    {
        static void Main(string[] args)
        {
            var tree = new BinaryTree();
            string line;
            System.IO.StreamReader file = new System.IO.StreamReader("in.txt");
            while ((line = file.ReadLine()) != null)
            {
                tree.Insert((long)Int32.Parse(line));
            }

        int Height = TreeHeight.Height(tree);
        List<BinaryTree> Tree = new List<BinaryTree> { };
        BinaryTreeTask.TreeSumm(tree, Tree, Height);
        Dictionary<BinaryTree, int[]> End = new Dictionary<BinaryTree, int[]> { };
        
        foreach (var item in Tree)
        {
            BinaryTreeTask.SadMan(item, End);
        }
        foreach(var item in End)
        {
            if (item.Value[0] <= item.Value[1])
                Tree.Remove(item.Key);
        }
        End.Clear();
        List<long> End_2 = new List<long> { };
        foreach (var item in Tree)
        {
            End_2.Add((long)item.Data);
        }
        End_2.Sort();
        List<long> Items = new List<long> { };
        if(End_2.Count()%2==0)
        {
          BinaryTreeSum.TreeSumm(tree, Items);
        }
        else
        {
            tree.Delete(tree.Find(End_2[End_2.Count()/2]));
            BinaryTreeSum.TreeSumm(tree, Items);
        }
        StreamWriter sw = new StreamWriter("out.txt");
        foreach (var item in Items)
        {
            sw.WriteLine(item);
        }

        file.Close();
        sw.Close();
        Console.ReadLine();
    }
}