import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Test_67_1 {
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("input.txt"));int N = in.nextInt();
        int [][] M = new int[N + 1][N + 1];
        int [] result = new int[N + 1];
        
        for (int i = 1; i <= N; i++) 
        	for (int j = 1; j <= N; j++)
        		M[i][j] = in.nextInt();
        
        in.close();
 
        Queue<Integer> Q = new LinkedList<Integer>();
        Q.add(1);
        int count = 0;
        
        for (int i = 1; i <= N; i++)
        	if (result[i] == 0) {
        		Q.add(i);
        		count ++;
        		result[i] = count;
        		while (Q.isEmpty() == false) {
        			int el = Q.poll();
        			for (int j = 1; j <= N; j++)
        				if (M[el][j] == 1 && result[j] == 0) {
        					Q.add(j);
        					count ++;
        					result[j] = count;
        				}
        		}
        	}
      
        	
        FileWriter out = new FileWriter("output.txt");
        for (int i = 1; i <= N; i++)
        	out.write(result[i] + " ");
        out.write("\n");
        out.close();      
        		
    }
	
	
}
