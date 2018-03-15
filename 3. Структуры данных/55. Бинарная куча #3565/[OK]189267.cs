using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Kucha
{
    class Program
    {
        static void Main(string[] args)
        {
            bool res = true;
            using (StreamReader sr = new StreamReader("input.txt"))
            {
                long N = Convert.ToInt32(sr.ReadLine());
                long[] table = new long[N + 1];
                long i = 1;
                string s = sr.ReadLine();
                string [] temp = s.Split(' ');
                for (long j =0; j < temp.Length; j++)
                {
                    table[i] = Convert.ToInt32(temp[j]);
                    i++;
                }
                for (long j =1; j < (N+1)/2; j++)
                {
                    if (table[j] <= table[2 * j] && table[j] <= table[2 * j + 1])
                    {

                    }
                    else res = false;
                }
                for (long j = N; j >= (N + 1) / 2; j--)
                {
                    if (table[j] >= table[j / 2]) { }
                    else res = false;
                }
            }
            using (StreamWriter sw = new StreamWriter("output.txt"))
            {
                if (res)
                {
                    sw.WriteLine("Yes");
                }
                else
                {
                    sw.WriteLine("No");
                }
          
                
            }
        }
    }
}
