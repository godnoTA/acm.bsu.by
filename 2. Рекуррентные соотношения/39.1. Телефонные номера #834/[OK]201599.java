import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static class pair {
        int word;
        String str;
        pair(int w, String s) {
            this.word = w;
            this.str = s;
        }
    }

    public static class WordsPair{
        String word;
        String transcription;
        public WordsPair(String w, String tr){
            this.word = w;
            this.transcription = tr;
        }

        @Override
        public boolean equals (Object object) {

            boolean fl =  this.transcription.equals(((WordsPair)object).transcription);
            if (fl)
            {
                if (this.word != null)
                    ((WordsPair) object).word = this.word;
                else
                    this.word = ((WordsPair) object).word;
            }
            return fl;
        }

        @Override
        public int hashCode(){
            return this.transcription.hashCode();
        }
    }

    public static char [] alphabet = new char [91];

    public static Set<WordsPair>[] dictionary = new Set[10];

    public static void search(String num, pair[] table) {
        int ind = num.length() - 1;
        while (ind != -1) {
            int j = num.charAt(ind) - '0';
            if (dictionary[j] != null) {
                for (int i = ind; i < num.length(); i++){
                    if ((i == num.length() - 1 || table[i + 1] != null) && (table[ind] == null || i == num.length() - 1 || table[ind].word > table[i+1].word + 1)){
                        String str = num.substring(ind, i+1);
                        WordsPair wp = new WordsPair(null, str);
                        if (dictionary[j].contains(wp)){
                            pair pa = null;
                            if (i == num.length() - 1)
                                pa = new pair(1, wp.word);
                            else
                                pa = new pair(table[i+1].word + 1, wp.word);
                            table[ind] = pa;
                        }
                    }
                }
            }
            ind--;
        }
    }

    public static void initAlphabet() {
        alphabet['I'] = '1';
        alphabet['J'] = '1';
        alphabet['1'] = '1';
        alphabet['A'] = '2';
        alphabet['B'] = '2';
        alphabet['C'] = '2';
        alphabet['2'] = '2';
        alphabet['D'] = '3';
        alphabet['E'] = '3';
        alphabet['F'] = '3';
        alphabet['3'] = '3';
        alphabet['G'] = '4';
        alphabet['H'] = '4';
        alphabet['4'] = '4';
        alphabet['K'] = '5';
        alphabet['L'] = '5';
        alphabet['5'] = '5';
        alphabet['M'] = '6';
        alphabet['N'] = '6';
        alphabet['6'] = '6';
        alphabet['P'] = '7';
        alphabet['R'] = '7';
        alphabet['S'] = '7';
        alphabet['7'] = '7';
        alphabet['T'] = '8';
        alphabet['U'] = '8';
        alphabet['V'] = '8';
        alphabet['8'] = '8';
        alphabet['W'] = '9';
        alphabet['X'] = '9';
        alphabet['Y']=  '9';
        alphabet['9'] = '9';
        alphabet['O'] = '0';
        alphabet['Q'] = '0';
        alphabet['Z'] = '0';
        alphabet['0'] = '0';
    }

    public static void readFile(BufferedReader reader) throws Exception {
        int N = Integer.parseInt(reader.readLine());
        for (int h = 0; h < N; h++) {
            String currentWord = reader.readLine();
            int j = alphabet[currentWord.charAt(0)] - '0';
            String transcription = "";
            for (int i = 0; i < currentWord.length(); i++)
                transcription += alphabet[currentWord.charAt(i)];
            if (dictionary[j] == null)
                dictionary[j] = new HashSet<>();
            WordsPair wp = new WordsPair(currentWord, transcription);
            if (!dictionary[j].contains(wp))
                dictionary[j].add(wp);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("in.txt"));
        String num = reader.readLine();
        initAlphabet();
        readFile(reader);
        reader.close();
        PrintWriter out = new PrintWriter(new File("out.txt").getAbsoluteFile());

        pair[] table = new pair[num.length()];
        search(num, table);
        if (table[0] == null)
            out.print("No solution");
        else {
            int ind = 0;
            String my = table[0].str;
            out.println(table[0].word);
            while (true) {
                out.print(my);
                ind += my.length();
                if (ind != num.length()) {
                    out.print(" ");
                    my = table[ind].str;
                }
                else
                    break;
            }
        }
        out.close();
    }
}