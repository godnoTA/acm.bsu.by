using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Collections;

namespace The_second_longest_route
{
    public class BinaryHeap
    {

        private List<Pair> list;
        public BinaryHeap()
        {
            list = new List<Pair>();
        }
        public int heapSize
        {
            get
            {
                return this.list.Count();
            }
        }
        public void write()
        {
            foreach (Pair a in list)
            {
                Console.WriteLine(a.First);
                Console.WriteLine(a.Second);
                Console.WriteLine(a.Kol);
                Console.WriteLine();

            }
        }

        public void add(int x, int y, int kol , int a)
        {

            list.Add(new Pair(x, y, kol , a));
            int i = heapSize - 1;
            int parent = (i - 1) / 2;

            while (i > 0 && list[parent].Kol >= list[i].Kol)
            {
                Pair temp = list[i];
                list[i] = list[parent];
                list[parent] = temp;

                i = parent;
                parent = (i - 1) / 2;
            }
        }

        public void heapify(int i)
        {
            int leftChild;
            int rightChild;
            int largestChild;

            for (;;)
            {
                leftChild = 2 * i + 1;
                rightChild = 2 * i + 2;
                largestChild = i;

                if (leftChild < heapSize && list[leftChild].Kol < list[largestChild].Kol)
                {
                    largestChild = leftChild;
                }

                if (rightChild < heapSize && list[rightChild].Kol < list[largestChild].Kol)
                {
                    largestChild = rightChild;
                }

                if (largestChild == i)
                {
                    break;
                }

                Pair temp = list[i];
                list[i] = list[largestChild];
                list[largestChild] = temp;
                i = largestChild;
            }
        }
        public Pair getMax()
        {
            Pair result = list[0];
            
                list[0] = list[heapSize - 1];
                list.RemoveAt(heapSize - 1);
                heapify(0);           
            return result;
        }

    }
    public class Pair
    {
        public Pair()
        {
        }

        public Pair(int first, int second, int kol , int a)
        {
            this.First = first;
            this.Second = second;
            this.Kol = kol;
            this.a = a;
        }

        public int First { get; set; }
        public int Second { get; set; }
        public int Kol { get; set; }
        public int a { get; set; }
    }
    public class Pair1
    {
        public Pair1()
        {
        }

        public Pair1(int first, int second, int kol)
        {
            this.First = first;
            this.Second = second;
            this.Kol = kol;
        }

        public int First { get; set; }
        public int Second { get; set; }
        public int Kol { get; set; }
    }
    class Program
    {
        static void Main(string[] args)
        {
            StreamReader input = new StreamReader("input.in");
            StreamWriter output = new StreamWriter("output.out");
            string[] matr = input.ReadLine().Split(' ');
            List<Pair1>[] cpicok_cm = new List<Pair1>[Convert.ToInt32(matr[0])];
            for (int i = 0; i < Convert.ToInt32(matr[1]); i++)
            {
                if (i < Convert.ToInt32(matr[0]) && cpicok_cm[i] == null)
                {
                    cpicok_cm[i] = new List<Pair1>();
                }
                string[] line = input.ReadLine().Split(' ');
                if (cpicok_cm[Convert.ToInt32(line[0]) - 1] == null)
                {
                    cpicok_cm[Convert.ToInt32(line[0]) - 1] = new List<Pair1>();
                }

                cpicok_cm[Convert.ToInt32(line[0]) - 1].Add(new Pair1(Convert.ToInt32(line[1]) - 1, Convert.ToInt32(line[2]), 0));
            }
            int[] mas_block = new int[Convert.ToInt32(matr[0])];
            int[] len = new int[Convert.ToInt32(matr[0])];
            //for (int i = 0; i < Convert.ToInt32(matr[1]); i++)
            //{
            //    string[] line = input.ReadLine().Split(' ');
            //    matrix_cm[Convert.ToInt32(line[0]) - 1, Convert.ToInt32(line[1]) - 1] = Convert.ToInt32(line[2]);
            //}

            string[] town = input.ReadLine().Split(' ');
            BinaryHeap heap = new BinaryHeap();
            Pair a;
            heap.add(Convert.ToInt32(town[0]) - 1, 0, 0 , 0);
            List<KeyValuePair<Pair1, int>> list1 = new List<KeyValuePair<Pair1, int>>();
            
            while (heap.heapSize != 0)
            {
                a = heap.getMax();
                //Console.WriteLine(a.First + "  " + a.Second + " " + a.Kol);
                
                mas_block[a.First] += 1;
                if (mas_block[a.First] <= 2)
                {
                    list1.Add(new KeyValuePair<Pair1, int>(new Pair1(a.First, a.Second, a.Kol), a.a));

                    //mas_block[a.First] += 1;

                    if (a.First == Convert.ToInt32(town[1]) - 1 && mas_block[a.First] == 2)
                    {
                        output.WriteLine(a.Kol);
                        break;
                    }
                    //if (mas_block[a.First] <= 2)
                    //{
                    int lenn = cpicok_cm[a.First].Count;
                    for (int i = 0; i < lenn; i++)
                        {
                       
                        if (mas_block[cpicok_cm[a.First][i].First] < 2)
                            {
                            
                                len[cpicok_cm[a.First][i].First] = a.Kol + cpicok_cm[a.First][i].Second;
                                //Console.WriteLine(cpicok_cm[a.First][i].First + "  " + a.First + " " + len[i] + " "+ a.Kol);

                                heap.add(cpicok_cm[a.First][i].First, a.First, len[cpicok_cm[a.First][i].First], cpicok_cm[a.First][i].Second);

                            }

                        }
                    //}
                }

            }
            //foreach (KeyValuePair<Pair1, int> c in list1)
            //{
            //    Console.WriteLine(c.Key.First + " " + c.Key.Second + " " + c.Key.Kol + " " + c.Value);
            //}
                  
            List<int> output1 = new List<int>();
            int promez_ot = list1[list1.Count - 1].Key.Second;
            int promez_kuda = list1[list1.Count - 1].Key.First;
            int promez_price = list1[list1.Count - 1].Key.Kol;
            int fin_price = list1[list1.Count - 1].Value;
            //output.WriteLine(len[Convert.ToInt32(town[1]) - 1]);
            output1.Add(list1[list1.Count - 1].Key.First + 1);
            for (int i = list1.Count - 1; i >= 0; i--)
            {
                if (promez_ot == list1[i].Key.First && promez_price - list1[i].Key.Kol == fin_price)
                {
                    //for(int j = 0; j < cpicok_cm[promez_ot].Count; j++)
                    //{
                    //    if (cpicok_cm[promez_ot][j].First == promez_kuda && promez_price - list1[i].Key.Kol == cpicok_cm[promez_ot][j].Second)
                    //    {
                    output1.Add(list1[i].Key.First + 1);
                    promez_ot = list1[i].Key.Second;
                    promez_kuda = list1[i].Key.First;
                    promez_price = list1[i].Key.Kol;
                    fin_price = list1[i].Value;
                    //    }
                    //}

                }
            }



            for (int i = output1.Count-1; i>=0; i--)
            {
                output.Write(output1[i] + " ");
            }
            //if (list[list.Count - 1] != Convert.ToInt32(town[1]) )
            //{
            //    list.Add(Convert.ToInt32(town[1]));
            //}
            //foreach (int c in list)
            //{
            //    Console.Write(c + " ");
            //}
            output.Close();
        }
    }
}
