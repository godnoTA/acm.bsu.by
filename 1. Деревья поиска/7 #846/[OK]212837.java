import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.Consumer;

class TreeNode {
    private Integer number;
    public TreeNode left = null;
    public TreeNode right = null;
    public Integer level = 0;
    public Integer height;
    public boolean mark = false;

    public TreeNode(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}


public class Main {

    public static void showTree(TreeNode root, PrintWriter writer) throws IOException {
        if (root != null) {
            writer.write(String.valueOf(root.getNumber()) + "\n");
            showTree(root.left, writer);
            showTree(root.right, writer);
        }
    }


    public static List<TreeNode> setLevel(TreeNode root, Integer middle) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<TreeNode> list = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            tempNode.level = getLevel(root,tempNode.getNumber(),0);
            if(tempNode.level > middle) {
                return list;
            }
            if(tempNode.level == middle) {
                if ((tempNode.right == null && tempNode.left == null) || (tempNode.left != null && tempNode.right != null && (tempNode.left.height == tempNode.right.height))) {
                    list.add(tempNode);
                }
            }
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
        return list;
    }

    public static Integer  getLevel(TreeNode root, Integer number, Integer level)
    {
        if (root == null)
            return 0;

        if (root.getNumber() == number)
            return level;

        int downlevel = getLevel(root.left, number, level + 1);
        if (downlevel != 0)
            return downlevel;

        downlevel = getLevel(root.right, number, level + 1);
        return downlevel;
    }



    public static TreeNode deleteVertex(TreeNode root, Integer deleteKey) {
        if (root == null)
            return root;

        if (deleteKey < root.getNumber()) {
            root.left = deleteVertex(root.left, deleteKey);
            return root;
        } else if (deleteKey > root.getNumber()) {
            root.right = deleteVertex(root.right, deleteKey);
            return root;
        }
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        } else {
            Integer min_key = find_min(root.right).getNumber();
            root.setNumber(min_key);
            root.right = deleteVertex(root.right, min_key);
            return root;
        }
    }

    public static TreeNode find_min(TreeNode root) {
        if (root.left != null) {
            return find_min(root.left);
        } else {
            return root;
        }
    }

    public static boolean setNode(TreeNode prev, TreeNode curr) {
        while (prev.getNumber() != curr.getNumber()) {
            if (prev.getNumber() > curr.getNumber()) {
                if (prev.left == null) {
                    prev.left = curr;
                    return true;
                } else {
                    prev = prev.left;
                }
            } else if (prev.getNumber() < curr.getNumber()) {
                if (prev.right == null) {
                    prev.right = curr;
                    return true;
                } else {
                    prev = prev.right;
                }
            }
        }
        return true;
    }


    public static void postOrder(TreeNode root, Consumer<TreeNode> func) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> deque1 = new ArrayDeque<>();
        Deque<TreeNode> deque2 = new ArrayDeque<>();
        deque1.push(root);
        while (!deque1.isEmpty()) {
            TreeNode temp = deque1.poll();
            if (temp.left != null) {
                deque1.push(temp.left);
            }
            if (temp.right != null) {
                deque1.push(temp.right);
            }
            deque2.push(temp);
        }
        while (!deque2.isEmpty()) {
            func.accept(deque2.poll());
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("in.txt"));
        TreeNode root;
        Integer temp = Integer.parseInt(sc.next());
        root = new TreeNode(temp);
        TreeNode prev;
        while (sc.hasNext()) {
            temp = Integer.parseInt(sc.next());
            prev = root;
            TreeNode current = new TreeNode(temp);
            setNode(prev, current);
        }
        postOrder(root, a -> {
            TreeNode left = a.left;
            TreeNode right = a.right;
            if (right == null && left == null) {
                a.height = 0;
            } else if (right != null && left == null) {
                a.height = a.right.height + 1;
            } else if (right == null) {
                a.height = a.left.height + 1;
            } else {
                a.height = Math.max(a.right.height, a.left.height) + 1;
            }
        });
        Integer middle;
        if(root.height%2!=0) {
            middle = root.height / 2+1;
        }
        else{
             middle = root.height/2;
        }
        PrintWriter writer = new PrintWriter("out.txt");
        List<TreeNode> list = setLevel(root, middle);
        if (list.size() % 2 != 0) {
            deleteVertex(root,list.get(list.size() / 2).getNumber());
        }
        showTree(root,writer);
        writer.close();
    }
}
