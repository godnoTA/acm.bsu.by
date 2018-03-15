using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Collections;


namespace ConsoleApplication1
{
    class Program
    {

        static void Main(string[] args)
        {
            double mas = 0;
            StreamReader f = new StreamReader("input.txt");
            ArrayList li = new ArrayList();
            while (!f.EndOfStream)
            {
                double s = Convert.ToDouble(f.ReadLine());
                if(!li.Contains(s)){
                    li.Add(s);
                    mas += s;
                }              
            }
            StreamWriter sw = new StreamWriter("output.txt");
            sw.WriteLine(mas);
            sw.Close();
            f.Close();
        }
    }
}
