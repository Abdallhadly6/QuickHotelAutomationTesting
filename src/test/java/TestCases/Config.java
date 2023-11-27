package TestCases;

import Base.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

//email
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;


public class Config extends TestBase {
    public Config() throws IOException {
        super();
    }

    Session newSession = null;
    @BeforeSuite
    public void start() throws IOException {
        extentReports = new ExtentReports("QuickHotelTestReport\\QuickHotelReport.html" , true);
        extentReports.addSystemInfo("Project","Quick Hotel");
        FileUtils.cleanDirectory(new File("QuickHotelTestReport"));
    }

    @AfterSuite
    public void end() throws MessagingException {
        extentReports.flush();

        

        /*
        //zip our report folder
        ZipUtil.pack(new File("QuickHotelTestReport\\"), new File("QuickHotelTestReport.zip"));

        //////////////////////////////////////////////////////////////////////////////////////
        String content = "Quick Hotel Test Report At  " + String.valueOf(java.time.LocalDate.now()) ;
        BodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setText(content);
        BodyPart messageBodyPart2 = new MimeBodyPart();
        String filename = "QuickHotelTestReport.zip";
        DataSource source = new FileDataSource(filename);
        messageBodyPart2.setDataHandler(new DataHandler(source));
        messageBodyPart2.setFileName(filename);
        Multipart multipartObject = new MimeMultipart();
        multipartObject.addBodyPart(messageBodyPart1);
        multipartObject.addBodyPart(messageBodyPart2);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("adlyabdallh9@gmail.com", "hqwnfmdpollsyenh");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("adlyabdallh9@gmail.com")); //Set from address of the email
            message.setContent(multipartObject, "text/html");
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("adlyabdallh9@gmail.com,abdallhadly8@gmail.com")); //Set email recipient
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("abdallhadly6@gmail.com")); //Set email recipient
            message.setSubject("Quick Hotel Report");
            Transport.send(message);
            System.out.println("sent email successfully!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        /////////////////////////////////////////////////////////////////////////////////////////

         */


    }
}
