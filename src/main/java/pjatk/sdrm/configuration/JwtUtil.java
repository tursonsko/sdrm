package pjatk.sdrm.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pjatk.sdrm.model.entity.Role;
import pjatk.sdrm.model.entity.UserData;
import pjatk.sdrm.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final UserService userService;
    private final JwtConfig jwtConfig;

    public JwtUtil(@Lazy UserService userService, JwtConfig jwtConfig) {
        this.userService = userService;
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(Authentication authentication){
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserData userData = userService.loadUserDataByUserName(principal.getUsername());
        String[] arr = userData.getRoles().stream().map(Role::getName).toArray(String[]::new);
        return JWT.create()
                .withSubject(principal.getUsername())
                .withClaim("userId",userData.getId())
                .withClaim("email",userData.getUserEmail())
                .withArrayClaim("role", arr)
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfig.getExpirationTime()))
                .sign(Algorithm.HMAC256(jwtConfig.getSecret()));
    }

}
