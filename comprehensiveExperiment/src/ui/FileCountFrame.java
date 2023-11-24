package ui;

import uti.ShareMethod;
import uti.OutputResult;

import java.awt.event.*;
import java.io.File;
import java.util.Objects;
import javax.swing.*;



public class FileCountFrame extends JFrame implements ComponentListener, MouseListener {
    JButton count = new JButton();
    JButton path = new JButton();
    JButton quit = new JButton();
    JLabel background=new JLabel();
    //private final String imagesPath=System.getProperty("user.dir")+ File.separator+"src"+File.separator+"images"+File.separator;
    private final String imagesPath=System.getProperty("user.dir")+File.separator+"comprehensiveExperiment.jar" +File.separator+"src"+File.separator+"images"+File.separator;
    public FileCountFrame(){
        initJFrame();
        initView();
        this.addComponentListener(this);
        this.addMouseListener(this);
        this.setVisible(true);
    }

    public void initJFrame(){
        this.setSize(650,680);
        this.setTitle("文件信息统计");
        //this.setAlwaysOnTop(true);
        // 界面居中
        this.setLocationRelativeTo(null);
        // 关闭模式
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // 取消默认居中放置
        this.setLayout(null);
    }

    public void initView(){
        ShareMethod.shareMethod(count,195,140,200,80);
        //count.setBounds(195,140,200,80);
        //count.setIcon(new ImageIcon(imagesPath+"countFile.png"));
        count.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/countFile.png"))));
        //count.setDoubleBuffered(false);
        //count.setContentAreaFilled(false);
        this.getContentPane().add(count);
        count.addMouseListener(this);

        ShareMethod.shareMethod(path,195,245,200,80);
        //path.setBounds(195,245,200,80);
        //path.setIcon(new ImageIcon(imagesPath+"project.png"));
        path.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/project.png"))));
        //path.setDoubleBuffered(false);
        //path.setContentAreaFilled(false);
        this.getContentPane().add(path);
        path.addMouseListener(this);

        ShareMethod.shareMethod(quit,195,350,200,80);
        //quit.setBounds(195,350,200,80);
        //quit.setIcon(new ImageIcon(imagesPath+"return.png"));
        quit.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/return.png"))));
        //quit.setDoubleBuffered(false);
        //quit.setContentAreaFilled(false);
        this.getContentPane().add(quit);
        quit.addMouseListener(this);

        // 背景
        //background=new JLabel(new ImageIcon(imagesPath+"background.png"));
        background.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/preview.jpg"))));
        //background.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/background.png"))));
        //background.setBounds(40, 25, 508, 560);
        background.setBounds(30, 25, 550, 560);
        this.getContentPane().add(background);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        count.setLocation((this.getWidth()-200)/2,(int) (this.getHeight()*0.25));
        path.setLocation((this.getWidth()-200)/2,(int) (this.getHeight()*0.4));
        quit.setLocation((this.getWidth()-200)/2,(int) (this.getHeight()*0.55));
        //background.setBounds(40, 25, this.getWidth() - 80, this.getHeight() - 55);
        background.setBounds(30, 25, this.getWidth() - 80, this.getHeight() - 55);
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
        if (e.getSource()==quit){
            //this.setVisible(false);
            dispose();
            new MainFrame();
        }else if(e.getSource()==path){
            String projectPath=System.getProperty("user.dir");
            JOptionPane.showMessageDialog(this,projectPath);
        }else if(e.getSource()==count){

            //JOptionPane.showMessageDialog(this,"测试一下");
            String folderName=JOptionPane.showInputDialog("请输入你要分析的目录的绝对路径");
            if(folderName == null || folderName.isEmpty()){
                System.out.println("用户没输入任何内容");
            }
            else{
                System.out.println(folderName);
                File folder = new File(folderName);
                if(folder.isDirectory()){
                    CountFile countfile=new CountFile(folder);
                    countfile.Count();
                    int result= OutputResult.output(folder,countfile);
                    if(result == 1){
                        JOptionPane.showMessageDialog(this,"文件统计完成，可以查看结果");
                    } else if (result==2) {
                        JOptionPane.showMessageDialog(this,"内容写入时发生错误");
                    } else if (result==3) {
                        JOptionPane.showMessageDialog(this,"创建文件夹失败");
                    } else {
                        JOptionPane.showMessageDialog(this,"出现错误");
                    }
                }
                else {
                    System.out.printf("错误，%s不是一个目录名或不存在！\n",folderName);
                    JOptionPane.showMessageDialog(this,"["+folderName+"]"+"不是合法目录名或不存在！");
                }
            }
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
