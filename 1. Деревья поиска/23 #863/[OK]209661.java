//package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Vector;


public class Tree {

    static private Node root;
    static FileWriter fileWriter;
    static Vector<Node> nodes = new Vector<>();
    class Node{
        Node left, right;
        int value, depth;
        Vector<Integer> heightsL, heightsR;
        public Node(int a){
            value = a;
            left = null;
            right = null;
            heightsL = new Vector<>();
            heightsR = new Vector<>();
            depth = 0;
        }
    }

    void add(int a){
        if(root == null){
            root = new Node(a);
            root.depth = 0;
        }
        else {
            Node node = root;
            int depth = root.depth;
            while (node.right!=null || node.left!=null){
                if (a > node.value && node.right!= null){
                    node = node.right;
                    depth++;
                }
                else if (a > node.value && node.right== null){
                    Node n = new Node(a);
                    n.depth = depth+1;
                    node.right = n;
                }
                else if (a < node.value && node.left!=null){
                    node = node.left;
                    depth++;
                }
                else if (a < node.value && node.left==null){
                    Node n = new Node(a);
                    n.depth = depth+1;
                    node.left = n;
                }
                else {
                    break;
                }
            }
            if (a>node.value){
                Node n = new Node(a);
                n.depth = depth+1;
                node.right = n;
            }
            else if(a<node.value){
                Node n = new Node(a);
                n.depth = depth+1;
                node.left = n;
            }
        }
    }

    void delete(int a){     /////////////////не меняю height
        Node node = root;
        Node prev = root;
        while (node.right!=null || node.left!=null){
            if (a > node.value && node.right!= null){
                prev = node;
                node = node.right;
            }
            else if (a > node.value && node.right== null){
                break;
            }
            else if (a < node.value && node.left!=null){
                prev = node;
                node = node.left;
            }
            else if (a < node.value && node.left==null){
                break;
            }
            else {
                break;
            }
        }
        if(a == root.value){
            if(root.right==null && root.left==null){
                root = null;
            }
            else if(root.left !=null && root.right==null){
                root = root.left;
            }
            else if(root.left ==null && root.right!=null){
                root = root.right;
            }
            else if(root.left !=null && root.right!=null){
                if(root.left.right!=null){
                    Node pr = root.left;
                    Node cur = root.left.right;
                    while (cur.right!=null){
                        pr = cur;
                        cur = cur.right;
                    }
                    int num = cur.value;
                    if (cur.left == null){
                        pr.right=null;
                    } else {
                        pr.right = pr.right.left;
                    }
                    root.value = num;
                }
                else {
                    Node rootRight = root.right;
                    root=root.left;
                    root.right=rootRight;
                }
            }
        }
        else if (a==node.value){
            if(node.left==null && node.right==null){
                if(a>prev.value){
                    prev.right=null;
                } else {
                    prev.left = null;
                }
            } else if(node.left !=null && node.right==null){
                if(a>prev.value){
                    prev.right=prev.right.left;
                } else {
                    prev.left = prev.left.left;
                }
            } else if(node.left ==null && node.right!=null){
                if(a>prev.value){
                    prev.right=prev.right.right;
                } else {
                    prev.left = prev.left.right;
                }
            } else if(node.left !=null && node.right!=null){
                if(node.left.right!=null){
                    Node pr = node.left;
                    Node cur = node.left.right;
                    while (cur.right!=null){
                        pr = cur;
                        cur = cur.right;
                    }
                    int num = cur.value;
                    if (cur.left == null){
                        pr.right=null;
                    } else {
                        pr.right = pr.right.left;
                    }
                    node.value = num;
                }
                else {
                    if(a>prev.value){
                        prev.right=prev.right.left;
                        prev.right.right=node.right;
                    } else {
                        prev.left = prev.left.left;
                        prev.left.right=node.right;
                    }
                }
            }
        }
    }

    void order1(Node node) throws IOException {
        String s = System.getProperty("line.separator");
        fileWriter.write(((Integer)node.value).toString()+s);

        if (node.left!= null){
            order1(node.left);
        }
        if (node.right!=null){
            order1(node.right);
        }
    }

    Node height(Node node){
        if(node.right==null && node.left==null){
            node.heightsL.add(0);
            node.heightsR.add(0);
            return node;
        } else if(node.right==null && node.left!=null){
            //HashSet<Integer> set2 = new HashSet<>();
            Vector<Integer> vec2 = new Vector<>();
            Node n = height(node.left);
            if (n.heightsL!=null) {
                vec2.addAll(n.heightsL);
            }
            if (n.heightsR!=null) {
                vec2.addAll(n.heightsR);
            }
            for(int i =0; i<vec2.size();i++){
                node.heightsL.add(vec2.get(i)+1);
            }
            node.heightsR = null;
            return node;
        } else if(node.right!=null && node.left==null){
            //HashSet<Integer> set2 = new HashSet<>();
            Vector<Integer> vec2 = new Vector<>();
            Node n = height(node.right);
            if (n.heightsL!=null) {
                vec2.addAll(n.heightsL);
            }
            if (n.heightsR!=null) {
                vec2.addAll(n.heightsR);
            }
            for(int i =0; i<vec2.size();i++){
                node.heightsR.add(vec2.get(i)+1);
            }
            node.heightsL = null;
            return node;
        } else {
            //HashSet<Integer> set1 = new HashSet<>();
            Vector<Integer> vec1 = new Vector<>();
            Node n1 = height(node.left);
            if (n1.heightsL!=null) {
                vec1.addAll(n1.heightsL);
            }
            if (n1.heightsR!=null) {
                vec1.addAll(n1.heightsR);
            }
            for(int i =0; i<vec1.size();i++){
                node.heightsL.add(vec1.get(i)+1);
            }
            //HashSet<Integer> set2 = new HashSet<>();
            Vector<Integer> vec2 = new Vector<>();
            Node n = height(node.right);
            if (n.heightsL!=null) {
                vec2.addAll(n.heightsL);
            }
            if (n.heightsR!=null) {
                vec2.addAll(n.heightsR);
            }
            for(int i =0; i<vec2.size();i++){
                node.heightsR.add(vec2.get(i)+1);
            }
            return node;
        }
    }

////////////////////////////////////////////////////////////////
    void isSuit(Node node, int k){
        //if(2*Collections.max(node.heights)>=k && 2*Collections.min(node.heights)<=k) { /////////////////////////////////////
            boolean suit = false;
            if (node.heightsL!=null && node.heightsR!=null) {  /////////////////////////////////////////////
                for (Integer i : node.heightsR) {
                    for (Integer j : node.heightsL) {
                        if (i + j == k) {
                            suit = true;
                            break;
                        }
                    }
                    if (suit) break;
                }
            }
            if (suit) {
                nodes.add(node);
            }
        //}
        if (node.left!= null){
            isSuit(node.left,k);
        }
        if (node.right!=null){
            isSuit(node.right,k);
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        Integer k=null;
        try(FileReader reader = new FileReader("in.txt")){
            Scanner scan = new Scanner(reader);
            if (scan.hasNextLine()){
                k = Integer.parseInt(scan.nextLine());
            }
            while (scan.hasNextLine()){
                int c = Integer.parseInt(scan.nextLine());
                tree.add(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        tree.height(root);
        if(k!= null) {
            tree.isSuit(root, k);
            if (!nodes.isEmpty()) {
                Vector<Node> vector = new Vector<>();
                int minDepth = nodes.get(0).depth;
                for (Node i : nodes) {
                    if (i.depth < minDepth) {
                        minDepth = i.depth;
                    }
                    //System.out.println(((Integer)i.value).toString() + ' ');
                }
                for (Node i : nodes) {
                    if (i.depth == minDepth) {
                        vector.add(i);
                    }
                }
                int mean = 0;
                for (Node i : vector) {
                    mean += i.value;
                }
                mean /= vector.size();
                //System.out.println(((Integer) mean).toString());
                for (Node i : vector) {
                    if (i.value == mean) {
                        tree.delete(i.value);
                    }
                }
            }
        }
        try {
            File file = new File("out.txt");
            file.createNewFile();
            fileWriter = new FileWriter("out.txt");
            tree.order1(root);
            System.out.println(root.right.heightsR.toString());
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

