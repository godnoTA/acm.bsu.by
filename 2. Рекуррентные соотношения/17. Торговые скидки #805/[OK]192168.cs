using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;





namespace hilllman
{
    class Discount
    {
        public int[] amount;
        public int price;
    }

    class Program
    {
        static int[,,,,] C;
        static Discount[] discounts;
        static int[] prices;
        static int[] bucket;
        static int allgoodscount;
        static int b;
        static int s;


        static void ListMultisets(int au4Boxes, int au4Balls, List<int> indexVector)
        {
            if (au4Boxes > 1)
            {
                for (int au4 = 0; au4 <= au4Balls; au4++)
                {
                    List<int> newStrBuild = new List<int>();
                    newStrBuild.AddRange(indexVector);
                    if (au4Balls - au4 > 5)
                        continue;
                    newStrBuild.Add(au4Balls - au4);
                    ListMultisets(au4Boxes - 1, au4, newStrBuild);
                }
            }
            else
            {
                if (au4Balls > 5)
                    return;
                indexVector.Add(au4Balls);
                while (indexVector.Count < 5)
                    indexVector.Add(0);

                //for (int i = 0; i < indexVector.Count; i++)
                //    Console.Write(indexVector[i] + " ");
                //Console.WriteLine();

                for (int i = 0; i < discounts.Length; i++)
                {
                    List<int> indexes = new List<int>(5);
                    bool flag = true;
                    for (int k = 0; k < b; k++)
                    {
                        if (indexVector[k] < discounts[i].amount[k])
                        {
                            flag = false;
                            break;
                        }
                        indexes.Add(indexVector[k] - discounts[i].amount[k]);
                    }
                    if (!flag)
                        continue;
                    while (indexes.Count < 5)
                        indexes.Add(0);

                    int currC = C[indexVector[0], indexVector[1], indexVector[2], indexVector[3], indexVector[4]];
                    int Cwithdiscount = C[indexes[0], indexes[1], indexes[2], indexes[3], indexes[4]] + discounts[i].price;
                    if (currC > Cwithdiscount)
                        C[indexVector[0], indexVector[1], indexVector[2], indexVector[3], indexVector[4]] = Cwithdiscount;
                }


            }
        }

        public static void InitFuctionValuesArray(int[,,,,] arr, int max_price)
        {
            for (int i1 = 0; i1 <= 5; i1++)
            {
                for (int i2 = 0; i2 <= 5; i2++)
                {
                    for (int i3 = 0; i3 <= 5; i3++)
                    {
                        for (int i4 = 0; i4 <= 5; i4++)
                        {
                            for (int i5 = 0; i5 <= 5; i5++)
                            {
                                arr[i1, i2, i3, i4, i5] = max_price * 25;
                            }
                        }
                    }
                }
            }
        }

        static void Main(string[] args)
        {
            System.IO.StreamReader input = new System.IO.StreamReader("discount.in");
            System.IO.StreamWriter output = new System.IO.StreamWriter("discount.out") { AutoFlush = true };

            //int max_price = -1;
            b = Int32.Parse(input.ReadLine());

            if (b == 0)
            {
                output.Write(0);
                return;
            }

            prices = new int[5];
            bucket = new int[5];

            string tmp;
            string[] tmparr;

            Dictionary<int, int> codesToIterators = new Dictionary<int, int>(b);
            allgoodscount = 0;
            for (int i = 0; i < b; i++)
            {
                tmp = input.ReadLine();
                tmparr = tmp.Split(' ');
                codesToIterators.Add(Int32.Parse(tmparr[0]), i);
                int tmpprice = Int32.Parse(tmparr[2]);
                int tmpamount = Int32.Parse(tmparr[1]);
                allgoodscount += tmpamount;
                bucket[i] = tmpamount;
                prices[i] = tmpprice;
                //if (tmpprice > max_price)
                //    max_price = tmpprice;
            }




            ///DISCOUNTS

            s = Int32.Parse(input.ReadLine());
            if (s == 0)
            {
                output.Write(bucket[0] * prices[0] + bucket[1] * prices[1] + bucket[2] * prices[2] + bucket[3] * prices[3] + bucket[4] * prices[4]);
                return;
            }

            discounts = new Discount[s];
            for (int i = 0; i < s; i++)
            {
                tmp = input.ReadLine();
                tmparr = tmp.Split(' ');
                int n = Int32.Parse(tmparr[0]);
                int[] tmpamount = new int[b];

                for (int j = 0; j < n; j++)
                {
                    int code = Int32.Parse(tmparr[2 * j + 1]);
                    int number = Int32.Parse(tmparr[2 * j + 2]);
                    tmpamount[codesToIterators[code]] = number;
                }
                for (int k = 0; k < b; k++)
                {
                    if (bucket[k] < tmpamount[k])
                        continue;
                }
                discounts[i] = new Discount
                {
                    amount = tmpamount,
                    price = Int32.Parse(tmparr[tmparr.Length - 1])
                };
            }





            C = new int[6, 6, 6, 6, 6];
            //InitFuctionValuesArray(C, max_price);

            for (int i1 = 0; i1 <= 5; i1++)
            {
                for (int i2 = 0; i2 <= 5; i2++)
                {
                    for (int i3 = 0; i3 <= 5; i3++)
                    {
                        for (int i4 = 0; i4 <= 5; i4++)
                        {
                            for (int i5 = 0; i5 <= 5; i5++)
                            {
                                C[i1, i2, i3, i4, i5] = i1 * prices[0] + i2 * prices[1] + i3 * prices[2] + i4 * prices[3] + i5 * prices[4];
                            }
                        }
                    }
                }
            }

            for (int cnt = 1; cnt <= allgoodscount; cnt++)
            {
                ListMultisets(b, cnt, new List<int>());
            }
            output.Write(C[bucket[0], bucket[1], bucket[2], bucket[3], bucket[4]]);
            output.Close();
        }
    }
}
