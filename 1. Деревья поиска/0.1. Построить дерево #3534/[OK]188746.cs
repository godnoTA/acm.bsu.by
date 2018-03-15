using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;


namespace Tree3
{
    class Program
    {
        static void Main(string[] args)
        {
            Tree tr = new Tree();
            using (StreamReader sr = new StreamReader("input.txt"))
            {
                tr.root = new Tree.Item(Convert.ToInt32(sr.ReadLine()));
                while (!sr.EndOfStream)
                {
                    int temp = Convert.ToInt32(sr.ReadLine());
                    tr.Insert(temp);
                }
            }
            using (StreamWriter sw = new StreamWriter("output.txt"))
            {
                tr.ShowLeft(tr.root, sw);
            }
        }
    }
    public class Tree
    {
        public class Item
        {
            public int info;
            public Item lSon, rSon, father;
            public Item(int x)
            {
                info = x;
                lSon = rSon = father = null;
            }



        }

        public Item root;

        public Tree()
        {
            root = null;
        }
        public void ShowLeft(Item t, StreamWriter SW)
        {
            if (t != null)
            {
                SW.WriteLine(t.info);
                ShowLeft(t.lSon, SW);
                ShowLeft(t.rSon, SW);
            }
        }
        public bool Find(int x, out Item p)
        {
            p = root;
            Item q = p;
            while (q != null)
            {
                p = q;
                if (q.info == x)
                    return true;
                if (q.info < x)
                    q = q.rSon;
                else
                    q = q.lSon;
            }
            return false;
        }

        public bool Find(int x)
        {
            Item p;
            return Find(x, out p);
        }
        public bool Insert(int x)
        {
            Item r, p;
            if (root == null)
            {
                r = new Item(x);
                root = r;
                return true;
            }
            if (Find(x, out r))
                return false;
            p = new Item(x);
            p.father = r;
            if (r.info < x)
                r.rSon = p;
            else
                r.lSon = p;
            return true;
        }
        public void deleteItem(Item x)
        {
            if (x.father == null)
                if (x.lSon != null)
                {
                    root = x.lSon;
                    x.lSon.father = null;
                }
                else
                {
                    root = x.rSon;
                    if (x.rSon != null)
                        x.rSon.father = null;
                }
            else
            if (x.father.lSon == x)
                if (x.lSon != null)
                {
                    x.father.lSon = x.lSon;
                    x.lSon.father = x.father;
                }
                else
                {
                    x.father.lSon = x.rSon;
                    if (x.rSon != null)
                        x.rSon.father = x.father;
                }
            else
                if (x.lSon != null)
            {
                x.father.rSon = x.lSon;
                x.lSon.father = x.father;
            }
            else
            {
                x.father.rSon = x.rSon;
                if (x.rSon != null)
                    x.rSon.father = x.father;
            }
            x.father = x.lSon = x.rSon = null;
        }
        public bool Delete(int x)
        {
            Item r, p;
            if (!Find(x, out r))
                return false;
            if ((r.lSon == null) || (r.rSon == null))
            {
                deleteItem(r);
                return true;
            }
            p = r.rSon;
            while (p.lSon != null)
                p = p.lSon;
            r.info = p.info;
            deleteItem(p);
            return true;
        }


    }
}
