import java.util.*;
import java.io.*;

public class Main {
    public static void main (String [] args) throws Exception{
        FileInputStream streamToInputData = new FileInputStream("input.txt");
        InputStreamReader objectToInputData = new InputStreamReader(streamToInputData);
        BufferedReader secondObjectToInputData = new BufferedReader(objectToInputData);

        FileOutputStream streamTpOutputData = new FileOutputStream("output.txt");
        OutputStreamWriter objectToOutputData = new OutputStreamWriter(streamTpOutputData);
        BufferedWriter secondObjectToOutputData = new BufferedWriter(objectToOutputData);

        TreeSet<Long> forResult = new TreeSet<Long>();

        String buff = secondObjectToInputData.readLine();

        long result = 0;

        while(buff != null){
            long number = Long.parseLong(buff);
            forResult.add(number);
            buff = secondObjectToInputData.readLine();
        }

        Iterator<Long> iterator = forResult.iterator();

        while(iterator.hasNext()){
            result += iterator.next();
        }

        secondObjectToOutputData.write(Long.toString(result));

        objectToInputData.close();
        secondObjectToInputData.close();
        secondObjectToOutputData.close();
        objectToOutputData.close();
    }
}
