import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class number_guessing extends JFrame {
    private int targetNumber;
    private int attempts;

    private JTextField guessField;
    private JLabel resultLabel;

    public number_guessing() {
        setTitle("Number Guessing Game");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        targetNumber = generateRandomNumber();
        attempts = 0;

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel instructionLabel = new JLabel("Guess the number between 1 and 100:");
        guessField = new JTextField();
        JButton guessButton = new JButton("Guess");
        resultLabel = new JLabel("");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        panel.add(instructionLabel);
        panel.add(guessField);
        panel.add(guessButton);

        add(panel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if (guess < targetNumber) {
                resultLabel.setText("Too low! Try again.");
            } else if (guess > targetNumber) {
                resultLabel.setText("Too high! Try again.");
            } else {
                resultLabel.setText("Congratulations! You guessed the number in " + attempts + " attempts.");
                guessField.setEditable(false);
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a number.");
        }
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new number_guessing().setVisible(true);
            }
        });
    }
}
