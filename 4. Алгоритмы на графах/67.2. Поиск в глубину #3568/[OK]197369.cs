using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;


namespace Поиск_в_глубину
{
    class Program
    {
        static void Main(string[] args)
        {
            
            int N, j, m = 0;
            string temp;
            int[,] graph;
            bool[] visit;
            int[] met;
            int[] path;
            Stack<int> lifo = new Stack<int>();
            using (StreamReader sr = new StreamReader("input.txt"))
            {
                N = Convert.ToInt32(sr.ReadLine());
                graph = new int[N + 1, N + 1];
                visit = new bool[N + 1];
                met = new int[N + 1];
                path = new int[N + 1];
                for (int i = 1; i <= N; i++)
                {
                    visit[i] = false;
                    met[i] = 0;
                    path[i] = 0;
                }


                for (int i = 1; i <= N; i++)
                {
                    temp = sr.ReadLine();
                    string[] w = temp.Split(' ');
                    for (j = 1; j <= N; j++)
                    {
                        graph[i, j] = Convert.ToInt32(w[j - 1]);
                    }
                }
            }

            for (int i=1; i <= N; i++)
            {
                if (visit[i] == false)
                {
                    lifo.Push(i);
                    visit[i] = true;
                    m++;
                    met[i] = m;
                    while(lifo.Count != 0)
                    {
                        int v = lifo.Peek();
                        for (int k = 1; k <= N; k ++)
                        {
                            if (graph[v, k] == 1 && met[k] == 0)
                            {
                                graph[v, k] = 0;
                                lifo.Push(k);
                                visit[k] = true;
                                m++;
                                met[k] = m;
                                break;
                            }
                            if (k == N)
                                lifo.Pop();
                        }
                    }
                }
            }

            using (StreamWriter sw = new StreamWriter("output.txt"))
            {
                for (int i = 1; i <= N; i++)
                {
                    sw.Write(met[i].ToString() + ' ');
                }
            }
        }
    }
}
