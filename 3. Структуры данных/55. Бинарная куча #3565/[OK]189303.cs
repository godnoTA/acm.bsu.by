using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Collections;


namespace nin
{
    class Program
    {
        static void Main(string[] args)
        {
            StreamReader in_f = new StreamReader("input.txt");
            StreamWriter out_f = new StreamWriter("output.txt");

            int matrix = Convert.ToInt32(in_f.ReadLine());
            string matrix_zna = in_f.ReadLine();
            string[] words = matrix_zna.Split(' ');
            int[] mas = new int[matrix+1];
            int b = 0;
            for(int i  = 1; i <= matrix; i++)
            {

                mas[i] = Convert.ToInt32(words[b]);
                b++;
            }
            for (int i = matrix;i >= 2;)
            {
                
                    if (mas[i / 2] <= mas[i])
                    {
                        i--;
                    }
                    else
                    {
                        out_f.WriteLine("No");
                        out_f.Close();
                        Environment.Exit(0);
                    }               
            }
            out_f.WriteLine("Yes");
            out_f.Close();
        }
    }
}
       