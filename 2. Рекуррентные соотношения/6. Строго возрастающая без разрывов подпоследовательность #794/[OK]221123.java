import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static class MySearch {

        public int search(List<Integer> list, int x) {     
            int i = -1;
            int low = 0, high = list.size(), mid;
            while (low < high) {
                mid = (low + high) / 2;
                if (x == list.get(mid)) {
                    i = mid;
                    break;
                } else if ((x < list.get(mid)) && (x > list.get(mid - 1))) {
                    i = mid;
                    break;
                } else {
                    if (x < list.get(mid)) {
                        high = mid;
                    } else {
                        low = mid + 1;
                    }
                }
            }
            return i;
        }
    }

    public static void main(String[] args) throws Exception {
        List<Integer> newList = new ArrayList<>();
        Scanner sc = new Scanner(new File("input.txt"));
        int length = sc.nextInt();
        int[] arr = new int[length];
        int j = 0;
        PrintStream ps = new PrintStream("output.txt");
        while (sc.hasNextInt()) {
            arr[j] = sc.nextInt();
            j++;
        }
        newList.add(arr[0]);
        MySearch obj = new MySearch();
        for (int i = 1; i < length; i++) {
            if (arr[i] > newList.get(newList.size() - 1)) {
                newList.add(arr[i]);
            } else if (arr[i] < newList.get(newList.size() - 1)) {
                if (arr[i] < newList.get(0)) {
                    newList.set(0, arr[i]);
                } else {
                    newList.set(obj.search(newList, arr[i]), arr[i]);
                }
            }
        }
        ps.println(newList.size());
        ps.close();
    }
}