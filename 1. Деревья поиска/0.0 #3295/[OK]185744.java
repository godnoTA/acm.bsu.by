
import java.math.BigInteger;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BigInteger my = BigInteger.valueOf(0);
        Set<String> mySet = new HashSet<String>();
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            if (!(mySet.contains(line)))
                my = my.add(BigInteger.valueOf(Long.parseLong(line)));
            mySet.add(line);
        }
        reader.close();
        File file = new File("output.txt");
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        out.print(my.toString());
        out.close();
    }
}
