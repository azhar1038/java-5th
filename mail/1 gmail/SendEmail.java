import java.util.Scanner;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail{
    public static void main(String[] args){
        String from = "mdazharuddin1011999@gmail.com";
        String pwd = "Azhar12#7%90$";
        String to = "srnaik2209@gmail.com";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        Session ses = Session.getDefaultInstance(prop, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from, pwd);
            }
        });
        try{
            MimeMessage msg = new MimeMessage(ses);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject("java mail SSL via gmail test.");
            msg.setText("Nothing to see here folks!");
            Transport.send(msg);
            System.out.println("Message Sent!");
        }catch(MessagingException me){
            System.out.println(me);
        }
    }
}