package Prettier_Homes.service.Impl;

import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.data.repository.UserRepository;
import Prettier_Homes.dto.requests.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;
    public void sendEmailRegister(String email, Long confirmToken, String userName) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Giriş Bilgisi");
        mailMessage.setText("Merhaba " + userName + ". Prettiere Homes web sitesine basariyla kaydoldunuz. Kaydinizi onaylamak icin lutfen asagidaki linke tiklayiniz!" +
                "" +"http://localhost:3000/registerConfirm/"+confirmToken);

        javaMailSender.send(mailMessage);
    }

    public Boolean sendMultiMediaMail(String email, String userName, String path) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setTo(email);
        message.setSubject("Dosya gonderildi bilgisi");
        message.setText("Merhaba " + userName + ", Sizin icin bir File gonderdik!");
        FileSystemResource file = new FileSystemResource(new File(path));
        message.addAttachment("file", file);
        javaMailSender.send(mimeMessage);
        return null;
    }

    public ResponseEntity<Boolean> sendEmail(EmailRequest request) throws MessagingException{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setSubject(request.getSubject());
        message.setText(request.getEmail());
        if (request.getUsers().size()>0) {
            List<String> emails = new ArrayList<>();
            request.getUsers().forEach(x -> {
                emails.add(x.getEmail());
            });
            message.setTo(emails.toArray(new String[0]));
        }
        if(request.getFilePath().size()>0) {
            request.getFilePath().forEach(x->{
                FileSystemResource file = new FileSystemResource(new File(x));
                try {
                    message.addAttachment(file.getFilename(), file);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });


        }
        javaMailSender.send(mimeMessage);
        return new ResponseEntity(true, HttpStatus.OK);
    }

    public void forgotPassword(UserEntity user, String email) throws MessagingException{
        Random random = new Random();
        Long resetPassword ;
        String password;
        do {
             resetPassword = 999999 + (long) (Math.random() * (100001 - 999999));
             password =""+resetPassword;
        }while (userRepository.findByResetPasswordCode(passwordEncoder.encode(password)).isPresent());
        System.out.println(email+"email service");


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Prettiere Homes User Info Update");
        mailMessage.setText("Merhaba " + user.getFirstName() +" "+user.getLastName()+", Reset Code : "+resetPassword+ " Lutfen bu sifreyi kimseyle paylasmayin ve yeni sifrenizi belirleyin.");
        user.setResetPasswordCode(passwordEncoder.encode(password));
        userRepository.save(user);
        javaMailSender.send(mailMessage);
//        sendEmailRegister(email,resetPassword,user.getFirstName());
    }


}


