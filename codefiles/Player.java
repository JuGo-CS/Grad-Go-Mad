package codefiles;

public class Player {
    private static Player player = null;
    private final int MAX_STATS = 100;
    
    private int sleepBar;
    private int foodBar;
    protected int coinCount;
    protected int acadCount;
    static boolean enoughCoins;

    private Player(){
        sleepBar = 100;
        foodBar = 100;
        coinCount = 0;
        acadCount = 0;
    }

    public static Player createPlayer(){
        if (player == null) {
            player = new Player();
        }

        return player;
    }


    public int getSleepBar(){
        return sleepBar;
    }

    public int getFoodBar(){
        return foodBar;
    }

    public int getAcadBar(){
        return acadCount;
    }

    //we cant use this since lamp ang aton gamiton for sleep
    // presentation lang hehe

    // public void setSleepBar(int value){
    //     sleepBar += value;
    // }

    // public void setFoodBar(int value){
    //     foodBar += value;
    // }

    //Adds and subtracts Stats
    public void incrementSleep(int value){
        this.sleepBar += value;
    }

    public void incrementFood(int value){
        this.foodBar += value;
    }

    public void incrementAcads(int value){
        this.acadCount += value;
    }

    public void incrementCoins(int value){
        this.coinCount += value;
    }



    //Checks if the stats are full and retrun it to 100
    public void max100Stats(){
        if(sleepBar > MAX_STATS){
            sleepBar = 100;
        }

        if(foodBar > MAX_STATS){
            foodBar = 100;
        }
        
    }

    //check if SleepBar and FoodBar is NOT empty
    public boolean checkStats() {
        if (sleepBar <= 0 || foodBar <= 0) {
            return false; 
        }
        return true; 
    }
    

    public void updateStats (String whatEvent, int whatYear) {
        switch (whatEvent) {
            case "brainrot":
                sleepBar -= (whatYear * 2);
                foodBar -= (whatYear);
                break;

            case "exam":
                sleepBar -= (whatYear);
                foodBar -= (whatYear);
                break;

            case "sick":
                sleepBar -= (whatYear);
                foodBar -= (whatYear * 2);
                break;
        }
    }

    public void updateStats(int whatYear) {
        sleepBar -= (whatYear);
        foodBar -= (whatYear);
    }


    public void updateStatsBar(){
        int currentFoodLevel = player.getFoodBar()/20;
        String imageFileString = "";
        switch(currentFoodLevel){
            case 5:
            case 4:
                imageFileString = "assets/statusBars/foodBar5.png";
                break;
            case 3:
                imageFileString = "assets/statusBars/foodBar4.png";
                break;
            case 2:
                imageFileString = "assets/statusBars/foodBar3.png";
                break;
            case 1:
                imageFileString = "assets/statusBars/foodBar2.png";
                break;
            case 0:
                imageFileString = "assets/statusBars/foodBar1.png";
                break;
            default:
                return;
        }
        GUI.changeFoodBar(imageFileString);

        int currentSleepLevel = player.getSleepBar()/20;
        switch(currentSleepLevel){
            case 5:
            case 4:
                imageFileString = "assets/statusBars/sleepBar5.png";
                break;
            case 3:
                imageFileString = "assets/statusBars/sleepBar4.png";
                break;
            case 2:
                imageFileString = "assets/statusBars/sleepBar3.png";
                break;
            case 1:
                imageFileString = "assets/statusBars/sleepBar2.png";
                break;
            case 0:
                imageFileString = "assets/statusBars/sleepBar1.png";
                break;

            default:
                return;
        }
        GUI.changeSleepBar(imageFileString);
    }
}
