using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace hilllman
{
    public struct MatrixSize
    {
        public int m;
        public int n;
    }

    class Program
    {
        static void Main(string[] args)
        {
            System.IO.StreamReader input = new System.IO.StreamReader("input.txt");
            System.IO.StreamWriter output = new System.IO.StreamWriter("output.txt") { AutoFlush = true };

            string tmp;
            string[] tmparr;
            int n = Int32.Parse(input.ReadLine());
            if (n == 1)
            {
                output.Write("Yes");
                output.Close();
                return;
            }
            List<int> mas = new List<int>(n + 1);
            mas.Add(0);//unused element ; 
            tmp = input.ReadLine();
            input.Close();
            tmparr = tmp.Split(' ');
            for (int i = 1; i <= n; i++)
                mas.Add(Int32.Parse(tmparr[i - 1]));

            for (int i = 1; i < n / 2; i++)
            {
                if (mas[i] > mas[i * 2] || mas[i] > mas[i * 2 + 1])
                {
                    output.Write("No");
                    output.Close();
                    return;
                }
            }

            int last = n / 2;
            if (mas[last] > mas[last * 2])
            {
                output.Write("No");
                output.Close();
                return;
            }
            else if (last * 2 + 1 <= n && mas[last] > mas[last * 2 + 1])
            {
                output.Write("No");
                output.Close();
                return;
            }
            output.Write("Yes");
            output.Close();
        }
    }
}
