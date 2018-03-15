import java.io.*;

class Root {

    int next;
    int prev;

    public Root() {
        next = 0;
        prev = 0;
    }

    public Root(int prev, int next) {
        this.next = next;
        this.prev = prev;
    }

/*    public void setNext(int next){
        this.next = next;
    }

    public void setPrev(int prev){
        this.prev = prev;
    }

    public int getNext(){
        return next;
    }

    public int getPrev(){
        return prev;
    }*/

}

public class Main {

    public static int check(int s, int k, boolean [] US, int [][] V) {
        for (int j = 0; j < k; j++) {
            if (!US[V[s][j]]) {
                US[V[s][j]] = true;
                return V[s][j];
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.in")));

        String line = reader.readLine();
        String[] data = line.split(" ");
        int n = Integer.parseInt(data[0]);
        int k = Integer.parseInt(data[1]);
        int hasBook = Integer.parseInt(data[2]) - 1;

        int [][] ourData = new int [n][k];
        boolean [][] friends = new boolean[n][n];
        boolean [] hasRead = new boolean[n];
        for(int i = 0; i<n;i++){
            hasRead[i] = false;
            for(int j=0;j<n;j++){
                friends[i][j]=false;
            }
        }
        Root [] ans = new Root[n];
        for(int i = 0; i< n; i++){
            ans [i] = new Root();
        }
        for (int i = 0; i < n; i++) {
            line = reader.readLine();
            data = line.split(" ");
            for (int j = 0; j < k; j++) {
                ourData[i][j] = Integer.parseInt(data[j]);
                ourData[i][j]--;
                friends[i][ourData[i][j]] = true;
            }
        }
        hasRead[hasBook] = true;

        int start = hasBook;
        int finish = hasBook;
        ans[hasBook] = new Root(hasBook,hasBook);

        while (true) {
            boolean found = false;

            int next = check(finish, k, hasRead, ourData);

            if (next != -1) {
                ans[finish].next = next;
                ans[next].prev = finish;
                finish = next;
                found = true;
            }

            if (!found) {
                next = check(start, k, hasRead, ourData);

                if (next != -1) {
                    ans[start].prev = next;
                    ans[next].next = start;
                    start = next;
                    found = true;
                }
            }

            if (!found) {
                if (!friends[start][finish]) {
                    int it = ans[start].next;
                    while (ans[it].next != ans[finish].prev) {
                        if (friends[it][finish] && friends[ans[it].next][start]) {
                            int s = ans[it].next;
                            int f = it;
                            ans[finish].next = f;
                            int jt = f;
                            while (jt != start) {
                                ans[jt] = new Root(ans[jt].next, ans[jt].prev);
                                jt = ans[jt].next;
                            }
                            ans[f].prev = finish;
                            ans[start] = new Root(ans[start].next, s);

                            finish = start;
                            start = s;

                            break;
                        }
                        it = ans[it].next;
                    }
                }
                ans[finish].next = start;
                ans[start].prev = finish;

                for (int i = 0; i < n; i++) {
                    if (!hasRead[i])
                        for (int j = 0; j < k; j++)
                            if (hasRead[ourData[i][j]]) {//us[v[i][j]]
                                hasRead[i] = true;
                                ans[i].next = ourData[i][j];
                                start = i;
                                finish = ans[ourData[i][j]].prev;
                                ans[ourData[i][j]].prev = i;
                                found = true;
                                i = n;
                                break;
                            }
                }
            }

            if (friends[start][finish]) {
                ans[finish].next = start;
                ans[start].prev = finish;
            }

            if (!found)
                break;
        }

        File file = new File("output.out");
        PrintWriter writer = new PrintWriter(file);

        writer.print((hasBook + 1) + " ");
        int it = hasBook;

        while (ans[it].next != hasBook) {
            writer.print((ans[it].next + 1) + " ");
            it = ans[it].next;
        }
        writer.print((hasBook + 1) + " ");
        writer.flush();
    }
}
