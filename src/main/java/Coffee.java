public class Coffee extends Beverage {
    private int caffeine; // x per mg
    private String strength; // strong, weak, medium, any
    private String creamerType;
    private String coffeeType;
    private String allergens;
    private String brewType;

    public Coffee() {
        super();
        allergens = "caffeine";
        ingredients.add("coffee beans");
        caffeine = 2;
        brewType = "drip";
    }

    public Coffee(String strength, String coffeeType, String creamerType) {
        super();
        this.strength = strength;
        this.ingredients.add(coffeeType);
        this.coffeeType = coffeeType;
        this.ingredients.add(creamerType);
        this.creamerType = creamerType;
        this.allergens = "caffeine dairy";
    }

    public int getCaffeine() {
        return caffeine;
    }

    public String getStrength() {
        return strength;
    }

    public String getAllergens() {
        return allergens;
    }

    protected String getBrewType() {
        switch(coffeeType.toLowerCase()) {
            default: case "espresso": case "columbian":
                return "drip";
            case "French press":
                return "press";
            case "donut shop": case "mocha":
                return "pod";
        }
    }
}
