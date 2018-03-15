using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

struct Time
{
    public Time(int a, int b, int c)
    {
        A = a;
        B = b;
        C = c;
    }
    public int A;
    public int B;
    public int C;
}


namespace hilllman
{
    class Program
    {
        static void Main(string[] args)
        {
            List<ulong> values;
            System.IO.StreamReader input = new System.IO.StreamReader("input.txt");
            System.IO.StreamWriter output = new System.IO.StreamWriter("output.txt") { AutoFlush = true };
            ulong N = UInt64.Parse(input.ReadLine());

            ulong num;
            ulong deg;
            values = new List<ulong>();
            while (N > 0)
            {
                num = 1;
                deg = 0;
                while (num*2 <= N)
                {
                    num *=2;
                    ++deg;
                }

                N = N - num;
                values.Add(deg);
            }
            values.Sort();
            foreach(ulong a in values)
                output.Write(a + "\n");
            output.Close();
        }
    }
}
