import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.PrintStream; 
import java.util.Scanner;
import java.util.Stack; 



public class IndTask { 
	public static void main(String[] args) throws FileNotFoundException { 
		Scanner in = new Scanner(new FileInputStream("input.txt")); 
		int L=0;
		String sotv="YES";
		PrintStream out = new PrintStream(new FileOutputStream("output.txt")); 
		Stack<Integer> mystack=new Stack<>();
		
		int st=0;
		int k=0;
		if(in.hasNext())
		{
			String t = in.next();
			while (k<t.length()) 
		{ 
			
			if(t.charAt(k)=='{') {
				mystack.push(1);
				L++;
				
			}
			if(t.charAt(k)=='(')
				{
				mystack.push(2);
				L++;
				}
			if(t.charAt(k)=='[')
				{
				mystack.push(3);
				L++;
				}
			if(t.charAt(k)=='}') {
				if(mystack.isEmpty())
				{
					sotv="NO";
					out.println(sotv);
					out.println(L);
					return;
				}
				else
					{
					st=mystack.pop();
				if(st!=1)
					{
					sotv="NO";
					out.println(sotv);
					out.println(L);
					return;
					}
				if(st==1)
					L++;
					}
			}
			if(t.charAt(k)==')') {
				if(mystack.isEmpty())
				{
					sotv="NO";
					out.println(sotv);
					out.println(L);
					return;
				}
				else
					{
				st=mystack.pop();
				if(st!=2)
					{
					sotv="NO";
					out.println(sotv);
					out.println(L);
					return;
					}
				if(st==2)
					L++;
					}
			}
			if(t.charAt(k)==']') {
				if(mystack.isEmpty())
				{
					sotv="NO";
					out.println(sotv);
					out.println(L);
					return;
				}
				else
					{
				st=mystack.pop();
				System.out.println(L);
				if(st!=3)
					{
					sotv="NO";
					out.println(sotv);
					out.println(L);
					return;
					}
				if(st==3)
					L++;
					}
			}
			k++;
		}
			
			if(!mystack.isEmpty()) {
				sotv="NO";
			}
			} 
		
		out.println(sotv); 
		System.out.println(sotv);
		if(sotv=="NO")
			out.println(L);
		in.close(); 
		out.close(); 
		System.exit(0); 
		} 
	}