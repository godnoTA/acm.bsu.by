import java.io.*; 
import java.util.*; 

public class test { 
public static void main(String[] args){ 
try { 
FileInputStream fis = new FileInputStream("input.txt"); 
FileOutputStream fos = new FileOutputStream("output.txt"); 
Scanner sc = new Scanner(fis); 
PrintStream ps = new PrintStream(fos); 
HashSet<Long> Set=new HashSet<Long>(); 

while(sc.hasNext()) { 
long w = Long.valueOf(sc.next()); 
Set.add(w); 
} 
long res=0; 
for(long i: Set) 
res+=i; 
ps.println(res); 


} 
catch (IOException ex){ 
System.out.println("Check your file"); 
} 
} 
}