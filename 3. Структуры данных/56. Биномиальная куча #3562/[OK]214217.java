import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args)throws IOException {
        ArrayList<Long> arrayList=new ArrayList<>();
        long n=Long.parseLong(new StringTokenizer(new BufferedReader(new FileReader(new File("input.txt"))).readLine()).nextToken());
        while(n>0){
            arrayList.add(n%2);
            n/=2;
        }

        FileWriter fileWriter=new FileWriter("output.txt");
        for(int i = 0; i < arrayList.size();i++){
            if(arrayList.get(i)==1){
                fileWriter.write(Integer.toString(i));
                fileWriter.write("\n");
            }
        }
        fileWriter.flush();
    }
}
