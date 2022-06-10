package softuni.pathfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.pathfinder.models.entities.PictureEntity;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {
    List<PictureEntity> findTop3ByOrderByIdDesc();
}
