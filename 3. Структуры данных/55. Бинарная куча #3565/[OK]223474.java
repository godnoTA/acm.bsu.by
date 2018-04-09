import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class bin_ky4a {
public static void main(String[]args){
try(FileReader r = new FileReader("input.txt"); PrintWriter w = new PrintWriter("output.txt")){
Scanner in = new Scanner(r);

int k = in.nextInt();
if(k%2==0)
k++;
long [] mas = new long [k];
for(int i = 0; i < k; ++i)
{ 

if(in.hasNextLong())
mas[i]=in.nextLong();
else
mas[i]=Long.MAX_VALUE;

}
System.out.println("OK");
boolean flag = false;
if(k>1)
{
for(int i = 0; i <= (k-2)/2; ++i)
{
flag = true;
if(mas[i]>mas[2*i+1]||mas[i]>mas[2*i+2])

{
flag = false;
break;
}
}
if(flag)
w.println("Yes");
else
w.println("No");
}
else
w.println("Yes");
r.close();
w.close();
}
catch(Exception e) {
System.err.println(e.getMessage());
}
}
}