using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Матрица_смежности
{
    class Program
    {
        static void Main(string[] args)
        {
            var file = File.ReadAllLines("input.txt");
            var firstLine = file[0].Split();
            int[,] matr = new int[Int32.Parse(firstLine[0]),Int32.Parse(firstLine[0])];
            for (int i = 1; i < file.Length ; i++)
            {
                var line = file[i].Split();
                matr[Int32.Parse(line[0])-1,Int32.Parse(line[1])-1] = 1;
                matr[Int32.Parse(line[1]) - 1, Int32.Parse(line[0]) - 1] = 1;
            }
            var outt = new StreamWriter("output.txt");
            for (int i = 0; i <Int32.Parse(firstLine[0]) ; i++)
            {
                for (int j = 0; j < Int32.Parse(firstLine[0]); j++)
                {
                    if (j + 1 == Int32.Parse(firstLine[0]))
                    {
                        outt.WriteLine(matr[i, j]);
                        break;
                    }
                    outt.Write(matr[i, j] + " ");
                }
            }
            outt.Close();
        }
    }
}
