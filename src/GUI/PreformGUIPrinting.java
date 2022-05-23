package GUI;

import Algorithm.Graph;
import Algorithm.algorithm;

import java.util.List;

public class PreformGUIPrinting {

    public MyPanel myPanel;

    private final long sleepTime;
    private final List<String> vex;
    private final Graph graph;
    private final algorithm algo;

    /**
     * 用于控制线程（绘图）暂停或继续
     */
    private final Object lock = new Object();
    private volatile boolean pause = false;

    /**
     * 构造器，传入图结构和算法名字
     * @param graph 图结构
     */
    public PreformGUIPrinting(Graph graph, long time) {
        this.graph = graph;
        this.vex = graph.getVex();
        this.sleepTime = time;

        // 把图结构传入算法实现类中
        this.algo = new algorithm(graph);
    }

    /**
     * 执行面板中的paint方法，使用repaint()重复画图
     * @param vs 执行Prim算法每次得到的边（两个顶点）
     */
    public void drawGraph(String[] vs) {
        if (vs != null) {
            for (int i = 0;i < vex.size();i++) {
                if (vs[0].equals(vex.get(i))) {
                    myPanel.v1 = i;
                    myPanel.adj.add(i);
                }
                if (vs[1].equals(vex.get(i))) {
                    myPanel.v2 = i;
                    myPanel.adj.add(i);
                }
            }
        }

        //System.out.println("*******");
        myPanel.repaint();

        //System.out.println("&&&&");

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 绘制相应算法的图形
     * @param algoName 算法名字(P/K)
     * @param firstVex Prim算法起始顶点
     */
    public void graphPainting(String algoName, int firstVex) {
        String[] content = new String[2];
        // 获取最小边数组
        EdgeData[] edgeData;
        System.out.println("最小生成树为");

        // 根据算法名字调用对应算法具体实现方法
        if ("P".equals(algoName)) {
            edgeData = algo.MST_Prim(graph, firstVex);

            // 如果只有一个顶点（也是连通图），则直接把该顶点变红色，然后演示结束
            if (graph.getEdgeNum() == 0) {
                myPanel.pOrK = "P1";
                drawGraph((null));
            }
        }else {
            edgeData = algo.MST_Kruskal(graph);

            // 先把全部顶点选入
            myPanel.pOrK = "K1";
            drawGraph((null));
        }

        // 获取最小边的个数
        int minEdgeNum = algo.index;

        // 根据得到的最小边数组绘图
        for(int j = 0;j < minEdgeNum;j++) {
            content[0] = edgeData[j].start;
            content[1] = edgeData[j].end;

            // 判断是否暂停线程
            if (pause) {
                onPause();
            }
            // 具体绘制图形
            drawGraph(content);
        }

        // 最后再画一次图，把最小生成树显示出来
        myPanel.isEnd = true;
        myPanel.repaint();
    }
}
