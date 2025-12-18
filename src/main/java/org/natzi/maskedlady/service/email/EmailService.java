package org.natzi.maskedlady.service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.natzi.maskedlady.utils.dto.SendEmailDTO;
import org.natzi.maskedlady.utils.template.HtmlTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendValidationEmail(SendEmailDTO emailDTO, String uri) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailDTO.email());
            helper.setSubject(emailDTO.subject());

            uri += "/verify?ott=" + emailDTO.ott();

            if (uri.contains("login")) {
                helper.setText(HtmlTemplate.templateLoginToken(emailDTO.username(), uri), true);
            } else {
                helper.setText(HtmlTemplate.templateValidationEmail(emailDTO.username(), uri), true);
            }
        } catch (MessagingException e) {
            throw new RuntimeException("Hubo un error con el envio del enlace");
        }
        mailSender.send(message);
    }
}