import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class de1 {
public static void main(String[]args){
try(FileReader r = new FileReader("input.txt"); PrintWriter w = new PrintWriter("output.txt")){
Scanner in = new Scanner(r);

long k = in.nextLong();
if(k==1)
	w.println("0");
else{
	String str = Long.toString(k, 2);

for(int i = str.length()-1; i>=0; i--)
{
	if(str.charAt(i)=='1')
	w.println(str.length()-i-1);
}
}
r.close();
w.close();
}
catch(Exception e) {
System.err.println(e.getMessage());
}
}
}