package Prettier_Homes.controller;


import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.data.repository.UserRepository;
import Prettier_Homes.dto.requests.EmailRequest;
import Prettier_Homes.security.JwtUserDetails;
import Prettier_Homes.service.Impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService service;

    @Autowired
    UserRepository repository;

    @GetMapping
    public ResponseEntity<Boolean> sendEmail(@RequestBody EmailRequest request) throws MessagingException {
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     String userName = authentication.getName();
        System.out.println(userName);
        return  service.sendEmail(request);
    }
    @GetMapping("/deneme")
    public String sendEmail() throws MessagingException {
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     String userName = authentication.getName();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
//        Long userId = userDetails.getId();
//
//        // Kullanıcı e-posta bilgisi
        String email = jwtUserDetails.getEmail();
     Long id = jwtUserDetails.getId();
        System.out.println(userName);
        System.out.println(email);
        return  userName +" "+email+" id: "+id ;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/deneme2")
    public String sendEmail2() throws MessagingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
//        Long userId = userDetails.getId();
//
//        // Kullanıcı e-posta bilgisi
        String email = jwtUserDetails.getEmail();
        Long id = jwtUserDetails.getId();
        System.out.println(userName);
        System.out.println(email);
        return  userName +" "+email+" id: "+id ;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/deneme3")
    public UserEntity sendEmail3() throws MessagingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
//        Long userId = userDetails.getId();
//
//        // Kullanıcı e-posta bilgisi
        String email = jwtUserDetails.getEmail();
        Long id = jwtUserDetails.getId();
        System.out.println(userName);
        System.out.println(email);

        return  repository.findById(id).get() ;
    }
}
