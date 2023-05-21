import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.sql.*;
import java.awt.*;

public class Reset_password extends JFrame implements ActionListener {

    String username1, password1, answer1, username, password, answer, securityQuestion;
    JButton saveDetailsButton, backButton;
    JFrame frame;
    Connection con;
    JLabel usernameLabel, securityQuestionLabel, answerLabel, newPasswordLabel, L_Hms, securityQuestionLabel2;
    JTextField usernameTextField, answerTextField;
    JPasswordField newPassTextField;

    public Reset_password() {
        try {
            con = ConnectionOneTime.getConnect();
            Statement st = con.createStatement();
            String q = "select * from UserDetails";
            ResultSet rs = st.executeQuery(q);
            while (rs.next()) {
                securityQuestion = "" + rs.getString("securityQ");
                username1 = "" + rs.getString("username");
                password1 = "" + rs.getString("password");
                answer1 = "" + rs.getString("answer");
            }
        } catch (Exception e) {

        }

        frame = new JFrame("Forget password");

        L_Hms = new JLabel("Enter Details");
        L_Hms.setBounds(450, 40, 500, 30);
        L_Hms.setFont(new Font("Alex", Font.BOLD, 25));

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(160, 120, 100, 60);
        usernameLabel.setFont(new Font("Alex", Font.BOLD, 15));

        securityQuestionLabel = new JLabel("Security Question");
        securityQuestionLabel.setBounds(160, 160, 150, 100);
        securityQuestionLabel.setFont(new Font("Alex", Font.BOLD, 15));

        answerLabel = new JLabel("Answer");
        answerLabel.setBounds(160, 200, 100, 140);
        answerLabel.setFont(new Font("Alex", Font.BOLD, 15));

        newPasswordLabel = new JLabel("New Password");

        newPasswordLabel.setBounds(160, 240, 150, 180);
        newPasswordLabel.setFont(new Font("Alex", Font.BOLD, 15));

        usernameTextField = new JTextField();
        usernameTextField.setBounds(400, 130, 350, 30);

        answerTextField = new JTextField();
        answerTextField.setBounds(400, 250, 350, 30);

        newPassTextField = new JPasswordField();
        newPassTextField.setBounds(400, 310, 350, 30);
        password = new String(newPassTextField.getPassword());

        saveDetailsButton = new JButton("Save");
        saveDetailsButton.setBounds(450, 390, 150, 40);

        backButton = new JButton("Back");
        backButton.setBounds(650, 390, 150, 40);

        securityQuestionLabel2 = new JLabel(securityQuestion);
        securityQuestionLabel2.setBounds(400, 160, 900, 100);
        securityQuestionLabel2.setFont(new Font("Alex", Font.BOLD, 15));

        add(securityQuestionLabel2);
        add(L_Hms);
        add(usernameLabel);
        add(securityQuestionLabel);
        add(answerLabel);
        add(newPasswordLabel);
        add(usernameTextField);
        add(answerTextField);
        add(newPassTextField);
        add(saveDetailsButton);
        add(backButton);

        saveDetailsButton.addActionListener(this);
        backButton.addActionListener(this);
        setBounds(500, 300, 900, 600);

        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setBounds(500, 300, 1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        answer = answerTextField.getText();
//        username = usernameTextField.getText();
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == backButton) {
                setVisible(false);
//                frame.dispose();
                new LoginForm();
            }
            if (e.getSource() == saveDetailsButton) {
                if (isValidDetails()) {
//                    password = new String(newPassTextField.getPassword());
//                    Connection con = ConnectionOneTime.getConnect();
                    PreparedStatement ps = con.prepareStatement("UPDATE UserDetails SET password = ? WHERE username = 'admin'");
                    ps.setString(1, password);
                    ps.executeUpdate();

//                    frame.dispose();
                    JOptionPane.showMessageDialog(frame, "Password Updated Successfully");
//                    new LoginForm();

                } else {
                    JOptionPane.showMessageDialog(frame, "Password Updation failed\n    Please try Again ");
                }
            }
        } catch (Exception g) {
            System.out.println("Exception : " + g);
        }
    }

    public static void main(String args[]) {
        Reset_password rs = new Reset_password();

    }

    private boolean isValidDetails() {
        answer = answerTextField.getText();
        username = usernameTextField.getText();
        password = new String(newPassTextField.getPassword());
        try {
            con = ConnectionOneTime.getConnect();
            Statement st = con.createStatement();
            String q = "select * from UserDetails";
            ResultSet rs = st.executeQuery(q);
            while (rs.next()) {
                securityQuestion = "" + rs.getString("securityQ");
                username1 = "" + rs.getString("username");
                password1 = "" + rs.getString("password");
                answer1 = "" + rs.getString("answer");
            }
        } catch (Exception e) {

        }
        System.out.println("" + username);
        System.out.println("" + answer);
        System.out.println("" + password);
        System.out.println("" + username1);
        System.out.println("" + answer1);
        System.out.println("" + password1);
        System.out.println("res : " + (username.equals(username1) && answer.equals(answer1)));
        return username.equals(username1) && answer.equals(answer1);
    }
}
