import java.io.*;
import java.nio.charset.StandardCharsets;


public class Test {

    public static boolean testIsHeap(int [] a){
        if(a.length==0){
            return false;
        }
        for (int j=1;j<=(a.length-1)/2;j++){
            if (2*j<a.length-1){
                if(a[j]>a[2*j]||a[j]>a[2*j+1]){
                    return false;
                }
            }
            else{
                if(a[j]>a[2*j]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("input.txt"), StandardCharsets.UTF_8))) {
            String line;
            if((line = reader.readLine()) == null)
                throw (new Exception("Empty file!"));
            int n = Integer.parseInt(line);
            line=reader.readLine();
            String [] s = line.split(" ");
            int [] a = new int[n+1];
            for(int i=0;i<n;i++){
                a[i+1]=Integer.parseInt(s[i]);
            }


            File file = new File("output.txt");
            FileWriter writer = new FileWriter(file);
            if(testIsHeap(a)){
                writer.write("Yes\r\n");
            }
            else{
                writer.write("No\r\n");
            }
            writer.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
