using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Канонический_вид_2
{
    class Program
    {
        static void Main(string[] args)
        {
            StreamReader input = new StreamReader("input.txt");
            StreamWriter output = new StreamWriter("output.txt");
            int n = Int32.Parse(input.ReadLine());
            int[,] matr = new int[n, n];
            var file = File.ReadAllLines("input.txt");
            int[] answer = new int[n];
            for (int i = 1; i < file.Length; i++)
            {
                var line = file[i].Split();
                for (int j = 0; j < n; j++)
                {
                    matr[i - 1, j] = Int32.Parse(line[j]);
                }
            }

            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if (matr[j, i] == 1)
                    {
                        answer[i] = j + 1;
                        break;
                    }
                    if (j == n - 1 && matr[j, i] == 0)
                        answer[i] = 0;
                }
            }

            for (int i = 0; i < n; i++)
            {
                if(i==n-1)
                {
                    output.Write(answer[i]);
                    break;
                }
                output.Write(answer[i] + " ");
            }
            output.Close();
        }
    }
}
