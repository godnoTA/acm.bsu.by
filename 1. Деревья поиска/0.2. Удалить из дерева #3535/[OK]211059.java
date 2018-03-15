import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    public static class Tree
    {
        // Класс "узел"
        public class Item
        {
            // info - значение, хранящееся в узле
            // lSon, rSon, father - ссылки на левого, правого сына и отца
            public int info;
            public Item lSon, rSon, father;
            // Конструктор узла
            // <param name="x">значение, хранящееся в узле</param>
            public Item(int x)
            {
                info = x;
                lSon = rSon = father = null;
            }

            public Item(){}
        }
        // ссылка на корень дерева
        public Item root;

        // конструктор дерева

        public Tree()
        {
            root = null;
        }

        // внутренняя процедура поиска
        /// <param name="x">искомое значение</param>
        /// <param name="p">ели найдено - ссылка на соответствующий узел, иначе - ссылка на то место, где остановились</param>
        /// <returns>нашли или нет</returns>

        private boolean Find(int x, Item p)
        {
            p = root;
            Item q = p;
            while (q != null)
            {
                p = q;
                if (q.info == x)
                    return true;
                if (q.info < x)
                    q = q.rSon;
                else
                    q = q.lSon;
            }
            return false;
        }

        private Item FindForValue(int x, Item p)
        {
            p = root;
            Item q = p;
            while (q != null)
            {
                p = q;
                if (q.info == x)
                    return p;
                if (q.info < x)
                    q = q.rSon;
                else
                    q = q.lSon;
            }
            return p;
        }

        // внешняя процедура поиска
        // <param name="x">искомое значение</param>
        // <returns>нашли или нет</returns>
        public boolean Find(int x){
            Item p = new Item();
            return Find(x, p);
        }

        // втавка в БПД
        // <param name="x">вставляемое значение</param>
        // <returns>смогли вставить или нет</returns>
        public boolean Insert(int x)
        {
            Item r, p;
            r = new Item();
            if (root == null){
                r = new Item(x);
                root = r;
                return true;
            }

            if (Find(x, r)) {
                return false;
            }

            r = FindForValue(x,r);

            p = new Item(x);
            p.father = r;

            if (r.info < x) {
                r.rSon = p;
            }

            else {
                r.lSon = p;
            }

            return true;
        }

        // удалить вершину (оборвать все ссылки)
        // <param name="x">удаляемая вершина</param>

        public void deleteItem(Item x)
        {
            if (x.father == null) {
                if (x.lSon != null) {
                    root = x.lSon;
                    x.lSon.father = null;
                }
                else {
                    root = x.rSon;
                    if (x.rSon != null)
                        x.rSon.father = null;
                }
            }

            else {
                if (x.father.lSon == x) {
                    if (x.lSon != null) {
                        x.father.lSon = x.lSon;
                        x.lSon.father = x.father;
                    } else {
                        x.father.lSon = x.rSon;
                        if (x.rSon != null)
                            x.rSon.father = x.father;
                    }
                }

                else if (x.lSon != null) {
                    x.father.rSon = x.lSon;
                    x.lSon.father = x.father;
                }
                else {
                    x.father.rSon = x.rSon;
                    if (x.rSon != null)
                        x.rSon.father = x.father;
                }

                x.father = x.lSon = x.rSon = null;
            }
        }

        // Удалить вершину по значению
        // <param name="x">удаляемое значение</param>
        // <returns>смогли удалить или нет</returns>

        public boolean leftDelete(int x)
        {
            Item r, p;
            r = new Item();
            if (!Find(x, r)) {
                return false;
            }
            r = FindForValue(x,r);

            if ((r.lSon == null) && (r.rSon == null)){
                if(r.father.info > r.info){
                    r.father.lSon = null;
                }
                else{
                    r.father.rSon = null;
                }
                return true;
            }

            if(((r.lSon == null)&&(r.rSon!=null))){
                if(r.father!=null) {
                    if (r.father.info > r.info) {
                        r.father.lSon = r.rSon;
                    } else {
                        r.father.rSon = r.rSon;
                    }
                    return true;
                }
                root = r.rSon;
                r.rSon.father = null;
                return true;
            }

            if(((r.lSon != null)&&(r.rSon==null))){
                if(r.father!=null) {
                    if (r.father.info > r.info) {
                        r.father.lSon = r.lSon;
                    } else {
                        r.father.rSon = r.lSon;
                    }
                    return true;
                }
                root = r.lSon;
                r.lSon.father = null;
                return true;
            }

            else{
                Item q = r.lSon;
                int counter = -1;
                while(q.rSon!=null || q.lSon!=null){
                    q = q.rSon;
                    counter++;
                }
                if(counter <0) {
                    r.info = q.info;
                    q.father.lSon = null;
                    return true;
                }
                else{
                    r.info = q.info;
                    q.father.rSon = null;
                    return true;
                }
            }

        }

        public boolean rightDelete(int x)
        {
            Item r, p;
            r = new Item();
            if (!Find(x, r)) {
                return false;
            }
            r = FindForValue(x,r);

            if ((r.lSon == null) && (r.rSon == null)){
                if(r.father.info > r.info){
                    r.father.lSon = null;
                }
                else{
                    r.father.rSon = null;
                }
                return true;
            }

            if(((r.lSon == null)&&(r.rSon!=null))){
                if(r.father!=null) {
                    if (r.father.info > r.info) {
                        r.father.lSon = r.rSon;
                    } else {
                        r.father.rSon = r.rSon;
                    }
                    return true;
                }
                root = r.rSon;
                r.rSon.father = null;
                return true;
            }

            if(((r.lSon != null)&&(r.rSon==null))){
                if(r.father!=null) {
                    if (r.father.info > r.info) {
                        r.father.lSon = r.lSon;
                    } else {
                        r.father.rSon = r.lSon;
                    }
                    return true;
                }
                root = r.lSon;
                r.lSon.father = null;
                return true;
            }

            else{
                Item q = r.rSon;
                int counter = -1;
                while(q.lSon!=null){
                    q = q.lSon;
                    counter++;
                }
                if(counter <0) {
                    r.info = q.info;
                    q.father.rSon = q.rSon;
                    return true;
                }
                else{
                    r.info = q.info;
                    q.father.lSon = q.rSon;
                    return true;
                }
            }

        }
       
    }


    static void left(BufferedWriter secondObjectToOutputData, Tree.Item q )throws Exception{
        if(q!=null) {
            secondObjectToOutputData.write(q.info+"");
            secondObjectToOutputData.newLine();
            left(secondObjectToOutputData, q.lSon);
            //q=q.father;
            left(secondObjectToOutputData, q.rSon);
            // q = q.father;
        }
    }

    public static void main(String[] args) throws Exception{
        Tree treeForCommonTask = new Tree();

        FileInputStream streamToInputData = new FileInputStream("input.txt");
        InputStreamReader objectToInputData = new InputStreamReader(streamToInputData);
        BufferedReader secondObjectToInputData = new BufferedReader(objectToInputData);

        FileOutputStream streamTpOutputData = new FileOutputStream("output.txt");
        OutputStreamWriter objectToOutputData = new OutputStreamWriter(streamTpOutputData);
        BufferedWriter secondObjectToOutputData = new BufferedWriter(objectToOutputData);

        String buff = secondObjectToInputData.readLine();
        int number = Integer.parseInt(buff);

        secondObjectToInputData.readLine();
        buff = secondObjectToInputData.readLine();


        while(buff != null){
            int numberOne = Integer.parseInt(buff);
            treeForCommonTask.Insert(numberOne);
            buff = secondObjectToInputData.readLine();
        }

        treeForCommonTask.rightDelete(number);

        left(secondObjectToOutputData, treeForCommonTask.root);

        secondObjectToInputData.close();
        objectToInputData.close();

        secondObjectToOutputData.close();
        objectToOutputData.close();
    }
}
