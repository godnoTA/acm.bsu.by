import java.awt.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


 class Pair{

	private final int num;
	private int value;

	public Pair(int n, int v) {
		num = n;
		value = v;
	}
	public Pair(int n) {
		num = n;
		value = -1;
	}

	public int getNum() { return num; }
	public int getValue() { return value; }
	public void setValue(int v){value = v;}

}
public class Test_7 {

	public static void main(String []args) throws IOException{
		int N;
		Scanner scan = new Scanner(new File("in.txt"));
		N = scan.nextInt();
		scan.close();
		//ArrayList<Pair> list = new ArrayList<Pair>(N);
		LinkedList<Pair> linkedList = new LinkedList<Pair>();
		int[] result = new int[N];
		for(int i = 1; i <= N; i ++){
			//list.add(new Pair(i));
			linkedList.add(new Pair(i));
		}
		boolean flagpop = true;
		int color = 0;
		while(!linkedList.isEmpty()){//(!list.isEmpty()){
			if(flagpop == true){
				//Pair p = list.remove(0);
				Pair p = linkedList.remove();
				if(color == 0){
					p.setValue(0);
					color = 1;
				}
				else{
					color = 0;
					p.setValue(1);
				}
				result[p.getNum() - 1] = p.getValue();
				flagpop = false;
			}
			else{
				//Pair p = list.remove(0);
				Pair p = linkedList.remove();
				//list.add(p);
				linkedList.add(p);
				flagpop = true;
			}
		}
		FileWriter out = new FileWriter("out.txt");
		for(int i = 0; i < N; i ++){
			out.write(Integer.toString(result[i]));
			if(i != N - 1){
				out.write(" ");
			}
		}
		out.close();
	}
}
