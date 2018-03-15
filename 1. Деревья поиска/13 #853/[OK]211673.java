import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Tree implements Runnable {

    public class Node {
        private Long value;
        private Node left;
        private Node right;
        private Long height = Long.valueOf(0);
        private Long depth = Long.valueOf(0);
        private Node parent;


        public Node(Long value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

    }
    private Node root;
    private Node maxWayRoot;
    private Node maxWayLeftNode;
    private Node maxWayRightNode;

    private Set<Long> keySet;

    private Long maxLength = Long.valueOf(0);
    private Long minSum = Long.valueOf(0);

    private boolean isLeftDefined = false;
    private boolean isRightDefined = false;
    private boolean isDeleteRequired = false;



    public Node getRoot() {
        return root;
    }

    public Tree(Long value) {
        this.root = new Node(value);
        root.depth = Long.valueOf(0);
        root.parent = null;
        keySet = new TreeSet<>();
    }

    public Tree() {
        keySet = new TreeSet<>();
    }

    public boolean find(Long value) {
        Node current = this.root;
        while (current != null) {
            if (current.value.compareTo(value) == 0) {
                return true;
            } else if (value.compareTo(current.value) < 0) {
                current = current.left;
            }
            else {
                current = current.right;
            }
        }
        return false;

    }

    public void insert(Long value) {
        Node toBeInserted = new Node(value);
        if(root == null) {
            root = toBeInserted;
            root.parent = null;
            root.depth = Long.valueOf(0);
        }
        else {
            Node parent = null;
            Node current = root;
            while(current != null) {
                if(current.value.compareTo(value) == 0) {
                    current = toBeInserted;
                    if(current == root) {
                        current.depth = Long.valueOf(0);
                        current.parent = null;
                    }
                    else {
                        current.parent = parent;
                        current.depth = parent.depth;
                    }
                    return;
                }
                else if(value.compareTo(current.value) < 0){
                    parent = current;
                    current = current.left;

                }
                else {
                    parent = current;
                    current = current.right;
                }

            }
            if(value.compareTo(parent.value) < 0) {
                parent.left = toBeInserted;
                parent.left.parent = parent;
                parent.left.depth = parent.left.parent.depth + 1;
            }
            else if (value.compareTo(parent.value) > 0)  {
                parent.right = toBeInserted;
                parent.right.parent = parent;
                parent.right.depth = parent.right.parent.depth + 1;
            }



        }

    }

    private Long defineMedium(Node left,Node node,Node right) {

        Node tempo1 = left;
        Node tempo2 = right;
        keySet.clear();
        while(tempo1 != null && tempo1 != node) {
            keySet.add(tempo1.value);
            tempo1 = tempo1.parent;
        }
        while (tempo2 != null && tempo2 != node) {
            keySet.add(tempo2.value);
            tempo2 = tempo2.parent;
        }
        keySet.add(node.value);
        Iterator<Long> iterator = keySet.iterator();
        Long counter = Long.valueOf(-1);
        Long index = Long.valueOf(0);
        if(keySet.size() % 2 == 0) {
            index = (Long.valueOf(keySet.size())  / 2) - 1 ;
        }
        else {
            index = (Long.valueOf(keySet.size()) / 2);
        }

        Long toDelete = Long.valueOf(0);
        while (iterator.hasNext() && counter != index) {
            toDelete = iterator.next();
            counter++;
        }
        return toDelete;
    }

    private void setNewMax(Node left,Node root,Node right) {
        if(root != null) {
            maxWayLeftNode = left;
            maxWayRoot = root;
            maxWayRightNode = right;
            if(left != null && right != null) {
                maxLength = (maxWayLeftNode.depth - maxWayRoot.depth) + (maxWayRightNode.depth - maxWayRoot.depth);
                minSum = maxWayLeftNode.value + maxWayRightNode.value;
            }
            else if(left != null && right == null) {
                maxLength = maxWayLeftNode.depth - maxWayRoot.depth;
                minSum = maxWayLeftNode.value + maxWayRoot.value;
            }
            else if(left == null && right != null) {
                maxLength = maxWayRightNode.depth - maxWayRoot.depth;
                minSum = maxWayRightNode.value + maxWayRoot.value;
            }
            else {
                minSum = maxWayRoot.value;
                maxLength = Long.valueOf(0);
            }
        }
    }

    private void changeNewMaxIfRequired(Node left,Node node,Node right,boolean delete) {

        Long length;
        Long sum;

        if(left != null && right != null) {
            length = (left.depth - node.depth) + (right.depth - node.depth);
            sum = left.value + right.value;
        }
        else if(left != null && right == null) {
            length = left.depth - node.depth;
            sum = left.value + node.value;
        }
        else if(left == null && right != null) {
            length = right.depth - node.depth;
            sum = right.value + node.value;
        }
        else {
            sum = node.value;
            length = Long.valueOf(0);
        }

        if(length == maxLength) {
            if(sum == minSum) {
                if(node.value == maxWayRoot.value) {
                    Long med1 = defineMedium(left,node,right);
                    Long med2 = defineMedium(maxWayLeftNode,maxWayRoot,maxWayRightNode);
                    if(med1 == med2) {
                        isDeleteRequired = true;
                    }
                    else {
                        isDeleteRequired = false;
                    }
                }
                else if(node.value < maxWayRoot.value) {
                    setNewMax(left,node,right);
                    isDeleteRequired = delete;
                }
            }
            else  if(sum < minSum) {
                setNewMax(left,node,right);
                isDeleteRequired = delete;
            }
        }
        else if(length > maxLength) {
            setNewMax(left,node,right);
            isDeleteRequired = delete;
        }


    }

    public void preOrderTraverse(Node node) {
        if(node != null) {

            if (!isLeftDefined && !isRightDefined) {
                if (node.left != null && node.right != null) {
                    setNewMax(defineLeaf(node.left), node, defineLeaf(node.right));
                    isDeleteRequired = true;
                    isLeftDefined = true;
                    isRightDefined = true;
                    if (maxWayLeftNode.height.compareTo(maxWayRightNode.height) == 0) {
                        Long temp1 = maxWayLeftNode.parent.value + maxWayRightNode.value;
                        Long temp2 = maxWayLeftNode.value + maxWayRightNode.parent.value;
                        if (temp1 < temp2) {
                            if (maxWayLeftNode.parent != maxWayRoot) {
                                setNewMax(maxWayLeftNode.parent, node, maxWayRightNode);
                                isDeleteRequired = true;
                            }
                            else {
                                setNewMax(null, node, maxWayRightNode);
                                isDeleteRequired = true;
                            }

                        }

                        else if (temp1 > temp2) {
                            if (maxWayRightNode.parent != maxWayRoot) {
                                setNewMax(maxWayLeftNode, node, maxWayRightNode.parent);
                                isDeleteRequired = true;
                            }
                            else {
                                setNewMax(maxWayLeftNode, node, null);
                                isDeleteRequired = true;
                            }

                        }
                        else {

                            Long med1 = defineMedium(maxWayLeftNode,node,maxWayRightNode.parent);
                            Long med2 = defineMedium(maxWayLeftNode.parent,node,maxWayRightNode);
                            if(med1 == med2) {
                                isDeleteRequired = true;
                                setNewMax(maxWayLeftNode, node, maxWayRightNode.parent);
                            }
                            else {
                                isDeleteRequired = false;
                                setNewMax(maxWayLeftNode, node, maxWayRightNode.parent);
                            }

                        }

                    }


                } else if (node.left != null && node.right == null) {
                    Node tempo = defineLeaf(node.left);
                    setNewMax(tempo, node, null);
                    isLeftDefined = true;
                    isDeleteRequired = true;

                } else if (node.left == null && node.right != null) {
                    Node tempo = defineLeaf(node.right);
                    setNewMax(null, node, tempo);
                    isRightDefined = true;
                    isDeleteRequired = true;

                } else {
                    setNewMax(null, node, null);
                    isDeleteRequired = false;
                }
            }
            else {
                if (node.left != null && node.right != null) {
                    Node tempo1 = defineLeaf(node.left);
                    Node tempo2 = defineLeaf(node.right);

                    Long temp1 = tempo1.parent.value + tempo2.value;
                    Long temp2 = tempo1.value + tempo2.parent.value;

                    if (temp1 < temp2) {
                        tempo1 = tempo1.parent;
                        changeNewMaxIfRequired(tempo1, node, tempo2, true);
                    } else if (temp1 > temp2) {
                        tempo2 = tempo2.parent;
                        changeNewMaxIfRequired(tempo1, node, tempo2, true);
                    } else {
                        Long med1 = defineMedium(tempo1.parent,node,tempo2);
                        Long med2 = defineMedium(tempo1,node,tempo2.parent);
                        Long length = tempo1.parent.depth - node.depth + tempo2.depth - node.depth;
                        if(length == maxLength) {
                            if(temp1 == minSum) {
                               if(node.value < maxWayRoot.value) {
                                   if(med1 == med2) {
                                       setNewMax(tempo1,node,tempo2.parent);
                                       isDeleteRequired = true;
                                   }
                                   else {
                                       setNewMax(tempo1,node,tempo2.parent);
                                       isDeleteRequired = false;
                                   }
                               }

                            }
                            else if(temp1 < minSum) {
                                if(med1 == med2) {
                                    setNewMax(tempo1,node,tempo2.parent);
                                    isDeleteRequired = true;
                                }
                                else {
                                    setNewMax(tempo1,node,tempo2.parent);
                                    isDeleteRequired = false;
                                }
                            }
                        }
                        else if(length > maxLength) {
                            if(med1 == med2) {
                                setNewMax(tempo1,node,tempo2.parent);
                                isDeleteRequired = true;
                            }
                            else {
                                setNewMax(tempo1,node,tempo2.parent);
                                isDeleteRequired = false;
                            }
                        }



                    }


                }


                else if(node.left != null && node.right == null) {
                    Node tempo = defineLeaf(node.left);
                    changeNewMaxIfRequired(tempo,node,null,true);

                }
                else if(node.left == null && node.right != null) {
                    Node tempo = defineLeaf(node.right);
                    changeNewMaxIfRequired(null,node,tempo,true);

                }


            }


            preOrderTraverse(node.left);
            preOrderTraverse(node.right);
            }

    }

    private Node defineLeaf(Node node) {

        while(node.left != null || node.right != null) {
            if(node.left != null && node.right != null) {
                if(node.left.height > node.right.height) {
                    node = node.left;
                }
                else if(node.left.height < node.right.height) {
                    node = node.right;
                }
                else {
                    if(node.left.value < node.right.value) {
                        node = node.left;
                    }
                    else {
                        node = node.right;
                    }
                }
            }
            else if(node.left == null && node.right != null) {
                node = node.right;

            }
            else if(node.left != null && node.right == null) {
                node = node.left;
            }


        }
        return node;


    }

    public void delete(Long value) {
        Node parent = null;
        Node current = this.root;
        while (current != null) {
            if (value.compareTo(current.value) == 0) {
                break;
            } else if (value.compareTo(current.value) > 0) {
                parent = current;
                current = current.right;
            } else {
                parent = current;
                current = current.left;
            }
        }
        if(current != null) {
            if(current.left == null && current.right == null) { // No Children
                if(parent == null) {
                    root = null;
                    return;
                }
                if(parent.left == current) {
                    parent.left = null;
                }
                else {
                    parent.right = null;
                }
            }
            else if( (current.left == null) != (current.right == null) ) { // One Child
                if(current.left != null) {
                    if(parent == null) {
                        root = current.left;
                        return;
                    }
                    if(parent.left == current) {
                        parent.left = current.left;
                    }
                    else {
                        parent.right = current.left;
                    }
                }
                else {
                    if(parent == null) {
                        root = current.right;
                        return;
                    }
                    if(parent.left == current) {
                        parent.left = current.right;
                    }
                    else {
                        parent.right = current.right;
                    }
                }
            }
            else { // Two Children
                Node nextParent = current;
                Node nextCurrent = current.right;
                while(nextCurrent.left != null) {
                    nextParent = nextCurrent;
                    nextCurrent = nextCurrent.left;
                }
                Long buf = nextCurrent.value;
                delete(buf);
                current.value = buf;

            }


        }
    }

    private void preOrderTraverseToFile(Node node,FileWriter writer) throws IOException {
        if(node != null) {
            writer.write(node.value.toString() + "\n");
            preOrderTraverseToFile(node.left,writer);
            preOrderTraverseToFile(node.right,writer);
        }
    }

    public void preOrderTraverseToFile(FileWriter writer) throws IOException {
        if(root != null) {
            writer.write(root.value.toString() + "\n");
            preOrderTraverseToFile(root.left,writer);
            preOrderTraverseToFile(root.right,writer);
        }
    }

    private void placeHeightTags(Node node) {
        if(node != null) {
           placeHeightTags(node.left);
           placeHeightTags(node.right);
           // 0 children
           if(node.left == null && node.right == null) {
               node.height = Long.valueOf(0);
           }

           else if(node.left != null) {
               // 1 child left
               if(node.right == null) {
                   node.height = node.left.height + 1;
               }
               else {
                   // 2 children
                   node.height = Math.max(node.left.height,node.right.height) + 1;
               }
           }
           else {
               // 1 child right
               node.height = node.right.height + 1;
           }

        }
    }

    public void placeHeightTags () {
        if(root != null) {
            placeHeightTags(root.left);
            placeHeightTags(root.right);
            // 0 children
            if(root.left == null && root.right == null) {
                root.height = Long.valueOf(0);
            }

            else if(root.left != null) {
                // 1 child left
                if(root.right == null) {
                    root.height = root.left.height + 1;
                }
                else {
                    // 2 children
                    root.height = Math.max(root.left.height,root.right.height) + 1;
                }
            }
            else {
                // 1 child right
                root.height = root.right.height + 1;
            }

        }
    }

    public static void main(String[] args) {
        new Thread(null, new Tree(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        boolean isFirstTime = true;
        Tree tree = null;
        File file = new File("tst.in");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                if(isFirstTime) {
                    isFirstTime = false;
                    long temp = scanner.nextLong();
                    tree = new Tree(temp);
                }
                else {
                    long temp = scanner.nextLong();
                    tree.insert(temp);
                }

            }

        }
        catch (NullPointerException e) {
            System.out.println("Oooops");
        }
        catch (NoSuchElementException e) {
            System.out.println("Double Oooops");
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found");

        }
        try {
            tree.placeHeightTags();
            tree.preOrderTraverse(tree.root);
            if(tree.isDeleteRequired && (tree.maxLength + 1) % 2 != 0) {
                tree.delete(defineMedium(tree.maxWayLeftNode,tree.maxWayRoot,tree.maxWayRightNode));
            }

            FileWriter writer = new FileWriter("tst.out");
            tree.preOrderTraverseToFile(writer);
            writer.close();

        } catch (IOException e) {
            System.out.println("File Not Found");
        } catch (NullPointerException e) {
            System.out.println("Something Went Wrong");
        }

    }

}
