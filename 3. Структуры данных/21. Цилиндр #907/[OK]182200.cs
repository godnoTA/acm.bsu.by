using System;
using System.Collections.Generic;
using System.IO;

namespace Pr21
{
    class Program
    {
        static void Main(string[] args)
        {
            int[,] matrix;
            int Y;
            int X;
            int amount = 0;
            Stack<int> stackX = new Stack<int>();
            Stack<int> stackY = new Stack<int>();

            using (StreamReader file = new StreamReader("in.txt"))
            {
                var fstr = file.ReadLine().Split();
                Y = Int32.Parse(fstr[0]);
                X = Int32.Parse(fstr[1]);
                matrix = new int[Y, X];

                string s = "";
                int i = 0;
                int temp = 0;
                while ((s = file.ReadLine()) != null)
                {
                    var buf = s.Split(' ');
                    for (int j = 0; j < buf.Length; j++)
                        if (Int32.TryParse(buf[j], out temp))
                            matrix[i, j] = temp;
                    i++;
                }
            }

            


            for (int i = 0; i < Y; i++)
            {
                for (int j = 0; j < X; j++)
                {
                    if (matrix[i, j] == 0)
                    {
                        amount++;
                        stackX.Push(j);
                        stackY.Push(i);
                        while (stackX.Count != 0)
                        {
                            int x = stackX.Pop();
                            int y = stackY.Pop();

                            matrix[y, x] = 1;

                            if (x > 0 && matrix[y, x - 1] == 0)
                            {
                                stackX.Push(x - 1);
                                stackY.Push(y);
                            }
                            if (x < X - 1 && matrix[y, x + 1] == 0)
                            {
                                stackX.Push(x + 1);
                                stackY.Push(y);
                            }

                            if (y > 0)
                            {
                                if (matrix[y - 1, x] == 0)
                                {
                                    stackX.Push(x);
                                    stackY.Push(y - 1);
                                }
                            }
                            else
                            {
                                if (matrix[Y - 1, x] == 0)
                                {
                                    stackX.Push(x);
                                    stackY.Push(Y - 1);
                                }
                            }

                            if (y < Y - 1)
                            {
                                if (matrix[y + 1, x] == 0)
                                {
                                    stackX.Push(x);
                                    stackY.Push(y + 1);
                                }
                            }
                            else
                            {
                                if (matrix[0, x] == 0)
                                {
                                    stackX.Push(x);
                                    stackY.Push(0);
                                }
                            }

                        }
                    }
                }

                using (StreamWriter file = new StreamWriter("out.txt"))
                    file.Write(amount);
            }
        }
    }
}
