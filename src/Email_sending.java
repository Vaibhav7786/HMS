import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email_sending extends PatientDetails {

    static public boolean sendemail(String to,String from,String subject,String text)
    {
        boolean flag = false;
        final String username = "myvaibhav.sonar";//your email id
        final String password = "bvturysutpihlsuq";// your password 
        // smtp properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
//        props.put("mail.smtp.ssl.trust", "");
        //Session
        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
//        session.setDebug(true);
        try {
            Random random=new Random();
            int number=random.nextInt(1000000);
            
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject("SHALBY HOSPITAL");
            message.setText("Hello "+PatientDetails.Patient_name+"\nYour Registration Process Is Done "+"\nYour Registered Mobile Number Is : "+PatientDetails.Patient_mo_no+"\nYour Admission Date And Time Is : "+PatientDetails.P_Admit_Date_Time+"\nYour Unique ID Is :  "+PatientDetails.Patient_Id+"\nYour OTP For Verfication In Hospital Is : "+number+"\nTHANKS FOR COMING TO SHALBY HOSPITAL ");
            Transport.send(message);
            System.out.println(""+PatientDetails.Patient_Id);
            System.out.println(""+PatientDetails.Patient_name);

            flag = true;
            System.out.println("Email Sent Succesfully");
        } catch (Exception e) {
            System.out.println("Problem in Sending Email ....");
            System.out.println(e.getMessage());
            System.out.println(e);
        }
        return flag;
    }
}