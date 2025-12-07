package codefiles;
import java.awt.*;
import javax.swing.*;

public class ButtonThree {

    public void showMiscOptions() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel Apanel = new JPanel();

        Icon[] options = {
        	new ImageIcon(new ImageIcon("assets/miscphotos/miscdo1.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("assets/miscphotos/miscdo2.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("assets/miscphotos/miscdo3.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH))
        };

        String[] miscNames = {"misc1", "misc2", "misc3"};

        JButton[] miscButtons = new JButton[options.length];
        final String[] selectedMisc = {null};

        for (int i = 0; i < options.length; i++) {
            miscButtons[i] = new JButton(options[i]);
            int index = i;
            miscButtons[i].addActionListener(e1 -> {
                selectedMisc[0] = miscNames[index];
                ((JButton) e1.getSource()).getTopLevelAncestor().setVisible(false);
            });
            Apanel.add(miscButtons[i]);
        }

        JOptionPane.showOptionDialog(
            frame,
            Apanel,
            "Select miscellaneous option: ",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new Object[] {},
            null
        );
        
        Player player = Player.createPlayer();
        boolean validAction = false;
        
        // switch (selectedMisc[0]) {
        //     case "misc1":
        //         StartGui.playSound("assets/soundeffects/medicine.wav");
        //         break;

        //     case "misc2":
        //         StartGui.playSound("assets/soundeffects/lost a life.wav");
        //         break;

        //     case "misc3":
        //         StartGui.playSound("assets/soundeffects/writing.wav");
        //         break;
        //     default:
        //         break;
        // }



        if (selectedMisc[0] != null) {
            // StringBuilder holder = new StringBuilder("haha");
            

            if(selectedMisc[0].equals("misc1") && Miscellaneous.whatEvent.toString().equals("sick")){
                if(player.coinCount >= 10){

                    // holder.delete(0, holder.length());
                    // holder.append("assets/soundeffects/medicine.wav");
                    StartGui.playSound("assets/soundeffects/medicine.wav");

                    validAction = true;
                    player.coinCount -= 10;
                    GUI.showSick(false);
                } else {
                    // holder.delete(0, holder.length());
                    // holder.append("assets/soundeffects/Sound Effects/notenoughcoin.wav");
                    StartGui.playSound("assets/soundeffects/Sound Effects/notenoughcoin.wav");
                }
            }
            else if(selectedMisc[0].equals("misc2") && Miscellaneous.whatEvent.toString().equals("brainrot")){

                if(player.coinCount >= 20){
                    // holder.delete(0, holder.length());
                    // holder.append("assets/soundeffects/lost a life.wav");
                    StartGui.playSound("assets/soundeffects/lost a life.wav");
                    
                    validAction = true;
                    player.coinCount -= 20;
                    GUI.showBrainRot(false);
                } else {
                    // holder.delete(0, holder.length());
                    // holder.append("assets/soundeffects/Sound Effects/notenoughcoin.wav");
                    StartGui.playSound("assets/soundeffects/Sound Effects/notenoughcoin.wav");
                }
            }
            else if(selectedMisc[0].equals("misc3") && Miscellaneous.whatEvent.toString().equals("exam")){
                GradGoMad.timerBySecond += 10;
                validAction = true;
                GUI.showExam(false);
                StartGui.playSound("assets/soundeffects/writing.wav");
                
            } else {
                // holder.delete(0, holder.length());
                // holder.append("assets/soundeffects/Sound Effects/lost a life.wav");
                StartGui.playSound("assets/soundeffects/Sound Effects/notenoughcoin.wav");
            }

            // holder.delete(0, holder.length());
            // holder.append("haha");

            
        }

        if(validAction){
            Miscellaneous miscellaneous = Miscellaneous.miscGetInstance();
            miscellaneous.doSomethingMisc();
        }

        

	    GUI.coinBar.setText(String.valueOf(player.coinCount));
	    GUI.acadBar.setText(String.valueOf(player.acadCount));

    }
}