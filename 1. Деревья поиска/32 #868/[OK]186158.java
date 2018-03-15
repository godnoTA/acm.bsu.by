import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.TreeSet;
import java.util.Set;

public class Main {
    public class Tree  {

        class Item implements Comparable<Item> {
            Item leftSon, rightSon, father;
            int value, leftListsDeep = 0, rightListsDeep = 0, halfWay = 0;
            int leftDepth, rightDepth, depth;
            @Override
            public int compareTo(Item o) {
                return Integer.compare(value, o.value);
            }
        }


        Item Root;
        private int maxHalfWay;

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


        private void addItem(int val, int dept, Item currentItem) {
            if (val < currentItem.value) {
                if (currentItem.leftSon != null) {
                    addItem(val, dept + 1, currentItem.leftSon);
                    if (currentItem.leftSon.leftDepth > currentItem.leftSon.rightDepth) {
                        currentItem.leftDepth = currentItem.leftSon.leftDepth;
                        currentItem.leftListsDeep = currentItem.leftSon.leftListsDeep;
                    }
                    else if (currentItem.leftSon.leftDepth < currentItem.leftSon.rightDepth) {
                        currentItem.leftDepth = currentItem.leftSon.rightDepth;
                        currentItem.leftListsDeep = currentItem.leftSon.rightListsDeep;
                    }
                    else if (currentItem.leftSon.leftDepth == currentItem.leftSon.rightDepth) {
                        currentItem.leftListsDeep = currentItem.leftSon.leftListsDeep + currentItem.leftSon.rightListsDeep;
                        currentItem.leftDepth = currentItem.leftSon.rightDepth;
                    }

                }
                else {
                    currentItem.leftSon = new Item();
                    currentItem.leftSon.father = currentItem;
                    currentItem.leftSon.value = val;
                    currentItem.leftListsDeep = 1;
                    currentItem.leftDepth = dept;
                    currentItem.leftSon.depth = dept;
                }
            }
            else if (val > currentItem.value) {
                if (currentItem.rightSon != null) {
                    addItem(val, dept + 1, currentItem.rightSon);
                    if (currentItem.rightSon.leftDepth > currentItem.rightSon.rightDepth) {
                        currentItem.rightDepth = currentItem.rightSon.leftDepth;
                        currentItem.rightListsDeep = currentItem.rightSon.leftListsDeep;
                    }
                    else if (currentItem.rightSon.leftDepth < currentItem.rightSon.rightDepth){
                        currentItem.rightDepth = currentItem.rightSon.rightDepth;
                        currentItem.rightListsDeep = currentItem.rightSon.rightListsDeep;
                    }
                    else if (currentItem.rightSon.leftDepth == currentItem.rightSon.rightDepth)  {
                        currentItem.rightListsDeep = currentItem.rightSon.leftListsDeep + currentItem.rightSon.rightListsDeep;
                        currentItem.rightDepth = currentItem.rightSon.leftDepth;
                    }

                }
                else {
                    currentItem.rightSon = new Item();
                    currentItem.rightSon.father = currentItem;
                    currentItem.rightSon.value = val;
                    currentItem.rightListsDeep = 1;
                    currentItem.rightDepth = dept;
                    currentItem.rightSon.depth = dept;
                }
            }
        }


        public void addItem(int val) {
            if (Root == null) {
                Root = new Item();
                Root.value = val;
                Root.depth = 0;
            }
            else {
                addItem(val, 1, Root);
            }
        }


        private void searhMaxHalfWay(Item currentItem)  {
            int halfW = currentItem.leftDepth + currentItem.rightDepth - 2 * currentItem.depth;
            if (halfW > maxHalfWay)
                maxHalfWay = halfW;
            if (currentItem.leftSon != null)
                searhMaxHalfWay(currentItem.leftSon);
            if (currentItem.rightSon != null)
                searhMaxHalfWay(currentItem.rightSon);
        }


        private void search(Item currentItem, Set<Item> itemSet, int fromUp, int lenFromUp) {
            if (currentItem.rightSon == null && currentItem.leftSon == null)
                currentItem.halfWay += fromUp;
            else
            {
                if (currentItem.leftDepth + currentItem.rightDepth - 2 * currentItem.depth == maxHalfWay) {
                    currentItem.halfWay += currentItem.leftListsDeep * currentItem.rightListsDeep;
                    if (currentItem.leftSon != null) {
                        if (currentItem.rightListsDeep == 0) {
                            currentItem.halfWay += currentItem.leftListsDeep;
                            search(currentItem.leftSon, itemSet, 1, 1);
                        }
                        else if (currentItem.leftDepth -  currentItem.depth + lenFromUp == maxHalfWay) {
                            currentItem.halfWay += fromUp * currentItem.leftListsDeep;
                            search(currentItem.leftSon, itemSet, currentItem.rightListsDeep + fromUp, lenFromUp + 1);
                        }
                        else
                            search(currentItem.leftSon, itemSet, currentItem.rightListsDeep, currentItem.rightDepth - currentItem.depth + 1);

                    }
                    if (currentItem.rightSon != null) {
                        if (currentItem.leftListsDeep == 0) {
                            currentItem.halfWay += currentItem.rightListsDeep;
                            search(currentItem.rightSon, itemSet, 1, 1);
                        }
                        else if (currentItem.rightDepth - currentItem.depth + lenFromUp  == maxHalfWay){
                            currentItem.halfWay += fromUp * currentItem.rightListsDeep;
                            search(currentItem.rightSon, itemSet, currentItem.leftListsDeep + fromUp, lenFromUp + 1);
                        }
                        else
                            search(currentItem.rightSon, itemSet, currentItem.leftListsDeep, currentItem.leftDepth - currentItem.depth + 1);
                    }
                }
                else if (currentItem.rightDepth - currentItem.depth + lenFromUp == maxHalfWay ||currentItem.leftDepth - currentItem.depth + lenFromUp == maxHalfWay ){
                    if (currentItem.rightDepth - currentItem.depth + lenFromUp == maxHalfWay) {
                        currentItem.halfWay += fromUp * currentItem.rightListsDeep;
                        search(currentItem.rightSon, itemSet, fromUp, lenFromUp + 1);
                    }
                    if (currentItem.leftDepth - currentItem.depth + lenFromUp == maxHalfWay) {
                        currentItem.halfWay += fromUp * currentItem.leftListsDeep;
                        search(currentItem.leftSon, itemSet, fromUp, lenFromUp + 1);
                    }
                }
                else
                {
                    if (currentItem.leftSon != null)
                        search(currentItem.leftSon, itemSet, 0,0);
                    if (currentItem.rightSon != null)
                        search(currentItem.rightSon, itemSet, 0, 0);
                }
            }
            if (currentItem.halfWay != 0 && (currentItem.halfWay & 1) == 0)
                itemSet.add(currentItem);
        }


        public void searchDelete() {
            Set <Item> itemSet = new TreeSet<>();
            searhMaxHalfWay(Root);
            Item currentItem = Root;

            search(Root, itemSet, 0, 0);
            if ((itemSet.size() & 1 ) != 0) {
                int num = (itemSet.size()/2);
                Item [] myItems = itemSet.toArray(new Item [itemSet.size()]);
                delete(myItems[num]);
            }
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


        public void delete(Item currentItem) {
            if (currentItem != null)
            {
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

    }

    public static void main(String [] args) throws  Exception {
        BufferedReader reader = new BufferedReader(new FileReader("tst.in"));
        Tree tree = new Main().new Tree();
        String string;


        boolean flag = false;
        while ((string = reader.readLine()) != null) {
            int num = Integer.parseInt(string);
            tree.addItem(num);
        }

        reader.close();
        tree.searchDelete();
        PrintWriter out = new PrintWriter(new File("tst.out").getAbsoluteFile());


        tree.goStraightLeft(out);
        out.close();
    }
}
