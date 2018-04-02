import java.io.*;

public class Heap {

public static boolean checkHeap(int[] a, int n) {

// for (int i = 0; i < (n - 2) / 2; i++) {
// if (a[i] > a[2 * i + 1] || a[i] > a[2 * i + 2])
// return false;
// }
// if (n % 2 == 0) {
// if (a[n / 2 - 1] > a[n - 1])
// return false;
// }
// if (a == null) {
// return false;
// }
for (int i = 0; i < a.length; i++) {

if ((2 * i + 1 < a.length && a[i] > a[2 * i + 1]) || (2 * i + 2 < a.length && a[i] > a[2*i + 2])){
return false;
}
}
return true;
}

public static void main(String[] args) {
try {
BufferedReader bufin = new BufferedReader(new FileReader("input.txt"));
BufferedWriter bufout = new BufferedWriter(new FileWriter("output.txt"));
String line = bufin.readLine();
int n = Integer.parseInt(line);

int[] mas = new int[n];

String[] tok = bufin.readLine().split(" ");
for (int i = 0; i < tok.length; i++) {
mas[i] = Integer.parseInt(tok[i]);
// System.out.println(mas[i]);
}
// System.out.println(mas.length);
if (checkHeap(mas, n)) {
// System.out.println("Yes");
bufout.write("Yes");
} else
bufout.write("No");

bufin.close();
bufout.close();

} catch (IOException e) {
e.printStackTrace();
}

}
}