import javafx.util.converter.BigIntegerStringConverter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

import static java.lang.Math.pow;

public class Main {
   
    public static void main(String[] args) throws IOException {

        
	Scanner in = new Scanner(new File("input.txt"));
        FileWriter out = new FileWriter("output.txt");
	int n = in.nextInt();
	
    BigInteger C[][] = new BigInteger[n+1][n+1];
    BigInteger Pow[] = new BigInteger[n+1];
    Pow[0] = BigInteger.valueOf(1);
    for(int i=1;i<=n;++i){
        Pow[i] = Pow[i-1].multiply(BigInteger.valueOf(2));
    }

    for(int i=0;i<=n;++i){
        C[i][0] = BigInteger.valueOf(1);
        C[0][i] = BigInteger.valueOf(1);
    }
    for(int i=1;i<=n;++i){
        for(int j=1;j<i;++j){
            C[j][i] = C[j-1][i-1].add(C[j][i-1]) ;
        }
        C[i][i] = BigInteger.valueOf(1);
    }

	BigInteger []f = new BigInteger[n+1];
        f[0] = BigInteger.valueOf(1);
        for(int i=1;i<=n;++i){
            f[i] = BigInteger.valueOf(0);
            int sign = 1;
            for(int j=1;j<=i;++j){
                if(sign==1) {
                    f[i] = f[i].add(C[j][i].multiply(Pow[i - j].pow(j)).multiply(f[i - j]));
                }
                else{
                    f[i] = f[i].subtract(C[j][i].multiply(Pow[i - j].pow(j)).multiply(f[i - j]));
                }
                sign*=-1;
            }
        }
        out.write(f[n].toString());
        out.close();


    }
}
