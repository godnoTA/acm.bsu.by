/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.File; 
import java.io.FileNotFoundException; 
import java.io.PrintWriter; 
import java.util.ArrayList; 
import java.util.Collections;
import java.util.List; 
import java.util.Scanner; 

/** 
* Created by User on 19.02.2017. 
*/ 
public class Tree { 


public List<Integer> forKeys;
public int numberOfNodes;
public List<Integer> forNodes;
public int averageElement;

public Tree() {
    forKeys = new ArrayList<Integer>();  
    forNodes = new ArrayList<Integer>();
    numberOfNodes = 0; 
}

static private class Node { 
public int key; 
public Node right; 
public Node left; 
int high; 
int children; 

public Node(int key) { 
this.key = key; 
} 
} 

private Node root; 

public void insert(int x) { 
root = doInsert(root, x); 
} 

private static Node doInsert(Node node, int x) { 
if (node == null) { 
return new Node(x); 
} 
if (x < node.key) { 
node.left = doInsert(node.left, x); 
} else if (x > node.key) { 
node.right = doInsert(node.right, x); 
} 
return node; 
} 

public int max(int a, int b) { 
if (a > b) { 
return a; 
} 
else return b; 
} 

public void postOrderTraversal(Node node){ 
if (node.left != null) { 
postOrderTraversal(node.left); 
} 
if (node.right != null) { 
postOrderTraversal(node.right); 

} 
if (node.right == null && node.left != null){ 
node.children = node.left.children + 1; 
node.high = node.left.high + 1; 
} 
else if (node.left != null && node.right != null){ 
node.children = node.right.children + node.left.children + 1; 
node.high = max(node.left.high, node.right.high) + 1; 
} 
else if (node.left == null && node.right != null ){ 
node.children = node.right.children + 1; 
node.high = node.right.high + 1; 
} 
else if (node.left == null && node.right == null) { 
node.children = 1; 
node.high = 0; 
} 
numberOfNodes++; 


} 

public int findAverage(List<Integer> forNodes) {
    if(forNodes.size() % 2 == 0) {
        return 0;
    }
    if (forNodes.size() % 2 == 1) {
        Collections.sort(forNodes);
        int index = (forNodes.size() / 2);
        
        averageElement = forNodes.get(index);
        System.out.println(forNodes);
        return averageElement;
    }
    return 0;
}


public void leftTravelsar(Node node) { 
if (node == null) { 
return; 
} 
if (node.right != null && node.left != null) { 
if (node.right.high == node.left.high && 
node.right.children != node.left.children) { 
  forNodes.add(node.key);
 

} 
} 

System.out.println(node.key + " High: " + node.high + "children: " + node.children); 
leftTravelsar(node.left); 
leftTravelsar(node.right); 
} 

public void leftTravelsarForOutput(Node node) { 
if (node == null) { 
return; 
} 
forKeys.add(node.key); 
leftTravelsarForOutput(node.left); 
leftTravelsarForOutput(node.right); 
} 




public void deleterecursivly(int x){ 
root = deleterecur(root, x); 
} 

public static Node deleterecur(Node node, int x){ 
if( node == null){ 
return null; 
} 
if( x < node.key){ 
node.left = deleterecur(node.left, x); 
return node; 
} 
else if(x > node.key){ 
node.right = deleterecur(node.right, x); 
return node; 
} 
if(node.left == null){ 
return node.right; 
} 
else if (node.right==null){ 
return node.left; 
} 
else{ 
int min = findmin(node.right).key; 
node.key = min; 
node.right = deleterecur(node.right, min); 
return node; 

} 
} 
public static Node findmin(Node current){ 
if( current.left != null){ 
return findmin(current.left); 
} 
else return current; 
} 


public Node getRoot() { 
return root; 
} 

public static void main(String[] args) throws FileNotFoundException { 
Tree mainTree = new Tree(); 

Scanner sc = new Scanner(new File("in.txt")); 

while (sc.hasNext()) { 
int x = sc.nextInt(); 
mainTree.insert(x); 
} 

mainTree.postOrderTraversal(mainTree.root); 

mainTree.leftTravelsar(mainTree.root); 

System.out.println(); 



int deletingMainElement = mainTree.findAverage(mainTree.forNodes);

    System.out.println(deletingMainElement);

mainTree.deleterecursivly(deletingMainElement);

mainTree.leftTravelsarForOutput(mainTree.root); 

System.out.println(mainTree.numberOfNodes); 

 
PrintWriter pw = new PrintWriter(new File("out.txt")); 
for (Integer number : mainTree.forKeys) { 
pw.println(number); 
} 
pw.close(); 
 

} 
}