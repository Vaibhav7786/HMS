import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateDifferenceCalculator extends JFrame {
    private JTextField date1Field;
    private JTextField date2Field;
    private JButton calculateButton;
    private JLabel resultLabel;

    public DateDifferenceCalculator() {
        initializeComponents();
        setupLayout();
        setupListeners();
    }

    private void initializeComponents() {
        date1Field = new JTextField(30);
        date2Field = new JTextField(30);
        calculateButton = new JButton("Calculate");
        resultLabel = new JLabel();
    }

    private void setupLayout() {
        setLayout(new FlowLayout());
        add(new JLabel("Date 1 (yyyy-MM-dd): "));
        add(date1Field);
        add(new JLabel("Date 2 (yyyy-MM-dd): "));
        add(date2Field);
        add(calculateButton);
        add(resultLabel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupListeners() {
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date1Text = date1Field.getText();
                String date2Text = date2Field.getText();

                try {
                    LocalDate date1 = LocalDate.parse(date1Text);
                    LocalDate date2 = LocalDate.parse(date2Text);
                    long daysDifference = ChronoUnit.DAYS.between(date1, date2);
                    resultLabel.setText("Difference In days: " + daysDifference);
                } catch (Exception ex) {
                    resultLabel.setText("Invalid date format");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DateDifferenceCalculator().setVisible(true);
            }
        });
    }
}