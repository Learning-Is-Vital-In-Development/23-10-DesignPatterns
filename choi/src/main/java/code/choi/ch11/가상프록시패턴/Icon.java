package code.choi.ch11.가상프록시패턴;

import java.awt.Component;
import java.awt.Graphics;

public interface Icon {
    int getIconWidth();
    int getIconHeight();
    void paintIcon(final Component c, Graphics g, int x, int y);
}
