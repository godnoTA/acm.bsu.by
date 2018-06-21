using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Канонический_вид_1
{
    class Program
    {
        static void Main(string[] args)
        {
            StreamReader input = new StreamReader("input.txt");
            StreamWriter output = new StreamWriter("output.txt");
            int n = Int32.Parse(input.ReadLine());
            int[] mas = new int[n];
            var file = File.ReadAllLines("input.txt");
            for (int i = 1; i < file.Length; i++)
            {
                var line = file[i].Split();
                mas[Int32.Parse(line[1]) - 1] = Int32.Parse(line[0]);
            }
            for (int i = 0; i < n; i++)
            {
                if (i == n - 1)
                {
                    output.Write(mas[i]);
                    break;
                }
                output.Write(mas[i] + " ");
            }
            output.Close();
        }
    }
}
