import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        Integer i = 0;
        Integer size = Integer.parseInt(br.readLine());
        Integer[] array = new Integer[size];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        FileWriter fw = new FileWriter("output.txt");
        String result = "Yes";
        while(i < size){
            array[i++] = Integer.parseInt(st.nextToken());
        }
        for (i = 1; i < array.length; i++) {
            if(array[i] < array[(i - 1)/ 2]){
                result = "No";
            }
        }
        fw.write(result);
        System.out.println(result);
        fw.close();
    }
}
