import java.io.*;
import java.util.*;

class Kletka{
    int i;
    int j;

    public Kletka(int ii, int jj){
        i = ii;
        j = jj;
    }
}

public class Main {
    public static int minNumOfHorses(int[][]A, int n, int m, int nOB){
        Kletka[] q = new Kletka[n*m-nOB];
        int count = 0;
        int first = 0;
        int numOfHorses = 0;                                    //количество коней
        int currentZeroI = 0;
        int currentZeroJ = 0;
        while(nOB < n*m) {                                      //пока количество занятых клеток меньше количества клеток на поле
            for(int i = currentZeroI; i < n; i++){              //ищем стартовую клетку
                for(int j = 0; j < m; j++){
                    if(A[i][j] == 0){                           //если нашли пустую клетку
                        currentZeroI = i;                       //создаём клетку current; это текущая клетка
                        currentZeroJ = j;
                        q[count] = new Kletka(i,j);             //добавляем её в очередь
                        count++;
                        A[i][j] = -1;                           //в матрице эту клутку помечаем как пройденную
                        nOB++;
                        break;                                  //выходим из цекла
                    }
                }
                if(q[first] != null)                            //если очередь не пуста
                    break;                                      //выходим из цикла
            }
            while (q[count-1] != null) {                        //пока очередь не пустая
                int ii = q[first].i;
                int jj = q[first].j;
                if ((ii-1 >= 0) && (jj-2 >=0) && (A[ii-1][jj-2] == 0)){
                    Kletka next = new Kletka(ii-1, jj-2);
                    q[count] = next;
                    count++;
                    A[next.i][next.j] = -1;
                    nOB++;
                }
                if ((ii-1 >= 0) && (jj+2 < m) && (A[ii-1][jj+2] == 0)){
                    Kletka next = new Kletka(ii-1, jj+2);
                    q[count] = next;
                    count++;
                    A[next.i][next.j] = -1;
                    nOB++;
                }
                if ((ii-2 >= 0) && (jj-1 >=0) && (A[ii-2][jj-1] == 0)){
                    Kletka next = new Kletka(ii-2, jj-1);
                    q[count] = next;
                    count++;
                    A[next.i][next.j] = -1;
                    nOB++;
                }
                if ((ii-2 >= 0) && (jj+1 < m) && (A[ii-2][jj+1] == 0)){
                    Kletka next = new Kletka(ii-2, jj+1);
                    q[count] = next;
                    count++;
                    A[next.i][next.j] = -1;
                    nOB++;
                }
                if ((ii+1 < n) && (jj-2 >=0) && (A[ii+1][jj-2] == 0)){
                    Kletka next = new Kletka(ii+1, jj-2);
                    q[count] = next;
                    count++;
                    A[next.i][next.j] = -1;
                    nOB++;
                }
                if ((ii+1 < n) && (jj+2 < m) && (A[ii+1][jj+2] == 0)){
                    Kletka next = new Kletka(ii+1, jj+2);
                    q[count] = next;
                    count++;
                    A[next.i][next.j] = -1;
                    nOB++;
                }
                if ((ii+2 < n) && (jj-1 >=0) && (A[ii+2][jj-1] == 0)){
                    Kletka next = new Kletka(ii+2, jj-1);
                    q[count] = next;
                    count++;
                    A[next.i][next.j] = -1;
                    nOB++;
                }
                if ((ii+2 < n) && (jj+1 < m) && (A[ii+2][jj+1] == 0)){
                    Kletka next = new Kletka(ii+2, jj+1);
                    q[count] = next;
                    count++;
                    A[next.i][next.j] = -1;
                    nOB++;
                }
                q[first] = null;                                //после нахождени всех возможных ходов удаляем текущую клетку
                first++;
            }
            numOfHorses++;                                      //увеличиваем число коней
        }
        return numOfHorses;
    }

    public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new FileReader("in.txt"));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] matrix = new int[n][m];
            int numOfBlocked = 0;
            for (int i = 0; i < n; i++) {
                StringTokenizer st1 = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = Integer.parseInt(st1.nextToken());
                    if(matrix[i][j] == -1)
                        numOfBlocked++;
                }
            }

            /*System.out.println(minNumOfHorses(matrix,n,m,numOfBlocked) + "\n");*/
            BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
            bw.write(minNumOfHorses(matrix,n,m,numOfBlocked) + "\n");
            bw.close();

    }
}
