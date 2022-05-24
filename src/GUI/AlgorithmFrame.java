package GUI;

import Algorithm.Position;
import Algorithm.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AlgorithmFrame {
    private final Position pos=new Position();
    public PreformGUIPrinting printgraph;
    //执行算法的名字
    private final String algoName;
    //可以删除
    private boolean isPause = false;
    private boolean isStart = false;

    public int firstVex=0;

    //下一步按钮的计数器，
    // 当计数器的数量到达最小边的数量时候，将下一步的按钮设置为不可用
    public int count=0;

    //构造函数完成算法演示的面板基本布局
    public AlgorithmFrame(Graph graph, String name, long time) {
        this.algoName = name;

        //算法演示初始化的界面设置
        final JFrame jf = new JFrame();
        jf.setTitle(name);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口的大小和布局
        jf.setSize(1000, 700);
        //算法演示窗口分开成上下两个部分
        jf.setLayout(new GridLayout(2,1));
//      +-------------------------------初始化第一个面板myPanel->用于显示图形和演示过程--------------------------------+
        this.printgraph = new PreformGUIPrinting(graph,1000);
        // 创建有图结构的面板
        printgraph.myPanel = new MyPanel(graph);//在这一步，图的买哪般已经画好了
//      +----------------------------------------------------------------------------+
//      +--------------------------------初始化第二个面板panel->用于显示按钮和文字标签----------------------------------+

        // 创建第二个面板，用于显示按钮和标签文字
        JPanel panel = new JPanel();

        // 控制演示的按键
        JButton startBtn = new JButton("开始演示");
        JButton nextBtn = new JButton("下一步");

        JLabel label  = new JLabel("<html><br><br><br>" +
                "------------------------------------" +
                "算法演示过程控制端" +
                "------------------------------------" +
                "<html>");

        panel.add(label);
        panel.add(startBtn);
        panel.add(nextBtn);
//      +----------------------------------------------------------------------------+

        // 在窗口中加入两个面板
        jf.add(printgraph.myPanel);
        jf.add(panel);

        // 根据算法名字显示对应的文字
        if ("Prim算法演示".equals(name)) {
            printgraph.myPanel.staus = "P";
        }else {
            printgraph.myPanel.staus = "K";
        }

//        +--------------------------------------------监听事件-----------------------------------------------+

        // 开始演示按键
        startBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("开始演示算法");
                    if ("Prim算法演示".equals(algoName)) {
                        // 算法执行前先把原图画出来
                        printgraph.drawGraph(null);
                        printgraph.graphPainting("P",firstVex);
                    }else {
                        // 开始执行前先把原图画出来
                        printgraph.drawGraph(null);
                        printgraph.graphPainting("K",-1);
                    }
                    isStart = true;

                    // 执行完开始按键后把按键置为不可点击
                    startBtn.setEnabled(false);
            }
        });

        // 点击下一步按键
        nextBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("下一步");
                    // 演示开始后再进行操作
                if (isStart) {
                    //实现的效果就是每点击一下按钮，算法的演示就会进行一次
                    //-----------------------------记得填写
                }
                    // 演示结束后关闭按键
                if (printgraph.myPanel.isEnd) {
                    nextBtn.setEnabled(false);
                }
            }
        });

        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}