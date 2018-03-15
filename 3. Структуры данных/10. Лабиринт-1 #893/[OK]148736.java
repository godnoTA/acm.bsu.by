import java.io.*;
import java.util.Stack;
/**
 * Created by Alexander on 03/05/2016.
 */
public class Runner {
    public static class Field {
        public int side;
        public int visit;
        public int wall;
        public int line;
        public int colomn;

        public Field(Field field) {
            side = field.side;
            visit = field.visit;
            wall = field.wall;
            line = field.line;
            colomn = field.colomn;
        }

        void setVisit(int vt) {
            visit = vt;
        }

        public Field() {
        }
    }

    public static int N;
    public static int M;

    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("out.txt"));
        BufferedReader bf = new BufferedReader(new FileReader(new File("in.txt")));
        int c;
        int tm;
        int[] a = new int[2];
        int b = 1;
//********************************************************************************************************************************************
        for (int i = 0; i < 2; i++) {
            while ((c = Character.getNumericValue(bf.read())) != -1) {
                a[i] = a[i] * b + c;
                b = 10;
            }
            b = 1;
        }
        N = a[0];
        M = a[1];
        Character.getNumericValue(bf.read());
        Field[][] labth = new Field[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                labth[i][j] = new Field();
                tm = Character.getNumericValue(bf.read());

                labth[i][j].wall = tm;
                labth[i][j].line = i;
                labth[i][j].colomn = j;
            }
            Character.getNumericValue(bf.read());
            Character.getNumericValue(bf.read());
        }

//*************************************************************************************************************************************

 //*******************************************************************************************************************************
        for (int j = 0; j < M; j++) {
            if (labth[0][j].wall == 1) continue;
            Field tmp = new Field();
            tmp = labth[0][j];

            Stack<Field> stk = new Stack<>();
            int m = stk.capacity();
            stk.push(tmp);
            while (stk.peek().line != (N - 1)) {
                tmp = stk.pop();
                //if(tmp.visit == 1) continue;
                tmp.visit = 1;
                if (tmp.side == 1) {
                    preUp(tmp, labth, stk);
                }
                else if(tmp.side == 3)
                    preDown(tmp, labth, stk);
                else if(tmp.side == 4)
                    preLeft(tmp, labth, stk);
                else if(tmp.side == 2)
                    preRight(tmp, labth, stk);
                else if(tmp.side == 0)
                    if(labth[tmp.line + 1][tmp.colomn].visit == 0 && labth[tmp.line + 1][tmp.colomn].wall != 1){
                        labth[tmp.line + 1][tmp.colomn].side = 1;
                        stk.push(labth[tmp.line + 1][tmp.colomn]);
                    }

                if(stk.isEmpty()){
                    pw.println("Impossible");
                    pw.flush();
                    return;
                }

            }

        }
        pw.println("Possible");
        pw.flush();
        System.exit(0);

    }

    //********************************************************************************************************************************************
    static void preUp(Field tm, Field[][] fld, Stack<Field> st) {
        if (tm.colomn + 1 < M && fld[tm.line][tm.colomn + 1].wall != 1 && fld[tm.line][tm.colomn + 1].visit != 1) {//3
            fld[tm.line][tm.colomn + 1].side = 4;
            st.push(fld[tm.line][tm.colomn + 1]);
        }
        if (tm.line + 1 < N && fld[tm.line + 1][tm.colomn].wall != 1 && fld[tm.line + 1][tm.colomn].visit != 1) { //2
            fld[tm.line + 1][tm.colomn].side = 1;
            st.push(fld[tm.line + 1][tm.colomn]);
        }
        if (tm.colomn - 1 >= 0 && fld[tm.line][tm.colomn - 1].wall != 1 && fld[tm.line][tm.colomn - 1].visit != 1) { //1
            fld[tm.line][tm.colomn - 1].side = 2;
            st.push(fld[tm.line][tm.colomn - 1]);
        }
    }

    static void preDown(Field tm, Field[][] fld, Stack<Field> st) {
        if (tm.colomn - 1 >= 0 && fld[tm.line][tm.colomn - 1].wall != 1 && fld[tm.line][tm.colomn - 1].visit != 1) {  //3
            fld[tm.line][tm.colomn - 1].side = 2;
            st.push(fld[tm.line][tm.colomn - 1]);
        }
        if (tm.line - 1 >= 0 && fld[tm.line - 1][tm.colomn].wall != 1 && fld[tm.line - 1][tm.colomn].visit != 1) {  //2
            fld[tm.line - 1][tm.colomn].side = 3;
            st.push(fld[tm.line - 1][tm.colomn]);
        }
        if (tm.colomn + 1 < M && fld[tm.line][tm.colomn + 1].wall != 1 && fld[tm.line][tm.colomn + 1].visit != 1) {  //1
            fld[tm.line][tm.colomn + 1].side = 4;
            st.push(fld[tm.line][tm.colomn + 1]);

        }
    }

    static void preLeft(Field tm, Field[][] fld, Stack<Field> st) {
        if (tm.line - 1 >= 0 && fld[tm.line - 1][tm.colomn].wall != 1 && fld[tm.line - 1][tm.colomn].visit != 1) { //3
            fld[tm.line - 1][tm.colomn].side = 3;
            st.push(fld[tm.line - 1][tm.colomn]);
        }
        if (tm.colomn + 1 < M && fld[tm.line][tm.colomn + 1].wall != 1 && fld[tm.line][tm.colomn + 1].visit != 1) { //2
            fld[tm.line][tm.colomn + 1].side = 4;
            st.push(fld[tm.line][tm.colomn + 1]);
        }
        if (tm.line + 1 < N && fld[tm.line + 1][tm.colomn].wall != 1 && fld[tm.line + 1][tm.colomn].visit != 1) { //1
            fld[tm.line + 1][tm.colomn].side = 1;
            st.push(fld[tm.line + 1][tm.colomn]);
        }
    }

    static void preRight(Field tm, Field[][] fld, Stack<Field> st) {
        if (tm.line + 1 < N && fld[tm.line + 1][tm.colomn].wall != 1 && fld[tm.line + 1][tm.colomn].visit != 1) { //3
            fld[tm.line + 1][tm.colomn].side = 1;
            st.push(fld[tm.line + 1][tm.colomn]);
        }
        if (tm.colomn - 1 >= 0 && fld[tm.line][tm.colomn - 1].wall != 1 && fld[tm.line][tm.colomn - 1].visit != 1) { //2
            fld[tm.line][tm.colomn - 1].side = 2;
            st.push(fld[tm.line][tm.colomn - 1]);
        }
        if (tm.line - 1 >= 0 && fld[tm.line - 1][tm.colomn].wall != 1 && fld[tm.line - 1][tm.colomn].visit != 1) { //1
            fld[tm.line - 1][tm.colomn].side = 3;
            st.push(fld[tm.line - 1][tm.colomn]);
        }
    }

}