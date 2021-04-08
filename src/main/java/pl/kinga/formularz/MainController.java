package pl.kinga.formularz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

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

