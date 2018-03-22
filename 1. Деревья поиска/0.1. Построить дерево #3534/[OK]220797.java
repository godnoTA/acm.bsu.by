 import java.io.*;
    public class Main implements Runnable {
        static class Node {
            int key ;
            Node left;
            Node right;

            public Node(int key) {
                this.key = key;
            }

        }

        static class Tree{
            FileWriter wr = new FileWriter("output.txt");
            Node root;

            Tree() throws IOException {
            }

            public void  add(int key) throws IOException {
                    Node parent = null;
                    Node node = root;
                    while (node != null) {
                        parent = node;
                        if (key < node.key) {
                            node = node.left;
                        } else if (key > node.key) {
                            node = node.right;
                        } else {
                            return;
                        }
                    }

                    Node newNode = new Node(key);

                    if (parent == null) {
                        root = newNode;
                    } else if (key < parent.key) {
                        parent.left = newNode;
                    } else if (key > parent.key) {
                        parent.right = newNode;
                    }
                }
            void directLeftBypass (Node node) throws IOException {
                wr.write (Integer.toString(node.key)+"\n");
                if(node.left != null){
                    directLeftBypass(node.left);
                }
                if(node.right != null){
                    directLeftBypass(node.right);
                }
            }
            void close() throws IOException {
                wr.close();
            }

        }
            public static void main(String[] args) throws IOException {
                new Thread(null, new Main(), "", 128 * 1024 * 1024).start();
            }

        public void run()  {
            Tree a = null;
            try {
                a = new Tree();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("input.txt")))){
                String line;
                String[] data;
                while ((line = reader.readLine()) != null) {
                    int key = Integer.parseInt(line);
                    a.add(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                a.directLeftBypass(a.root);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                a.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

