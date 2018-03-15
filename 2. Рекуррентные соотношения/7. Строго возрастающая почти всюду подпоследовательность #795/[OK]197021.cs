using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Posledovatelnost
{
    class Program
    {
        static void Main(string[] args)
        {
            
            int n;
            int[] temp;
            int res = 0;
            int[][] L = new int[2][];
            int r;
            
            using (StreamReader sr = new StreamReader("input.txt"))
            {
                n = Convert.ToInt32(sr.ReadLine());
                temp = new int[n];
                string[] c = sr.ReadLine().Split(' ');
                for (int i = 0; i < n; i++)
                {
                    temp[i] = Convert.ToInt32(c[i]);
                }
            }
            L[0] = new int[n];
            L[1] = new int[n];

            List<int> d = new List<int>();
            int length = 0;
            d.Add(-1);
            for (int i = 1; i <= n; i++)
            {
                d.Add(Int32.MaxValue);
            }
            for (int i = 0; i < n; i++)
            {
                int j = d.BinarySearch(temp[i]);
                if (j < 0)
                {
                    j = ~j;
                    if (d[j - 1] < temp[i] && temp[i] < d[j] )
                    {
                        d[j] = temp[i];
                        length = Math.Max(length, j);
                    }
                }
                L[0][i] = length;
            }

            r = length;
            List<int> w = new List<int>();
            length = 0;

            w.Add(Int32.MinValue);
            for (int i = 1; i <= n + 1; i++)
            {
                w.Add(Int32.MaxValue);
            }
            
            for (int i = n-1; i >= 0; i--)
            {
                int j = w.BinarySearch(temp[i]*(-1));
                if (j < 0)
                {
                    j = ~j;
                    if (w[j + 1] > temp[i]*(-1) && temp[i]*(-1) < w[j])
                    {
                        w[j] = (-1)*temp[i];
                        length = Math.Max(length, j);
                    }
                }
                L[1][i] = length;
            }
            int[] B1max = new int[n];
            B1max[0] = L[0][0];
            int[] B2max = new int[n];
            B2max[n-1] = L[1][n-1];
            
            for (int i = 1; i < n; i++)
                B1max[i] = B1max[i - 1] > L[0][i] ? B1max[i - 1] : L[0][i];

            for (int i = n - 2; i >= 0; i--)
                B2max[i] = B2max[i + 1] > L[1][i] ? B2max[i + 1] : L[1][i];
            for (int i = 0; i < n; i++)
                res = Math.Max(res, B1max[i] + B2max[i]);

            if (res > n)
                res = n;

            using (StreamWriter sw = new StreamWriter("output.txt"))
            {
                sw.WriteLine(res);
            }
        }

        static int search (int[] mass, int index, int length, int value )
        {
            bool b;
            int k = 0;
            int i = (index + length) / 2;
            if (mass[i] == value)
                return i;
            if (mass[i] < value)
            {
                while(i >= index)
                {
                    if (mass[i] == value)
                        return i;
                    i--;
                }
                return -1;
            }
            else
            {
                while (i < index + length)
                {
                    if (mass[i] == value)
                        return i;
                    i++;
                }
                return -1;
            }
        }

        

    }
}