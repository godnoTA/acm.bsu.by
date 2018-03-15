import java.io.*;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;


public class M0
{
    public static void main(String[] args)
            throws IOException {

        BufferedReader br =
                new BufferedReader(
                        new FileReader("input.txt"));

        try
        {
            List<Integer> list;
            list = new ArrayList<>();

            String k = "";
            long summa= 0;
            int temp;
            k = br.readLine();
            StringTokenizer st;

            while(k!=null){
                st= new StringTokenizer(k," ");
                while(st.hasMoreTokens()) {
                    temp = Integer.parseInt(st.nextToken());
                        if(!(list.contains(temp))){
                            list.add(temp);
                         }
                }
                k = br.readLine();
            }

            for(int i=0;i<list.size();i++)
                summa+=list.get(i);

       PrintWriter pw = new PrintWriter("output.txt");
            pw.println(summa);
            pw.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (NumberFormatException e) //введено НЕ число, преобразовать к типу int нельзя
        {
            System.out.println("Не целое число");
        }
        catch(NullPointerException e) {

        }
        catch(IndexOutOfBoundsException e){

        }
    }
}


