import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Driver;
import java.sql.DriverManager;
import javax.swing.table.DefaultTableModel;

public class DoctorDetails extends JFrame implements ActionListener {

    JLabel jlabel_doc_name, jlabel_doc_Specialisation, jlabel_doc__Gender, jlabel_doc_age, jlabel_doc_id, jlabel_doc_mo_no, jlabel_doc__Quali, jlabel_doc__Email, jlabel_doc_Fees, jl5;
    JButton jb1, Jb2, back, menu, ad, dd, see_table;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7;
    JFrame frame;
    JComboBox cb, cb2;
    JTable table;
    JButton doc_Specialisation;
    JButton submit;
    Object data[][] = new Object[][]{};
    String Dia_Name, Select_diago, Select_diago2;

    public DoctorDetails() {
        frame = new JFrame();
        getContentPane().setBackground(Color.pink);

        setTitle("DOCTOR_INFORMATION_");
        submit = new JButton("Submit");
        submit.setBounds(170, 400, 100, 30);

        back = new JButton("Back");
        back.setBounds(290, 400, 100, 30);

        menu = new JButton("Menu");
        menu.setBounds(550, 30, 70, 40);

//      JButton Main_Menu = new JButton("Menu");
//      jb.setBounds(170, 10, 100, 30);
        jlabel_doc_name = new JLabel("Doctor Name :");
        jlabel_doc_name.setBounds(40, 40, 250, 30);

        jlabel_doc_age = new JLabel("Doctor Age :");
        jlabel_doc_age.setBounds(40, 80, 250, 30);

        jlabel_doc_mo_no = new JLabel("Doctor Mobile No. :");
        jlabel_doc_mo_no.setBounds(40, 120, 250, 30);

        jlabel_doc__Quali = new JLabel("Doctor Qualification :");
        jlabel_doc__Quali.setBounds(40, 160, 250, 30);

        jlabel_doc__Email = new JLabel("Doctor Email ID :");
        jlabel_doc__Email.setBounds(40, 200, 250, 30);

        jlabel_doc__Gender = new JLabel("Doctor Gender :");
        jlabel_doc__Gender.setBounds(40, 240, 250, 30);

        jlabel_doc_Specialisation = new JLabel("Doctor Specialization :");
        jlabel_doc_Specialisation.setBounds(40, 280, 250, 30);

        jlabel_doc_Fees = new JLabel("Doctor Fees :");
        jlabel_doc_Fees.setBounds(40, 320, 250, 30);

    //  String doc_Specialisation[]= {"Sports Injury ","Cancer","ENT Surgery", "Liver Transplant","Paralysis", "Hair Transplant","Tuberculosis(TB)", "Infertility and IVF","Spine Surgery", "Infectious Diseases","NeuroSurgery", "Plastic Surgery","Endocrinology-Diabetology", "Dental Cosmetic"   };
        //  cb2 = new JComboBox(doc_Specialisation);
        //  cb2.setBounds(170,290,140,20);
        doc_Specialisation = new JButton("Specialisation ");
        doc_Specialisation.setBounds(170, 290, 140, 20);
      //add(doc_Specialisation);

        String mf[] = {"Male", "Female", "Other"};
        cb = new JComboBox(mf);
        cb.setBounds(170, 250, 60, 20);

        tf1 = new JTextField();
        tf1.setBounds(170, 40, 300, 30);

        tf2 = new JTextField();
        tf2.setBounds(170, 80, 300, 30);

        tf4 = new JTextField();
        tf4.setBounds(170, 120, 300, 30);

        tf5 = new JTextField();
        tf5.setBounds(170, 160, 300, 30);
        tf6 = new JTextField();
        tf6.setBounds(170, 200, 300, 30);

        tf7 = new JTextField();
        tf7.setBounds(170, 320, 140, 20);
        add(submit);
        //add(Main_Menu);
        add(cb);
        // add(cb2);
        add(doc_Specialisation);

        add(jlabel_doc_name);
        add(tf1);

        add(jlabel_doc_age);
        add(tf2);

        add(jlabel_doc_mo_no);
        add(tf4);

        add(jlabel_doc__Quali);
        add(tf5);

        add(jlabel_doc__Email);
        add(tf6);
        add(jlabel_doc_Specialisation);
        add(jlabel_doc__Gender);
        add(jlabel_doc_Fees);
        add(tf7);
        add(back);
        add(menu);

        submit.addActionListener(this);
        back.addActionListener(this);
        menu.addActionListener(this);
        doc_Specialisation.addActionListener(this);

        setLayout(null);
        setVisible(true);
        setBounds(500, 300, 1100, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == menu) {
            new Features();
        }
        if (e.getSource() == back) {
            dispose();
            new Doctor_Menu().setVisible(true);
        }
        if (e.getSource() == doc_Specialisation) {
            //dispose();
            try {
                JFrame f = new JFrame("Table View");
                f.setBounds(320, 150, 700, 500);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                String[] col = new String[]{"Diagnosis_Name"};

                JPanel panel = new JPanel();
                panel.setBounds(5, 5, 5, 5);
                f.setContentPane(panel);
                panel.setLayout(null);
                DefaultTableModel model = new DefaultTableModel(data, col);
                table = new JTable(model);
                f.getContentPane().setBackground(Color.pink);
                f.setBounds(320, 150, 1100, 700);
                f.setResizable(false);
                table.setBounds(200, 40, 300, 30);
                JScrollPane jsp = new JScrollPane(table);
                jsp.setBounds(25, 90, 900, 100);
                panel.add(jsp);
                jsp.createVerticalScrollBar();
                JButton jb = new JButton("Select Diagnosis");
                jb.setBounds(40, 300, 150, 30);
                f.add(jb);
//                      JButton jb2 = new JButton ("Update Row");
//                      jb2.setBounds(150,300,120,30);
//                      f.add(jb2);

                      //f.add(new JScrollPane(table));
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
                                DefaultTableModel tbl2 = (DefaultTableModel) table.getModel();
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
            } catch (Exception eee) {

            }

        }

        if (e.getSource() == submit) {
            if (!(SetValidation.IsValidEmail(tf6.getText())) && (SetValidation.isValidContactNumber(tf4.getText())) && (SetValidation.isValidName(tf1.getText())) && (SetValidation.isEligible(tf2.getText())) && (SetValidation.isValidQualification(tf5.getText()))) {

                try {

                    Connection con = ConnectionOneTime.getConnect();

                    PreparedStatement st = con.prepareStatement("INSERT INTO doctor_my_hms(D_Name,D_Age,D_mobile_number,D_Qualification,D_email_id,Doctor_Gender,Doctor_Specialisation,Dr_Fees) values(?,?,?,?,?,?,?,?)");
//                  PreparedStatement st1 = con.prepareStatement("INSERT INTO patient_myhms(Dr_Fees) values(?)");
//                  String m_f=""+cb.getSelectedItem();
//                  String D_Specialisation=""+cb2.getSelectedItem();

                    st.setString(1, tf1.getText());
                    st.setString(2, tf2.getText());
                    st.setString(3, tf4.getText());
                    st.setString(4, tf5.getText());
                    st.setString(5, tf6.getText());
                    st.setString(6, "" + cb.getSelectedItem());
                    st.setString(7, "" + Select_diago2);
                    st.setString(8, tf7.getText());
//                  st1.setString(1, tf7.getText());

                    //st.execute();
                    if (st.execute() == false) {
                        JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Something Wrong");

                    }
                    new Doctor_Menu().setVisible(true);

                    st.close();
                    con.close();
                } catch (Exception ex) {
                    System.out.println("Exception : " + ex);
                    ex.printStackTrace();
                }
            } else {
                dispose();
                JOptionPane.showMessageDialog(frame, "Something Went Wrong\nRe-Enter the Details");
                new DoctorDetails();
            }
        }

    }

    public static void main(String[] args) {
        DoctorDetails dd = new DoctorDetails();
    }
}
