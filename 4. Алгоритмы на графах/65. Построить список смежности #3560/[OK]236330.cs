using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Windows;
//НА самом деле это список смежности
namespace Хэш_таблица
{
    class Program
    {
        static void Main(string[] args)
        {
            List<List<int>> list = new List<List<int>> { };
            StreamReader input = new StreamReader("input.txt");
            StreamWriter output = new StreamWriter("output.txt");
            var line1 = input.ReadLine().Split();
            int n = Int32.Parse(line1[0]), m = Int32.Parse(line1[1]);
            for (int i = 0; i < n; i++)
            {
                list.Add(new List<int> { });
            }

            int[,] matr = new int[n, n];
            var file = File.ReadAllLines("input.txt");
            for (int i = 1; i < file.Length; i++)
            {
                var line = file[i].Split();
                list[Int32.Parse(line[0])-1].Add(Int32.Parse(line[1]));
                list[Int32.Parse(line[1])-1].Add(Int32.Parse(line[0]));
            }

            foreach (var item in list)
            {
                output.Write(item.Count);
                foreach (var item1 in item)
                {
                    output.Write(" " + item1);
                }
                output.WriteLine();
            }
            output.Close();
        }
    }
}
