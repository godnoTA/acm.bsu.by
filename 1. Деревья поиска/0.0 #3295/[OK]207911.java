import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) throws IOException {

        Set<BigInteger> set = new HashSet<>();
        Scanner sc;
        BigInteger sum = new BigInteger( "0" );
        try {
            try (FileWriter writer = new FileWriter( "output.txt" )) {
                sc = new Scanner( new FileReader( "input.txt" ) );
                while (sc.hasNext()) {
                    AtomicReference<StringTokenizer> stringTokenizer = new AtomicReference<>( new StringTokenizer( sc.nextLine(), " " ) );
                    while (stringTokenizer.get().hasMoreElements()) {
                        set.add( BigInteger.valueOf( Long.parseLong( stringTokenizer.get().nextToken() ) ) );
                    }
                }
                Iterator<BigInteger> it = set.iterator();
                while (it.hasNext()) {
                   sum = sum.add(it.next());
                }
                writer.write( String.valueOf( sum ) );
            }

        } catch (FileNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
