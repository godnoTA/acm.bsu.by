import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private static final int WHITE = 0, BLACK = 1;

    public static void main(String[] argv) {

        try {
            int SIZE;
            Scanner sc = new Scanner(new FileInputStream("in.txt"));
            SIZE = sc.nextInt();
            System.setOut(new PrintStream("out.txt"));

            LinkedList<Integer> deck1 = new LinkedList<>(), deck2 = new LinkedList<>();

            for (int i = 0; i < SIZE; i++)
                deck2.addLast(i % 2);

            for (int i = 0; !deck2.isEmpty(); i++) {
                if (i % 2 == 0) {
                    deck1.addLast(deck2.peekLast());
                    deck2.pollLast();
                } else {
                    deck1.addLast(deck1.peekFirst());
                    deck1.pollFirst();
                }
            }

            System.out.print(deck1.pollLast());
            while (!deck1.isEmpty())
                System.out.print(" " + deck1.pollLast());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}