using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace matrix_cm
{
    class Program
    {
        static void Main(string[] args)

        {
            StreamReader input = new StreamReader("input.txt");
            StreamWriter output = new StreamWriter("output.txt");
            string a;

            a = input.ReadLine();
            string[] words = a.Split(' ');
            int[] matrix = new int[Convert.ToInt32(words[0])];
            int n = 0;
            List<int>[] mas_l = new List<int>[Convert.ToInt32(words[0])];
            for (int i = 0; i < Convert.ToInt32(words[0]); i++)
            {
                mas_l[i] = new List<int>();               
            }
            while (!input.EndOfStream)
            {
                string s = input.ReadLine();
                string[] ad = s.Split(' ');

                matrix[Convert.ToInt32(ad[0]) - 1] += 1;
                mas_l[Convert.ToInt32(ad[0]) - 1].Add(Convert.ToInt32(ad[1]));
                matrix[Convert.ToInt32(ad[1]) - 1] += 1;
                mas_l[Convert.ToInt32(ad[1]) - 1].Add(Convert.ToInt32(ad[0]));
                n++;
            }
            for (int l = 0; l < Convert.ToInt32(words[0]); l++)
            {

                output.Write(matrix[l] + " ");

                foreach (int al in mas_l[l])
                {
                    output.Write(al + " ");
                }
                output.WriteLine();

            }
            output.Close();
        }
    }
}
