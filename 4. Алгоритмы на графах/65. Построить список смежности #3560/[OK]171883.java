import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TestingM {
	public static void main(String[] args) throws IOException {
		Scanner SC = new Scanner(new File("input.txt")); 
		if(!SC.hasNext()) { SC.close(); return; }
        int n = SC.nextInt();
        int m = SC.nextInt();
        ArrayList<Integer> [] mas = new ArrayList[n+1];
        for(int i = 1; i < n + 1; i++) {
        		mas[i] = new ArrayList<Integer>();
        }
	    try {
	    	PrintWriter pw = new PrintWriter(new File("output.txt"));
            while(SC.hasNext()) {
            	int a = SC.nextInt();
            	int b = SC.nextInt();
            	mas[a].add(b);
            	mas[b].add(a);
            }
            for(int i = 1; i < n + 1; i++) {
        		pw.print(mas[i].size() + " ");
        		for(int x : mas[i]) {
        			pw.print(x + " ");
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
