using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
namespace Test_struct_2
{
    class Program
    {
        static void Main(string[] args)
        {
            int count = 0;
            Stack<long>[,] mas;
            StreamReader sr = new StreamReader("in.txt");

            long powK = Int32.Parse(sr.ReadLine());
            long length = (long)Math.Pow(4, powK);
            long sqrtLen = (long)Math.Pow(2, powK);
            mas = new Stack<long>[sqrtLen, sqrtLen];
            long[] result = new long[length];


            for (long i = 0; i < mas.GetLength(0); i++)
            {
                for (long j = 0; j < mas.GetLength(1); j++)
                {
                    mas[i, j] = new Stack<long>();
                    mas[i, j].Push(count);
                    count++;
                }
            }

            long curLength = mas.GetLength(0);

            while (curLength != 0)
            {
                for (long i = 0; i < curLength; i++)
                {
                    for (long j = 0; j < curLength / 2; j++)
                    {
                        while (mas[i, curLength - 1 - j].Count != 0)
                        {
                            mas[i, j].Push(mas[i, curLength - 1 - j].Pop());
                        }
                    }

                }

                for (long i = 0; i < curLength / 2; i++)
                {
                    for (long j = 0; j < curLength; j++)
                    {
                        while (mas[curLength - 1 - i, j].Count != 0)
                        {
                            mas[i, j].Push(mas[curLength - 1 - i, j].Pop());
                        }
                    }
                }

                curLength /= 2;
            }



            for (long i = 0; i < result.Length; i++)
            {
                result[mas[0, 0].Pop()] = result.Length - i;
            }
            string str = null;
            for(int i = 0;i < result.Length;i++)
            {
                str += result[i] + " ";
            }

            str = str.Trim();
            using (StreamWriter sw = new StreamWriter("out.txt"))
            {
                sw.Write(str);
            }
        }
    }
}