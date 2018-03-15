import java.io.IOException;
import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new InputStreamReader(new FileInputStream("in.txt")));
        FileWriter writer = new FileWriter("out.txt", false);

        int rowLength = scan.nextInt();
        int numbersCount = scan.nextInt();
        int[] greenNumbers = new int[numbersCount];
        int greenSum = 0;
        int whiteSum = 0;
        int whitePlacesCount = numbersCount + 1;


        for(int i = 0 ; i < greenNumbers.length ; i++){
            greenNumbers[i] = scan.nextInt();
            greenSum += greenNumbers[i];
        }

        whiteSum = rowLength - greenSum;
        if(numbersCount > 1){
            whiteSum -= (numbersCount - 1);
        }

        BigInteger differentRowsCount = doFactorial(whiteSum + whitePlacesCount - 1).divide((doFactorial(whitePlacesCount - 1)));
        differentRowsCount = differentRowsCount.divide(doFactorial(whiteSum));

        writer.write(differentRowsCount.toString());
        writer.flush();
    }

    public static BigInteger doFactorial(int n){
        BigInteger fact = new BigInteger("1");
        for(int i = 1 ; i <= n ; i++){
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }
}