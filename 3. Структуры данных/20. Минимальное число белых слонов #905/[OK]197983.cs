using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;





namespace hilllman
{
    class Program
    {
        static int[,] desk;
        static int M;
        static int N;

        static void Walk(int x, int y, int count)
        {
            desk[x, y] = count;
            // Нижняя граница
            if (y > 0)
            {
                if (x > 0)
                    if (desk[x - 1, y - 1] == 0)
                        Walk(x - 1, y - 1, count);
                if (x < M - 1)
                    if (desk[x + 1, y - 1] == 0)
                        Walk(x + 1, y - 1, count);
            }
            // Верхняя граница
            if (y < N - 1)
            {
                if (x < M - 1)
                    if (desk[x + 1, y + 1] == 0)
                        Walk(x + 1, y + 1, count);
                if (x > 0)
                    if (desk[x - 1, y + 1] == 0)
                        Walk(x - 1, y + 1, count);
            }

        }
        public class Point
        {
            public int x;
            public int y;
            public Point(int _x, int _y)
            {
                x = _x;
                y = _y;
            }
            public Point()
            {
                x = 0;
                y = 0;
            }
        }

        static void Main(string[] args)
        {
            System.IO.StreamReader input = new System.IO.StreamReader("in.txt");
            System.IO.StreamWriter output = new System.IO.StreamWriter("out.txt") { AutoFlush = true };
            string line;
            string[] tmparr;
            line = input.ReadLine().Trim();
            tmparr = line.Split(' ');
            N = Int32.Parse(tmparr[0]);
            M = Int32.Parse(tmparr[1]);
            desk = new int[N, M];


            for (int i = 0; i < N; i++)
            {
                line = input.ReadLine().Trim();
                tmparr = line.Split(new[] { " " }, StringSplitOptions.RemoveEmptyEntries);

                for (int j = 0; j < M; j++)
                    desk[i, j] = Int32.Parse(tmparr[j]);
            }

            int counter = 0;
            Point[] walked = new Point[500000];
            for (int i = 0; i < 500000; i++)
                walked[i] = new Point();

            for (int i = 0; i < N; i++)
                for (int j = 0; j < M; j++)
                    if (desk[i, j] == 0)
                    {
                        int startIndex = 0, endIndex = 1;
                        desk[i, j] = 1;
                        ++counter;
                        walked[0].x = i;
                        walked[0].y = j;

                        while (startIndex < endIndex)
                        {
                            if (walked[startIndex].x > 0)
                            {
                                if (walked[startIndex].y > 0)
                                {
                                    if (desk[walked[startIndex].x - 1, walked[startIndex].y - 1] == 0)
                                    {
                                        desk[walked[startIndex].x - 1, walked[startIndex].y - 1] = 1;
                                        walked[endIndex].x = walked[startIndex].x - 1;
                                        walked[endIndex++].y = walked[startIndex].y - 1;
                                    }
                                }
                                if (walked[startIndex].y < M - 1)
                                {
                                    if (desk[walked[startIndex].x - 1, walked[startIndex].y + 1] == 0)
                                    {
                                        desk[walked[startIndex].x - 1, walked[startIndex].y + 1] = 1;
                                        walked[endIndex].x = walked[startIndex].x - 1;
                                        walked[endIndex++].y = walked[startIndex].y + 1;
                                    }
                                }
                            }
                            if (walked[startIndex].x < N - 1)
                            {
                                if (walked[startIndex].y > 0)
                                {
                                    if(desk[walked[startIndex].x + 1, walked[startIndex].y - 1] == 0)
                                    {
                                        desk[walked[startIndex].x + 1, walked[startIndex].y - 1] = 1;
                                        walked[endIndex].x = walked[startIndex].x + 1;
                                        walked[endIndex++].y = walked[startIndex].y - 1;
                                    }
                                }
                                if (walked[startIndex].y < M - 1)
                                {
                                    if(desk[walked[startIndex].x + 1, walked[startIndex].y + 1] == 0)
                                    {
                                        desk[walked[startIndex].x + 1, walked[startIndex].y + 1] = 1;
                                        walked[endIndex].x = walked[startIndex].x + 1;
                                        walked[endIndex++].y = walked[startIndex].y + 1;
                                    }
                                }
                            }
                            startIndex++;
                        }
                    }
            //for (int j = 0; j < M; j++)
            //    for (int i = 0; i < N; i++)
            //        if (desk[j, i] == 0)
            //            Walk(j, i, ++counter);

            output.Write(counter);
            output.Close();
        }
    }
}
