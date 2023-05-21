import java.sql.*;
import javax.swing.*;

public class HospitalBill extends JFrame {

    JTextArea textArea;

    public void showPatienDetails() {

        // Create the text area for the bill
        textArea = new JTextArea();

        // Retrieve the patient's name and ID from the database
        String patientName = "";
        int patientID = 0;
        String patientAge = "", gender = "", phone = "", email = "", diagnosis = "", doctor = "", admit = "", discharge = "", bld = "";
        try {
            Connection con = ConnectionOneTime.getConnect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM patient_myhms WHERE P_ID=1");

            while (rs.next()) {
                patientName = rs.getString("P_Name");
                patientID = rs.getInt("P_ID");
                patientAge = rs.getString("P_Age");
                gender = rs.getString("Gender");
                bld = rs.getString("P_Blood_Group");
                phone = rs.getString("P_mobile_No");
                email = rs.getString("P_Email");
                diagnosis = rs.getString("P_Diagnosis");
                doctor = rs.getString("Allot_Doc");
                admit = rs.getString("P_Admit_Date_Time");
                discharge = rs.getString("P_dis_date_time");

                patientID = rs.getInt("P_ID");
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        // Add the patient's name and ID to the bill text area
        textArea.append("Patient Name: " + patientName + "\n");
        textArea.append("Patient ID: " + patientID + "\n");
        textArea.append("Patient Age : " + patientAge + "\n");
        textArea.append("Patient Gender : " + gender + "\n");
        textArea.append("Patient Blood Group : " + bld + "\n");
        textArea.append("Contact Number : " + phone + "\n");
        textArea.append("Email ID : " + email + "\n");
        textArea.append("Patient Diagnosis : " + diagnosis + "\n");
        textArea.append("Alloted Doctor  : " + doctor + "\n");
        textArea.append("Admit Date  : " + admit + "\n");
        textArea.append("Discharge Date   : " + discharge + "\n");

        // Add the text area to the JFrame
        add(new JScrollPane(textArea));

        // Set the size and visibility of the JFrame
        setSize(900, 800);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HospitalBill().showPatienDetails();
    }
}
