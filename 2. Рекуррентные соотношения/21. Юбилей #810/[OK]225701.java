import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        Scanner sc = new Scanner(new File("input.txt"));
        FileWriter fw = new FileWriter("output.txt");

        int people = sc.nextInt();
        int offers_amount = sc.nextInt();



        Set<String> stringSet = new HashSet<>();
        for (int i =0; i<offers_amount+1; i++)
            stringSet.add(sc.nextLine());




        int[][] offers = new int[offers_amount][4];

        ArrayList<String> list = new ArrayList<String>();
        list.addAll(stringSet);

        list.remove(0);

        offers_amount = list.size();

        for (int i = 0; i < list.size(); i++) {
            StringTokenizer st = new StringTokenizer(list.get(i), " ");
            for (int j = 0; j < 4; j++) {
                offers[i][j] = Integer.parseInt(st.nextToken());
            }
        }



        int[] total_needs = new int[3];
        for (int i = 0; i < 3; i++) {
            total_needs[i] = sc.nextInt();
            System.out.println(total_needs[i]);
        }

        SetFBCS[][][] set = new SetFBCS[10][10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    set[i][j][k] = new SetFBCS();
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if ((i == 0) && (j == 0) && (k == 0)) {
                        set[0][0][0].init();
                        k++;
                    }
                    int price = -1;
                    int fanta = -1;
                    int bananas = -1;
                    int cakes = -1;
                    if (i > 0) {
                        if (set[i - 1][j][k].f >= i) {
                            price = set[i - 1][j][k].pr;
                            fanta = set[i - 1][j][k].f;
                            bananas = set[i - 1][j][k].b;
                            cakes = set[i - 1][j][k].c;
                        }
                    }
                    if (j > 0) {
                        if (set[i][j - 1][k].b >= j) {
                            if (price == -1 || price > set[i][j - 1][k].pr) {
                                price = set[i][j - 1][k].pr;
                                fanta = set[i][j - 1][k].f;
                                bananas = set[i][j - 1][k].b;
                                cakes = set[i][j - 1][k].c;
                            }
                        }
                    }
                    if (k > 0) {
                        if (set[i][j][k - 1].c >= k) {
                            if (price == -1 || price > set[i][j][k - 1].pr) {
                                price = set[i][j][k - 1].pr;
                                fanta = set[i][j][k - 1].f;
                                bananas = set[i][j][k - 1].b;
                                cakes = set[i][j][k - 1].c;
                            }
                        }
                    }
                    for (int g = 0; g < offers_amount; g++) {
                        int[] rems = new int[3];
                        rems[0] = i;
                        rems[1] = j;
                        rems[2] = k;

                        int tempSum = offers[g][3];

                        rems[0] = rems[0] - offers[g][0];
                        rems[1] = rems[1] - offers[g][1];
                        rems[2] = rems[2] - offers[g][2];
                        for (int q = 0; q < 3; q++) {
                            if (rems[q] < 0)
                                rems[q] = 0;
                        }

                        if (!((rems[0] == i) && (rems[1] == j) && (rems[2] == k))) {
                            tempSum = tempSum + set[rems[0]][rems[1]][rems[2]].pr;
                            if (price == -1) {
                                price = tempSum;
                                fanta = offers[g][0] + set[rems[0]][rems[1]][rems[2]].f;
                                bananas = offers[g][1] + set[rems[0]][rems[1]][rems[2]].b;
                                cakes = offers[g][2] + set[rems[0]][rems[1]][rems[2]].c;
                            }
                            if (price > tempSum) {
                                price = tempSum;
                                fanta = offers[g][0] + set[rems[0]][rems[1]][rems[2]].f;
                                bananas = offers[g][1] + set[rems[0]][rems[1]][rems[2]].b;
                                cakes = offers[g][2] + set[rems[0]][rems[1]][rems[2]].c;
                            }
                        }
                    }
                    set[i][j][k].pr = price;
                    set[i][j][k].f = fanta;
                    set[i][j][k].c = cakes;
                    set[i][j][k].b = bananas;
                    if (price == -1) {
                        set[i][j][k].f = -1;
                        set[i][j][k].c = -1;
                        set[i][j][k].b = -1;
                    }
                }
            }
        }


        int result = (set[total_needs[0]][total_needs[1]][total_needs[2]].pr) / people;
        if (result * people < set[total_needs[0]][total_needs[1]][total_needs[2]].pr)
            result++;

        fw.append(String.valueOf(result));
        fw.close();








    }

    public static boolean equals(final String s1, final String s2) {
        return s1 != null && s2 != null && s1.hashCode() == s2.hashCode()
                && s1.equals(s2);
    }

}

class SetFBCS {
    int f;
    int b;
    int c;
    int pr;

    public void init() {
        this.f = 0;
        this.c = 0;
        this.b = 0;
        this.pr = 0;
    }
}