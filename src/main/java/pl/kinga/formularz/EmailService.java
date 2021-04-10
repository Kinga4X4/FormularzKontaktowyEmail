package pl.kinga.formularz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;
    EmailConfiguration emailConfiguration;

    @Autowired
    public EmailService(EmailConfiguration emailConfiguration, JavaMailSender javaMailSender) {
        this.emailConfiguration = emailConfiguration;
        this.javaMailSender = javaMailSender;
    }

    public void sendHtmlEmail(String to, String subject, String content) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo(this.emailConfiguration.getUsername());
            helper.setFrom(this.emailConfiguration.getUsername());
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
