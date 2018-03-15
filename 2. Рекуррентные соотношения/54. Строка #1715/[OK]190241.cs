using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Collections;

namespace ConsoleApplication04
{
    class Program
    {
        public static int del_one(ArrayList a, int kol_del)
        {
            ArrayList myAL = new ArrayList(a.Count);
            for (int i = 0; i < a.Count; i++)
            {
                myAL.Add(a[i]);
            }
            myAL.Sort();
            int kol = 0;
            for (int i = 0; i < myAL.Count;) // убираю одинарные символы 
            {
                if (++i < myAL.Count)
                {
                    i--;
                    while (Convert.ToChar(myAL[i]) == Convert.ToChar(myAL[++i]))
                    {
                        if (++i < myAL.Count)
                        {
                            i--;
                            kol++;
                        }
                        else
                        {
                            kol = 300;
                            break;
                        }
                    }
                }
                if (kol == 0)
                {
                    a.Remove(myAL[--i]);
                    kol_del++;
                    i++;
                }
                kol = 0;
            }
            return kol_del;

        }


        private static void del_podr_id(ArrayList a)
        {

            for (int i = 0; i < a.Count;) // удаляет подряд идущие одинаковые символы
            {
                if (++i < a.Count)
                {
                    --i;
                    if (Convert.ToChar(a[i]) == Convert.ToChar(a[++i]))
                    {

                        a.RemoveAt(i);
                        i--;
                    }
                }
            }
        }
        //private static void proverka(int pod_stroka ,int strok, int gran , int vtor , int[,] matrix)
        //{

        //    for (; pod_stroka < gran;)
        //    {
        //        vtor = matrix[strok, pod_stroka] + proverka(++pod_stroka , gran)
        //        if (a == true)
        //        {
        //            min = vtor;
        //            a = false;
        //        }
        //        if (vtor < min)
        //        {
        //            min = vtor;
        //        }
        //    }
        //    matrix[0, l] = Math.Min(matrix[0, l], min);
        //}
        //private static int del_me(ArrayList a, int kol_del)
        //{
        //    int raznn = 0;
        //    int min = 300;
        //    int grann_1 = 0;
        //    int grann_2 = 0;
        //    for (int i = 0; i < a.Count; i++) // удал между ближ 
        //    {
        //        int z = i;
        //        if (++z < a.Count)
        //        {

        //            while (Convert.ToChar(a[i]) != Convert.ToChar(a[z]))
        //            {
        //                raznn++;
        //                if (++z < a.Count)
        //                {
        //                }
        //                else
        //                {
        //                    raznn = 0;
        //                    break;
        //                }

        //            }
        //        }
        //        if (raznn < min && raznn > 0)
        //        {
        //            if (raznn == 1)
        //            {
        //                min = raznn;
        //                a.RemoveAt(++i);
        //                kol_del++;
        //                return kol_del;
        //            }
        //            min = raznn;
        //            grann_1 = ++i;
        //            grann_2 = --z;
        //        }
        //        raznn = 0;
        //    }
        //    if (grann_1 != 0 && grann_2 != 0)
        //    {
        //        for (; grann_1 <= grann_2;)
        //        {
        //            a.RemoveAt(grann_1);
        //            grann_2--;
        //            kol_del++;
        //        }
        //    }
        //    return kol_del;
        //}

        static void Main(string[] args)
        {
            StreamReader in_f = new StreamReader("input.txt");
            StreamWriter out_f = new StreamWriter("output.txt");
            string input = in_f.ReadLine();
            int k;
            if (input.Length > 300)
            {
                k = 300;
            }
            else
                k = input.Length;
            ArrayList myALL = new ArrayList();
            for (int i = 0; i < k; i++)
            {
                myALL.Add(input[i]);
            }
            int kol_del = 0;

            kol_del = del_one(myALL, kol_del);
            del_podr_id(myALL);
            ////kol_del = del_me(myALL, kol_del);
            int n = myALL.Count;
            if (n != 0)
            {
                int[,] matrix = new int[n, n];
                for (int i = 0; i < n; i++)
                {
                    matrix[i, i] = 1;
                }
                int l = n;
                l--;
                int strok = 0;
                int kol1 = 1;
                int clo = 0;
                int vtor = 0;
                bool a = true;
                int min = 0;
                int bred = 0;
                for (int i = 1; i <= n - 1; i++)
                {
                    for (int kol = kol1; kol <= l; kol++)
                    {
                        if (Convert.ToChar(myALL[strok]) != Convert.ToChar(myALL[kol]))
                        {
                            
                            matrix[strok, kol] = Math.Min(matrix[strok, kol - 1], matrix[strok + 1, kol]) + 1;
                            //if (kol - strok > 1)
                            //{
                                for (int str = strok+1; str <= kol;str++)
                                {
                                    if (Convert.ToChar(myALL[strok]) == Convert.ToChar(myALL[str]))
                                    {
                                        if (str != kol)
                                        {
                                            clo = matrix[strok + 1, str - 1] + matrix[str, kol];
                                            if (a)
                                            {
                                                min = clo;
                                                a = false;
                                            }
                                            if (clo < min)
                                            {
                                                min = clo;
                                            }
                                        }
                                    }
                                }
                                a = true;
                                if (min > 0)
                                {
                                    matrix[strok, kol] = Math.Min(matrix[strok, kol], min);
                                }
                            //}
                            
                        }
                        else
                        {
                            matrix[strok, kol] = matrix[strok + 1, kol - 1] + 1;
                            //if (kol - strok > 4)
                            //{
                                
                                for (int str = strok+1; str <= kol;str++)
                                {
                                    if (Convert.ToChar(myALL[strok]) == Convert.ToChar(myALL[str]))
                                    {
                                        if (str != kol)
                                        {
                                            vtor = matrix[strok + 1, str - 1] + matrix[str, kol];
                                            if (a)
                                            {
                                                min = vtor;
                                                a = false;
                                            }
                                            if (vtor < min)
                                            {
                                                min = vtor;
                                            }
                                        }
                                     
                                    }
                                }
                                a = true;
                                if (min > 0)
                                {
                                    matrix[strok, kol] = Math.Min(matrix[strok, kol], min);
                                }
                            //}
                            
                        }
                        vtor = 0;
                        bred = 0;
                        strok++;
                        a = true;
                        min = 0;
                    }
                    strok = 0;
                    kol1++; // незабыть обнул 

                }
                int minn = 0;
                bool av = true;
                int al;
                //for (int kk = 0; kk < l;)
                //{
                //    if (av)
                //    {
                //        minn = matrix[0, kk] + matrix[++kk, l];
                //        av = false;
                //    }
                //    al = matrix[0, kk] + matrix[++kk, l];
                //    if (al < minn)
                //    {
                //        minn = al;
                //    }
                //}

                //matrix[0, l] = Math.Min(matrix[0, l], minn);

                //for (int i = 0; i < n; i++)
                //{
                //    for (int j = 0; j < n; j++)
                //    {

                //        out_f.Write("{0}\t", matrix[i, j]);
                //    }
                //    out_f.WriteLine();
                //}
                out_f.Write(matrix[0, n - 1] + kol_del);
            }
            else
                out_f.Write(kol_del);
            out_f.Close();
        }
    }
}