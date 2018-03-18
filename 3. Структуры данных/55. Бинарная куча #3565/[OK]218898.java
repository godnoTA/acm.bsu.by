import java.io.*;

public class Main {

    public static void main(String[] args)throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("input.txt")))) {
            String line;
            boolean result = true;
            int numberOfData = Integer.parseInt(reader.readLine());
            int[] elements = new int[numberOfData];
            line = reader.readLine();
            String[] data = line.split(" ");
            for (int i = 0; i <data.length ; i++) {
                elements[i] = Integer.parseInt(data[i]);
            }
            int k =(numberOfData)/2;
            for (int i = 0; i <(numberOfData)/2 ; i++) {
                if(result){
                    if(2*k > numberOfData -1){
                        if (elements[k-1] > elements[2*k-1]){
                            result = false;
                        }
                    }
                    else if((elements[k-1] > elements[2*k-1])||(elements[k-1] > elements[2*k])){
                     result = false;
                 }
                 k--;
                }
                else break;
            }
            FileWriter writer = new FileWriter("output.txt");
            if ((result == true) || (data.length == 1)) {
                writer.write("Yes");
                writer.close();
            }
            else{
                writer.write("No");
                writer.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
