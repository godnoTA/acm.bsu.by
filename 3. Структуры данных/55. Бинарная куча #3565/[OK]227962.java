import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Heap {
    static boolean heap( int[] a ){
        for( int i = 0; i < a.length; i++ ) {
            int index = 2 * i + 1;
            if (index >= 0 && index < a.length && a[index] < a[i]) {
                return false;
            }
            index = 2 * i + 2;
            if (index >= 0 && index < a.length && a[index] < a[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main( String[] args ) {
        try{
            File input = new File( "input.txt" );
            Scanner sc = new Scanner( input );
            int s = sc.nextInt();
            int[] a = new int[s];
            for( int i = 0; i < s; i++ )
                a[i] = sc.nextInt();
            FileWriter output = new FileWriter( "output.txt" );
            if( heap(a) )
                output.write( "Yes" );
            else output.write( "No" );
            sc.close();
            output.flush();
            output.close();
        }
        catch( Exception e ){
            System.out.println( e );
            System.exit( 1 );
        }
    }
}
