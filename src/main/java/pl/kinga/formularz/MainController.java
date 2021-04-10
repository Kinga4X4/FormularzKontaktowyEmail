package pl.kinga.formularz;


import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Controller
public class MainController {

    EmailService mailService;
    TemplateEngine templateEngine;

    public MainController(EmailService mailService,
                          TemplateEngine templateEngine) {
        this.mailService = mailService;
        this.templateEngine = templateEngine;
    }

    @GetMapping("/sendHtmlEmail")
    public String sendEmail() {
        Context context = new Context();
        context.setVariable("formularz", "email - szablon i wysyłanie");
        context.setVariable("wypełniony formularz", "Kontakt");
        String body = templateEngine.process("kontakt", context);
        mailService.sendHtmlEmail("kinga@byom.de", "Test e-mail", body);
        return "E-mail sent!";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/kontakt")
    public String kontakt() {
        return "kontakt";
    }

    @GetMapping("/projekty")
    public String projekty() {
        return "projekty";
    }


}



