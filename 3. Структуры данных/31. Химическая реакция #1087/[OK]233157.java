import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Stack;

public class React {
	public static void main(String[] args) throws FileNotFoundException { 
		Scanner in = new Scanner(new FileInputStream("in.txt")); 
		int n, m;
		n=in.nextInt();
		m=in.nextInt();
		int[][]R=new int[n][n];
		int[]el=new int[m];
		Stack<Integer> myStack=new Stack<>();
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				R[i][j]=in.nextInt();
		for(int i=0; i<m; i++)
			el[i]=in.nextInt();
		PrintStream out = new PrintStream(new FileOutputStream("out.txt")); 
		int k=0, k1=0, f=0;
		myStack.push(el[0]);
		for(int i=1; i<m; i++) {
			k1=el[i];
			f=0;
			while(!myStack.isEmpty()&&f==0) {
			k=myStack.pop();
			if(R[k-1][k1-1]==0) {
				myStack.push(k);
				myStack.push(k1);
				f=1;
			}
			else {
				if(k==k1) {
					myStack.push(k);
					f=1;
					}
				else {
					k1=R[k-1][k1-1];
				}
				}
			}
			if(f==0) {
				myStack.push(k1);
			}
		}
		int K;
		K=myStack.pop();
		out.print(K);
		while(!myStack.isEmpty()) {
			K=myStack.pop();
			out.print(" "+K);
		}
		/*int K=0, k=0, k1=0;
		K=el[0];
		for(int i=1; i<m; i++) {
			k1=0;
			k=R[K-1][el[i]-1];
			if(k==0) {
				//out.print(K+" ");
				if(!myStack.isEmpty()) {
					k1=myStack.pop();
					k=R[k1-1][K-1];
					if(k!=0)
						K=k;
				}
				while(k!=0&&!myStack.isEmpty()) {
					k1=myStack.pop();
					k=R[k-1][k1-1];
					if(k!=0)
						K=k;
				}
				if(k==0&&k1!=0)
					myStack.push(k1);
				if(k1!=K)
					myStack.add(K);
				K=el[i];
			}
			else {
				K=k;
				if(!myStack.isEmpty()) {
					k1=myStack.pop();
					if(R[K-1][k1-1]==0)
						myStack.push(k1);
					else {
						K=R[K-1][k1-1];
					}
					while(k!=0&&!myStack.isEmpty()) {
						k1=myStack.pop();
						k=R[k-1][k1-1];
						if(k!=0)
							K=k;
						else
							if(k1!=K)
								myStack.push(k1);
					}
				}
			}
			//System.out.println(K);
		}
		k1=0;
		if(!myStack.isEmpty()) {
			k1=myStack.pop();
			k=R[k1-1][K-1];
			if(k!=0)
				K=k;
		}
		while(k!=0&&!myStack.isEmpty()) {
			k1=myStack.pop();
			k=R[k-1][k1-1];
			if(k!=0)
				K=k;
		}
		if(k==0&&k1!=0)
			myStack.push(k1);
		if(k1!=K)
			myStack.add(K);
		K=myStack.pop();
		out.print(K);
		while(!myStack.isEmpty()) {
			k=myStack.pop();
			if(K!=k) {
				K=k;
				out.print(" "+K);
			}
		}
		System.out.println(K);*/
		in.close(); 
		out.close(); 
		System.exit(0); 
		} 

}