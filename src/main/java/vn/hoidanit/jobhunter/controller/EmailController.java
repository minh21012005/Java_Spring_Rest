package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.service.EmailService;

@RestController
@RequestMapping("/api/v1")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/email")
    public String sendSimpleEmail() {
        // this.emailService.sendSimpleEmail();
        // this.emailService.sendEmailSync("vohanhtrang2019@gmail.com", "hehehe",
        // "<h1><b>Trang peos ị</b></h1>", false,
        // true);
        this.emailService.sendEmailFromTemplateSync("vohanhtrang2019@gmail.com", "test email hehehe", "job");
        return "ok";
    }
}
