import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class Main {
    public class Tree
    {
        class Item {
            Item leftSon, rightSon, father;
            int value;
        }


        Item Root;


        private void goSLeft(PrintWriter out, Item currentItem) {
            if (currentItem != null) {
                out.println(currentItem.value);
                goSLeft(out, currentItem.leftSon);
                goSLeft(out, currentItem.rightSon);
            }
        }


        public void goStraightLeft(PrintWriter out) {
            goSLeft(out, this.Root);
        }


        public void addItem(int val) {
            if (Root == null) {
                Root = new Item();
                Root.value = val;
            }
            else {
                Item currentItem = Root;
                while (true)
                {
                    if (val < currentItem.value) {
                        if (currentItem.leftSon != null)
                            currentItem = currentItem.leftSon;
                        else {
                            currentItem.leftSon = new Item();
                            currentItem.leftSon.father = currentItem;
                            currentItem.leftSon.value = val;
                            break;
                        }
                    }
                    else if (val > currentItem.value){
                        if (currentItem.rightSon != null)
                            currentItem = currentItem.rightSon;
                        else {
                            currentItem.rightSon = new Item();
                            currentItem.rightSon.father = currentItem;
                            currentItem.rightSon.value = val;
                            break;
                        }
                    }
                    else
                        break;
                }
            }
        }


        private Item search (int val, Item currentItem) {
            if (currentItem.value == val)
                return currentItem;
            if (val > currentItem.value)
                return search(val, currentItem.rightSon);
            return search(val, currentItem.leftSon);
        }


        public Item minimumRight(Item currentItem) {
            if (currentItem.leftSon != null)
                return minimumRight(currentItem.leftSon);
            return currentItem;
        }


        public void deleteLeaf(Item currentItem) {
            if (currentItem.father == null)
                Root = null;
            else if(currentItem.father.value < currentItem.value)
                currentItem.father.rightSon = null;
            else
                currentItem.father.leftSon = null;
        }


        public void deleteWithRightTree(Item currentItem) {
            if (currentItem.father == null) {
                currentItem.rightSon.father = null;
                Root = currentItem.rightSon;
            }
            else if (currentItem.father.value > currentItem.value) {
                currentItem.rightSon.father = currentItem.father;
                currentItem.father.leftSon = currentItem.rightSon;
            }
            else {
                currentItem.rightSon.father = currentItem.father;
                currentItem.father.rightSon = currentItem.rightSon;
            }
        }


        public void deleteWithLeftTree(Item currentItem){
            if (currentItem.father == null) {
                Root = currentItem.leftSon;
                currentItem.leftSon.father = null;
            }
            else if (currentItem.father.value > currentItem.value) {
                currentItem.leftSon.father = currentItem.father;
                currentItem.father.leftSon = currentItem.leftSon;
            }
            else {
                currentItem.leftSon.father = currentItem.father;
                currentItem.father.rightSon = currentItem.leftSon;
            }
        }


        public void delete(int val) {
            Item currentItem = search(val, Root);
            if (currentItem.leftSon == null && currentItem.rightSon == null)
               deleteLeaf(currentItem);
            else if (currentItem.leftSon == null)
               deleteWithRightTree(currentItem);
            else if (currentItem.rightSon == null)
                deleteWithLeftTree(currentItem);
            else {
                 Item exchange = minimumRight(currentItem.rightSon);
                 if (exchange.leftSon == null && exchange.rightSon == null)
                     deleteLeaf(exchange);
                 else if (exchange.leftSon == null)
                     deleteWithRightTree(exchange);
                 else if (exchange.rightSon == null)
                     deleteWithLeftTree(exchange);
                 currentItem.value = exchange.value;
            }
        }

    }

    public static void main(String [] args) throws  Exception {
         BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
         Tree tree = new Main().new Tree();
         String string = reader.readLine();
         int keyNumber = Integer.parseInt(string);
         string = reader.readLine();
         boolean flag = false;
         while ((string = reader.readLine()) != null) {
             int num = Integer.parseInt(string);
             if (num == keyNumber)
                 flag = true;
             tree.addItem(num);
         }
         reader.close();
         if (flag)
             tree.delete(keyNumber);
         PrintWriter out = new PrintWriter(new File("output.txt").getAbsoluteFile());
         tree.goStraightLeft(out);
         out.close();
    }

}
