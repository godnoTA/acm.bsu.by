using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Collections;

namespace search
{
    class Program
    {
        //static public void ser(int[,] matrix, Queue<int> qe, int c, int[] mas, int a, Queue<int> qe1)
        //{
        //    bool aa = true;
        //    for (int k = 1; k < a; k++)
        //    {
        //        if (matrix[c, k] == 1 && mas[k] == 0)
        //        {
        //            qe.Enqueue(k);
        //            mas[k] = -1;
        //            aa = false;
        //        }

        //    }
        //    if (aa == true && mas[c] == 0)
        //    {
        //        mas[c] = -1;
        //    }
        //    aa = true;

        //}
        static void Main(string[] args)
        {
            StreamReader input = new StreamReader("input.txt");
            StreamWriter output = new StreamWriter("output.txt");
            int a = Convert.ToInt32(input.ReadLine());
            int[,] matrix = new int[a, a];
            for (int i = 0; i < a; i++)
            {
                string strok = input.ReadLine();
                string[] words = strok.Split(' ');
                for (int k = 0; k < a; k++)
                {
                    matrix[i, k] = Convert.ToInt32(words[k]);
                }
            }
            int[] mas_metok = new int[a];
            int[] mas_pomech = new int[a];
            Queue<int> qe = new Queue<int>();
          
            int c;

            int metka = 1;
            mas_metok[0] = 1;
            mas_pomech[0] = -1;
            qe.Enqueue(0);
            while (qe.Count != 0)
            {

                c = qe.Dequeue();
                
                bool aa = true;
                for (int k = 0; k < a; k++)
                {
                    if (matrix[c, k] == 1 && mas_pomech[k] == 0)
                    {
                        metka++;
                        mas_metok[k] = metka;
                        qe.Enqueue(k);
                        mas_pomech[k] = -1;
                        aa = false;
                    }

                }
  //ser(matrix,qe, c, mas1, a, qe1);
            }
            for (int i = 0; i < a; i++)
            {
                if (mas_pomech[i] != -1)
                {
                    qe.Enqueue(i);
                    mas_pomech[i] = -1;
                    metka++;
                    mas_metok[i] = metka;
                    while (qe.Count != 0)
                    {
                        c = qe.Dequeue();                       
                        bool aa = true;
                        for (int k = 0; k < a; k++)
                        {
                            if (matrix[c, k] == 1 && mas_pomech[k] == 0)
                            {
                                metka++;
                                mas_metok[k] = metka;
                                qe.Enqueue(k);
                                mas_pomech[k] = -1;                               
                            }

                        }
                     
                    }
                }
            }


            foreach (int i in mas_metok)
                output.Write(i + " ");

            output.Close();
            //for (int i = 0; i < a; i++)
            //{
            //    for (int j = 0; j < a; j++)
            //    {

            //        Console.Write("{0}\t", matrix[i, j]);
            //    }
            //    Console.WriteLine();
            //}
        }
    }
}
