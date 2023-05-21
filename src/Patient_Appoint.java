//import java.awt.event.ActionEvent;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//
//import java.awt.event.ActionListener;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//import java.sql.*;
//import java.sql.SQLException;
//import java.sql.Driver;
//import java.sql.DriverManager;
//
//public class Patient_Appoint extends JFrame implements ActionListener {
//
//    JLabel jlabel_admin_panel;
//    JButton ep, ap, back, back2, menu;
//    JTextField tf1, tf_for_id;
//    JFrame frame, frame2;
//
//    public Patient_Appoint() {
//        frame = new JFrame();
//        getContentPane().setBackground(Color.pink);
//
//        setTitle("Patient_Appoint");
//        ep = new JButton("Existing Patient");
//        ep.setBounds(40, 120, 150, 30);
//
//        ap = new JButton("Add Patient");
//        ap.setBounds(300, 120, 120, 30);
//
//        back = new JButton("Back");
//        back.setBounds(190, 200, 100, 30);
//
//        menu = new JButton("Menu");
//        menu.setBounds(530, 30, 70, 40);
//
//        add(ep);
//
//        add(ap);
//        add(back);
//        add(menu);
//
//        ep.addActionListener(this);
//        ap.addActionListener(this);
//        back.addActionListener(this);
//        menu.addActionListener(this);
//
//        setLayout(null);
//        setVisible(true);
//        setBounds(500, 300, 1100, 700);
//        setResizable(false);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        try {
//            if (e.getSource() == menu) {
//                new Features();
//            }
//            if (e.getSource() == back) {
//                dispose();
//                new Features().setVisible(true);
//            }
//            if (e.getSource() == ep) {
//                dispose();
//                // getContentPane().setBackground(Color.pink);
//
//                frame2 = new JFrame();
//                frame2.getContentPane().setBackground(Color.pink);
//
//                frame2.setTitle("PATIENT_ID");
//                ep = new JButton("Submit");
//                ep.setBounds(150, 200, 100, 30);
//
//                back2 = new JButton("Back");
//                back2.setBounds(300, 200, 100, 30);
//
//                menu = new JButton("Menu");
//                menu.setBounds(530, 30, 70, 40);
//
//                JLabel patient_id = new JLabel("Patient ID :");
//                patient_id.setBounds(60, 100, 100, 30);
//                tf_for_id = new JTextField();
//                tf_for_id.setBounds(150, 100, 150, 30);
//                frame2.add(ep);
//                frame2.add(back2);
//                frame2.add(menu);
//                frame2.add(patient_id);
//                frame2.add(tf_for_id);
//                ep.addActionListener(this);
//                menu.addActionListener(new ActionListener() {
//
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource() == menu) {
//                            new Features();
//                        }
//                    }
//                });
//                back2.addActionListener(new ActionListener() {
//
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource() == back2) {
//                            new Patient_Appoint();
//                        }
//                        if (e.getSource() == ep) {
//
//                            new Patient_Appoint();
//                        }
//
//                    } // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//
//                });
//                frame2.setLayout(null);
//                frame2.setVisible(true);
//                frame2.setBounds(500, 300, 1100, 700);
//                frame2.setResizable(false);
//                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//                //   new DoctorDetails().setVisible(true);
//            }
//            if (e.getSource() == ap) {
//                dispose();
//                new PatientDetails().setVisible(true);
//            }
//
//        } catch (Exception ex) {
//            System.out.println("Exception : " + ex);
//        }
//    }
//
//    public static void main(String[] args) {
//        Patient_Appoint apobj = new Patient_Appoint();
//    }
//
//}
