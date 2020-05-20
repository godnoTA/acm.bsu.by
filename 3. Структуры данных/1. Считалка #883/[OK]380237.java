import java.io.*;
import java.util.*;

//input
//used - матрица клеток, которые уже были рассмотрены/побывали в очереди
//пока очередь не опустеет, рассматриваем в ней первый элемент:
//цикл for от 0 до 4 - красивый и удобный способ рассмотрения 4 вариантов направления движения
//используя массивы directionsX и directionsY мы на каждой итерации смещаемся на 1 клетку в одну из сторон
//если эта клетка проходит все критерии, то если она финальная - выводим ответ и выходим из программы,
//если нет - помечаем ее как использованную и добавляем в очередь
//если очередь опустеет выводит No
//Вопрос: если, пройдя по какому-то пути мы добавили в очередь определенную вершину,
//пометили ее как использованную, то мы больше не можем попасть в эту вершину, пройдя по другому пути. Почему это не ломает прогу?
//Ответ: наша очередь работает как поиск в ширину и если мы добавляем вершину в очередь, значит мы дошли до нее по кратчайшему пути
//Асимптотика O(nm), т.к. каждую вершину мы рассматриваем максимум по одному разу

public class Main {
    static int n, m, k, x1, y1, x2, y2;
    static int[][] matrix;
    final static int[] directionsX = {1, 0, -1, 0};
    final static int[] directionsY = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        input();
        boolean[][] used = new boolean[n][m];
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.add(Arrays.asList(x1, y1, 0));
        used[x1][y1] = true;
        while (!queue.isEmpty()) {
            List<Integer> current = queue.remove();
            for (int i = 0; i < 4; i++) {
                int x = current.get(0) + directionsX[i];
                int y = current.get(1) + directionsY[i];
                if (x >= 0 && x < n && y >= 0 && y < m && !used[x][y] && Math.abs(matrix[x][y] - matrix[current.get(0)][current.get(1)]) <= k) {
                    if (x == x2 && y == y2)
                        output(current.get(2) + 1);
                    used[x][y] = true;
                    queue.add(Arrays.asList(x, y, current.get(2) + 1));
                }
            }
        }
        output(-1);
    }

    public static void input() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));
        String[] temp = reader.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            temp = reader.readLine().split(" ");
            for (int j = 0; j < m; j++)
                matrix[i][j] = Integer.parseInt(temp[j]);
        }
        k = Integer.parseInt(reader.readLine());
        temp = reader.readLine().split(" ");
        x1 = Integer.parseInt(temp[0]) - 1;
        y1 = Integer.parseInt(temp[1]) - 1;
        x2 = Integer.parseInt(temp[2]) - 1;
        y2 = Integer.parseInt(temp[3]) - 1;
    }

    public static void output(int length) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"));
        if (length > 0)
            writer.write("Yes\n" + length);
        else
            writer.write("No");
        writer.close();
        System.exit(0);
    }
}
