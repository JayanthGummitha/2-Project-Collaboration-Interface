package com.collaboration_interface.controller;

import com.collaboration_interface.config.JwtProvider;
import com.collaboration_interface.modal.User;
import com.collaboration_interface.repository.UserRepository;
import com.collaboration_interface.request.LoginRequest;
import com.collaboration_interface.response.AuthResponse;
import com.collaboration_interface.service.CustomeUserDetailsImpl;
import com.collaboration_interface.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomeUserDetailsImpl customeUserDetails;

    @Autowired
    private SubscriptionService subscriptionService;


   @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User issueExit=userRepository.findByEmail(user.getEmail());

        if(issueExit!=null){
            throw new Exception("email already exist with another account");

        }
        User createdUser=new User();

        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        User saveduser=userRepository.save(createdUser);

        subscriptionService.createSubscription(saveduser);

       Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
       SecurityContextHolder.getContext().setAuthentication(authentication);
       String jwt= JwtProvider.generateToken(authentication);
       AuthResponse res=new AuthResponse();
       res.setMessage("signup success");
       res.setJwt(jwt);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> signing(@RequestBody LoginRequest loginRequest){

       String username=loginRequest.getEmail();
       String password= loginRequest.getPassword();

       Authentication authentication=authenticate(username,password);
       SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt= JwtProvider.generateToken(authentication);
        AuthResponse res=new AuthResponse();
        res.setMessage("signing success");
        res.setJwt(jwt);
        return new ResponseEntity<>(res, HttpStatus.CREATED);


    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails=customeUserDetails.loadUserByUsername(username);

        if(userDetails==null){

            throw new BadCredentialsException("invalid username");

        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("invalid password");

        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}

