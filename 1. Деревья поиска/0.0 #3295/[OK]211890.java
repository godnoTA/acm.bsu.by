import java.io.FileWriter; 
import java.io.PrintWriter; 
import java.util.*; 
import java.io.File; 
import java.io.IOException;
public class Main { 
public static void main(String[] args) throws IOException{ 
Scanner sc = new Scanner(new File("input.txt")); 
PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
Set<Long> set = new TreeSet<>(); 
Long sum = new Long(0);
while (sc.hasNext()) 
{ set.add(sc.nextLong()); } 
Iterator<Long> it = set.iterator(); 
while(it.hasNext()) { sum += it.next(); } 
out.println(sum); 
out.flush(); }
 }