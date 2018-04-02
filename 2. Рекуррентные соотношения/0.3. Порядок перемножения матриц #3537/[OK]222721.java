import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Recursion {
public static int Solution(ArrayList<Integer> a, int size) {
int[][] mas = new int[size][size];
int i, j, k, len, cost;
for (i = 0; i < size; i++) {
mas[i][i] = 0;
}
for (len = 2; len < size; len++) {
for (i = 1; i < size - len + 1; i++) {
j = len + i - 1;
if (j == size)
continue;
mas[i][j] = Integer.MAX_VALUE;
for (k = i; k < j; ++k) {
cost = mas[i][k] + mas[k + 1][j] + a.get(i - 1) * a.get(k) * a.get(j);
if (cost < mas[i][j]) {
mas[i][j] = cost;
}
}
}
}
return mas[1][size - 1];
}
public static void main(String[] args) throws IOException {
BufferedReader bufin = new BufferedReader(new FileReader("input.txt"));
BufferedWriter bufout = new BufferedWriter(new FileWriter("output.txt"));
String line = bufin.readLine();
ArrayList<Integer> arr = new ArrayList<Integer>();
line = bufin.readLine();
String[] s = line.split(" ");
arr.add(Integer.parseInt(s[0]));
arr.add(Integer.parseInt(s[1]));
while ((line = bufin.readLine()) != null) {
s = line.split(" ");
arr.add(Integer.parseInt(s[1]));
}
//System.out.println(String.valueOf(Solution(arr, arr.size())));
bufout.write(String.valueOf(Solution(arr, arr.size())));
bufin.close();
bufout.close();
}
}