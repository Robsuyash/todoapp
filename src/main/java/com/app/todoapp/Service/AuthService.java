package com.app.todoapp.service;

import com.app.todoapp.dto.LoginRequest;
import com.app.todoapp.dto.RegisterRequest;
import com.app.todoapp.model.User;
import com.app.todoapp.repository.UserRepository;
import com.app.todoapp.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// @Service
// public class AuthService {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;
//     private final JwtUtil jwtUtil;

//     public AuthService(UserRepository userRepository,
//                        PasswordEncoder passwordEncoder,
//                        JwtUtil jwtUtil) {
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//         this.jwtUtil = jwtUtil;
//     }

//     public void register(RegisterRequest request) {
//         if (userRepository.existsByEmail(request.getEmail())) {
//             throw new RuntimeException("Email already exists");
//         }

//         User user = new User();
//         user.setName(request.getName());
//         user.setEmail(request.getEmail());
//         user.setPassword(passwordEncoder.encode(request.getPassword()));

//         userRepository.save(user);
//     }

//     public String login(LoginRequest request) {
//         User user = userRepository.findByEmail(request.getEmail())
//                 .orElseThrow(() -> new RuntimeException("Invalid credentials"));

//         if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//             throw new RuntimeException("Invalid credentials");
//         }

//         return jwtUtil.generateToken(user.getEmail());
//     }
// }

// @Service
// public class AuthService {

//     private final UserRepository userRepository;

//     public AuthService(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     public void register(RegisterRequest request) {
//         if (userRepository.existsByEmail(request.getEmail())) {
//             throw new RuntimeException("Email already exists");
//         }

//         User user = new User();
//         user.setName(request.getName());
//         user.setEmail(request.getEmail());
//         user.setPassword(request.getPassword()); 

//         userRepository.save(user);
//     }

//     public void login(LoginRequest request) {
//         User user = userRepository.findByEmail(request.getEmail())
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         if (!user.getPassword().equals(request.getPassword())) {
//             throw new RuntimeException("Invalid password");
//         }
//     }
// }


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;   // ✅ ADD THIS

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {   // ✅ ADD THIS
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }
public String login(LoginRequest request) {
    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(
            request.getPassword(),
            user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }

    return jwtUtil.generateToken(user.getEmail());
}

}