import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import static java.lang.Math.pow;

public class Main {

    public static void main(String[] args){

        int n = 0;
        BigInteger c = new BigInteger("1");
        BigInteger p = new BigInteger("1");
        BigInteger num2 = new BigInteger("2");
        BigInteger num_1 = new BigInteger("-1");
        BigInteger temp = new BigInteger("0");




        try {
            Scanner in = new Scanner(new File("input.txt"));
            while (in.hasNext())
            {
                n = in.nextInt();
            }


            BigInteger[] array_T = new BigInteger[n + 1];


            for (int i = 0; i <= n; ++i)
            {
                array_T[i] = BigInteger.ZERO;
            }
            array_T[0] = BigInteger.ONE; array_T[1] = BigInteger.ONE;



            for (int i = 2; i <= n; ++i)
            {
                c = BigInteger.ONE;
                for (int j = i - 1; j >= 0; --j)
                {
                    p = num2.pow((j + 1) * (i - j - 1));
                    if (j % 2 == 0)
                        //array_T[i] += array_T[i - j - 1] * c * pow(2, (j + 1)*(i - j - 1));
                        array_T[i] = array_T[i].add(array_T[i - j - 1].multiply(c).multiply(p));
                    else
                        //array_T[i] -= array_T[i - j - 1] * c * pow(2, (j + 1)*(i - j - 1));
                        array_T[i] = array_T[i].add(array_T[i - j - 1].multiply(c).multiply(p).multiply(num_1));
                    //c *= (j + 1); c /= (i - j);
                    c = c.multiply(new BigInteger(Integer.toString(j + 1 )));
                    c = c.divide(new BigInteger(Integer.toString(i - j)));
                }
            }


            FileWriter writer = new FileWriter("output.txt");
            writer.write(array_T[n].toString());
            writer.close();

            System.out.println(array_T[n].toString());


        }catch(IOException e)
        {
            System.out.println(e);
        }
    }

}
