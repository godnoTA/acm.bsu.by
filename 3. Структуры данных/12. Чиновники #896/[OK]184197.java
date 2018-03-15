import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Main {


    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("in.txt"));
            int N = sc.nextInt();
            ArrayList<Long>[] listArr = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                listArr[i] = new ArrayList<>();
            }


            int I;
            int J;
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            long[] size = new long[N];
            long[] order = new long[N];
            long[] parrentChilds = new long[N];
            long ind;
            for (int i = 0; i < N; i++) {
                I = sc.nextInt();
                J = sc.nextInt();
                size[I - 1] = J;
                if (J == 0) {
                    queue.addLast(I - 1);
                }
                while (J > 0) {
                    ind = sc.nextLong();
                    parrentChilds[(int) ind - 1] = I - 1;
                    listArr[I - 1].add(ind);
                    listArr[I - 1].add(sc.nextLong());
                    J--;
                }
            }

            long curNode = 0;
            int idx = 0;
            while (!queue.isEmpty()) {
                curNode = queue.pop();
                order[idx] = curNode;
                idx++;
                size[(int) parrentChilds[(int) curNode]]--;
                if (size[(int) parrentChilds[(int) curNode]] == 0) {
                    queue.addLast((int) parrentChilds[(int) curNode]);
                }
            }


            long[] arrCost = new long[N];
            Arrays.fill(arrCost, 0);

            int ordI = 0;
            int i = 0;
            long index;
            long bribe;
            long minIndex = 0;
            long minBribe = 0;

            while (ordI < N) {
                i = (int) order[ordI];
                if (!listArr[i].isEmpty()) {
                    Iterator<Long> it = listArr[i].iterator();
                    minIndex = it.next();
                    arrCost[(int) minIndex - 1] += it.next();
                    minBribe = arrCost[(int) minIndex - 1];
                    while (it.hasNext()) {
                        index = it.next();
                        bribe = it.next();
                        arrCost[(int) index - 1] += bribe;
                        if (arrCost[(int) index - 1] <= minBribe) {
                            minBribe = bribe;
                            minIndex = index;
                        }
                    }
                    arrCost[i] += arrCost[(int) minIndex - 1];
                }
                ordI++;
            }

            List<Integer> result = new ArrayList<>();
            result.add(1);

            int curIndex = 0;
            boolean key = true;
            while (key) {
                Iterator<Long> it = listArr[curIndex].iterator();
                key = false;
                while (it.hasNext()) {
                    index = it.next();
                    bribe = it.next();
                    if (arrCost[(int) index - 1] == arrCost[curIndex]) {
                        result.add((int) index);
                        arrCost[(int) index - 1] -= bribe;
                        curIndex = (int) index - 1;
                        key = true;
                    }
                }
            }
            sc.close();
            FileWriter fw = new FileWriter("out.txt");
            fw.write(new Long(arrCost[0]).toString());
            fw.write("\n");

            i = result.size();
            for (Integer item : result) {
                fw.write(item.toString());
                if (i != 1) {
                    fw.write(" ");
                }
                i--;
            }


            fw.close();
        } catch (
                IOException e)

        {
            System.out.println("jgjgjh");
        }
    }
}
