import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;

public class Main {

    public static double distance(double rad1, double rad2){
        double dist= Math.sqrt((rad1+rad2)*(rad1+rad2) - (rad1-rad2)*(rad1-rad2));
        return dist;
    }

    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("in.txt"));
        } catch (java.io.FileNotFoundException ex) {
            System.out.println("Файл не найден!");
            return;
        }

        int count = scanner.nextInt();
        double[][] disks = new double[count+1][2];
        int it=0;
        while (scanner.hasNext()){
            disks[it][0]=scanner.nextDouble();
            it++;
        }

        disks[0][1]=disks[0][0];
       for(int i=0; i<count-1; i++){
            double dist = distance(disks[i][0], disks[i+1][0]);
            disks[i+1][1]= disks[i][1]+dist;
            for(int j=i-1; j>-1; j--){
                dist=distance(disks[i+1][0], disks[j][0]);
                if(disks[j][1]+dist>disks[i+1][1]){
                    disks[i+1][1]=disks[j][1]+dist;
                }
            }
       }

       double leftDistMax=disks[0][0]-disks[0][1];
        double rightDistMax=disks[0][0]+disks[0][1];
         for(int i=1; i<count; i++){
             if(leftDistMax<disks[i][0] - disks[i][1])
                 leftDistMax=disks[i][0]-disks[i][1];
         }
        for(int i=1; i<count; i++){
            if(rightDistMax<disks[i][0] + disks[i][1])
                rightDistMax=disks[i][0]+disks[i][1];
        }

        double allDist = leftDistMax+rightDistMax;

        try {
            DecimalFormatSymbols s = new DecimalFormatSymbols();
            s.setDecimalSeparator('.');
            DecimalFormat f = new DecimalFormat("#,##0.00000", s);
            String res = f.format(allDist);
            FileWriter fw = new FileWriter("out.txt");
            fw.write(res);
            fw.close();
        } catch (Exception e) {
        }
    }
}
