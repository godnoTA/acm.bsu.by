import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by User on 01/05/2016.
 */
public class Main {
    static List<Integer> waysLength = new ArrayList<>();
    static List<List<Node>> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        FileWriter writer = new FileWriter("output.txt");
        Cell startCell = null;
        Cell endCell = null;
        List<List<Cell>> cellHolder = new ArrayList<>();
        int m = 0;
        int n = 0;
        int k = 0;

        try {
            Scanner sc = new Scanner(new File("input.txt"));
            n = sc.nextInt();
            m = sc.nextInt();
            k = sc.nextInt();

            initCellHolder(cellHolder, m, n);
            initEmptyCells(cellHolder, k, sc);
            startCell = initStartCell(cellHolder, sc);
            endCell = initEndCell(cellHolder, sc);
            initCellBrothers(cellHolder, m, n);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        boolean end = false;
        Queue<Cell> mainQueue = new LinkedTransferQueue<>();
        Cell.endCell = endCell;
        Cell.cellHolder = cellHolder;
        for (List<Cell> list : cellHolder) {
            for (Cell cell : list) {
                bfs(m, n, mainQueue, cell);
            }
        }

        Node root = new Node(startCell.getColumn(), startCell.getRow(), startCell.getMinWayLength());

        findNewNodeOnDesk(root, n, m, cellHolder);

        result.sort(new MyComparator());

        if (result.size() == 0) {
            writer.write("no_solutions");
        }

        for (List<Node> list : result) {
            writer.write(Integer.toString(list.size() - 1));
            writer.write('\n');
            for (Node node : list) {
                writer.write(Integer.toString(node.getColumn()));
                writer.write(' ');
                writer.write(Integer.toString(node.getRow()));
                writer.write('\n');
            }
        }

        writer.close();

    }

    private static void bfs(int m, int n, Queue<Cell> mainQueue, Cell cell) {
        if (!cell.isEmptyField()) {
            if (cell.equals(Cell.endCell)) {
                cell.setMinWayLength(0);
                clearCellHolder(Cell.cellHolder, m, n);
                mainQueue.clear();
            } else {
                mainQueue.add(cell);
                Cell nextNode = cell;
                while (!mainQueue.isEmpty() && !nextNode.equals(Cell.endCell)) {
                    nextNode = mainQueue.remove();

                    nextNode.visit = true;

                    List<Cell> listForPush = nextNode.getBrothers();
                    for (int i = 0; i < listForPush.size(); i++) {
                        Cell c = listForPush.get(i);
                        if (!c.isVisit() && !mainQueue.contains(c)) {
                            c.setParent(nextNode);
                            c.setDirection(c.directionFrom(nextNode));
                            if (c.getDirection() == c.getParent().getDirection()) {
                                c.setWidth(c.getParent().getWidth());
                            } else {
                                c.setWidth(c.getParent().getWidth() + 1);
                            }
                            mainQueue.add(c);
                        }
                    }
                }
            }
            if (Cell.endCell.isVisit()) {
                cell.setMinWayLength(Cell.endCell.getWidth());
                clearCellHolder(Cell.cellHolder, m, n);
                mainQueue.clear();
            }
        }
    }

    private static void findNewNodeOnDesk(Node node, int columns, int rows, List<List<Cell>> cellHolder) {

        Node newNode;
        int startRow = node.getRow();
        int startColumn = node.getColumn();
        int endRow = node.getRow();
        int endColumn = node.getColumn();
        while (startRow > 0 && !cellHolder.get(node.getColumn()).get(startRow - 1).isEmptyField()) {
            startRow--;
        }
        while (startColumn > 0 && !cellHolder.get(startColumn - 1).get(node.getRow()).isEmptyField()) {
            startColumn--;
        }
        while (endRow < rows-1 && !cellHolder.get(node.getColumn()).get(endRow + 1).isEmptyField()) {
            endRow++;
        }
        while (endColumn < columns-1 && !cellHolder.get(endColumn + 1).get(node.getRow()).isEmptyField()) {
            endColumn++;
        }

        for (int i = startRow; i <= endRow; i++) {
            if (cellHolder.get(node.getColumn()).get(i).getMinWayLength() + 1 == node.getWidth()) {
                newNode = new Node(node.getColumn(), i, node.getWidth() - 1);
                node.addChild(newNode);
                newNode.setParent(node);
                findNewNodeOnDesk(newNode, columns, rows, cellHolder);
            }
        }
        for (int i = startColumn; i <= endColumn; i++) {
            if (cellHolder.get(i).get(node.getRow()).getMinWayLength() + 1 == node.getWidth()) {
                newNode = new Node(i, node.getRow(), node.getWidth() - 1);
                node.addChild(newNode);
                newNode.setParent(node);
                findNewNodeOnDesk(newNode, columns, rows, cellHolder);
            }
        }

        buildWay(node);
    }

    private static void buildWay(Node node) {
        if (node.getWidth() == 0) {
            List<Node> tempList = new ArrayList<>();
            ;
            while (node.getParent() != null) {
                tempList.add(node);
                node = node.getParent();
            }
            tempList.add(node);
            if (tempList != null) {
                Collections.reverse(tempList);
            }
            result.add(tempList);
        }
    }

    private static void clearCellHolder(List<List<Cell>> cellHolder, int m, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Cell curr = cellHolder.get(i).get(j);
                curr.setWidth(0);
                curr.setVisit(false);
                curr.setParent(null);
                curr.setDirection(Cell.Direction.UNDEFINED);
            }
        }
    }

    private static void initEmptyCells(List<List<Cell>> cellHolder, int k, Scanner sc) {
        for (int i = 0; i < k; i++) {
            Cell emptyCell = cellHolder.get(sc.nextInt()).get(sc.nextInt());
            emptyCell.setEmptyField(true);
        }
    }

    private static Cell initStartCell(List<List<Cell>> cellHolder, Scanner sc) {
        Cell startCell;
        startCell = cellHolder.get(sc.nextInt()).get(sc.nextInt());

        startCell.setStart(true);
        return startCell;
    }

    private static Cell initEndCell(List<List<Cell>> cellHolder, Scanner sc) {
        Cell endCell;
        endCell = cellHolder.get(sc.nextInt()).get(sc.nextInt());

        endCell.setStart(true);
        return endCell;
    }

    private static void initCellBrothers(List<List<Cell>> cellHolder, int m, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Cell currCell = cellHolder.get(i).get(j);
                if (!currCell.isEmptyField()) {
                    int topBorder = currCell.getRow();
                    int botBorder = currCell.getRow();
                    int rightBorder = currCell.getColumn();
                    int leftBorder = currCell.getColumn();
                    while (topBorder > 0 && !cellHolder.get(currCell.getColumn()).get(topBorder - 1).isEmptyField()) {
                        topBorder--;
                    }
                    while (botBorder < m - 1 && !cellHolder.get(currCell.getColumn()).get(botBorder + 1).isEmptyField()) {
                        botBorder++;
                    }
                    while (leftBorder > 0 && !cellHolder.get(leftBorder - 1).get(currCell.getRow()).isEmptyField()) {
                        leftBorder--;
                    }
                    while (rightBorder < n - 1 && !cellHolder.get(rightBorder + 1).get(currCell.getRow()).isEmptyField()) {
                        rightBorder++;
                    }
                    for (int k = topBorder; k <= botBorder; k++) {
                        if (!cellHolder.get(currCell.getColumn()).get(k).equals(currCell))
                            currCell.addBrother(cellHolder.get(currCell.getColumn()).get(k));
                    }
                    for (int k = leftBorder; k <= rightBorder; k++) {
                        if (!cellHolder.get(k).get(currCell.getRow()).equals(currCell))
                            currCell.addBrother(cellHolder.get(k).get(currCell.getRow()));
                    }
                }
            }
        }
    }

    private static void initCellHolder(List<List<Cell>> cellHolder, int m, int n) {
        for (int i = 0; i < n; i++) {
            cellHolder.add(new ArrayList<>());
            for (int j = 0; j < m; j++) {
                Cell newCell = new Cell(i, j);
                cellHolder.get(i).add(newCell);
            }
        }
    }
}

class MyComparator implements Comparator<List<Node>> {

    @Override
    public int compare(List<Node> o1, List<Node> o2) {
        for (int i = 0; i < o1.size() - 1; i++) {
            if (o1.get(i + 1).directionFrom(o1.get(i)).weight - o2.get(i + 1).directionFrom(o2.get(i)).weight > 0) {
                return -1;
            }
            if (o1.get(i + 1).directionFrom(o1.get(i)).weight - o2.get(i + 1).directionFrom(o2.get(i)).weight < 0) {
                return 1;
            }
            if (o1.get(i + 1).directionFrom(o1.get(i)).weight - o2.get(i + 1).directionFrom(o2.get(i)).weight == 0) {
                int firstCol = o1.get(i).getColumn();
                int firstRow = o1.get(i).getRow();
                int secondCol = o1.get(i + 1).getColumn();
                int secondRow = o1.get(i + 1).getRow();
                int res1 = Math.max(Math.abs(secondCol - firstCol), Math.abs(secondRow - firstRow));
                int firstCol1 = o2.get(i).getColumn();
                int firstRow1 = o2.get(i).getRow();
                int secondCol1 = o2.get(i + 1).getColumn();
                int secondRow1 = o2.get(i + 1).getRow();
                int res2 = Math.max(Math.abs(secondCol1 - firstCol1), Math.abs(secondRow1 - firstRow1));
                if (res1 > res2) {
                    return 1;
                }
                if (res1 < res2) {
                    return -1;
                }
            }
        }
        return 0;
    }
}

class Cell implements Comparable<Cell> {

    @Override
    public int compareTo(Cell o) {
        if (this.getWidth() - o.getWidth() != 0) {
            return this.getWidth() - o.getWidth();
        } else {
            boolean first = onOneLine(this);
            boolean second = onOneLine(o);
            if (first && !second) return -1;
            else if (!first && second) return 1;
            else {
                return 0;
            }

        }
    }

    public enum Direction {
        LEFT(2), RIGHT(4), TOP(1), BOTTOM(3), UNDEFINED(0);
        int weight;

        Direction(int weight) {
            this.weight = weight;
        }
    }

    static Cell endCell;
    static List<List<Cell>> cellHolder;

    int width;
    Direction direction;
    boolean visit;
    int column;
    int row;
    Cell parent;
    boolean start;
    boolean end;
    boolean emptyField;
    int minWayLength;
    List<Cell> brothers;

    public Cell(int column, int row) {
        this.column = column;
        this.parent = null;
        this.row = row;
        this.width = 0;
        this.visit = false;
        this.minWayLength = -2;
        this.brothers = new ArrayList<>();
    }

    public boolean onOneLine(Cell c) {
        if (!(c.getRow() == endCell.getRow()) && !(c.getColumn() == endCell.getColumn())) {
            return false;
        } else {
            if (c.getRow() == endCell.getRow()) {
                for (int i = c.getColumn() + 1; i < endCell.getColumn() - 1; i++) {
                    if (cellHolder.get(i).get(c.getRow()).isEmptyField()) return false;
                }
                return true;
            }
            if (c.getColumn() == endCell.getColumn()) {
                for (int i = c.getRow() + 1; i < endCell.getRow() - 1; i++) {
                    if (cellHolder.get(c.getColumn()).get(i).isEmptyField()) return false;
                }
                return true;
            } else {
                return true;
            }
        }
    }

    public Direction directionFrom(Cell c1) {
        if (c1.getColumn() == this.getColumn()) {
            if (c1.getRow() > this.getRow()) {
                return Direction.TOP;
            } else {
                return Direction.BOTTOM;
            }
        } else {
            if (c1.getColumn() > this.getColumn()) {
                return Direction.LEFT;
            } else {
                return Direction.RIGHT;
            }
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }

    public boolean isVisit() {
        return visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public boolean isEmptyField() {
        return emptyField;
    }

    public void setEmptyField(boolean emptyField) {
        this.emptyField = emptyField;
    }

    public int getMinWayLength() {
        return minWayLength;
    }

    public void setMinWayLength(int minWayLength) {
        this.minWayLength = minWayLength;
    }

    public List<Cell> getBrothers() {
        return brothers;
    }

    public void setBrothers(List<Cell> brothers) {
        this.brothers = brothers;
    }

    public void addBrother(Cell bro) {
        this.brothers.add(bro);
    }

    @Override
    public String toString() {
        return "column: " + column +
                ", row: " + row +
                " , empty: " + emptyField +
                " , start: " + start +
                " , end: " + end;
    }
}

class Node {
    int column;
    int row;
    int width;
    Node parent;
    List<Node> children;

    public Node(int column, int row, int width) {
        this.column = column;
        this.row = row;
        this.width = width;
        children = new ArrayList<>();
    }

    public Cell.Direction directionFrom(Node c1) {
        if (c1.getColumn() == this.getColumn()) {
            if (c1.getRow() > this.getRow()) {
                return Cell.Direction.TOP;
            } else {
                return Cell.Direction.BOTTOM;
            }
        } else {
            if (c1.getColumn() > this.getColumn()) {
                return Cell.Direction.LEFT;
            } else {
                return Cell.Direction.RIGHT;
            }
        }
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}