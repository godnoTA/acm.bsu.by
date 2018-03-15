using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Collections;

namespace Trade_discounts
{
    class Program
    {
       
        static int[] discount(StreamReader input, ArrayList info, int discounts , ArrayList exceptions1)
        { // работаю со скидками


            int[] mas = new int[discounts + 3];

            string discount = input.ReadLine();
            string[] disc = discount.Split(' ');
            int k = 0;
          
            bool a = true;
           
            for (int prov = 0; prov < exceptions1.Count; prov++)
            {
                for (int discc = 1; discc < disc.Length - 1; discc = discc + 2)
                {
                    if (Convert.ToInt32(exceptions1[prov]) == Convert.ToInt32(disc[discc]))
                    {
                        return null;
                    }
                }
            }
            for (int i = 0, index_disk = 1, lol = 2; i < Convert.ToInt32(disc[0]); i++, index_disk = index_disk + 2, lol = lol + 2)
            {
                //Console.WriteLine(info.Count);
                for (int key = 0; key < info.Count; key = key + 3)
                {

                    if (Convert.ToInt32(info[key]) == Convert.ToInt32(disc[index_disk]))
                    {
                        mas[k] = Convert.ToInt32(disc[lol]);
                    }
                    k++;
                }
                k = 0;
            }
            mas[discounts] = Convert.ToInt32(disc[disc.Length - 1]);
            return mas;

        }

        static void Main(string[] args)
        {
            int cv = 20;
            StreamReader input = new StreamReader("discount.in");
            StreamWriter output = new StreamWriter("discount.out");
            int number_of_products = Convert.ToInt32(input.ReadLine());
            string[] words;
            string info_product;
            ArrayList inf = new ArrayList();
            int kkl;
            ArrayList exceptions1 = new ArrayList();
            int n = 0;
            int dublikat = number_of_products;
            int mnoz = 3;         
            while (n < number_of_products)
            {
                info_product = input.ReadLine();
                words = info_product.Split(' ');
                if (Convert.ToInt32(words[1]) >= 1 && Convert.ToInt32(words[1]) <= 5 && Convert.ToInt32(words[0]) >= 1 && Convert.ToInt32(words[0]) <= 999 && Convert.ToInt32(words[2]) >= 1 && Convert.ToInt32(words[2]) <= 9999)
                {                    
                    for (int i = 0; i < 3; i++)
                    {
                        inf.Add(words[i]);
                    }
                }
                else
                {
                    exceptions1.Add(words[0]);
                    dublikat--;
                }
                n++;
            }
            if (dublikat > 5 && dublikat < 0 )
            {
                output.WriteLine(0);
                output.Close();
            }
            else
            {
                switch (dublikat)
                {
                    case 0:
                        {
                            output.WriteLine(0);
                            output.Close();
                            break;
                        }
                    case 1:
                        {
                            int[] matrix = new int[Convert.ToInt32(inf[1]) + 1];
                            int first_price = Convert.ToInt32(inf[2]);
                            int border_strok = Convert.ToInt32(inf[1]);
                            for (int strok = 0; strok <= border_strok; strok++)
                            {
                                matrix[strok] = strok * first_price;
                            }
                            int discounts = Convert.ToInt32(input.ReadLine());
                            int dis = 0;
                            int[] mas;
                            while (dis < discounts)
                            {
                                mas = discount(input, inf, 1 , exceptions1);

                                if (mas == null)
                                {
                                    dis++;
                                }
                                else
                                {                                    
                                    matrix[mas[0]] = Math.Min(mas[1], matrix[mas[0]]);                                                                  
                                    for (int stroka = mas[0]; stroka <= border_strok; stroka++)
                                    {                                       
                                        matrix[stroka] = Math.Min(matrix[stroka], matrix[mas[0]] + matrix[stroka - mas[0]]);
                                    }
                                    dis++;
                                    mas[0] = 0;
                                }
                            }
                            output.Write(matrix[border_strok]);
                            output.Close();
                            break;
                        }
                    case 2:
                        {
                           
                            int[,] matrix = new int[Convert.ToInt32(inf[1]) + 1, Convert.ToInt32(inf[4]) + 1];                          
                            int first_price = Convert.ToInt32(inf[2]);
                            int second_price = Convert.ToInt32(inf[5]);
                            int border_strok = Convert.ToInt32(inf[1]);
                            int border_kol = Convert.ToInt32(inf[4]);
                            for (int stroka = 0; stroka <= border_strok; stroka++)
                            {
                                for (int kol = 0; kol <= border_kol; kol++)
                                {
                                    matrix[stroka, kol] = stroka * first_price + kol * second_price;
                                }
                            }
                            int discounts = Convert.ToInt32(input.ReadLine());
                            cv = cv * 4;
                            int dis = 0;
                            int[] mas;
                            kkl = 0;
                            while (dis < discounts)
                            {
                                cv = cv + 35;
                                mas = discount(input, inf, 2, exceptions1);
                                if (mas == null)
                                {
                                    dis++;
                                }
                                else
                                {                                                        
                                    if (mas[0] <= border_strok && mas[1] <= border_kol)
                                    {
                                        matrix[mas[0], mas[1]] = Math.Min(matrix[mas[0], mas[1]] , mas[2]);                                       
                                        
                                        for (int stroka = mas[0]; stroka <= border_strok; stroka++)
                                        {

                                            for (int kol = mas[1]; kol <= border_kol; kol++)
                                            {                                                
                                                matrix[stroka, kol] = Math.Min(matrix[stroka, kol], matrix[mas[0], mas[1]] + matrix[stroka - mas[0], kol - mas[1]]);
                                                
                                            }
                                           
                                        }
                                    }
                                    //Console.WriteLine(matrix[mas[0], mas[1]]);
                                    //for (int i = 0; i <= border_strok; i++)
                                    //{
                                    //    for (int j = 0; j <= border_kol; j++)
                                    //    {

                                    //        output.Write("{0}\t", matrix[i, j]);
                                    //    }
                                    //    output.WriteLine();
                                    //}
                                    //output.WriteLine();

                                    mas[0] = 0;
                                    mas[1] = 0;
                                    mas[2] = 0;
                                    dis++;
                                    kkl = matrix[border_strok, border_kol];
                                }
                            }                          
                            output.Write(matrix[border_strok , border_kol]);
                            output.Close();
                            break;

                            
                        }
                    case 3:
                        {
                           
                            int[,,] matrix = new int[Convert.ToInt32(inf[1]) + 1, Convert.ToInt32(inf[4]) + 1, Convert.ToInt32(inf[7]) + 1];
                            int first_price = Convert.ToInt32(inf[2]);
                            int second_price = Convert.ToInt32(inf[5]);
                            int third_price = Convert.ToInt32(inf[8]);
                            int border_strok = Convert.ToInt32(inf[1]);
                            int border_kol = Convert.ToInt32(inf[4]);
                            int border_gl = Convert.ToInt32(inf[7]);
                            mnoz *= mnoz;
                            for (int stroka = 0; stroka <= border_strok; stroka++)
                            {
                                for (int kol = 0; kol <= border_kol; kol++)
                                {
                                    for (int gl = 0; gl <= border_gl; gl++)
                                    {
                                        matrix[stroka, kol, gl] = stroka * first_price + kol * second_price + gl * third_price;
                                    }
                                }
                            }

                            int discounts = Convert.ToInt32(input.ReadLine());
                            int dis = 0;
                            int[] mas;
                            while (dis < discounts)
                            {
                                mas = discount(input, inf, 3, exceptions1);
                                if (mas == null)
                                {
                                    dis++;
                                }
                                else
                                {                                                                    
                                    if (mas[0] <= border_strok && mas[1] <= border_kol && mas[2] <= border_gl)
                                    {
                                        matrix[mas[0], mas[1], mas[2]] = Math.Min( matrix[mas[0], mas[1], mas[2]] , mas[3] );                                      
                                        for (int stroka = mas[0]; stroka <= border_strok; stroka++)
                                        {
                                            for (int kol = mas[1]; kol <= border_kol; kol++)
                                            {
                                                for (int gl = mas[2]; gl <= border_gl; gl++)
                                                {                                                   
                                                    matrix[stroka, kol, gl] = Math.Min(matrix[stroka, kol, gl], Math.Min( matrix[mas[0], mas[1], mas[2]] + matrix[stroka- mas[0] , kol - mas[1] , gl - mas[2]] , matrix[mas[0], mas[1], mas[2]]+ matrix[stroka - mas[0], 0, 0] + matrix[0, kol - mas[1], 0] + matrix[0,0, gl - mas[2]]));                                                    
                                                }                                               
                                            }                                           
                                        }
                                    }
                                   
                                    dis++;
                                    mas[0] = 0;
                                    mas[1] = 0;
                                    mas[2] = 0;
                                    mas[3] = 0;
                                    
                                    for (int i = 0; i <= border_strok; i++)
                                    { // вывод матрицы 

                                        for (int j = 0; j <= border_kol; j++)
                                        {
                                            for (int k = 0; k <= border_gl; k++)
                                            {
                                                Console.Write("{0}\t", matrix[i, j, k]);
                                            }
                                            Console.WriteLine();
                                        }
                                        Console.WriteLine();
                                    }
                                }

                            }
                            
                           
                            output.WriteLine(matrix[border_strok, border_kol, border_gl]);
                            output.Close();
                            break;
                        }
                    case 4:
                        {
                            int[,,,] matrix = new int[Convert.ToInt32(inf[1]) + 1, Convert.ToInt32(inf[4]) + 1, Convert.ToInt32(inf[7]) + 1, Convert.ToInt32(inf[10]) + 1];
                            int first_price = Convert.ToInt32(inf[2]);
                            int second_price = Convert.ToInt32(inf[5]);
                            int third_price = Convert.ToInt32(inf[8]);
                            int fourth_price = Convert.ToInt32(inf[11]);
                            int border_strok = Convert.ToInt32(inf[1]);
                            int border_kol = Convert.ToInt32(inf[4]);
                            int border_gl = Convert.ToInt32(inf[7]);
                            int border_gr = Convert.ToInt32(inf[10]);
                            for (int stroka = 0; stroka <= border_strok; stroka++)
                            {
                                for (int kol = 0; kol <= border_kol; kol++)
                                {
                                    for (int gl = 0; gl <= border_gl; gl++)
                                    {
                                        for (int gr = 0; gr <= border_gr; gr++)
                                        {
                                            matrix[stroka, kol, gl, gr] = stroka * first_price + kol * second_price + gl * third_price + fourth_price * gr;
                                        }
                                    }
                                }
                            }
                            int discounts = Convert.ToInt32(input.ReadLine());
                            int dis = 0;
                            int[] mas;
                            while (dis < discounts)
                            {

                                mas = discount(input, inf, 4, exceptions1);
                                if (mas == null)
                                {
                                    dis++;
                                }
                                else
                                {
                                    
                                    if (mas[0] <= border_strok && mas[1] <= border_kol && mas[2] <= border_gl && mas[3] <= border_gr)
                                    {
                                        
                                        matrix[mas[0], mas[1], mas[2], mas[3]] =Math.Min( mas[4] , matrix[mas[0], mas[1], mas[2], mas[3]]);                                       
                                        for (int stroka = mas[0]; stroka <= border_strok; stroka++)
                                        {

                                            for (int kol = mas[1]; kol <= border_kol; kol++)
                                            {
                                                for (int gl = mas[2]; gl <= border_gl; gl++)
                                                {
                                                    for (int gr = mas[3]; gr <= border_gr; gr++)
                                                    {                                                      
                                                        matrix[stroka, kol, gl, gr] = Math.Min(matrix[stroka, kol, gl, gr], matrix[mas[0], mas[1], mas[2], mas[3]] + matrix[stroka - mas[0],kol- mas[1], gl-mas[2],gr - mas[3]]);
                                                       
                                                    }
                                                    
                                                }
                                               
                                            }
                                          
                                        }
                                    }
                                    dis++;
                                    mas[0] = 0;
                                    mas[1] = 0;
                                    mas[2] = 0;
                                    mas[3] = 0;
                                    mas[4] = 0;
                                }
                            }
                            //for (int i = 0; i <= border_strok; i++)
                            //{ // вывод матрицы 

                            //    for (int j = 0; j <= border_kol; j++)
                            //    {
                            //        for (int k = 0; k <= border_gl; k++)
                            //        {
                            //            for (int g = 0; g <= border_gr; g++)
                            //            {
                            //                output.Write("{0}\t", matrix[i, j, k , g]);
                            //            }
                            //            output.WriteLine();
                            //        }
                            //        output.WriteLine();
                            //    }
                            //    output.WriteLine();
                            //}
                            output.WriteLine(matrix[border_strok, border_kol, border_gl, border_gr]);
                            output.Close();
                            break;
                        }
                    case 5:
                        {
                            int[,,,,] matrix = new int[Convert.ToInt32(inf[1]) + 1, Convert.ToInt32(inf[4]) + 1, Convert.ToInt32(inf[7]) + 1, Convert.ToInt32(inf[10]) + 1, Convert.ToInt32(inf[13]) + 1];
                            int first_price = Convert.ToInt32(inf[2]);
                            int second_price = Convert.ToInt32(inf[5]);
                            int third_price = Convert.ToInt32(inf[8]);
                            int fourth_price = Convert.ToInt32(inf[11]);
                            int fifth_price = Convert.ToInt32(inf[14]);
                            int border_strok = Convert.ToInt32(inf[1]);
                            int border_kol = Convert.ToInt32(inf[4]);
                            int border_gl = Convert.ToInt32(inf[7]);
                            int border_gr = Convert.ToInt32(inf[10]);
                            int border_gk = Convert.ToInt32(inf[13]);
                            for (int stroka = 0; stroka <= border_strok; stroka++)
                            {
                                for (int kol = 0; kol <= border_kol; kol++)
                                {
                                    for (int gl = 0; gl <= border_gl; gl++)
                                    {
                                        for (int gr = 0; gr <= border_gr; gr++)
                                        {
                                            for (int gk = 0; gk <= border_gk; gk++)
                                            {
                                                matrix[stroka, kol, gl, gr, gk] = stroka * first_price + kol * second_price + gl * third_price + fourth_price * gr + fifth_price * gk;
                                            }
                                        }
                                    }
                                }
                            }                            
                            int discounts = Convert.ToInt32(input.ReadLine());
                            int dis = 0;
                            int[] mas;
                            while (dis < discounts)
                            {
                                mas = discount(input, inf, 5, exceptions1);
                                if (mas == null)
                                {
                                    dis++;
                                }
                                else
                                {
                                    
                                    if (mas[0] <= border_strok && mas[1] <= border_kol && mas[2] <= border_gl && mas[3] <= border_gr && mas[4] <= border_gk)
                                    {                                       
                                        matrix[mas[0], mas[1], mas[2], mas[3], mas[4]] = Math.Min(matrix[mas[0], mas[1], mas[2], mas[3], mas[4]] , mas[5]);                                                                                                                    
                                        for (int stroka = mas[0]; stroka <= border_strok; stroka++)
                                        {
                                            for (int kol = mas[1]; kol <= border_kol; kol++)
                                            {
                                                for (int gl = mas[2]; gl <= border_gl; gl++)
                                                {
                                                    for (int gr = mas[3]; gr <= border_gr; gr++)
                                                    {
                                                        for (int gk = mas[4]; gk <= border_gk; gk++)
                                                        {
                                                            matrix[stroka, kol, gl, gr, gk] = Math.Min(matrix[stroka, kol, gl, gr, gk], matrix[mas[0], mas[1], mas[2], mas[3], mas[4]] + matrix[stroka - mas[0],kol - mas[1],gl - mas[2],gr - mas[3],gk - mas[4]]);
                                                            
                                                        }                                                        
                                                    }                                                    
                                                }                                               
                                            }                                          
                                        }                                        
                                    }
                                    dis++;
                                    mas[0] = 0;
                                    mas[1] = 0;
                                    mas[2] = 0;
                                    mas[3] = 0;
                                    mas[4] = 0;
                                    mas[5] = 0;
                                }
                                //for (int i = 0; i <= border_strok; i++)
                                //{ // вывод матрицы 

                                //    for (int j = 0; j <= border_kol; j++)
                                //    {
                                //        for (int k = 0; k <= border_gl; k++)
                                //        {
                                //            for (int g = 0; g <= border_gr; g++)
                                //            {
                                //                for (int l = 0; l <= border_gr; l++)
                                //                {
                                //                    output.Write("{0}\t", matrix[i, j, k, g , l]);
                                //                }
                                //                output.WriteLine();
                                //            }
                                //            output.WriteLine();
                                //        }
                                //        output.WriteLine();
                                //    }
                                //    output.WriteLine();
                                //}                                
                            }
                            output.WriteLine(matrix[border_strok, border_kol, border_gl, border_gr, border_gk]);
                            output.Close();
                            break;
                        }
                }
            }
        }
    }
}
