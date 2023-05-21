//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.table.DefaultTableModel;
//
//public class Give_Medicine extends JFrame {
//
//    JLabel jlabel_pat_id, jlabel_doc_age, jlabel_doc_id, jlabel_doc_mo_no, jl5;
//    JButton jb1, Jb2;
//    JFrame f;
//
//    JTextField tf1, tf2, tf3, tf4, tf5;
//    JFrame frame;
//    JButton am, dm, see_table, back, menu;
//    static String pid;
//    static String Med_Name, Patient_name, Med_price;
//    Object data[][] = new Object[][]{};
//
//    static void sendMedName() {
//        try {
//
//            Connection con = ConnectionOneTime.getConnect();
//            System.out.println(Patient_name);
//            System.out.println(pid);
//          
//            
//           PreparedStatement ps = con.prepareStatement("update  patient_myhms set Med_type =(select group_concat(med_name separator ',') from medicine_2_myhms) where P_ID = '"+pid+"'");
//            ps.setString(1, "crocin");
//            // ps.setString(2, Med_price);
//            ps.execute();
//        } catch (Exception ee) {
//
//        }
//        // String sq = "update  patient_myhms set Med_Type =(select group_concat(med_name separator ',') from medicine_2_myhms;
//
//    }
//
//    Give_Medicine() {
//        this.Patient_name = Patient_name;
//        f = new JFrame("Table View");
//
//        f.setBounds(320, 150, 650, 400);
//       
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        String[] col = new String[]{"Medicine Name", "Mg", "Quantity", "Price"};
//        JPanel panel = new JPanel();
//        panel.setBounds(5, 5, 5, 5);
//        f.setContentPane(panel);
//        panel.setLayout(null);
//        DefaultTableModel model = new DefaultTableModel(data, col);
//        JTable table = new JTable(model);
//        f.getContentPane().setBackground(Color.pink);
//
//        table.setBounds(20, 40, 300, 30);
//        JScrollPane jsp = new JScrollPane(table);
//        jsp.setBounds(25, 100, 500, 100);
//        panel.add(jsp);
//        jsp.createVerticalScrollBar();
//
//        JButton jb = new JButton("Give Medicine");
//        jb.setBounds(40, 250, 100, 30);
//        f.add(jb);
////        JButton jb3 = new JButton("Give Medicine");
////        jb3.setBounds(40, 250, 100, 30);
////        f.add(jb);
//         jlabel_pat_id = new JLabel("Patient ID");
//        jlabel_pat_id.setBounds(50, 30, 200,40);
//        f.add(jlabel_pat_id);
//        tf1 = new JTextField();
//        tf1.setBounds(150, 30, 200, 40);
//        f.add(tf1);
//        JButton jb2 = new JButton("more");
//        jb2.setBounds(150, 250, 100, 30);
//        f.add(jb2);
//
//        JButton back = new JButton("Back");
//        back.setBounds(290, 250, 90, 30);
//        f.add(back);
//
//        JButton menu = new JButton("Menu");
//        menu.setBounds(550, 30, 70, 40);
//        f.add(menu);
//
//        menu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == menu) {
//                    dispose();
//                    new Features();
//                }
//            }
//        });
//
//        back.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == back) {
//                    dispose();
//                    new Medicine_Menu();
//                }
//            }
//        });
//        jb2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (e.getSource() == jb2) {
//                        // dispose();
//                    //  new Medicine_Menu();
//                    pid=tf1.getText();
//                    System.out.println(pid);
//                    Give_Medicine.sendMedName();
//                }
//            }
//        });
//
//        jb.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (table.getSelectedRow() == -1) {
//                    JOptionPane.showMessageDialog(null, "Please Select Row ", "error", JOptionPane.ERROR_MESSAGE);
//                }
//
//                int status = JOptionPane.showConfirmDialog(null, "Do You Want to Delete This Row", "Confirm", JOptionPane.YES_NO_OPTION);
//                if (status == JOptionPane.YES_OPTION) {
//                    // model.removeRow(table.getSelectedRow());
//                    try {
//                        DefaultTableModel tbl = (DefaultTableModel) table.getModel();
//                        Med_Name = tbl.getValueAt(table.getSelectedRow(), 0).toString();
//                        System.out.println(Med_Name);
////                                Med_mg = tbl.getValueAt(table.getSelectedRow(), 1).toString();
////                                Med_Quant = tbl.getValueAt(table.getSelectedRow(), 2).toString();
//                        Med_price = tbl.getValueAt(table.getSelectedRow(), 3).toString();
////                                Doctor_Quali = tbl.getValueAt(table.getSelectedRow(), 4).toString();
////                                Doctor_Email = tbl.getValueAt(table.getSelectedRow(), 5).toString();
//
//                        Connection con = ConnectionOneTime.getConnect();
//                        DefaultTableModel tbl2 = (DefaultTableModel) table.getModel();
//                        if (table.getSelectedRowCount() == 1) {
//                                   // String s = "select from medicine_myhms where Med_Name='" + Med_Name + "'";
//
//                                   // String sq = "insert into medicine_2__myhms (Med_name,Med_price) values (?,?)";
//                            System.out.println("" + Patient_name);
//                            PreparedStatement ps = con.prepareStatement("insert into medicine_2_myhms (Med_Name,Med_price) values (?,?)");
//
//                                    //PreparedStatement ps = con.prepareStatement(sq);
////                                    String sq = "update  patient_myhms set Med_Type ='"+Med_Name+" where P_Name='" + Patient_name + "'";
////
//                            //PreparedStatement st = con.prepareStatement("INSERT INTO patient_myhms(P_Name,P_Age,P_Diagnosis,P_mobile_No,P_Email,P_Blood_Group,P_Room_No,Gender,Allot_Doc,P_Admit_Date_Time) values(?,?,?,?,?,?,?,?,?,?)");
//                            ps.setString(1, Med_Name);
//                            ps.setString(2, Med_price);
//
//                            ps.executeUpdate();
////                                    Give_Medicine.sendMedName();
//
//                            //tbl2.removeRow(table.getSelectedRow());
//                        }
//
//                    } catch (Exception ex) {
//
//                    }
//
//                }
//                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
//        setLayout(null);
//        f.setVisible(true);
//
//        Connection con = ConnectionOneTime.getConnect();
//        try {
//            Statement st = con.createStatement();
//            String q = "select * from medicine_myhms";
//            ResultSet rs = st.executeQuery(q);
//            String MName = "";
//            String Med_mg = "";
//            String M_Quan = "";
//            String Med_Price = "";
//            while (rs.next()) {
//                MName = "" + rs.getString("Med_Name");
//                Med_mg = "" + rs.getString(2);
//                M_Quan = "" + rs.getInt(3);
//                Med_Price = "" + rs.getInt(4);
//
//                String array[] = {MName, Med_mg, M_Quan, Med_Price};
//                DefaultTableModel m = (DefaultTableModel) table.getModel();
//                m.addRow(array);
//            }
//        } catch (Exception e) {
//
//        }
//
//    }
//
//    public static void main(String[] args) {
//
//          Give_Medicine gmobj = new Give_Medicine();
//    }
//
//}
