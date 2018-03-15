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
            int a;
           
            a = Convert.ToInt32(input.ReadLine());
            int[] mas = new int[a];
            int n = 0;
           for(int k =0;k < a-1; k++) { 
                string s = input.ReadLine();
                string[] ad = s.Split(' ');
                
                mas[Convert.ToInt32(ad[1]) - 1] = Convert.ToInt32(ad[0]) ;

                n++;
            }
            for (int l = 0; l < a; l++)
            {
                
               output.Write(mas[l] + " ");
               
            }
            output.Close();
        }
    }
}
