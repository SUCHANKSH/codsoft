import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApplication extends JFrame {
    private JLabel questionLabel;
    private JCheckBox[] optionCheckBoxes;
    private JButton submitButton;
    private JLabel timerLabel;
    private Timer timer;

    private String[] questions;
    private String[][] options;
    private boolean[][] correctAnswers;
    private int currentQuestionIndex;
    private int score;
    private int secondsRemaining;

    public QuizApplication() {
        setTitle("Quiz Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        // Initialize components
        questionLabel = new JLabel();
        optionCheckBoxes = new JCheckBox[4];
        for (int i = 0; i < 4; i++) {
            optionCheckBoxes[i] = new JCheckBox();
        }
        submitButton = new JButton("Submit");
        timerLabel = new JLabel();

        // Add components to the frame
        add(questionLabel);
        JPanel optionsPanel = new JPanel(new GridLayout(1, 4));
        for (JCheckBox checkBox : optionCheckBoxes) {
            optionsPanel.add(checkBox);
        }
        add(optionsPanel);
        add(submitButton);
        add(timerLabel);

        // Set up quiz questions, options, and correct answers
        questions = new String[]{
                "What is the capital of France?",
                "Who wrote 'Romeo and Juliet'?",
                "What is the chemical symbol for water?",
                "What is the tallest mammal?",
                "What is the largest planet in our solar system?"
        };

        options = new String[][]{
                {"Paris", "London", "Berlin", "Rome"},
                { "Charles Dickens", "Jane Austen","William Shakespeare", "Mark Twain"},
                { "CO2","H2O", "O2", "NaCl"},
                {"Giraffe", "Elephant", "Whale", "Penguin"},
                { "Saturn", "Mars","Jupiter", "Earth"}
        };

        correctAnswers = new boolean[][]{
                {true, false, false, false},  // Paris is correct for the first question
                { false, false, true, false},  // William Shakespeare is correct for the second question
                {false, true, false, false},  // H2O is correct for the third question
                {true, false, false, false},  // Giraffe is correct for the fourth question
                {false, false, true, false}  // Earth is correct for the fifth question
        };

        score = 0;
        currentQuestionIndex = 0;

        // Display first question
        displayQuestion();

        // Start timer
        secondsRemaining = 15; // Time limit for each question
        timerLabel.setText("Time left: " + secondsRemaining);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsRemaining--;
                timerLabel.setText("Time left: " + secondsRemaining);
                if (secondsRemaining == 0) {
                    checkAnswer();
                }
            }
        });
        timer.start();

        // Action listener for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
    }

    private void displayQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < 4; i++) {
            optionCheckBoxes[i].setText(options[currentQuestionIndex][i]);
            optionCheckBoxes[i].setSelected(false);
        }
    }

    private void checkAnswer() {
        timer.stop(); // Stop the timer

        boolean[] selectedOptions = new boolean[4];
        for (int i = 0; i < 4; i++) {
            selectedOptions[i] = optionCheckBoxes[i].isSelected();
        }

        // Check if the selected options match the correct answers
        boolean[] correctOptions = correctAnswers[currentQuestionIndex];
        boolean allCorrect = true;
        for (int i = 0; i < 4; i++) {
            if (selectedOptions[i] != correctOptions[i]) {
                allCorrect = false;
                break;
            }
        }

        if (allCorrect) {
            score++;
        }

        currentQuestionIndex++;

        if (currentQuestionIndex < questions.length) {
            secondsRemaining = 15; // Reset timer
            timer.start(); // Restart the timer
            displayQuestion();
        } else {
            showResult();
        }
    }

    private void showResult() {
        // Display final score and summary of correct/incorrect answers
        JOptionPane.showMessageDialog(this, "Quiz Over!\nYour Score: " + score + "/" + questions.length);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuizApplication().setVisible(true);
            }
        });
    }
}
