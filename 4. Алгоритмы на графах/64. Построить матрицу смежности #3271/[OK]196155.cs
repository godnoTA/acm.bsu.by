using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Матрица_смежности
{
    class Program
    {
        static void Main(string[] args)
        {
            int N, M, q, j;
            int[,] graph;
            string temp;
            using (StreamReader sr = new StreamReader("input.txt"))
            {
                temp = sr.ReadLine();
                string[] w = temp.Split(' ');
                N = Convert.ToInt32(w[0]);
                M = Convert.ToInt32(w[1]);
                graph= new int[N,N];
                for (int i = 0; i < M; i++)
                {
                    temp = sr.ReadLine();
                    w = temp.Split(' ');
                    q = Convert.ToInt32(w[0]);
                    j = Convert.ToInt32(w[1]);
                    graph[q-1, j-1] = 1;
                    graph[j-1,q-1] = 1;
                }
            }

            using (StreamWriter sw = new StreamWriter("output.txt"))
            {
                for (int i=0; i < N; i++)
                {
                    for (j=0; j < N; j++)
                    {
                        sw.Write(graph[i, j].ToString() + ' ');
                    }
                    sw.WriteLine();
                }
                 

            }
        }
    }
}
