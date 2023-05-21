import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.*;
import org.jdatepicker.*;
public class DischargePatient extends JFrame implements ActionListener {
  JFrame frame;
JLabel resultLabel;
   String Admit_date,dis_date;
   JLabel jlabel_doc_name,jlabel_doc_age,jlabel_doc_id,jlabel_doc_mo_no,jl5;
     JButton jb1,Jb2,back,menu;

  JTextField tf1, tf2, tf3, tf4, tf5;
  JButton hn,ht,hl;
    JDatePicker datePicker;
    public DischargePatient(){ 
     frame = new JFrame();
      getContentPane().setBackground(Color.pink);

      setTitle("Discharge Patient");
      hn = new JButton("Submit");
     hn.setBounds(150,250, 100, 30);
        back = new JButton("Back");
      back.setBounds(320,250, 100, 30);
      
      menu = new JButton("Menu");
      menu.setBounds(450,30, 70, 40);
      
      jlabel_doc_name = new JLabel("Patient ID :");
      jlabel_doc_name.setBounds(40,100,150, 30);
 
       tf1 = new JTextField();
       tf1.setBounds(150,100,150, 30);
//      String Patient_Digonise[]= {"Sports Injury ","Cancer","ENT Surgery", "Liver Transplant","Paralysist", "Hair Transplant","Tuberculosis(TB)", "Infertility and IVF","Spine Surgery", "Infectious Diseases","NeuroSurgery", "Plastic Surgery","Endocrinology-Diabetology", "Dental Cosmetic"};

        JLabel appoint_label = new JLabel("Discharge Date");
        add(appoint_label);
        appoint_label.setBounds(40, 180, 200, 25);

        JPanel panel = new JPanel();
        JDateComponentFactory factory = new JDateComponentFactory();
        datePicker = factory.createJDatePicker();
        datePicker.getModel().setDate(LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth());
        datePicker.getModel().setSelected(true);
        panel.add((Component) datePicker);
        panel.setBounds(150, 180, 210, 34);
        
        panel.add((Component) datePicker);
        add(panel);

        add(jlabel_doc_name);
        add(tf1);
        add(hn);
        add(back);
        add(menu);
        add(resultLabel);
        hn.addActionListener(this);
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
        if(e.getSource()==menu){
        dispose();
        new Features();
        }
        else if(e.getSource()==back){
        dispose();
        new Features();
        }
       else if(e.getSource()==hn){
            try{
                int year = datePicker.getModel().getYear();
                String month="",day = "";
                 if ((datePicker.getModel().getMonth() + 1) < 10) {
                    month = "0" + (datePicker.getModel().getMonth() + 1);
                } else {
                    month = String.valueOf((datePicker.getModel().getMonth() + 1));
                }
                if ((datePicker.getModel().getDay()) < 10) {
                    day = "0" + datePicker.getModel().getDay();
                } else {
                    day = String.valueOf(datePicker.getModel().getDay());
                }
                dis_date = day+"/"+month+"/"+year;
                String pid= tf1.getText();
                System.out.println(pid);
                Connection con1 = ConnectionOneTime.getConnect();
                Statement st = con1.createStatement();
                

                String q = "select P_Admit_Date_Time from patient_myhms where P_ID = '"+pid+"'";
                ResultSet rs = st.executeQuery(q);
                Admit_date = null;
                while (rs.next()) {
                    Admit_date =rs.getString(1);
                }
                System.out.println(Admit_date+"\t"+dis_date);
               
                if(IsValidAdmitDate(Admit_date, dis_date)){
//                    System.out.println("12345");
                } else {
//                    System.out.println("123");
                    JOptionPane.showMessageDialog(null, "Invalid Date ", "error", JOptionPane.ERROR_MESSAGE);
                }
            }catch(Exception exx){
                
            }
              
        }
      
    }
    public static void main(String[] args) {
       DischargePatient obj = new DischargePatient();
    }
    
    static boolean IsValidAdmitDate(String apt_date, String admit_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate aptDate = LocalDate.parse(apt_date, formatter);
        LocalDate admitDate = LocalDate.parse(admit_date, formatter);
        boolean p_status;
        
        
        if (aptDate.isBefore(admitDate) || apt_date.equals(admit_date)) {
            p_status = true;
        } else {
            p_status = false;
        }
        return p_status;
    }
    
}
