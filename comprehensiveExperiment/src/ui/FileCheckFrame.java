package ui;

import uti.ShareMethod;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Objects;

public class FileCheckFrame extends JFrame implements ComponentListener, MouseListener {

    JLabel background = new JLabel();
    JButton check = new JButton();
    JButton exit = new JButton();
    //private final String imagesPath=System.getProperty("user.dir")+ File.separator+"src"+File.separator+"images"+File.separator;
    private final String imagesPath = System.getProperty("user.dir") + File.separator + "comprehensiveExperiment.jar" + File.separator + "src" + File.separator + "images" + File.separator;

    public FileCheckFrame() {
        initJFrame();
        initView();
        this.setVisible(true);
        this.addMouseListener(this);
        this.addComponentListener(this);
    }

    public void check() {
        String projectPath = System.getProperty("user.dir");
        String resultFolderName = projectPath + File.separator + "result";
        File resultFile = new File(resultFolderName);
        StringBuilder builder = new StringBuilder();
        if (resultFile.isDirectory() && resultFile.exists()) {
            File[] files = resultFile.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
            if (files != null && files.length > 0) {
                int index = 1;
                for (int i = 0; i < files.length; i++) {
                    System.out.println(index + "." + files[i].getName());
                    builder.append(index);
                    builder.append(".");
                    builder.append(files[i].getName());
                    builder.append('\n');
                    index++;
                }
                builder.append("请选择要查看的结果文件（0表示放弃）：");
                String str = JOptionPane.showInputDialog(builder.toString());
                if (str == null || str.isEmpty()) {
                    System.out.println("用户没输入");
                } else {
                    int choice = Integer.parseInt(str);
                    System.out.print("请选择要查看的结果文件的序号（0表示放弃）：");
                    //int choice=Integer.parseInt(sc.nextLine());
                    if (choice == 0) {
                        System.out.println("结束");
                        int confirm = JOptionPane.showConfirmDialog(this, "你确定要退出吗？", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (confirm == JOptionPane.YES_OPTION) {
                            dispose();
                            new MainFrame();
                        }
                    } else if (choice >= 1 && choice < index) {
//                    String filePath=resultFolderName+File.separator+files[choice-1];
//                    System.out.println(filePath);
//                    System.out.println(files[choice-1]);
                        ReadTxtFile readTxtFile = new ReadTxtFile(files[choice - 1].toString());
                        readTxtFile.ReadFile();

                        JTextArea textArea = new JTextArea();
                        textArea.setText(readTxtFile.getResult());
                        JScrollPane scrollPane = new JScrollPane(textArea);

                        JFrame frame = new JFrame();
                        frame.add(scrollPane);
                        frame.setSize(600, 600);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);

                        //JOptionPane.showMessageDialog(this,readTxtFile.getResult());
                    } else {
                        JOptionPane.showMessageDialog(this, "输入编号错误");
                        System.out.println("输入编号错误");
                    }
                }

            } else {
                JOptionPane.showMessageDialog(this, "还没有分析结果");
                System.out.println("还没有分析结果！");
            }
        } else {
            JOptionPane.showMessageDialog(this, "还不存在分析结果");
            System.out.println("还不存在分析结果");
        }
    }

    public void initView() {

        // 查看按钮
        ShareMethod.shareMethod(check,195, (int) (this.getHeight() * 0.3), 200, 80);
        //check.setBounds(195, (int) (this.getHeight() * 0.3), 200, 80);
        //count.setBounds(70, 80, 47, 17);
        //check.setIcon(new ImageIcon("D:\\java\\comprehensiveExperiment\\images\\look.jpg"));
        //check.setIcon(new ImageIcon(imagesPath+"look.jpg"));
        check.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/look.jpg"))));
        //check.setDoubleBuffered(false);
        //check.setContentAreaFilled(false);
        this.getContentPane().add(check);
        check.addMouseListener(this);

        // 退出按钮
        ShareMethod.shareMethod(exit,195, (int) (this.getHeight() * 0.6), 200, 80);
        //exit.setBounds(195, (int) (this.getHeight() * 0.6), 200, 80);
        //count.setBounds(70, 80, 47, 17);
        //exit.setIcon(new ImageIcon("D:\\java\\comprehensiveExperiment\\images\\return.png"));
        //exit.setIcon(new ImageIcon(imagesPath+"return.png"));
        exit.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/return.png"))));
        //exit.setDoubleBuffered(false);
        //exit.setContentAreaFilled(false);

        this.getContentPane().add(exit);
        exit.addMouseListener(this);

        //background=new JLabel(new ImageIcon("D:\\java\\comprehensiveExperiment\\images\\background.png"));
        //background=new JLabel(new ImageIcon(imagesPath+"background.png"));
        background.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/preview.jpg"))));
        //background.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/background.png"))));
        background.setBounds(40, 25, 508, 560);
        background.setBounds(30, 25, 508, 560);
        this.getContentPane().add(background);
    }

    public void initJFrame() {
        this.setSize(650, 680);
        this.setTitle("简易文件信息统计系统");
        this.setAlwaysOnTop(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //取消默认居中放置
        this.setLayout(null);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int frameWidth = this.getWidth();
        int frameHeight = this.getHeight();
        //background.setBounds(40, 25, frameWidth - 80, frameHeight - 55);
        background.setBounds(30, 25, frameWidth - 80, frameHeight - 55);
        check.setLocation((frameWidth - 200) / 2, (int) (frameHeight * 0.3));
        exit.setLocation((frameWidth - 200) / 2, (int) (frameHeight * 0.6));

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
        if (e.getSource() == check) {
            check();
        } else if (e.getSource() == exit) {
            dispose();
            new MainFrame();
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
