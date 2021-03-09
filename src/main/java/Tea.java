public class Tea extends Beverage {
    // extend = add to the class (become a child of)
    private int caffeine; // x per mg
    private String strength; // strong, weak, medium, any
    protected boolean cream;
    protected boolean lemon;
    protected int sugar; // lumps of sugar
    protected boolean expiration = false;


    public Tea() {
        // this = this class
        // super = the super class
        super();
        ingredients.add("tea leaves");
        cream = false;
        lemon = false;
        sugar = 2;

        caffeine = 10;
        strength = "strong";
    }

    public Tea(int caffeine, String strength) {
        super();
        this.caffeine = caffeine;
        this.strength = strength;
    }

    // getter/setters

    public String getStrength() {
        return strength;
    }

    /* get region:
            no attribute because we are only calling
         */
    protected String getRegion() { return "China"; } // #1 producer of tea

    // Override the methods which conflict with what I need to do here

    @Override
    protected boolean checkExpired() {
        if (expiration) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean checkExpired(boolean status) {
        if (status) {
            expiration = true;
        } else {
            expiration = false;
        }
        return expiration;
    }
}
