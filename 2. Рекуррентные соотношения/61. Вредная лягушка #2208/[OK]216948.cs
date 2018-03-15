using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace TA_Frogs
{
    class Program
    {
        static void Main(string[] args)
        {
            int n;
            string[] temp;            
            Dictionary<int, HashSet<int>> f = new Dictionary<int, HashSet<int>>();
            int[] bushesY;
            int[] bushesX;
            int r;
            int c;
            using(var reader = new StreamReader("input.txt"))
            {
                 temp = reader.ReadLine().Split(' ');
                 r = int.Parse(temp[0]);
                 c = int.Parse(temp[1]);
 
                 n = int.Parse(reader.ReadLine());
 
                 bushesY = new int[n];
                 bushesX = new int[n];

                for (int i = 0; i < n; ++i)
                {
                    temp = reader.ReadLine().Split(' ');
                    int x = int.Parse(temp[1]);
                    int y = int.Parse(temp[0]);
                    bushesY[i] = int.Parse(temp[0]);
                    bushesX[i] = int.Parse(temp[1]);
                    if(!f.ContainsKey(x))
                    {
                        f[x] = new HashSet<int>();
                    }
                    f[x].Add(y);
                }
            }

            int maxPath = 0;
 
            Sort(bushesX, bushesY, 0, n - 1);
 
            int xDiff, yDiff, topPointYCoordinate;
            for (int i = 0; i < n - 1; ++i)
            {
                for (int j = i + 1; j < n; ++j)
                {
                    topPointYCoordinate = Math.Min(bushesY[i], bushesY[j]);
                    yDiff = bushesY[i] - bushesY[j];
                    xDiff = bushesX[i] - bushesX[j];
                    int cnt = 0;
                    if (xDiff == 0 && (topPointYCoordinate - Math.Abs(yDiff) < 1))
                    {
                        cnt = 1;
                        for (int y = bushesY[i] - yDiff; y <= r && y > 0; ++cnt, y -= yDiff)
                        {
                            if (IsTrue(bushesX[i], y, f) == false)
                            {
                                cnt = 0;
                                break;
                            }
                            if (y > r)
                                break;
                        }
                    }
                    else if ((bushesX[i] + xDiff < 1) || (bushesY[i] + yDiff < 1))
                    {
                        cnt = 1;
                        for (int x = bushesX[i] - xDiff, y = bushesY[i] - yDiff; 
                         x <= c && y <= r && x > 0 && y > 0; x -= xDiff, y -= yDiff)
                         {
                            if (x > c || y > r)
                                break;
                            if (IsTrue(x, y, f) == false)
                            {
                                cnt = 0;
                                break;
                            }
                            else
                                ++cnt;
                         }

                    }
                    if (cnt > 2 && cnt > maxPath)
                        maxPath = cnt;
                }
            }
            using (var writer = new StreamWriter("output.txt"))
            {
                writer.WriteLine(maxPath);
            }
        }

        static Boolean IsTrue(int x, int y, Dictionary<int, HashSet<int>> map)
        { 
           if(map.ContainsKey(x) && map[x].Contains(y)) 
                return true; 
            return false; 
        }

        static int Partition(int[] arr, int[] arrY, int low, int high)
        { 
            int pivot = arr[high]; 
            int i = (low - 1); 
            int temp;
            for (int j = low; j < high; j++)
            { 
                if (arr[j] <= pivot)
                { 
                    i++; 
                    temp = arr[i]; 
                    arr[i] = arr[j]; 
                    arr[j] = temp; 

                    temp = arrY[i]; 
                    arrY[i] = arrY[j]; 
                    arrY[j] = temp; 
                } 
           } 
           temp = arr[i + 1]; 
           arr[i + 1] = arr[high]; 
           arr[high] = temp; 

           temp = arrY[i + 1]; 
           arrY[i + 1] = arrY[high]; 
           arrY[high] = temp; 
           return i + 1; 
        }   
 
        static void Sort(int[] arr, int[] arrY, int low, int high) 
        { 
            if (low < high) 
            { 
                int pi = Partition(arr, arrY, low, high); 
                Sort(arr,arrY, low, pi-1); 
                Sort(arr,arrY, pi+1, high); 
            } 
        } 
    }
}