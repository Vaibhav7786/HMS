//import static LoginForm.password;
//import static LoginForm.username;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.*;  
import java.text.SimpleDateFormat;  
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;  
import java.util.Random;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Generate_Bill extends JFrame implements ActionListener {
        Random random=new Random();
        int number=random.nextInt(10000);
        int number1=random.nextInt(10000);
        int med,room=4000,total1;
        boolean med_no=false;
          String pid;
        String p_id;
        String Room_Charges = ""+number;
        String dr_fees,Payment_Mode,Payment_Mode_Details,Total_Charges,No_Of_Days,Bill_Date,Total_Room_Charges,Bill_No;
          //   String Service_Charges = Dr_Fees + Medical_Fees;
        JComboBox cbPaymentMode;
        JTextArea textArea;
        ResultSet rs;
      String patientName = "",patientAge = "",gender="",phone="",email="",diagnosis="",doctor="",admit="",discharge="",bld="";
      int patientID,total; 
      long days;
      JLabel id,heading,roomCharges,serviceCharges,paymentMode,mediclame,mediClameAmt,totalCharges,bill_No,noOfDays,billingDate,totalRoomCharges,jlabel_Patient_Mo_No,jlabel_Patient_Bld_Grp,jl6;
      JButton receipt,clear,print,submit,search,totalAmt;
      JTextField tf0,tf1, tf2, tf3, tf4, tf5,tf6,tf7,tf8,tf9,tf10;
      JFrame frame ;
      JRadioButton yesRadioButton,noRadioButton;
      public Generate_Bill()
      { 
        frame = new JFrame();
        setTitle("Generate_Bill");
        getContentPane().setBackground(Color.pink);

     // setLayout(null);
      
        
        JLabel heading = new JLabel("HOSPITAL BILL", SwingConstants.CENTER);
        heading.setBounds(20,20,900,20);
        Font boldFont = new Font(heading.getFont().getName(), Font.BOLD, 24);
        heading.setFont(boldFont);

        // Add the label to the content pane
        getContentPane().add(heading, BorderLayout.NORTH);   
        
        id = new JLabel("Patient ID");
        id.setBounds(40,80,250,30);
        
        tf0 = new JTextField();
        tf0.setBounds(170,80,300, 30);
        
        
        search = new JButton("Search");
        search.setBounds(170,120, 120, 30);
        
        roomCharges = new JLabel("Room Charges");
        roomCharges.setBounds(40,160,250, 30);

        tf1 = new JTextField();
        tf1.setBounds(170,160,300, 30);
        
        serviceCharges = new JLabel("Service Charges");
        serviceCharges.setBounds(40,200,250, 30);
        
        tf2 = new JTextField();
        tf2.setBounds(170,200,300, 30);
      
        paymentMode = new JLabel("Payment Mode");
        paymentMode.setBounds(40,404,250,30);  //40 240
        
        String paymentModes[]= {"By Cash","By Online"};
        cbPaymentMode = new JComboBox(paymentModes);
        cbPaymentMode.setBounds(170,404,80,30); //170, 240
      
        mediclame = new JLabel("Medi-Claim :");
        mediclame.setBounds(40,444,250, 30);  //40  280
        
        JPanel panel = new JPanel();
        yesRadioButton = new JRadioButton("Yes");
        noRadioButton = new JRadioButton("No");
         
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(yesRadioButton);
        buttonGroup.add(noRadioButton);
       
        panel.add(yesRadioButton);
        panel.add(noRadioButton);
        panel.setBounds(170,444,150,30); //560
        add(panel);

        mediClameAmt = new JLabel("Medi-Claim Amount ");
        mediClameAmt.setBounds(40,490,250, 30);  //40  
        
        tf4 = new JTextField();
        tf4.setBounds(170,490,300, 30); //170 320
        
        totalRoomCharges = new JLabel("No. Of Days ");
        totalRoomCharges.setBounds(40,240,250, 30);   //360   
        
        tf10 = new JTextField();
        tf10.setBounds(170,240,300, 30); //360
        bill_No = new JLabel("Bill No. ");
        bill_No.setBounds(40,360,250, 30);
      
        tf5 = new JTextField();
        tf5.setBounds(170,284,300, 30);  //400
        
        noOfDays = new JLabel("Total Room Charges ");
        noOfDays.setBounds(40,284,250, 30);  //400
        
        tf6 = new JTextField();
        tf6.setBounds(170,324,300, 30); //440 
      
        billingDate = new JLabel("Billing Date ");
        billingDate.setBounds(40,324,250, 30); //440
        
        tf7 = new JTextField();
        tf7.setBounds(170,364,300, 30); //480
      
        bill_No = new JLabel("Bill No. ");
        bill_No.setBounds(40,364,250, 30); //480
        
        tf8 = new JTextField();
        tf8.setBounds(170,568,300, 30);
        totalCharges = new JLabel("Total Charges ");
        totalCharges.setBounds(40,568,250, 30);
        
//        tf9 = new JTextField();
//        tf9.setBounds(170,400,300, 30);
       
        clear = new JButton("Clear");
        clear.setBounds(600,500,120, 30);
        
        receipt = new JButton("Receipt");
        receipt.setBounds(600,540, 120, 30);
        
        print = new JButton("Print");
        print.setBounds(600,580, 120, 30);
        
        totalAmt = new JButton("Get Total");
        totalAmt.setBounds(170, 528, 120, 30);
        
        textArea = new JTextArea();
        textArea.setBounds(600, 80, 500, 400);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add the JScrollPane to the frame
        getContentPane().add(scrollPane);
        frame.add(scrollPane);
        add(totalAmt);
        add(id);
        add(tf0);
        add(search);
        add(textArea);
        add(roomCharges);
        add(tf1);
        add(serviceCharges);
        add(tf2);
        add(cbPaymentMode);
//        add(paymentModeDetails);
//        add(tf3);
        add(mediclame);
        add(totalCharges);
        add(tf4);
        add(bill_No);
        add(tf5);
        add(noOfDays);
        add(tf6);
        add(billingDate);
        add(tf7);
        add(totalRoomCharges);
        add(tf8);
        add(paymentMode);
        add(clear);
        add(receipt);
        add(print);
        add(mediClameAmt);
        add(tf10);
        totalAmt.addActionListener(this);
        yesRadioButton.addActionListener(this);
        noRadioButton.addActionListener(this);
        search.addActionListener(this);
        clear.addActionListener(this);
        receipt.addActionListener(this);
        print.addActionListener(this);
        

        
      setSize(1200, 1000);
      setLayout(null);
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
      
    

        public void actionPerformed(ActionEvent e){  
          
            if(e.getSource()==search){
                 pid= tf0.getText();
                System.out.println(""+pid);
                if (pid.length()==0) {
                       JOptionPane.showMessageDialog(null, "Something Went Wrong");
                }
                else{         
                    try {
                            Connection con = ConnectionOneTime.getConnect();
                            Statement stmt = con.createStatement();             
                            System.out.println(p_id);
                            rs = stmt.executeQuery("SELECT * FROM patient_myhms WHERE P_ID='"+pid+"';");
                            while (rs.next()) {
                                dr_fees = rs.getString("Dr_Fees");
                                admit = rs.getString("P_Admit_Date_Time");
                                discharge = rs.getString("P_dis_date_time");
                                patientID = rs.getInt("P_ID");
                            }
                            
                        //Finding difference between dates (no. of days)
                        LocalDate date1 = LocalDate.parse(admit);
                        LocalDate date2 = LocalDate.parse(discharge);
                        System.out.println("admit date "+admit);
                        System.out.println("Dis date "+discharge);
                        
                        days = ChronoUnit.DAYS.between(date1, date2);
                        System.out.println("aaaaaaaaaaaaaaa"+days);
                            con.close();
                        } catch (Exception ee) {
                            System.out.println(ee);
                        }
                        System.out.println(days);
                                          System.out.println(days);
                                                               System.out.println(days);


                        p_id = tf0.getText();
                        if(Integer.parseInt(pid)!=patientID ){
                           JOptionPane.showMessageDialog(null, "Patient ID Not Found");
                        }else{
                    
                        System.out.println(dr_fees);
                            System.out.println("=>"+dr_fees);
                       //int total1= number1+Integer.parseInt(dr_fees);
                        tf1.setText(""+room);
                        tf2.setText(dr_fees);
                        tf7.setText(""+number1);
                        tf10.setText(""+days);
                        total = (int)days*room;
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date();
                        String t = formatter.format(date);
                        tf6.setText(t);
                        tf5.setText(""+total);
                        total1 = total+Integer.parseInt(dr_fees);
                        System.out.println("-------> "+total1);
                        }
                        
                }
            }
            if(e.getSource()==yesRadioButton){
                tf4.setEnabled(true);
            }
            if(e.getSource()==noRadioButton){
                med_no=true;
                tf4.setText("0");
               // tf4.setEnabled(false);
     
            }
            if(e.getSource()==clear){
                textArea.setText("");
                tf10.setText("");
                tf0.setText("");
                tf1.setText("");
                tf2.setText("");
                tf4.setText("");
                tf5.setText("");
                tf6.setText("");
                tf7.setText("");
                tf8.setText("");
               // tf9.setText("");
                
            }
    //  Retrieve the patient's name and ID from the database
            if(e.getSource()==receipt){
//                if( tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf4.getText().isEmpty() || tf5.getText().isEmpty() || tf6.getText().isEmpty() || tf7.getText().isEmpty() || tf8.getText().isEmpty() || tf9.getText().isEmpty() || tf10.getText().isEmpty()) {
//                    JOptionPane.showMessageDialog(null, "Something Went Wrong");
//                }
//                else{
                    p_id = tf0.getText();
                    System.out.println(p_id);
                    try {
                        Connection con = ConnectionOneTime.getConnect();
                        Statement stmt = con.createStatement();
                        System.out.println(p_id);
                        rs = stmt.executeQuery("SELECT * FROM patient_myhms WHERE P_ID='"+p_id+"';");
                        // ResultSet res = stmt.executeQuery("SELECT * FROM patient_myhms WHERE D_Name = "+rs.getString("Allot_Doc"));
                            while (rs.next()) {
                                patientName = rs.getString("P_Name");
                                patientID = rs.getInt("P_ID");
                                patientAge = rs.getString("P_Age");
                                gender = rs.getString("Gender");
                                bld = rs.getString("P_Blood_Group");
                                phone = rs.getString("P_mobile_No");
                                email= rs.getString("P_Email");
                                diagnosis = rs.getString("P_Diagnosis");
                                doctor= rs.getString("Allot_Doc");
                                admit = rs.getString("P_Admit_Date_Time");
                                discharge = rs.getString("P_dis_date_time");
                                dr_fees = rs.getString("Dr_Fees");
//                                int total = Integer.parseInt(""+number)+Integer.parseInt(""+number1);
//                                
//                                tf10.setText(""+total);
      
                            }
                            con.close();
                        } catch (Exception ee) {
                            System.out.println(ee);
                        }
                
            // Add the patient's name and ID to the bill text area
                        textArea.append("----------------------------HOSPITAL BILL---------------------------\n");
                        textArea.append("Patient Name       "+"\t"+": "+patientName + "\n");
                        textArea.append("Patient ID         "+"\t\t"+": "+patientID + "\n");
                        textArea.append("Patient Age        "+"\t\t"+": "+patientAge+"\n");
                        textArea.append("Patient Gender     "+"\t"+": "+gender+"\n");
                        textArea.append("Blood Group        "+"\t"+": "+bld+"\n");
                        textArea.append("Contact Number "+"\t"+": "+phone+"\n");
                        textArea.append("Email ID       "+"\t\t"+": "+email+"\n");
                        textArea.append("Patient Diagnosis  "+"\t"+": "+diagnosis+"\n");
                        textArea.append("Alloted Doctor     "+"\t"+": "+doctor+"\n");
                        textArea.append("Admit Date         "+"\t\t"+": "+admit+"\n");     
                        textArea.append("Discharge Date     "+"\t"+": "+discharge+"\n");
//                        textArea.append("Doctor Fees       : "+dr_fees+"\n");

                        textArea.append("\n----------------------------PAYMENT DETAILS---------------------------\n");
                        textArea.append("\nRoom Charges         "+"\t"+": "+tf1.getText());
                        textArea.append("\nService Charges      "+"\t"+": "+dr_fees);
        //              textArea.append("\nDoctor Fees          "+"\t"+": "+""+dr_fees);
                        textArea.append("\nPayment Mode         "+"\t"+": "+cbPaymentMode.getSelectedItem());
                        textArea.append("\nMediclaim Amount     "+"\t"+": "+tf4.getText());
                        textArea.append("\nNo. of Days          "+"\t"+": "+tf10.getText());
                        textArea.append("\nBilling Date         "+"\t"+": "+tf6.getText());
                        textArea.append("\nBill No.             "+"\t\t"+": "+tf7.getText());
                        textArea.append("\nTotal Room Charges   "+"\t"+": "+total);
                        textArea.append("\nTotal Charges        "+"\t"+": "+tf8.getText());
                    }
//            }
            if(e.getSource()==print){
                    if (textArea.getDocument().getLength() == 0 && e.getSource()==print) {
                        JOptionPane.showMessageDialog(null, "Text area is empty.");
                    }
                    else{
                        PrintRequestAttributeSet printAttributes = new HashPrintRequestAttributeSet();
                        printAttributes.add(new MediaPrintableArea(0, 0, 210, 297, MediaPrintableArea.MM));
                        PrinterJob printerJob = PrinterJob.getPrinterJob();
                        printerJob.setPrintable(new Printable() {
                            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                                if (pageIndex > 0) {
                                  return NO_SUCH_PAGE;
                                }
                                Graphics2D graphics2D = (Graphics2D) graphics;
                                graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                                textArea.print(graphics2D);
                                return PAGE_EXISTS;
                            }
                        });

                        if (printerJob.printDialog()) {
                            try {
                                printerJob.print(printAttributes);
                            } catch (PrinterException ex) {
                                ex.printStackTrace();
                            }
                        }      
                    }
            } 
            if(e.getSource()==totalAmt){
                System.out.println("fffffffffffffffffffffff");
                String s = tf4.getText();
                System.out.println("===>"+s);
                
                med = Integer.parseInt(s);
                System.out.println("med-----"+med);
                System.out.println("----------> "+med);
//                if(med_no==false){
                   // tf8.setText(""+(total1));
//                }
//                else{
                    tf8.setText(""+(total1-med));
//                }
            }  
    }
    public static void main(String[] args) {
       
     Generate_Bill gb = new Generate_Bill();
    

       
    }
    
}
      // Trial---------------
//        try {
//            Connection con = ConnectionOneTime.getConnect();
//            Statement stmt = con.createStatement();
//            ResultSet res = stmt.executeQuery("SELECT Dr_Fees FROM doctor_my_hms WHERE D_Name="+""+rs.getString("Allot_Doc"));
//
//          //  String alloted = rs.getString("Allot_Doc");
//          // Resu}
//
//            con.close();
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }                               ltSet res = stmt.executeQuery("SELECT * FROM patient_myhms WHERE D_Name = "+rs.getString("Allot_Doc"));
//            while (res.next()) {
//               dr_fees = res.getString("Dr_Fees");
//            }
//
//            con.close();
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }number=random.nextInt(10