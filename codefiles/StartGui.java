package codefiles;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class StartGui{

    private static Clip backgroundMusicClip;
    static JFrame frame = null;
    static boolean gameStarts = false;

    private StartGui() {
        // Create the main frame
        frame = new JFrame("Grad Go Mad!");
        frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Absolute positioning

        // Play background music
        playMusic("assets/needs/startmusic.wav"); // Replace with your music file path

        // Add the animated GIF as the background
        ImageIcon gifIcon = new ImageIcon("assets/needs/coding.gif"); // Replace with your GIF path
        JLabel background = new JLabel(gifIcon);
        background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        background.setLayout(null); // Allows child components to be manually positioned

        // Add the PNG title image to the center, scaled to fit a smaller size
        ImageIcon titleIcon = new ImageIcon("assets/needs/title.png"); // Replace with your PNG image path
        int titleWidth = titleIcon.getIconWidth();
        int titleHeight = titleIcon.getIconHeight();

        // Apply a smaller scale factor (e.g., 50% size)
        double scaleFactor = 0.5; // Adjust this value to make it smaller
        int scaledTitleWidth = (int) (titleWidth * scaleFactor);
        int scaledTitleHeight = (int) (titleHeight * scaleFactor);

        // Create the title label with the scaled image
        JLabel titleLabel = new JLabel(titleIcon);
        titleLabel.setIcon(new ImageIcon(titleIcon.getImage().getScaledInstance(scaledTitleWidth, scaledTitleHeight, Image.SCALE_SMOOTH)));
        titleLabel.setBounds((frame.getWidth() - scaledTitleWidth) / 2, 20, scaledTitleWidth, scaledTitleHeight); // Center the title
        background.add(titleLabel);

        // Button size adjustments
        int buttonWidth = 130;
        int buttonHeight = 55;
        int centerX = (frame.getWidth() - buttonWidth) / 2;
        int bottomY = frame.getHeight() - (2 * buttonHeight + 60);
        int buttonGap = 10;

       
        JButton playButton = createButton("assets/needs/play.png", centerX, bottomY, buttonWidth, buttonHeight, () -> { StartGui.gameStarts = true; });
        JButton quitButton = createButton("assets/needs/quit.png", centerX, bottomY + buttonHeight + buttonGap, buttonWidth, buttonHeight, () -> System.exit(0));


        background.add(playButton);
        background.add(quitButton);

        frame.add(background);

        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                background.setSize(frame.getWidth(), frame.getHeight());

                int newCenterX = (frame.getWidth() - buttonWidth) / 2;
                int newBottomY = frame.getHeight() - (2 * buttonHeight + 60);
                playButton.setBounds(newCenterX, newBottomY, buttonWidth, buttonHeight);
                quitButton.setBounds(newCenterX, newBottomY + buttonHeight + buttonGap, buttonWidth, buttonHeight);

                titleLabel.setIcon(new ImageIcon(titleIcon.getImage().getScaledInstance(scaledTitleWidth, scaledTitleHeight, Image.SCALE_SMOOTH)));
                titleLabel.setBounds((frame.getWidth() - scaledTitleWidth) / 2, 20, scaledTitleWidth, scaledTitleHeight);
            }
        });

        frame.setVisible(true);
    }

    private static JButton createButton(String iconPath, int x, int y, int width, int height, Runnable action) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JButton button = new JButton(scaledIcon);
        button.setBounds(x, y, width, height);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> action.run());
        return button;
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