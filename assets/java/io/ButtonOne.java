package finalproject;
import java.awt.*;
import javax.swing.*;

import finalproject.GradGoMad;
import finalproject.Player;

public class ButtonOne extends NullPointerException {
    
    public void showFoodOptions() {
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel Fpanel = new JPanel(new GridLayout(3, 3));

        Icon[] options = {
            new ImageIcon(new ImageIcon("foodsphotos/cf1.jpg").getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("foodsphotos/jf3.jpg").getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("foodsphotos/hf2.jpg").getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("foodsphotos/hf3.jpg").getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("foodsphotos/cf2.jpg").getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("foodsphotos/jf2.jpg").getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("foodsphotos/jf1.jpg").getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("foodsphotos/hf1.jpg").getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)),
            new ImageIcon(new ImageIcon("foodsphotos/cf3.jpg").getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH))
        };

        String[] foodNames = {
            "CF1-Lechon Manok", "JF3-Doritos", "HF2-Sandwich", "HF3-Shawarma",
            "CF2-Fishball", "JF2-Fries", "JF1-Coke", "HF1-Lemon", "CF3-Ice Cream"
        };

        JButton[] foodButtons = new JButton[options.length];
        final String[] selectedFood = {null};

        for (int i = 0; i < options.length; i++) {
            foodButtons[i] = new JButton(options[i]);
            int index = i;
            foodButtons[i].addActionListener(e1 -> {
                selectedFood[0] = foodNames[index];
                ((JButton) e1.getSource()).getTopLevelAncestor().setVisible(false);
            });
            Fpanel.add(foodButtons[i]);
        }

        JOptionPane.showOptionDialog(
            frame,
            Fpanel,
            "Select your preferred food option:",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new Object[] {},
            null
        );

        String foodClassString = "";
        switch (selectedFood[0]) {

            //For the JunkFoods
            case "JF1-Coke":
            case "JF2-Fries":
            case "JF3-Doritos":
                foodClassString = "junkFood";
                break;
            
            //For Comfort Food
            case "CF1-Lechon Manok":
            case "CF2-Fishball":
            case "CF3-Ice Cream":
                foodClassString = "comfortFood";
                break;
            
            //For Healthy Foods
            case "HF1-Lemon":
            case "HF2-Sandwich":
            case "HF3-Shawarma":
                foodClassString = "healthyFood";
                break;

            default:
                break;
        }

        FoodClass foodClass = FactoryForObject.createFoodObject(foodClassString);
        foodClass.changeFoodStats();
        GUI.coinBar.setText(String.valueOf(foodClass.playerInstance.coinCount));
    
        if (selectedFood[0] != null) {
            System.out.println("User selected: " + selectedFood[0]);
        }
    }
}
