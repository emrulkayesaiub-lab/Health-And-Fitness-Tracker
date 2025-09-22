import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FitnessGUI extends JFrame {
    private User user;
    private ArrayList<Activity> activities = new ArrayList<>();
    private ArrayList<Diet> diets = new ArrayList<>();

    private JTextField nameField, ageField, feetField, inchField, weightField;
    private JTextArea outputArea;

    public FitnessGUI() {
        setTitle("Health & Fitness Tracker");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel profilePanel = new JPanel(new GridLayout(6,2));
        profilePanel.setBorder(BorderFactory.createTitledBorder("User Profile"));

        profilePanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        profilePanel.add(nameField);

        profilePanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        profilePanel.add(ageField);

        profilePanel.add(new JLabel("Height (ft):"));
        feetField = new JTextField();
        profilePanel.add(feetField);

        profilePanel.add(new JLabel("Height (in):"));
        inchField = new JTextField();
        profilePanel.add(inchField);

        profilePanel.add(new JLabel("Weight (kg):"));
        weightField = new JTextField();
        profilePanel.add(weightField);

        JButton saveUserBtn = new JButton("Save Profile");
        profilePanel.add(saveUserBtn);

        add(profilePanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        JButton addActivityBtn = new JButton("Add Activity");
        JButton addDietBtn = new JButton("Add Diet");
        JButton showReportBtn = new JButton("Show Report");

        bottomPanel.add(addActivityBtn);
        bottomPanel.add(addDietBtn);
        bottomPanel.add(showReportBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        saveUserBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                int feet = Integer.parseInt(feetField.getText());
                int inches = Integer.parseInt(inchField.getText());
                double weight = Double.parseDouble(weightField.getText());

                int totalInches = (feet * 12) + inches;
                double heightMeters = totalInches * 0.0254;

                user = new User(name, age, heightMeters, weight);

                outputArea.setText("Profile Saved!\nName: " + user.getName() +
                        "\nHeight: " + feet + " ft " + inches + " in" +
                        "\nWeight: " + weight + " kg" +
                        "\nBMI: " + String.format("%.2f", user.calculateBMI()) +
                        " (" + user.getBMICategory() + ")");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Try again.");
            }
        });

        addActivityBtn.addActionListener(e -> {
            try {
                String type = JOptionPane.showInputDialog("Enter activity type (Running/Walking):");
                int duration = Integer.parseInt(JOptionPane.showInputDialog("Enter duration (min):"));
                int cal = Integer.parseInt(JOptionPane.showInputDialog("Enter calories burned:"));
                activities.add(new Activity(type, duration, cal));
                outputArea.append("\nAdded Activity: " + type + " (" + duration + " min, " + cal + " cal)");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Try again.");
            }
        });

        addDietBtn.addActionListener(e -> {
            try {
                String food = JOptionPane.showInputDialog("Enter food name:");
                int cal = Integer.parseInt(JOptionPane.showInputDialog("Enter calories:"));
                diets.add(new Diet(food, cal));
                outputArea.append("\nAdded Diet: " + food + " (" + cal + " cal)");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Try again.");
            }
        });

        showReportBtn.addActionListener(e ->{
            int totalActivityCal = activities.stream().mapToInt(Activity::getCalories).sum();
            int totalDietCal = diets.stream().mapToInt(Diet::getCalories).sum();

            outputArea.append("\n\n===== Daily Report =====");
            outputArea.append("\nTotal Calories Burned: " + totalActivityCal);
            outputArea.append("\nTotal Calories Consumed: " + totalDietCal);
            outputArea.append("\nNet Balance: " + (totalDietCal - totalActivityCal));
        });
    }
}
