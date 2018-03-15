import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class Main {

    public static int returnIndex(Integer [] array, int val, int first, int last)
    {
        while (true)
        {
            int medium = (first+last) >> 1;

            if (array[medium] == null)
                return medium;

            if (array[medium] >= val && (medium - 1 == -1 || array[medium-1] < val))
                return medium;

            if (array[medium] < val)
                first = medium + 1;
            else
                last = medium - 1;
        }
    }

    public static void main(String [] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        String string = reader.readLine();
        int N  = Integer.parseInt(string);

        string = reader.readLine();

        String [] my =  string.split(" ");

        Integer [] array = new Integer [N];
        int lastIndex = 0;

        array[0] = Integer.parseInt(my[0]);
        for (int i = 1; i < my.length; i++)
        {
            int val = Integer.parseInt(my[i]);
            int j = returnIndex(array, val,0 , lastIndex + 1);
            array[j] = val;
            if (j > lastIndex)
                lastIndex = j;
        }

        reader.close();
        PrintWriter out = new PrintWriter(new File("output.txt").getAbsoluteFile());
        out.print(lastIndex + 1);

        out.close();
    }
}