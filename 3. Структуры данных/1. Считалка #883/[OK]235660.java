import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Schitalka {

	static int getLastRemoved(int n, int m) {
		List<Integer> persons = new ArrayList<>();
		for (int i = 1, count = 0; i <= n; i++, count++) 
			persons.add(count, i);
		List<Integer> orderOfDeleting = new ArrayList<>();
		int index = 0;
		while (persons.size() != 0) {
			int next = (m + index - 1) % persons.size();
			orderOfDeleting.add(persons.get(next));
			persons.remove(next);
			index = next;
		}
		return orderOfDeleting.get(orderOfDeleting.size() - 1);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n, m, k;
		int lastNumber = 0, startNumber = 0;
		try (Scanner sc = new Scanner(new File("in.txt"))) {
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			lastNumber = getLastRemoved(n, m);
			startNumber = 1 - lastNumber + k;
			if (startNumber < 1)
				startNumber += n;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (FileWriter out = new FileWriter(new File("out.txt"))) {
			out.write(lastNumber + "\r\n");
			out.write(startNumber + "\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
