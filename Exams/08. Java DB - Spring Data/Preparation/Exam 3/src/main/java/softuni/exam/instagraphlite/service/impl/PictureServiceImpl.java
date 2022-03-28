package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.PictureImportDTO;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.interfaces.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class PictureServiceImpl implements PictureService {
    private static final String PICTURES_FILE_PATH = "src/main/resources/files/pictures.json";

    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validator;
    private final StringBuilder stringBuilder;

    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validator, StringBuilder stringBuilder) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
        this.stringBuilder = stringBuilder;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(PICTURES_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        if (this.areImported()) {
            return null;
        }

        Arrays.stream(gson.fromJson(this.readFromFileContent(), PictureImportDTO[].class))
                .forEach(pictureDTO -> {
                    if (this.validator.isValid(pictureDTO)) {
                        Picture picture = this.modelMapper.map(pictureDTO, Picture.class);

                        try {
                            this.pictureRepository.save(picture);

                            this.stringBuilder
                                    .append(String.format("Successfully imported Picture, with size %.2f",
                                            pictureDTO.getSize()))
                                    .append(System.lineSeparator());
                        } catch (DataIntegrityViolationException e) {
                            appendInvalidPicture();
                        }
                    } else {
                        appendInvalidPicture();
                    }
                });
        return stringBuilder.toString().trim();
    }

    @Override
    public String exportPictures() {
        this.stringBuilder.setLength(0);

        this.pictureRepository
                .findAllBySizeGreaterThanOrderBySize(30000.00)
                .forEach(picture -> stringBuilder
                        .append(String.format("%.2f - %s", picture.getSize(), picture.getPath()))
                        .append(System.lineSeparator())
                );

        return stringBuilder.toString().trim();
    }

    @Override
    public Picture findPictureByPath(String profilePicture) {
        return this.pictureRepository.findByPath(profilePicture);
    }

    private void appendInvalidPicture() {
        this.stringBuilder.append("Invalid Picture").append(System.lineSeparator());
    }
}
