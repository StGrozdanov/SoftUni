package softuni.pathfinder.services;

import org.springframework.stereotype.Service;
import softuni.pathfinder.models.dtos.MostCommentedRouteDTO;
import softuni.pathfinder.models.entities.RouteEntity;
import softuni.pathfinder.repositories.RouteRepository;

import javax.transaction.Transactional;

@Service
public class RouteService {
    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Transactional
    public MostCommentedRouteDTO getMostCommentedRoute() {
        RouteEntity routeEntity = this.routeRepository
                                                    .getRouteEntityByCommentsLength()
                                                    .stream()
                                                    .limit(1)
                                                    .findFirst()
                                                    .get();

        String routeFirstPictureUrl = routeEntity.getPictures().stream().toList().get(0).getUrl();
        String descriptionSummary = routeEntity.getDescription().substring(0, 150) + "...";

        MostCommentedRouteDTO routeDTO = new MostCommentedRouteDTO();

        routeDTO.setCoverPicture(routeFirstPictureUrl);
        routeDTO.setDescriptionSummary(descriptionSummary);
        routeDTO.setName(routeEntity.getName());
        routeDTO.setId(routeEntity.getId());

        return routeDTO;
    }
}
