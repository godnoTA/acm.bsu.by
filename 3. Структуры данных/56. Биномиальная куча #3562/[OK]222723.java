import java.io.*;

public class Heap {

public static void main(String[] args) throws IOException {
BufferedReader bufin = new BufferedReader(new FileReader("input.txt"));
BufferedWriter bufout = new BufferedWriter(new FileWriter("output.txt"));
String line = bufin.readLine();
String p = Long.toBinaryString(Long.parseLong(line));
// String p = Integer.toBinaryString(13);
// System.out.println(p);

for (int i = p.length() - 1; i >= 0; i--) {
if (p.charAt(i) == '1') {
int x = p.length() - 1 - i;
// System.out.println(x);
bufout.write(String.valueOf(x));
bufout.newLine();
}
}

bufin.close();
bufout.close();
}
}