package pl.kinga.formularz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;
import javax.naming.Context;

@Controller
public class MainController {

    MailService mailService;
    TemplateEngine templateEngine;

    @Autowired
    public MainController(MailService mailService,
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

