package pl.kinga.formularz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;


@Controller
public class MainController {

    EmailService mailService;
    TemplateEngine templateEngine;

    public MainController(EmailService mailService,
                          TemplateEngine templateEngine) {
        this.mailService = mailService;
        this.templateEngine = templateEngine;
    }

    @PostMapping("/sendHtmlEmail")
    @ResponseBody
    public String sendEmail(String name,
                            String email,
                            String message) {
        mailService.sendHtmlEmail("kinga@byom.de", name, message);
        return "E-mail sent! We will contact you soon";
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



