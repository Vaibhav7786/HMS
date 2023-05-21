import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class PatientDetails extends JFrame implements ActionListener {

    static String Patient_name, Patient_mo_no, Patient_email, ID, P_Admit_Date_Time;
    static int Patient_Id;
    String P_age;
    String Patient_Diago, dd;
//    String Patient_mo_no;
//    String Patient_email;
    String Blood_Group;
    String Allot_Doctor_Name, Allot_Doctor_Spec, Doc_con_name_spe, Allot_Doctor_id, doc_fee;
    String room, gender, Allote_Doc, Dia_Name, Select_diago, Select_diago2;
    JComboBox cb, cb2, cb3, cb4, cb5;

    JLabel jlabel_Patient_ID, jlabel_Aloot_Doc, jlabel_Patient_mf, jlabel_Patient_name, jlabel_Patient_Room,
            jlabel_Patient_age, jlabel_Patient_Email, jlabel_Patient_Digonise, jlabel_Patient_Mo_No,
            jlabel_Patient_Bld_Grp, jl6;
    JButton jb1, Jb2, Allot_doc, Select_diagno, back, jb, menu;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8;
    JFrame frame;
    Object data[][] = new Object[][]{};
    JTable table;
    // JButton jb;

    public PatientDetails() {
        frame = new JFrame();
        setTitle("PatientDetails");
        getContentPane().setBackground(Color.pink);

        // setLayout(null);
        jb = new JButton("Submit");
        jb.setBounds(170, 450, 100, 30);

        back = new JButton("Back");
        back.setBounds(370, 450, 100, 30);

        menu = new JButton("Menu");
        menu.setBounds(570, 30, 70, 40);

        Allot_doc = new JButton("Allot Doctor");
        Allot_doc.setBounds(170, 250, 100, 20);

        Select_diagno = new JButton("Select Diagnosis");
        Select_diagno.setBounds(170, 200, 200, 30);
        add(Select_diagno);

        // Allot_doc = new JButton("Select Diagnosis");
        // Allot_doc.setBounds(170, 250, 100, 20);
        jlabel_Patient_name = new JLabel("Patient Name :");
        jlabel_Patient_name.setBounds(40 
                , 40, 250, 30);

        jlabel_Patient_age = new JLabel("Patient Age :");
        jlabel_Patient_age.setBounds(40, 80, 250, 30);

        jlabel_Patient_Digonise = new JLabel("Patient Diagnosis :");
        jlabel_Patient_Digonise.setBounds(40, 200, 250, 30);

        jlabel_Patient_Mo_No = new JLabel("Patient Mobile No. :");
        jlabel_Patient_Mo_No.setBounds(40, 160, 250, 30);

        jlabel_Patient_Bld_Grp = new JLabel("Patient Email ID :");
        jlabel_Patient_Bld_Grp.setBounds(40, 120, 250, 30);

        // jlabel_Patient_ID = new JLabel("Patient ID :");
        // jlabel_Patient_ID.setBounds(40,240,250, 30);
        jlabel_Patient_Email = new JLabel("Patient Blood Group :");
        jlabel_Patient_Email.setBounds(40, 280, 250, 30);

        jlabel_Patient_Room = new JLabel("Room No. :");
        jlabel_Patient_Room.setBounds(40, 320, 250, 30);

        jlabel_Patient_mf = new JLabel("Gender :");
        jlabel_Patient_mf.setBounds(40, 360, 250, 30);

        jlabel_Aloot_Doc = new JLabel("Allot Doctor:");
        jlabel_Aloot_Doc.setBounds(40, 240, 250, 30);

        tf1 = new JTextField();
        tf1.setBounds(170, 40, 300, 30);

        tf2 = new JTextField();
        tf2.setBounds(170, 80, 300, 30);

        // tf3 = new JTextField();
        // tf3.setBounds(160,120,300, 30);
        tf4 = new JTextField();
        tf4.setBounds(170, 160, 300, 30);

        tf5 = new JTextField();
        tf5.setBounds(170, 120, 300, 30);

        String roomList[] = {"R-1", "R-2", "R-3", "R-4", "R-5", "R-6", "R-7", "R-8",
            "R-9", "R-10", "R-11", "R-12", "R-13", "R-14", "R-15", "R-16", "R-17", "R-18", "R-19", "R-20",
            "R-20", "R-21", "R-22", "R-23", "R-24", "R-25", "R-26", "R-27", "R-28", "R-29", "R-30", "R-31", "R-32", "R-33", "R-34", "R-35", "R-36", "R-37", "R-38", "R-39", "R-40", "R-41", "R-42", "R-43", "R-44", "R-45", "R-45", "R-46", "R-47", "R-48", "R-49", "R-50"};
        cb2 = new JComboBox(roomList);
        cb2.setBounds(170, 330, 80, 20);

        String bloodList[] = {"A+", "B+", "O+", "AB+", "A-", "B -", "O-", "AB-"};
        cb = new JComboBox(bloodList);
        cb.setBounds(170, 290, 50, 20);

        String mf[] = {"Male", "Female", "Other"};
        cb3 = new JComboBox(mf);
        cb3.setBounds(170, 370, 60, 20);

        // String Patient_Digonise[]= {"Sports Injury ","Cancer","ENT Surgery", "Liver
        // Transplant","Paralysist", "Hair Transplant","Tuberculosis(TB)", "Infertility
        // and IVF","Spine Surgery", "Infectious Diseases","NeuroSurgery", "Plastic
        // Surgery","Endocrinology-Diabetology", "Dental Cosmetic"};
        // cb4 = new JComboBox(Patient_Digonise);
        // cb4.setBounds(170,200,140, 30);
        // String Allot_Doc[]= {"Dr.Y K Mishra (SP_Sports Injury)","Dr.Ajay Kaul
        // (SP_Cancer)","Dr.Z S Meharwal (SP_ENT Surgery)","Dr.Bhaba Nanda(SP_Liver
        // Transplant)","Dr.Anoop Kapoor (SP_Paralysist)","Dr.Raju Vyas (SP_Hair
        // Transplant)","Dr.Asha Khanna (SP_Tuberculosis(TB))","Dr.Naveen Saraf
        // (SP_Infertility and IVF)","Dr.Rajiv Bajaj (SP_Spine Surgery)","Dr.Sunil Modi
        // (SP_Infectious Diseases)","Dr.Aparna Jaswal (SP_NeuroSurgery)","Dr.Atul
        // Mathur (SP_Plastic Surgery)","Dr.Vijay
        // Kumar(SP_Endocrinology-Diabetology)","Dr.Ashok Seth (SP_)","Dr.Kailash Gupta
        // (SP_Dental Cosmetic)"};
        // cb5 = new JComboBox(Allot_Doc);
        // cb5.setBounds(170,250,180,20);
        add(jb);
        add(Allot_doc);
        add(cb);
        add(cb2);
        add(cb3);
        // add(cb4);
        // add(cb5);

        add(jlabel_Patient_name);
        add(tf1);
        add(jlabel_Patient_age);
        add(tf2);
        add(jlabel_Patient_Digonise);
        // add(tf3);
        add(jlabel_Patient_Mo_No);
        add(tf4);
        add(jlabel_Patient_Email);
        add(tf5);
        add(jlabel_Patient_Bld_Grp);
        add(jlabel_Patient_Room);
        add(jlabel_Patient_mf);
        add(jlabel_Aloot_Doc);
        add(back);
        add(menu);

        jb.addActionListener(this);
        back.addActionListener(this);
        menu.addActionListener(this);

        Select_diagno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent exaa) {
                try {

                    JFrame f = new JFrame("Table View");
                    //  f.setBounds(320, 150, 700, 500);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    String[] col = new String[]{"Diagnosis_Name"};
                    JPanel panel = new JPanel();
                    panel.setBounds(5, 5, 5, 5);
                    f.setContentPane(panel);
                    panel.setLayout(null);
                    DefaultTableModel model = new DefaultTableModel(data, col);

                    JTable table = new JTable(model);
                    f.getContentPane().setBackground(Color.pink);
                    f.setBounds(320, 150, 1100, 700);
                    f.setResizable(false);
                    table.setBounds(200, 40, 300, 30);
                    JScrollPane jsp = new JScrollPane(table);
                    jsp.setBounds(25, 90, 900, 100);
                    panel.add(jsp);
                    jsp.createVerticalScrollBar();
//                  back = new JButton("Back");
//        back.setBounds(250, 300, 180, 30);
//        f.add(back);

//        menu = new JButton("Menu");
//        menu.setBounds(1000, 30, 80, 30);
//        f.add(menu);
//        
                    JButton jb = new JButton("Select Diagnosis");
                    jb.setBounds(40, 300, 180, 30);
                    f.add(jb);
                    // JButton jb2 = new JButton ("Update Row");
                    // jb2.setBounds(150,300,120,30);
                    // f.add(jb2);
                    //
                    //  f.add(new JScrollPane(table));
//                        back.addActionListener(new ActionListener(){
//                        @Override
//                                                public void actionPerformed(ActionEvent e) {
//                                                    if(e.getSource()==back){
//                                                       // dispose();
//                                                     new PatientDetails();
//                                                    }
//                                                }
//                        });

//                        menu.addActionListener(new ActionListener(){
//                        @Override
//                                                public void actionPerformed(ActionEvent e) {
//                                                    if(e.getSource()==menu){
//                                                        dispose();
//                                                     new Features();
//                                                    }
//                                                }
//                        });
                    jb.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (table.getSelectedRow() == -1) {
                                JOptionPane.showMessageDialog(null, "Please Select Row ", "error",
                                        JOptionPane.ERROR_MESSAGE);
                            }

                            int status = JOptionPane.showConfirmDialog(null, "Do You Want to Select this Row",
                                    "Confirm", JOptionPane.YES_NO_OPTION);
                            if (status == JOptionPane.YES_OPTION) {
                                // model.removeRow(table.getSelectedRow());
                                try {
                                    DefaultTableModel tbl = (DefaultTableModel) table.getModel();
                                    Connection con = ConnectionOneTime.getConnect();
                                    if (table.getSelectedRowCount() == 1) {
                                        Select_diago = tbl.getValueAt(table.getSelectedRow(), 0).toString();
                                        Select_diago2 = Select_diago;
                                        //  System.out.println("" + Select_diago);

                                        JOptionPane.showMessageDialog(null, "Diagnosis selected Successfully");

                                    }

                                } catch (Exception ex) {

                                }

                            }
                            // throw new UnsupportedOperationException("Not supported yet."); //To change
                            // body of generated methods, choose Tools | Templates.
                        }
                    });

                    setLayout(null);
                    f.setVisible(true);

                    Connection con = ConnectionOneTime.getConnect();

                    Statement st = con.createStatement();
                    String q = "select * from diagnosis_myhms";
                    ResultSet rs = st.executeQuery(q);
                    String Diagno_Name = "";
                    while (rs.next()) {
                        Diagno_Name = "" + rs.getString("Diagnosis_Name");
                        String array[] = {Diagno_Name};
                        DefaultTableModel m = (DefaultTableModel) table.getModel();
                        m.addRow(array);
                    }
                } catch (Exception eaa) {
                    System.out.println("" + eaa);

                }
            }
        });

        Allot_doc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Select_diago2 = Select_diago;

                    // Patient_Diago = "" + cb4.getSelectedItem();
                    // System.out.println(""+Patient_Diago);
                    JFrame f = new JFrame("Table View");
//                    f.setBounds(320, 150, 700, 500);
                    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    String[] col = new String[]{"Doctor_ID", "Doctor_Name", "Doctor_Age", "Doctor_Mobile_No",
                        "Doctor_Qualification", "Doctor_Email_ID", "Doctor_Gender", "Doctor_Specialisation", "D_Fees"};
                    JPanel panel = new JPanel();
                    panel.setBounds(5, 5, 5, 5);
                    f.setContentPane(panel);
                    panel.setLayout(null);
                    DefaultTableModel model = new DefaultTableModel(data, col);
                    table = new JTable(model);
                    f.getContentPane().setBackground(Color.pink);
                    f.setBounds(320, 150, 1100, 700);
                    f.setResizable(false);
                    table.setBounds(200, 40, 350, 30);
                    JScrollPane jsp = new JScrollPane(table);
                    jsp.setBounds(25, 90, 900, 100);
                    panel.add(jsp);
                    jsp.createVerticalScrollBar();
                    JButton jb = new JButton("Allot Doctor");
                    jb.setBounds(40, 300, 100, 30);
                    f.add(jb);

                    jb.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (table.getSelectedRow() == -1) {
                                JOptionPane.showMessageDialog(null, "Please Select Row ", "error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            int status = JOptionPane.showConfirmDialog(null, "Do You Want to Allot This Doctor",
                                    "Confirm",
                                    JOptionPane.YES_NO_OPTION);
                            // System.out.println(""+status);
                            if (status == JOptionPane.YES_OPTION) {
                                try {
                                    DefaultTableModel tbl = (DefaultTableModel) table.getModel();

                                    Connection con = ConnectionOneTime.getConnect();
                                    DefaultTableModel tbl2 = (DefaultTableModel) table.getModel();
                                    if (table.getSelectedRowCount() == 1) {
                                        Allot_Doctor_Name = tbl.getValueAt(table.getSelectedRow(), 1).toString();
                                        Allot_Doctor_id = tbl.getValueAt(table.getSelectedRow(), 0).toString();
                                        doc_fee = tbl.getValueAt(table.getSelectedRow(), 8).toString();
                                        System.out.println("" + doc_fee);
                                        System.out.println("" + doc_fee);

//                                        Allot_Doctor_Spec = tbl.getValueAt(table.getSelectedRow(), 7).toString();
//                                        Doc_con_name_spe = Allot_Doctor_Name + " " + Allot_Doctor_Spec;
//                                        System.out.println("" + Doc_con_name_spe);
                                        JOptionPane.showMessageDialog(null, "Doctor Alloted Successfully");
                                    }

                                } catch (Exception ex) {

                                }
                            }
                        }
                    });

                    //  f.add(new JScrollPane(table));
                    setLayout(null);
                    f.setVisible(true);

                    Connection con = ConnectionOneTime.getConnect();
                    Statement st = con.createStatement();

                    String q = "select * from doctor_my_hms where   Doctor_Specialisation = '" + Select_diago2 + "'";
                    ResultSet rs = st.executeQuery(q);
                    String D_ID = "";
                    String D_Name = "";
                    String D_Mobile_No = "";
                    String D_Age = "";
                    String D_Quali = "";
                    String D_Email = "";
                    String D_Gender = "";
                    String D_Specialisation = "";
                    String D_Fees = "";

                    while (rs.next()) {

                        D_ID = "" + rs.getInt(1);
                        D_Name = "" + rs.getString("D_Name");
                        D_Mobile_No = "" + rs.getString("D_mobile_number");
                        D_Age = "" + rs.getInt(3);
                        D_Quali = "" + rs.getString("D_Qualification");
                        D_Email = "" + rs.getString("D_email_id");
                        D_Gender = "" + rs.getString("Doctor_Gender");
                        D_Specialisation = "" + rs.getString("Doctor_Specialisation");
                        D_Fees = "" + rs.getString("Dr_Fees");
                        String array[] = {D_ID, D_Name, D_Age, D_Mobile_No, D_Quali, D_Email, D_Gender,
                            D_Specialisation, D_Fees};
                        DefaultTableModel m = (DefaultTableModel) table.getModel();
                        m.addRow(array);
                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    }
                } catch (Exception ex) {

                }
            }

        });

        setLayout(null);
        setVisible(true);
        setBounds(200, 50, 1100, 700);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == menu) {
            dispose();
            new Features();
        }
        if (e.getSource() == back) {
            dispose();
            new Features();

        }
        if (e.getSource() == jb) {
            if (!(SetValidation.IsValidEmail(tf5.getText())) && (SetValidation.isValidName(tf1.getText()))
                    && (SetValidation.isValidContactNumber(tf4.getText())) && (SetValidation.isValidAge(tf2.getText()))) {

                try {

                    Connection con = ConnectionOneTime.getConnect();

                    Patient_name = tf1.getText();
                    P_age = tf2.getText();
                    // Patient_Diago=""+cb4.getSelectedItem();
                    Patient_mo_no = tf4.getText();
                    Patient_email = tf5.getText();
                    Blood_Group = "" + cb.getSelectedItem();
                    room = "" + cb2.getSelectedItem();
                    gender = "" + cb3.getSelectedItem();
                    // Allote_Doc=""+cb5.getSelectedItem();

                    PreparedStatement st = con.prepareStatement(
                            "INSERT INTO patient_myhms(P_Name,P_Age,P_Diagnosis,P_mobile_No,P_Email,P_Blood_Group,P_Room_No,Gender,Allot_Doc,P_Admit_Date_Time,D_ID,Dr_Fees) values(?,?,?,?,?,?,?,?,?,?,?,?)");
                    st.setString(1, Patient_name);
                    st.setString(2, P_age);
                    st.setString(3, Select_diago2);
                    st.setString(4, Patient_mo_no);
                    st.setString(5, Patient_email);
                    st.setString(6, Blood_Group);
                    st.setString(7, room);
                    st.setString(8, gender);
                    st.setString(9, Allot_Doctor_Name);
                    st.setString(11, Allot_Doctor_id);
                    st.setString(12, doc_fee);

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//HH:mm:ss
                    Date date = new Date();
                    String t = formatter.format(date);

                    st.setString(10, t);

                    if (st.execute() == false) {
                        System.out.println(doc_fee + "////" + Allot_Doctor_Name);
                        Connection con1 = ConnectionOneTime.getConnect();
                        Statement sql = con1.createStatement();
                        String q = "select P_ID from patient_myhms where P_Email ='" + Patient_email + "';";
                        ResultSet rs = st.executeQuery(q);
                        while (rs.next()) {
                            Patient_Id = rs.getInt(1);

                        }

                        String zx = "select P_Admit_Date_Time from patient_myhms where P_Email ='" + Patient_email + "';";
                        ResultSet rs2 = st.executeQuery(zx);
                        while (rs2.next()) {
                            P_Admit_Date_Time = "" + rs2.getString(1);
                        }
                        System.out.println("" + P_Admit_Date_Time);

                        String to = Patient_email;
                        String from = "myvaibhav.sonar@gmail.com";
                        String subject = "Sending Email using Gmail";
                        String text = "This is An Example of Sending Email Using Gmail ";
                        boolean back = Email_sending.sendemail(to, from, subject, text);
                        if (back) {
                            System.out.println("Email is Sent Successfully");
                        } else {
                            System.out.println("Some Issue While Sending An Email ");
                        }
                        JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
                        Give_Medicine obj = new Give_Medicine(Patient_name);
                    } else {
                        JOptionPane.showMessageDialog(null, "Something Wrong");

                    }
                    st.close();
                    con.close();
                } catch (Exception ex) {
                    System.out.println("Exception : " + ex);
                    ex.printStackTrace();

                }
            } else {
                dispose();

                JOptionPane.showMessageDialog(frame, "Something Went Wrong\nRe-Enter the Details");
                new PatientDetails();
            }
        }

    }

    public static void main(String[] args) {

        PatientDetails pd = new PatientDetails();

    }

}
