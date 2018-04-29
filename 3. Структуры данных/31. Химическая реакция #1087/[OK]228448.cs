using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Collections.Specialized;

namespace ConsoleApplication4
{
    class Program
    {
        static void Main(string[] args)
        {
            var allLines = File.ReadAllLines("in.txt");
            int n = Convert.ToInt32(allLines[0].Split()[0]);
            int m = Convert.ToInt32(allLines[0].Split()[1]);
            int[,] matr = new int[n, n];
            for (int i = 0; i < n; i++)
            {
                var line = allLines[i + 1].Split();
                for (int j = 0; j < n; j++)
                {
                    matr[i, j] = Convert.ToInt32(line[j]);
                }
            }
            Stack<int> chemistry = new Stack<int>();
            var lastLine = allLines[allLines.Count() - 1].Split();
            for (int i = lastLine.Count() - 1; i >= 0; i--)
            {
                chemistry.Push(Convert.ToInt32(lastLine[i]));
            }
            List<int> probirka = new List<int>();
            for (int i = 0; i < m; i++)
            {
                probirka.Add(chemistry.Peek());
                chemistry.Pop();
                if (i == 0)
                    continue;
                if (matr[probirka[probirka.Count-1] - 1, probirka[probirka.Count - 2] - 1] == 0)
                    continue;
                for (int j = probirka.Count-1; j > 0; j--)
                {
                    if (matr[probirka[j]-1, probirka[j - 1]-1] == 0)
                        break;
                    probirka[j - 1] = matr[probirka[j]-1, probirka[j - 1]-1];
                    probirka.RemoveAt(j);
                }

            }
            StreamWriter outt = new StreamWriter("out.txt");
            for (int i = probirka.Count-1; i >= 0; i--)
            {
                if (i == 0)
                    outt.Write(probirka[i]);
                else
                    outt.Write(probirka[i] + " ");
            }
            outt.Close();
        }
    }
}