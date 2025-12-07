package codefiles;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Miscellaneous {
    private static Miscellaneous instanceMisc = null;
    static Random random = new Random();

    private boolean isBrainRot;
    private boolean thereExam;
    private boolean isSick;
    private boolean activeActivity;

    private Miscellaneous() {
        isBrainRot = false;
        thereExam = false;
        isSick = false;
        activeActivity = false;
    }

    public static Miscellaneous miscGetInstance() {
        if (instanceMisc == null) {
            instanceMisc = new Miscellaneous();
        }
        return instanceMisc;
    }

    public Boolean getIsSick() {
        return isSick;
    }

    public Boolean getThereExam() {
        return thereExam;
    }

    public Boolean getIsBrainRot() {
        return isBrainRot;
    }

    public Boolean getActiveActivity() {
        return activeActivity;
    }

    private static final int DIFFICULTY_PERCENTAGE = 6; //(Current difficulty:9) highnumber means less difficulty (lower percentage)
    public static StringBuilder whatEvent = new StringBuilder("empty");

    public void getRandomEvent() {

        if (!activeActivity) {

            // int percentageInt = DIFFICULTY_PERCENTAGE - GradGoMad.currentYearLevel;
            int randomizeNumber = random.nextInt(DIFFICULTY_PERCENTAGE - GradGoMad.currentYearLevel);
            if (randomizeNumber == GradGoMad.currentYearLevel) {
                int eventType = random.nextInt(3); 
                whatEvent.delete(0, whatEvent.length());
                switch (eventType) {
                    case 0 -> {
                        isBrainRot = true;
                        whatEvent.append("brainrot");
                        GUI.showBrainRot(true);
                        StartGui.playSound("assets/soundeffects/brain rot.wav");
                    }
                    case 1 -> {
                        thereExam = true;
                        whatEvent.append("exam");
                        GUI.showExam(true);
                        StartGui.playSound("assets/soundeffects/tutor.wav");
                    }
                    case 2 -> {
                        isSick = true;
                        whatEvent.append("sick");
                        GUI.showSick(true);
                        StartGui.playSound("assets/soundeffects/gotsick.wav");
                    }
                }
                
                GradGoMad.isMiscellaneousIgnore = true;
                activeActivity = true;
                
            }
            
        }

    }

    public static StringBuilder start() {
        Miscellaneous miscInstance = Miscellaneous.miscGetInstance();
        miscInstance.getRandomEvent();
        
        return whatEvent;
    };
    
    private void resetEvent() {
        Timer resetTimer = new Timer();
        resetTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                isBrainRot = false;
                thereExam = false;
                isSick = false;
                whatEvent.delete(0, whatEvent.length());
                whatEvent.append("empty");
    
                resetTimer.cancel();
            }
        }, 1);
    }

    public void doSomethingMisc(){
        GradGoMad.isMiscellaneousIgnore = false;
        whatEvent.delete(0, whatEvent.length()); 
        whatEvent.append("empty");
        activeActivity = false;
        resetEvent();
    }
    
}
