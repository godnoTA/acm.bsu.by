import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;

public class Main {
    public static double minOfTwoElements(double a , double b){
        return a>b?b:a;
    }

    public static  double arrayFilling(int index,ArrayList<Double>a){
        double [] b = new double[index];
        double [] c = new double[index+1];
        b[0] = 0; b[1] = a.get(1) - a.get(0);
        c[0] = 0; c[1] = a.get(1) - a.get(0); c[2] = b[1];
        for (int i = 2; i < index ; i++) {
            b[i] = minOfTwoElements(b[i-1]+a.get(i) - a.get(i-1),c[i-1]+a.get(i) - a.get(i-1));
            c[i+1] = b[i];
        }
        return b[index-1];
    }

    public static void main (String[]args) throws IOException {
        long time1 = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("in.txt")))) {
            String line;
            Formatter f = new Formatter();
            int numberOfNails = Integer.parseInt(reader.readLine());
            ArrayList<Double> nails = new ArrayList<>(numberOfNails);
            while ((line = reader.readLine()) != null) {
                nails.add(Double.parseDouble(line));
            }
            DecimalFormat df = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.US));
            double finalLength = arrayFilling(numberOfNails,nails);
            FileWriter writer = new FileWriter("out.txt");
            writer.write(df.format(finalLength));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2-time1);
    }

}