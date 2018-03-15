using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Списки_дуг
{
    class Program
    {
        static void Main(string[] args)
        {
            int N,q,j;
            string temp;
            int[] p;
            using (StreamReader sr = new StreamReader("input.txt"))
            {
                N = Convert.ToInt32(sr.ReadLine());
                p = new int[N+1];
                for (int i = 0; i < N-1; i++)
                {
                    temp = sr.ReadLine();
                    string [] w = temp.Split(' ');
                    q = Convert.ToInt32(w[0]);
                    j = Convert.ToInt32(w[1]);
                    p[j] = q;
                }
            }
            using (StreamWriter sw = new StreamWriter("output.txt"))
            {
                for (int i = 1; i <= N; i++)
                {
                    sw.Write(p[i].ToString() + ' ');
                }
            }
        }
    }
}
