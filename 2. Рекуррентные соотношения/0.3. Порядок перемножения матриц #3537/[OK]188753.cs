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
            List<MatrixSize> matrixSizes = new List<MatrixSize>(n);

            int a, b;
            while ((tmp = input.ReadLine()) != null)
            {
                tmparr = tmp.Split(' ');
                matrixSizes.Add(new MatrixSize()
                {
                    m = Int32.Parse(tmparr[0]),
                    n = Int32.Parse(tmparr[1]),
                });
            }
            input.Close();

            int[,] F = new int[n, n];

            int minvalue;
            for (int j = 1; j < n; ++j)
            {
                for (int i = j - 1; i >= 0; --i)
                {
                    minvalue = Int32.MaxValue;
                    for (int k = i; k < j; k++)
                        minvalue = Math.Min(minvalue, F[i,k] + F[k + 1,j] + matrixSizes[i].m * matrixSizes[k].n * matrixSizes[j].n);
                    F[i,j] = minvalue;
                }
            }
            output.Write(F[0, n - 1]);
            output.Close();
        }
    }
}
