package GUI;

import Algorithm.Graph;
import Algorithm.algorithm;
import Algorithm.Edge;

import java.util.List;

/**
 * 图形绘制，更改的实际操作的类
 */
public class PreformGUIPrinting {

    public MyPanel myPanel;

    private final long sleepTime;
    private final List<String> vex;
    private final Graph graph;
    private algorithm algo=new algorithm();
    private final Object lock = new Object();
    private volatile boolean pause = false;

    /**
     * 构造函数传入图结构和算法名字
     * @param graph 图的基本信息
     * @param time 执行算法的计时时刻
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
     * @param vs 执行算法每次得到的边（两个顶点）
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

        myPanel.repaint();
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 绘制相应算法的图形
     * @param algoName 算法名字(P/K)
     * @param firstVex Prim算法起始顶点，设置为0
     */
    public void graphPainting(String algoName, int firstVex) {
        String[] content = new String[2];
        // 获取最小边数组
        Edge[] edgeData;
        System.out.println("最小生成树为");

        // 根据算法名字调用对应算法具体实现方法
        if ("P".equals(algoName)) {
            edgeData = algo.p.changed_mit_prim(graph);

            // 如果只有一个顶点（也是连通图），则直接把该顶点变红色，然后演示结束
            if (graph.getM() == 0) {
                System.out.println("只有一个节点，算法结束");
                myPanel.judge = "P1";
                drawGraph((null));
            }
        }else {
            edgeData = algo.k.changed_mit_kruskal(graph);
            // 先把全部顶点选入
            myPanel.judge = "K1";
            drawGraph((null));
        }

        // 获取最小边的个数
        int minEdgeNum = graph.n-1;

        for(Edge e:edgeData) {
            System.out.println(e.start+" "+e.end);
        }
        // 根据得到的最小边数组绘图
        for(int j = 0;j < minEdgeNum;j++) {
            content[0] = edgeData[j].start;
            content[1] = edgeData[j].end;
            System.out.println(content[0]+" "+content[1]);
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

    /**
     * 线程的控制
     */
    public void onPause() {
        // 创建一把锁对象lock，调用wait()会释放锁，同时暂停线程；调用notify()函数会唤醒锁，从而重新获取这把锁
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 实现执行算法线程的暂停
     */
    public void pauseThread() {
        pause = true;
    }

    /**
     * 实现执行算法线程的恢复
     */
    public void resumeThread() {
        pause = false;
        // 调用notify()函数唤醒锁，恢复线程
        synchronized (lock){
            lock.notify();
        }
    }
}
