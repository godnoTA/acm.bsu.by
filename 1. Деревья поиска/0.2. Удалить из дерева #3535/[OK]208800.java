
import java.io.*;
import java.util.*;
public class Test {
    static class Tree {
        private class Node {
            public int key;
            public Node left;
            public Node right;
            public Node parent;
            public Node(int key) {
                this.key = key;
            }
        }

        private Node root;
        void replace(Node a,Node b) {
            if (a.parent == null)
                root = b;
            else if (a == a.parent.left)
                a.parent.left = b;
            else
                a.parent.right = b;
            if (b != null)
                b.parent = a.parent;
        }
        void remove(Node t, int key) {
            if (t == null)
                return;
            if (key < t.key)
                remove(t.left, key);
            else if (key > t.key)
                remove(t.right, key);
            else if (t.left != null && t.right != null) {
                Node m = t.right;
                while (m.left != null)
                    m = m.left;
                t.key = m.key;
                replace(m, m.right);
            } else if (t.left != null) {
                replace(t, t.left);
            } else if (t.right != null) {
                replace(t, t.right);
            } else {
                replace(t, null);
            }
        }
        public void remove(int key) {
            remove(root, key);
        }

        public void insert(int x) {
            root = doInsert(root, x);
        }

        private Node doInsert(Node node, int x) {
            if (node == null) {
                Node test = new Node(x);
                return test;
            }
            if (x < node.key) {
                node.left = doInsert(node.left, x);
                node.left.parent = node;
            } else if (x > node.key) {
                node.right = doInsert(node.right, x);
                node.right.parent = node;
            }
            return node;
        }
    }

    public static void main(String[] args) {
        Tree binaryTree = new Tree();
        try {
            BufferedReader in = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
            String str;
            int delNumber = Integer.parseInt(in.readLine());
            in.readLine();
            while ((str = in.readLine()) != null)
                binaryTree.insert(Integer.parseInt(str));
            in.close();
            binaryTree.remove(delNumber);
            out.write(leftDirect(binaryTree));
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String leftDirect(Tree binaryTree) {
        Stack<Tree.Node> stack = new Stack<>();
        Tree.Node top = binaryTree.root;
        String str = "";
        while (top != null || !stack.empty()) {
            if (!stack.empty()) {
                top = stack.pop();
            }
            while (top != null) {
                str += top.key + "\n";
                if (top.right != null)
                    stack.push(top.right);
                top = top.left;
            }
        }
        return str;
    }
//    public void delElement(int delNumber, Tree.Node node) {
//        if(node==null)
//            return;
//        else if(delNumber>node.key)
//            delElement(delNumber,node.right);
//        else if(delNumber<node.key)
//            delElement(delNumber,node.left);
//        else{
//            if(node.left==null&&node.right==null)
//            {
//                if(node.parent.key>node.key)
//                    node.parent.left=null;
//                else
//                    node.parent.right=null;
//            }
//            else if(node.left==null)
//            {
//                if(node.parent.key>node.key)
//                    node.parent.left=node.right;
//                else
//                    node.parent.right=node.right;
//            }
//            else if(node.right==null)
//            {
//                if(node.parent.key>node.key)
//                    node.parent.left=node.left;
//                else
//                    node.parent.right=node.left;
//            }
//        }}
}