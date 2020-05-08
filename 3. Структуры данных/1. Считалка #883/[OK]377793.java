import java.io.*;
import java.util.ArrayList;

//1) input, создали arraylist с элементами от 0 до n
//2) пока не останется одино число в массиве, удаляем то что с номером index(изначально m), и уменьшаем index на количество людей пока он не станет от 0 до количества людей(по модулю числа людей берем его).
//3) поскольку n и m у нас одни и те же, ответом на вторую часть будет разница между ответом на первую и данным k(взятое по модулю n)
//Ассимптотика - O(n), т.к. у нас один только цикл которым мы по одному удаляем все элементы массива

public class Main {
    static ArrayList<Integer> people = new ArrayList<>();
    static int n, m, k;
    public static void main(String[] args) throws IOException {
        input();
        int last = task1(people, m);
        int first = k - last +1;
        if (first < 1)
            first += n;
        output(last, first);
    }

    static int task1(ArrayList<Integer> people, int m){
        int index = m;
        while(index>people.size()-1)
            index-=people.size()-1;
        while(people.size()-1>1){
            people.remove(index);
            index+=m-1;
            if(index>people.size()-1)
                index-=(people.size()-1)*(index/(people.size()-1));
            if(index <= 0)
                index+=people.size()-1;
        }
        return people.get(1);
    }

    static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));
        String[] line = reader.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        k = Integer.parseInt(line[2]);
        for(int i=0;i<n+1;i++)
            people.add(i);
    }

    static void output(int a, int b) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("out.txt")));
        writer.write(a+"\n"+b);
        writer.close();
    }
}
