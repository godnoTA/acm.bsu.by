using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace ConsoleApplication1
{
    class Program
    {
        public static int[] p;
        public static int[] rank;
        static void Main(string[] args)
        {
            int N, M, q,j;
            List<Tuple<int, int>> nerav = new List<Tuple<int, int>>();
            string result = "Yes";
            string temp;
            using (StreamReader sr = new StreamReader("equal-not-equal.in"))
            {
                temp = sr.ReadLine();
                string[] w = temp.Split(' ');
                N = Convert.ToInt32(w[0]);
                M = Convert.ToInt32(w[1]);
                p = new int[N+1];
                rank = new int[N + 1];
                for (int i=1;i < N+1;i++)
                {
                    p[i] = i;
                }

                for (int i=0; i < M; i++)
                {
                    temp = sr.ReadLine();
                    w = temp.Split(' ');
                    q = Convert.ToInt32(w[0].Substring(1));
                    j = Convert.ToInt32(w[2].Substring(1));
                    if (w[1][0] =='=')
                    {
                        Unite(q, j);
                    }
                    else
                    {
                        nerav.Add(Tuple.Create<int, int>(q, j));
                    }
                }
            }

            for (int i = 0; i < nerav.Count; i++)
            {
                q = nerav[i].Item1;
                j = nerav[i].Item2;
                if (Find(q) == Find(j))
                {
                    result = "No";
                    break;
                }
            }

            using (StreamWriter sw = new StreamWriter("equal-not-equal.out"))
            {
                sw.WriteLine(result);
            }
        }

        public static int Find(int x)
        {
            if (p[x] == x) return x;
            return p[x] = Find(p[x]);
        }

        public static void Unite(int x, int y)
        {
            x = Find(x);
            y = Find(y);
            if (rank[x] < rank[y])
                p[x] = y;
            else
            {
                p[y] = x;
                if (rank[x] == rank[y])
                    ++rank[x];
            }
        }
    }
}
