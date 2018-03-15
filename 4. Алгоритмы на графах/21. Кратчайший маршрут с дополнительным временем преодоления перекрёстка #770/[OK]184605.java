import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int min(Long[] path, boolean[] visit) {
        Long priceWay = Long.MAX_VALUE;
        int position = 0;
        for (int i = 1; i < path.length; i++) { // 0 -> 1
            if (!visit[i] && path[i] <= priceWay) {
                priceWay = path[i];
                position = i;
            }
        }
        return position;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        PrintWriter pr = new PrintWriter(new File("output.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Top tops[] = new Top[N + 1];
        for(int i = 1;i <= N;i++){
            tops[i] = new Top();
        }
        int weight;
        int n;
        int m;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            tops[n].addTop(m, weight);
            tops[m].addTop(n, weight);
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int finish = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        Long[] path = new Long[N+1];
        boolean[] visit = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            path[i] = Long.MAX_VALUE;
        }
        path[start] = (long) q * tops[start].numberRoad.size();
        for (int i = 0; i < N; i++) {
            visit[start] = true;
            for (int j = 0; j < tops[start].numberRoad.size(); j++) {
                m = tops[start].numberRoad.get(j);
                if (!visit[m] && path[m] > (path[start] + tops[start].weight.get(j) + q * tops[m].numberRoad.size())) {
                    path[m] = path[start] + tops[start].weight.get(j) + q * tops[m].numberRoad.size();
                }
            }
            start = min(path, visit);
        }
        long answer = path[finish] - q * tops[finish].numberRoad.size();
        if (path[finish] != Long.MAX_VALUE) {
            pr.println("Yes");
            pr.print(answer);
        } else {
            pr.print("No");
        }
        pr.close();
    }
}

class Top {
    public List<Integer> numberRoad;
    public List<Integer> weight;

    public Top() {
        numberRoad = new ArrayList<>();
        weight = new ArrayList<>();
    }

    public void addTop(int point, int tempWeight) {
        numberRoad.add(point);
        weight.add(tempWeight);
    }
}