package codefiles;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.*;
import javax.swing.*;

public class StartGui{

    private static Clip backgroundMusicClip;
    static JFrame frame = null;
    static boolean gameStarts = false;
    public static String playerName = "";
    public static boolean is_leaderboard_opened = false; 
    static JFrame lbFrame;

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
                String prompt = JOptionPane.showInputDialog(frame, "(For Leaderboards) Enter your name :", "Player Name", JOptionPane.QUESTION_MESSAGE);
                if (prompt != null && !prompt.trim().isEmpty()) {
                    playerName = prompt.trim();
                }
                gameStarts = true; 
            });
        JButton quitButton = createButton("assets/needs/quit.png", centerX, bottomY + buttonHeight + buttonGap, buttonWidth, buttonHeight, () -> System.exit(0));

        JButton throphyButton = createButton("assets/needs/throphy.png", 1550, 650, buttonWidth, buttonHeight * 2, () -> { 
            is_leaderboard_opened = true;
            try {
                Player p = Player.createPlayer();
                int score = p.coinCount + p.getAcadBar();
                File csvFile = new File("codefiles/leaderboards.csv");
                try (FileWriter fw = new FileWriter(csvFile, true)) {
                    if (csvFile.length() == 0) {
                        fw.write("name,score\n");
                    }
                    fw.write(StartGui.playerName + "," + score + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Show top 10 leaderboard
            try {
                List<String[]> entries = new ArrayList<>();
                BufferedReader br = new BufferedReader(new FileReader("codefiles/leaderboards.csv"));
                String line;
                br.readLine(); // skip header
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        entries.add(new String[]{parts[0], parts[1].trim()});
                    }
                }
                br.close();

                // Sort desc by score
                entries.sort((a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1]));

                // Top 10
                List<String[]> top10 = entries.subList(0, Math.min(10, entries.size()));

                lbFrame = new JFrame("LEADERBOARD TOP 10");
                lbFrame.setSize(500, 400);
                lbFrame.setResizable(false);
                lbFrame.setLocationRelativeTo(null);
                lbFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JTextArea textArea = new JTextArea();
                textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
                textArea.setEditable(false);
                textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                StringBuilder sb = new StringBuilder();
                sb.append("----------------------------------------\n");
                sb.append(" LEADERBOARD TOP 10\n");
                sb.append("----------------------------------------\n\n");
                String[] ordinals = {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th"};
                for (int i = 0; i < top10.size(); i++) {
                    String name = top10.get(i)[0];
                    int pts = Integer.parseInt(top10.get(i)[1]);
                    sb.append(String.format("%-4s | %-10s | %4d pts\n", ordinals[i], name, pts));
                }
                textArea.setText(sb.toString());
                lbFrame.add(new JScrollPane(textArea));
                lbFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Leaderboard error: " + e.getMessage());
            }

            });

        panel.add(playButton);
        panel.add(quitButton);
        panel.add(throphyButton);

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
        if(is_leaderboard_opened){
            lbFrame.dispose();
        }
        frame.dispose();
    }

    public static boolean getStartGui(){
        new StartGui();
        while(gameStarts != true){System.out.println();}
        StartGui.switchToGame();
        return true;
    }
}
