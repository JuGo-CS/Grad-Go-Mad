package codefiles;
import java.util.Timer;
import java.util.TimerTask;


public class GradGoMad{
    // static Random random = new Random();
    static Player player = Player.createPlayer();
    protected static int currentYearLevel = 0; //Current year level of the player
    protected static StringBuilder actionButton = new StringBuilder();
    static boolean isPlayerDied = false;
    static boolean isGameOn = false;

    public static void main(String[] args){
        boolean isGameStarts = StartGui.getStartGui();

        if(isGameStarts){
            GradGoMad.start();
            StartGui.playMusic("assets/soundeffects/upnamingmahal8bit.wav");
            GUI.main(new String[0]);
            isGameOn = true;
            isPlayerDied = false;
        }
    }

    public static void continueGame(){
        if(isGameOn && timerBySecond <= 360) {
            boolean statsGood = player.checkStats();
            if (!statsGood) { 
                isPlayerDied = true;
                isGameOn = false;
            }

            
            GradGoMad.actionButton.delete(0, actionButton.length());
        } else {
            GradGoMad.stop();
            StartGui.stopMusic();
            
            if(isPlayerDied){
                GUI.printingResults(false);
            } else if (player.getAcadBar() >=  500){
                GUI.printingResults(true);

            } else {
                GUI.printingResults(false);
            }

            GUI.getInstanceGUI().getFrame().dispose();
        }

        
    }

    public static int timerBySecond = 0;
    public static int timerYear = 0;
    public static boolean activeEvent;
    public static boolean isMiscellaneousIgnore = false; 
    
    static Timer timer = new Timer();
    static TimerTask task = new TimerTask() {

        int secondCounterForExam = 0;

        @Override
        public void run() {
            player.max100Stats(); 
            timerBySecond++;

            if(player.getSleepBar() >= 0 && player.getFoodBar() >= 0){
                System.out.println("Sleep: " + player.getSleepBar() + " Food: " + player.getFoodBar() + " Current Year: " + currentYearLevel + " Time: " + timerBySecond);
            }

            StringBuilder whatEvent = new StringBuilder("empty");

            if(GradGoMad.timerBySecond % 10 == 0){
                whatEvent = Miscellaneous.start();
            }

            if(timerBySecond >= timerYear) {
                currentYearLevel += 1;
                GradGoMad.timerYear += 91; 
                StartGui.playSound("assets/soundeffects/level up.wav"); 
                TypingGame.updateRandomWordObject(currentYearLevel);
            }

            if(whatEvent.toString().equals("exam")){ 
                secondCounterForExam++;
                if (secondCounterForExam == 10){
                    secondCounterForExam = 0;
                    player.incrementAcads(-(int) Math.round(player.getAcadBar() * 0.2));
                
                    if(player.getAcadBar() < 0) {
                        player.acadCount = 0;
                    }
                
                GUI.acadBar.setText(Integer.toString(player.getAcadBar())); 
                }
            }

            if(secondCounterForExam % 10 == 0) {
                secondCounterForExam = 1;
            }

            if(whatEvent.toString().equals("empty")){
                player.updateStats(currentYearLevel);

            } else {
                if (!isMiscellaneousIgnore) {
                    player.updateStats(whatEvent.toString(), currentYearLevel);

                } else {
                    Miscellaneous.whatEvent.delete(0, Miscellaneous.whatEvent.length());
                    Miscellaneous.whatEvent.append("empty");
                    player.updateStats(currentYearLevel);
                }
                
            }

            player.updateStatsBar();
            continueGame();
        }
    };
    
    public static void start() {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }
    public static void stop() {
        timer.cancel();
        task.cancel();
    }
}




