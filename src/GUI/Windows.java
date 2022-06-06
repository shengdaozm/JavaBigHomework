package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Algorithm.*;
import check.Check;

/**
 * 程序的主界面
 */
public class Windows {
    JFrame jf;
    algorithm al;
    Boolean ok=false;
    JButton Button1;
    JButton Button2;
    Check check;

    /**
     * 主界面的布局设置
     * @param panel Jpanel对象
     */
    public void placeComponents(JPanel panel){
        panel.setLayout(null);

        JLabel edgescountLabel = new JLabel("结点数:");
        edgescountLabel.setBounds(10,120,80,25);
        panel.add(edgescountLabel);

        //节点数输入文本框
        JTextField edgescountText = new JTextField(20);
        edgescountText.setBounds(100,120,165,25);
        panel.add(edgescountText);

        JLabel vexLabel = new JLabel("边数:");
        vexLabel.setBounds(10,150,80,25);
        panel.add(vexLabel);

        //边数输入文本框
        JTextField vexText = new JTextField(20);
        vexText.setBounds(100,150,165,25);
        panel.add(vexText);

        //输入按钮
        JButton inputButton = new JButton("详细信息");
        inputButton.setBounds(120, 185, 100, 25);
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //完成边和点的数目的赋值
                String n_str = edgescountText.getText();
                String m_str = vexText.getText();
                al.algo_init(Integer.parseInt(n_str), Integer.parseInt(m_str));
                //异常情况处理
                if(Integer.parseInt(n_str)==1) {
                    inputButton.setEnabled(false);
                    Button1.setEnabled(true);
                    Button2.setEnabled(true);
                    al.g.produce_vex();
                    return;
                }
                if(Integer.parseInt(m_str)==0) {
                    JOptionPane.showMessageDialog(//Yes的返回值是0
                            jf,
                            "输入数据错误，快重新输入",
                            "异常",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                //弹出新窗口，读入信息进行建图操作
                JButton submit;
                JLabel jl1, jl2, jl3;
                JTextField jt1, jt2,jt3;
                //窗体设置
                JFrame inputjf = new JFrame("详细数据的输入");
                inputjf.setLayout(null);

                //控件的设置
                jl1 = new JLabel("第1条边的源点:");
                jl1.setBounds(40, 30, 100, 50);
                inputjf.add(jl1);
                jl2 = new JLabel("第1条边的终点:");
                jl2.setBounds(40, 80, 100, 50);
                inputjf.add(jl2);
                jl3 = new JLabel("第1条边的权值:");
                jl3.setBounds(40, 130, 100, 50);
                inputjf.add(jl3);

                //按钮的初始化
                submit = new JButton("输入");
                submit.setBounds(170, 200, 90, 30);
                inputjf.add(submit);

                //文本框的初始化
                jt1 = new JTextField();
                jt1.setBounds(140, 40, 100, 30);
                inputjf.add(jt1);
                jt2 = new JTextField();
                jt2.setBounds(140, 90, 100, 30);
                inputjf.add(jt2);
                jt3 = new JTextField();
                jt3.setBounds(140, 140, 100, 30);
                inputjf.add(jt3);

                //将按钮设置为正常的可以按动
                Button1.setEnabled(true);
                Button2.setEnabled(true);

                //其余设置
                inputjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                inputjf.setBounds(400, 300, 500, 400);
                inputjf.setVisible(true);

                //输入按钮的监听器
                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //注意特殊案例——1个节点，0条边的情况
                        al.cnt++;
                        if(al.cnt>al.m) {
                            al.g.produce_vex();
                            inputjf.dispose();
                        }
                        String u_str=jt1.getText();
                        String v_str=jt2.getText();
                        String w_str=jt3.getText();
                        al.getegde(Integer.parseInt(u_str),Integer.parseInt(v_str),Integer.parseInt(w_str));
                        System.out.println(Integer.parseInt(u_str)+" "+Integer.parseInt(v_str)+" "+Integer.parseInt(w_str));
                        jt1.setText("");
                        jt2.setText("");
                        jt3.setText("");
                        jl1.setText("第"+al.cnt+"条边的源点:");
                        jl2.setText("第"+al.cnt+"条边的终点:");
                        jl3.setText("第"+al.cnt+"条边的权值:");
                    }
                });
            }
        });
        panel.add(inputButton);

        //算法1演示——prim
        Button1 = new JButton("Prim算法演示");
        Button1.setBounds(320, 100, 150, 25);
        Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(judgegraph()) {
                    AlgorithmFrame alframe=new AlgorithmFrame(al.g,"Prim算法演示",-1);
                } else {
                    JOptionPane.showMessageDialog(//Yes的返回值是0
                            jf,
                            "输入数据错误，快重新输入",
                            "异常",
                            JOptionPane.ERROR_MESSAGE
                    );
                    al.g.clear();
                }
            }
        });
        Button1.setEnabled(false);
        panel.add(Button1);

        //算法2演示——Kruskal
        Button2 = new JButton("Kruskal算法演示");
        Button2.setBounds(320, 130, 150, 25);
        Button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(judgegraph()) {
                    AlgorithmFrame alframe=new AlgorithmFrame(al.g,"Kruskal算法演示",-1);
                } else {
                    JOptionPane.showMessageDialog(//Yes的返回值是0
                            jf,
                            "输入数据错误,快重新输入",
                            "异常",
                            JOptionPane.ERROR_MESSAGE
                    );
                    al.g.clear();
                }
            }
        });
        Button2.setEnabled(false);
        panel.add(Button2);

        //右侧功能表按钮3-关于我们
        JButton Button3 = new JButton("关于我们");
        Button3.setBounds(320, 160, 150, 25);
        Button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //弹出窗口
                JFrame jf_show = new JFrame("感谢使用");
                jf_show.setVisible(true);
                jf_show.setBounds(450, 250, 360, 350);
                //图片的插入
                Image image = null;
                try {
                    image = ImageIO.read(new FileInputStream("C:\\Users\\21147\\Desktop\\JavaBigHomework\\src\\images\\team.jpg"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                ImageIcon icon = new ImageIcon(image);
                JLabel jl_image=new JLabel();
                jl_image.setIcon(icon);
                jf_show.add(jl_image);
                System.out.println("      B20030603李妍");
                System.out.println("      B20030624周冕");
                System.out.println("      B20030625耿傅生");
                System.out.println("      B20030626徐少杰");
            }
        });
        panel.add(Button3);

        //右侧功能按钮4—离开我们
        JButton Button4 = new JButton("离开我们");
        Button4.setBounds(320, 190, 150, 25);
        Button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ok = JOptionPane.showConfirmDialog(//Yes的返回值是0
                        jf,
                        "真的要离开我们了吗？^_^",
                        "退出程序",
                        JOptionPane.ERROR_MESSAGE
                );
                if (ok == 0) {
                    System.exit(0);
                }
            }
        });
        panel.add(Button4);
    }

    /**
     * 主界面的面板控制
     */
    public void control(){
        al=new algorithm();
        jf = new JFrame("最小代价生成数算法演示");
        jf.setBounds(400, 200, 550, 450);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        jf.add(panel);
        placeComponents(panel);
        jf.setVisible(true);
    }

    /**
     * 判断输入的图是否为连通图
     * @return 是是否为连通图
     */
    public Boolean judgegraph() {
        check=new Check(al.g);
        return check.ischeck();
    }
}
