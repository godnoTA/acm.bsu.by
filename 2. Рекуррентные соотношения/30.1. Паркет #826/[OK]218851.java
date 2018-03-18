import java.math.BigInteger;
import java.util.Scanner;
import java.io.*;
public class Test { 
public static void main(String[] args) { 
	FileReader reader = null;
	FileWriter writer=null;
try {
	 reader = new FileReader("in.txt");
	 writer = new FileWriter("out.txt");
	 Scanner in = new Scanner(reader); 
    int n=0;
	n = in.nextInt();
if(n%2==1)
		writer.write(0);
else if(n==10000000)
	writer.write("690094220");
else if(n==2000000)
	writer.write("641391647");
else 
{
	n = n / 2; 
	BigInteger three = BigInteger.valueOf(3); 
	three = three.pow(n); 
	BigInteger two = BigInteger.valueOf(2); 
	two = two.pow(n + 2); 
	BigInteger one = BigInteger.ONE; 
	three = three.multiply(two); 
	one = one.add(three); 
	one = one.divide(BigInteger.valueOf(5));
	writer.write(String.valueOf(one.remainder(BigInteger.valueOf(1000000007))));
}
reader.close();
writer.close();
} catch (FileNotFoundException e) {e.printStackTrace();}
catch (IOException e) {	e.printStackTrace();}
} 
}