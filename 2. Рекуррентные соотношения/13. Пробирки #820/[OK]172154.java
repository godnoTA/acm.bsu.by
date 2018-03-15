import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));
            PrintStream writer = new PrintStream("out.txt");

            String line;
            int need = Integer.parseInt(reader.readLine());
            int first = Integer.parseInt(reader.readLine());
            int second = Integer.parseInt(reader.readLine());
            int third = 100 - first - second;
            int number = 0;

            ArrayList<Integer> scale = new ArrayList<>();
            scale.add(0);

            line = reader.readLine() + " ";
            int pos1 = 0, pos2 = 0;
            while (pos2 != -1) {
                pos2 = line.indexOf(" ", pos1);

                int x = Integer.parseInt(line.substring(pos1, pos2));
                if (x == 0) {
                    break;
                }

                scale.add(x);
                pos2++;
                pos1 = pos2;
            }


            ArrayList<State> queue = new ArrayList<>();
            HashSet<State> setStates = new HashSet<>();
            int head = 0;

            if (add(new State(first, second, third, number), setStates, queue, need, writer)) {
                return;
            }

            while (head != queue.size()) {
                first = queue.get(head).x1;
                second = queue.get(head).x2;
                third = queue.get(head).x3;
                number = queue.get(head).num;
                number++;

                HashSet<Integer> tmp = getListOfDelta("one", first, second, scale);

                for (Integer i : tmp) {
                    State state = new State(first - i, second + i, third, number);
                    if (add(state, setStates, queue, need, writer)) {
                        return;
                    }
                }

                tmp = getListOfDelta("two", first, third, scale);

                for (Integer i : tmp) {
                    State state = new State(first - i, second, third + i, number);
                    if (add(state, setStates, queue, need, writer)) {
                        return;
                    }
                }

                tmp = getListOfDelta("two", second, third, scale);

                for (Integer i : tmp) {
                    State state = new State(first, second- i, third + i, number);
                    if (add(state, setStates, queue, need, writer)) {
                        return;
                    }
                }

                head++;
            }

            writer.println("No solution");

            reader.close();
            writer.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    private static HashSet<Integer> getListOfDelta(String type, int volume1, int volume2, ArrayList<Integer> scale) {
        HashSet<Integer> result = new HashSet<>();

        switch (type) {
            case "one": {
                for (Integer sc : scale) {
                    if (sc >= volume1) {
                        break;
                    }

                    result.add(volume1 - sc);
                }

                for (int i = scale.size() - 1; i >= 0; i--) {
                    if (volume1 >= scale.get(i) - volume2) {
                        if (scale.get(i) <= volume2) {
                            break;
                        }

                        result.add(scale.get(i) - volume2);
                    }
                }

                for (Integer sc : scale) {
                    if (sc >= volume2) {
                        break;
                    }

                    result.add(sc - volume2);
                }

                for (int i = scale.size() - 1; i >= 0; i--) {
                    if (volume2 >= scale.get(i) - volume1) {
                        if (scale.get(i) <= volume1) {
                            break;
                        }

                        result.add(volume1 - scale.get(i));
                    }
                }

                return result;
            }
            case "two": {
                for (Integer sc : scale) {
                    if (sc >= volume1) {
                        break;
                    }

                    result.add(volume1 - sc);
                }

                for (int i = scale.size() - 1; i >= 0; i--) {
                    if (volume2 >= scale.get(i) - volume1) {
                        if (scale.get(i) <= volume1) {
                            break;
                        }

                        result.add(volume1 - scale.get(i));
                    }
                }

                if (volume2 != 0) {
                    result.add(-volume2);
                }

                return result;
            }
        }

        return null;
    }
    private static boolean add(State state, HashSet<State> set, ArrayList<State> queue, int need, PrintStream writer) {
        if (!set.contains(state)) {
            if (state.x3 == need) {
                writer.println(state.num);
                return true;
            }
            set.add(state);
            queue.add(state);
        }

        return false;
    }
}


class State {
    int x1, x2, x3, num;

    State(int x1, int x2, int x3, int num) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.num = num;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        return x1 == state.x1 && x2 == state.x2 && x3 == state.x3;
    }

    @Override
    public int hashCode() {
        int result = x1;
        result = 31 * result + x2;
        result = 31 * result + x3;
        return result;
    }
}



