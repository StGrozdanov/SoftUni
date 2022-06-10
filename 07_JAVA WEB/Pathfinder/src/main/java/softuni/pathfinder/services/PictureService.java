package softuni.pathfinder.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.pathfinder.models.dtos.PictureUrlDTO;
import softuni.pathfinder.models.entities.PictureEntity;
import softuni.pathfinder.repositories.PictureRepository;

import java.util.List;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;


    public PictureService(PictureRepository pictureRepository, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
    }

    public List<PictureUrlDTO> getTheLastThreeImages() {
        List<PictureEntity> lastThreePictures = this.pictureRepository.findTop3ByOrderByIdDesc();

        return lastThreePictures
                                .stream()
                                .map(pictureEntity -> this.modelMapper.map(pictureEntity, PictureUrlDTO.class))
                                .toList();
    }
}
