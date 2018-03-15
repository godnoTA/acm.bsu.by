using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace depth_search
{
    class Program
    {
        //static public void met(Stack<int> myStack ,int[,] matrix , int[] mas_pom , int metka , int[] mas_metka , int a)
        //{
        //    bool bol = true;
        //    while (myStack.Count != 0)
        //    {
        //        int ver = myStack.Peek();
        //        for (int i = 0; i < a; i++)
        //        {
        //            if (matrix[ver, i] == 1 && !myStack.Contains(i) && mas_pom[i] != -1)
        //            {
        //                metka++;
        //                mas_metka[i] = metka;
        //                bol = false;
        //                myStack.Push(i);
        //                break;
        //            }
        //        }
        //        if (bol == true)
        //        {
        //            int l = myStack.Pop();
        //            mas_pom[l] = -1;
        //        }
        //        bol = true;
        //    }
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
            int[] mas_pom = new int[a];
            int[] mas_metka = new int[a];
            int metka = 1;

            Stack<int> myStack = new Stack<int>();
            myStack.Push(0);
            mas_metka[0] = metka;
            bool bol = true;
            while (myStack.Count != 0)
            {
                int ver = myStack.Peek();
                for (int i = 0; i < a; i++)
                {
                    if (matrix[ver, i] == 1 && !myStack.Contains(i) && mas_pom[i] != -1)
                    {
                        metka++;
                        mas_metka[i] = metka;
                        bol = false;
                        myStack.Push(i);
                        break;
                    }
                }
                if (bol == true)
                {
                    int l = myStack.Pop();
                    mas_pom[l] = -1;
                }
                bol = true;
            }
            //met(myStack, matrix, mas_pom, metka, mas_metka, a);
            for(int i = 0; i< a; i++)
            {
                if (mas_pom[i] == 0)
                {
                    mas_metka[i] = ++metka;
                    myStack.Push(i);
                    while (myStack.Count != 0)
                    {
                        int ver = myStack.Peek();
                        for (int s = 0; s < a; s++)
                        {
                            if (matrix[ver, s] == 1 && !myStack.Contains(s) && mas_pom[s] != -1)
                            {
                                metka++;
                                mas_metka[s] = metka;
                                bol = false;
                                myStack.Push(s);
                                break;
                            }
                        }
                        if (bol == true)
                        {
                            int l = myStack.Pop();
                            mas_pom[l] = -1;
                        }
                        bol = true;
                    }
                }
            }
            foreach(int k in mas_metka)
            {
                output.Write(k+" ");
            }
            output.Close();

        }
    }
}
