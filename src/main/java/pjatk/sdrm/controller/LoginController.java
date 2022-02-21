package pjatk.sdrm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pjatk.sdrm.configuration.LoginCreds;
import pjatk.sdrm.model.dto.LoginDto;
import pjatk.sdrm.model.dto.SignUpDto;
import pjatk.sdrm.repository.RoleRepository;
import pjatk.sdrm.repository.UserRepository;
import pjatk.sdrm.service.UserService;

@RestController
@RequestMapping("/api")
public class LoginController {


    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    public LoginController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping(value="/login")
    public void login(@RequestBody LoginCreds loginCreds){
    }


    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpDto signUpDto){
        try {
            return new ResponseEntity<>(userService.signUpUser(signUpDto), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/createAdmin")
    public ResponseEntity<String> createAdmin(@RequestBody SignUpDto signUpDto){
        try {
            return new ResponseEntity<>(userService.createAdmin(signUpDto), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
