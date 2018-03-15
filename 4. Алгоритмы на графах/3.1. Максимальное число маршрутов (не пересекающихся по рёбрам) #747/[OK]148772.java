import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MaximumFlow {


//    static class Edge {
//        private Node start;
//        private Node end;
//        private Edge backEdge;
//        private int weight = 1;
//
//        public Edge(Node first,Node second){
//            start = first;
//            end = second;
//        }
//
//        @Override
//        public String toString() {
//            return "("+(start.key+1)+","+(end.key+1)+")";
//        }
//    }

    static class Node {
        private Integer key;
        private Boolean isVisited;
        private Node parentNode;
        private LinkedList<Node> listOfNodes = new LinkedList<>();

        Node(Integer akey) {
            this.key = akey;
            this.isVisited = false;
            this.parentNode = null;
        }

        @Override
        public String toString() {
            return key.toString();
        }

    }

    private static ArrayList<Node> arrOfNodes = new ArrayList<>();
    private static Node source;
    private static Node destination;
    private static LinkedList<Node> path = new LinkedList<>();
    private static LinkedList<Node> queue = new LinkedList<>();
    private static int[][] matrix = new int[0][0];
    private static int maxFlow = 0;

    public static void main(String[] args){

        long startTime = System.currentTimeMillis();

        try {

            BufferedReader readData = new BufferedReader(new InputStreamReader(new FileInputStream(new File("input.in")), "UTF-8"));

            String strData;

            int amount = 0;
            int count = 0;

            StringTokenizer stringTokenizer = new StringTokenizer(readData.readLine(), " ", false);
            amount = Integer.parseInt(stringTokenizer.nextToken());

            matrix = new int[amount][amount];
            for (int i = 0 ; i < amount ; ++i){
                for (int j = 0 ; j < amount ; ++j){
                    matrix[i][j] = 0;
                }
            }
            arrOfNodes = new ArrayList<>(amount);
            for (int i = 0 ; i < amount ; ++i){
                arrOfNodes.add(new Node(i));
            }

            stringTokenizer.nextToken();

            while ( (strData = readData.readLine()) != null){

                stringTokenizer = new StringTokenizer(strData, " ", false);

                if (count < amount) {
                    while (stringTokenizer.hasMoreTokens()) {
                        int tempValue = Integer.parseInt(stringTokenizer.nextToken())-1;
                        if (tempValue != -1) {
                            matrix[count][tempValue] = 1;
                            arrOfNodes.get(count).listOfNodes.add(arrOfNodes.get(tempValue));
                        }
                    }
                    ++count;
                }else {
                    source = arrOfNodes.get(Integer.parseInt(stringTokenizer.nextToken())-1);
                    destination = arrOfNodes.get(Integer.parseInt(stringTokenizer.nextToken())-1);
                }
            }

            System.out.println((source.key + 1) + " " + (destination.key + 1));

            breadthFirstSearch();
            if (!queue.isEmpty()) {
                restorePath();
                System.out.println(path);
                modifyNetwork();
                resetAll();
            }

            while (true){
                extendedBreadthFirstSearch();
                if (!queue.isEmpty()) {
                    restorePath();
                    System.out.println(path);
                    modifyNetwork();
                    resetAll();
                }else {
                    break;
                }
            }


            System.out.println(maxFlow);

            FileWriter writer = new FileWriter("output.out", false);
            writer.write(String.valueOf(maxFlow));
//            StringBuilder builder = new StringBuilder();
//            for (int i = 0 ; i < 100; ++i){
//                for (int j = 0 ; j < 100;++j){
//                    if (i != j) {
//                        builder.append(String.valueOf(j + 1)).append(" ");
//                    }
//                }
//                builder.append("0\n");
//            }
//            writer.write(builder.toString());
            writer.flush();

        }catch (IOException ex){
            ex.getStackTrace();
        }

        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("program was carried out -  " + timeSpent + "  - milliseconds ");
    }

    public static void breadthFirstSearch(){
        queue.clear();
        queue.add(source);
        source.isVisited = true;
        while (!queue.isEmpty() && queue.peekLast() != destination){
            Node tmpVertex = queue.poll();
            for (Node tempNode : tmpVertex.listOfNodes){
                if (!tempNode.isVisited){
                    queue.add(tempNode);
                    tempNode.isVisited = true;
                    tempNode.parentNode = tmpVertex;
                    if (tempNode == destination){
                        break;
                    }
                }
            }
        }
        source.parentNode = null;
    }

    public static void extendedBreadthFirstSearch(){
        queue.clear();
        queue.add(source);
        source.isVisited = true;
        while (!queue.isEmpty() && queue.peekLast() != destination) {
            Node tmpVertex = queue.poll();
            int min = minConstInEdges(tmpVertex);
            if (min == 999999999){
                continue;
            }
            for (Node tempNode : tmpVertex.listOfNodes){
                if (!tempNode.isVisited && matrix[tmpVertex.key][tempNode.key] == min){
                    queue.add(tempNode);
                    tempNode.isVisited = true;
                    tempNode.parentNode = tmpVertex;
                    if (tempNode == destination) {
                        break;
                    }
                }
            }
        }
        source.parentNode = null;
    }

    public static void restorePath(){
        path.clear();
        Node tempNode = destination;
        while (tempNode != null) {
            path.addFirst(tempNode);
            tempNode = tempNode.parentNode;
        }
    }

    public static int minConstInPath(){
        int min = 999999999;
        LinkedList<Node> tempPath = new LinkedList<>(path);
        Node vert = tempPath.pollFirst();
        while (!tempPath.isEmpty()){
            Node temp = tempPath.peekFirst();
            if (min > matrix[vert.key][temp.key]) {
                min = matrix[vert.key][temp.key];
            }
            vert = tempPath.pollFirst();
        }
        return min;
    }

    public static int minConstInEdges(Node vertex){
        int min = 999999999;
        for (Node tempVertex : vertex.listOfNodes){
            if (!tempVertex.isVisited && matrix[vertex.key][tempVertex.key] != 0){
                if (min > matrix[vertex.key][tempVertex.key]){
                    min = matrix[vertex.key][tempVertex.key];
                }
            }
        }
//        for (int i = 0 ; i < matrix.length; ++i){
//            if (min > matrix[vertex.key][i] && matrix[vertex.key][i] != 0){
//                min = matrix[vertex.key][i];
//            }
//        }
        return min;
    }

    public static void modifyNetwork(){
        int min = minConstInPath();
        maxFlow+=min;
        LinkedList<Node> tempPath = new LinkedList<>(path);
        Node vert = tempPath.pollFirst();
        while (!tempPath.isEmpty()){
            Node temp = tempPath.peekFirst();
            matrix[vert.key][temp.key] -= min;
            matrix[temp.key][vert.key] += min;
            vert = tempPath.pollFirst();
        }
    }
    public static void resetAll(){
        for (Node tempNode : arrOfNodes){
            tempNode.isVisited = false;
            tempNode.parentNode = null;
            path.clear();
            queue.clear();
        }
    }

}
