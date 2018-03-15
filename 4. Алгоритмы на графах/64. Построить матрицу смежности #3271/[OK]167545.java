import javafx.util.Pair;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Work {
    public static void main(String[] args){
        try {
            File file = new File("input.txt");

            FileReader reader = new FileReader(file);
            BufferedReader in = new BufferedReader(reader);
            String string;
            string = in.readLine();
            int amountNode;
            String[] element = string.split(" ");
            amountNode = Integer.parseInt(element[0]);
            ArrayList <Pair<Integer,Integer> > arrayRebe = new ArrayList<>();
            int first, second;
            while ((string = in.readLine()) != null){
                element = string.split(" ");
                first = Integer.parseInt(element[0]);
                second = Integer.parseInt(element[1]);
                arrayRebe.add(new Pair<>(first, second));
            }
            in.close();
            PrintWriter writer = new PrintWriter(new File("output.txt"));
            int[][] masResult = new int[amountNode][amountNode];
            for (Pair p: arrayRebe) {
                masResult[(int)p.getValue()-1][(int)p.getKey()-1] = 1;
                masResult[(int)p.getKey()-1][(int)p.getValue()-1] = 1;
            }
            for (int i = 0; i < amountNode; i++){
                for (int j = 0; j < amountNode; j++){
                    if (j!=amountNode-1){
                        writer.print(masResult[i][j]+" ");
                    }
                    else{
                        writer.print(masResult[i][j]);
                    }
                }
                if(i!=amountNode-1) {
                    writer.println();
                }

            }
//            int size = arrayRebe.size(), length = size + 1;
//            for (int z = 0; z < amountNode; z++) {
//                int j = 1;
//                for (int i = 0; i < size; i++) {
//                    if (z+1 == arrayRebe.get(i).getKey()) {
//                        masResult[0]++;
//                        masResult[j++] = arrayRebe.get(i).getValue();
//                    }
//                    if (z+1 == arrayRebe.get(i).getValue()) {
//                        masResult[0]++;
//                        masResult[j++] = arrayRebe.get(i).getKey();
//                    }
//                }
//                int k;
//                for (k = 0; k < length-1 && masResult[k] != 0; k++){
//                    writer.print(masResult[k] + " ");
//                }
//                if (k == 0 ){
//                    writer.print(0);
//                }
//                if(z != amountNode - 1) {
//                    writer.println();
//                }
//                Arrays.fill(masResult,0);
//            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
