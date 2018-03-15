
import java.io.*;
import java.nio.charset.StandardCharsets;


/**
 * Created by Евгения on 17.02.2017.
 */
public class Test {
    public static void main(String[] args) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), StandardCharsets.UTF_8));
        String line = reader.readLine();
            String [] s = line.split(" ");
            int m = Integer.parseInt(s[0]);
            int c = Integer.parseInt(s[1]);
            int n = Integer.parseInt(s[2]);

            int [] table = new int [m];
            for(int i=0;i<m;i++){
                table[i]=-1;
            }
            int x,j,k;
            for(int i=0;i<n;i++){
                x = Integer.parseInt(reader.readLine());
                j=0;
                while (true){
                    k=((x%m)+c*j)%m;
                    if(table[k]==x){
                        break;
                    }
                    if(table[k]==-1){
                        table[k]=x;
                        break;
                    }
                    j++;
                }
            }

            File file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            for(int i=0;i<m;i++) {
                writer.write(table[i]+" ");
            }
            writer.write("\r\n");
            writer.close();
    }
}