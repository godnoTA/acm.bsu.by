import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

class Stack {
	private int mSize;
    private int[] stackArray;
    private int top;
 
    public Stack(int m) {
        this.mSize = m;
        stackArray = new int[mSize];
        top = -1;
    }
    public void push(int element) {
        stackArray[++top] = element;
    }
 
    public int pop() {
        return stackArray[top--];
    }
 
    public int readTop() {
        return stackArray[top];
 
    }
 
    public boolean isEmpty() {
        return (top == -1);
    }
 
    public boolean isFull() {
        return (top == mSize - 1);
    }
}
public class Kant {
	public static void main(String[] args) throws IOException {
		File file = new File("in.txt");
		File file1 = new File("out.txt");
		BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
		PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
		int N, M;
		ArrayList<Integer> prob=new ArrayList<Integer>();
		String[] arr=in.readLine().split(" ");
		N=Integer.parseInt(arr[0]);
		M=Integer.parseInt(arr[1]);
		int[][] mas=new int[N][N];
		for(int i=0;i<N;i++){
			arr=in.readLine().split(" ");
			for(int j=0;j<N;j++){
				mas[i][j]=Integer.parseInt(arr[j]);
			}
		}
		/*for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				System.out.print(mas[i][j]+" ");
			}
			System.out.println();
		}*/
		arr=in.readLine().split(" ");
		for(int i=0;i<M;i++){
			prob.add(Integer.parseInt(arr[i]));
		}
		Stack stak=new Stack(M);
		for(int i=0;i<M;i++){
			if(stak.isEmpty()==true){
				stak.push(prob.get(i));
				continue;
			}
			int p=stak.pop();
			int f=mas[p-1][prob.get(i)-1];
			if(f==0){
				stak.push(p);
				stak.push(prob.get(i));
				continue;
			}
			if(f==p){
				stak.push(p);
				continue;
			}
			if(f>0){
				prob.set(i, f);
				i--;
				continue;
			}
		}
		out.print(stak.pop());
		while(stak.isEmpty()!=true){
			out.print(" "+stak.pop());
		}
		in.close();
		out.close();
	}
}
