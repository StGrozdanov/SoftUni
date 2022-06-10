package softuni.pathfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.pathfinder.models.entities.RouteEntity;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
    @Query("SELECT r FROM RouteEntity r ORDER BY SIZE(r.comments) DESC")
    List<RouteEntity> getRouteEntityByCommentsLength();
}
