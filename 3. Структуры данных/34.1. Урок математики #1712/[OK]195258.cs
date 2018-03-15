using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Collections;

namespace Math_lesson2
{
    class Program
    {
        static int binarysearch(int a, int[] mass, int n, int[] mas)
        {
            int low, high, middle;
            low = 0;
            high = n - 1;
            while (low <= high)
            {
                middle = (low + high) / 2;
                if (a < mass[middle])
                    high = middle - 1;
                else if (a > mass[middle])
                    low = middle + 1;
                else
                {
                    while (middle - 1 >= 0 && mass[middle] == mass[middle - 1] && mas[middle - 1] == 0 /*&& mas[middle - 1] != 1*/)
                    {
                        middle--;
                    }
                    int nn = mass.Length; 
                    while (middle + 1 < nn && mas[middle] == 1)
                    {
                        //if(mass[1, middle] == mass[1, middle + 1]) {
                        middle++;

                        //}
                    }
                    mas[middle] = 1;
                    return middle;
                }

            }
            return -1;
        }
        static void Main(string[] args)
        {
            StreamReader input = new StreamReader("input.txt");
            StreamWriter output = new StreamWriter("output.txt");
            int kol = Convert.ToInt32(input.ReadLine());
            string line = input.ReadLine();
            string[] num1 = line.Split(' ');
            //int[] num = new int[num1.Length];
            ArrayList num = new ArrayList(kol * kol);
            ArrayList outt = new ArrayList();
            int[] mas = new int[kol * kol];
            int[] mas1 = new int[kol * kol];
            //Console.WriteLine(num1.Length);
            //for (int i = 0; i < num1.Length; i++)
            //{
            //    if (0 < Convert.ToInt32(num1[i]) && Convert.ToInt32(num1[i]) <= 2000000000)
            //    {
            //        num.Add(Convert.ToInt32(num1[i]));
            //    }
            //}
            //num.Sort();
            //LinkedList<int> link = new LinkedList<int>();
            for (int i = 0; i < num1.Length; i++)
            {
                mas[i] = Convert.ToInt32(num1[i]);
            }
            Array.Sort(mas);

            int first = 0;
            bool okk = true;
            //for (int a = 0; a < kol; a++)
            //{
            //    if(okk == true)
            //    {
            //        if (Convert.ToInt32(num[0]) != 1)
            //        {
            //            first = Convert.ToInt32(num[0]) / 2;
            //        }
            //        else
            //        {
            //            first = 1;
            //        }
            //        okk = false;
            //        outt.Add(first);
            //    }
            //    else
            //    {
            //        outt.Add(Convert.ToInt32(num[a]) - first); 
            //    }
            //}
            if (Convert.ToInt32(mas[0]) != 1)
            {
                first = Convert.ToInt32(mas[0]) / 2;
            }
            else
            {
                first = 1;
            }
            int ch = kol - 1;
            int podc = 0;
            outt.Add(first);
            //outt.Add(Convert.ToInt32(num[1]) - first);   
            int ll = 0;
            int c = 0;
            bool k = true;
            int delete = 0;
            
            int coun = mas.Length - 1;
            int min = 0;
            int v = 0;
            for (int i = 0; i < kol; i++)
            {
                for (; c < outt.Count; c++)
                {
                    for (int a = 0; a < outt.Count; a++)
                    {
                        if (Convert.ToInt32(outt[c]) != Convert.ToInt32(outt[a]) || k == false)
                        {
                            int ass = Convert.ToInt32(outt[c]) + Convert.ToInt32(outt[a]);
                            delete = binarysearch(ass, mas, coun, mas1);
                            mas1[delete + 1] = 1;
                            ll += 2;
                            //link.Remove(ass);
                            //link.Remove(ass);
                        }
                        else
                        {
                            if (k == true)
                            {
                                ll++;
                                binarysearch(Convert.ToInt32(outt[c]) * 2, mas, coun, mas1);
                                k = false;
                            }
                        }
                    }
                    k = true;
                }
                if (mas.Length > ll)
                {

                    //Console.WriteLine(link.First());
                    while (mas1[v] == 1)
                    {
                        v++;
                    }
                    outt.Add(Convert.ToInt32(mas[v] - first));
                }
            }
            //foreach(int a in outt)
            //{
            //    Console.WriteLine(a);
            //}
            for (int a = 0; a < kol; a++)
            {
                output.WriteLine(outt[a]);
            }
            output.Close();

        }
    }
}

