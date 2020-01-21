package kosta.namtang.mail.core;

import kosta.namtang.mail.model.Email;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Log4j2
@Component
public class Emailer {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(Email email) throws Exception {
        if (email.isAttachedFile == true) {
            this.attachedMail(email);
        } else {
            this.simple(email);
        }
    }


    private void simple(Email email) throws RuntimeException {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email.sendTo);
        msg.setSubject(email.subject);
        msg.setText(email.content);

        javaMailSender.send(msg);

    }

    private void attachedMail(Email email) throws Exception {


        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(email.sendTo);
        helper.setSubject(email.subject);

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        //helper.setText("<h1>Check attachment for image!</h1>", true);
        helper.setText(email.content);

        //FileSystemResource file = new FileSystemResource(new File("classpath:android.png"));
        //Resource resource = new ClassPathResource("android.png");
        //InputStream input = resource.getInputStream();

        //ResourceUtils.getFile("classpath:android.png");

        //helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);

    }
}
