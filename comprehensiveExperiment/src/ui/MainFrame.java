package ui;

import uti.ShareMethod;

import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

public class MainFrame extends JFrame implements MouseListener, ComponentListener {

    JButton count = new JButton();
    JButton check = new JButton();
    JButton exit = new JButton();
    JLabel background = new JLabel();

    // private final String imagesPath=System.getProperty("user.dir")+ File.separator+"src"+File.separator+"images"+File.separator;
    // private final String imagesPath = System.getProperty("user.dir") + File.separator + "comprehensiveExperiment.jar" + File.separator + "src" + File.separator + "images" + File.separator;

    public MainFrame() {
        initJFrame();
        initView();
        this.setVisible(true);
        this.addMouseListener(this);
        //this.addMouseMotionListener(this);
        this.addComponentListener(this);
    }

    public void initView() {
        // 统计按钮
        ShareMethod.shareMethod(count,195, (int) (getHeight() * 0.25), 200, 80);
        //count.setBounds(195, (int) (getHeight() * 0.25), 200, 80);
        //count.setIcon(new ImageIcon("D:\\java\\comprehensiveExperiment\\images\\count4.png"));
        //count.setIcon(new ImageIcon(imagesPath+"count4.png"));
        count.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/count.png"))));
        //count.setDoubleBuffered(false);
        //count.setContentAreaFilled(false);
        this.getContentPane().add(count);
        count.addMouseListener(this);

        // 查询结果按钮
        ShareMethod.shareMethod(check,195, (int) (getHeight() * 0.4), 200, 80);
        //check.setBounds(195, (int) (getHeight() * 0.4), 200, 80);
        //count.setBounds(70, 80, 47, 17);
        //check.setIcon(new ImageIcon("D:\\java\\comprehensiveExperiment\\images\\check.png"));
        //check.setIcon(new ImageIcon(imagesPath+"check.png"));
        check.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/check.png"))));
        //check.setDoubleBuffered(false);
        //check.setContentAreaFilled(false);
        this.getContentPane().add(check);
        check.addMouseListener(this);

        // 退出按钮
        ShareMethod.shareMethod(exit,195, (int) (getHeight() * 0.55), 200, 80);
        //exit.setBounds(195, (int) (getHeight() * 0.55), 200, 80);
        //count.setBounds(70, 80, 47, 17);
        //ShareExitMethod.shareMethod(exit);
        //exit.setIcon(new ImageIcon("D:\\java\\comprehensiveExperiment\\images\\exit1.png"));
        //exit.setIcon(new ImageIcon(imagesPath+"exit1.png"));
        exit.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/exit1.png"))));
        //exit.setDoubleBuffered(false);
        //exit.setContentAreaFilled(false);
        this.getContentPane().add(exit);
        exit.addMouseListener(this);

        // 背景
        //background=new JLabel(new ImageIcon("D:\\java\\comprehensiveExperiment\\images\\background.png"));
        //background=new JLabel(new ImageIcon(imagesPath+"background.png"));
        background.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/preview.jpg"))));
        //background.setBounds(40, 25, 508, 560);
        background.setBounds(30, 25, 550, 560);
        this.getContentPane().add(background);

    }

    public void initJFrame() {
        this.setSize(650, 680);
        this.setTitle("简易文件信息统计系统");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //取消默认居中放置
        this.setLayout(null);
        //this.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
        //this.pack();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int frameWidth = this.getWidth();
        int frameHeight = this.getHeight();
        count.setLocation((frameWidth - 200) / 2, (int) (frameHeight * 0.25));
        check.setLocation((frameWidth - 200) / 2, (int) (frameHeight * 0.4));
        exit.setLocation((frameWidth - 200) / 2, (int) (frameHeight * 0.55));
        //background.setBounds(40, 25, frameWidth - 80, frameHeight - 55);
        background.setBounds(30, 25, frameWidth - 80, frameHeight - 55);
    }

    public void showDialog(String content) {

        JDialog jDialog = new JDialog();
        jDialog.setSize(200, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);// 居中
        jDialog.setModal(true);// 不关闭无法进行其他操作

        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, (getWidth() - 200) / 2, (int) (getHeight() * 0.5));
        jDialog.getContentPane().add(warning);
        jDialog.setVisible(true);
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == exit) {
            //showDialog("你确定要退出吗？");
            int choice = JOptionPane.showConfirmDialog(this, "你确定要退出吗？", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == count) {
            //this.setVisible(false);
            dispose();
            new FileCountFrame();
        } else if (e.getSource() == check) {
            dispose();
            ;
            new FileCheckFrame();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
