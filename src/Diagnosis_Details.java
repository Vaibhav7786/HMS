import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.*;

public class Diagnosis_Details extends JFrame implements ActionListener {

    JFrame frame;

    JLabel jlabel_doc_name, jlabel_doc_age, jlabel_doc_id, jlabel_doc_mo_no, jl5;
    JButton jb1, Jb2, back, menu;
    ;
      JTextField tf1, tf2, tf3, tf4, tf5;
    JButton hn, ht, hl;

    public Diagnosis_Details() {
        frame = new JFrame();
        getContentPane().setBackground(Color.pink);

        setTitle("Diagnosis_Details");
        hn = new JButton("Submit");
        hn.setBounds(150, 200, 100, 30);

        back = new JButton("Back");
        back.setBounds(320, 200, 100, 30);

        menu = new JButton("Menu");
        menu.setBounds(450, 30, 70, 40);

        jlabel_doc_name = new JLabel("Diagnosis Name :");
        jlabel_doc_name.setBounds(40, 100, 150, 30);

        tf1 = new JTextField();
        tf1.setBounds(150, 100, 150, 30);
//      String Patient_Digonise[]= {"Sports Injury ","Cancer","ENT Surgery", "Liver Transplant","Paralysist", "Hair Transplant","Tuberculosis(TB)", "Infertility and IVF","Spine Surgery", "Infectious Diseases","NeuroSurgery", "Plastic Surgery","Endocrinology-Diabetology", "Dental Cosmetic"};

        add(jlabel_doc_name);

        add(tf1);

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

            if (e.getSource() == menu) {
                dispose();
                new Features();
            }

            if (e.getSource() == back) {
                dispose();
                new Diagnosis_Menu().setVisible(true);
            }

            if (e.getSource() == hn) {
                dispose();
                Connection con = ConnectionOneTime.getConnect();
                PreparedStatement st = con.prepareStatement("INSERT INTO diagnosis_myhms (Diagnosis_Name) Values (?)");
                st.setString(1, tf1.getText());

                // st.executeUpdate();
                if (st.execute() == false) {
                    JOptionPane.showMessageDialog(null, "Data Inserte Successfully");
                    new Manage_Entities().setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Something Wrong");

                }
            }

        } catch (Exception ex) {
            System.out.println("Exception : " + ex);
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Diagnosis_Details ddobj = new Diagnosis_Details();
    }

}
