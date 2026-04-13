package codefiles;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

public class StartGuiResizer extends ComponentAdapter {
    private JLabel background;
    private JLabel titleLabel;
    private ImageIcon titleIcon;
    private JButton playButton;
    private JButton quitButton;
    private double titleScale = 0.5;
    private int baseTitleWidth, baseTitleHeight;

    public StartGuiResizer(JLabel bg, JLabel tl, ImageIcon ti, JButton pb, JButton qb, int tw, int th) {
        this.background = bg;
        this.titleLabel = tl;
        this.titleIcon = ti;
        this.playButton = pb;
        this.quitButton = qb;
        this.baseTitleWidth = tw;
        this.baseTitleHeight = th;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        JFrame f = (JFrame) e.getComponent();
        background.setSize(f.getWidth(), f.getHeight());

        int fw = f.getWidth();
        int fh = f.getHeight();
        int bw = Math.max(100, (int)(fw * 0.26));
        int bh = Math.max(44, (int)(bw * 0.42));
        int gap = Math.max(10, bw / 13);
        int cx = (fw - bw) / 2;
        int by1 = fh - (2 * bh + 60);
        playButton.setBounds(cx, by1, bw, bh);
        quitButton.setBounds(cx, by1 + bh + gap, bw, bh);

        int sw = (int) (baseTitleWidth * titleScale * (fw / 800.0));
        int sh = (int) (baseTitleHeight * titleScale * (fw / 800.0));
        titleLabel.setIcon(new ImageIcon(titleIcon.getImage().getScaledInstance(sw, sh, Image.SCALE_SMOOTH)));
        titleLabel.setBounds((fw - sw) / 2, Math.max(20, fh / 20), sw, sh);
    }
}
