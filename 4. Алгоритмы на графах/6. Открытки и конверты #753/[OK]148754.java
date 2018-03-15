import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Convert {
	
	public static class Rect
	{
		public double  x;
		public double y;
		Rect(double x,double y)
		{
			this.x=x;
			this.y=y;
		}
		
		public boolean isPlacedIn(Rect r2)
		{
			double A=Math.max(this.x, this.y);
			double B=Math.min(this.x, this.y);
			
			double C=Math.max(r2.x, r2.y);
			double D=Math.min(r2.x, r2.y);
		
			if(B>D)
				return false;
			
			
			if(A<=C)
				return true;
			
			
			double x1=0; double x2=0; 
			
			double d1=Math.sqrt(A*A*C*C-(A*A+B*B)*(C*C-B*B));

				x1=(A*C-d1)/(A*A+B*B);
								
				x2=(A*C+d1)/(A*A+B*B);
				
			double x0;
			
			if(x2<C/A)
				x0=x2;
			else
				x0=x1;
			
				double x3=Math.sqrt(1-x0*x0);
				
				if((A*x3+B*x0-D)<=0)
					return true;
		
		
			
		return false;
		}
	}
	
	
	public static Rect[] converts;
	
	public static Rect[] cards;
	
	public static int[][]mas;
	
	public static int [] checked;
	
	public static int [] to;
	
	
	public static int allCount=0;
	
	public static int N;

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		
		mainActivity();
		
	}
	
	public static void mainActivity() throws IOException
	{

		wrtitingFile();
		
		setMatrix();
		
		count();
		
		getResult();
		
		
	}
	
	public static void getResult()throws IOException 
	{
						
		
		 PrintWriter out = new PrintWriter(new FileWriter("output.out"));	
		 
		 if(allCount==N)
			out.println("YES");
		 else
		 {
			 out.println("NO");
			 out.println(allCount);
		 }

			
		    out.flush();
	}
	
	
	public static void wrtitingFile() throws IOException
	{
		Scanner fr=new Scanner(new FileReader("input.in"));
		
		N=fr.nextInt();
		
		converts=new Rect[N];
		
		cards=new Rect[N];
		mas=new int[N][N];
		
		checked=new int[N];
		to=new int[N];
				
		for(int i=0;i<N;i++)
		{
			cards[i]=new Rect(Double.parseDouble(fr.next()),Double.parseDouble(fr.next()));
		}
		
		for(int i=0;i<N;i++)
		{
			converts[i]=new Rect(Double.parseDouble(fr.next()),Double.parseDouble(fr.next()));
		}
		
	//	for(int i=0;i<3;i++)			
		//	for(int j=0;j<3;j++)
	//	if(cards[2].isPlacedIn(converts[2]))
			//System.out.println("YES");
	//	else
			//System.out.println("NO");
				
	}
	
	public static void setMatrix()
	{
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				if(cards[i].isPlacedIn(converts[j]))
					mas[i][j]=1;
	}
				
	public static int match(int v1)
	{ 

		  if(checked[v1] == 1)
		    return 0;
		  
		  checked[v1] = 1;
		  
		  for(int i = 0; i < N; i++) 
		    if (mas[v1][i]==1 && (to[i] == -1 || match(to[i])==1))
		    {
		      to[i] = v1; 
		      
		      return 1;
		    }  
		  return 0;    
	}
	
	static public void count()
	{
		for(int i=0;i<N;i++)
			to[i]=-1;
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
				checked[j]=0;
			
			match(i);
			
		}
		
		for(int i=0;i<N;i++ )
			if(to[i]!=-1)
				allCount++;
	}
	
	

}
