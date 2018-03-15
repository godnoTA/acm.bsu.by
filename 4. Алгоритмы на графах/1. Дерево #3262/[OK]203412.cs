using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace derevoo0
{
    class Program
    {
        static void Main(string[] args)
        {
            int N;
            int[][] graph;
            string temp;
            int j;
            using (StreamReader sr = new StreamReader("input.txt"))
            {
                N = Convert.ToInt32(sr.ReadLine());
                graph = new int[N] [];
                for (int i = 0; i < N; i++)
                {
                    graph[i] = new int[N];
                }
                    for (int i = 0; i < N; i++)
                {
                    temp = sr.ReadLine();
                    string[] w = temp.Split(' ');
                    for (j = 0; j < N; j++)
                    {
                        graph[i][ j] = Convert.ToInt32(w[j]);
                    }
                }
            }

            using (StreamWriter sw = new StreamWriter("output.txt"))
            {
                if (IsTree(graph, N))
                    sw.WriteLine("Yes");
                else
                    sw.WriteLine("No");
            }
            Console.WriteLine();

        }

        static bool dfs(int i, int[][] arr, bool [] col, int SIZE)
        {
            if (col[i])
            {
                return false;
            }
            else
            {
                col[i] = true;
                for (int j = 0; j < SIZE; ++j)
                {
                    if (arr[i][j] == 1)
                    {
                        dfs(j, arr, col, SIZE);
                    }
                }
                return true;
            }
        }

        static bool IsTree(int[][] arr, int SIZE)
        {
            int edges = 0;
            for (int i = 0; i < SIZE; ++i)
            {
                for (int j = i + 1; j < SIZE; ++j)
                {
                    if (arr[i][j]==1)
                    {
                        edges++;
                    }
                }
            }
            if (edges != SIZE - 1)
            {
                return false;
            }
            bool []col = new bool [SIZE];
            
            dfs(0, arr, col, SIZE);
            for (int i = 0; i < SIZE; ++i)
            {
                if (!col[i])
                {
                    return false;
                }
            }
            return true;
        }

        static bool originalFloydWarshall(int[,] matrix, int numberOfVert)
        {
            
            for (int k = 0; k < numberOfVert; k++)
            {
                for (int i = 0; i < numberOfVert; i++)
                {
                    for (int j = 0; j < numberOfVert; j++)
                    {
                        matrix[i,j] = Math.Min(matrix[i,j], matrix[i,k] + matrix[k,j]);
                    }
                }
            }

            for (int q = 0; q < numberOfVert; q++)
            {
                if (matrix[q,q] != 0)
                    return true;
            }

            return false;
        }
    }
}
