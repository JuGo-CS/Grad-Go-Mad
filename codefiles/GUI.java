package codefiles;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GUI{
    public static GUI guiInstance = null;
    public static TypingGame typeGamePanel;
    static int overallMoney = 0;
    static int overallAcademicPoints = 0;
    static JLabel coinBar = new JLabel(String.valueOf(GUI.overallMoney), SwingConstants.CENTER);
    static JLabel acadBar = new JLabel(String.valueOf(GUI.overallAcademicPoints), SwingConstants.CENTER);
    JLabel typingBar = new JLabel();
    static JButton foodBarButton = new JButton();
    static JButton sleepBarButton = new JButton();
    static JButton sickMiscButton = new JButton();
    static JButton brainRotMiscButton = new JButton();
    static JButton examMiscButton = new JButton();
	public static Object panel;
    static JFrame frame = null;

    static final int SPRITE_MULTIPLIER = 2;

    public static Timer sleepTimer = null;
    private static TimerTask sleepTask;

    public JFrame getFrame() {
        return frame;
    }

    public static GUI getInstanceGUI(){
        if(guiInstance == null) {
            guiInstance = new GUI();
        }

        return guiInstance;
    }

    private GUI() {
        frame = new JFrame("Grad Go Mad!");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(500, 400);
        frame.setResizable(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        JLayeredPane layeredPane = new JLayeredPane();
        frame.setContentPane(layeredPane);

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String gifPath = "assets/needs/coding.gif";
                ImageIcon icon = new ImageIcon(gifPath);
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);  // SCALES TO FULLSCREEN
            }
        };
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);  

        // RESIZE HANDLER - scales panel + GIF on window resize
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
                panel.repaint();
            }
        });

        //     @Override
        //     protected void paintComponent(Graphics g) {
        //         super.paintComponent(g);
        //         String gifPath = "assets/needs/coding.gif";
        //         ImageIcon icon = new ImageIcon(gifPath);
        //         g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
        //     }
        // };

        // panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        
        // Centered GIF
        JLabel characterLabel = new JLabel();
        ImageIcon characterIcon = new ImageIcon("assets/needs/mycharver1_750.gif");
        characterLabel.setIcon(characterIcon);
        characterLabel.setBounds(600, 130, 1000, 1000);
        characterLabel.setOpaque(false); 
        panel.add(characterLabel);

        // Button 1
        JButton button1 = new JButton();
        ImageIcon buttonIcon1 = new ImageIcon("assets/needs/plate.png");
        Image scaledImage1 = buttonIcon1.getImage().getScaledInstance(100 * SPRITE_MULTIPLIER, 100 * SPRITE_MULTIPLIER, Image.SCALE_SMOOTH);
        button1.setIcon(new ImageIcon(scaledImage1));
        button1.setBounds(120, 650, 100 * SPRITE_MULTIPLIER, 100 * SPRITE_MULTIPLIER);
        button1.setFocusPainted(false);
        button1.setContentAreaFilled(false);
        button1.setOpaque(false);
        button1.setBorderPainted(false);
        button1.addActionListener(e -> {
            StartGui.playSound("assets/soundeffects/food.wav");
            new ButtonOne().showFoodOptions();  
        });
        
        // Button 2
        JButton button2 = new JButton();
        ImageIcon buttonIcon2 = new ImageIcon("assets/needs/studysheesh.png");
        Image scaledImage2 = buttonIcon2.getImage().getScaledInstance(75 * SPRITE_MULTIPLIER, 75 * SPRITE_MULTIPLIER, Image.SCALE_SMOOTH);
        button2.setIcon(new ImageIcon(scaledImage2));
        button2.setBounds(250, 550, 100 * SPRITE_MULTIPLIER, 100 * SPRITE_MULTIPLIER);
        button2.setFocusPainted(false);
        button2.setContentAreaFilled(false);
        button2.setOpaque(false);
        button2.setBorderPainted(false);
        button2.addActionListener(e -> {
            StartGui.playSound("assets/soundeffects/openbook.wav");
            new ButtonTwo().showAcadOptions();  
        });

        // Button 3
        JButton button3 = new JButton(); 
        ImageIcon buttonIcon3 = new ImageIcon("assets/needs/warning.png"); 
        Image scaledImage3 = buttonIcon3.getImage().getScaledInstance(95 * SPRITE_MULTIPLIER + 95, 95 * SPRITE_MULTIPLIER + 95, Image.SCALE_SMOOTH);
        button3.setIcon(new ImageIcon(scaledImage3)); 
        button3.setBounds(1600, 700, 100 * SPRITE_MULTIPLIER + 95, 100 * SPRITE_MULTIPLIER + 95); 
        button3.setFocusPainted(false);
        button3.setContentAreaFilled(false); 
        button3.setOpaque(false);
        button3.setBorderPainted(false); 
        button3.addActionListener(e -> {
            StartGui.playSound("assets/soundeffects/batteryminus.wav");
            new ButtonThree().showMiscOptions();  
        });
        
        // Lamp
        JPanel dimPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        dimPanel.setBounds(0, 0, 2000, 1100);
        dimPanel.setOpaque(false);
        dimPanel.setVisible(false);
        layeredPane.add(dimPanel, JLayeredPane.PALETTE_LAYER);

        JButton button4 = new JButton();
        ImageIcon buttonIcon4 = new ImageIcon("assets/needs/lampara.png");
        Image scaledImage4 = buttonIcon4.getImage().getScaledInstance(230 * SPRITE_MULTIPLIER, 210 * SPRITE_MULTIPLIER, Image.SCALE_SMOOTH);
        button4.setIcon(new ImageIcon(scaledImage4));
        button4.setBounds(1550,  275, 100 * SPRITE_MULTIPLIER, 125 * SPRITE_MULTIPLIER);
        button4.setFocusPainted(false);
        button4.setContentAreaFilled(false);
        button4.setOpaque(false);
        button4.setBorderPainted(false);
        panel.add(button4);

        final boolean[] isSleeping = {false};

        button4.addActionListener(e -> {
            if (isSleeping[0]) {
                // Turn off
                StartGui.stopMusic();
                dimPanel.setVisible(false);
                StartGui.playSound("assets/soundeffects/off.wav");
                if (sleepTimer != null) {
                    sleepTimer.cancel();
                    sleepTimer.purge();
                    sleepTimer = null;
                }
                typeGamePanel.setTypingEnabled(true);
                isSleeping[0] = false;
            } else {
                // Turn on
                StartGui.playMusic("assets/soundeffects/sleep.wav");
                dimPanel.setVisible(true);
                StartGui.playSound("assets/soundeffects/on.wav");
                sleepTask = new TimerTask() {
                    public void run() {
                        Player.createPlayer().incrementSleep(8);
                    }
                };
                sleepTimer = new Timer(true);
                sleepTimer.scheduleAtFixedRate(sleepTask, 0, 1000);
                typeGamePanel.setTypingEnabled(false);
                isSleeping[0] = true;
            }
        });
        
        // Status bars...
        ImageIcon foodBarIconButton = new ImageIcon("assets/statusBars/foodBar5.png");
        Image foodBarScaledImage = foodBarIconButton.getImage().getScaledInstance(120 * SPRITE_MULTIPLIER + 120, 50 * SPRITE_MULTIPLIER + 50, Image.SCALE_SMOOTH);
        foodBarButton.setIcon(new ImageIcon(foodBarScaledImage));
        foodBarButton.setBounds(15, 10, 140 * SPRITE_MULTIPLIER + 140, 50 * SPRITE_MULTIPLIER + 50);
        foodBarButton.setFocusPainted(false);
        foodBarButton.setContentAreaFilled(false);
        foodBarButton.setOpaque(false);
        foodBarButton.setBorderPainted(false);
        panel.add(foodBarButton);

        ImageIcon sleepBarIconButton = new ImageIcon("assets/statusBars/sleepBar5.png");
        Image sleepBarScaledImage = sleepBarIconButton.getImage().getScaledInstance(120 * SPRITE_MULTIPLIER + 120, 50 * SPRITE_MULTIPLIER + 50, Image.SCALE_SMOOTH);
        sleepBarButton.setIcon(new ImageIcon(sleepBarScaledImage));
        sleepBarButton.setBounds(15, 175, 140 * SPRITE_MULTIPLIER + 140, 50 * SPRITE_MULTIPLIER + 50);
        sleepBarButton.setFocusPainted(false);
        sleepBarButton.setContentAreaFilled(false);
        sleepBarButton.setOpaque(false);
        sleepBarButton.setBorderPainted(false);
        panel.add(sleepBarButton);

        // Misc buttons (sick, brainrot, exam)
        ImageIcon sickMiscImage = new ImageIcon("assets/miscphotos/miscSick.png"); 
        Image sickMiscScaledImage = sickMiscImage.getImage().getScaledInstance(80 * SPRITE_MULTIPLIER, 100 * SPRITE_MULTIPLIER, Image.SCALE_SMOOTH);
        sickMiscButton.setIcon(new ImageIcon(sickMiscScaledImage));
        sickMiscButton.setBounds(1350, 550, 90 * SPRITE_MULTIPLIER, 100 * SPRITE_MULTIPLIER);
        sickMiscButton.setFocusPainted(false);
        sickMiscButton.setContentAreaFilled(false);
        sickMiscButton.setOpaque(false);
        sickMiscButton.setBorderPainted(false);
        sickMiscButton.setVisible(false); 
        
        ImageIcon brainRotMiscImage = new ImageIcon("assets/miscphotos/miscBrainRot.png");
        Image brainRotScaledImage = brainRotMiscImage.getImage().getScaledInstance(80 * SPRITE_MULTIPLIER, 100 * SPRITE_MULTIPLIER, Image.SCALE_SMOOTH);
        brainRotMiscButton.setIcon(new ImageIcon(brainRotScaledImage));
        brainRotMiscButton.setBounds(1350, 550, 90 * SPRITE_MULTIPLIER, 100 * SPRITE_MULTIPLIER);
        brainRotMiscButton.setFocusPainted(false);
        brainRotMiscButton.setContentAreaFilled(false);
        brainRotMiscButton.setOpaque(false);
        brainRotMiscButton.setBorderPainted(false);
        brainRotMiscButton.setVisible(false);
        
        ImageIcon examMiscImage = new ImageIcon("assets/miscphotos/miscExam.png");
        Image examMiscScaledImage = examMiscImage.getImage().getScaledInstance(80 * SPRITE_MULTIPLIER, 100 * SPRITE_MULTIPLIER * 3, Image.SCALE_SMOOTH);
        examMiscButton.setIcon(new ImageIcon(examMiscScaledImage));
        examMiscButton.setBounds(1350, 550, 90 * SPRITE_MULTIPLIER, 100 * SPRITE_MULTIPLIER);
        examMiscButton.setFocusPainted(false);
        examMiscButton.setContentAreaFilled(false);
        examMiscButton.setOpaque(false);
        examMiscButton.setBorderPainted(false);
        examMiscButton.setVisible(false);

        // Coin/acad bars
        ImageIcon coinPoints = new ImageIcon("assets/needs/coinbar.png");
        Image coinImage = coinPoints.getImage().getScaledInstance(112  * SPRITE_MULTIPLIER + 112, 112 * SPRITE_MULTIPLIER + 112, Image.SCALE_SMOOTH);
        coinBar.setIcon(new ImageIcon(coinImage));
        coinBar.setBounds(1525, -100, 112 * SPRITE_MULTIPLIER + 122, 112 * SPRITE_MULTIPLIER + 122);
        coinBar.setHorizontalTextPosition(JLabel.CENTER);
        coinBar.setVerticalTextPosition(JLabel.CENTER);
        coinBar.setFont(new Font("Arial", Font.BOLD, 35));
        coinBar.setForeground(Color.white);

        ImageIcon acadPoints = new ImageIcon("assets/needs/acadbar.png");
        Image acadImage = acadPoints.getImage().getScaledInstance(112 * SPRITE_MULTIPLIER + 122, 112 * SPRITE_MULTIPLIER + 112, Image.SCALE_SMOOTH);
        acadBar.setIcon(new ImageIcon(acadImage));
        acadBar.setBounds(1175, -100, 112 * SPRITE_MULTIPLIER + 122, 112 * SPRITE_MULTIPLIER + 122);
        acadBar.setHorizontalTextPosition(JLabel.CENTER);
        acadBar.setVerticalTextPosition(JLabel.CENTER);
        acadBar.setFont(new Font("Arial", Font.BOLD, 35));
        acadBar.setForeground(Color.white);

        // TypingGame
        typeGamePanel = new TypingGame();
        typeGamePanel.setBounds(200, 375, 100 * SPRITE_MULTIPLIER + 100, 100 * SPRITE_MULTIPLIER + 100);
        panel.add(typeGamePanel);
        
        // PSP
        JButton psp = new JButton();
        ImageIcon buttonIcon6 = new ImageIcon("assets/needs/typer.png");
        Image scaledImage6 = buttonIcon6.getImage().getScaledInstance(110 * SPRITE_MULTIPLIER + 100, 110 * SPRITE_MULTIPLIER + 100, Image.SCALE_SMOOTH);
        psp.setIcon(new ImageIcon(scaledImage6));
        psp.setBounds(188, 275, 110 * SPRITE_MULTIPLIER + 100, 110 * SPRITE_MULTIPLIER+ 100);
        psp.setFocusPainted(false);
        psp.setContentAreaFilled(false);
        psp.setOpaque(false);
        psp.setBorderPainted(false);
        panel.add(psp);
        panel.add(coinBar);
        panel.add(acadBar);
        panel.add(typingBar);
        panel.add(examMiscButton);
        panel.add(brainRotMiscButton);
        panel.add(sickMiscButton);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        frame.setVisible(true);
    }

    public static void changeFoodBar(String imageFileString){
        ImageIcon foodBarIconButton = new ImageIcon(imageFileString);
        Image foodBarScaledImage = foodBarIconButton.getImage().getScaledInstance(130 * SPRITE_MULTIPLIER + 130, 50 * SPRITE_MULTIPLIER + 50, Image.SCALE_SMOOTH);
        foodBarButton.setIcon(new ImageIcon(foodBarScaledImage));
    }

    public static void changeSleepBar(String imageFileString){
        ImageIcon sleepBarIconButton = new ImageIcon(imageFileString);
        Image sleepBarScaledImage = sleepBarIconButton.getImage().getScaledInstance(130  * SPRITE_MULTIPLIER + 130, 50  * SPRITE_MULTIPLIER + 50, Image.SCALE_SMOOTH);
        sleepBarButton.setIcon(new ImageIcon(sleepBarScaledImage));
    }

    public static void showSick(Boolean state){
        sickMiscButton.setVisible(state);
    }

    public static void showBrainRot(Boolean state){
        brainRotMiscButton.setVisible(state);
    }

    public static void showExam(Boolean state){
        examMiscButton.setVisible(state);
    }

    public static void disposeFrame() {
        GUI.frame.dispose();
    }

    public static void printingResults(boolean playerResult) {
            JFrame frames = new JFrame("Result!");
            frames.setSize(500, 400);
            frames.setResizable(false);
            frames.setLocationRelativeTo(null);
            frames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frames.setLayout(null);

            if (playerResult) {
                StartGui.playMusic("assets/soundeffects/winnermusic.wav");
                ImageIcon gifIcon = new ImageIcon("assets/needs/youwon.gif");
                JLabel background = new JLabel(gifIcon);
                background.setBounds(0, 0, frames.getWidth() - 15, frames.getHeight() - 25);
                background.setLayout(null);
                frames.add(background);
            } else {
                StartGui.playMusic("assets/soundeffects/lossmusic.wav");
                ImageIcon gifIcon = new ImageIcon("assets/needs/gameover.gif");
                JLabel background = new JLabel(gifIcon);
                background.setBounds(0, 0, frames.getWidth(), frames.getHeight());
                background.setLayout(null);
                frames.add(background);
            }
            frames.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
