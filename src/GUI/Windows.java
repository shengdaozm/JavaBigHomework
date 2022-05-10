package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Algorithm.*;

public class Windows {
    JFrame jf;
    algorithm al;

    void placeComponents(JPanel panel){
        panel.setLayout(null);

        JLabel userLabel = new JLabel("结点数:");
        userLabel.setBounds(10,120,80,25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,120,165,25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("边数:");
        passwordLabel.setBounds(10,150,80,25);
        panel.add(passwordLabel);

        JTextField passwordText = new JTextField(20);
        passwordText.setBounds(100,150,165,25);
        panel.add(passwordText);

        JButton loginButton = new JButton("详细信息");
        loginButton.setBounds(120, 185, 100, 25);
        //监听器设定
        panel.add(loginButton);

        JButton loginButton1 = new JButton("Prim算法演示");
        loginButton1.setBounds(320, 100, 150, 25);
        //监听器设定
        panel.add(loginButton1);

        JButton loginButton2 = new JButton("Kruskal算法演示");
        loginButton2.setBounds(320, 130, 150, 25);
        //监听器设定
        panel.add(loginButton2);

        JButton loginButton3 = new JButton("关于我们");
        loginButton3.setBounds(320, 160, 150, 25);
        loginButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //弹出窗口
                JFrame jf_show = new JFrame();
                jf_show.setVisible(true);
                jf_show.setBounds(450, 250, 400, 250);
                //文本设置
                JLabel jl_message = new JLabel("B20030603李妍  B20030624周冕  B20030626徐少杰", JLabel.CENTER);
                jf_show.add(jl_message);
                jl_message.setBounds(500, 0, 690, 20);
            }
        });
        panel.add(loginButton3);

        JButton loginButton4 = new JButton("离开我们");
        loginButton4.setBounds(320, 190, 150, 25);
        loginButton4.addActionListener(new ActionListener() {
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
        panel.add(loginButton4);
    }

    void control(){
        JFrame frame = new JFrame("最小代价生成数算法演示");
        frame.setBounds(400, 200, 550, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    //测试类
    public static void main(String[] args){
        Windows w=new Windows();
        w.control();

    }
}
