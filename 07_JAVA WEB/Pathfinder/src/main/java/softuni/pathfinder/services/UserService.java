package softuni.pathfinder.services;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.pathfinder.models.dtos.authDTOs.UserLoginDTO;
import softuni.pathfinder.models.dtos.authDTOs.UserRegisterDTO;
import softuni.pathfinder.models.entities.UserEntity;
import softuni.pathfinder.models.enums.LevelEnum;
import softuni.pathfinder.models.sessions.UserSession;
import softuni.pathfinder.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserSession userSession;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, UserSession userSession) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userSession = userSession;
    }

    public boolean registerNewUser(UserRegisterDTO userRegisterDTO) {
        UserEntity newUser = this.modelMapper.map(userRegisterDTO, UserEntity.class);

        try {
            newUser.setLevel(LevelEnum.BEGINNER);
            this.userRepository.save(newUser);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    public boolean loginUser(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> registeredUser = this.userRepository
                .findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());

        if (registeredUser.isPresent()) {
            this.userSession.setUsername(registeredUser.get().getUsername());
            this.userSession.setLoggedIn(true);
        }

        return this.userSession.getLoggedIn();
    }

    public void logoutUser() {
        this.userSession.clear();
    }

}
