import java.io.*;
import java.util.*;

public class Task {
    static int K;
    static Worker[] heap;
    static Vector<Work> works;
    static int N;
    static int size = 0;
    static int j, left, right;
    static int answer[];
    static int record = 0;

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter(new File("output.txt"));
        K = in.nextInt();
        works = new Vector<>();
        int numb = 0;
        while (in.hasNext()) {
            works.add(new Work(numb, in.nextInt()));
            numb++;
        }
        N = works.size();
        answer = new int[N];

        heap = new Worker[K];
        for(int j = 0; j<K; j++)
        {
            heap[j] = new Worker(j);
        }

        int counter = 0;
        Collections.sort(works, new WorkComparator());

        for(int i = 0; i<K; i++)
        {
            insert(works.get(counter).value);
            answer[counter] = counter+1;
            counter++;
        }
        while(counter<N)
        {
            heap[0].volume += works.get(counter).value;
            answer[counter] = heap[0].count+1;
            invariant(0);
            counter++;
        }
        for(Worker w:heap)
        {
            if(w.volume>record)
            {
                record = w.volume;
            }
        }

        out.println(record);
        for(int a: answer)
        {
            out.print(a+ " ");
        }
        out.close();
    }
    static void insert(int value)
    {
        size++;
        heap[size-1].volume = value;
        int i = size - 1;
        int parent = (i - 1) / 2;
        Worker temp;

        while (i > 0 && heap[parent].volume > heap[i].volume)
        {
            temp = heap[i];
            heap[i] = heap[parent];
            heap[parent] = temp;

            i = parent;
            parent = (i - 1) / 2;
        }
    }
    static void invariant(int index)
    {
        int left;
        int right;
        int change;
        Worker temp;
        while (true)
        {
            change = index;
            left = 2*index + 1;
            right = 2*index + 2;
            if((left < size)&&(heap[left].volume < heap[change].volume))
            {
                change = left;
            }
            if((right < size)&&(heap[right].volume < heap[change].volume))
            {
                change = right;
            }
            if(change == index)
                break;
            temp = heap[index];
            heap[index] = heap[change];
            heap[change] = temp;
            index = change;
        }
    }

    static class Worker
    {
        int count;
        int volume;

        public Worker(int count)
        {
            this.count = count;
        }
    }
    static class Work {
        int value;
        int number;

        public Work(int number, int value) {
            this.value = value;
            this.number = number;
        }
    }
    static class WorkComparator implements Comparator<Work>
    {
        @Override
        public int compare(Work o1, Work o2) {
            return (o2.value-o1.value);
        }
    }
}