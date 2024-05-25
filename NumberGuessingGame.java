import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private JLabel promptLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JLabel attemptsLabel; // Added label for displaying attempts left

    private int randomNumber;
    private int attemptsLeft;
    private int score;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1)); // Increased grid layout to accommodate the attempts label

        promptLabel = new JLabel("Enter your guess (1-100):");
        guessField = new JTextField();
        guessButton = new JButton("Guess");
        resultLabel = new JLabel("");
        attemptsLabel = new JLabel(""); // Initialize attempts label

        add(promptLabel);
        add(guessField);
        add(guessButton);
        add(resultLabel);
        add(attemptsLabel); // Add attempts label to the frame

        randomNumber = generateRandomNumber();
        attemptsLeft = 5;
        score = 0;

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        updateAttemptsLabel(); // Update attempts label initially
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            if (guess < 1 || guess > 100) {
                resultLabel.setText("Please enter a number between 1 and 100.");
            } else {
                if (guess == randomNumber) {
                    resultLabel.setText("Congratulations! You guessed it right.");
                    score++;
                    randomNumber = generateRandomNumber();
                    attemptsLeft = 5;
                } else if (guess < randomNumber) {
                    resultLabel.setText("Too low. Try again.");
                } else {
                    resultLabel.setText("Too high. Try again.");
                }
                attemptsLeft--;
                if (attemptsLeft == 0) {
                    resultLabel.setText("Out of attempts. The number was " + randomNumber);
                    randomNumber = generateRandomNumber();
                    attemptsLeft = 5;
                }
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number.");
        }
        guessField.setText("");
        updateAttemptsLabel(); // Update attempts label after each guess
    }

    private void updateAttemptsLabel() {
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}
