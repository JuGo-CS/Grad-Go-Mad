package codefiles;
import java.awt.*;
import javax.swing.*;

public class ButtonTwo {
    static final String[] selectedAcad = {null};

    public void showAcadOptions() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel Apanel = new JPanel();

        Icon[] options = {
            new ImageIcon(new ImageIcon("assets/acadphotos/dictionary.jpg").getImage().getScaledInstance(95, 95, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("assets/acadphotos/housee.jpg").getImage().getScaledInstance(95, 95, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("assets/acadphotos/tutor2.jpg").getImage().getScaledInstance(95, 95, Image.SCALE_SMOOTH))
        };

        String[] acadNames = {"Subject 1-Book", "Subject 2-Study Hub", "Subject 3-Tutor"};

        JButton[] acadButtons = new JButton[options.length];
        

        for (int i = 0; i < options.length; i++) {
            acadButtons[i] = new JButton(options[i]);
            int index = i;
            acadButtons[i].addActionListener(e1 -> {
                selectedAcad[0] = acadNames[index];
                ((JButton) e1.getSource()).getTopLevelAncestor().setVisible(false);
            });
            Apanel.add(acadButtons[i]);
        }

        JOptionPane.showOptionDialog(
            frame,
            Apanel,
            "Select your preferred academic option: ",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new Object[] {},
            null
        );
        
        String acadClassString = "";
        if(selectedAcad[0] == null){
            return;
        }

        switch (selectedAcad[0]) {
            case "Subject 1-Book" -> {
                acadClassString = "buyBook";
            }

            case "Subject 2-Study Hub" -> {
                acadClassString = "studyHub";
            }
                
            case "Subject 3-Tutor" -> {
                acadClassString = "tutor";
            }

            default -> {
            }
        }

        Miscellaneous miscInstance = Miscellaneous.miscGetInstance();
        Player playerInstance = Player.createPlayer();
        AcadClass acadClass = FactoryForObject.createAcadsObject(acadClassString);
        // System.out.println("before the action class");
        acadClass.changeAcadStats(miscInstance.getIsSick());
        GUI.coinBar.setText(String.valueOf(playerInstance.coinCount));
        GUI.acadBar.setText(String.valueOf(playerInstance.acadCount));

        // if (selectedAcad[0] != null) {
        //     System.out.println("User selected: " + selectedAcad[0]);
        // }
    }

    static public void musicForAcad(boolean okayMovment) {
        if(okayMovment) {
            switch (selectedAcad[0]) {
                case "Subject 1-Book" -> {
                    // Player.audioCoinChecker("assets/soundeffects/openbook.wav");
                    StartGui.playSound("assets/soundeffects/openbook.wav");
                }
    
                case "Subject 2-Study Hub" -> {
                    StartGui.playSound("assets/soundeffects/writing.wav");
                    // Player.audioCoinChecker("assets/soundeffects/writing.wav");
                }
                    
                case "Subject 3-Tutor" -> {
                    StartGui.playSound("assets/soundeffects/tutor.wav");
                    // Player.audioCoinChecker("assets/soundeffects/tutor.wav");
                }
    
                default -> {
                }
            }
        } else {
            // Player.audioCoinChecker("assets/soundeffects/Sound Effects/notenoughcoin.wav");
            StartGui.playSound("assets/soundeffects/Sound Effects/notenoughcoin.wav");
        }

        
    }
}
