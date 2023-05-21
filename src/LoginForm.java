import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm {

    String username1;
    String password1;
    String username;
    String password;
    JFrame frame1;

    public LoginForm() {
        frame1 = new JFrame("ADMIN PANEL");
        frame1.setBounds(500, 300, 1100, 700);
        frame1.setResizable(true);
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);

        JLabel welcome = new JLabel("  WELCOME ADMIN ");

        frame1.getContentPane().setBackground(Color.pink);
        // ImageIcon image = new ImageIcon("adminlogo.png");
        // frame1.setIconImage(image.getImage());

        JLabel usernameLabel = new JLabel("Username :");
        JTextField name = new JTextField();

        JLabel passwordLabel = new JLabel("Password :");
        JPasswordField pass = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton forgetButton = new JButton("Forget Password");
        //JButton FGButton = new JButton("Forgot Password");
        //setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                username = name.getText();

                password = new String(pass.getPassword());

                if (new LoginForm().isValidLogin(username, password)) {
                    //frame1.dispose(); // dispose of frame1
                    JOptionPane.showMessageDialog(null, "Password Login Successfully");
                    frame1.dispose();
                    new Features().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame1, "Invalid Username or Password. Please Try Again.");

                }
            }
        });

        forgetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame1.dispose();
                new Reset_password();
            }
        });
// Set the layout manager of the content pane to null
        Container c = frame1.getContentPane();
        c.setLayout(null);

        // Set the bounds of each component using absolute positioning
        welcome.setBounds(100, 50, 150, 20);
        usernameLabel.setBounds(20, 100, 80, 20);
        name.setBounds(100, 100, 150, 20);
        passwordLabel.setBounds(20, 150, 80, 20);
        pass.setBounds(100, 150, 150, 20);
        loginButton.setBounds(50, 200, 80, 20);
        forgetButton.setBounds(160, 200, 80, 20);
        //FGButton.setBounds(280, 80, 80, 20);
        // Add the components to the content pane
        c.add(usernameLabel);
        c.add(name);
        c.add(passwordLabel);
        c.add(pass);
        c.add(loginButton);
        c.add(forgetButton);
        c.add(welcome);
        // c.add(FGButton);
        // closeButton.addActionListener(this);

//        frame1.setSize(300, 150);
        frame1.setLocationRelativeTo(null); // center frame1 on screen
        frame1.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
//        JFrame frame1 = new JFrame("ADMIN PANEL");
//        frame1.setBounds(500, 400, 310, 300);
//        frame1.setResizable(true);
//        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
//
//        JLabel welcome = new JLabel("  WELCOME ADMIN ");
//
//        frame1.getContentPane().setBackground(Color.pink);
//        // ImageIcon image = new ImageIcon("adminlogo.png");
//        // frame1.setIconImage(image.getImage());
//
//        JLabel usernameLabel = new JLabel("Username :");
//        JTextField name = new JTextField();
//
//        JLabel passwordLabel = new JLabel("Password :");
//        JPasswordField pass = new JPasswordField();
//
//        JButton loginButton = new JButton("Login");
//        JButton forgetButton = new JButton("Forget Password");
//        //JButton FGButton = new JButton("Forgot Password");
//        //setVisible(true);
//
//        loginButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                username = name.getText();
//
//                password = new String(pass.getPassword());
//
//                if (new LoginForm().isValidLogin(username, password)) {
//                    frame1.dispose(); // dispose of frame1
//                    JOptionPane.showMessageDialog(frame1, "Password Login Successfully");
//                    new Features().setVisible(true);
//                } else {
//                    JOptionPane.showMessageDialog(frame1, "Invalid Username or Password. Please Try Again.");
//                    
//                }
//            }
//        });
//
//        forgetButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                frame1.dispose();
//                new Reset_password();
//            }
//        });
//// Set the layout manager of the content pane to null
//        Container c = frame1.getContentPane();
//        c.setLayout(null);
//
//        // Set the bounds of each component using absolute positioning
//        welcome.setBounds(100, 50, 150, 20);
//        usernameLabel.setBounds(20, 100, 80, 20);
//        name.setBounds(100, 100, 150, 20);
//        passwordLabel.setBounds(20, 150, 80, 20);
//        pass.setBounds(100, 150, 150, 20);
//        loginButton.setBounds(50, 200, 80, 20);
//        forgetButton.setBounds(160, 200, 80, 20);
//        //FGButton.setBounds(280, 80, 80, 20);
//        // Add the components to the content pane
//        c.add(usernameLabel);
//        c.add(name);
//        c.add(passwordLabel);
//        c.add(pass);
//        c.add(loginButton);
//        c.add(forgetButton);
//        c.add(welcome);
//        // c.add(FGButton);
//        // closeButton.addActionListener(this);
//
////        frame1.setSize(300, 150);
//        frame1.setLocationRelativeTo(null); // center frame1 on screen
//        frame1.setVisible(true);
    }

    private boolean isValidLogin(String username, String password) {
//        String username1="";
        try {
            Connection con = ConnectionOneTime.getConnect();
            Statement st = con.createStatement();
            String q = "select * from UserDetails";
            ResultSet rs = st.executeQuery(q);
            while (rs.next()) {
                username1 = "" + rs.getString("username");
                password1 = "" + rs.getString("password");
            }
        } catch (Exception e) {

        }
        return username.equals(username1) && password.equals(password1);
    }

}
