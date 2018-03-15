using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Канон_вид
{
    class Program
    {
        static void Main(string[] args)
        {
            int N, q, j;
            string temp;
            int[,] p;
            using (StreamReader sr = new StreamReader("input.txt"))
            {
                N = Convert.ToInt32(sr.ReadLine());
                p = new int[N,N];
                for (int i = 0; i < N ; i++)
                {
                    temp = sr.ReadLine();
                    string[] w = temp.Split(' ');
                    for (j=0; j < N; j++)
                    {
                        p[i, j] = Convert.ToInt32(w[j]);
                    }
                }
            }
            using (StreamWriter sw = new StreamWriter("output.txt"))
            {
                for (int i = 0; i < N; i++)
                {
                    for (j=0; j < N; j++)
                    {
                        if (p[j, i] == 1)
                        {
                            sw.Write((j + 1).ToString() + ' ');
                            break;
                        }
                            
                    }
                    if (j == N)
                        sw.Write("0 ");
                }
            }
        }
    }
}
