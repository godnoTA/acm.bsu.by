import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Main {


    public static void main(String[] args) throws Exception{

        File fin = new File("littera.in");
        Scanner sc = new Scanner(fin);
        int size = sc.nextInt();
        StringBuilder allTextB = new StringBuilder();
        while(sc.hasNext()) allTextB.append(sc.next());
        System.out.println(allTextB);//////////////////////////////////

        char inSym, outSym, midSym; int midSymNum;
        String inputStr = allTextB.substring(0, allTextB.length() / 2);
        String outputStr = allTextB.substring(allTextB.length() / 2, allTextB.length());
        StringBuilder middleStr = new StringBuilder();

        for (int i = 0; i < inputStr.length(); ++i)
        {
            inSym = inputStr.charAt(i);
            outSym = outputStr.charAt(i);
            if (Character.isAlphabetic(inSym))
            {
                midSymNum = outSym - inSym;
                if (midSymNum <= 0) midSymNum += 26;
                midSym = (char)(96 + midSymNum);
                middleStr.append(midSym);
            }
        }

        System.out.println(middleStr);//////////////////////////////////////
        char[] chars = new char[middleStr.length()];
        for (int i = 0; i < chars.length; ++i) chars[i] = middleStr.charAt(i);
        int wordLength = begPosition(middleStr.toString());
        int q = 0;
        if (wordLength < 0) q = count(chars);//////////////////////////////////////////////////////
        System.out.println(Arrays.toString(chars));////////////////////////////

        StringBuilder twiceword = new StringBuilder();
        for (int i = 0; i < wordLength; ++i) twiceword.append(middleStr.charAt(i));
        twiceword.append(twiceword);
        System.out.println(twiceword);////////////////////////////////////////

        File fout = new File("littera.out");
        FileWriter fileWriter = new FileWriter(fout);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String s = twiceword.toString().substring(0, wordLength);
        System.out.println(s);///////////////////////////////////////////////////////////
        System.out.println(necessaryWord(twiceword.toString()).substring(0, wordLength));////////////////////////////////////////////
        bufferedWriter.write(necessaryWord(twiceword.toString()).substring(0, wordLength));
        bufferedWriter.flush();
        bufferedWriter.close();


    }


    public static int begPosition(String str)
    {
        int len = str.length();
        int idx;
        int[] array = new int[len];
        array[0] = 0;

        for (int i = 1; i < len; ++i){
            idx = array[i - 1];
            while(idx > 0 && (str.charAt(i) != str.charAt(idx))) idx = array[idx - 1];
            if (str.charAt(i) == str.charAt(idx)) ++idx;
            array[i] = idx;
        }
        return  len - array[array.length - 1];
    }


    public static String necessaryWord(String str)
    {
        str += str;
        int len = str.length();
        int idx = 0; int res = 0;
        int pos1, pos2;
        while (idx < len / 2)
        {
            res = idx;
            pos1 = idx + 1; pos2 = idx;
            while (pos1 < len && str.charAt(pos2) <= str.charAt(pos1)){
                if (str.charAt(pos2) < str.charAt(pos1)) pos2 = idx;
                else ++pos2;
                ++pos1;
            }
            while (pos2 >= idx) idx += pos1 - pos2;
        }
        return str.substring(res, len / 2);
    }


      private static double getHs(double[] hs, int l, int r){
        double result = hs[r];
        if (l > 0) result -= hs[l - 1];
        return result;

    }

    public static void arrayOut(char[] str)
    {
        System.out.print("String is ");
        for (int i = 0; i < str.length; ++i) System.out.print(str[i] + " ");
        System.out.println();
    }


    public static int count(char[] str)
    {
        int k2 = 0;
        for (int i = 1; i < str.length; ++i)
        {
            if (k2 == 0) { if (str[0] == str[i]) k2 = i; }
            else{
                if (str[0] == str[k2])
                {
                    if (str[i] != str[i - k2]) k2 = i;
                    if (k2 == str.length - 1) k2++;
                }
                else k2++;
            }
        }
        return k2;
    }

}
