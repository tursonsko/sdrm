package pjatk.sdrm.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pjatk.sdrm.configuration.SecurityConfig;
import pjatk.sdrm.exception.DuplicatedUsernameException;
import pjatk.sdrm.exception.ReservationNotFoundException;
import pjatk.sdrm.mapper.UserMapper;
import pjatk.sdrm.model.dto.SignUpDto;
import pjatk.sdrm.model.dto.UserDtoRequest;
import pjatk.sdrm.model.dto.UserDtoResponse;
import pjatk.sdrm.model.entity.PlaceDetails;
import pjatk.sdrm.model.entity.Role;
import pjatk.sdrm.model.entity.UserData;
import pjatk.sdrm.repository.RoleRepository;
import pjatk.sdrm.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public UserService(UserRepository userRepository, SecurityConfig securityConfig, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserDtoResponse save(UserDtoRequest newUserData) throws DuplicatedUsernameException {
        boolean storedUsersList =
                userRepository.findAll().stream().anyMatch(x -> x.getUserName().equals(newUserData.getName()));
        if (storedUsersList) {
            throw new DuplicatedUsernameException("This username already exists in Database! Use another.");
        }
        UserData userData = UserMapper.mapToUserDataEntity(newUserData,
                passwordEncoder.encode(newUserData.getPassword()));
        return UserMapper.mapToUserDtoResponse(userRepository.save(userData));
    }

    public List<UserDtoResponse> findAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::mapToUserDtoResponse).collect(Collectors.toList());
    }

    public UserDtoResponse findById(Long id){
        return userRepository.findById(id)
                .map(UserMapper::mapToUserDtoResponse)
                .get();
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserData userData = loadUserDataByUserName(username);
//        UserDetails user = User.withUsername(userData.getUserName()).password(userData.getUserPassword()).authorities("USER").build();
//        return user;
//    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        UserData userData = userRepository.findUserDataByUserNameOrUserEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email:" + usernameOrEmail));
        return User.withUsername(userData.getUserName())
                .password(userData.getUserPassword())
                .authorities(mapRolesToAuthorities(userData.getRoles()))
                .build();
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public UserData loadUserDataByUserName(String username) {
        return userRepository.findUserDataByUserName(username);
    }

    public Optional<List<PlaceDetails>> findAllReservationsByUserId(Long id) throws ReservationNotFoundException {
        Optional<UserData> userId = userRepository.findById(id);
        if (userId.isPresent()) {
            return Optional.ofNullable(userId.get().getReservationDetailsList());
        } else {
            throw new ReservationNotFoundException("There is no reservation");
        }
    }

    public String signUpUser(SignUpDto signUpDto) {
        if(userRepository.existsUserDataByUserName(signUpDto.getUsername()))
            throw new RuntimeException("Username is already taken!");
        if(userRepository.existsUserDataByUserEmail(signUpDto.getEmail()))
            throw new RuntimeException("Email is already taken!");

        UserData userData = new UserData();
        userData.setLastName(signUpDto.getName());
        userData.setUserName(signUpDto.getUsername());
        userData.setUserEmail(signUpDto.getEmail());
        userData.setUserPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role roles = roleRepository.findByName("USER")
                .orElseThrow(RuntimeException::new);
        userData.setRoles(Collections.singleton(roles));
        userRepository.save(userData);

        return "Congratulations! You are now signed into SDRM!";
    }

    public String createAdmin(SignUpDto signUpDto) {
        if(userRepository.existsUserDataByUserName(signUpDto.getUsername()))
            throw new RuntimeException("Username is already taken!");
        if(userRepository.existsUserDataByUserEmail(signUpDto.getEmail()))
            throw new RuntimeException("Email is already taken!");

        UserData userData = new UserData();
        userData.setLastName(signUpDto.getName());
        userData.setUserName(signUpDto.getUsername());
        userData.setUserEmail(signUpDto.getEmail());
        userData.setUserPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role roles = roleRepository.findByName("ADMIN")
                .orElseThrow(RuntimeException::new);
        userData.setRoles(Collections.singleton(roles));
        userRepository.save(userData);

        return "Congratulations! Admin account added correctly!";
    }
}