import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class Main {
    public class Tree
    {
        class Item {
            Item leftSon, rightSon;
            int value;
        }

        Item Root;


        private void goSLeft(PrintWriter out, Item currentItem) {
            if (currentItem != null)
            {
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
                            currentItem.leftSon.value = val;
                            break;
                        }
                    }
                    else if (val > currentItem.value){
                        if (currentItem.rightSon != null)
                            currentItem = currentItem.rightSon;
                        else {
                            currentItem.rightSon = new Item();
                            currentItem.rightSon.value = val;
                            break;
                        }
                    }
                    else
                        break;
                }
            }
        }

    }

    public static void main(String [] args) throws  Exception {
         BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
         Tree tree = new Main().new Tree();
         String string;
         while ((string = reader.readLine()) != null)
             tree.addItem(Integer.parseInt(string));
         reader.close();
         PrintWriter out = new PrintWriter(new File("output.txt").getAbsoluteFile());
         tree.goStraightLeft(out);
         out.close();
    }

}
