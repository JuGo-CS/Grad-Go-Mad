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
    private boolean typingEnabled = true;

    public TypingGame(){
        TypingGame.randomWordObject = new RandwordsYear1();
        
        //Prompt Label
        typingGamePromptLabel = new JLabel("INSERT WORD");
        typingGamePromptLabel.setPreferredSize(new Dimension(100, 25));
        typingGamePromptLabel.setText(getRandomWord());
        typingGamePromptLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        typingGamePromptLabel.setVerticalAlignment(SwingConstants.CENTER); 
        typingGamePromptLabel.setOpaque(false);  
        typingGamePromptLabel.setFont(new Font("Arial", Font.BOLD, 15));
        typingGamePromptLabel.setForeground(new Color(0xA52A2A));
        typingGamePromptLabel.setBounds(50, 13, 100, 25);
        
        
        //Text Input
        typingGameTextInput = new JTextField();
        typingGameTextInput.setOpaque(true);  
        typingGameTextInput.setBackground(Color.orange);
        typingGameTextInput.setForeground(new Color(0xA52A2A));
        typingGameTextInput.setPreferredSize(new Dimension(100, 25));
        typingGameTextInput.setFont(new Font("Arial", Font.ITALIC, 12));
        typingGameTextInput.setHorizontalAlignment(JTextField.CENTER);  
        typingGameTextInput.setBorder(BorderFactory.createEmptyBorder());
        typingGameTextInput.addKeyListener(new KeyListener() {
        	
            @Override
            public void keyTyped(KeyEvent e) {
                if (!typingEnabled) {
                    e.consume(); // Block typing
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!typingEnabled || e.getKeyCode() != KeyEvent.VK_ENTER) return;
                submitWord(typingGameTextInput.getText());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!typingEnabled || e.getKeyCode() != KeyEvent.VK_ENTER) return;
                typingGameTextInput.setText("");  
                GUI.coinBar.setText(String.valueOf(playerInstance.coinCount));
                GUI.acadBar.setText(String.valueOf(playerInstance.acadCount));
            }
        });

        typingGameTextInput.setPreferredSize(new Dimension(93,21));
       
        //Panel Modifications
        setOpaque(false);
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(100,25));
        this.add(typingGamePromptLabel);
        this.add(typingGameTextInput);
        setTypingEnabled(true);
    }
   
    public void setTypingEnabled(boolean enabled) {
        this.typingEnabled = enabled;
        typingGameTextInput.setEnabled(enabled);
        typingGameTextInput.setBackground(enabled ? Color.orange : Color.gray);
        typingGamePromptLabel.setForeground(enabled ? new Color(0xA52A2A) : Color.gray);
        if (!enabled) typingGameTextInput.setText("");
    }
    
    public void submitWord(String word){
        if(!typingEnabled || !wordToType.equals(word)) return;
        StartGui.playSound("assets/soundeffects/coin.wav");
        typingGamePromptLabel.setText(getRandomWord());
        playerInstance.incrementCoins(wordToType.length());
        playerInstance.incrementAcads(wordToType.length());
    }

    public static void updateRandomWordObject(int currentYearLevel){
        TypingGame.randomWordObject = RandWordsFactory.createRandWords(currentYearLevel);
    }

    public String getRandomWord(){
        this.wordToType = randomWordObject.getRandword(random.nextInt(15));
        return wordToType;
    }
    
}
