package codefiles;

class FactoryForObject{
    public static FoodClass createFoodObject(String foodName){
        return switch (foodName) {
            case "junkFood" -> JunkFoods.getJunkFoodInstance();
            case "comfortFood" -> ComfortFoods.getComfortFoodsInstance();
            case "healthyFood" -> HealthyFoods.getHealthyFoodsInstance();
            default -> null;
        };
    }

    public static AcadClass createAcadsObject(String acadsName){
        return switch (acadsName) {
            case "buyBook" -> BuyBooks.getBuyBooksInstance();
            case "studyHub" -> StudyHubs.getStudyHubsInstance();
            case "tutor" -> Tutor.getTutorInstance();
            default -> null;
        };
    }
}

abstract class FoodClass{
    Player playerInstance = Player.createPlayer();
    protected int hungerAdd;
    protected int foodCost;

    public void changeFoodStats(){
        if(playerInstance.coinCount >= this.foodCost) {
            playerInstance.incrementFood(this.hungerAdd);
            playerInstance.incrementCoins(-this.foodCost);
            StartGui.playSound("assets/soundeffects/Sound Effects/selectfood.wav");
        } else {
            StartGui.playSound("assets/soundeffects/Sound Effects/notenoughcoin.wav");
        }
    }
}

class JunkFoods extends FoodClass{
    private static JunkFoods junkFoodsInstance = null;
    private JunkFoods(){
        hungerAdd = 10;
        foodCost = 10;
    }

    public static JunkFoods getJunkFoodInstance(){
        if(junkFoodsInstance == null) {
            junkFoodsInstance = new JunkFoods();
        }

        return junkFoodsInstance;
    }
}

class ComfortFoods extends FoodClass{
    private static ComfortFoods comfortFoodsInstance = null;
    private ComfortFoods(){
        hungerAdd = 25;
        foodCost = 15;
    }

    public static ComfortFoods getComfortFoodsInstance(){
        if(comfortFoodsInstance == null) {
            comfortFoodsInstance = new ComfortFoods();
        }

        return comfortFoodsInstance;
    }
}

class HealthyFoods extends FoodClass{
    private static HealthyFoods healthyFoodsInstance = null;
    private HealthyFoods(){
        hungerAdd = 60;
        foodCost = 30;
    }

    public static HealthyFoods getHealthyFoodsInstance(){
        if(healthyFoodsInstance  == null) {
            healthyFoodsInstance  = new HealthyFoods();
        }

        return healthyFoodsInstance ;
    }

}


abstract class AcadClass{
    Player playerInstance = Player.createPlayer();
    protected int acadAdd;
    protected int acadCost;

    public void changeAcadStats(boolean isSickIgnored) {
        
        if(playerInstance.coinCount >= this.acadCost){
            ButtonTwo.musicForAcad(true);
            if(isSickIgnored) {
                playerInstance.incrementAcads((int) Math.round(this.acadAdd / 2));
            } else {
                playerInstance.incrementAcads(this.acadAdd);
            }
            playerInstance.incrementCoins(-this.acadCost);

        } else {
            ButtonTwo.musicForAcad(false);
        }
    }

    
}

class BuyBooks extends AcadClass{
    private static BuyBooks buyBooksInstance = null;
    
    private BuyBooks(){
        acadAdd = 50;
        acadCost = 30;
    }

    public static BuyBooks getBuyBooksInstance(){
        if(buyBooksInstance  == null) {
            buyBooksInstance  = new BuyBooks();
        }

        return buyBooksInstance ;
    }
}

class StudyHubs extends AcadClass{
    private static StudyHubs studyHubsInstance = null;

    private StudyHubs(){
        acadAdd = 100;
        acadCost = 50;
    }

    public static StudyHubs getStudyHubsInstance(){
        if(studyHubsInstance  == null) {
            studyHubsInstance  = new StudyHubs();
        }

        return studyHubsInstance ;
    }
}

class Tutor extends AcadClass{
    private static Tutor tutorInstance = null;

    private Tutor(){
        acadAdd = 200;
        acadCost = 100;
    }

    public static Tutor getTutorInstance(){
        if(tutorInstance  == null) {
            tutorInstance  = new Tutor();
        }

        return tutorInstance ;
    }
}