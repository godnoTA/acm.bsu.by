import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

//Вокруг считающего стоят n человек, из которых выделен первый, а остальные занумерованы по часовой стрелке числами от 2 до n.
//        Считающий, начиная с некоторого человека, ведёт счёт до m. Человек, на котором остановился счёт, выходит из круга.
//        Счёт продолжается со следующего человека аналогичным образом до тех пор, пока не останется один человек.
//Необходимо определить:
//        1)номер оставшегося человека, если известно m и то, что счёт начинался с первого человека;
//        2)номер человека c которого начинался счёт, если известны m и номер k оставшегося человека.
//        Формат входного файла:
//        В единственной строке записано три целых числа: n (1 ≤ n ≤ 10 000), m (1 ≤ m ≤ 10^9) и k (1 ≤ k ≤ n).
//        Формат выходного файла:
//        В первой строке выведите номер оставшегося человека, во второй — номер человека, с которого должен был начаться счёт.

public class Main {
    public static void task1(List<Integer> a, int counter) {
        int deleteIndex = counter-1;
        while (a.size() != 1) {
            while (deleteIndex >= a.size()){
                int fast = deleteIndex/a.size();
                if(fast == 0){fast = 1;}
                deleteIndex-=a.size()*fast;
            }
            a.remove(deleteIndex);
            deleteIndex+=counter-1;
        }
    }
    public static int task2(List<Integer> a, int last,int number){
        int res = last - a.get(0)+1;
        if(res < 0){
            res = number + res;
        }
        if (res > number) {
            res=res-number;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("in.txt")));
        String line;
        line = reader.readLine();
        String[] dataLine = line.split("[ ]");
        int numberOfPeople = Integer.parseInt(dataLine[0]);
        int counter = Integer.parseInt(dataLine[1]);
        int remainingPerson = Integer.parseInt(dataLine[2]);
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < numberOfPeople; i++) {
            list.add(i + 1);
        }
        task1(list,counter);
        int res = task2(list,remainingPerson,numberOfPeople);
        FileWriter writer = new FileWriter("out.txt");
        writer.write(list.get(0)+"\n");
        writer.write(res+"");
        writer.close();
    }
}

