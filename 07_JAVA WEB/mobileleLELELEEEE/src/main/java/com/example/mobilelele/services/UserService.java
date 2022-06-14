package com.example.mobilelele.services;

import com.example.mobilelele.models.dtos.UserDTOs.UserLoginDTO;
import com.example.mobilelele.models.dtos.UserDTOs.UserRegisterDTO;
import com.example.mobilelele.models.entities.RoleEntity;
import com.example.mobilelele.models.entities.UserEntity;
import com.example.mobilelele.models.sessions.UserSession;
import com.example.mobilelele.repositories.RoleRepository;
import com.example.mobilelele.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;


@Service
public class UserService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSession userSession;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserSession userSession) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }

    public void registerUser(UserRegisterDTO userRegisterDTO) {
        userRegisterDTO.setPassword(this.passwordEncoder.encode(userRegisterDTO.getPassword()));
        UserEntity createdUser = this.modelMapper.map(userRegisterDTO, UserEntity.class);
        RoleEntity userRole = this.roleRepository.findById(1L).get();
        createdUser.setRole(userRole);

        this.userRepository.save(createdUser);
    }

    public boolean userLoggedIn(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> userByUsername = this.userRepository.findByUsername(userLoginDTO.getUsername());

        if (userByUsername.isEmpty()) {
            LOGGER.info("User with not found. User name: {}", userLoginDTO.getUsername());
            return false;
        }

        String entityPassword = userByUsername.get().getPassword();
        String loginDTOPassword = userLoginDTO.getPassword();

        boolean loginSuccess = this.passwordEncoder.matches(loginDTOPassword, entityPassword);

        if (loginSuccess) {
            userSession.setLoggedIn(true);
            userSession.setUsername(userLoginDTO.getUsername());
        }

        return loginSuccess;
    }

    public void logout() {
        this.userSession.clear();
    }

    public Optional<UserEntity> findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}