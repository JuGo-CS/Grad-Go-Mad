package finalproject;

class FactoryForObject{
    public static FoodClass createFoodObject(String foodName){
        return switch (foodName) {
            case "junkFood" -> new JunkFoods();
            case "comfortFood" -> new ComfortFoods();
            case "healthyFood" -> new HealthyFoods();
            default -> null;
        };
    }

    public static AcadClass createAcadsObject(String acadsName){
        return switch (acadsName) {
            case "buyBook" -> new BuyBooks();
            case "studyHub" -> new StudyHubs();
            case "tutor" -> new Tutor();
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
            System.out.println(this.foodCost);
            playerInstance.incrementFood(this.hungerAdd);
            playerInstance.incrementCoins(-this.foodCost);

        } else {
            System.out.println("Not Enough Coins for Foods");
            //throw exception or something na ma pop up sa screen na ma say sorry, coins not enough
        }
    }
}

class JunkFoods extends FoodClass{
    public JunkFoods(){
        hungerAdd = 10;
        foodCost = 10;
    }
}

class ComfortFoods extends FoodClass{
    public ComfortFoods(){
        hungerAdd = 15;
        foodCost = 25;
    }
}

class HealthyFoods extends FoodClass{
    public HealthyFoods(){
        hungerAdd = 30;
        foodCost = 60;
    }
}


abstract class AcadClass{
    Player playerInstance = Player.createPlayer();
    protected int acadAdd;
    protected int acadCost;

    public void changeAcadStats(boolean isSickIgnored) {
        if(playerInstance.coinCount >= this.acadCost){
            // if(isSickIgnored) {
            //     playerInstance.incrementAcads((int) Math.round(this.acadAdd / 2));
            // } else {
                playerInstance.incrementAcads(this.acadAdd);
            // }
            playerInstance.incrementCoins(-this.acadCost);

        } else {
            System.out.println("Not Enough Coins for Acads Object");
            //throw exception or something na ma pop up sa screen na ma say sorry, coins not enough
        }
    }
}

class BuyBooks extends AcadClass{
    public BuyBooks(){
        acadAdd = 50;
        acadCost = 30;
    }
}

class StudyHubs extends AcadClass{
    public StudyHubs(){
        acadAdd = 100;
        acadCost = 50;
    }
}

class Tutor extends AcadClass{
    public Tutor(){
        acadAdd = 200;
        acadCost = 100;
    }
}


// Line 21
// public static AcadClass createAcadsObject(String acadsName){
    //     AcadClass acadClass = null;

    //     switch (acadsName) {
    //         case "buyBook":
    //             acadClass = new BuyBooks();
    //             break;

    //         case "studyHub":
    //             acadClass = new StudyHubs();
    //             break;

    //         case "tutor":
    //             acadClass = new BuyBooks();
    //             break;
    //     }

    //     return acadClass;
    // }