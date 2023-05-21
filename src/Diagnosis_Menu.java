import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Diagnosis_Menu extends JFrame implements ActionListener {
    
   JLabel jlabel_doc_name, jlabel_doc_age, jlabel_doc_id, jlabel_doc_mo_no, jl5;
    JButton jb1, Jb2,back;
    JTextField tf1, tf2, tf3, tf4, tf5;
    JFrame frame;
    JButton ad, dd, see_table,menu;
    JTable table;
    String Dia_Name;
   // String did, Doctor_Name, Doctor_Age, Doctor_Mo_No, Doctor_Quali, Doctor_Email,Doctor_Specialisation,Doctor_Gender;

    Object data[][] = new Object[][] {};

    public Diagnosis_Menu() {
        frame = new JFrame();
        getContentPane().setBackground(Color.pink);
        setTitle("Diagnosis_Menu");
        ad = new JButton("Add Diagnosis");
        ad.setBounds(100,160, 150, 30);

        dd = new JButton("Delete Diagnosis");
        dd.setBounds(350,160, 150, 30);

        see_table = new JButton("See Table");
        see_table.setBounds(100,240, 150, 30);
        
        back = new JButton("Back");
        back.setBounds(350,240, 150, 30);
        
        menu = new JButton("Menu");
        menu.setBounds(530,30, 70, 40);
        
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
                if(e.getSource()==menu){
                    new Features(); 
               }  
              if(e.getSource()==back){
                  dispose();
                  new Manage_Entities().setVisible(true);
              }

            if (e.getSource() == ad) {
                dispose();
                new Diagnosis_Details().setVisible(true);
            }
            if (e.getSource() == dd) {
                dispose();
                 JFrame f =new JFrame("Table View");    
                  f.setBounds(320,150,500,400);

                  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  f.getContentPane().setBackground(Color.pink);

                    String[] col = new String[]{"Diagnosis_Name"}; 
                    JPanel panel = new JPanel();
                    panel.setBounds(5,5,5,5);
                    f.setContentPane(panel);
                    panel.setLayout(null);
                    DefaultTableModel model = new DefaultTableModel(data,col);
                    JTable table = new JTable(model);
                    f.getContentPane().setBackground(Color.pink);

                    table.setBounds(100,40,60,30) ;
                     JScrollPane jsp = new JScrollPane(table);
                jsp.setBounds(25,90,430,100);
                panel.add(jsp);
                jsp.createVerticalScrollBar();
                
                    JButton jb = new JButton ("Delete Row");
                    jb.setBounds(40,300,100,30);
                    f.add(jb);
//                    JButton jb2 = new JButton ("Update Row");
//                    jb2.setBounds(170,300,120,30);
//                    f.add(jb2);
                    
                    JButton back = new JButton ("Back");
                    back.setBounds(340,300,100,30);
                    f.add(back);
                    
                   back.addActionListener(new ActionListener() {
                    @Override
                   public void actionPerformed(ActionEvent e) {
                       if(e.getSource()==back){
                       dispose();
                       new Diagnosis_Menu();
                       }
                   }
                   }
                   );

                    //f.add(new JScrollPane(table));

                   jb.addActionListener(new ActionListener() {

                   @Override
                   public void actionPerformed(ActionEvent e) {
                       if(table.getSelectedRow()==-1){
                           JOptionPane.showMessageDialog(null,"Please Select Row ","error",JOptionPane.ERROR_MESSAGE);
                       }

                       int status = JOptionPane.showConfirmDialog(null, "Do You Want to Delete This Row","Confirm",JOptionPane.YES_NO_OPTION);
                       if(status==JOptionPane.YES_OPTION){
                          // model.removeRow(table.getSelectedRow());
                              try {
                            DefaultTableModel tbl = (DefaultTableModel) table.getModel();
                            Dia_Name = tbl.getValueAt(table.getSelectedRow(), 0).toString();

                            Connection con = ConnectionOneTime.getConnect();
                            DefaultTableModel tbl2 = (DefaultTableModel) table.getModel();
                            if (table.getSelectedRowCount() == 1) {
                                String s = "delete from Diagnosis_myhms where Diagnosis_Name='" + Dia_Name + "'";
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
                    String q = "select * from diagnosis_myhms";
                    ResultSet rs = st.executeQuery(q);
                    String Diagno_Name = "";
                  while(rs.next()){
                    Diagno_Name = ""+rs.getString("Diagnosis_Name");
                     String array[]={Diagno_Name};
                    DefaultTableModel m =(DefaultTableModel)table.getModel();
                    m.addRow(array);
                }

                // new PatientDetails().setVisible(true);
            }
             if (e.getSource() == see_table) {
                dispose();
                // new PatientDetails().setVisible(true);
                  JFrame f =new JFrame("Table View");    
                  f.setBounds(320,150,500,400);

                  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  f.getContentPane().setBackground(Color.pink);

                    String[] col = new String[]{"Diagnosis_Name"}; 
                    JPanel panel = new JPanel();
                    panel.setBounds(5,5,5,5);
                    f.setContentPane(panel);
                    panel.setLayout(null);
                    DefaultTableModel model = new DefaultTableModel(data,col);
                    JTable table = new JTable(model);
                    f.getContentPane().setBackground(Color.pink);

                    table.setBounds(100,40,60,30) ;
                     JScrollPane jsp = new JScrollPane(table);
                jsp.setBounds(25,90,430,100);
                panel.add(jsp);
                jsp.createVerticalScrollBar();
                
//                    JButton jb = new JButton ("Delete Row");
//                    jb.setBounds(40,300,100,30);
//                    f.add(jb);
//                    JButton jb2 = new JButton ("Update Row");
//                    jb2.setBounds(170,300,120,30);
//                    f.add(jb2);
                    
                    JButton back = new JButton ("Back");
                    back.setBounds(40,300,100,30);
                    f.add(back);
                    
                   back.addActionListener(new ActionListener() {
                    @Override
                   public void actionPerformed(ActionEvent e) {
                       if(e.getSource()==back){
                       dispose();
                       new Diagnosis_Menu();
                       }
                   }
                   }
                   );

                    //f.add(new JScrollPane(table));

//                   jb.addActionListener(new ActionListener() {
//
//                   @Override
//                   public void actionPerformed(ActionEvent e) {
//                       if(table.getSelectedRow()==-1){
//                           JOptionPane.showMessageDialog(null,"Please Select Row ","error",JOptionPane.ERROR_MESSAGE);
//                       }
//
//                       int status = JOptionPane.showConfirmDialog(null, "Do You Want to Delete This Row","Confirm",JOptionPane.YES_NO_OPTION);
//                       if(status==JOptionPane.YES_OPTION){
//                          // model.removeRow(table.getSelectedRow());
//                              try {
//                            DefaultTableModel tbl = (DefaultTableModel) table.getModel();
//                            Dia_Name = tbl.getValueAt(table.getSelectedRow(), 0).toString();
//
//                            Connection con = ConnectionOneTime.getConnect();
//                            DefaultTableModel tbl2 = (DefaultTableModel) table.getModel();
//                            if (table.getSelectedRowCount() == 1) {
//                                String s = "delete from Diagnosis_myhms where Diagnosis_Name='" + Dia_Name + "'";
//                                PreparedStatement ps = con.prepareStatement(s);
//
//                                ps.execute();
//                                tbl2.removeRow(table.getSelectedRow());
//                            }
//
//                        } catch (Exception ex) {
//
//                        }
//
//                       }
//                      // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                   }
//               });



                    setLayout(null);
                    f.setVisible(true);

                    Connection con = ConnectionOneTime.getConnect();

                    Statement st = con.createStatement();
                    String q = "select * from diagnosis_myhms";
                    ResultSet rs = st.executeQuery(q);
                    String Diagno_Name = "";
                  while(rs.next()){
                    Diagno_Name = ""+rs.getString("Diagnosis_Name");
                     String array[]={Diagno_Name};
                    DefaultTableModel m =(DefaultTableModel)table.getModel();
                    m.addRow(array);
                }

        }
      }catch(Exception ex){

      }

 }
     
    public static void main(String[] args) {
        Diagnosis_Menu dobj = new Diagnosis_Menu();
    }
}