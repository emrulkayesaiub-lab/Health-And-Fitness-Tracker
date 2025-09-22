public class User {
    private String name;
    private int age;
    private double height; 
    private double weight; 

    public User(String name, int age, double height, double weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public double calculateBMI() {
        return weight / (height * height);
    }

    public String getBMICategory() {
        double bmi = calculateBMI();
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 24.9) return "Normal";
        else if (bmi < 29.9) return "Overweight";
        else return "Obese";
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getHeight() { return height; }
    public double getWeight() { return weight; }
}
