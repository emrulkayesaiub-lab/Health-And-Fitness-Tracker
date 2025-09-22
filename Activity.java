public class Activity {
    private String type;
    private int duration; 
    private int calories;

    public Activity(String type, int duration, int calories) {
        this.type = type;
        this.duration = duration;
        this.calories = calories;
    }

    public String getType() { return type; }
    public int getDuration() { return duration; }
    public int getCalories() { return calories; }
}
