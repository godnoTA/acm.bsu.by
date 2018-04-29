import java.io.*;
import java.util.*;

class List{
    public List parent = null;
    public List lftSon = null;
    public List rghtSon = null;
    int value;

    List(int _value){
        value = _value;
    }

    public Boolean hasAllSons(){
        return lftSon != null && rghtSon != null;
    }

    public Boolean hasOnlylftSon(){
        return lftSon != null && rghtSon == null;
    }

    public Boolean hasOnlyrghtSon(){
        return rghtSon != null && lftSon == null;
    }

    public Boolean hasNoSons(){
        return lftSon == null && rghtSon == null;
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

public class del implements Runnable{

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
                place = place.rghtSon;
            }
            else{
                placeFather = place;
                place = place.lftSon;
            }
        }
        wrap.place = placeFather;
        return false;
    }

    public void add(List father, List place){
        if(father.value < place.value){
            place.rghtSon = father.rghtSon;
            father.rghtSon = place;
        }
        else{
            place.lftSon = father.lftSon;
            father.lftSon = place;
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
                    place.place.parent.lftSon = null;
                    place.place = null;
                }
                else{
                    place.place.parent.rghtSon = null;
                    place.place = null;
                }

            }
            else
            if(place.place.hasOnlylftSon()){
                if(place.place == root){
                    root = place.place.lftSon;
                }
                else
                if(place.place.parent.value > place.place.value){
                    place.place.parent.lftSon = place.place.lftSon;
                    place.place.lftSon.parent = place.place.parent;
                    place.place = null;
                }
                else{
                    place.place.parent.rghtSon = place.place.lftSon;
                    place.place.lftSon.parent = place.place.parent;
                    place.place = null;
                }
            }
            else
            if(place.place.hasOnlyrghtSon()) {
                if(place.place == root){
                    root = place.place.rghtSon;
                }
                else
                if (place.place.parent.value > place.place.value) {
                    place.place.parent.lftSon = place.place.rghtSon;
                    place.place.rghtSon.parent = place.place.parent;
                    place.place = null;
                } else {
                    place.place.parent.rghtSon = place.place.rghtSon;
                    place.place.rghtSon.parent = place.place.parent;
                    place.place = null;
                }
            }
            else
            if(place.place.hasAllSons()){
                List rightlftSon = place.place.rghtSon;
                while(rightlftSon.lftSon != null){
                    rightlftSon = rightlftSon.lftSon;
                }
                int val = rightlftSon.value;
                if(rightlftSon.rghtSon != null){
                    if(rightlftSon.parent.value < rightlftSon.value){
                        rightlftSon.parent.rghtSon = rightlftSon.rghtSon;
                        rightlftSon.rghtSon.parent = rightlftSon.parent;
                    }
                    else{
                        rightlftSon.parent.lftSon = rightlftSon.rghtSon;
                        rightlftSon.rghtSon.parent = rightlftSon.parent;
                    }
                }
                else{
                    if(rightlftSon.parent.value < rightlftSon.value){
                        rightlftSon.parent.rghtSon = null;
                    }
                    else{
                        rightlftSon.parent.lftSon = null;
                    }
                }

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
            firstOrder(place.lftSon);
            firstOrder(place.rghtSon);

        }
    }

    static ArrayList<Integer> store = new ArrayList<Integer>();

    public void firstOrderWrap(List root){
        outputValues.clear();
        firstOrder(root);
        for(Integer i : outputValues){
           
            store.add(i);
        }

    }
    public static void main(String[] args){
        new Thread(null, new del(), "", 64 * 1024 * 1024).start();

    }

    @Override
    public void run() {
        del tree = new del();
        tree.firstOrderWrap(tree.root);

        int keyValue = 0;
        try(BufferedReader fr = new BufferedReader(new FileReader("input.txt"))){
            String key = fr.readLine();
            keyValue = Integer.parseInt(key);
            key = fr.readLine();
            while(true){
                String str = fr.readLine();
                tree.addList(new List(Integer.parseInt(str)));
            }
        }
        catch(Exception e){

        }
        tree.remove(keyValue);
        tree.firstOrderWrap(tree.root);

        try(FileWriter fw = new FileWriter(new File("output.txt"))){

            fw.write(store.get(0) + "");
            for(int i = 1; i < store.size(); ++i){
                fw.append(System.getProperty("line.separator"));
                fw.write(store.get(i) + "");
            };
        }
        catch(Exception e) {

        }
    }
}