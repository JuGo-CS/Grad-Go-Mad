package finalproject;
import java.awt.*;
import javax.swing.*;

import finalproject.GradGoMad;
import finalproject.Player;


public class GUI extends NullPointerException{
    
    // static int overallMoney = 1000;
    // static int overallAcademicPoints = 0;
    static Player player = Player.createPlayer();
    static JLabel coinBar = new JLabel(String.valueOf(player.coinCount), SwingConstants.CENTER);
    static JLabel acadBar = new JLabel(String.valueOf(player.getAcadBar()), SwingConstants.CENTER);
    
    public GUI() {
        JFrame frame = new JFrame("Grad Go Mad!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        JLayeredPane layeredPane = new JLayeredPane();
        frame.setContentPane(layeredPane);
        
        

        //_____________________________________________________________________________________________
        // Main panel 
        
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String gifPath = "needs/coding.gif";
                ImageIcon icon = new ImageIcon(gifPath);
                g.drawImage(icon.getImage(), 0, 0, this);
            }
        };
        panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);

        //_____________________________________________________________________________________________
        // Centered GIF (character.gif)
        
        JLabel characterLabel = new JLabel();
        ImageIcon characterIcon = new ImageIcon("needs/mycharver1.gif");
        characterLabel.setIcon(characterIcon);
        characterLabel.setBounds(125, 110, 250, 250);
        characterLabel.setOpaque(false); 
        panel.add(characterLabel);

        //_____________________________________________________________________________________________
        // Button 1 (plate.png)
        
        JButton button1 = new JButton();
        ImageIcon buttonIcon1 = new ImageIcon("needs/plate.png");
        Image scaledImage1 = buttonIcon1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        button1.setIcon(new ImageIcon(scaledImage1));
        button1.setBounds(0, 205, 100, 100);
        button1.setFocusPainted(false);
        button1.setContentAreaFilled(false);
        button1.setOpaque(false);
        button1.setBorderPainted(false);
        button1.addActionListener(e -> {
            // ButtonOne foodOptions = new ButtonOne();
            // foodOptions.showFoodOptions();  
            GradGoMad.actionButton.append("foodButton");
        });
        
        //_____________________________________________________________________________________________
        // Button 2 (libro.png)
        
        JButton button2 = new JButton();
        ImageIcon buttonIcon2 = new ImageIcon("needs/studysheesh.png");
        Image scaledImage2 = buttonIcon2.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        button2.setIcon(new ImageIcon(scaledImage2));
        button2.setBounds(60, 190, 100, 100);
        button2.setFocusPainted(false);
        button2.setContentAreaFilled(false);
        button2.setOpaque(false);
        button2.setBorderPainted(false);
        button2.addActionListener(e -> {
            // ButtonTwo acadOptions = new ButtonTwo();
            // acadOptions.showAcadOptions();  
            GradGoMad.actionButton.append("acadButton");
        });

        //________________________________________________________________________________________________
        // Button 3 (libro.png)
        
        JButton button3 = new JButton(); 
        ImageIcon buttonIcon3 = new ImageIcon("needs/warning.png"); 
        Image scaledImage3 = buttonIcon3.getImage().getScaledInstance(95, 95, Image.SCALE_SMOOTH);
        button3.setIcon(new ImageIcon(scaledImage3)); 
        button3.setBounds(380, 275, 100, 100); 
        button3.setFocusPainted(false);
        button3.setContentAreaFilled(false); 
        button3.setOpaque(false);
        button3.setBorderPainted(false); 
        button3.addActionListener(e -> {
            GradGoMad.actionButton.append("miscButton");
        });


        //_______________________________________________________________________________________________
        // Button 4 (lampara.png) - with dim effect
        
        JPanel dimPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        dimPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        dimPanel.setOpaque(false);
        dimPanel.setVisible(false);
        layeredPane.add(dimPanel, JLayeredPane.PALETTE_LAYER);

        JButton button4 = new JButton();
        ImageIcon buttonIcon4 = new ImageIcon("needs/lampara.png");
        Image scaledImage4 = buttonIcon4.getImage().getScaledInstance(230, 195, Image.SCALE_SMOOTH);
        button4.setIcon(new ImageIcon(scaledImage4));
        button4.setBounds(360, 83, 100, 125);
        button4.setFocusPainted(false);
        button4.setContentAreaFilled(false);
        button4.setOpaque(false);
        button4.setBorderPainted(false);
        panel.add(button4);

        boolean[] isSleeping = {false};

        // for the lamp to add the sleepBar due Time by seconds
        Timer timerLamp = new Timer(1000, e -> {
            // Increments the sleepBar
            player.incrementSleep(2);
            
        });

        button4.addActionListener(e -> {
            if (isSleeping[0]) {
                dimPanel.setVisible(false);
                timerLamp.stop();

            } else {
                dimPanel.setVisible(true);
                timerLamp.start();
            }
            isSleeping[0] = !isSleeping[0];
        });

        //________________________________________________________________________________________________
        // Button 5 (level.png)
        
        JButton button5 = new JButton();
        ImageIcon buttonIcon5 = new ImageIcon("needs/level.png");
        Image scaledImage5 = buttonIcon5.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        button5.setIcon(new ImageIcon(scaledImage5));
        button5.setBounds(0, 0, 100, 100);
        button5.setFocusPainted(false);
        button5.setContentAreaFilled(false);
        button5.setOpaque(false);
        button5.setBorderPainted(false);

        //________________________________________________________________________________________________
        // ACADEMIC POINTS AND COIN POINTS

        ImageIcon coinPoints = new ImageIcon("needs/coinbar.png");
        Image coinImage = coinPoints.getImage().getScaledInstance(112, 112, Image.SCALE_SMOOTH);
        coinBar.setIcon(new ImageIcon(coinImage));
        coinBar.setBounds(248, -26, 112, 112);
        coinBar.setHorizontalTextPosition(JLabel.CENTER);
        coinBar.setVerticalTextPosition(JLabel.CENTER);
        coinBar.setFont(new Font("Arial", Font.BOLD, 15));
        coinBar.setForeground(Color.white);

       
        ImageIcon acadPoints = new ImageIcon("needs/acadbar.png");
        Image acadImage = acadPoints.getImage().getScaledInstance(112, 112, Image.SCALE_SMOOTH);
        acadBar.setIcon(new ImageIcon(acadImage));
        acadBar.setBounds(358, -26, 112, 112);
        acadBar.setHorizontalTextPosition(JLabel.CENTER);
        acadBar.setVerticalTextPosition(JLabel.CENTER);
        acadBar.setFont(new Font("Arial", Font.BOLD, 15));
        acadBar.setForeground(Color.white);

        //__________________________________________________________________________________________________
        // Add buttons to the panel
        
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(coinBar);
        panel.add(acadBar);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
