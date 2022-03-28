package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.UserImportDTO;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.Post;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.interfaces.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final String USERS_FILE_PATH = "src/main/resources/files/users.json";

    private final UserRepository userRepository;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final StringBuilder stringBuilder;

    public UserServiceImpl(UserRepository userRepository, PictureService pictureService, ModelMapper modelMapper, Gson gson, ValidationUtil validator, StringBuilder stringBuilder) {
        this.userRepository = userRepository;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.stringBuilder = stringBuilder;
    }

    @Override
    public boolean areImported() {
        return this.userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(USERS_FILE_PATH));
    }

    @Override
    public String importUsers() throws IOException {
        if (this.areImported()) {
            return null;
        }

        Arrays.stream(this.gson
                        .fromJson(this.readFromFileContent(), UserImportDTO[].class))
                .forEach(userDTO -> {
                    if (this.validator.isValid(userDTO)) {
                        Picture picture = this.pictureService.findPictureByPath(userDTO.getProfilePicture());
                        User user = this.modelMapper.map(userDTO, User.class);

                        try {
                            user.setProfilePicture(picture);

                            this.userRepository.save(user);

                            this.stringBuilder
                                    .append(String.format("Successfully imported User: %s",
                                            user.getUsername()))
                                    .append(System.lineSeparator());

                        } catch (DataIntegrityViolationException e) {
                            this.appendInvalidUser();
                        }
                    } else {
                        this.appendInvalidUser();
                    }
                });
        return this.stringBuilder.toString().trim();
    }

    @Override
    public String exportUsersWithTheirPosts() {
        this.stringBuilder.setLength(0);

        this.userRepository
                .findAllByOrderByPostsDescAndId()
                .stream()
                .map(user -> {
                    List<Post> posts = user
                            .getPosts()
                            .stream()
                            .sorted(Comparator.comparing(p -> p.getPicture().getSize()))
                            .toList();
                    user.setPosts(posts);
                    return user;
                })
                .forEach(user -> this.stringBuilder.append(user).append(System.lineSeparator()));

        return this.stringBuilder.toString().trim();
    }

    @Override
    public User findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    private void appendInvalidUser() {
        this.stringBuilder.append("Invalid User").append(System.lineSeparator());
    }
}
