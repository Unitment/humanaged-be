package vn.fsoft.humanaged.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String email, String tokenResetLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("humanaged.service@gmail.com","Humanaged Support Service");
        helper.setTo(email);
        String subject = "Here is the link to reset your password";
        String content = "<p>Hi</p>" +
                "<p>You have request to reset your password</p>" +
                "<p>Click the link below to change your password</p>" +
                "<a href=\""+tokenResetLink+"\">Change my password</a><br><br><br>" +
                "<p><b>Ignore this email if you do not have any request to reset password!</b></p>";
        helper.setSubject(subject);
        helper.setText(content,true);
        mailSender.send(message);
    }
}
