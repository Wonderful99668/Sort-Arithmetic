import java.io.IOException;
import java.util.Scanner;

public class Digraph {

    private char[] mVertex;//储存顶点
    private int[][] mMatrix;//邻接矩阵
    private int vlen;//顶点数
    private int elen;//边数

    /**
     * 自己输入图时调用的构造器
     */
    public Digraph() {
        System.out.println("input vertex number: ");
        vlen = readInt();
        System.out.println("input edges number: ");
        elen = readInt();
        if (vlen < 1) {
            System.out.println("input error: invalid parameters!");
            return;
        }

        for (int i = 0; i < vlen; i++) {
            System.out.print("vertex:" + i);
            mVertex[i] = readChar();
        }
        System.out.println("vertex input complete.");

        /*初始化矩阵*/
        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++)
                mMatrix[i][j] = 0;
        }

        for (int i = 0; i < elen; i++) {
            System.out.print("edge:" + i);
            char ch1 = readChar();
            char ch2 = readChar();
            int p1 = getPosition(ch1);
            int p2 = getPosition(ch2);
            if (p1 == -1 || p2 == -1) {
                System.out.println("input error: invalid edge!");
                return;
            }
            mMatrix[p1][p2] = 1;
        }
    }

    /**
     * 传入已知节点,边时调用的构造器
     */
    public Digraph(char[] vertex, char[][] edges) {
        vlen = vertex.length;
        elen = edges.length;

        mVertex = new char[vlen];
        for (int i = 0; i < mVertex.length; i++)
            mVertex[i] = vertex[i];

        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++)
                mMatrix[i][j] = 0;
        }
        for (int i = 0; i < elen; i++) {
            int p1 = getPosition(edges[i][0]);
            int p2 = getPosition(edges[i][1]);
            mMatrix[p1][p2] = 1;
        }

    }

    /**
     * 已知邻接矩阵，调用的构造器
     */
    public Digraph(int[][] matrix) {
        this.mMatrix = matrix;
        this.vlen = matrix.length;
        this.mVertex = new char[vlen];

        /*初始化顶点*/
        for (int i = 0; i < vlen; i++)
            this.mVertex[i] = (char) (65 + i);
    }

    /**
     * 输入流，输出流控制
     */
    private char readChar() {
        char ch = '0';
        do {
            try {
                ch = (char) System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')));
        return ch;
    }

    private int readInt() {
        return new Scanner(System.in).nextInt();
    }

    private int getPosition(char ch) {
        for (int i = 0; i < mVertex.length; i++) {
            if (ch == mVertex[i])
                return i;
        }
        return -1;
    }

    /**
     * 返回节点数，边数
     */
    public int vertexNum() {
        return mVertex.length;
    }

    public int edgeNum() {
        return elen;
    }

    /**
     * 获得给定索引的顶点
     */
    public char getV(int target) {
        return mVertex[target];
    }

    public int[][] getmMatrix() {
        return mMatrix;
    }

    /**
     * 获得每个顶点的入度
     */
    public int getIndegree(char vertex) {
        int position = getPosition(vertex);
        if (position != -1) {
            int count = 0;
            for (int i = 0; i < mVertex.length; i++) {
                if (mMatrix[i][position] == 1)
                    count++;
            }
            return count;
        }
        return -1;
    }

    public int[] indegrees() {
        int[] result = new int[mVertex.length];
        for (int i = 0; i < mVertex.length; i++)
            result[i] = getIndegree(mVertex[i]);

        return result;
    }


    public boolean remove(int vNum, int i) {
        if (mMatrix[vNum][i] == 1) {
            mMatrix[vNum][i] = 0;
            return true;
        }
        return false;
    }


    public void print() {
        System.out.println("Matrix Graph: ");
        for (int i = 0; i < mVertex.length; i++) {
            for (int j = 0; j < mVertex.length; j++)
                System.out.print(mMatrix[i][j] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
                {'A', 'B'},
                {'A', 'C'},
                {'D', 'C'},
                {'D', 'E'},
                {'B', 'F'},
                {'B', 'G'},
                {'C', 'G'},
                {'E', 'G'}};
        Digraph pG = new Digraph(vexs, edges);

        KhanTopological method = new KhanTopological();
        method.Topologiacal(pG);
        pG.print();   // 打印图

    }

}
