import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Driver;
import java.sql.DriverManager;

public class Manage_Entities extends JFrame implements ActionListener {

    JFrame frame;
    JButton md, mm, shbc, manage_Dia, back, menu;

    public Manage_Entities() {
        frame = new JFrame();
        getContentPane().setBackground(Color.pink);

        setTitle("MANAGE_ENTITIES");

        shbc = new JButton("Set Hospital Basic Details");
        shbc.setBounds(30, 130, 200, 30);

        md = new JButton("Manage Doctor");
        md.setBounds(320, 130, 200, 30);

        mm = new JButton("Manage Medicine");
        mm.setBounds(30, 200, 200, 30);

        back = new JButton("Back");
        back.setBounds(240, 280, 70, 30);

        manage_Dia = new JButton("Manage Diagnosis");
        manage_Dia.setBounds(320, 200, 200, 30);

        menu = new JButton("Menu");
        menu.setBounds(530, 30, 70, 40);

        add(md);
        add(mm);
        add(shbc);
        add(manage_Dia);
        add(back);
        add(menu);

        md.addActionListener(this);
        mm.addActionListener(this);
        shbc.addActionListener(this);
        manage_Dia.addActionListener(this);
        back.addActionListener(this);
        menu.addActionListener(this);

        setLayout(null);
        setVisible(true);
        setBounds(500, 300, 1100, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            if (e.getSource() == md) {
                dispose();
                new Doctor_Menu().setVisible(true);
            }
            if (e.getSource() == mm) {
                dispose();
                new Medicine_Menu().setVisible(true);
            }
            if (e.getSource() == shbc) {
                dispose();
                new Hospital_Details().setVisible(true);
            }
            if (e.getSource() == manage_Dia) {
                new Diagnosis_Menu().setVisible(true);
            }

            if (e.getSource() == back) {
                new Features();
                //  new Medicine().setVisible(true);
            }
            if (e.getSource() == menu) {
                new Features();
                //  new Medicine().setVisible(true);
            }
////        
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Manage_Entities me = new Manage_Entities();
    }
}
