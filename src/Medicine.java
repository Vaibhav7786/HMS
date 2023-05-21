import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import java.awt.event.*;

import java.sql.*;
import java.sql.SQLException;
import java.sql.Driver;
import java.sql.DriverManager;
import javax.swing.JScrollPane;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Medicine extends JFrame implements ActionListener {

    JLabel jlabel_med_name, jlabel_med_mg, jlabel_doc_id, jlabel_doc_mo_no, jlabel_med_Quan, jlabel_med_Price, jl5;
    JButton jb1, Jb2, back, menu;
    JTextField tf1, tf2, tf3, tf4, tf5, MName, Med_mg;
    JFrame frame;
    JComboBox bx;
    Vector v;

    public Medicine() {
        frame = new JFrame();
        getContentPane().setBackground(Color.pink);

        setTitle("MEDICINE_DETAILS");
        JButton mb = new JButton("Submit");
        mb.setBounds(150, 300, 100, 30);

        back = new JButton("Back");
        back.setBounds(350, 300, 100, 30);

        menu = new JButton("Menu");
        menu.setBounds(530, 30, 70, 40);

        jlabel_med_name = new JLabel("Medicine Name");
        jlabel_med_name.setBounds(40, 80, 250, 30);

        jlabel_med_mg = new JLabel("Medicine mg");
        jlabel_med_mg.setBounds(40, 120, 250, 30);

        jlabel_med_Quan = new JLabel("Medicine Quantity");
        jlabel_med_Quan.setBounds(40, 160, 250, 30);

        jlabel_med_Price = new JLabel("Medicine Price");
        jlabel_med_Price.setBounds(40, 200, 250, 30);

        tf1 = new JTextField();
        tf1.setBounds(150, 80, 300, 30);

        tf2 = new JTextField();
        tf2.setBounds(150, 120, 300, 30);

        tf3 = new JTextField();
        tf3.setBounds(150, 160, 300, 30);
        tf4 = new JTextField();
        tf4.setBounds(150, 200, 300, 30);

       //add(table);
        //add(columnNames);
        add(mb);
        add(tf1);
        add(jlabel_med_name);
        add(tf2);
        add(jlabel_med_mg);

        add(tf3);
        add(jlabel_med_Quan);

        add(tf4);
        add(jlabel_med_Price);
        add(back);
        add(menu);

        mb.addActionListener(this);
        back.addActionListener(this);
        menu.addActionListener(this);

        setLayout(null);
        setVisible(true);
        //table.setSize(400,400);
        setBounds(500, 300, 1100, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            if (e.getSource() == menu) {
                dispose();
                new Features();
            } else if (e.getSource() == back) {
                dispose();
                new Medicine_Menu().setVisible(true);
            } else {
                Connection con = ConnectionOneTime.getConnect();
                PreparedStatement st = con.prepareStatement("INSERT INTO medicine_myhms values(?,?,?,?)");
                st.setString(1, tf1.getText());
                st.setString(2, tf2.getText());
                st.setString(3, tf3.getText());
                st.setString(4, tf4.getText());
                st.executeUpdate();
                JFrame frame1 = new JFrame();
                jl5 = new JLabel("Data Insert Successfully");
                frame1.add(jl5);
                frame1.pack();
                frame1.setLocationRelativeTo(null); // center frame2 on screen
                frame1.setVisible(true);
                frame.dispose();
                new Medicine_Menu().setVisible(true);

                    //for fetching data on terminal      screen code 
//                    Statement st2 = con.createStatement();
//                    ResultSet rs = st2.executeQuery("select * from Medicine");
//                    while (rs.next()) {
//                      
//                               System.out.println(rs.getString(1)+"\t"+rs.getInt(2));
//                          }
                st.close();
                con.close();
            }
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Medicine mobj = new Medicine();

    }

}
