using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Список_смежности
{
    class Program
    {
        static void Main(string[] args)
        {
            int N, M, q, j;
            List<int>[] graph;
            List<int>[] mass;
            string temp;
            using (StreamReader sr = new StreamReader("input.txt"))
            {
                temp = sr.ReadLine();
                string[] w = temp.Split(' ');
                N = Convert.ToInt32(w[0]);
                M = Convert.ToInt32(w[1]);
                mass = new List<int>[N+1];
                for (int i = 1; i < N + 1; i++)
                    mass[i] = new List<int>();
                for (int i = 0; i < M; i++)
                {
                    temp = sr.ReadLine();
                    w = temp.Split(' ');
                    q = Convert.ToInt32(w[0]);
                    j = Convert.ToInt32(w[1]);
                    mass[q].Add(j);
                    mass[j].Add(q);
                }

                
            }

            using (StreamWriter sw = new StreamWriter("output.txt"))
            {
                for (int i =1; i <= N; i++)
                {
                    sw.Write(mass[i].Count.ToString() +' ');
                    for (j=0; j<mass[i].Count;j++)
                    {
                        sw.Write(mass[i][j].ToString() + ' ');
                    }
                    sw.WriteLine();
                }


            }
        }
    }
}
