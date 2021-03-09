import java.util.ArrayList;
import java.util.List;

public class Beverage {
    private int taste; // 10 point scale
    private String temperature; // hot, cold, frozen, any
    public List<String> ingredients = new ArrayList<>();

    // Set default values for attributes
    public Beverage() {
        taste = 5;
        temperature = "any";
        ingredients.add("water");
    }

    // getter and setters
    public void setTemperature(String temperature) {
        switch(temperature.toLowerCase()) {
            case "cold": case "cool":
                this.temperature = "cold";
                break;
            case "hot": case "warm":
                this.temperature = "hot";
                break;
            case "frozen": case "ice": case "icey":
                this.temperature = "frozen";
                break;
            default:
                this.temperature = "any";
        }
    }
    public String getTemperature() { return temperature; }

    public void setTaste(int taste) { this.taste = taste; }
    public int getTaste() { return taste; }

    protected boolean checkExpired() { return false; }
    protected String getAllergens() { return "none"; }
}
