package code.choi.ch11.가상프록시패턴.상태패턴추가;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import code.choi.ch11.가상프록시패턴.Icon;

public class ImageLoaded implements Icon {

    volatile ImageIcon imageIcon;
    public ImageLoaded(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    public int getIconWidth() {
        return imageIcon.getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return imageIcon.getIconHeight();
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        imageIcon.paintIcon(c, g, x, y);
    }
}
