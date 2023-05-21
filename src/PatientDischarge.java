import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PatientDischarge extends JFrame {

    JTable table;
    Object data[][] = new Object[][]{};
    String pid = "";

    public PatientDischarge() {
        try {
            JFrame f = new JFrame("Table View");
            f.setBounds(150, 50, 1100, 700);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            String[] col = new String[]{"Patient ID", "Name", "Age", "Diagnosis", "Mobile No", "Email-ID", "Blood Group", "Room No", "Gender", "Doctor Alloted", "Admit Date", "Discharge Date", "Active"};

            JPanel panel = new JPanel();
            panel.setBounds(5, 5, 5, 5);
            f.setContentPane(panel);
            panel.setLayout(null);
            DefaultTableModel model = new DefaultTableModel(data, col);

            table = new JTable(model);
            f.getContentPane().setBackground(Color.pink);

            table.setBounds(200, 40, 300, 30);
            JScrollPane jsp = new JScrollPane(table);
            jsp.setBounds(25, 100, 640, 100);
            panel.add(jsp);
            jsp.createVerticalScrollBar();
            JButton jb = new JButton("Discharge");
            jb.setBounds(40, 300, 100, 30);
            f.add(jb);

            JButton jb2 = new JButton("Back");
            jb2.setBounds(150, 300, 100, 30);
            f.add(jb2);

            jb2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == jb2) {
                        f.dispose();
                        new Features();
                    }
                }
            });
            table.setVisible(true);
            jb.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (table.getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(null, "Please Select Row ", "error", JOptionPane.ERROR_MESSAGE);
                    }

                    int status = JOptionPane.showConfirmDialog(null, "Do You Want to Discharge This Patient", "Confirm", JOptionPane.YES_NO_OPTION);

                    if (status == JOptionPane.YES_OPTION) {
                        try {
                            DefaultTableModel tbl = (DefaultTableModel) table.getModel();
                            pid = tbl.getValueAt(table.getSelectedRow(), 0).toString();

                            Connection con = ConnectionOneTime.getConnect();
                            DefaultTableModel tbl2 = (DefaultTableModel) table.getModel();
                            if (table.getSelectedRowCount() == 1) {
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//HH:mm:ss
                                Date date = new Date();
                                String dis_date = formatter.format(date);

                                String s = "update patient_myhms set  Is_active = false where P_ID='" + pid + "'";
                                PreparedStatement ps = con.prepareStatement(s);
                                ps.executeUpdate();

                                String s2 = "UPDATE patient_myhms SET P_dis_date_time = ? WHERE P_ID = ?";
                                PreparedStatement ps2 = con.prepareStatement(s2);
                                ps2.setString(1, dis_date);
                                ps2.setString(2, pid);
                                ps2.executeUpdate();

                                dispose();
                                new PatientDischarge();
//                                tbl2.(table.getSelectedRow());
                            }

                        } catch (Exception ex) {

                        }
                    }
                }
            });
            setBounds(500, 300, 1100, 700);
            setResizable(false);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         f.setSize(700,500);
//        f.setBounds(320, 150, 700, 500);
            // f.add(new JScrollPane(table));
//        try {

            setLayout(null);
            f.setVisible(true);

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
        } catch (Exception e) {
            System.out.println("Exception  : " + e);
        }
//        table.setVisible(true);
    }

    public static void main(String[] args) {
        PatientDischarge p = new PatientDischarge();
    }

}
