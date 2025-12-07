package finalproject;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import finalproject.GradGoMad;

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

    
    private static final int MAX_SECOND = 360; 
    private static final int SECONDS_PER_YEAR = 90;
    private static final int DIFFICULTY_PERCENTAGE = 9; //highnumber means less difficulty (lower percentage)

    public static StringBuilder start() {
        Miscellaneous miscInstance = Miscellaneous.miscGetInstance();
        if (GradGoMad.timerBySecond % 10 == 0) {
            miscInstance.getRandomEvent();
        }
        
        return whatEvent;
    };

    public static StringBuilder whatEvent = new StringBuilder("empty");

    public void getRandomEvent() {
        if (!activeActivity) {
            int percentageInt = DIFFICULTY_PERCENTAGE - GradGoMad.currentYearLevel;

            if (random.nextInt(percentageInt) == GradGoMad.currentYearLevel) {
                int eventType = random.nextInt(3); // 0 = BrainRot, 1 = Exam, 2 = Sick
        
                switch (eventType) {
                    case 0 -> {
                        isBrainRot = true;
                        whatEvent.append("brainrot");
                    }
                    case 1 -> {
                        thereExam = true;
                        whatEvent.append("exam");
                    }
                    case 2 -> {
                        isSick = true;
                        whatEvent.append("sick");
                    }
                }
                GradGoMad.isMiscellaneousIgnore = true;
                activeActivity = true;
                resetEvent();
            }
            
        }

    }
    

    private void resetEvent() {
        Timer resetTimer = new Timer();
        resetTimer.schedule(new TimerTask() {
            public void run() {
                // Reset all states
                isBrainRot = false;
                thereExam = false;
                isSick = false;
                activeActivity = false;
                whatEvent.delete(0, whatEvent.length());
    
                resetTimer.cancel();
            }
        }, 10);
    }
    
}
