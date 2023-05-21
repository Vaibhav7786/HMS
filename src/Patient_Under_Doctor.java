import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Driver;
import java.sql.DriverManager;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class Patient_Under_Doctor extends JFrame implements ActionListener {

    Object data[][] = new Object[][]{};
    JFrame f;
    String pid;
    JLabel jl1;
    JButton back;

    public Patient_Under_Doctor(String pid) {

        this.pid = pid;

        f = new JFrame("Table View");
        // f.setSize(700, 500);
        f.setBounds(500, 300, 1100, 700);
        f.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] col = new String[]{"P_ID", "Name", "Diagnosis", "Gender", "Is_active"};
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 5, 5);
        f.setContentPane(panel);
        panel.setLayout(null);
        DefaultTableModel model = new DefaultTableModel(data, col);
        JTable table = new JTable(model);
        f.getContentPane().setBackground(Color.pink);
        jl1 = new JLabel("PATIENT LIST UNDER DOCTOR");
        jl1.setBounds(200, 20, 200, 100);
        f.add(jl1);
        table.setBounds(200, 40, 300, 30);
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(25, 90, 1040, 100);
        panel.add(jsp);
        jsp.createVerticalScrollBar();
        // f.add(new JScrollPane(table));
        back = new JButton("Back");
        back.setBounds(50, 200, 100, 30);
        f.add(back);
        back.addActionListener(this);

        setLayout(null);
        f.setVisible(true);

        try {
            Connection con = ConnectionOneTime.getConnect();

            Statement st = con.createStatement();

            String q = "select * from patient_myhms where D_ID= '" + pid + "'";
            ResultSet rs = st.executeQuery(q);
            String MName = "";
            String Med_mg = "";
            String M_Quan = "";
            String Med_Price = "";
            String Is_active = "";
            while (rs.next()) {
                MName = "" + rs.getString("P_ID");
                Med_mg = "" + rs.getString("P_Name");
                M_Quan = "" + rs.getString("P_Diagnosis");
                Med_Price = "" + rs.getString("Gender");
                Is_active = "" + rs.getString("Is_active");

                String array[] = {MName, Med_mg, M_Quan, Med_Price, Is_active};
                DefaultTableModel m = (DefaultTableModel) table.getModel();
                m.addRow(array);
            }
        } catch (Exception ee) {

        }
    }

    public void actionPerformed(ActionEvent e) {

        try {

            if (e.getSource() == back) {
                dispose();

                new DischargePatient_Id().setVisible(true);
            }

        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

    }
}
