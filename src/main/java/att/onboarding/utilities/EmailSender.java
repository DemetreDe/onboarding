package att.onboarding.utilities;

import att.onboarding.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailSender{

    @Autowired
    private JavaMailSender mailSender;

    public void sendAuthenticationEmail(Employee employee) {
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom("attunetesting@gmail.com");
        message.setTo(employee.getEmail());
        message.setText("Hello" + employee.getFirstname() + "!" +
                "\n\n to set up your profile with Attune \n" +
                " please use the link below \n" + employee.getAuthenticationToken());
        message.setSubject("Getting Started!");

//        mailSender.send(message);
        System.out.println("An email was sent");

    }

}
