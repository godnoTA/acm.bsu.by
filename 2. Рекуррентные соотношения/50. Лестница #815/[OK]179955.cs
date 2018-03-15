using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Numerics;

namespace FloorsS
{
    class Program
    {
        static void Main(string[] args)
        {
            StreamReader read = new StreamReader("input.txt");
            StreamWriter write = new StreamWriter("output.txt");

            string[] s = (read.ReadLine()).Split(' ');
            int N = int.Parse(s[0]);
            int K = int.Parse(s[1]);

            BigInteger[] P = new BigInteger[K];
            BigInteger[] S = new BigInteger[N];

            bool allowCycleP = true;
            bool allowCycleS = true;
            S[0] = P[0] = 1;
            if (K == 1)
                allowCycleP = false;
            else if (K == 2)
            {
                P[1] = 2;
                allowCycleP = false;
            }
            else
                P[1] = 2;
            if (N == 1)
                allowCycleS = false;
            if (allowCycleP)
            {
                for (int i = 2; i < K; i++)
                    P[i] = P[i - 1] + P[i - 2];
            }
            BigInteger l = P[K - 1] * P[K - 1];
            if (allowCycleS)
            {
                for (int i = 1; i < N; i++)
                    S[i] = S[i - 1] * (l + 1);
            }

            BigInteger a = new BigInteger(1000000009);
            write.Write(S[N - 1] % a);
            read.Close();
            write.Close();
        }
    }
}