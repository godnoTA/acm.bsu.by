using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Поиск_в_ширину
{
    class Program
    {
        static void Main(string[] args)
        {

            bool z = true;
            int N, q, j, m = 1 ;
            string temp;
            int[,] graph;
            bool[] visit;
            int[] met;
            int[] path;
            Queue<int> fifo = new Queue<int>();
            using (StreamReader sr = new StreamReader("input.txt"))
            {
                N = Convert.ToInt32(sr.ReadLine());
                graph = new int[N+1, N+1];
                visit = new bool[N+1];
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
                        graph[i, j] = Convert.ToInt32(w[j-1]);
                    }
                }
            }
            /*fifo.Enqueue(1);
            while (z)
            {
                q = fifo.Dequeue();
                visit[q] = true;
                met[q] = m;
                m++;
                for (j = 1; j < N; j ++)
                {
                    if (graph[q, j] == 1 && visit[j] == false)
                    {
                        fifo.Enqueue(j);
                        visit[j] = true;

                    }
                        
                }
                if (fifo.Count == 0 )
                {
                    z = false;
                    for (int i = 1; i <= N; i++)
                    {
                        if (visit[i] == false)
                        {
                            fifo.Enqueue(i);
                            z = true;
                            break;
                        }
                    }
                }

            }*/

            for (int i = 1; i <= N; i++)
            {
                if (fifo.Count == 0)
                {
                    if (visit[i] == false)
                        fifo.Enqueue(i);
                    visit[i] = true;
                }
                if (fifo.Count != 0)
                {
                    while (fifo.Count != 0)
                    {
                        int del = fifo.Dequeue();
                        met[del] = m;
                        m++;
                        for (j = 1; j <= N; j++)
                            if (graph[del,j] == 1 && visit[j] == false)
                            {
                                fifo.Enqueue(j);
                                visit[j] = true;
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
