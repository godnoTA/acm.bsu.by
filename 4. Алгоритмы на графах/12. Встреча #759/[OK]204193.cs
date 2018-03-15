using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace LastAlgoNist
{
    class Program
    {
        
        static void Main(string[] args)
        {
            int N =0;
            int M =0;
            int[][] w;
            string[] temp = new string[2];
            int[] sum;
            int answer_V = 0;
            int answer_S = int.MaxValue;
            using (StreamReader sr = new StreamReader("input.in"))
            {
                temp = sr.ReadLine().Split(' ');
                N = Convert.ToInt32(temp[0]);
                M = Convert.ToInt32(temp[1]);
                w = new int[N+1][];
                sum = new int[N + 1];
                for (int i =0; i <= N; i++)
                {
                    w[i] = new int[N + 1];
                    sum[i] = 0;
                }
                for (int i = 1; i <= N; i++)
                {
                    for (int j = 1; j <= N; j++)
                    {
                        w[i][j] = int.MaxValue;
                    }
                }

                for (int i = 0; i < M; i++)
                {
                    string [] temp2 = sr.ReadLine().Split(' ');
                    w[Convert.ToInt32(temp2[0])][Convert.ToInt32(temp2[1])] = Convert.ToInt32(temp2[2]);
                    w[Convert.ToInt32(temp2[1])][Convert.ToInt32(temp2[0])] = Convert.ToInt32(temp2[2]);
                }
                
            }
            for (int i=1; i <= N; i++)
            {
                sum[i] = Dijkstra(i,w, N);
                if (sum[i] < answer_S)
                {
                    answer_S = sum[i];
                    answer_V = i;
                }
            }
            
            
            using (StreamWriter sw = new StreamWriter("output.out"))
            {
                if (N == 2)
                {
                    sw.WriteLine("1 2 " + (w[1][2] / 2).ToString());
                }
                else
                {
                    sw.WriteLine(answer_V.ToString() + " " + answer_S.ToString());
                }
            }
        }
        static int Dijkstra(int st, int[][]w, int N)
        {
           
            bool [] visited = new bool [N+1];
            int [] D = new int [N+1];
            for (int i = 1; i <= N; i++)
            {
                D[i] = w[st][i];
                visited[i] = false;
            }
            D[st] = int.MaxValue;
            int index = 0, u = 0;
            for (int i = 1; i <= N; i++)
            {
                int min = int.MaxValue;
                for (int j = 1; j <= N; j++)
                {
                    if (!visited[j] && D[j] < min)
                    {
                        min = D[j];
                        index = j;
                    }
                }
                u = index;
                visited[u] = true;
                for (int j = 0; j <= N; j++)
                {
                    if (!visited[j] && w[u][j] != int.MaxValue && D[u] != int.MaxValue && (D[u] + w[u][j] < D[j]))
                    {
                        D[j] = D[u] + w[u][j];
                    }
                }
            }
            int res = 0;
            for (int i = 1; i <= N; i++)
            {
                if (st!=i)
                    res += D[i];
            }
            
            return res;
        }
    }
}
