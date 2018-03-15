using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;


namespace Tree2
{
    class Program
    {
        static void Main(string[] args)
        {
            Tree tr = new Tree();
            List<Tree.Item> mylist = new List<Tree.Item>();
            int max, min;
            using (StreamReader sr = new StreamReader("in.txt"))
            {
                tr.root = new Tree.Item(Convert.ToInt32(sr.ReadLine()));
                while (!sr.EndOfStream)
                {
                    int temp = Convert.ToInt32(sr.ReadLine());
                    tr.Insert(temp);
                }
            }
            tr.root.calcHeight(tr.root);
            tr.root.calcPolyput(tr.root);
            max = tr.root.polyput;
            tr.root.FindN(tr.root, ref max);
            tr.root.FindItem(tr.root, max, ref mylist);
            tr.root.calcSum(mylist);
            min = mylist[0].sum;
            foreach(Tree.Item t in mylist)
            {
                min = Math.Min(min, t.sum);
            }
            int i = 0;
            for (i = 0; i <= mylist.Count && mylist[i].sum != min; i++) { }
            int kor = mylist[i].info;
            tr.Delete(kor);
            using (StreamWriter sw = new StreamWriter("out.txt"))
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
            public int height;
            public int polyput;
            public int sum;
            //public int depth, level;
            public Item(int x)
            {
                info = x;
                lSon = rSon = father = null;
            }
            public int calcHeight(Item i)
            {
                if (i != null)
                {
                    return i.height = Math.Max(calcHeight(i.lSon), calcHeight(i.rSon)) + 1;
                }
                else return -1;
            }
            public void calcPolyput(Item i)
            {
                if (i!=null)
                {
                    i.polyput = 0; 
                    if (i.lSon != null)
                    {
                        i.polyput += i.lSon.height;
                        i.polyput++;
                    }
                    if (i.rSon != null)
                    {
                        i.polyput += i.rSon.height;
                        i.polyput++;
                    }
                    calcPolyput(i.lSon);
                    calcPolyput(i.rSon);
                }

            }
            public void FindN(Item i, ref int max)
            {
                if (i != null)
                {
                    max = Math.Max(max, i.polyput);
                    FindN(i.lSon, ref max);
                    FindN(i.rSon, ref max);
                }
            }
            public void FindItem(Item i, int max, ref List<Item> mylist)
            {
                if (i != null)
                {
                    if (i.polyput == max)
                    {
                        mylist.Add(i);
                    }
                    FindItem(i.lSon, max, ref mylist);
                    FindItem(i.rSon, max, ref mylist);
                }
            }
            public void calcSum(List<Item> mylist)
            {
                foreach(Item q in mylist)
                {
                    if (q.lSon != null)
                    {
                        Item it = q.lSon;
                        while (it.height != 0)
                        {
                            if (it.lSon!= null)
                            {
                                if (it.lSon.height == (it.height - 1))
                                    it = it.lSon;
                                else it = it.rSon;
                            }
                            else it = it.rSon;
                        }
                        q.sum = it.info;
                    }
                    else q.sum = q.info;
                    if (q.rSon != null)
                    {
                        Item it = q.rSon;
                        while (it.height != 0)
                        {
                            if (it.lSon!= null)
                            {
                                if (it.lSon.height == (it.height - 1)){
                                    it = it.lSon;
                                }
                                else it = it.rSon;
                            }
                            else it = it.rSon;
                        }
                        q.sum += it.info;
                    }
                    else
                    {
                        if (q.lSon != null)
                            q.sum += q.info;
                    }
                }
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
