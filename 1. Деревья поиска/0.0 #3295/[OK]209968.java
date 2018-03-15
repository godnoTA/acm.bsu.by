import java.io.*;
import java.util.*;



public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        FileWriter writer = new FileWriter("output.txt");

        List<Integer> list = new ArrayList<Integer>();
        while (in.hasNextLine())
            list.add(Integer.parseInt(in.nextLine()));
      
      
       Set<Integer> SetList = new HashSet<>();
       SetList.addAll(list);
       list.clear();
       list.addAll(SetList);
       long sum=0;
       for (int i=0; i<list.size(); i++)
       {
           sum+=list.get(i);
       }
        System.out.println (sum);
        String finaly = Long.toString(sum);
       writer.write (finaly);


        writer.close();
        in.close();
    }

}
