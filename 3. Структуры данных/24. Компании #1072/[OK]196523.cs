using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Kompanii
{
    class Program
    {
        static void Main(string[] args)
        {
            int max = 0;
            string temp;
            Queue<Tuple<int, int, int>> fifo = new Queue<Tuple<int, int, int>>();
            using (StreamReader sr = new StreamReader("input.txt"))
            {
                while (!sr.EndOfStream) {
                    temp = sr.ReadLine();
                    string[] t = temp.Split(' ');
                    int[] w = new int[3];
                    w[0] = Convert.ToInt32(t[0]);
                    w[1] = Convert.ToInt32(t[1]);
                    w[2] = Convert.ToInt32(t[2]);
                    if (w[0] >= w[1])
                        max = Math.Max(max, w[0]);
                    else
                        max = Math.Max(max, w[1]);
                    fifo.Enqueue(Tuple.Create(w[0], w[1], w[2]));
                }
            }
            int[,] info = new int[max + 1, max + 1];
            while (fifo.Count != 0)
            {
                Tuple<int, int, int> tempo = fifo.Dequeue();
                info[tempo.Item1, tempo.Item2] = tempo.Item3;
            }
            int[] driver = new int[max + 1];
            using (StreamWriter sw = new StreamWriter("output.txt"))
            {
                for (int i=1; i <= max; i++)
                {
                    List<int> answer = new List<int>();
                    Queue<int> q = new Queue<int>();
                    for (int j =1; j <= max; j++)
                    {
                        if (info[i, j] > 50)
                        {
                            q.Enqueue(j);
                            answer.Add(j);
                        }
                        driver[j] = info[i, j];
                    }
                    while (q.Count != 0)
                    {
                        int lol = q.Dequeue();
                        for (int j=1; j <= max; j++)
                        {
                            if (j != i)
                            {
                                driver[j] += info[lol, j];
                                /*if (driver[j] >= 50 && !answer.Contains(j))
                                {
                                    answer.Add(j);
                                    q.Enqueue(j);
                                }*/
                            }
                        }
                        for (int j = 1; j <= max; j++)
                        {
                            if (driver[j] > 50 && driver[j] <= 100 && !answer.Contains(j))
                            {
                                answer.Add(j);
                                q.Enqueue(j);
                            }

                        }
                    }
                    
                    /*if (answer.Contains(i))
                        answer.Remove(i);*/
                    answer.Sort();
                    for (int k = 0; k < answer.Count; k++)
                        sw.WriteLine(i.ToString() + ' ' + answer[k].ToString());
                    for (int j = 1; j <= max; j++)
                        driver[j] = 0;
                }
            }
        }
    }
}
