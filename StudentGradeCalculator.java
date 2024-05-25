import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator extends JFrame {
    private JLabel[] subjectLabels;
    private JTextField[] marksFields;
    private JButton calculateButton;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    private int totalMarks;
    private double averagePercentage;
    private char grade;

    public StudentGradeCalculator() {
        setTitle("Student Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        // Initialize components
        subjectLabels = new JLabel[5];
        marksFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + " Marks:");
            marksFields[i] = new JTextField();
            add(subjectLabels[i]);
            add(marksFields[i]);
        }

        calculateButton = new JButton("Calculate");
        totalMarksLabel = new JLabel("Total Marks: ");
        averagePercentageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Grade: ");
        
        // Add components to the frame
        add(calculateButton);
        add(totalMarksLabel);
        add(averagePercentageLabel);
        add(gradeLabel);

        // Action listener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGrade();
            }
        });
    }

    private void calculateGrade() {
        totalMarks = 0;
        for (int i = 0; i < 5; i++) {
            try {
                int marks = Integer.parseInt(marksFields[i].getText());
                totalMarks += marks;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid marks for all subjects.");
                return;
            }
        }

        averagePercentage = (double) totalMarks / 5;

        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Display the results
        totalMarksLabel.setText("Total Marks: " + totalMarks);
        averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
        gradeLabel.setText("Grade: " + grade);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentGradeCalculator().setVisible(true);
            }
        });
    }
}
