using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Diagnostics;
using System.Collections;

namespace min_ch_xodov
{


    public class Pair
    {
        public Pair()
        {
        }

        public Pair(int first, int second, int kol)
        {
            this.First = first;
            this.Second = second;
            this.Kol = kol;
        }

        public int First { get; set; }
        public int Second { get; set; }
        public int Kol { get; set; }
    }
    class Program
    {


        static void Main(string[] args)
        {
            Stopwatch stopWatch = new Stopwatch();
            stopWatch.Start();


            StreamReader input = new StreamReader("in.txt");
            StreamWriter output = new StreamWriter("out.txt");
            //for (int i = 0; i < 1000; i++)
            //{
            //    for (int k = 0; k < 1000; k++)
            //    {
            //        mat[i, k] = rnd.Next(0, 2);
            //        if (k < 998)
            //        {
            //            output.Write(mat[i, k] + " ");
            //        }
            //        else
            //        {
            //            output.Write(mat[i, k]);
            //        }
            //    }
            //    output.WriteLine();

            //}
            string[] line = input.ReadLine().Split(' ');
            
            int[,] matrix = new int[Convert.ToInt32(line[0]) + 4, Convert.ToInt32(line[1]) + 4];
            int n = 0;
            for (int i = 0; i < Convert.ToInt32(line[0]) + 4; i++)
            {
                if (i < 2 || i > Convert.ToInt32(line[0]) + 1)
                {
                    for (int l = 0; l < Convert.ToInt32(line[1]) + 4; l++)
                    {
                        matrix[i, l] = -1;
                    }
                }
                else
                {
                    string[] matr = input.ReadLine().Split(' ');
                    for (int u = 0; u < Convert.ToInt32(line[1]) + 4; u++)
                    {
                        if (u < 2 || u > Convert.ToInt32(line[1]) + 1)
                        {
                            matrix[i, u] = -1;
                        }
                        else
                        {
                            matrix[i, u] = Convert.ToInt32(matr[u - 2]);
                        }
                    }
                }
            }
            int[,] matrix1 = new int[Convert.ToInt32(line[0]) + 4, Convert.ToInt32(line[1]) + 4];
            string[] cord = input.ReadLine().Split(' ');
            //Queue<cordinat> cor = new Queue<cordinat>();

            //cor.Enqueue(new cordinat(Convert.ToInt32(cord[0]), Convert.ToInt32(cord[1])));
            //foreach(cordinat x in cor)
            //{
            //    Console.WriteLine();
            ////}
            //Pair pair = new Pair();
            bool b = true;
            int[] mas = new int[] { 1, 2, -1, 2, 1, -2, -1, -2, 2, -1, 2, 1, -2, -1, -2, 1 };
            Pair pair_qe_0;
            Pair pair_qe_1;
            Pair pair_dobavl;
            
            Queue<Pair> qe_1 = new Queue<Pair>();
            Queue<Pair> qe_0 = new Queue<Pair>();
            qe_0.Enqueue(new Pair(Convert.ToInt32(cord[0]) + 1, Convert.ToInt32(cord[1]) + 1, 0));
            int x;
            int y;
            int xx;
            int yy;
            if (Convert.ToInt32(cord[0]) == Convert.ToInt32(cord[2]) && Convert.ToInt32(cord[1]) == Convert.ToInt32(cord[3]))
            {
                if (matrix[Convert.ToInt32(cord[2]), Convert.ToInt32(cord[3])] == -1)
                {
                    output.WriteLine("No");
                }
                //output.WriteLine(0);
            }
            else
            {

                bool a = true;
                while (qe_0.Count != 0 || qe_1.Count != 0)
                {

                    if (qe_0.Count != 0 && qe_1.Count != 0)
                    {
                        pair_qe_0 = qe_0.Peek();
                        pair_qe_1 = qe_1.Peek();
                        if (pair_qe_0.Kol < pair_qe_1.Kol)
                        {
                            pair_dobavl = qe_0.Dequeue();
                        }
                        else
                        {
                            pair_dobavl = qe_1.Dequeue();
                        }
                    }
                    else
                    {
                        if (qe_0.Count == 0)
                        {
                            pair_dobavl = qe_1.Dequeue();
                        }
                        else
                        {
                            pair_dobavl = qe_0.Dequeue();
                        }
                    }





                    for (int i = 0, k = 0; i < 8; i++, k = k + 2)
                    {
                        x = pair_dobavl.First;
                        y = pair_dobavl.Second;
                        xx = mas[k];
                        yy = mas[k + 1];

                        int x1 = xx + pair_dobavl.First;
                        int y1 = yy + pair_dobavl.Second;
                        if (matrix[x1, y1] == 1 || matrix[x1, y1] == 0)
                        {



                            if (matrix[x + xx, y + yy] == 1)
                            {

                                matrix1[x + xx, y + yy] = matrix1[x, y] + 2;
                                matrix[x + xx, y + yy] = matrix[x, y] + 2;
                                qe_1.Enqueue(new Pair(x1, y1, matrix1[x + xx, y + yy]));

                            }
                            else
                            {

                                matrix1[x + xx, y + yy] = matrix1[x, y] + 1;
                                matrix[x + xx, y + yy] = matrix[x, y] + 1;
                                qe_0.Enqueue(new Pair(x1, y1, matrix1[x + xx, y + yy]));

                            }

                        }
                            
                        

                    }

                   
                }
            }

            if (matrix1[Convert.ToInt32(cord[2]) + 1, Convert.ToInt32(cord[3]) + 1] != 0)
            {
                output.WriteLine(matrix1[Convert.ToInt32(cord[2]) + 1, Convert.ToInt32(cord[3]) + 1]);
                }
                else
                {
                    output.WriteLine("No");
                }

            stopWatch.Stop();
            // Get the elapsed time as a TimeSpan value.
            TimeSpan ts = stopWatch.Elapsed;

            // Format and display the TimeSpan value.
            string elapsedTime = String.Format("{0:00}:{1:00}:{2:00}.{3:00}",
                ts.Hours, ts.Minutes, ts.Seconds,
                ts.Milliseconds / 10);
            Console.WriteLine("RunTime " + elapsedTime);
            //for (int k =0; k < Convert.ToInt32(line[0]) + 4; k++)
            //{
            //    for(int s = 0; s < Convert.ToInt32(line[1]) + 4; s++)
            //    {
            //        output.Write("{0}\t", matrix[k, s]);
            //    }
            //    output.WriteLine();
            //}
            output.Close();
        }
    }
}