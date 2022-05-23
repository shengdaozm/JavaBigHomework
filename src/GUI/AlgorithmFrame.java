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
        // 根据time值选择对应演示方式（手动演示time为-1）->初始化paintG
        this.printgraph = new PreformGUIPrinting(graph,1000);
        // 创建有图结构的面板
        printgraph.myPanel = new MyPanel(graph);
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

        // 建立两个线程，分别执行两个算法
        Thread algo1 = new Thread() {
            @Override
            public void run() {
                // 算法执行前先把原图画出来
                printgraph.drawGraph(null);
                printgraph.graphPainting("P",firstVex);
            }
        };

        Thread algo2 = new Thread() {
            @Override
            public void run() {
                // 开始执行前先把原图画出来
                printgraph.drawGraph(null);
                printgraph.graphPainting("K",-1);
            }
        };
        /*
          每次对子线程进行操作前都要先让主线程休眠一段时间
         */
        // 开始演示按键
        startBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("开始演示算法");
                    if ("Prim算法演示".equals(algoName)) {
                        algo1.start();
                    }else {
                        algo2.start();
                    }
                    isStart = true;

                    // 如果是手动演示，则需要开始演示后就暂停演示
                    if (time == -1) {
                        //Thread.sleep(100);
                        // 暂停线程
                        // printgraph.pauseThread();
                    }
                    // 执行完开始按键后把按键置为不可点击
                    startBtn.setEnabled(false);
            }
        });

        // 点击下一步按键
        nextBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("下一步");

                try {
                    // 演示开始后再进行操作
                    if (isStart) {
                        // 先恢复进程，过0.1秒后再暂停线程
                        Thread.sleep(100);
                        printgraph.resumeThread();
                        Thread.sleep(100);
                        printgraph.pauseThread();
                        Thread.sleep(100);
                    }
                    // 演示结束后关闭按键
                    if (paintG.myPanel.isEnd) {
                        nextBtn.setEnabled(false);
                    }
                } catch(InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}

//暂停按钮的注释

/*
        // 暂定/继续按键
        pauseBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("暂停/继续演示");

                try {
                    if (!isPause) {
                        // 暂停线程
                        Thread.sleep(100);
                        paintG.pauseThread();
                        isPause = true;
                    }else {
                        // 恢复线程
                        Thread.sleep(100);
                        paintG.resumeThread();
                        isPause = false;
                    }

                    // 演示结束，关闭按键
                    if (paintG.myPanel.isEnd) {
                        pauseBtn.setEnabled(false);
                    }
                } catch(InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
        });
* */