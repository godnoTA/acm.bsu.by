using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace BinomKucha
{
    class Program
    {
        static void Main(string[] args)
        {
            long  N;
            long temp=1;
            using (StreamReader sr = new StreamReader("input.txt"))
            {
                N = Convert.ToInt64(sr.ReadLine());
            }
            temp = N;
            using (StreamWriter sw = new StreamWriter("output.txt"))
            {
                long i = 0;
                while (temp != 0)
                {
                    
                    if (temp % 2 == 1)
                        sw.WriteLine(i);
                    temp = temp / 2;
                    i++;
                }
                
            }

        }
    }
}
