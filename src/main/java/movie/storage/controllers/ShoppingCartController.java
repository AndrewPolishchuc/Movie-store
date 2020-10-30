package movie.storage.controllers;

import movie.storage.model.ShoppingCart;
import movie.storage.model.dto.MovieSessionRequestDto;
import movie.storage.service.ShoppingCartService;
import movie.storage.service.UserService;
import movie.storage.service.mapper.MovieSessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/shopping-carts")
@RestController
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieSessionMapper movieSessionMapper;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService, MovieSessionMapper movieSessionMapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @GetMapping("/by-user")
    public ShoppingCart getByUser(Authentication authenticationUser) {
        return shoppingCartService.getByUser(userService.findByEmail(authenticationUser.getName()));
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(Authentication authenticationUser,
                                @RequestBody MovieSessionRequestDto movieSession) {
        shoppingCartService.addSession(movieSessionMapper.convertDtoToMovieSession(movieSession),
                userService.findByEmail(authenticationUser.getName()));
    }
}
