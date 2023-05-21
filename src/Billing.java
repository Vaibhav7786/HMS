//import java.awt.Font;
//import javax.swing.*;
//import java.awt.event.*;
//import java.sql.*;
//
//public class Billing extends JFrame implements ActionListener{
//    JLabel lb1,lb2,lb3;
//    JTextField txt1,txt2;
//    JButton btn;
//    
//    public Billing(){
//        setTitle("Login Page");
//        setLocation(200,200);
//        setSize(600, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(null);
//       
//          lb1 = new JLabel("Login Form");
//            lb1.setBounds(50, 50, 300, 30);
//            lb1.setFont(new Font("Candara",Font.BOLD,24));
//            add(lb1);
//            
//            lb2 = new JLabel("Enter Email");
//            lb2.setBounds(50, 110, 150, 30);
//            lb2.setFont(new Font("Candara",Font.BOLD,24));
//            add(lb2);
//
//            lb3 = new JLabel("Enter Password");
//            lb3.setBounds(50, 150, 150, 30);
//            lb3.setFont(new Font("Candara",Font.BOLD,24));
//            add(lb3);
//            
//            txt1 = new JTextField();
//            txt1.setBounds(210, 100, 300, 40);
//            txt1.setFont(new Font("Candara",Font.BOLD,24));
//            add(txt1);
//            
//            txt2 = new JTextField();
//            txt2.setBounds(210, 150, 300, 40);
//            txt2.setFont(new Font("Candara",Font.BOLD,24));
//            add(txt2);
//
//            btn = new JButton("Login");
//            btn.setBounds(210, 200, 300, 40);
//            btn.setFont(new Font("Candara",Font.BOLD,24));
//            add(btn);
//
//        btn.addActionListener(this);
//        setVisible(true);
//    }
//    @Override
//    public void actionPerformed(ActionEvent e){
//          if(e.getSource()==btn){
//            String email = txt1.getText();
//            String password = txt2.getText();
//            
//            Connection con = ConnectionOneTime.getConnect();
//            try
//            {
//                String query = "select * from user where email=? and password=?";
//                PreparedStatement ps = con.prepareStatement(query);
//                ps.setString(1,email);
//                ps.setString(2,password);
//                
//                  ResultSet rs = ps.executeQuery();
//                  if(rs.next())
//                  {
//                       dispose();
//                       Profile profile =  new Profile();
//                       profile.setVisible(true);
//                  }else{
//                      JOptionPane.showMessageDialog(new Billing(), "Error while Login");                      
//                  }
//            
//            }catch(SQLException E){
//                System.out.println("Exception : "+e);
//            }
//        }
//    }
//    public static void main(String[] args) {
//        Billing billing = new Billing();
//        billing.setVisible(true);
//    }
//}
