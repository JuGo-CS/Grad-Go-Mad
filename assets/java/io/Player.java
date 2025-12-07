package finalproject;

public class Player {
    private static Player player = null;
    private final int MAX_STATS = 100;
    
    private int sleepBar;
    private int foodBar;
    protected int coinCount;
    protected int acadCount;

    private Player(){
        sleepBar = 100;
        foodBar = 100;
        coinCount = 1000;
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
        return foodBar;
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
        System.out.println("AcadBar now is: " + acadCount);
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
                //twice decrease speed of sleep bar
                sleepBar -= (2 * whatYear);
                foodBar -= (1 * whatYear);
                break;

            case "exam":
            case "sick":
                sleepBar -= (1 * whatYear);
                foodBar -= (1 * whatYear);
            //still thinking on how to implement the exam and sick
        }
    }

    public void updateStats(int whatYear) {
        sleepBar -= (1 * whatYear);
        foodBar -= (1 * whatYear);
    }
}
