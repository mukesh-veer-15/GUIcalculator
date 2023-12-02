


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame {
    private JTextField displayField;

    private String operator;
    private double operand1;

    public CalculatorGUI() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        operator = "";
        operand1 = 0.0;

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        panel.add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);

        add(panel);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String command = source.getText();

            if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
                displayField.replaceSelection(command);
            } else {
                performOperation(command);
            }
        }
    }

    private void performOperation(String command) {
        switch (command) {
            case "=":
                calculateResult();
                operator = "";
                break;
            case "/":
            case "*":
            case "-":
            case "+":
                setOperator(command);
                break;
            default:
                break;
        }
    }

    private void setOperator(String newOperator) {
        operator = newOperator;
        operand1 = Double.parseDouble(displayField.getText());
        displayField.setText("");
    }

    private void calculateResult() {
        double result = 0.0;
        double operand2 = Double.parseDouble(displayField.getText());

        switch (operator) {
            case "/":
                result = operand1 / operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "+":
                result = operand1 + operand2;
                break;
            default:
                break;
        }

        displayField.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculatorGUI().setVisible(true);
            }
        });
    }
}


