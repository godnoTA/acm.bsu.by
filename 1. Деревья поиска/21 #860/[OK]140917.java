import java.io.*;
import java.util.Collections;
import java.util.Vector;

/**
 * Created by Alexander on 21/02/2016.
 */
public class Runner {
    static PrintWriter out;
    public static void main(String[] args) throws IOException {
        TreeFunc tr = new TreeFunc();
        int a;
        out = new PrintWriter((new FileWriter("out.txt")));
        BufferedReader br = new BufferedReader(new FileReader("in.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            int x = Integer.parseInt(line);
            tr.add(x);
        }
        tr.postOrder();
        if ((tr.getV().size() % 2) != 0) {
            Collections.sort(tr.getV());
            a = (int)tr.getV().get(tr.getV().size() / 2);
            tr.delete(a);
        }
        tr.getAnswer();
    }

    static class TreeNode {
        private int value;
        private int height = 0;
        private int sons = 0;
        private TreeNode left, right;

        public TreeNode() {
            this(0, null, null);
        }

        public TreeNode(int x) {
            this(x, null, null);
        }

        public TreeNode(int x, TreeNode aLeft, TreeNode aRight) {
            value = x;
            left = aLeft;
            right = aRight;
        }

        public TreeNode(TreeNode p) {
            value = p.value;
            left = p.left;
            right = p.right;
        }

        int getValue() {
            return value;
        }

        void setValue(int x) {
            value = x;
        }

        TreeNode getLeft() {
            return left;
        }

        void setLeft(TreeNode pLeft) {
            left = pLeft;
        }

        TreeNode getRight() {
            return right;
        }

        void setRight(TreeNode pRight) {
            right = pRight;
        }

        int getHeight() {
            return height;
        }

        void setHeight(int h) {
            height = h;
        }

        int getSons() {
            return sons;
        }

        void setSons(int s) {
            sons = s;
        }
    }

    //---------------------------------------------------------------------------------------
    static class TreeFunc {
        public boolean add(int x) {
            TreeNode p = t, pp = null;
            boolean xIsLeft = true, xRetVal = true;
            while (p != null) {
                if (x < p.getValue()) {
                    pp = p;
                    p = p.getLeft();
                    xIsLeft = true;
                } else if (x > p.getValue()) {
                    pp = p;
                    p = p.getRight();
                    xIsLeft = false;
                } else // x=p.getElement()
                {//xRetVal = false;
                    return false;
                    //break;
                }
            }
            p = new TreeNode(x);
            if (pp == null)
                t = p;
            else if (xIsLeft)
                pp.setLeft(p);
            else
                pp.setRight(p);
            return xRetVal;
        }

        //-------------------------------------------------------------------------------
        public void solve(TreeNode k) {
            if (k.getLeft() == null && k.getRight() == null) {
                return;
            }
            if (k.getLeft() != null && k.getRight() == null) {
                k.setHeight(k.getLeft().getHeight() + 1);
                k.setSons(k.getLeft().getSons() + 1);
                return;
            }
            if (k.getLeft() == null) {
                k.setHeight(k.getRight().getHeight() + 1);
                k.setSons(k.getRight().getSons() + 1);
                return;
            }
            k.setHeight(Math.max(k.getLeft().getHeight(), k.getRight().getHeight()) + 1);
            k.setSons(k.getLeft().getSons() + k.getRight().getSons() + 2);
            if (k.getLeft().getHeight() == k.getRight().getHeight() && k.getLeft().getSons() != k.getRight().getSons()) {
                v.add(k.getValue());
            }
        }

        //----------------------------------------------------------------------------
        private void postOrder(TreeNode tr) {
            if (tr != null) {
                postOrder(tr.getLeft());
                postOrder(tr.getRight());
                solve(tr);
            }
        }

        //---------------------------------------------------------------------------
        public void postOrder() {
            postOrder(t);
        }

        //----------------------------------------------------------------------------------
        private void delete(TreeNode vr, int val) {
            TreeNode p = null;
            while (vr != null) {
                if (val == vr.getValue()) {
                    break;
                } else {
                    p = vr;
                    if (val < vr.getValue()) {
                        vr = vr.getLeft();
                    } else {
                       vr = vr.getRight();
                    }
                }
            }
            if (vr == null) {
                return;
            }
            if (vr.getRight() == null) {
                if (p == null) {
                    vr = vr.getLeft();
                } else {
                    if (vr == p.getLeft()) {
                        p.setLeft(vr.getLeft());
                    } else {
                        p.setRight(vr.getLeft());
                    }
                }
            } else {
                TreeNode tmp = vr.getRight();
                p = null;
                while (tmp.getLeft() != null) {
                    p = tmp;
                    tmp = tmp.getLeft();
                }
                if (p != null) {
                    p.setLeft(tmp.getRight());
                } else {
                    vr.setRight(tmp.getRight());
                }
                vr.setValue(tmp.getValue());
            }
        }

        //------------------------------------------------------------------------------------
        public void delete(int num) {
            delete(t, num);
        }

        //------------------------------------------------------------------------------------
        private void getAnswer(TreeNode tr) {
            if (tr != null) {
                out.println(tr.getValue());
                //out.flush();
                //vet.add(tr.getValue());
                getAnswer(tr.getLeft());
                getAnswer(tr.getRight());
            }
        }

        //----------------------------------------------------------------------------------------
        public void getAnswer() {
            //Vector vec = new Vector();
            getAnswer(t);
            out.flush();
        }

        public Vector getV() {
            return v;
        }

        //------------------------------------------------------------------------------------
        private TreeNode t;
        private Vector<Integer> v = new Vector<>();
    }
}