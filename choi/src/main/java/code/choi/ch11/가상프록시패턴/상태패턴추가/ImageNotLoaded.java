package code.choi.ch11.가상프록시패턴.상태패턴추가;

import java.awt.Component;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;

import code.choi.ch11.가상프록시패턴.Icon;

public class ImageNotLoaded implements Icon {

    private ImageProxy imageProxy;
    private URL imageURL;
    private Thread retrievalThread;
    private boolean retrieving = false;

    public ImageNotLoaded(ImageProxy imageProxy, URL url) {
        this.imageProxy = imageProxy;
        this.imageURL = url;
    }

    @Override
    public int getIconWidth() {
        return 800;
    }

    @Override
    public int getIconHeight() {
        return 600;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.drawString("앨범 커버를 불러오는 중입니다.", x + 300, y + 190);
        if (!retrieving) {
            retrieving = true;
            retrievalThread = new Thread(() -> {
                try {
                    Icon imageLoaded = new ImageLoaded(new ImageIcon(imageURL, "CD Cover"));
                    imageProxy.setImage(imageLoaded);
                    c.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            retrievalThread.start();
        }
    }
}
