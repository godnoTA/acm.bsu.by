import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

public class Main {
    public static void main(String[] args) {
        try {
            int count = 0;
            boolean end = false;
            Scanner sc = new Scanner(new File("input.in"));
            FileWriter writer = new FileWriter("output.out");

            int n = sc.nextInt();
            int m = sc.nextInt();

            int arrayOfBackArcVal[][] = new int[n][n];
            int arrayOfArcVal[][] = new int[n][n];

            int path[] = new int[n];
            ArrayList<Node> nodeList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                path[i] = -1;
                nodeList.add(new Node(i));
            }
            ArrayList<ArrayList<Node>> graph = new ArrayList<>();
            Queue<Node> mainQueue = new LinkedTransferQueue<>();
            for (int i = 0; i < n; i++) {
                ArrayList<Node> tempList = new ArrayList<>();
                int index;
                while ((index = sc.nextInt()) != 0) {
                    Node tempNode = nodeList.get(index - 1);
                    tempList.add(tempNode);
                }
                graph.add(tempList);
                for (int j = 0; j < n; j++) {
                    Node tempNode = nodeList.get((j));
                    if (graph.get(i).contains(tempNode)) {
                        arrayOfArcVal[i][j] = 1;
                    }
                }
            }
            Node startNode = nodeList.get(sc.nextInt() - 1);
            Node endNode = nodeList.get(sc.nextInt() - 1);

            while (!end) {
                mainQueue.add(startNode);
                Node nextNode = startNode;
                while (!mainQueue.isEmpty() && !nextNode.equals(endNode)) {
                    nextNode = mainQueue.remove();

                    nextNode.visit = true;
                    if (path[nextNode.number] != -1) {
                        nextNode.width = nodeList.get(path[nextNode.number]).width + 1;
                    } else {
                        nextNode.width = 0;
                    }
                    ArrayList<Node> listForPush = graph.get(nextNode.getNumber());
                    for (int i = 0; i < listForPush.size(); i++) {
                        Node k = listForPush.get(i);
                        if (!k.isVisit() && !mainQueue.contains(k) &&
                                (arrayOfArcVal[nextNode.getNumber()][k.getNumber()] > 0
                                        || arrayOfBackArcVal[nextNode.getNumber()][k.getNumber()] > 0)) {
                            path[k.getNumber()] = nextNode.getNumber();
                            mainQueue.add(k);
                        }
                    }
                }
                if (endNode.isVisit()) {
                    count++;
                    arrayOfBackArcVal[endNode.getNumber()][path[endNode.getNumber()]]++;
                    arrayOfArcVal[path[endNode.getNumber()]][endNode.getNumber()]--;
                    int backToStart = path[endNode.getNumber()];
                    while (path[backToStart] != -1) {
                        arrayOfArcVal[backToStart][path[backToStart]]++;
                        arrayOfArcVal[path[backToStart]][backToStart]--;
                        backToStart = path[backToStart];
                    }
                    for (int i = 0; i < n; i++) {
                        path[i] = -1;
                        Node node = nodeList.get(i);
                        node.visit = false;
                        node.width = 0;
                        mainQueue.clear();
                    }
                } else {
                    end = true;
                }
            }

            writer.write(Integer.toString(count));
            writer.close();

        } catch (
                IOException e
                )

        {
            e.printStackTrace();
        }
    }

    private static class Node {
        private int number;
        private int width;
        private boolean visit;

        public Node(int number) {
            this.number = number;
            this.width = 0;
            this.visit = false;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public boolean isVisit() {
            return visit;
        }

        public void setVisit(boolean visit) {
            this.visit = visit;
        }
    }
}
