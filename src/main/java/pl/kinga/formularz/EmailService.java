package pl.kinga.formularz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private JavaMailSender javaMailSender;
    EmailConfiguration emailConfiguration;

    public EmailService(JavaMailSender javaMailSender) {

        this.javaMailSender = javaMailSender;
    }

    public void sendHtmlEmail(String to, String subject, String content) {
        logger.debug("Wysylanie emaila {}", to);
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo(this.emailConfiguration.getUsername());
            helper.setFrom(this.emailConfiguration.getUsername());
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(mail);
            logger.debug("Email do {} wyslany poprawnie ", to);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
