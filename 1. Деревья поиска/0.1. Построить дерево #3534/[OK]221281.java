import java.io.*;
import java.util.*;

class List{
    public List parent = null;
    public List leftSon = null;
    public List rightSon = null;
    int value;

    List(int _value){
        value = _value;
    }

    public Boolean hasAllSons(){
        return leftSon != null && rightSon != null;
    }

    public Boolean hasOnlyLeftSon(){
        return leftSon != null && rightSon == null;
    }

    public Boolean hasOnlyRightSon(){
        return rightSon != null && leftSon == null;
    }

    public Boolean hasNoSons(){
        return leftSon == null && rightSon == null;
    }

}

class Wrapper{
    List place = null;
}

class Way{
    public List first = null;
    public List second = null;
    public int size = 0;

    Way(List _first, List _second, int _size){
        first = _first;
        second = _second;
        size = _size;
    }
}

public class Graf implements Runnable {

    public List root = null;

    public Boolean find(int value, Wrapper wrap){
        if(root == null){
            return null;
        }
        List placeFather = null;
        List place = root;
        while(place != null){
            if(place.value == value){
                wrap.place = place;
                return true;
            }
            else if(place.value < value){
                placeFather = place;
                place = place.rightSon;
            }
            else{
                placeFather = place;
                place = place.leftSon;
            }
        }
        wrap.place = placeFather;
        return false;
    }

    public void add(List father, List place){
        if(father.value < place.value){
            place.rightSon = father.rightSon;
            father.rightSon = place;
        }
        else{
            place.leftSon = father.leftSon;
            father.leftSon = place;
        }
        place.parent = father;
    }

    public Boolean addList(List newList){
        Wrapper place = new Wrapper();
        if(root == null){
            root = newList;
        }
        else if(!find(newList.value, place)){
            add(place.place, newList);
            return true;
        }
        else{
            return false;
        }
        return true;
    }

    public Boolean remove(int value){
        Wrapper place = new Wrapper();
        if(!find(value, place)){
            return false;
        }
        else
        {
            if(place.place.hasNoSons()){
                if(place.place == root){
                    root = null;
                }
                else
                if(place.place.parent.value > place.place.value){
                    place.place.parent.leftSon = null;
                    place.place = null;
                }
                else{
                    place.place.parent.rightSon = null;
                    place.place = null;
                }

            }
            else
            if(place.place.hasOnlyLeftSon()){
                if(place.place == root){
                    root = place.place.leftSon;
                }
                else
                if(place.place.parent.value > place.place.value){
                    place.place.parent.leftSon = place.place.leftSon;
                    place.place.leftSon.parent = place.place.parent;
                    place.place = null;
                }
                else{
                    place.place.parent.rightSon = place.place.leftSon;
                    place.place.leftSon.parent = place.place.parent;
                    place.place = null;
                }
            }
            else
            if(place.place.hasOnlyRightSon()) {
                if(place.place == root){
                    root = place.place.rightSon;
                }
                else
                if (place.place.parent.value > place.place.value) {
                    place.place.parent.leftSon = place.place.rightSon;
                    place.place.rightSon.parent = place.place.parent;
                    place.place = null;
                } else {
                    place.place.parent.rightSon = place.place.rightSon;
                    place.place.rightSon.parent = place.place.parent;
                    place.place = null;
                }
            }
            else
            if(place.place.hasAllSons()){
                List rightLeftSon = place.place.rightSon;
                while(rightLeftSon.leftSon != null){
                    rightLeftSon = rightLeftSon.leftSon;
                }
                int val = rightLeftSon.value;
                if(rightLeftSon.rightSon != null){
                    rightLeftSon.rightSon.parent = rightLeftSon.parent;
                    rightLeftSon.parent.leftSon = rightLeftSon.rightSon;
                    rightLeftSon = null;
                }
                else{
                    rightLeftSon.parent.leftSon = null;
                }

                //rightLeftSon.parent.leftSon = rightLeftSon.rightSon;

                place.place.value = val;

            }

        }
        return true;
    }

    static ArrayList<Integer> outputValues = new ArrayList<Integer>();
    static ArrayList<Way> way = new ArrayList<Way>();
    static ArrayList<List> finishLists = new ArrayList<List>();

    public void firstOrder(List root){
        List place = root;
        if(place == null){
            return;
        }
        else{
            outputValues.add(place.value);
            firstOrder(place.leftSon);
            firstOrder(place.rightSon);

        }
    }

    static ArrayList<Integer> store = new ArrayList<Integer>();

    public void firstOrderWrap(List root){
        outputValues.clear();
        firstOrder(root);
        for(Integer i : outputValues){
            System.out.print(i + "\t");
            store.add(i);
        }
        System.out.println();
    }

    public void secondOrder(List root){
        List place = root;
        if(place == null){
            return;
        }
        else{
            secondOrder(place.leftSon);
            outputValues.add(place.value);
            secondOrder(place.rightSon);
        }
    }

    public void secondOrderWrap(List root){
        outputValues.clear();
        secondOrder(root);
        for(Integer i : outputValues){
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    public void thirdOrder(List root){
        List place = root;
        if(place == null){
            return;
        }
        else{
            thirdOrder(place.leftSon);
            thirdOrder(place.rightSon);
            outputValues.add(place.value);

        }
    }

    public void thirdOrderWrap(List root){
        outputValues.clear();
        thirdOrder(root);
        for(Integer i : outputValues){
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    public static int lenghtBetweenLists(List first, List second){
        ArrayList<Integer> firstArray = new ArrayList<Integer>();
        ArrayList<Integer> secondArray = new ArrayList<Integer>();
        List place = first;
        while(place.parent != null){
            firstArray.add(place.value);
            place = place.parent;
        }
        place = second;
        while(place.parent != null){
            secondArray.add(place.value);
            place = place.parent;
        }
        for(int i = 0; i < firstArray.size(); ++i){
            Integer value = firstArray.get(i);
            if(secondArray.contains(value)){
                secondArray.remove(secondArray.indexOf(value));
                firstArray.remove(firstArray.indexOf(value));
                --i;
            }
        }
        for(int i = 0; i < secondArray.size(); ++i){
            Integer value = secondArray.get(i);
            if(firstArray.contains(value)){
                firstArray.remove(firstArray.indexOf(value));
                secondArray.remove(secondArray.indexOf(value));
                --i;
            }
        }
        return firstArray.size() + secondArray.size();
    }

    public static void findFinishLists(List root){

        List place = root;
        if(place == null || place.hasNoSons()){
            if(place != null){
                finishLists.add(place);
            }
            return;
        }
        else{
            findFinishLists(place.leftSon);
            findFinishLists(place.rightSon);
        }
    }

    public int findTheLongestWaysAndReturnSecondValue(){
        findFinishLists(root);
        ///////////////////
        finishLists.add(root);
        ///////////////////
        for(int i = 0; i < finishLists.size(); ++i){
            for(int j = i + 1; j < finishLists.size(); ++j){
                List list1 = finishLists.get(i);
                List list2 = finishLists.get(j);
                int length = lenghtBetweenLists(list1, list2);
                if(way.isEmpty() || way.get(0).size == length){
                    way.add(new Way(list1, list2, length));
                }
                else
                if(way.get(0).size < length){
                    way.clear();
                    way.add(new Way(list1, list2, length));
                }
            }
        }
        ArrayList<Integer> values = new ArrayList<Integer>();
        for(Way w : way){
            if(!values.contains(w.first.value)){
                values.add(w.first.value);
            }
            if(!values.contains(w.second.value)){
                values.add(w.second.value);
            }

        }
        Collections.sort(values);
        return values.get(1);

    }


    public static void main(String[] args){

        new Thread(null, new Graf(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        Graf tree = new Graf();
        /*int[] values = {5, 1, 3, 4, 2, 9, 8, 10, 12, 11, 15};
        for(int i = 0; i < values.length; ++i){
            tree.addList(new List(values[i]));
        }
        tree.firstOrderWrap(tree.root);*/
        /*tree.firstOrderWrap(tree.root);
        tree.remove(12);
        tree.firstOrderWrap(tree.root);
        tree.remove(15);
        tree.firstOrderWrap(tree.root);
        tree.remove(1);*/
        tree.firstOrderWrap(tree.root);
        try(BufferedReader fr = new BufferedReader(new FileReader("input.txt"))){
            while(true){
                String str = fr.readLine();
                tree.addList(new List(Integer.parseInt(str)));
            }
        }
        catch(Exception e){

        }
        /*
        tree.firstOrderWrap(tree.root);
        int value = tree.findTheLongestWaysAndReturnSecondValue();
        tree.remove(value);
        tree.firstOrderWrap(tree.root);*/
        tree.firstOrderWrap(tree.root);

        try(FileWriter fw = new FileWriter(new File("output.txt"))){
            /*fw.write(outputValues.get(0) + "");
            for(int i = 1; i < outputValues.size(); ++i){
                fw.append(System.getProperty("line.separator"));
                fw.write(outputValues.get(i) + "");
            };*/
            fw.write(store.get(0) + "");
            for(int i = 1; i < store.size(); ++i){
                fw.append(System.getProperty("line.separator"));
                fw.write(outputValues.get(i) + "");
            };
        }
        catch(Exception e) {

        }
    }
}