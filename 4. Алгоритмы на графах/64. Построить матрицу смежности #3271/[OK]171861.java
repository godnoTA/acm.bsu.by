import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TestingM {
	public static void main(String[] args) throws IOException {
		Scanner SC = new Scanner(new File("input.txt")); 
		if(!SC.hasNext()) { SC.close(); return; }
        int n = SC.nextInt();
        int m = SC.nextInt();
        int [][] mas = new int[n][n];
        for(int i = 0; i < n; i++) {
        	for(int g = 0; g < n; g++) {
        		mas[i][g] = 0;
        	}
        }
	    try {
	    	PrintWriter pw = new PrintWriter(new File("output.txt"));
            while(SC.hasNext()) {
            	int a = SC.nextInt() - 1;
            	int b = SC.nextInt() - 1;
            	mas[a][b] = 1;
                mas[b][a] = 1;
            }
            for(int i = 0; i < n; i++) {
            	for(int g = 0; g < n; g++) {
            		pw.print(mas[i][g] + " ");
            	}
            	pw.println();
            }
	    	pw.close();
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    SC.close();
	}
}
