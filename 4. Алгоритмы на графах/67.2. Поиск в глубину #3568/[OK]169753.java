import java.io.*;
import java.util.*;
public class SearchInDeep {
	
	static Queue <Integer> dek = new LinkedList<>();
	static int k=1;
	static void fun(int [] array,boolean []flag,int mas[][],int n,int c){
        for(int p=0; p<n;p++){
            if(mas[c][p]==1 && flag[p]==false){
                flag[p]=true;
                array[p]=k;
                k++;
                fun(array,flag,mas,n,p);
            }
        }
	}
    static BufferedWriter writer;
    public static void main(String[] args) throws IOException {
        Scanner in=new Scanner (new File("input.txt"));
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));
        int n=in.nextInt();
        int mas[][]=new int[n][n];
        int array[]= new int[n];
        boolean flag [] = new boolean [n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                mas[i][j]=in.nextInt();
            }
            flag[i]=false;
        }
        for(int i=0; i<n; i++) {
        	if(flag[i]==false){
        		 flag[i]=true;
        		 array[i]=k;
        		 k++;
                 fun(array,flag,mas,n,i);
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
