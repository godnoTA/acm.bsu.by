using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Collections;

namespace cons
{
    class Program
    {
        static void Main(string[] args)
        {
            StreamReader f = new StreamReader("input.txt");
            StreamWriter vi = new StreamWriter("output.txt");
            long N = Convert.ToInt64(f.ReadLine());
            ArrayList ab = new ArrayList();
            long buff = 0;
            while (N != 1)
            {
                buff = N % 2;
                ab.Add(buff);
                N=N/2;
                //if (N == 1)
                //{
                //    ab.Add(N);
                //    break;
                //}
            }
            ab.Add(N);
            long O = 0;
            foreach (long of in ab)
            {
                if(of == 1)
                {
                    vi.WriteLine(O);
                }
                O++;
            }
            vi.Close();
        }
    }
}
