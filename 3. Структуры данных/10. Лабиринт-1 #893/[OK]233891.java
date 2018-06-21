

import java.io.*;
import java.util.*;

public class Main {
    private int[][] arr;
    private int rows;
    private int cols;

    public void down() throws FileNotFoundException, InterruptedException, IOException {
        List<Integer> inputs = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            if (arr[0][i] == 0) {
                inputs.add(i);
            }
        }
        List<Integer> outs = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            if (arr[rows - 1][i] == 0) {
                outs.add(i);
            }
        }
        PrintStream ps = new PrintStream("out.txt");
        int ind;
        String prior = "down";
        String prev_prior = prior;
        int col = 0;//строки
        int finish_ind = 0;
        int exits = 0;
        if (inputs.size() > outs.size()) {
            ps.println("Impossible");
            return;
        }
        for (int i = 0; i < cols; i++) {
            arr[0][i] = 1;
        }
        while (exits < inputs.size()) {
            col = 0;
            ind = inputs.get(exits);
            List<String> priors = new ArrayList<>();
            List<Integer> prev_col = new ArrayList<>();
            List<Integer> prev_ind = new ArrayList<>();
            priors.add("down");

            while (true) {

                if (col == rows - 1 && arr[col][ind] == 0) {
                    break;
                }


                switch (prior) {
                    case "down":
                        if (ind > 0) {
                            if (arr[col][ind - 1] != 1) {//left
                                arr[col][ind] = 1;
                                prev_col.add(col);
                                prev_ind.add(ind);
                                ind--;
                                prev_prior = prior;
                                priors.add(prev_prior);
                                prior = "left";

                            } else if (col < rows - 1) {
                                if (arr[col + 1][ind] != 1) {//down
                                    arr[col][ind] = 1;
                                    prev_col.add(col);
                                    prev_ind.add(ind);
                                    col++;
                                    prev_prior = prior;
                                    priors.add(prev_prior);
                                    prior = "down";

                                } else if (ind < cols - 1) {
                                    if (arr[col][ind + 1] != 1) {//right
                                        arr[col][ind] = 1;
                                        prev_col.add(col);
                                        prev_ind.add(ind);
                                        ind++;
                                        prev_prior = prior;
                                        priors.add(prev_prior);
                                        prior = "right";

                                    } else {
                                        if (col == 0) {
                                            break;
                                        } else {
                                            arr[col][ind] = 1;

                                            col = prev_col.get(prev_col.size() - 1);
                                            ind = prev_ind.get(prev_ind.size() - 1);

                                            prev_col.remove(prev_col.size() - 1);
                                            prev_ind.remove(prev_ind.size() - 1);

                                            arr[col][ind] = 0;
                                            prior = prev_prior;
                                            priors.remove(priors.size() - 1);
                                            prev_prior = priors.get(priors.size() - 1);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "left":
                        if (col > 0) {
                            if (arr[col - 1][ind] != 1) {//up
                                arr[col][ind] = 1;
                                prev_col.add(col);
                                prev_ind.add(ind);
                                col--;
                                prev_prior = prior;
                                priors.add(prev_prior);
                                prior = "up";

                            } else if (ind > 0) {
                                if (arr[col][ind - 1] != 1) {//left
                                    arr[col][ind] = 1;
                                    prev_col.add(col);
                                    prev_ind.add(ind);
                                    ind--;
                                    prev_prior = prior;
                                    priors.add(prev_prior);
                                    prior = "left";

                                } else if (col < rows - 1) {
                                    if (arr[col + 1][ind] != 1) {//down
                                        arr[col][ind] = 1;
                                        prev_col.add(col);
                                        prev_ind.add(ind);
                                        col++;
                                        prev_prior = prior;
                                        priors.add(prev_prior);
                                        prior = "down";

                                    } else {
                                        if (col == 0) {
                                            break;
                                        } else {
                                            arr[col][ind] = 1;

                                            col = prev_col.get(prev_col.size() - 1);
                                            ind = prev_ind.get(prev_ind.size() - 1);

                                            prev_col.remove(prev_col.size() - 1);
                                            prev_ind.remove(prev_ind.size() - 1);

                                            arr[col][ind] = 0;
                                            prior = prev_prior;
                                            priors.remove(priors.size() - 1);
                                            prev_prior = priors.get(priors.size() - 1);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "right":
                        if (col < rows - 1) {
                            if (arr[col + 1][ind] != 1) {//down
                                arr[col][ind] = 1;
                                prev_col.add(col);
                                prev_ind.add(ind);
                                col++;
                                prev_prior = prior;
                                priors.add(prev_prior);
                                prior = "down";

                            } else if (ind < cols - 1) {
                                if (arr[col][ind + 1] != 1) {//right
                                    arr[col][ind] = 1;
                                    prev_col.add(col);
                                    prev_ind.add(ind);
                                    ind++;
                                    prev_prior = prior;
                                    priors.add(prev_prior);
                                    prior = "right";

                                } else if (col > 0) {
                                    if (arr[col - 1][ind] != 1) {//up
                                        arr[col][ind] = 1;
                                        prev_col.add(col);
                                        prev_ind.add(ind);
                                        col--;
                                        prev_prior = prior;
                                        priors.add(prev_prior);
                                        prior = "up";

                                    } else {
                                        if (col == 0) {
                                            break;
                                        } else {
                                            arr[col][ind] = 1;

                                            col = prev_col.get(prev_col.size() - 1);
                                            ind = prev_ind.get(prev_ind.size() - 1);

                                            prev_col.remove(prev_col.size() - 1);
                                            prev_ind.remove(prev_ind.size() - 1);

                                            arr[col][ind] = 0;
                                            prior = prev_prior;
                                            priors.remove(priors.size() - 1);
                                            prev_prior = priors.get(priors.size() - 1);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "up":
                        if (ind < cols - 1) {
                            if (arr[col][ind + 1] != 1) {//right
                                arr[col][ind] = 1;
                                prev_col.add(col);
                                prev_ind.add(ind);
                                ind++;
                                prev_prior = prior;
                                priors.add(prev_prior);
                                prior = "right";

                            } else if (col > 0) {
                                if (arr[col - 1][ind] != 1) {//up
                                    arr[col][ind] = 1;
                                    prev_col.add(col);
                                    prev_ind.add(ind);
                                    col--;
                                    prev_prior = prior;
                                    priors.add(prev_prior);
                                    prior = "up";

                                } else if (ind > 0) {
                                    if (arr[col][ind - 1] != 1) {//left
                                        arr[col][ind] = 1;
                                        prev_col.add(col);
                                        prev_ind.add(ind);
                                        ind--;
                                        prev_prior = prior;
                                        priors.add(prev_prior);
                                        prior = "left";

                                    } else {
                                        if (col == 0) {
                                            break;
                                        } else {
                                            arr[col][ind] = 1;
                                            col = prev_col.get(prev_col.size() - 1);
                                            ind = prev_ind.get(prev_ind.size() - 1);

                                            prev_col.remove(prev_col.size() - 1);
                                            prev_ind.remove(prev_ind.size() - 1);

                                            arr[col][ind] = 0;
                                            prior = prev_prior;
                                            priors.remove(priors.size() - 1);
                                            prev_prior = priors.get(priors.size() - 1);
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }

                /*System.out.println(prev_col + " " + col);
                System.out.println(prev_ind + " " + ind);
                System.out.println(priors);
                System.out.println(prior);
                output(col, ind);
                Thread.sleep(1000);*/
                if (((prev_col.size() == 0) && (prev_ind.size() == 0)) || (col == 0)) {
                    if (checkForEnd(col, ind)) {
                        //System.out.println("check");
                        ps.println("Impossible");
                        return;
                    }
                }

            }

            exits++;
        }
        ps.println("Possible");
        ps.close();
    }

    public boolean checkForEnd(int col, int ind) {
        if (arr[col + 1][ind] == 1) {
            return true;
        } else return false;
    }

    public void output(int row, int col) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == row && j == col) {
                    System.out.print("[" + arr[i][j] + "]");
                } else {

                    System.out.print(" " + arr[i][j]);
                }
            }
            System.out.println();
        }
    }

    public void input() throws InputMismatchException, FileNotFoundException {
        Scanner sc = new Scanner(new File("in.txt"));
        rows = sc.nextInt();
        cols = sc.nextInt() + 2;
        String[] col;
        sc.nextLine();
        arr = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            col = sc.nextLine().split("");
            for (int j = 1; j < cols - 1; j++) {
                arr[i][j] = Integer.parseInt(col[j - 1]);
            }
            arr[i][cols - 1] = 1;
            arr[i][0] = 1;
        }
    }

    public static void main(String[] args) throws Exception {
        Main obj = new Main();
        obj.input();
        obj.down();
    }
}