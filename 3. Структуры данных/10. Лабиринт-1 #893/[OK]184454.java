import javafx.util.Pair;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

public class Labirynt implements Runnable{
    static int N;
    static int M;
    static int[][] labirynt;
    static boolean flag = false;
    static Scanner in;
    static PrintWriter out;
    static Direction UP = new Direction("UP", -1, 0);
    static Direction DOWN = new Direction("DOWN", 1, 0);
    static Direction RIGHT = new Direction("RIGHT", 0, 1);
    static Direction LEFT = new Direction("LEFT", 0, -1);


    public static void main(String[] args)  {
        new Thread(null, new Labirynt(), "", 64*1024*1024).start();
    }

    public void run() {
        try
        {
            in = new Scanner(new File("in.txt"));
            out = new PrintWriter(new File("out.txt"));
        }
        catch(FileNotFoundException exception)
        {
        }
        boolean answer = true;
        N = in.nextInt();
        M = in.nextInt();
        labirynt = new int[N][M];

        for(int i = 0; i < N; i++)
        {
            String str = in.next();
            for(int j = 0; j < M; j++)
            {
                if( str.charAt(j)=='0')
                    labirynt[i][j] = 0;
                else
                    labirynt[i][j] = 1;
            }
        }

        for (int i = 0; i < M; i++)
        {
            if (labirynt[0][i] == 0)
            {
                findRoute(i);
                answer = flag;
                if(!answer)
                    break;
            }
        }

        if(answer)
        {
            out.print("Possible");
        }
        else
        {
            out.print("Impossible");
        }
        out.close();
    }

    static boolean search(Stack<Pair<Integer, Integer>> route, Direction direction)
    {
        Pair<Integer, Integer> npos = new Pair<>(direction.x + route.peek().getKey(), direction.y + route.peek().getValue());
        if (npos.getKey() < 0 || npos.getKey() >= N || npos.getValue() < 0 ||
                npos.getValue() >= M || labirynt[npos.getKey()][npos.getValue()] == 1)
            return false;
        else
        {
            route.push(npos);
            labirynt[npos.getKey()][npos.getValue()] = 1;
            if (npos.getKey() == N - 1)
                return true;
            else
            {
                Direction[] directions = {};
                switch (direction.direction)
                {
                    case "DOWN":
                        directions = new Direction[]{ LEFT, DOWN,  RIGHT };
                        break;
                    case "LEFT":
                        directions = new Direction[]{ UP, LEFT, DOWN };
                        break;
                    case "RIGHT":
                        directions = new Direction[]{ DOWN, RIGHT,  UP };
                        break;
                    case "UP":
                        directions = new Direction[]{ RIGHT, UP, LEFT };
                        break;
                }
                boolean found = true;
                for (int i = 0; i < 3; i++)
                {
                    found = search(route, directions[i]);
                    if (found)
                        return true;
                }
                labirynt[npos.getKey()][npos.getValue()] = 1;
                route.pop();
                return false;
            }
        }
    }


    static void findRoute(int begin)
    {
        Stack<Pair<Integer, Integer>> route = new Stack<>();
        route.push(new Pair<>(0, begin));
        flag = search(route, DOWN);
    }

}

class Direction{
    String direction;
    int x;
    int y;

    public Direction(String direction, int x, int y) {
        this.direction = direction;
        this.x = x;
        this.y = y;
    }
}