import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.*;

public class Discharged_Patient extends JFrame implements ActionListener {

    String Patient_name;
    String P_age;
    String Patient_Diago;
    String Patient_mo_no;
    String Patient_email;
    String Blood_Group;
    String ID;
    String room;
    JComboBox cb, cb2;

    JLabel jlabel_Patient_ID, jlabel_Patient_name, jlabel_Patient_Room, jlabel_Patient_age, jlabel_Patient_Email, jlabel_Patient_Digonise, jlabel_Patient_Mo_No, jlabel_Patient_Bld_Grp, jl6;
    JButton jb1, Jb2;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7;
    JFrame frame;

    public Discharged_Patient() {
        frame = new JFrame();
        setTitle("Discharge_Patient_Rec");

        // setLayout(null);
        JButton jb = new JButton("Submit");
        jb.setBounds(160, 370, 100, 30);

        jlabel_Patient_name = new JLabel("Patient Name :");
        jlabel_Patient_name.setBounds(40, 40, 250, 30);

        jlabel_Patient_age = new JLabel("Patient Age :");
        jlabel_Patient_age.setBounds(40, 80, 250, 30);

        jlabel_Patient_Digonise = new JLabel("Patient Diagnosis :");
        jlabel_Patient_Digonise.setBounds(40, 120, 250, 30);

        jlabel_Patient_Mo_No = new JLabel("Patient Mobile No. :");
        jlabel_Patient_Mo_No.setBounds(40, 160, 250, 30);

        jlabel_Patient_Bld_Grp = new JLabel("Patient Email ID :");
        jlabel_Patient_Bld_Grp.setBounds(40, 200, 250, 30);

//      jlabel_Patient_ID = new JLabel("Patient ID :");
//      jlabel_Patient_ID.setBounds(40,240,250, 30);
        jlabel_Patient_Email = new JLabel("Patient Blood Group :");
        jlabel_Patient_Email.setBounds(40, 280, 250, 30);

        jlabel_Patient_Room = new JLabel("Room No. :");
        jlabel_Patient_Room.setBounds(40, 320, 250, 30);

        tf1 = new JTextField();
        tf1.setBounds(160, 40, 300, 30);

        tf2 = new JTextField();
        tf2.setBounds(160, 80, 300, 30);

        tf3 = new JTextField();
        tf3.setBounds(160, 120, 300, 30);

        tf4 = new JTextField();
        tf4.setBounds(160, 160, 300, 30);

        tf5 = new JTextField();
        tf5.setBounds(160, 200, 300, 30);

//       tf7 = new JTextField();
//     tf7.setBounds(160,280,300, 30);
        String bloodList[] = {"A+", "B+", "AB+", "O+", "A-", "B-", "AB-", "O-"};
        cb = new JComboBox(bloodList);
        cb.setBounds(170, 290, 50, 20);

        String roomList[] = {"AC_R_01", "AC_R_02", "AC_R_03", "AC_R_04", "AC_R_05", "AC_R_06", "AC_R_07", "AC_R_07", "AC_R_08", "AC_R_09", "AC_R_09", "AC_R_10"};
        cb2 = new JComboBox(roomList);
        cb2.setBounds(170, 330, 50, 20);

        add(jb);
        add(cb);
        add(cb2);

        add(jlabel_Patient_name);
        add(tf1);
        add(jlabel_Patient_age);
        add(tf2);
        add(jlabel_Patient_Digonise);
        add(tf3);
        add(jlabel_Patient_Mo_No);
        add(tf4);
        add(jlabel_Patient_Email);
        add(tf5);
        add(jlabel_Patient_Bld_Grp);
        add(jlabel_Patient_Room);

        jb.addActionListener(this);
        setLayout(null);
        setVisible(true);
        setBounds(100, 100, 700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if (!(SetValidation.IsValidEmail(tf5.getText())) && (SetValidation.isValidName(tf1.getText())) && (SetValidation.isValidContactNumber(tf4.getText())) && (SetValidation.isValidAge(tf2.getText()))) {

            try {
              // Class.forName("com.mysql.jdbc.Driver");
                // Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myhms","root","root");
                Connection con = ConnectionOneTime.getConnect();

                Patient_name = tf1.getText();
                P_age = tf2.getText();
                Patient_Diago = tf3.getText();
                Patient_mo_no = tf4.getText();
                Patient_email = tf5.getText();
                Blood_Group = "" + cb.getSelectedItem();
                room = "" + cb2.getSelectedItem();

                PreparedStatement st = con.prepareStatement("INSERT INTO patient_myhms(P_Name,P_Age,P_Diagnosis,P_mobile_No,P_Email,P_Blood_Group,P_Room_No) values(?,?,?,?,?,?,?)");
                st.setString(1, Patient_name);
                st.setString(2, P_age);
                st.setString(3, Patient_Diago);
                st.setString(4, Patient_mo_no);
                st.setString(5, Patient_email);
                st.setString(6, Blood_Group);
                st.setString(7, room);

                st.execute();

                JFrame frame1 = new JFrame();
                // jl6.setBounds(10, 10, 20, 20);
                jl6 = new JLabel("Data Insert Successfully ");
                frame1.add(jl6);
                frame1.pack();
                frame1.setLocationRelativeTo(null); // center frame2 on screen
                frame1.setVisible(true);
                frame.dispose();
                /// new Admin_Panel().setVisible(true);

                st.close();
                con.close();
            } catch (Exception ex) {
                System.out.println("Exception : " + ex);
                ex.printStackTrace();

            }

        } else {
            //   dispose();
            JOptionPane.showMessageDialog(frame, "Something Went Wrong\nRe-Enter the Details");
        }

    }

    public static void main(String[] args) {

        Discharged_Patient discharge = new Discharged_Patient();

    }

}
