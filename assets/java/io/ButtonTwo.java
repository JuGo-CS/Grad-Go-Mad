package finalproject;
import java.awt.*;
import javax.swing.*;

public class ButtonTwo extends NullPointerException {

    public void showAcadOptions() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel Apanel = new JPanel();

        Icon[] options = {
            new ImageIcon(new ImageIcon("acadphotos/dictionary.jpg").getImage().getScaledInstance(95, 95, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("acadphotos/housee.jpg").getImage().getScaledInstance(95, 95, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("acadphotos/tutor2.jpg").getImage().getScaledInstance(95, 95, Image.SCALE_SMOOTH))
        };

        String[] acadNames = {"Subject 1-Book", "Subject 2-Study Hub", "Subject 3-Tutor"};

        JButton[] acadButtons = new JButton[options.length];
        final String[] selectedAcad = {null};

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
        switch (selectedAcad[0]) {
            case "Subject 1-Book":
                acadClassString = "buyBook";
                break;

            case "Subject 2-Study Hub":
                acadClassString = "studyHub";
                break;
                
            case "Subject 3-Tutor":
                acadClassString = "tutor";
                break;

            default:
                break;
        }
        // acadClass = FactoryForObject.createAcadsObject(acadClassString);
        // acadClass.changeAcadStats(Miscellaneous.getIsSick());

        // GUI.coinBar.setText(String.valueOf(acadClass.playerInstance.coinCount));
        // GUI.acadBar.setText(String.valueOf(acadClass.playerInstance.acadCount));
        Miscellaneous miscInstance = Miscellaneous.miscGetInstance();
        Player playerInstance = Player.createPlayer();
        AcadClass acadClass = FactoryForObject.createAcadsObject(acadClassString);
        acadClass.changeAcadStats(miscInstance.getIsSick());
        GUI.coinBar.setText(String.valueOf(playerInstance.coinCount));
        GUI.acadBar.setText(String.valueOf(playerInstance.acadCount));

        if (selectedAcad[0] != null) {
            System.out.println("User selected: " + selectedAcad[0]);
        }
    }
}
