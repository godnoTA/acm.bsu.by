import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Created by Yurii on 28.04.2016.
 */
public class HuffmanAlgorythm {

    private static LinkedList<Long> firstList = new LinkedList<>();
    private static LinkedList<Long> secondList = new LinkedList<>();
    private static long numbOfSymb = 0;
    private static long res = 0;

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        try {
            BufferedReader readData = new BufferedReader(new InputStreamReader(new FileInputStream(new File("huffman.in")), "UTF-8"));

            numbOfSymb = Integer.parseInt(readData.readLine());

            StringTokenizer stringTokenizer = new StringTokenizer(readData.readLine(), " ", false);
            while (stringTokenizer.hasMoreTokens()) {
                firstList.add(Long.parseLong(stringTokenizer.nextToken()));
            }

            while (true) {
                if (firstList.size() == 1 && secondList.isEmpty()) {
                    break;
                }
                if (firstList.isEmpty() && secondList.size() == 1) {
                    break;
                }
                if (firstList.size()==1 && secondList.size() == 1){
                    res+=firstList.poll() + secondList.poll();
                    break;
                }
                if (secondList.isEmpty()) {
                    long tempValue = firstList.poll() + firstList.poll();
                    res += tempValue;
                    if ( firstList.isEmpty() || firstList.peekLast() <= tempValue) {
                        firstList.add(tempValue);
                    } else {
                        secondList.add(tempValue);
                    }
                    continue;
                }
                if (firstList.isEmpty()){
                    long tempValue = secondList.poll() + secondList.poll();
                    res += tempValue;
                    if (secondList.isEmpty() || secondList.peekLast() <= tempValue) {
                        secondList.add(tempValue);
                    } else {
                        firstList.add(tempValue);
                    }
                    continue;
                }
                if (firstList.size() > 1 && secondList.size() == 1){
                    long tempValue = 0;
                    if (firstList.get(1) <= secondList.get(0)){
                        tempValue = firstList.poll()+firstList.poll();
                    }else {
                        tempValue = firstList.poll()+secondList.poll();
                    }
                    if (firstList.isEmpty() || firstList.peekLast() <= tempValue) {
                        firstList.add(tempValue);
                    } else {
                        secondList.add(tempValue);
                    }
                    res+=tempValue;
                    continue;
                }
                if (secondList.size() > 1 && firstList.size() == 1){
                    long tempValue = 0;
                    if (firstList.get(0) <= secondList.get(1)){
                        tempValue = firstList.poll()+secondList.poll();
                    }else {
                        tempValue = secondList.poll()+secondList.poll();
                    }
                    if ( secondList.isEmpty() || secondList.peekLast() <= tempValue) {
                        secondList.add(tempValue);
                    } else {
                        firstList.add(tempValue);
                    }
                    res+=tempValue;
                    continue;
                }
                if (firstList.size()>1 && secondList.size()>1){
                    long tempValue = 0;
                    if (firstList.peekLast() >= secondList.peekLast()){
                        if (firstList.get(1) <= secondList.get(0)){
                            tempValue = firstList.poll()+firstList.poll();
                        }else if (firstList.get(0) > secondList.get(1)) {
                            tempValue = secondList.poll()+secondList.poll();
                        }else {
                            tempValue = firstList.poll() + secondList.poll();
                        }

                        if ( firstList.isEmpty() || firstList.peekLast() <= tempValue) {
                            firstList.add(tempValue);
                        } else {
                            secondList.add(tempValue);
                        }

                    }else {
                        if (secondList.get(1) <= firstList.get(0)){
                            tempValue = secondList.poll()+secondList.poll();
                        }else if (secondList.get(0) > firstList.get(1)) {
                            tempValue = firstList.poll()+firstList.poll();
                        }else {
                            tempValue = secondList.poll() + firstList.poll();
                        }

                        if ( secondList.isEmpty() || secondList.peekLast() <= tempValue) {
                            secondList.add(tempValue);
                        } else {
                            firstList.add(tempValue);
                        }

                    }
                    res+=tempValue;
                }


            }

            long timeSpent = System.currentTimeMillis() - startTime;
            System.out.println("program was carried out -  " + timeSpent + "  - milliseconds ");

            FileWriter writer = new FileWriter("huffman.out", false);
            writer.write(String.valueOf(res));
            writer.flush();

        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

}