import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import static java.lang.Integer.min;


public class Main implements Runnable {

    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    public static int dp[][][][][] = new int[31][31][31][31][16];
    public static boolean existence[][][][][] = new boolean[31][31][31][31][16];
    public static int answer[][] = new int[31][31];

    public static int n, a;
    public static ArrayList<ArrayList<Integer>> horizontals = new ArrayList<>(), verticals = new ArrayList<>();

    //checks if we can draw the line in a certain direction
    public static boolean lineExist(int horiz, int vert, int directionsArray, int direction) {
        if (isClosed(directionsArray, direction))
            return false;
        if (direction == UP) {
            return Integer.compare(verticals.get(vert).get(0), horiz) == 0;
        } else if (direction == DOWN) {
            return Integer.compare(verticals.get(vert).get(verticals.get(vert).size()-1), horiz) == 0;
        } else if (direction == LEFT) {
            return Integer.compare(horizontals.get(horiz).get(0), vert) == 0;
        } else {
            return Integer.compare(horizontals.get(horiz).get(horizontals.get(horiz).size()-1), vert) == 0;
        }
    }

    //finds first value in the array which is greater or equals val
    public static int firstGEqualValue(ArrayList<Integer> arrayList, Integer val) {
        if (arrayList.size() == 0)
            return 9999;
        for (int i = 0; i < arrayList.size(); ++i) {
            if (arrayList.get(i) >= val)
                return i;
        }
        return 9999;

    }

    //finds first value in the array which is greater than val
    public static int firstGreaterValue(ArrayList<Integer> arrayList, Integer val) {
        if (arrayList.size() == 0)
            return 9999;
        for (int i = 0; i < arrayList.size(); ++i) {
            if (arrayList.get(i) > val)
                return i;
        }
        return 9999;

    }

    //closes a certain direction
    public static int closeDirection(int directionsArray, int direction) {
        return directionsArray | (1 << direction);
    }

    //checks if the direction is closed
    public static boolean isClosed(int directionsArray, int direction) {
        return  ((directionsArray >> direction) & 1) != 0;
    }

    //interprets bytes of directionsArray into direction
    public static int directionsArrayToDirection(int directionsArray) {
        return (directionsArray + 3) % 4;
    }

    public static int solution(int left, int top, int right, int bottom, int directionsArray) {
        if (existence[left][top][right][bottom][directionsArray])
            return dp[left][top][right][bottom][directionsArray];

        dp[left][top][right][bottom][directionsArray] = 9999;

        if (right < left || bottom < top) {
            return 0;
        }

        if (directionsArray == 15) {
            return dp[left][top][right][bottom][directionsArray];
        }

        int leftL = firstGEqualValue(horizontals.get(left), top);
        int leftR = firstGreaterValue(horizontals.get(left), bottom);
        if (leftL == leftR) {
            dp[left][top][right][bottom][directionsArray] = solution(left + 1, top, right, bottom, directionsArray);
            existence[left][top][right][bottom][directionsArray] = true;
            return dp[left][top][right][bottom][directionsArray];
        }

        int rightL = firstGEqualValue(horizontals.get(right), top);
        int rightR = firstGreaterValue(horizontals.get(right), bottom);
        if (rightL == rightR) {
            dp[left][top][right][bottom][directionsArray] = solution(left, top, right - 1, bottom, directionsArray);
            existence[left][top][right][bottom][directionsArray] = true;
            return dp[left][top][right][bottom][directionsArray];
        }

        int topT = firstGEqualValue(verticals.get(top), left);
        int topB = firstGreaterValue(verticals.get(top), right);
        if (topT == topB) {
            dp[left][top][right][bottom][directionsArray] = solution(left, top + 1, right, bottom, directionsArray);
            existence[left][top][right][bottom][directionsArray] = true;
            return dp[left][top][right][bottom][directionsArray];
        }
        int bottomT = firstGEqualValue(verticals.get(bottom), left);
        int bottomB = firstGreaterValue(verticals.get(bottom), right);
        if (bottomT == bottomB) {
            dp[left][top][right][bottom][directionsArray] = solution(left, top, right, bottom - 1, directionsArray);
            existence[left][top][right][bottom][directionsArray] = true;
            return dp[left][top][right][bottom][directionsArray];
        }


        if (!isClosed(directionsArray, UP) || !isClosed(directionsArray, DOWN)) {
            boolean upAvailable = true;
            int count = 0;
            for (int i = leftL; i != leftR && i < horizontals.get(left).size(); ++i) {
                int vert = horizontals.get(left).get(i);
                if (lineExist(left, vert, directionsArray, DOWN)) {
                    int result =  solution(left, top, right, vert - 1, closeDirection(directionsArray, RIGHT))
                            + solution(left, vert + 1, right, bottom, closeDirection(directionsArray, LEFT))
                            + a - left;
                    dp[left][top][right][bottom][directionsArray] = min(dp[left][top][right][bottom][directionsArray], result);
                }
                ++count;
                upAvailable &= lineExist(left, vert, directionsArray, UP);
            }
            if (upAvailable) {
                int result = solution(left + 1, top, right, bottom, directionsArray) + left * count;
                dp[left][top][right][bottom][directionsArray] = min(dp[left][top][right][bottom][directionsArray], result);
            }

            boolean downAvailable = true;
            count = 0;
            for (int i = rightL; i != rightR && i < horizontals.get(right).size(); ++i) {
                int vert = horizontals.get(right).get(i);
                if (lineExist(right, vert, directionsArray, UP)) {
                    int result = solution(left, top, right, vert - 1, closeDirection(directionsArray, RIGHT)) +
                            solution(left, vert + 1, right, bottom, closeDirection(directionsArray, LEFT))
                            + right;
                    dp[left][top][right][bottom][directionsArray] = min(dp[left][top][right][bottom][directionsArray], result);
                }
                ++count;
                downAvailable &= lineExist(right, vert, directionsArray, DOWN);
            }
            if (downAvailable) {
                int result = solution(left, top, right - 1, bottom, directionsArray) + (a - right) * count;
                dp[left][top][right][bottom][directionsArray] = min(dp[left][top][right][bottom][directionsArray], result);
            }
        }


        if (!isClosed(directionsArray, LEFT) || !isClosed(directionsArray, RIGHT)) {
            boolean leftAvailable = true;
            int count = 0;
            for (int i = topT; i != topB && i < verticals.get(top).size(); ++i) {
                int horiz = verticals.get(top).get(i);
                if (lineExist(horiz, top, directionsArray, RIGHT)) {
                    int result = solution(left, top, horiz - 1, bottom, closeDirection(directionsArray, DOWN)) +
                            solution(horiz + 1, top, right, bottom, closeDirection(directionsArray, UP)) + a - top;
                    dp[left][top][right][bottom][directionsArray] = min(dp[left][top][right][bottom][directionsArray], result);
                }
                ++count;
                leftAvailable &= lineExist(horiz, top, directionsArray, LEFT);
            }
            if (leftAvailable) {
                int result = solution(left, top + 1, right, bottom, directionsArray) + top * count;
                dp[left][top][right][bottom][directionsArray] = min(dp[left][top][right][bottom][directionsArray], result);
            }


            boolean rightAvailable = true;
            count = 0;
            for (int i = bottomT; i != bottomB && i < verticals.get(bottom).size(); ++i) {
                int horiz = verticals.get(bottom).get(i);
                if (lineExist(horiz, bottom, directionsArray, LEFT)) {
                    int result = solution(left, top, horiz - 1, bottom, closeDirection(directionsArray, DOWN)) +
                            solution(horiz + 1, top, right, bottom, closeDirection(directionsArray, UP)) + bottom;
                    dp[left][top][right][bottom][directionsArray] = min(dp[left][top][right][bottom][directionsArray], result);
                }
                ++count;
                rightAvailable &= lineExist(horiz, bottom, directionsArray, RIGHT);
            }
            if (rightAvailable) {
                int result = solution(left, top, right, bottom - 1, directionsArray) + (a - bottom) * count;
                dp[left][top][right][bottom][directionsArray] = min(dp[left][top][right][bottom][directionsArray], result);
            }
        }

        existence[left][top][right][bottom][directionsArray] = true;
        return dp[left][top][right][bottom][directionsArray];
    }

    public static void restoreSolution(int left, int top, int right, int bottom, int directionsArray) {
        int step = dp[left][top][right][bottom][directionsArray];

        if (right < left || bottom < top)
            return;

        //TOP
        int leftL = firstGEqualValue(horizontals.get(left), top);
        int leftR = firstGreaterValue(horizontals.get(left), bottom);
        if (leftL == leftR) {
            restoreSolution(left + 1, top, right, bottom, directionsArray);
            return;
        }

        int rightL = firstGEqualValue(horizontals.get(right), top);
        int rightR = firstGreaterValue(horizontals.get(right), bottom);
        if (rightL == rightR) {
            restoreSolution(left, top, right - 1, bottom, directionsArray);
            return;
        }
        //LEFT
        int topT = firstGEqualValue(verticals.get(top), left);
        int topB = firstGreaterValue(verticals.get(top), right);
        if (topT == topB) {
            restoreSolution(left, top + 1, right, bottom, directionsArray);
            return;
        }
        int bottomT = firstGEqualValue(verticals.get(bottom), left);
        int bottomB = firstGreaterValue(verticals.get(bottom), right);
        if (bottomT == bottomB) {
            restoreSolution(left, top, right, bottom - 1, directionsArray);
            return;
        }


        if (!isClosed(directionsArray, UP) || !isClosed(directionsArray, DOWN)) {
            boolean upAvailable = true;
            int count = 0;
            for (int i = leftL; i != leftR && i < horizontals.get(left).size(); ++i) {
                int vert = horizontals.get(left).get(i);
                if (lineExist(left, vert, directionsArray, DOWN)) {
                    int result = solution(left, top, right, vert - 1, closeDirection(directionsArray, RIGHT))
                            + solution(left, vert + 1, right, bottom, closeDirection(directionsArray, LEFT))
                            + a - left;
                    if (result == step) {
                        answer[left][vert] = DOWN;
                        restoreSolution(left, top, right, vert - 1, closeDirection(directionsArray, RIGHT));
                        restoreSolution(left, vert + 1, right, bottom, closeDirection(directionsArray, LEFT));
                        return;
                    }
                }
                ++count;
                upAvailable &= lineExist(left, vert, directionsArray, UP);
            }
            if (upAvailable) {
                int result = solution(left + 1, top, right, bottom, directionsArray) + left * count;
                if (result == step) {
                    for (int i = leftL; i != leftR && i < horizontals.get(left).size(); ++i) {
                        answer[left][horizontals.get(left).get(i)] = UP;
                    }
                    restoreSolution(left + 1, top, right, bottom, directionsArray);
                    return;
                }
            }


            boolean downAvailable = true;
            count = 0;
            for (int i = rightL; i != rightR && i < horizontals.get(right).size(); ++i) {
                int vert = horizontals.get(right).get(i);
                if (lineExist(right, vert, directionsArray, UP)) {
                    int result = solution(left, top, right, vert - 1, closeDirection(directionsArray, RIGHT)) +
                            solution(left, vert + 1, right, bottom, closeDirection(directionsArray, LEFT)) + right;
                    if (result == step) {
                        answer[right][vert] = UP;
                        restoreSolution(left, top, right, vert - 1, closeDirection(directionsArray, RIGHT));
                        restoreSolution(left, vert + 1, right, bottom, closeDirection(directionsArray, LEFT));
                        return;
                    }
                }
                ++count;
                downAvailable &= lineExist(right, vert, directionsArray, DOWN);
            }
            
            if (downAvailable) {
                int result = solution(left, top, right - 1, bottom, directionsArray) + (a - right) * count;
                if (result == step) {
                    for (int i = rightL; i != rightR && i < horizontals.get(right).size(); ++i) {
                        answer[right][horizontals.get(right).get(i)] = DOWN;
                    }
                    restoreSolution(left, top, right - 1, bottom, directionsArray);
                    return;
                }
            }
        }

        if (!isClosed(directionsArray, LEFT) || !isClosed(directionsArray, RIGHT)) {
            boolean leftAvailable = true;
            int count = 0;
            for (int i = topT; i != topB && i < verticals.get(top).size(); ++i) {
                int horiz = verticals.get(top).get(i);
                if (lineExist(horiz, top, directionsArray, RIGHT)) {
                    int result = solution(left, top, horiz - 1, bottom, closeDirection(directionsArray, DOWN)) +
                            solution(horiz + 1, top, right, bottom, closeDirection(directionsArray, UP)) + a - top;
                    if (result == step) {
                        answer[horiz][top] = RIGHT;
                        restoreSolution(left, top, horiz - 1, bottom, closeDirection(directionsArray, DOWN));
                        restoreSolution(horiz + 1, top, right, bottom, closeDirection(directionsArray, UP));
                        return;
                    }
                }
                ++count;
                leftAvailable &= lineExist(horiz, top, directionsArray, LEFT);
            }
            
            if (leftAvailable) {
                int result = solution(left, top + 1, right, bottom, directionsArray) + top * count;
                if (result == step) {
                    for (int i = topT; i != topB && i < verticals.get(top).size(); ++i) {
                        answer[verticals.get(top).get(i)][top] = LEFT;
                    }
                    restoreSolution(left, top + 1, right, bottom, directionsArray);
                    return;
                }
            }

            boolean rightAvailable = true;
            count = 0;
            for (int i = bottomT; i != bottomB && i < verticals.get(bottom).size(); ++i) {
                int horiz = verticals.get(bottom).get(i);
                if (lineExist(horiz, bottom, directionsArray, LEFT)) {
                    int result = solution(left, top, horiz - 1, bottom, closeDirection(directionsArray, DOWN)) +
                            solution(horiz + 1, top, right, bottom, closeDirection(directionsArray, UP)) + bottom;
                    if (result == step) {
                        answer[horiz][bottom] = LEFT;
                        restoreSolution(left, top, horiz - 1, bottom, closeDirection(directionsArray, DOWN));
                        restoreSolution(horiz + 1, top, right, bottom, closeDirection(directionsArray, UP));
                        return;
                    }
                }
                ++count;
                rightAvailable &= lineExist(horiz, bottom, directionsArray, RIGHT);
            }
            if (rightAvailable) {
                int result = solution(left, top, right, bottom - 1, directionsArray) + (a - bottom) * count;
                if (result == step) {
                    for (int i = bottomT; i != bottomB && i < verticals.get(bottom).size(); ++i) {
                        answer[verticals.get(bottom).get(i)][bottom] = RIGHT;
                    }
                    restoreSolution(left, top, right, bottom - 1, directionsArray);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 256 * 1024 * 1024).start();
    }

    public void run(){
        Scanner scanner;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return;
        }
        a = Integer.parseInt(scanner.next());
        n = Integer.parseInt(scanner.next());

        for (int i = 0; i < 39; ++i) {
            horizontals.add(new ArrayList<>());
            verticals.add(new ArrayList<>());
        }

        ArrayList<Point> points = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            int x = Integer.parseInt(scanner.next()), y = Integer.parseInt(scanner.next());
            points.add(new Point(x, y));
            horizontals.get(x).add(y);
            verticals.get(y).add(x);
        }

        for (int i = 0; i < 31; ++i) {
            Collections.sort(horizontals.get(i));
            Collections.sort(verticals.get(i));
        }

        FileWriter fout;
        try {
            fout = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fout);
            fout.write(solution(1, 1, a - 1, a - 1, 0) + "\n");
            restoreSolution(1, 1, a - 1, a - 1, 0);
            for (int i = 0; i < points.size(); ++i) {
                Point temp = points.get(i);
                switch (directionsArrayToDirection(answer[temp.x][temp.y])) {
                    case UP:
                        fout.write("UP\n");
                        break;
                    case DOWN:
                        fout.write("DOWN\n");
                        break;
                    case LEFT:
                        fout.write("LEFT\n");
                        break;
                    case RIGHT:
                        fout.write("RIGHT\n");
                        break;
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}