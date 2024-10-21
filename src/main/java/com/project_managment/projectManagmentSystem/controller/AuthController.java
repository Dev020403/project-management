package com.project_managment.projectManagmentSystem.controller;

import com.project_managment.projectManagmentSystem.config.JwtProvider;
import com.project_managment.projectManagmentSystem.model.User;
import com.project_managment.projectManagmentSystem.repository.UserRepository;
import com.project_managment.projectManagmentSystem.request.LoginRequest;
import com.project_managment.projectManagmentSystem.response.AuthResponse;
import com.project_managment.projectManagmentSystem.service.CustomUserDetailsImpl;
import com.project_managment.projectManagmentSystem.service.SubscriptionService;
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
    private CustomUserDetailsImpl customUserDetails;

    @Autowired
    private SubscriptionService subscriptionService;

    //done
    @PostMapping("/signup")
    public ResponseEntity<?> createUserHandler(@RequestBody User user) throws Exception {
        try {
            if (user.getEmail() == null || user.getEmail().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be null or empty");
            }
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                throw new IllegalArgumentException("Password cannot be null or empty");
            }

            User isUserExist = userRepository.findByEmail(user.getEmail());
            if (isUserExist != null) {
                throw new Exception("Email already exists");
            }

            User createdUser = new User();
            createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
            createdUser.setEmail(user.getEmail());
            createdUser.setFullName(user.getFullName());

            User savedUser = userRepository.save(createdUser);

            subscriptionService.createSubscription(savedUser);

            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = JwtProvider.generateToken(authentication);

            AuthResponse res = new AuthResponse(jwt, "signup successful");
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error during signup: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //done
    @PostMapping("/login")
    public ResponseEntity<?> loginHandler(@RequestBody LoginRequest loginRequest) throws Exception {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();


        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(jwt, "login successful");
        return new ResponseEntity<>(res, HttpStatus.CREATED);

    }

    //done
    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(email);
        if (userDetails == null) {
            throw new BadCredentialsException("invalid email");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}