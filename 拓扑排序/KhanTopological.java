import java.util.LinkedList;
import java.util.List;

/**
 * 拓扑排序：khan算法
 */
public class KhanTopological {
    private List<Character> result;
    private LinkedList<Integer> setOfZeroIndegree;
    private Digraph digraph;
    private int eNum;
    private int vNum;
    private int[]indegree;

    public void Topologiacal(Digraph graph) {
        digraph = graph;
        result = new LinkedList<>();
        vNum = digraph.vertexNum();//节点数
        eNum = digraph.edgeNum();//边数
        setOfZeroIndegree = new LinkedList<>();//保存入度为0的节点
        indegree = digraph.indegrees();//记录开始时各个节点的入度

            for (int i = 0; i < indegree.length; i++) {
                if (indegree[i] == 0) {
                    setOfZeroIndegree.add(i);
                }
            }
            handle();


    }

    private void handle() {
        while (!setOfZeroIndegree.isEmpty()) {
            int v = setOfZeroIndegree.removeFirst();//移除当前入度为0的节点
            result.add(digraph.getV(v));//将该节点加入到结果集中
            for (int i =0;i<vNum;i++){
                if (digraph.remove(v,i)){
                    eNum--;
                    indegree[i]--;
                    if (indegree[i]==0)
                        setOfZeroIndegree.add(i);
                }
            }
        }
        //System.out.print(eNum);
        if (eNum != 0){
            throw new IllegalArgumentException("Has Circle!");
        }
        else {
            for (int i =0;i<result.size();i++)
            System.out.print(result.get(i)+" ");
        }
    }

}
