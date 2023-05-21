import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tables extends JFrame implements ActionListener {

    Object data[][] = new Object[][]{};
    JTable table;
    String did;
    String pid;
    String Med_Name;
    JFrame frame, f, f2, f3;
    JButton dr, pat, med, about, jb, jb2, back, menu, back2, menu2, back3, menu3;

    public Tables() {
        frame = new JFrame();
        getContentPane().setBackground(Color.pink);

        setTitle("Tables");
        dr = new JButton("Doctor Details");
        dr.setBounds(70, 120, 150, 30);

        pat = new JButton("Patient Details");
        pat.setBounds(330, 120, 150, 30);

        med = new JButton("Medicines");
        med.setBounds(70, 200, 150, 30);

        about = new JButton("About");
        about.setBounds(330, 200, 150, 30);

        back = new JButton("Back");
        back.setBounds(230, 300, 100, 30);

        menu = new JButton("Menu");
        menu.setBounds(530, 30, 70, 40);

        add(menu);
        add(dr);
        add(pat);
        add(med);
        add(about);
        add(back);

        dr.addActionListener(this);
        pat.addActionListener(this);
        med.addActionListener(this);
        about.addActionListener(this);
        back.addActionListener(this);
        menu.addActionListener(this);

        setLayout(null);
        setVisible(true);
        setBounds(500, 300, 1100, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == menu) {
                dispose();
                new Features();
            }

            if (e.getSource() == back) {
                dispose();
                new Features().setVisible(true);
            }

            if (e.getSource() == dr) {
                dispose();
//                    JFrame f =new JFrame("Table View");  
                f = new JFrame("Table View");
               // f.setBounds(320, 150, 700, 500);

                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                String[] col = new String[]{"Doctor_ID", "Name", "Age", "Mobile_No",
                    "Qualification", "Email_ID", "Gender", "Specialisation"};
                JPanel panel = new JPanel();
                panel.setBounds(5, 5, 5, 5);
                f.setContentPane(panel);
                panel.setLayout(null);
                DefaultTableModel model = new DefaultTableModel(data, col);
                table = new JTable(model);
                f.getContentPane().setBackground(Color.pink);
                f.setBounds(500, 300, 1100, 700);
                f.setResizable(false);
                table.setBounds(200, 60, 300, 30);
                JScrollPane jsp = new JScrollPane(table);
                jsp.setBounds(25, 90, 900, 100);
                panel.add(jsp);
                jsp.createVerticalScrollBar();
                back = new JButton("Back");
                back.setBounds(280, 300, 100, 30);
                menu = new JButton("Menu");
                menu.setBounds(950, 30, 70, 40);
                f.add(back);
                f.add(menu);

                jb = new JButton("Delete Row");
                jb.setBounds(40, 300, 100, 30);
                f.add(jb);
//                jb2 = new JButton("Update Row");
//                jb2.setBounds(150, 300, 120, 30);
//                f.add(jb2);
                back.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == back) {
                            f.dispose();
                            new Tables();
                        }
                    }
                });
                menu.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == menu) {
                            new Features();
                        }
                    }
                });

                jb.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
//                        
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
//                                 Doctor_Specialisation = tbl.getValueAt(table.getSelectedRow(), 7).toString();
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

                // f.setSize(700,500);
                //f.setBounds(320, 150, 700, 500);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//                        String[] col = new String[]{"Doctor_ID","Doctor_Name","Doctor_Age","Doctor_Mobile_No","Doctor_Qualification","Doctor_Email_ID"}; 
//                        DefaultTableModel model = new DefaultTableModel(data,col);
//                        JTable table = new JTable(model);
//                        table.setBounds(320,150,700,500) ;
                //f.add(new JScrollPane(table));
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

                while (rs.next()) {
                    D_ID = "" + rs.getInt(1);
                    D_Name = "" + rs.getString("D_Name");
                    D_Mobile_No = "" + rs.getString("D_mobile_number");
                    D_Age = "" + rs.getInt(3);
                    D_Quali = "" + rs.getString("D_Qualification");
                    D_Email = "" + rs.getString("D_email_id");
                    D_Gender = "" + rs.getString("Doctor_Gender");
                    D_Specialisation = "" + rs.getString("Doctor_Specialisation");
                    String array[] = {D_ID, D_Name, D_Age, D_Mobile_No, D_Quali, D_Email, D_Gender, D_Specialisation};
                    DefaultTableModel m = (DefaultTableModel) table.getModel();
                    m.addRow(array);
                }
            }
            if (e.getSource() == pat) {
                dispose();
//                    JFrame f =new JFrame("Table View");  
                f2 = new JFrame("Table View");
                f2.setBounds(500, 300, 1100, 700);
                f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                String[] col = new String[]{"Patient_ID", "Name", "Age", "Dignosis", "Mobile_No",
                    "Email_ID", "Blood_group", "Room_No", "Gender", "Allot_Doctor", "Admit_Date_Time", "Dis_Date_Time", "Is_active"};
                JPanel panel = new JPanel();
                panel.setBounds(5, 5, 5, 5);
                f2.setContentPane(panel);
                panel.setLayout(null);
                DefaultTableModel model = new DefaultTableModel(data, col);
                table = new JTable(model);
                f2.getContentPane().setBackground(Color.pink);

               // table.setBounds(200, 40, 300, 30);
                // f.setBounds(500, 300, 1100, 700);
                // f2.setResizable(false);
                table.setBounds(200, 60, 300, 30);
                JScrollPane jsp = new JScrollPane(table);
                jsp.setBounds(25, 90, 1030, 100);
                panel.add(jsp);
                jsp.createVerticalScrollBar();
                back2 = new JButton("Back");
                back2.setBounds(280, 300, 100, 30);
                menu2 = new JButton("Menu");
                menu2.setBounds(950, 30, 70, 40);
                f2.add(back2);
                f2.add(menu2);
                jb = new JButton("Delete Row");
                jb.setBounds(40, 300, 100, 30);
                f2.add(jb);
//                jb2 = new JButton("Update Row");
//                jb2.setBounds(150, 300, 120, 30);
//                f2.add(jb2);
                back2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == back2) {
                            f2.dispose();
                            new Tables();
                        }
                    }
                });
                menu2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == menu2) {
                            f2.dispose();
                            new Features();
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
                                pid = tbl.getValueAt(table.getSelectedRow(), 0).toString();

                                Connection con = ConnectionOneTime.getConnect();
                                DefaultTableModel tbl2 = (DefaultTableModel) table.getModel();
                                if (table.getSelectedRowCount() == 1) {
                                    String s = "delete from patient_myhms where P_ID='" + pid + "'";
                                    PreparedStatement ps = con.prepareStatement(s);

                                    ps.execute();
                                    tbl2.removeRow(table.getSelectedRow());
                                }

                            } catch (Exception ex) {

                            }
                        }
                    }
                });

                // f.setSize(700,500);
                // f2.setBounds(320, 150, 700, 500);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//                        String[] col = new String[]{"Patient_ID","Patient_Name","Patient_Age","Diagnosis","Contact No.","Email_ID","Blood_Group","P_Room_No"}; 
//                        DefaultTableModel model = new DefaultTableModel(data,col);
//                        JTable table = new JTable(model);
//                        table.setBounds(320,150,700,500) ;
                // f.add(new JScrollPane(table));
                setLayout(null);
                f2.setVisible(true);
                Connection con = ConnectionOneTime.getConnect();
                Statement st = con.createStatement();
                String q = "select * from patient_myhms";
                ResultSet rs = st.executeQuery(q);
                String P_ID = "";
                String P_Name = "";
                String P_Age = "";
                String P_Diagnosis = "";
                String P_mobile_No = "";
                String P_Email = "";
                String P_Blood_Group = "";
                String P_Room_No = "";
                String P_Gender = "";
                String Allot_Doc = "";
                String P_Admit_Date_Time = "";
                String P_dis_date_time = "";
                String Is_active = "";

                while (rs.next()) {
                    P_ID = "" + rs.getInt(1);
                    P_Name = "" + rs.getString("P_Name");
                    P_Age = "" + rs.getInt(3);
                    P_Diagnosis = "" + rs.getString("P_Diagnosis");
                    P_mobile_No = "" + rs.getString("P_mobile_No");
                    P_Email = "" + rs.getString("P_Email");
                    P_Blood_Group = "" + rs.getString("P_Blood_Group");
                    P_Room_No = "" + rs.getString("P_Room_No");
                    P_Gender = "" + rs.getString("gender");
                    Allot_Doc = "" + rs.getString("Allot_DOc");
                    P_Admit_Date_Time = "" + rs.getString("P_Admit_Date_Time");
                    P_dis_date_time = "" + rs.getString("P_dis_date_time");
                    Is_active = rs.getBoolean("Is_active") == true ? "true" : "false";
                    String array[] = {P_ID, P_Name, P_Age, P_Diagnosis, P_mobile_No, P_Email, P_Blood_Group, P_Room_No, P_Gender, Allot_Doc, P_Admit_Date_Time, P_dis_date_time, Is_active};
                    DefaultTableModel m = (DefaultTableModel) table.getModel();
                    m.addRow(array);
                }
            }

            if (e.getSource() == med) {
                // new PatientDetails().setVisible(true);
                dispose();
                f3 = new JFrame("Table View");
                f3.setBounds(500, 300, 1100, 700);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                String[] col = new String[]{"MName", "Med_mg", "M_Quan", "Med_Price"};
                JPanel panel = new JPanel();
                panel.setBounds(5, 5, 5, 5);
                f3.setContentPane(panel);
                panel.setLayout(null);
                DefaultTableModel model = new DefaultTableModel(data, col);
                JTable table = new JTable(model);
                f3.getContentPane().setBackground(Color.pink);

                table.setBounds(200, 40, 300, 30);

                JScrollPane jsp = new JScrollPane(table);
                jsp.setBounds(25, 90, 1030, 100);
                panel.add(jsp);
                jsp.createVerticalScrollBar();
                back3 = new JButton("Back");
                back3.setBounds(280, 300, 100, 30);
                menu3 = new JButton("Menu");
                menu3.setBounds(950, 30, 70, 40);
                f3.add(back3);
                f3.add(menu3);
                JButton jb = new JButton("Delete Row");
                jb.setBounds(40, 300, 100, 30);
                f3.add(jb);
//                JButton jb2 = new JButton("Update Row");
//                jb2.setBounds(150, 300, 120, 30);
//                f3.add(jb2);

                // f.add(new JScrollPane(table));
                back3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == back3) {
                            f3.dispose();
                            new Tables();
                        }
                    }
                });
                menu3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == menu3) {
                            f3.dispose();
                            new Features();
                        }
                    }
                });

                jb.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (table.getSelectedRow() == -1) {
                            JOptionPane.showMessageDialog(null, "Please Select Row ", "error", JOptionPane.ERROR_MESSAGE);
                        }

                        int status = JOptionPane.showConfirmDialog(null, "Do You Want to Delete This Row", "Confirm", JOptionPane.YES_NO_OPTION);
                        if (status == JOptionPane.YES_OPTION) {
                            // model.removeRow(table.getSelectedRow());
                            try {
                                DefaultTableModel tbl = (DefaultTableModel) table.getModel();
                                Med_Name = tbl.getValueAt(table.getSelectedRow(), 0).toString();
//                                Med_mg = tbl.getValueAt(table.getSelectedRow(), 1).toString();
//                                Med_Quant = tbl.getValueAt(table.getSelectedRow(), 2).toString();
//                                Med_price = tbl.getValueAt(table.getSelectedRow(), 3).toString();
//                                Doctor_Quali = tbl.getValueAt(table.getSelectedRow(), 4).toString();
//                                Doctor_Email = tbl.getValueAt(table.getSelectedRow(), 5).toString();

                                Connection con = ConnectionOneTime.getConnect();
                                DefaultTableModel tbl2 = (DefaultTableModel) table.getModel();
                                if (table.getSelectedRowCount() == 1) {
                                    String s = "delete from medicine_myhms where Med_Name='" + Med_Name + "'";
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
                f3.setVisible(true);

                Connection con = ConnectionOneTime.getConnect();

                Statement st = con.createStatement();
                String q = "select * from medicine_myhms";
                ResultSet rs = st.executeQuery(q);
                String MName = "";
                String Med_mg = "";
                String M_Quan = "";
                String Med_Price = "";
                while (rs.next()) {
                    MName = "" + rs.getString("Med_Name");
                    Med_mg = "" + rs.getString(2);
                    M_Quan = "" + rs.getInt(3);
                    Med_Price = "" + rs.getInt(4);

                    String array[] = {MName, Med_mg, M_Quan, Med_Price};
                    DefaultTableModel m = (DefaultTableModel) table.getModel();
                    m.addRow(array);
                }
            }

            if (e.getSource() == about) {
                // new PatientDetails().setVisible(true);
            }
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Tables t = new Tables();
    }
}
