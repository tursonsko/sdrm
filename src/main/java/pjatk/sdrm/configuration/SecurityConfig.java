package pjatk.sdrm.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import pjatk.sdrm.service.UserService;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final RestAuthenticationSuccessHandler authenticationSuccessHandler;
    private final RestAuthenticationFailureHandler authenticationFailureHandler;
    private final String secret;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private UserService userService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(RestAuthenticationSuccessHandler authenticationSuccessHandler,
                          RestAuthenticationFailureHandler authenticationFailureHandler,
                          @Value("${jwt.secret}") String secret, PasswordEncoder passwordEncoder) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.secret = secret;
        this.userService = userService;

        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("src/main/resources/public/**,/index.html**")
                .permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/signup").permitAll()
                .antMatchers("/api/signin").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/api/manage/**").hasRole("ADMIN")
                .antMatchers("/api/CreateAdmin").permitAll()
                .antMatchers("/api/signin").permitAll()
                .antMatchers("/api/users/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/reservation/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/email/**").hasAnyRole("USER", "ADMIN")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(authenticationFilter())
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userService, secret))
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        http.headers().httpStrictTransportSecurity()
                .maxAgeInSeconds(0)
                .includeSubDomains(true);
    }

    @Bean
    public AuthFilter authenticationFilter() throws Exception {
        AuthFilter filter = new AuthFilter();
        filter.setFilterProcessesUrl("/api/login");
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setAuthenticationManager(super.authenticationManager());
        return filter;
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
