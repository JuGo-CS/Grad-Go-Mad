package codefiles;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;


public class TypingGame extends JPanel{
    private static final Random random = new Random();
    Player playerInstance = Player.createPlayer();
    static RandWords randomWordObject;


    String wordToType;
    JLabel typingGamePromptLabel;
    JLabel typingBar;
    JTextField typingGameTextInput;


    public TypingGame(){
        TypingGame.randomWordObject = new RandwordsYear1();
        
        //Prompt Label
        typingGamePromptLabel = new JLabel("INSERT WORD");
        typingGamePromptLabel.setPreferredSize(new Dimension(100, 25));
        typingGamePromptLabel.setText(getRandomWord());
        typingGamePromptLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        typingGamePromptLabel.setVerticalAlignment(SwingConstants.CENTER); 
        typingGamePromptLabel.setOpaque(false);  // Set to false to make the background transparent
        typingGamePromptLabel.setFont(new Font("Arial", Font.BOLD, 15));
        typingGamePromptLabel.setForeground(new Color(0xA52A2A));
        typingGamePromptLabel.setBounds(50, 13, 100, 25);
        
        
        //Text Input
        typingGameTextInput = new JTextField();
        typingGameTextInput.setOpaque(true);  // Set to false to make the background transparent
        typingGameTextInput.setBackground(Color.orange);//new Color(0xFFD700));
        typingGameTextInput.setForeground(new Color(0xA52A2A));
        typingGameTextInput.setPreferredSize(new Dimension(100, 25));
        typingGameTextInput.setFont(new Font("Arial", Font.ITALIC, 12));
        typingGameTextInput.setHorizontalAlignment(JTextField.CENTER);  
        typingGameTextInput.setBorder(BorderFactory.createEmptyBorder());
        typingGameTextInput.addKeyListener(new KeyListener() {
        	
            @Override
            public void keyTyped(KeyEvent e) {
                // Optionally add typing sound here
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  // Check for "Enter" key
                    submitWord(typingGameTextInput.getText());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  // Check for "Enter" key release
                    typingGameTextInput.setText("");  // Clear text field
                    GUI.coinBar.setText(String.valueOf(playerInstance.coinCount));
                    GUI.acadBar.setText(String.valueOf(playerInstance.acadCount));
                }
            }
        });


        typingGameTextInput.setPreferredSize(new Dimension(93,21));


       
        //Panel Modifications
        setOpaque(false);
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(100,25));
        this.add(typingGamePromptLabel);
        this.add(typingGameTextInput);
    }
   
    //In GUI: SubmitButton.addActionListener(e -> submitWord(textfield.getText()));
    public void submitWord(String word){
        if(wordToType.equals(word)){
            StartGui.playSound("assets/soundeffects/coin.wav");
            typingGamePromptLabel.setText(getRandomWord());
            playerInstance.incrementCoins(wordToType.length());
            playerInstance.incrementAcads(wordToType.length());
        }
    }

//di ko gid ma figure out ang pag exit sang frame bisan ma chatgpt ko, di gid siya nakakas
//gin singleton ko na gani ang gui
    //In GradGoMad:updateRandomWordObject(currentYearLevel); everytime the year changes
    public static void updateRandomWordObject(int currentYearLevel){
        TypingGame.randomWordObject = RandWordsFactory.createRandWords(currentYearLevel);
    }


    //In GUI: PromptLabel.setText(getRandomWord());
    public String getRandomWord(){
        this.wordToType = randomWordObject.getRandword(random.nextInt(15));
        return wordToType;
    }
    
}