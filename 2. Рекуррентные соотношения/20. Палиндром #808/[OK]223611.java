import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String line =  reader.readLine();
        int size = line.length()+1;
        StringBuffer sb = new StringBuffer(line);
        String rl = sb.reverse().toString();
        int matrix[][] = new int[size][size];
        PrintWriter writer = new PrintWriter("output.txt");
        if (size-1 == 1) {
            writer.write(1 + "\n");
            writer.write(line);
        } else if (size-1 == 0) {
            writer.write(0 + "\n");
        } else if (size-1 == 2) {
            if (line.charAt(0) != line.charAt(1)) {
                writer.write(1 + "\n");
                writer.write(line.charAt(0));
            } else {
                writer.write(2 + "\n");
                writer.write(line.charAt(0));
                writer.write(line.charAt(1));
            }
        } else {
            for(int i = 1; i<size; i++){
                for(int j =1; j<size;j++){
                    if(line.charAt(i-1)== rl.charAt(j-1)){
                        matrix[i][j] = matrix[i-1][j-1]+1;
                    }
                    else {
                        matrix[i][j]=Math.max(matrix[i][j-1],matrix[i-1][j]);
                    }
                }
            }
            writer.write(matrix[size-1][size - 1] + "\n");
            int i = size-1;
            int j = size-1;
            while(matrix[i][j]>0){
                while(matrix[i][j]==matrix[i][j-1])
                    j--;
                if(line.charAt(i-1)==rl.charAt(j-1)){
                    writer.print(line.charAt(i-1));
                    j--;
                    i--;
                }
                else{
                    i--;
                }
            }
        }
        writer.close();
    }
}