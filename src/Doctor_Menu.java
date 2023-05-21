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

public class Doctor_Menu extends JFrame implements ActionListener {

    JLabel jlabel_doc_name, jlabel_doc_age, jlabel_doc_id, jlabel_doc_mo_no, jl5;
    JButton jb1, Jb2;
    JTextField tf1, tf2, tf3, tf4, tf5;
    JFrame frame;
    JFrame f;
    JButton ad, dd, see_table, back, menu;
    JTable table;
    String did, Doctor_Name, Doctor_Age, Doctor_Mo_No, Doctor_Quali, Doctor_Email, Doctor_Specialisation, Doctor_Gender;

    Object data[][] = new Object[][]{};

    public Doctor_Menu() {
        frame = new JFrame();
        getContentPane().setBackground(Color.pink);

        setTitle("Doctor_Menu");
        ad = new JButton("Add Doctor");
        ad.setBounds(150, 130, 150, 30);

        dd = new JButton("Delete Doctor");
        dd.setBounds(350, 130, 150, 30);

        see_table = new JButton("See Table");
        see_table.setBounds(150, 200, 150, 30);

        back = new JButton("Back");
        back.setBounds(350, 200, 150, 30);
        menu = new JButton("Menu");
        menu.setBounds(530, 30, 70, 40);

        add(ad);
        add(dd);
        add(see_table);
        add(back);
        add(menu);

        ad.addActionListener(this);
        dd.addActionListener(this);
        see_table.addActionListener(this);
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
            if (e.getSource() == ad) {
                dispose();
                new DoctorDetails().setVisible(true);
            }

            if (e.getSource() == back) {
                dispose();
                new Manage_Entities();
                // new PatientDetails().setVisible(true);
            }
            if (e.getSource() == menu) {
                dispose();
                new Features();
                // new PatientDetails().setVisible(true);
            }
            if (e.getSource() == dd) {
                dispose();
                f = new JFrame("Table View");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                String[] col = new String[]{"Doctor_ID", "Name", "Age", "Mobile_No",
                    "Qualification", "Email_ID", "Gender", "Specialisation", "Fees"};
                JPanel panel = new JPanel();
                panel.setBounds(5, 5, 5, 5);
                f.setContentPane(panel);
                panel.setLayout(null);
                DefaultTableModel model = new DefaultTableModel(data, col);
                table = new JTable(model);
                f.getContentPane().setBackground(Color.pink);
                f.setBounds(320, 150, 1100, 700);
                f.setResizable(false);
                table.setBounds(200, 60, 300, 30);
                JScrollPane jsp = new JScrollPane(table);
                jsp.setBounds(25, 90, 900, 100);
                panel.add(jsp);
                jsp.createVerticalScrollBar();

                JButton jb = new JButton("Delete Row");
                jb.setBounds(40, 250, 100, 30);
                f.add(jb);

//                JButton jb2 = new JButton("Update Row");
//                jb2.setBounds(150, 250, 120, 30);
//                f.add(jb2);
                JButton back = new JButton("Back");
                back.setBounds(290, 250, 90, 30);
                f.add(back);

                JButton menu = new JButton("Menu");
                menu.setBounds(950, 30, 70, 40);
                f.add(menu);

                menu.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == menu) {
                            f.dispose();
                            new Features();
                        }
                    }
                });

                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == back) {
                            f.dispose();
                            new Doctor_Menu();
                        }
                    }
                });
                jb.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (table.getSelectedRow() == -1) {
                            JOptionPane.showMessageDialog(null, "Please Select Row ", "error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        int status = JOptionPane.showConfirmDialog(null, "Do You Want to Delete This Row", "Confirm",
                                JOptionPane.YES_NO_OPTION);
                        // System.out.println(""+status);
                        if (status == JOptionPane.YES_OPTION) {
                            try {
                                DefaultTableModel tbl = (DefaultTableModel) table.getModel();
                                did = tbl.getValueAt(table.getSelectedRow(), 0).toString();
//                                Doctor_Name = tbl.getValueAt(table.getSelectedRow(), 1).toString();
//                                Doctor_Age = tbl.getValueAt(table.getSelectedRow(), 2).toString();
//                                Doctor_Mo_No = tbl.getValueAt(table.getSelectedRow(), 3).toString();
//                                Doctor_Quali = tbl.getValueAt(table.getSelectedRow(), 4).toString();
//                                Doctor_Email = tbl.getValueAt(table.getSelectedRow(), 5).toString();
//                                Doctor_Gender = tbl.getValueAt(table.getSelectedRow(), 6).toString();
//                                Doctor_Specialisation = tbl.getValueAt(table.getSelectedRow(), 7).toString();
                                Connection con = ConnectionOneTime.getConnect();
                                DefaultTableModel tbl2 = (DefaultTableModel) table.getModel();
                                if (table.getSelectedRowCount() == 1) {
                                    String s = "delete from doctor_my_hms where D_ID='" + did + "'";
                                    PreparedStatement ps = con.prepareStatement(s);

                                    ps.execute();
                                    tbl2.removeRow(table.getSelectedRow());
                                }

                            } catch (Exception ex) {

                            }
                        }
                    }
                });

                setLayout(null);
                f.setVisible(true);

                setLayout(null);
                f.setVisible(true);

                Connection con = ConnectionOneTime.getConnect();
                Statement st = con.createStatement();

                String q = "select * from doctor_my_hms";
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

            }
            if (e.getSource() == see_table) {
                dispose();
                f = new JFrame("Table View");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                String[] col = new String[]{"Doctor_ID", "Name", "Age", "Mobile_No",
                    "Qualification", "Email_ID", "Gender", "Specialisation", "Fees"};
                JPanel panel = new JPanel();
                panel.setBounds(5, 5, 5, 5);
                f.setContentPane(panel);
                panel.setLayout(null);
                DefaultTableModel model = new DefaultTableModel(data, col);
                table = new JTable(model);
                f.getContentPane().setBackground(Color.pink);
                f.setBounds(320, 150, 1100, 700);
                f.setResizable(false);
                table.setBounds(200, 60, 300, 30);
                JScrollPane jsp = new JScrollPane(table);
                jsp.setBounds(25, 90, 900, 100);
                panel.add(jsp);
                jsp.createVerticalScrollBar();

//                JButton jb = new JButton("Delete Row");
//                jb.setBounds(40, 250, 100, 30);
//                f.add(jb);
//                
//                JButton jb2 = new JButton("Update Row");
//                jb2.setBounds(150, 250, 120, 30);
//                f.add(jb2);
                JButton back = new JButton("Back");
                back.setBounds(40, 250, 100, 30);
                f.add(back);

                JButton menu = new JButton("Menu");
                menu.setBounds(950, 30, 70, 40);
                f.add(menu);

                menu.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == menu) {
                            dispose();
                            new Features();
                        }
                    }
                });

                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == back) {
                            dispose();
                            new Doctor_Menu();
                        }
                    }
                });

                setLayout(null);
                f.setVisible(true);

                setLayout(null);
                f.setVisible(true);

                Connection con = ConnectionOneTime.getConnect();
                Statement st = con.createStatement();

                String q = "select * from doctor_my_hms";
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

                // new PatientDetails().setVisible(true);
            }

        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
            ex.printStackTrace();
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        Doctor_Menu dm = new Doctor_Menu();

    }

}
