package kosta.namtang.mail.contoller;

import kosta.namtang.mail.common.ShopResponse;
import kosta.namtang.mail.common.StatusCode;
import kosta.namtang.mail.core.Emailer;
import kosta.namtang.mail.model.Email;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;

@Log4j2
@RestController
public class MailController {

    //https://docs.spring.io/spring/docs/5.1.6.RELEASE/spring-framework-reference/integration.html#mail
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Emailer emailer;

    @RequestMapping("/")
    public void Home() {

        log.info("welcome to mail server");
    }

    @PostMapping("api/v1/mail")
    public ShopResponse mail(@RequestBody Email email) {
        try {
            emailer.sendEmail(email);
            return new ShopResponse(StatusCode.Success, "");
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ShopResponse(StatusCode.Fail, "");
        }

    }



    @GetMapping("/test/simple")
    public void test(String... args) {

        System.out.println("Sending Email...");

        sendEmail();
        //sendEmailWithAttachment();

        System.out.println("Done");

    }

    @GetMapping("/test/attachedMail")
    public void test2(String... args) {

        System.out.println("Sending Email...");

        try {
            //sendEmail();
            sendEmailWithAttachment();

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    }

    void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("hankkuu@gmail.com", "guttu00@naver.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }

    void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("hankkuu@gmail.com");
        helper.setTo("guttu00@naver.com");

        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

        //FileSystemResource file = new FileSystemResource(new File("classpath:android.png"));

        //Resource resource = new ClassPathResource("android.png");
        //InputStream input = resource.getInputStream();

        //ResourceUtils.getFile("classpath:android.png");

        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);

    }
}
