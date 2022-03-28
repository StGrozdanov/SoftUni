package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.posts.PostsBodyDTO;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.Post;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.interfaces.ValidationUtil;
import softuni.exam.instagraphlite.util.interfaces.XMLTool;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PostServiceImpl implements PostService {
    private static final String POSTS_FILE_PATH = "src/main/resources/files/posts.xml";

    private final PostRepository postRepository;
    private final PictureService pictureService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final XMLTool xmlTool;
    private final ValidationUtil validator;
    private final StringBuilder stringBuilder;

    public PostServiceImpl(PostRepository postRepository, PictureService pictureService, UserService userService, ModelMapper modelMapper, XMLTool xmlTool, ValidationUtil validator, StringBuilder stringBuilder) {
        this.postRepository = postRepository;
        this.pictureService = pictureService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.xmlTool = xmlTool;
        this.validator = validator;
        this.stringBuilder = stringBuilder;
    }

    @Override
    public boolean areImported() {
        return this.postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(POSTS_FILE_PATH));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        if (this.areImported()) {
            return null;
        }
        this.xmlTool
                .fromXML(POSTS_FILE_PATH, PostsBodyDTO.class)
                .getPosts()
                .forEach(postDTO -> {
                    if (this.validator.isValid(postDTO)) {
                        Post post = this.modelMapper.map(postDTO, Post.class);

                        Picture picture = this.pictureService.findPictureByPath(postDTO.getPicture().getPath());
                        User user = this.userService.findUserByUsername(postDTO.getUser().getUsername());

                        try {
                            post.setPicture(picture);
                            post.setUser(user);

                            this.postRepository.save(post);

                            this.stringBuilder
                                    .append(String.format("Successfully imported Post, made by %s",
                                            post.getUser().getUsername()))
                                    .append(System.lineSeparator());
                        } catch (DataIntegrityViolationException e) {
                            this.appendInvalidPost();
                        }

                    } else {
                        this.appendInvalidPost();
                    }
                });

        return this.stringBuilder.toString().trim();
    }

    private void appendInvalidPost() {
        this.stringBuilder.append("Invalid Post").append(System.lineSeparator());
    }
}
