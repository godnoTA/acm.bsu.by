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
            System.IO.StreamReader input = new System.IO.StreamReader("input.txt");
            System.IO.StreamWriter output = new System.IO.StreamWriter("output.txt") { AutoFlush = true };
            int N = Int32.Parse(input.ReadLine());

            List<Time> time = new List<Time>(N + 1);
            time.Add(new Time(0, 0, 0));
            string tmp;
            string[] tmparr;
            for (int i = 1; i <= N; i++)
            {
                tmp = input.ReadLine();
                tmparr = tmp.Split(' ');
                time.Add(new Time(Int32.Parse(tmparr[0]), Int32.Parse(tmparr[1]), Int32.Parse(tmparr[2])));
            }
            List<int> d = new List<int>(N + 1);
            d.Add(0);
            d.Add(time[1].A);
            if(N==1)
            {
                output.Write(d[N]);
                output.Close();
                return;
            }
            d.Add(Math.Min(d[1] + time[2].A, time[1].B));
            if (N == 2)
            {
                output.Write(d[N]);
                output.Close();
                return;

            }
            d.Add(Math.Min(d[2] + time[3].A, Math.Min(d[1] + time[2].B, time[1].C)));
            if (N == 3)
            {
                output.Write(d[N]);
                output.Close();
                return;

            }

            for (int i = 4; i <= N; i++)
                d.Add(Math.Min(d[i - 1] + time[i].A, Math.Min(d[i - 2] + time[i - 1].B, d[i - 3] + time[i - 2].C)));

            output.Write(d[N]);
            output.Close();
        }
    }
}
