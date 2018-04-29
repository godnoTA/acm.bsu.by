using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _0
{
    class Program
    {
        static int k = 0;

        static void bfs(int s, int[,] g, int[] met, int n) {
            met[s] = ++k;
            Queue<int> q = new Queue<int>();
            q.Enqueue(s);
            while (q.Count != 0) {
                int v = q.Dequeue();
                for (int i = 0; i < n; ++i) {
                    if (g[v, i] == 1 && met[i] == 0) {
                        met[i] = ++k;
                        q.Enqueue(i);
                    }
                }
            }
        }

        static void Main(string[] args)
        {
            StreamReader input = new StreamReader("input.txt");
            StreamWriter output = new StreamWriter("output.txt");
            HashSet<long> set = new HashSet<long>();
            int n = int.Parse(input.ReadLine());
            int[,] g = new int[n, n];
            int[] met = new int[n];
            for (int i = 0; i < n; ++i) {
                met[i] = 0;
                String value = input.ReadLine();
                String[] substrings = value.Split();
                for(int j = 0; j < n; ++j) {
                    if (substrings[j] == "1")
                    {
                        g[i, j] = 1;
                    }
                    else {
                        g[i, j] = 0;
                    }
                }
            }
            for (int i = 0; i < n; ++i) {
                if (met[i] == 0) {
                    bfs(i, g, met, n);
                }
            }
            String str = "";
            for (int i = 0; i < n; ++i) {
                str += met[i].ToString() + " ";
            }
            output.Write(str);
            output.Close();
        }
    }
}
