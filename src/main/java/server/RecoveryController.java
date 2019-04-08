package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
@RestController
public class RecoveryController {

    private static PreparedStatement findUserInfo;
    private static PreparedStatement changePassword;

    private static Map<String, String> recoveryEntries = new HashMap<>();

    static {
        try {
            findUserInfo = ServerApp.dbConnection.prepareStatement(
                    "SELECT * FROM user_login WHERE email = ?;"
            );

            changePassword = ServerApp.dbConnection.prepareStatement(
                    "UPDATE user_login SET password = ? WHERE email = ?;"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("oopproject72@gmail.com");
        mailSender.setPassword("namfyh-kaxjIg-0gitzo");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping(value = "/recoverPassword", method = RequestMethod.POST)
    public String recoverPassword(@RequestBody String email) {
        email = email.substring(1, email.length()-1);
        try {
            findUserInfo.setString(1, email);
            ResultSet result = findUserInfo.executeQuery();
            String rr = "fail";
            if(result.next()) {
                rr = "success";
                String id = String.format("%04d", new Random().nextInt(10000));

                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(email);
                message.setSubject("Greenly | Password recovery");
                message.setText("Please recover your password with the following id: " + id + ".");
                javaMailSender.send(message);

                recoveryEntries.put(id, email);

            }
            return email;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String changePassword(@RequestParam String id, @RequestBody String password) {
        String email = recoveryEntries.get(id);
        if(email != null && email.length() >= 1) {
            try {
                changePassword.setString(1, password);
                changePassword.setString(2, email);
                changePassword.executeUpdate();
                return "success";
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "fail";
    }

}
