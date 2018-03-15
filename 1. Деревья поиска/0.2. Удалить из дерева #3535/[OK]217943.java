import java.io.*;
import java.util.*;

public class BinTree {
    static class Tree {
        private class TreeNode {
            public long value;
            public TreeNode left, right, parent;

            public TreeNode(long value) {
                this.value = value;
            }
        }

        private TreeNode rootOfNode;

        void replace(TreeNode first, TreeNode second) {
            if (first.parent == null)
                rootOfNode = second;
            else if (first == first.parent.left)
                first.parent.left = second;
            else
                first.parent.right = second;
            if (second != null)
                second.parent = first.parent;
        }

        void Delete(TreeNode tree, long value) {
            if (tree == null)
                return;
            if (tree.value > value)
                Delete(tree.left, value);
            else
                if (tree.value < value)
                    Delete(tree.right, value);
                else
                    if (tree.left != null && tree.right != null) {
                        TreeNode tempNode = tree.right;
                        while (tempNode.left != null)
                            tempNode = tempNode.left;
                        tree.value = tempNode.value;

                        replace(tempNode, tempNode.right);
                    }
                    else
                        if (tree.left != null) {
                            replace(tree, tree.left);
                        }
                        else
                            if (tree.right != null) {
                                replace(tree, tree.right);
                            }
                            else {
                                replace(tree, null);
                            }
        }

        public void SerchDeleteNode(long key) {
            Delete(rootOfNode, key);
        }

        public void AddNode(long temp) {
            rootOfNode = Add(rootOfNode, temp);
        }

        private TreeNode Add(TreeNode node, long val) {
            if (node == null) {
                TreeNode tree = new TreeNode(val);
                return tree;
            }
            if (node.value > val) {
                node.left = Add(node.left, val);
                node.left.parent = node;
            } else if (node.value < val) {
                node.right = Add(node.right, val);
                node.right.parent = node;
            }
            return node;
        }
    }

    private static ArrayList<Long> MakeStraightThroughTree(Tree binaryTree) {
        ArrayList<Long> list= new ArrayList<>();
        Stack<Tree.TreeNode> stack = new Stack<>();
        Tree.TreeNode top = binaryTree.rootOfNode;

        while (top != null || !stack.empty()) {
            if (!stack.empty()) {
                top = stack.pop();
            }
            while (top != null) {
                list.add(top.value);
                if(top.right != null)
                    stack.push(top.right);
                top = top.left;
            }
        }
        return list;
    }

    private static void WriteResult(ArrayList<Long> list) throws IOException{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))){
            for(Long num : list){
                writer.write(num + "\n");
            }
            writer.close();
        }
    }

    public static void main(String[] args) {
        Tree binTreeSerch = new Tree();
        try(BufferedReader reader= new BufferedReader(new FileReader("input.txt"))){
            long deleteNumber = Long.parseLong(reader.readLine());
            reader.readLine();
            while (reader.ready()){
                binTreeSerch.AddNode(Long.parseLong(reader.readLine()));
            }
            reader.close();
            binTreeSerch.SerchDeleteNode(deleteNumber);
            ArrayList<Long> list= MakeStraightThroughTree(binTreeSerch);
            WriteResult(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}