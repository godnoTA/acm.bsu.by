using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Web;
using System.IO;

namespace algo
{
    class Program
    {
        static bool isExit(int[] A, int a,  int size)
        {
            for (int i = 0; i < size; i++)
                if (A[i] == a)
                    return true;
            return false;
        }
        static void Main(string[] args)
        {
            int  n, m, k;
            int[] ent, ex;
            int[,] T, tmpT;
            Stack<Tuple<Tuple<int, int>, int>> iStack = new Stack<Tuple<Tuple<int, int>, int>>();
            using (StreamReader sr = new StreamReader("input.txt"))
            {
                string[] w = sr.ReadLine().Split(' ');
                n = Convert.ToInt32(w[0]);
                m = Convert.ToInt32(w[1]);
                k = Convert.ToInt32(w[2]);
                T = new int[n, m];
                tmpT = new int[n, m];
                ent = new int[k];
                ex = new int[k];
                w = sr.ReadLine().Split(' ');
                for (int q = 0; q < k; q++)
                {
                    ent[q] = Convert.ToInt32(w[q]) - 1;
                }
                w = sr.ReadLine().Split(' ');
                for (int q = 0; q < k; q++)
                {
                    ex[q] = Convert.ToInt32(w[q]) - 1;
                }

                for (int t = 0; t < n; t++)
                {
                    w = sr.ReadLine().Split(' ');
                    for (int q = 0; q < m; q++)
                    {
                        T[t,q] = Convert.ToInt32(w[q]);
                        tmpT[t,q] = Convert.ToInt32(w[q]);
                    }
                }

            }
            
            int i = 0, j = 0;
            bool isfirst;
            bool istupick;
            bool become;
            int answer = 0;
            for (int l = 0; l < k; l++)
            {
                isfirst = true;
                i = 0;
                j = ent[l];
                become = true;
                while (i != (n - 1) || !isExit(ex, j, k))
                {
                    Tuple<Tuple<int, int>, int> templ;
                    istupick = true;
                    if (isfirst)
                    {
                        T[i,j] = 1;
                        iStack.Push(Tuple.Create(Tuple.Create(i, j), 3));
                        isfirst = false;
                        if ((j - 1 >= 0) && T[i,j - 1] <= 0)
                        {
                            templ = iStack.Pop();
                            iStack.Push(new Tuple<Tuple<int, int>, int>(new Tuple<int, int>(templ.Item1.Item1, templ.Item1.Item2), 1));
                            j--;
                            istupick = false;
                        }
                        else
                            if ((i + 1 < n) && T[i + 1, j] <= 0)
                        {
                            templ = iStack.Pop();
                            iStack.Push(new Tuple<Tuple<int, int>, int>(new Tuple<int, int>(templ.Item1.Item1, templ.Item1.Item2), 2));
                            i++;
                            istupick = false;
                        }
                        else
                                if ((j + 1 < m) && T[i,j + 1] <= 0)
                        {
                            templ = iStack.Pop();
                            iStack.Push(new Tuple<Tuple<int, int>, int>(new Tuple<int, int>(templ.Item1.Item1, templ.Item1.Item2), 3));
                            j++;
                            istupick = false;
                        }

                    }
                    else
        
                        if ((i - 1) >= 0 && iStack.Peek().Item1.Item1 == (i - 1) && iStack.Peek().Item1.Item2 == j)
                    {
                        if ((j - 1 >= 0) && T[i,j - 1] <= 0)
                        {
                            T[i,j] = 1;
                            iStack.Push(new Tuple<Tuple<int, int>, int>(new Tuple<int, int>(i,j), 1));
                            j--;
                            istupick = false;
                        }
                        else
                            if ((i + 1 < n) && T[i + 1,j] <= 0)
                        {
                            T[i,j] = 1;
                            iStack.Push(new Tuple<Tuple<int, int>, int>(new Tuple<int, int>(i, j), 2));
                            i++;
                            istupick = false;
                        }
                        else
                                if ((j + 1 < m) && T[i,j + 1] <= 0)
                        {
                            T[i,j] = 1;
                            iStack.Push(new Tuple<Tuple<int, int>, int>(new Tuple<int, int>(i, j), 3));
                            j++;
                            istupick = false;
                        }


                    }
                    else
                            if (iStack.Peek().Item1.Item1 == (i) && (j + 1) < m && iStack.Peek().Item1.Item2 == j + 1)
                    {
                        if ((i - 1 >= 0) && T[i - 1,j] <= 0)
                        {
                            T[i,j] = 1;
                            iStack.Push(new Tuple<Tuple<int, int>, int>(new Tuple<int, int>(i, j), 1));
                            i--;
                            istupick = false;
                        }
                        else
                            if ((j - 1 >= 0) && T[i,j - 1] <= 0)
                        {
                            T[i,j] = 1;
                            iStack.Push(new Tuple<Tuple<int, int>, int>(new Tuple<int, int>(i, j), 2));
                            j--;
                            istupick = false;
                        }
                        else
                                if ((i + 1 < n) && T[i + 1,j] <= 0)
                        {
                            T[i,j] = 1;
                            iStack.Push(new Tuple<Tuple<int, int>, int>(new Tuple<int, int>(i, j), 3));
                            i++;
                            istupick = false;
                        }
                    }
                    else
                                if ((i + 1) < n && iStack.Peek().Item1.Item1 == (i + 1) && iStack.Peek().Item1.Item2 == j)
                    {
                        if ((j + 1) < m && T[i,j + 1] <= 0)
                        {
                            T[i,j] = 1;
                            iStack.Push(new Tuple<Tuple<int, int>, int>(new Tuple<int, int>(i, j), 1));
                            j++;
                            istupick = false;
                        }
                        else
                            if ((i - 1 >= 0) && T[i - 1,j] <= 0)
                        {
                            T[i,j] = 1;
                            iStack.Push(new Tuple<Tuple<int, int>, int>(new Tuple<int, int>(i, j), 2));
                            i--;
                            istupick = false;
                        }
                        else
                                if ((j - 1 >= 0) && T[i,j - 1] <= 0)
                        {
                            T[i,j] = 1;
                            iStack.Push(new Tuple<Tuple<int, int>, int>(new Tuple<int, int>(i, j), 3));
                            j--;
                            istupick = false;
                        }
                    }
                    else
                                    if ((j - 1) >= 0 && iStack.Peek().Item1.Item1 == (i) && iStack.Peek().Item1.Item2 == (j - 1))
                    {
                        if ((i + 1 < n) && T[i + 1,j] <= 0)
                        {
                            T[i,j] = 1;
                            iStack.Push(new Tuple<Tuple<int, int>, int>(new Tuple<int, int>(i, j), 1));
                            i++;
                            istupick = false;
                        }
                        else
                            if ((j + 1 < m) && T[i,j + 1] <= 0)
                        {
                            T[i,j] = 1;
                            iStack.Push(new Tuple<Tuple<int, int>, int>(new Tuple<int, int>(i, j), 2));
                            j++;
                            istupick = false;
                        }
                        else
                                if ((i - 1 >= 0) && T[i - 1,j] <= 0)
                        {
                            T[i,j] = 1;
                            iStack.Push(new Tuple<Tuple<int, int>, int>(new Tuple<int, int>(i, j), 3));
                            i--;
                            istupick = false;
                        }
                    }


                    if (istupick)
                    {
                        if (iStack.Count != 1)
                        {
                            T[i,j] = 1;
                            i = iStack.Peek().Item1.Item1;
                            j = iStack.Peek().Item1.Item2;
                            iStack.Pop();
                        }
                        else
                            if (iStack.Count == 1)
                        {
                            templ = iStack.Peek();
                            if (templ.Item2 == 3)
                            {
                                become = false;
                                iStack.Pop();
                                break;
                            }
                            else
                            {
                                l--;
                                T[i,j] = 1;
                                become = false;
                                iStack.Pop();
                                break;
                            }
                        }
                    }

                }
                if (become)
                {
                    answer++;
                    tmpT[i,j] = (l + 2) * -1;
                    while (iStack.Count != 0)
                    {
                        tmpT[iStack.Peek().Item1.Item1, iStack.Peek().Item1.Item2] = l + 2;
                        iStack.Pop();
                    }
                    for (int f = 0; f < n; f++)
                    {
                        for (int t = 0; t < m; t++)
                            T[f,t] = tmpT[f,t];
                    }
                }

            }
            for (int q = 0; q < m; q++)
                if (tmpT[n - 1,q] < 0)
                    tmpT[n - 1, q] *= -1;

           
            
            using (StreamWriter sw = new StreamWriter("output.txt"))
            {
                sw.WriteLine(answer);
                for (int f = 0; f < n; f++)
                {
                    for (int t = 0; t < m; t++)
                    {
                        sw.Write(tmpT[f, t] + " ");
                    }
                    sw.WriteLine();
                }
                
            }
        }
    }
}
