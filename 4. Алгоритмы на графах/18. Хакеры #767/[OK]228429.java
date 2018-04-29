import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static int n;
    public static ArrayList graph[]; //список смежности\
    public static ArrayList reversed_graph[]; //список смежности трансопонированного графа
    public static ArrayList<Integer> lables;
    public static boolean used[];
    public static boolean usedTransp[];
    public static ArrayList<Integer>[] componentArray;
    public static int vertexToComponent[];//какая вершина к какой компоненте относится
    public static ArrayList<Integer> order;
    public static int componentNum;
    public static ArrayList<Integer> minVert;


    public static void DFS(int v) {
        if (used[v])
            return;
        used[v] = true;
        for (int i = 0; i < graph[v].size(); i++) {
            int to = (int) graph[v].get(i);
            if (!used[to]) {
                DFS(to);
            }
        }
        order.add(v);
    }

    public static void DFStoComp(int v) {
        if (usedTransp[v])
            return;
        usedTransp[v] = true;
        vertexToComponent[v] = componentNum;
        for (int i = 0; i < reversed_graph[v].size(); i++) {
            int to = (int) reversed_graph[v].get(i);
            if (!usedTransp[to]) {
                DFStoComp(to);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (java.io.FileNotFoundException ex) {
            System.out.println("Файл не найден!");
            return;
        }
        n = scanner.nextInt();

        graph = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList();
        }

        reversed_graph = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            reversed_graph[i] = new ArrayList();
        }

        lables = new ArrayList(n);
        for (int i = 0; i < n; i++) {
            lables.add(scanner.nextInt());
        }

        int u = scanner.nextInt();
        int v = scanner.nextInt();
        while (u != 0 && v != 0) {
            u--;
            v--;
            graph[u].add(v);
            reversed_graph[v].add(u);
            u = scanner.nextInt();
            v = scanner.nextInt();
        }

        used = new boolean[n];
        usedTransp = new boolean[n];
        vertexToComponent = new int[n];
        Arrays.fill(used, false);
        Arrays.fill(usedTransp, false);
        Arrays.fill(vertexToComponent, -1);

        order = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            if (!used[i])
                DFS(i);
        }
        Collections.reverse(order);

        componentNum = 0;
        for (int i = 0; i < order.size(); i++) {
            int vert = order.get(i);
            if (vertexToComponent[vert] == -1) {
                DFStoComp(vert);
                componentNum++;
            }
        }
        int kolComp = vertexToComponent[0];
        for (int i = 0; i < vertexToComponent.length; i++) {
            if (vertexToComponent[i] > kolComp)
                kolComp = vertexToComponent[i];
        }
        kolComp++;
        componentArray = new ArrayList[kolComp];
        for (int i = 0; i < kolComp; ++i) {
            componentArray[i] = new ArrayList();
        }

        for (int i = 0; i < vertexToComponent.length; i++) {
            int k = vertexToComponent[i];
            componentArray[k].add(i);
        }

        minVert = new ArrayList<>(kolComp);
        for (int i = 0; i < kolComp; i++) {
            int minCompVert = componentArray[i].get(0);
            int minComplabel = lables.get(minCompVert);
            int needVert=minCompVert;
            for (int j = 0; j < componentArray[i].size(); j++) {
                int stepLabel = componentArray[i].get(j);
                if (minComplabel > lables.get(stepLabel)) {
                    needVert = stepLabel;
                    minComplabel = lables.get(stepLabel);
                }
            }
            minVert.add(needVert);
           /* for (int s = 0; s < n; s++) {
                if (lables.get(s) == minComplabel) {
                    minVert.add(s);
                    break;
                }
            }*/
        }
        order.clear();
        ArrayList<Integer> finalVect = new ArrayList<>();
        Arrays.fill(used, false);
        for (int i = 0; i < minVert.size(); i++) {
            int vert = minVert.get(i);
            if (!used[vert]) {
                finalVect.add(vert);
                DFS(vert);
            }
        }

        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            String text1 = String.valueOf(finalVect.size());
            fileWriter.write(text1+'\n');
            for (int i = 0; i < finalVect.size(); i++) {
                String text = String.valueOf(finalVect.get(i)+1);
                fileWriter.write(text);
                fileWriter.append(" ");
            }
            fileWriter.flush();
        } catch (Exception ex) {
        }
    }
    }

