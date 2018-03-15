import java.io.*;
import java.util.*;
public class Search {
    static BufferedWriter writer;
    public static void main(String[] args) throws IOException {
        Scanner in=new Scanner (new File("input.txt"));
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
        int n=in.nextInt();
        int k=1;
        int mas[][]=new int[n][n];
        int array[]= new int[n];
        Queue <Integer> dek = new LinkedList<>();
        boolean flag [] = new boolean [n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                mas[i][j]=in.nextInt();
            }
            flag[i]=false;
        }
        for(int i=0; i<n; i++) {
        	if(flag[i]==false){
        		 dek.add(i);
        		 flag[i]=true;
        		 array[i]=k;
        		 k++;
        		 while(!dek.isEmpty()){
                      int c=dek.poll();
                      for(int p=0; p<n;p++){
                          if(mas[c][p]==1 && flag[p]==false){
                              dek.add(p);
                              flag[p]=true;
                              array[p]=k;
                              k++;
                          }
                      }
                 }
        	}            
        }
        for(int i=0; i<n; i++) {
            writer.write(array[i]+" ");
        }
        writer.write("\n");
        in.close();
        writer.flush();
    }
}