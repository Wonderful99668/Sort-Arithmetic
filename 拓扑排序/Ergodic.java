/**
 * 基于邻接矩阵表示的图的广度优先遍历(BFS)和深度优先遍历(DFS)
 */
public class Ergodic {

    private Digraph graph;
    private int vNum;
    /**
     * 深度优先遍历
     */
    public void DFS(Digraph di) {
        graph = di;
        vNum = graph.vertexNum();
        boolean[] visited = new boolean[vNum];
        for (int i = 0; i < vNum; ++i)
            visited[i] = false;

        for (int i = 0; i < vNum; ++i) {
            if (!visited[i])
                dfs(i, visited);
        }
    }

    /**对一个节点进行深度优先遍历*/
    private void dfs(int target, boolean[] visited) {
        visit(target);
        visited[target] = true;
        for (int w = firstVertexLinkedToTarget(target); w != -1; w = nextVertexLinkedToTarget(target, w)) {
            if (!visited[w])
                dfs(w, visited);
        }
    }

    private int firstVertexLinkedToTarget(int target) {
        for (int i = 0; i < vNum; i++) {
            if (graph.getmMatrix()[target][i] == 1)
                return i;
        }
        return -1;
    }

    private int nextVertexLinkedToTarget(int target, int w) {
        for (int i = w + 1; i < vNum; ++i) {
            if (graph.getmMatrix()[target][i] == 1)
                return i;
        }
        return -1;
    }

    private void visit(int target) {
        System.out.print(graph.getV(target)+" ");
    }

    /**广度优先遍历*/

    public void BFS(Digraph di){

    }

    public static void main(String[] args){
        int matrix[][] = {
                {0, 1, 1, 1, 0, 0},//A
                {1, 0, 0, 1, 0, 0},//B
                {1, 0, 0, 1, 1, 0},//C
                {1, 1, 1, 0, 0, 1},//D
                {0, 0, 1, 0, 0, 1},//E
                {0, 0, 0, 1, 1, 0}//F
        };
        Digraph one = new Digraph(matrix);
        Ergodic test = new Ergodic();
        test.DFS(one);
    }
}
