import java.io.*;
import java.text.DecimalFormat;

class Disks{
    int n;
    double boxLength;
    Disk[] arr;

    public Disks(int num){
        boxLength = 0;
        n = num;
        arr = new Disk[n];
    }

    class Disk{
        double radius;              //радиус
        double X;                   //координата центра на оси абсцис

        public Disk(double rad){
            radius = rad;
            X = 0;
        }
    }

    public void fillArr(BufferedReader br){
        try{
            for(int i = 0; i < n; i ++)
                arr[i] = new Disk(Double.parseDouble(br.readLine()));
        } catch(IOException e){}
    }


    public void searchBoxLength(){
        arr[0].X = arr[0].radius;                   //координата первого диска равна его радиусу (первый диск касается стенки коробки)
        for(int i = 1; i < arr.length; i++){        //цикл для определения координат последующих дисков
            double max = arr[i].radius;             //переменная для хранения максимальной координаты текущего диска; предположим, текущий диск касается стены коробки
            for(int j = 0; j < i; j++){             //цикл для нахождения максимальной координаты в заисимости от того, какого из предыдущих дисков, касается текущий диск
                if(2*Math.sqrt(arr[i].radius*arr[j].radius) + arr[j].X> max)
                    max = 2*Math.sqrt(arr[i].radius*arr[j].radius) + arr[j].X;
            }
            arr[i].X = max;
        }
        boxLength = arr[n-1].X + arr[n-1].radius;
    }

    public void printBoxLength(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
            String format = new DecimalFormat("#0.00000").format(boxLength);
            String formatedBoxLength = new String(new StringBuffer(format.replace(',', '.')));
            bw.write(formatedBoxLength + "\n");
            bw.close();
        }
        catch(IOException e){}
    }
}

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("in.txt"));
            Disks disks = new Disks(Integer.parseInt(br.readLine()));
            if(disks.n == 0){
                BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
                bw.write("0.00000" + "\n");
                bw.close();
                return;
            }
            disks.fillArr(br);
            disks.searchBoxLength();
            disks.printBoxLength();

        }
        catch(IOException e){}
    }
}
