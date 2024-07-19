package uz.pdp.app_hr_2.backend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import uz.pdp.app_hr_2.backend.models.User;
import uz.pdp.app_hr_2.backend.payload.ApiResponse;
import uz.pdp.app_hr_2.backend.repository.UserRepository;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = Logger.getLogger(EmailService.class.getName());

    public void sendEmail(String sendingEmail, String emailCode) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setFrom("alizanovmarat1@gmail.com");
            helper.setTo(sendingEmail);
            helper.setSubject("Verify your account");

            Context context = new Context();
            context.setVariable("emailCode", emailCode);
            context.setVariable("sendingEmail", sendingEmail);

            String emailContent = templateEngine.process("verify-email", context);

            helper.setText(emailContent, true);
            javaMailSender.send(mimeMessage);

            LOGGER.info("Verification email sent to " + sendingEmail);
        } catch (MessagingException e) {
            LOGGER.severe("Failed to send verification email to " + sendingEmail + ": " + e.getMessage());
        }
    }

    public ApiResponse verifyEmail(String email, String emailCode) {
        LOGGER.info("Checking email code: " + emailCode + " for email: " + email);

        Optional<User> optionalUser = userRepository.findByEmailAndEmailCode(email, emailCode);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(true);
            user.setEmailCode(null);
            userRepository.save(user);
            return new ApiResponse("Email account verification successful", true);
        } else {
            return new ApiResponse("Email account verification unsuccessful", false);
        }
    }
}