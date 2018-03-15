import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(new File("input.txt")); 
		int numVertexs = sc.nextInt();
        int numEdges = sc.nextInt();
        
        int[] connectQuantity = new int[numVertexs];
        StringBuilder[] connectList = new StringBuilder[numVertexs];
        for (int i = 0; i < numVertexs; i++) {
        	connectList[i] = new StringBuilder("");
        }
        
        int x;
        int y;
        
        for (int i = 0; i < numEdges; i++) {
        	x = sc.nextInt();
        	y = sc.nextInt();
        	
        	connectQuantity[x - 1]++;
        	connectQuantity[y - 1]++;
        	
        	connectList[x - 1].append(" ").append(y);
        	connectList[y - 1].append(" ").append(x);
        }
        
        FileWriter fileWriter = new FileWriter("output.txt");
        for (int i = 0; i < connectQuantity.length; i++) {
            fileWriter.write(connectQuantity[i] + String.valueOf(connectList[i]));
            if (i < connectQuantity.length - 1) {
                fileWriter.write("\n");
            }
        }
        fileWriter.close();
	}
}