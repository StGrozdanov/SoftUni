package softuni.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.pathfinder.models.dtos.MostCommentedRouteDTO;
import softuni.pathfinder.models.dtos.PictureUrlDTO;
import softuni.pathfinder.services.PictureService;
import softuni.pathfinder.services.RouteService;

import java.util.List;

@Controller
public class HomeController {
    private final RouteService routeService;
    private final PictureService pictureService;

    public HomeController(RouteService routeService, PictureService pictureService) {
        this.routeService = routeService;
        this.pictureService = pictureService;
    }

    @GetMapping("/")
    public String mostCommentedRoutePage(Model model) {
        MostCommentedRouteDTO mostCommentedRoute = this.routeService.getMostCommentedRoute();
        List<PictureUrlDTO> theLastThreeImages = this.pictureService.getTheLastThreeImages();

        model.addAttribute("mostCommentedRoute", mostCommentedRoute);
        model.addAttribute("latestThreePictures", theLastThreeImages);

        return "index";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

}