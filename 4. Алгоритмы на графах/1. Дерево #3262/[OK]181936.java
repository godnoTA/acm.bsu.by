import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


class GraphNode {
    public int number;
    public boolean isVisited;

    public GraphNode(int number) {
        this.number = number;
        this.isVisited = false;
    }
}

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(new InputStreamReader(new FileInputStream("input.txt")));
        FileWriter writer = new FileWriter("output.txt", false);

        int nodesCount = scan.nextInt();
        int edgesCount = 0;

        int[][] adjMatrix = new int[nodesCount][nodesCount];
        for (int i = 0 ; i < nodesCount ; i++) {
            for (int g = 0 ; g < nodesCount ; g++) {
                adjMatrix[i][g] = scan.nextInt();
                if (adjMatrix[i][g] == 1) {
                    edgesCount++;
                }
            }
        }
        edgesCount = edgesCount/2;

        if(isTree(adjMatrix,edgesCount,nodesCount)) {
            writer.write("Yes");
        } else {
            writer.write("No");
        }

        writer.flush();
    }

    static boolean isTree(int[][] adjMatrix,int edgesCount,int nodesCount) {
        if(nodesCount == 1) {
            return true;
        }

        if (edgesCount != nodesCount - 1) {
            return false;
        }

        for (int i = 0 ; i < nodesCount ; i++) {
            int edgeCounter = 0;
            for (int g = 0 ; g < nodesCount ; g++) {
                if (adjMatrix[i][g] == 1) {
                    edgeCounter++;
                    break;
                }
            }
            if(edgeCounter == 0) {
                return false;
            }
        }
        return isCoherent(adjMatrix,nodesCount);
    }

    static boolean isCoherent(int[][] adjMatrix,int nodesCount) {
       ArrayList<GraphNode> nodesList = new  ArrayList<GraphNode>();
       GraphNode[] nodes = new GraphNode[nodesCount];
       for (int i = 0 ; i < nodesCount ; i++) {
           nodes[i] = new GraphNode(i);
       }
       nodes[0].isVisited = true;
        for (int i = 0 ; i < nodesCount ; i++) {
            if (adjMatrix[0][i] == 1) {
                nodesList.add(nodes[i]);
                nodes[i].isVisited = true;
            }
        }
        while (nodesList.size() != 0) {
            ArrayList<GraphNode> nextNodesList = new  ArrayList<GraphNode>();
            for (int i = 0 ; i < nodesList.size() ; i++) {
                GraphNode currentNode = nodesList.get(i);
                    currentNode.isVisited = true;
                    for (int g = 0 ; g < nodesCount ; g++ ) {
                        GraphNode adjNode = nodes[g];
                        if(adjMatrix[currentNode.number][g] == 1 && adjNode.isVisited != true) {
                            nextNodesList.add(adjNode);
                        }
                    }
            }
            nodesList = nextNodesList;
        }
        for (int i = 0 ; i < nodesCount ; i++) {
            if (nodes[i].isVisited != true){
                return false;
            }
        }
        return true;
    }
}