using System;
using System.Collections.Generic;
using System.IO;

namespace alg_str2
{
    class Program
    {
        static void Main(string[] args)
        {
            StreamReader fread = new StreamReader("in.txt");
            StreamWriter fwrite = new StreamWriter("out.txt");
            string[] s = (fread.ReadLine()).Split(' ');
            int N = int.Parse(s[0]);
            int M = int.Parse(s[1]);
            int[,] reactions = new int[N, N];
            for (int i = 0; i < N; i++)
            {
                string[] a = (fread.ReadLine()).Split(' ');
                for (int j = 0; j < N; j++)
                    reactions[i, j] = int.Parse(a[j]);
            }
            s = (fread.ReadLine()).Split(' ');
            int[] input = new int[M];
            for (int i = 0; i < M; i++)
                input[i] = int.Parse(s[i]);

            Stack<int> result = new Stack<int>();

            result.Push(input[0]);
            for (int i = 1; i < M; i++)
                if (reactions[result.Peek() - 1, input[i] - 1] == 0)
                    result.Push(input[i]);
                else
                {
                    int a = reactions[result.Pop() - 1, input[i] - 1];
                    while (result.Count != 0 && reactions[result.Peek() - 1, a - 1] != 0)
                    {
                        result.Push(reactions[result.Pop() - 1, a - 1]);
                        a = result.Pop();
                    }
                    result.Push(a);
                }

            while (result.Count != 1)
                fwrite.Write(result.Pop() + " ");
            fwrite.Write(result.Pop());

            fread.Close();
            fwrite.Close();
        }
    }
}