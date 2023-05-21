import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Driver;
import java.sql.DriverManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Medicine_Menu extends JFrame implements ActionListener {

    JLabel jlabel_doc_name, jlabel_doc_age, jlabel_doc_id, jlabel_doc_mo_no, jl5;
    JButton jb1, Jb2;
    JFrame f;

    JTextField tf1, tf2, tf3, tf4, tf5;
    JFrame frame;
    JButton am, dm, see_table, back, menu;
    String Med_Name;
    Object data[][] = new Object[][]{};

    public Medicine_Menu() {
        frame = new JFrame();
        getContentPane().setBackground(Color.pink);

        setTitle("Medicine_Menu");
        am = new JButton("Add Medicine");
        am.setBounds(100, 160, 150, 30);

        dm = new JButton("Delete Medicine");
        dm.setBounds(350, 160, 150, 30);
        see_table = new JButton("See Table");
        see_table.setBounds(100, 240, 150, 30);
        back = new JButton("Back");
        back.setBounds(350, 240, 150, 30);
        menu = new JButton("Menu");
        menu.setBounds(530, 30, 70, 40);

        add(am);
        add(dm);
        add(see_table);
        add(back);
        add(menu);

        am.addActionListener(this);
        dm.addActionListener(this);
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
            if (e.getSource() == menu) {
                new Features();
            }
            if (e.getSource() == back) {
                dispose();
                new Manage_Entities().setVisible(true);

            }

            if (e.getSource() == am) {
                dispose();
                new Medicine().setVisible(true);
            }
            if (e.getSource() == dm) {
                dispose();
                f = new JFrame("Table View");

                f.setBounds(320, 150, 650, 400);

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                String[] col = new String[]{"Medicine Name", "Mg", "Quantity", "Price"};
                JPanel panel = new JPanel();
                panel.setBounds(5, 5, 5, 5);
                f.setContentPane(panel);
                panel.setLayout(null);
                DefaultTableModel model = new DefaultTableModel(data, col);
                JTable table = new JTable(model);
                f.getContentPane().setBackground(Color.pink);

                table.setBounds(20, 40, 300, 30);
                JScrollPane jsp = new JScrollPane(table);
                jsp.setBounds(25, 100, 500, 100);
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
                menu.setBounds(550, 30, 70, 40);
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
                            new Medicine_Menu();
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
                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });

                setLayout(null);
                f.setVisible(true);

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
                // new Medicine_Menu().setVisible(true);
            }
            if (e.getSource() == see_table) {
                dispose();
                // new Medicine_Table().setVisible(true);
                f = new JFrame("Table View");

                f.setBounds(320, 150, 650, 400);

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                String[] col = new String[]{"Medicine Name", "Mg", "Quantity", "Price"};
                JPanel panel = new JPanel();
                panel.setBounds(5, 5, 5, 5);
                f.setContentPane(panel);
                panel.setLayout(null);
                DefaultTableModel model = new DefaultTableModel(data, col);
                JTable table = new JTable(model);
                f.getContentPane().setBackground(Color.pink);

                table.setBounds(20, 40, 300, 30);
                JScrollPane jsp = new JScrollPane(table);
                jsp.setBounds(25, 100, 500, 100);
                panel.add(jsp);
                jsp.createVerticalScrollBar();

//                JButton jb = new JButton("Delete Row");
//                jb.setBounds(40, 250, 100, 30);
//                f.add(jb);
//                JButton jb2 = new JButton("Update Row");
//                jb2.setBounds(150, 250, 120, 30);
//                f.add(jb2);

                JButton back = new JButton("Back");
                back.setBounds(40, 250, 100, 30);
                f.add(back);

                JButton menu = new JButton("Menu");
                menu.setBounds(550, 30, 70, 40);
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
                            new Medicine_Menu();
                        }
                    }
                });

//                jb.addActionListener(new ActionListener() {
//
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (table.getSelectedRow() == -1) {
//                            JOptionPane.showMessageDialog(null, "Please Select Row ", "error", JOptionPane.ERROR_MESSAGE);
//                        }
//
//                        int status = JOptionPane.showConfirmDialog(null, "Do You Want to Delete This Row", "Confirm", JOptionPane.YES_NO_OPTION);
//                        if (status == JOptionPane.YES_OPTION) {
//                            // model.removeRow(table.getSelectedRow());
//                            try {
//                                DefaultTableModel tbl = (DefaultTableModel) table.getModel();
//                                Med_Name = tbl.getValueAt(table.getSelectedRow(), 0).toString();
////                                Med_mg = tbl.getValueAt(table.getSelectedRow(), 1).toString();
////                                Med_Quant = tbl.getValueAt(table.getSelectedRow(), 2).toString();
////                                Med_price = tbl.getValueAt(table.getSelectedRow(), 3).toString();
////                                Doctor_Quali = tbl.getValueAt(table.getSelectedRow(), 4).toString();
////                                Doctor_Email = tbl.getValueAt(table.getSelectedRow(), 5).toString();
//
//                                Connection con = ConnectionOneTime.getConnect();
//                                DefaultTableModel tbl2 = (DefaultTableModel) table.getModel();
//                                if (table.getSelectedRowCount() == 1) {
//                                    String s = "delete from medicine_myhms where Med_Name='" + Med_Name + "'";
//                                    PreparedStatement ps = con.prepareStatement(s);
//
//                                    ps.execute();
//                                    tbl2.removeRow(table.getSelectedRow());
//                                }
//
//                            } catch (Exception ex) {
//
//                            }
//
//                        }
//                        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    }
//                });

                setLayout(null);
                f.setVisible(true);

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
        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Medicine_Menu dm = new Medicine_Menu();
    }

}
