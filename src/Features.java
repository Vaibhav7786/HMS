import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Driver;
import java.sql.DriverManager;

public class Features extends JFrame implements ActionListener {

    JFrame frame;
    JButton shd, dp, aap, dtp, print_bill, show_details, closeButton;

    public Features() {
        frame = new JFrame();
        getContentPane().setBackground(Color.pink);

        setTitle("FEATURES");
        shd = new JButton("Set Hospital Details");
        shd.setBounds(90, 60, 200, 30);

        dp = new JButton("Patient Under Doctor");
        dp.setBounds(400, 60, 200, 30);

        aap = new JButton("Admit Patient");
        aap.setBounds(90, 130, 200, 30);

        dtp = new JButton("Discharge Patient");
        dtp.setBounds(400, 130, 200, 30);

        print_bill = new JButton("Print Bill");
        print_bill.setBounds(90, 200, 200, 30);
        show_details = new JButton("Show Details");
        show_details.setBounds(400, 200, 200, 30);
        closeButton = new JButton("Close");
        closeButton.setBounds(290, 280, 100, 30);

        add(shd);
        add(dp);
        add(aap);
        add(dtp);
        add(print_bill);
        add(show_details);
        add(closeButton);

        shd.addActionListener(this);
        dp.addActionListener(this);
        aap.addActionListener(this);
        dtp.addActionListener(this);
        print_bill.addActionListener(this);
        show_details.addActionListener(this);
        closeButton.addActionListener(this);

        setLayout(null);
        //setLocationRelativeTo(null); // center frame on screen
        setVisible(true);

//        frame.setSize(700,500);
        setBounds(500, 300, 1100, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            if (e.getSource() == shd) {
                dispose();

                new Manage_Entities().setVisible(true);
            }
            if (e.getSource() == dtp) {
                dispose();
                new PatientDischarge();
                // new Patient_Appoint().setVisible(true);
            }
            if (e.getSource() == aap) {
                dispose();
                new PatientDetails();

            }

            if (e.getSource() == show_details) {
                dispose();
                new Tables().setVisible(true);
            }
            if (e.getSource() == dp) {
                dispose();
                new DischargePatient_Id();
                 
            }
            if (e.getSource() == print_bill) {
                dispose();
                new Generate_Bill();
            }
            if (e.getSource() == closeButton) {
                dispose();
            }

        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Features fobj = new Features();
    }

}
