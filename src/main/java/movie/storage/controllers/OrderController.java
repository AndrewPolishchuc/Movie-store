package movie.storage.controllers;

import java.util.List;
import java.util.stream.Collectors;
import movie.storage.model.ShoppingCart;
import movie.storage.model.User;
import movie.storage.model.dto.OrderResponseDto;
import movie.storage.service.OrderService;
import movie.storage.service.ShoppingCartService;
import movie.storage.service.UserService;
import movie.storage.service.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orders")
@RestController
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, UserService userService,
                           ShoppingCartService shoppingCartService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestParam String email) {
        User user = userService.findByEmail(email);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        orderService.completeOrder(shoppingCart.getTickets(), user);
    }

    @GetMapping
    public List<OrderResponseDto> getHistoryByUser(@RequestParam String email) {
        return orderService.getOrderHistory(userService.findByEmail(email)).stream()
                .map(orderMapper::convertOrderToDto)
                .collect(Collectors.toList());
    }
}
