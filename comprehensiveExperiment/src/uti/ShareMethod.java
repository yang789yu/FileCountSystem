package uti;

import javax.swing.*;
import java.util.Objects;

public class ShareMethod {
    public static void shareMethod(JButton jButton,int x,int y,int width,int height){
        jButton.setBounds(x, y, width, height);
        jButton.setDoubleBuffered(false);
        jButton.setContentAreaFilled(false);
    }
}
