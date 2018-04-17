import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new File("in.txt"));

        int people = sc.nextInt();
        int jump = sc.nextInt();
        int last_man = sc.nextInt();

        sc.close();

        ArrayList<Integer> peopleList = new ArrayList<>();

        for (int i = 0; i < people; i++)
            peopleList.add(i + 1);

        //System.out.println(peopleListback);

        int stPoint = 0;

        while (peopleList.size() != 1) {
            int b = (stPoint + jump - 1) % (peopleList.size());
            peopleList.remove(b);
            stPoint = b;
        }

        //System.out.println(peopleList);
        FileWriter fw = new FileWriter("out.txt");
        fw.append(peopleList.get(0).toString()).append("\n");
        int startPoint = 0;


        int a = (last_man + people - peopleList.get(0) + 1) % people;
        if (a == 0)
            a = 1;
        fw.append(String.valueOf(a));

//        ArrayList<Integer> listM = new ArrayList<>();
//        for (int j = 0; j < people; j++)
//            listM.add(j + 1);
//        ArrayList<Integer> list = new ArrayList<>();
//
//        for (int i = 0; i < people; i++) {
//
//            list = (ArrayList<Integer>) listM.clone();
//
//            System.out.println(list);
//            startPoint = i;
//
//            while (list.size() != 1) {
//                int a = ((startPoint + jump - 1) % (list.size()));
//                list.remove(a);
//                startPoint = (a);
//                System.out.println(list);
//            }
//
//
//
//            //System.out.println(list.get(0));
//            if (list.get(0) == last_man) {
//                fw.append(String.valueOf(i + 1));
//                fw.close();
//                break;
//            }
//        }

        fw.close();
    }
}
