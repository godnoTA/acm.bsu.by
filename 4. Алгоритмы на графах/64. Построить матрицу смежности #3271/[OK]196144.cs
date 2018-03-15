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
            int[,] matrix = new int[Convert.ToInt32(words[0]), Convert.ToInt32(words[0])];
            int n = 0;
            while(Convert.ToInt32(words[1]) != n)
            {
                string s = input.ReadLine();
                string[] ad = s.Split(' ');
                matrix[Convert.ToInt32(ad[0]) - 1, Convert.ToInt32(ad[1]) - 1] = 1;
                matrix[Convert.ToInt32(ad[1]) - 1, Convert.ToInt32(ad[0]) - 1] = 1;
                n++;
            }
            for(int l = 0; l < Convert.ToInt32(words[0]); l++)
            {
                for(int m = 0; m < Convert.ToInt32(words[0]); m++)
                {
                    output.Write("{0}\t",  matrix[l, m]);
                   
                }
                output.WriteLine();
            }
            output.Close();
        }
    }
}
