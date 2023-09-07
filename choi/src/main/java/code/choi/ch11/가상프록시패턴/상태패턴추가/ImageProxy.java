package code.choi.ch11.가상프록시패턴.상태패턴추가;

import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;

import code.choi.ch11.가상프록시패턴.Icon;

public class ImageProxy implements Icon {

    private Icon image;

    public ImageProxy(final URL url) {
        image = new ImageNotLoaded(this, url);
    }

    @Override
    public int getIconWidth() {
        return image.getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return image.getIconHeight();
    }

    @Override
    public void paintIcon(final Component c, Graphics g, int x, int y) {
        image.paintIcon(c, g, x, y);
    }

    public void setImage(Icon image) {
        this.image = image;
    }
}
