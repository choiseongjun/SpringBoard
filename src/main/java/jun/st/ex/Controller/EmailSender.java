package jun.st.ex.Controller;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jun.st.ex.Persistence.DTO.Email;

@Component
public class EmailSender  {
 
    @Autowired
    private JavaMailSender mailSender;
 
     
    public void SendEmail(Email email) throws Exception {
    	
    	/*if(mailSender == null) {
    		System.out.println("널널널널널널널널널널널널널널널널@#$%^)$%^&*(널널널널널널");
    	}else {
    		System.out.println("나는 메일 센더다@#$%^&*()"+mailSender);
    	}
    	
    	
        System.out.println(email);*/
        MimeMessage msg = mailSender.createMimeMessage();//에러지점
        try {
             
            msg.setSubject(email.getSubject());
            msg.setText(email.getContent());
            msg.setRecipients(MimeMessage.RecipientType.TO , InternetAddress.parse(email.getReceiver()));
            mailSender.send(msg);
             
        }catch(MessagingException e) {
            System.out.println("MessagingException");
            e.printStackTrace();
        }
 
    }
}