package codefiles;
import java.awt.*;
import javax.swing.*;

public class GUI{
    public static GUI guiInstance = null;
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        JLayeredPane layeredPane = new JLayeredPane();
        frame.setContentPane(layeredPane);

        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String gifPath = "assets/needs/coding.gif"; // Ensure this path is correct
                ImageIcon icon = new ImageIcon(gifPath);
                g.drawImage(icon.getImage(), 0, 0, this);
            }
        };

        // Set the panel bounds to match the frame size
        panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
        
        //_____________________________________________________________________________________________
        // Centered GIF (character.gif)
        
        JLabel characterLabel = new JLabel();
        ImageIcon characterIcon = new ImageIcon("assets/needs/mycharver1.gif");
        characterLabel.setIcon(characterIcon);
        characterLabel.setBounds(125, 110, 250, 250);
        characterLabel.setOpaque(false); 
        panel.add(characterLabel);

        //_____________________________________________________________________________________________
        // Button 1 (plate.png)
        
        JButton button1 = new JButton();
        ImageIcon buttonIcon1 = new ImageIcon("assets/needs/plate.png");
        Image scaledImage1 = buttonIcon1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        button1.setIcon(new ImageIcon(scaledImage1));
        button1.setBounds(0, 205, 100, 100);
        button1.setFocusPainted(false);
        button1.setContentAreaFilled(false);
        button1.setOpaque(false);
        button1.setBorderPainted(false);
        button1.addActionListener(e -> {
            StartGui.playSound("assets/soundeffects/food.wav");
            ButtonOne foodOptions = new ButtonOne();
            foodOptions.showFoodOptions();  
            
        });
        
        //_____________________________________________________________________________________________
        // Button 2 (libro.png)
        
        JButton button2 = new JButton();
        ImageIcon buttonIcon2 = new ImageIcon("assets/needs/studysheesh.png");
        Image scaledImage2 = buttonIcon2.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        button2.setIcon(new ImageIcon(scaledImage2));
        button2.setBounds(60, 190, 100, 100);
        button2.setFocusPainted(false);
        button2.setContentAreaFilled(false);
        button2.setOpaque(false);
        button2.setBorderPainted(false);
        button2.addActionListener(e -> {
            StartGui.playSound("assets/soundeffects/openbook.wav");
            ButtonTwo acadOptions = new ButtonTwo();
            acadOptions.showAcadOptions();  
        });

        //________________________________________________________________________________________________
        // Button 3 (misc.png)
        
        JButton button3 = new JButton(); 
        ImageIcon buttonIcon3 = new ImageIcon("assets/needs/warning.png"); 
        Image scaledImage3 = buttonIcon3.getImage().getScaledInstance(95, 95, Image.SCALE_SMOOTH);
        button3.setIcon(new ImageIcon(scaledImage3)); 
        button3.setBounds(380, 275, 100, 100); 
        button3.setFocusPainted(false);
        button3.setContentAreaFilled(false); 
        button3.setOpaque(false);
        button3.setBorderPainted(false); 
        button3.addActionListener(e -> {

            StartGui.playSound("assets/soundeffects/batteryminus.wav");
            ButtonThree miscOptions = new ButtonThree();
            miscOptions.showMiscOptions();  
            
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
		ImageIcon buttonIcon4 = new ImageIcon("assets/needs/lampara.png");
		Image scaledImage4 = buttonIcon4.getImage().getScaledInstance(230, 195, Image.SCALE_SMOOTH);
		button4.setIcon(new ImageIcon(scaledImage4));
		button4.setBounds(360, 83, 100, 125);
		button4.setFocusPainted(false);
		button4.setContentAreaFilled(false);
		button4.setOpaque(false);
		button4.setBorderPainted(false);
		panel.add(button4);

		boolean[] isSleeping = {false};

        Timer timerLamp = new Timer(1000, e -> {
            Player player = Player.createPlayer();
            player.incrementSleep(8);
           
        });

		button4.addActionListener(e -> {
            
		    if (isSleeping[0]) {
                StartGui.stopMusic();
		        dimPanel.setVisible(false);

                StartGui.playSound("assets/soundeffects/off.wav");
                timerLamp.stop();
                
		    } else {
                StartGui.playMusic("assets/soundeffects/sleep.wav");
		        dimPanel.setVisible(true);
                StartGui.playSound("assets/soundeffects/on.wav");
                timerLamp.start();
		    }
		    isSleeping[0] = !isSleeping[0];
		});




		
        //________________________________________________________________________________________________
        // foodBar (foodBar(number).png)
        
        ImageIcon foodBarIconButton = new ImageIcon("assets/statusBars/foodBar5.png");
        Image foodBarScaledImage = foodBarIconButton.getImage().getScaledInstance(120, 50, Image.SCALE_SMOOTH);
        foodBarButton.setIcon(new ImageIcon(foodBarScaledImage));
        foodBarButton.setBounds(5, 3, 140, 50); // Ensure button size matches the scaled image size
        foodBarButton.setFocusPainted(false);
        foodBarButton.setContentAreaFilled(false);
        foodBarButton.setOpaque(false);
        foodBarButton.setBorderPainted(false);
        panel.add(foodBarButton);

        //________________________________________________________________________________________________
        // sleepBar (sleepBar(number).png)

        ImageIcon sleepBarIconButton = new ImageIcon("assets/statusBars/sleepBar5.png");
        Image sleepBarScaledImage = sleepBarIconButton.getImage().getScaledInstance(120, 50, Image.SCALE_SMOOTH);
        sleepBarButton.setIcon(new ImageIcon(sleepBarScaledImage));
        sleepBarButton.setBounds(5, 53, 140, 50);
        sleepBarButton.setFocusPainted(false);
        sleepBarButton.setContentAreaFilled(false);
        sleepBarButton.setOpaque(false);
        sleepBarButton.setBorderPainted(false);
        panel.add(sleepBarButton);

        //________________________________________________________________________________________________
        // FOR MISCELLANEOUS
        

        ImageIcon sickMiscImage = new ImageIcon("assets/miscphotos/miscSick.png"); 
        Image sickMiscScaledImage = sickMiscImage.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
        sickMiscButton.setIcon(new ImageIcon(sickMiscScaledImage));
        sickMiscButton.setBounds(350, 195, 90, 100);
        sickMiscButton.setFocusPainted(false);
        sickMiscButton.setContentAreaFilled(false);
        sickMiscButton.setOpaque(false);
        sickMiscButton.setBorderPainted(false);
        sickMiscButton.setVisible(false); 
        
        ImageIcon brainRotMiscImage = new ImageIcon("assets/miscphotos/miscBrainRot.png");
        Image brainRotScaledImage = brainRotMiscImage.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
        brainRotMiscButton.setIcon(new ImageIcon(brainRotScaledImage));
        brainRotMiscButton.setBounds(350, 195, 90, 100);
        brainRotMiscButton.setFocusPainted(false);
        brainRotMiscButton.setContentAreaFilled(false);
        brainRotMiscButton.setOpaque(false);
        brainRotMiscButton.setBorderPainted(false);
        brainRotMiscButton.setVisible(false);
        

        ImageIcon examMiscImage = new ImageIcon("assets/miscphotos/miscExam.png");
        Image examMiscScaledImage = examMiscImage.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
        examMiscButton.setIcon(new ImageIcon(examMiscScaledImage));
        examMiscButton.setBounds(350, 195, 90, 100);
        examMiscButton.setFocusPainted(false);
        examMiscButton.setContentAreaFilled(false);
        examMiscButton.setOpaque(false);
        examMiscButton.setBorderPainted(false);
        examMiscButton.setVisible(false);


        //________________________________________________________________________________________________
        // ACADEMIC POINTS AND COIN POINTS

        ImageIcon coinPoints = new ImageIcon("assets/needs/coinbar.png");
        Image coinImage = coinPoints.getImage().getScaledInstance(112, 112, Image.SCALE_SMOOTH);
        coinBar.setIcon(new ImageIcon(coinImage));
        coinBar.setBounds(248, -26, 112, 112);
        coinBar.setHorizontalTextPosition(JLabel.CENTER);
        coinBar.setVerticalTextPosition(JLabel.CENTER);
        coinBar.setFont(new Font("Arial", Font.BOLD, 15));
        coinBar.setForeground(Color.white);

       
        ImageIcon acadPoints = new ImageIcon("assets/needs/acadbar.png");
        Image acadImage = acadPoints.getImage().getScaledInstance(112, 112, Image.SCALE_SMOOTH);
        acadBar.setIcon(new ImageIcon(acadImage));
        acadBar.setBounds(358, -26, 112, 112);
        acadBar.setHorizontalTextPosition(JLabel.CENTER);
        acadBar.setVerticalTextPosition(JLabel.CENTER);
        acadBar.setFont(new Font("Arial", Font.BOLD, 15));
        acadBar.setForeground(Color.white);

        //__________________________________________________________________________________________________
        // Typing Game
        
        TypingGame typeGamePanel = new TypingGame();
        typeGamePanel.setBounds(38, 115, 100, 100);  // Setting bounds for TypingGame panel
        
        //________________________________________________________________________________________________
        // PSP (psp.png)
		
		JButton psp = new JButton();
		ImageIcon buttonIcon6 = new ImageIcon("assets/needs/typer.png");
		Image scaledImage6 = buttonIcon6.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		psp.setIcon(new ImageIcon(scaledImage6));
		psp.setBounds(33, 70, 110, 110);  // Set position and size
		psp.setFocusPainted(false);
		psp.setContentAreaFilled(false);
		psp.setOpaque(false);
		psp.setBorderPainted(false);

        //__________________________________________________________________________________________________
        // Miscellaneous
        
       
        //__________________________________________________________________________________________________
        // Add buttons to the panel
        
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(typeGamePanel);
        panel.add(psp);
        panel.add(coinBar);
        panel.add(acadBar);
        panel.add(typingBar);
        panel.add(examMiscButton);
        panel.add(brainRotMiscButton);
        panel.add(sickMiscButton);

        frame.setVisible(true);
    }

    public static void changeFoodBar(String imageFileString){
        ImageIcon foodBarIconButton = new ImageIcon(imageFileString);
        Image foodBarScaledImage = foodBarIconButton.getImage().getScaledInstance(130, 50, Image.SCALE_SMOOTH);
        foodBarButton.setIcon(new ImageIcon(foodBarScaledImage));
    }

    public static void changeSleepBar(String imageFileString){
        ImageIcon sleepBarIconButton = new ImageIcon(imageFileString);
        Image sleepBarScaledImage = sleepBarIconButton.getImage().getScaledInstance(130, 50, Image.SCALE_SMOOTH);
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
    } //amoni na function na isip ko but still, di na run

    public static void printingResults(boolean playerResult) {
            JFrame frames = new JFrame("Result!");
            frames.setSize(500, 400);
            frames.setResizable(false);
            frames.setLocationRelativeTo(null);
            frames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frames.setLayout(null);


            // Add the animated GIF as the background
            if (playerResult) {
                // StartGui.playMusic("soundeffects/winnermusic.wav");C:\CODING FILE\Vscode\needs
                StartGui.playMusic("assets/soundeffects/winnermusic.wav");
                ImageIcon gifIcon = new ImageIcon("assets/needs/youwon.gif"); // Replace with your GIF path
                JLabel background = new JLabel(gifIcon);
                background.setBounds(0, 0, frames.getWidth() - 15, frames.getHeight() - 25);
                background.setLayout(null);
                frames.add(background);
            } else {
                StartGui.playMusic("assets/soundeffects/lossmusic.wav");
                ImageIcon gifIcon = new ImageIcon("assets/needs/gameover.gif"); // Replace with your GIF path
                JLabel background = new JLabel(gifIcon);
                background.setBounds(0, 0, frames.getWidth(), frames.getHeight());
                background.setLayout(null);
                frames.add(background);
            }
            //geh  try run
            frames.setVisible(true);

    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
        
    }
}