//хеш-таблица
import java.io.*;
import java.util.*;

public class Test {
    
    public void run(){
        try {

            Scanner in = new Scanner(new File("input.txt"));
            int m = in.nextInt();
            int c = in.nextInt();
            int n = in.nextInt();

            int hashTable[] = new int[m];
            int keys[] = new int[n];

            for(int i=0; i<n; i++){
                int d=in.nextInt();
                for(int count=0; count<i;count++){
                    if (keys[count]==d){
                        d=-1;
                        break;
                    }
                }
                keys[i]=d;
            }
            for(int i=0; i<m; i++){
                hashTable[i]=-1;
            }
            for(int i=0; i<n;i++){
                if(keys[i]!=-1){
                    for (int j=0; j<m;j++){
                    int hash=(int) ((keys[i]%m+c*j)%m);
                    if(hashTable[hash]==-1){
                        hashTable[hash]=keys[i];
                        break;}}
                }
            }

            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
            for(int i=0; i<m; i++){
                out.print(hashTable[i] + " ");
            }
            out.flush();
        }
        catch(Exception e){}
    }

    public static void main(String[] args) throws IOException {
        new Test().run();
    }
}