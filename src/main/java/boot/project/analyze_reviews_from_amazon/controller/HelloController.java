package boot.project.analyze_reviews_from_amazon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping(value = "/")
    public String showFirstPage() {
        return "index";
    }

    @GetMapping(value = "/header")
    public String showHeader() {
        return "header";
    }

    @GetMapping(value = "/footer")
    public String showFooter() {
        return "footer";
    }
}
