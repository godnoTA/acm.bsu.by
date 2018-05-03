
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Matr {

            public static void main(String[] args){

                Boolean realgraf = true;
                Boolean realmulgraf = true;
                Boolean[][] realmatr = new Boolean[0][0];


                    try(BufferedReader br = new BufferedReader(new FileReader("input.txt"))){
                        String str = br.readLine();
                        StringTokenizer st = new StringTokenizer(str, " ");
                        int raz = Integer.parseInt(st.nextToken());
                        int mn = Integer.parseInt(st.nextToken());
                        realmatr = new Boolean[raz][raz];
                        for (int i = 0; i < raz; ++i)
                        {
                            for (int j = 0; j < raz; ++j)
                            {
                                realmatr[i][j] = false;
                            }
                        }
                        for(int i = 0; i < mn; ++i){
                            str = br.readLine();
                            st = new StringTokenizer(str, " ");
                            int x = Integer.parseInt(st.nextToken());
                            int y = Integer.parseInt(st.nextToken());
                            if (x == y)
                            {
                                realgraf = false;
                                realmulgraf = false;
                                break;
                            }
                            if (realmatr[x - 1][y - 1])
                            {
                                realgraf = false;
                            }
                            else
                            {
                                realmatr[x - 1][y - 1] = true;
                                realmatr[y - 1][x - 1] = true;
                            }
                        }
                        BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
                        if(realgraf){
                            bw.write("Yes");
                        }
                        else{
                            bw.write("No");
                        }
                        bw.write(System.getProperty("line.separator"));
                        if(realmulgraf){
                            bw.write("Yes");
                        }
                        else{
                            bw.write("No");
                        }
                        bw.write(System.getProperty("line.separator"));
                        bw.write("Yes");
                        br.close();
                        bw.close();
                    }
                    catch(Exception e){

                    }
                }

}
