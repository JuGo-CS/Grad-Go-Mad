package finalproject;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GradGoMad{
    static Random random = new Random();
    static Player player = Player.createPlayer();
    protected static int currentYearLevel = 1; //Current year level of the player
    protected static StringBuilder actionButton = new StringBuilder();
    
    public static void main(String[] args){
        GradGoMad.start();
        GUI.main(new String[0]);
        boolean isGameOn = true;
        boolean isPlayerDied = false;
        
        //pag run ug game, instantly pick if what yearlvl the mini game will pop up
        //plan namin ni Meshach para easy nalang + 1 mini game lang per game.

        // int whichYrMiniGame = random.nextInt(4) + 1; still a plan

        int currentYearLvl = 0;
        while(isGameOn && timerBySecond <= 360) { //can be edit ang condition

            boolean statsGood = player.checkStats();
            if (!statsGood) {  // When stats are good, player is not dead
                System.out.println("INSIDE");
                isPlayerDied = true;
                isGameOn = false;
                break;
            }

            //this is just temporary, gin butang ko lang string for temporary
            //it must be a keylistener from the player, masunod if ano na button gin tap kang player
            switch (GradGoMad.actionButton.toString()) {
                case "foodButton" -> feedActionButton();
                case "acadButton" -> acadActionButton();
                case "miscButton" -> miscActionButton();
            }


            //here where the mini game will enter
            //int timeClockSecond = 30;//if like lets say 1 min per year, in 30 seconds, the mini game will pop up + the time will stop
            //code that will let the time stops
            //this is still a plan
            // if((currentYearLvl == whichYrMiniGame) && (timerBySecond % timeClockSecond == 0)){
            //     //pauseTime + go to the popUp mini game;
            //     boolean isMiniGameWin = miniGame();

            //     if(isMiniGameWin){
            //         //will pop up tha
            //     }
            // }

            player.max100Stats(); //make sure that stats stay at 100 max
            GradGoMad.actionButton.delete(0, actionButton.length());
        }

        if(isPlayerDied){
            //basta ma output na gameover + start again
        }


        System.exit(0); 
    }

    public static boolean miniGame(){
        //implementation of mini game
        //go Meshach!!!

        return true;//TruOrAlse lang hehe
    }


    public static void feedActionButton(){
        //action that will show the button of 3
        //the Junk + Comfort + Healthy
        // String choiceFood = ""; //again temporary
        // FoodClass foodObject = FactoryForObject.createFoodObject(choiceFood);
        // foodObject.changeFoodStats();//Ill fix this next time nalang, di ko gets why ga error huhuhu

        System.out.println("FoodButtonsMethod");
        ButtonOne foodOptions = new ButtonOne();
        foodOptions.showFoodOptions();  
    }

    public static void acadActionButton(){
        //action that will show the button of 3
        //the BuyBooks + StudyHub + Tutor
        // String choiceAcad = ""; //again temporary
        // AcadClass acadObject = FactoryForObject.createAcadsObject(choiceAcad);
        // acadObject.changeAcadStats(isMiscellaneousIgnore);

        System.out.println("acadButtonsMethod");
        ButtonTwo acadOptions = new ButtonTwo();
        acadOptions.showAcadOptions();  
    }

    public static void miscActionButton(){
        
    }

    //time component of this game

    public static int timerBySecond = 0;
    public static boolean activeEvent;
    public static boolean isMiscellaneousIgnore = false; 

    //
    //
    //
    //  LOOK AT THIS
    //
    //
    //take note na ang button sa action for Misc ay e check wether na action-nan ng user ang misc na need
    //if yes, like if sick then napagamot ni player and iya na character, e make sure na e set ang "isMiscellaneousIgnore" E SET SA FALSE;
    //Need siya for logic in line 131 to 138
    
    static Timer timer = new Timer();
    static TimerTask task = new TimerTask() {

        int secondCounterForExam = 0;

        public void run() {
            timerBySecond++;
            secondCounterForExam++;

            StringBuilder whatEvent = Miscellaneous.start();

            if(timerBySecond % 91 == 0) {
                currentYearLevel++;
            }

            // if(whatEvent.equals("exam") && secondCounterForExam == 5){
            //     player.getAcadCount() -= (int) Math.round(player.getAcadCount() * 0.25);
            //     secondCounterForExam = 0;
            // }

            // if(whatEvent.toString().equals("empty")){
                player.updateStats(currentYearLevel);

            // } else {

            //     if (isMiscellaneousIgnore) {
            //         player.updateStats(whatEvent.toString(), currentYearLevel);
            //         secondCounterForExam = 0;

            //     } else {
            //         Miscellaneous.whatEvent.delete(0, Miscellaneous.whatEvent.length());
            //         Miscellaneous.whatEvent.append("empty");
            //         player.updateStats(currentYearLevel);
            //     }
                
            // }

            System.out.println("Sleep: " + player.getSleepBar() + " Food: " + player.getFoodBar() + " Current Year: " + currentYearLevel + " Time: " + timerBySecond);

            
        }
    };
            
    public static void start() {
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }
}

