package codefiles;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

public class GUIResizer extends ComponentAdapter {
    private JLayeredPane layeredPane;
    private JPanel panel;
    private JPanel dimPanel;
    private JLabel characterLabel;
    private JButton button1, button2, button3, button4, psp;
    private JButton foodBarButton, sleepBarButton;
    private JButton sickMiscButton, brainRotMiscButton, examMiscButton;
    private JLabel coinBar, acadBar;
    private TypingGame typeGamePanel;
    private JFrame frame;

    public GUIResizer(JLayeredPane lp, JPanel p, JPanel dp, JLabel cl, JButton b1, JButton b2, JButton b3, JButton b4, JButton pspb, JButton fbb, JButton sbb, JButton smb, JButton brmb, JButton emb, JLabel cb, JLabel ab, TypingGame tgp, JFrame f) {
        this.layeredPane = lp;
        this.panel = p;
        this.dimPanel = dp;
        this.characterLabel = cl;
        this.button1 = b1;
        this.button2 = b2;
        this.button3 = b3;
        this.button4 = b4;
        this.psp = pspb;
        this.foodBarButton = fbb;
        this.sleepBarButton = sbb;
        this.sickMiscButton = smb;
        this.brainRotMiscButton = brmb;
        this.examMiscButton = emb;
        this.coinBar = cb;
        this.acadBar = ab;
        this.typeGamePanel = tgp;
        this.frame = f;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int fw = frame.getWidth();
        int fh = frame.getHeight();
        double scale = fw / 500.0; // Base from original 500x400

        // Panel and dim resize
        panel.setBounds(0, 0, fw, fh);
        dimPanel.setBounds(0, 0, fw, fh);
        layeredPane.setSize(fw, fh);

        // Status bars top left
        int barW = (int)(140 * scale);
        int barH = (int)(50 * scale);
        foodBarButton.setBounds(5, 3, barW, barH);
        ImageIcon fIcon = (ImageIcon) foodBarButton.getIcon();
        if (fIcon != null) foodBarButton.setIcon(new ImageIcon(fIcon.getImage().getScaledInstance((int)(120 * scale), barH, Image.SCALE_SMOOTH)));
        sleepBarButton.setBounds(5, (int)(53 * scale), barW, barH);
        ImageIcon sIcon = (ImageIcon) sleepBarButton.getIcon();
        if (sIcon != null) sleepBarButton.setIcon(new ImageIcon(sIcon.getImage().getScaledInstance((int)(120 * scale), barH, Image.SCALE_SMOOTH)));

        // Coin/Acad top right
        int barIconW = (int)(112 * scale);
        coinBar.setBounds((int)(248 * scale), (int)(-26 * scale), barIconW, barIconW);
        ImageIcon cIcon = (ImageIcon) coinBar.getIcon();
        if (cIcon != null) coinBar.setIcon(new ImageIcon(cIcon.getImage().getScaledInstance(barIconW, barIconW, Image.SCALE_SMOOTH)));
        coinBar.setFont(coinBar.getFont().deriveFont((float)(15 * scale)));
        acadBar.setBounds((int)(358 * scale), (int)(-26 * scale), barIconW, barIconW);
        ImageIcon aIcon = (ImageIcon) acadBar.getIcon();
        if (aIcon != null) acadBar.setIcon(new ImageIcon(aIcon.getImage().getScaledInstance(barIconW, barIconW, Image.SCALE_SMOOTH)));
        acadBar.setFont(acadBar.getFont().deriveFont((float)(15 * scale)));

        // PSP typer
        int pspW = (int)(110 * scale);
        psp.setBounds((int)(33 * scale), (int)(70 * scale), pspW, pspW);
        ImageIcon pIcon = (ImageIcon) psp.getIcon();
        if (pIcon != null) psp.setIcon(new ImageIcon(pIcon.getImage().getScaledInstance(pspW, pspW, Image.SCALE_SMOOTH)));

        // Typing game
        int tgW = (int)(100 * scale);
        int tgH = (int)(100 * scale);
        typeGamePanel.setBounds((int)(38 * scale), (int)(115 * scale), tgW, tgH);
        typeGamePanel.repaint(); // Trigger internal resize if it supports

        // Buttons large
        int btnSize = (int)(100 * scale);
        int btnIconSize = (int)(95 * scale);
        button1.setBounds(0, (int)(205 * scale), btnSize, btnSize);
        ImageIcon i1 = (ImageIcon) button1.getIcon();
        if (i1 != null) button1.setIcon(new ImageIcon(i1.getImage().getScaledInstance(btnIconSize, btnIconSize, Image.SCALE_SMOOTH)));
        button2.setBounds((int)(60 * scale), (int)(190 * scale), btnSize, btnSize);
        ImageIcon i2 = (ImageIcon) button2.getIcon();
        if (i2 != null) button2.setIcon(new ImageIcon(i2.getImage().getScaledInstance(btnIconSize, btnIconSize, Image.SCALE_SMOOTH)));
        button3.setBounds((int)(380 * scale), (int)(275 * scale), btnSize, btnSize);
        ImageIcon i3 = (ImageIcon) button3.getIcon();
        if (i3 != null) button3.setIcon(new ImageIcon(i3.getImage().getScaledInstance(btnIconSize, btnIconSize, Image.SCALE_SMOOTH)));

        // Lamp button
        int lampW = (int)(100 * scale);
        int lampH = (int)(125 * scale);
        int lampIconW = (int)(230 * scale);
        button4.setBounds((int)(360 * scale), (int)(83 * scale), lampW, lampH);
        ImageIcon i4 = (ImageIcon) button4.getIcon();
        if (i4 != null) button4.setIcon(new ImageIcon(i4.getImage().getScaledInstance(lampIconW, (int)(195 * scale), Image.SCALE_SMOOTH)));

        // Character center
        int charW = (int)(250 * scale);
        int charH = (int)(250 * scale);
        characterLabel.setBounds((fw - charW) / 2, (fh - charH) / 2 - 50, charW, charH);
        ImageIcon charIcon = (ImageIcon) characterLabel.getIcon();
        if (charIcon != null) characterLabel.setIcon(new ImageIcon(charIcon.getImage().getScaledInstance(charW, charH, Image.SCALE_SMOOTH)));

        // Misc buttons right side
        int miscW = (int)(90 * scale);
        int miscH = (int)(100 * scale);
        int miscIconH = (int)(100 * scale);
        sickMiscButton.setBounds((int)(350 * scale), (int)(195 * scale), miscW, miscH);
        ImageIcon smIcon = (ImageIcon) sickMiscButton.getIcon();
        if (smIcon != null) sickMiscButton.setIcon(new ImageIcon(smIcon.getImage().getScaledInstance((int)(80 * scale), miscIconH, Image.SCALE_SMOOTH)));
        brainRotMiscButton.setBounds((int)(350 * scale), (int)(195 * scale), miscW, miscH);
        ImageIcon brIcon = (ImageIcon) brainRotMiscButton.getIcon();
        if (brIcon != null) brainRotMiscButton.setIcon(new ImageIcon(brIcon.getImage().getScaledInstance((int)(80 * scale), miscIconH, Image.SCALE_SMOOTH)));
        examMiscButton.setBounds((int)(350 * scale), (int)(195 * scale), miscW, miscH);
        ImageIcon exIcon = (ImageIcon) examMiscButton.getIcon();
        if (exIcon != null) examMiscButton.setIcon(new ImageIcon(exIcon.getImage().getScaledInstance((int)(80 * scale), miscIconH, Image.SCALE_SMOOTH)));

        panel.revalidate();
        panel.repaint();
    }
}
