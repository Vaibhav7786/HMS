import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Driver;
import java.sql.DriverManager;

public class Hospital_Details extends JFrame implements ActionListener {

    JLabel jlabel_doc_name, jlabel_doc_age, jlabel_doc_id, jlabel_doc_mo_no, jl5;
    JButton jb1, Jb2;
    JTextField tf1, tf2, tf3, tf4, tf5;
    JFrame frame;
    JButton hn, ht, hl, back, menu;

    public Hospital_Details() {
        frame = new JFrame();
        getContentPane().setBackground(Color.pink);

        setTitle("HOSPITAL_DETAILS");
        hn = new JButton("Submit");
        hn.setBounds(150, 250, 100, 30);

        back = new JButton("Back");
        back.setBounds(350, 250, 100, 30);

        menu = new JButton("Menu");
        menu.setBounds(530, 30, 70, 40);

        jlabel_doc_name = new JLabel("Hospital Name");
        jlabel_doc_name.setBounds(40, 80, 250, 30);

        jlabel_doc_age = new JLabel("Hospital Time");
        jlabel_doc_age.setBounds(40, 120, 250, 30);

        jlabel_doc_id = new JLabel("Hospital Location");
        jlabel_doc_id.setBounds(40, 160, 250, 30);

//      jlabel_doc_mo_no = new JLabel("Doctor Mobile No.");
//      jlabel_doc_mo_no.setBounds(40,160,250, 30);
//      
        tf1 = new JTextField();
        tf1.setBounds(150, 80, 300, 30);

        tf2 = new JTextField();
        tf2.setBounds(150, 120, 300, 30);

        tf3 = new JTextField();
        tf3.setBounds(150, 160, 300, 30);

        add(jlabel_doc_name);

        add(tf1);
        add(jlabel_doc_age);

        add(tf2);
        add(jlabel_doc_id);

        add(tf3);
        add(hn);
        add(back);
        add(menu);

        hn.addActionListener(this);
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
            if (e.getSource() == back) {
                dispose();
                new Manage_Entities().setVisible(true);
            }
            if (e.getSource() == menu) {
                dispose();
                new Features().setVisible(true);
            }
            if (e.getSource() == hn) {
                Connection con = ConnectionOneTime.getConnect();
                PreparedStatement st = con.prepareStatement("INSERT INTO hospital_details Values (?,?,?)");
                st.setString(1, tf1.getText());
                st.setString(2, tf2.getText());
                st.setString(3, tf3.getText());

                frame.dispose();

                if (st.executeUpdate() == 1) {
                    JOptionPane.showMessageDialog(null, "Hospital Name Set Successfully");

                } else {
                    JOptionPane.showMessageDialog(null, "Somthing Wrong Re-Enter Again");
                    //new Hospital_Details();

                }

                new Manage_Entities().setVisible(true);
            }

            //  Statement st2 = con.createStatement();
//                    ResultSet rs = st2.executeQuery("select * from Medicine");
//                  while (rs.next()) {
//                             System.out.println(rs.getString(1)+"\t"+rs.getInt(2));
//                        }
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Hospital_Details dd = new Hospital_Details();
    }

}
