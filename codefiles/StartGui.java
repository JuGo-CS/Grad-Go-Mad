package codefiles;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class StartGui{

    private static Clip backgroundMusicClip;
    static JFrame frame = null;
    static boolean gameStarts = false;
    public static String playerName = "";

    private StartGui() {
        // Create the main frame
        frame = new JFrame("Grad Go Mad!");
        // frame.setSize(500, 400);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Absolute positioning

        JLayeredPane layeredPane = new JLayeredPane();
        frame.setContentPane(layeredPane);
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String gifPath = "assets/needs/coding.gif";
                ImageIcon icon = new ImageIcon(gifPath);
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);  // Scales big, anim OK
            }
        };
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
                panel.repaint();
            }
        });

        // Play background music
        playMusic("assets/needs/startmusic.wav"); // Replace with your music file path

        ImageIcon titleIcon = loadImageIcon("assets/needs/title.png");
        double scaleFactor = 1.5;
        int scaledTitleWidth = (int)(titleIcon.getIconWidth() * scaleFactor);
        int scaledTitleHeight = (int)(titleIcon.getIconHeight() * scaleFactor);
        JLabel titleLabel = new JLabel();
        titleLabel.setIcon(new ImageIcon(titleIcon.getImage().getScaledInstance(scaledTitleWidth, scaledTitleHeight, Image.SCALE_SMOOTH)));
        titleLabel.setBounds((frame.getWidth() - scaledTitleWidth)/2, 170, scaledTitleWidth, scaledTitleHeight);
        panel.add(titleLabel);

        int buttonWidth = 130 * 3, buttonHeight = 55 * 3;
        int centerX = (frame.getWidth() - buttonWidth)/2;
        int bottomY = frame.getHeight() - (2 * buttonHeight + 60);
        int buttonGap = 20;

        JButton playButton = createButton("assets/needs/play.png", centerX, bottomY, buttonWidth, buttonHeight, () -> { 
                String prompt = JOptionPane.showInputDialog(frame, "Enter your player name for the leaderboard:", "Player Name", JOptionPane.QUESTION_MESSAGE);
                if (prompt != null && !prompt.trim().isEmpty()) {
                    playerName = prompt.trim();
                }
                gameStarts = true; 
            });
        JButton quitButton = createButton("assets/needs/quit.png", centerX, bottomY + buttonHeight + buttonGap, buttonWidth, buttonHeight, () -> System.exit(0));

        panel.add(playButton);
        panel.add(quitButton);

        // Resizer for title/buttons
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                titleLabel.setBounds((frame.getWidth() - scaledTitleWidth)/2, 20, scaledTitleWidth, scaledTitleHeight);
                int newCenterX = (frame.getWidth() - buttonWidth)/2;
                int newBottomY = frame.getHeight() - (2 * buttonHeight + 60);
                playButton.setBounds(newCenterX, newBottomY, buttonWidth, buttonHeight);
                quitButton.setBounds(newCenterX, newBottomY + buttonHeight + buttonGap, buttonWidth, buttonHeight);
            }
        });

        frame.setVisible(true);
    }

    private static JButton createButton(String iconPath, int x, int y, int width, int height, Runnable action) {
        ImageIcon icon = loadImageIcon(iconPath);
        JButton button;
        if (icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            button = new JButton(scaledIcon);
        } else {
            button = new JButton(iconPath.replace("assets/needs/", "").replace(".png", ""));
        }
        button.setBounds(x, y, width, height);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> action.run());
        return button;
    }

    private static ImageIcon loadImageIcon(String path) {
        File file = new File(path);
        if (file.exists()) {
            return new ImageIcon(path);
        }
        System.out.println("Image file not found: " + path);
        BufferedImage placeholder = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        return new ImageIcon(placeholder);
    }
    
    public static boolean musicWillLoop = false;
    public static void playMusic(String filePath) {
        try {
            File musicFile = new File(filePath);
            if (musicFile.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
                backgroundMusicClip = AudioSystem.getClip();
                backgroundMusicClip.open(audioStream);
                backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop music
                backgroundMusicClip.start();
            } else {
                System.out.println("Music file not found: " + filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playSound(String soundFile) {
        try {
            File sound = new File(soundFile);  // Ensure the path is correct
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopMusic() {
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop(); 
            backgroundMusicClip.close(); 
        }
    }

    
    private static void switchToGame() {
        StartGui.stopMusic();
        frame.dispose();
    }

    public static boolean getStartGui(){
        new StartGui();
        while(gameStarts != true){System.out.println();}
        StartGui.switchToGame();
        return true;
    }
}
