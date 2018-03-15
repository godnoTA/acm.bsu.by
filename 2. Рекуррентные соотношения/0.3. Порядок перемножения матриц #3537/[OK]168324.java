import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class noInteresting {
	
    public static class matrixSize {
	  int height;
	  int length;
      public matrixSize(int h, int l) {
		height = h;
		length = l;
	  }
    };

	public static void main(String[] args) throws IOException {
	Scanner SC = new Scanner(new File("input.txt"));
	int n = SC.nextInt();
	int [][] a = new int[n][n]; 
	matrixSize [] ms = new matrixSize[n];
    if(!SC.hasNext()) { SC.close(); return; }
       int idx = 0;
       while(SC.hasNext()) {
         ms[idx] = new matrixSize(SC.nextInt(), SC.nextInt());
         ++idx;
	   }
       for(int g = 0; g < a.length; ++g) {
    	   a[g][g] = 0;
       }
       
       for (int i = 1; i < n; ++i) {
    	   for (int h = 0; h < n - i; ++h) {
    		   int j = i + h;
    		   a[h][j] = Integer.MAX_VALUE;
    		   for(int p = h; p <= j - 1; p++) {
    			   a[h][j] = Math.min(a[h][j], a[h][p] + a[p + 1][j] + ms[h].height * ms[p + 1].height * ms[j].length);
    		   }
    	   }
       }
       
    try {
    	  PrintWriter pw = new PrintWriter(new File("output.txt"));    	
    	  pw.println(a[0][n-1]);
    	  pw.close();
    }
    catch (Exception e) {
    	e.printStackTrace();
    }
    SC.close();
    }
}
