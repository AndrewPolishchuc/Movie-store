package movie.storage.service.mapper;

import java.util.ArrayList;
import java.util.List;
import movie.storage.model.Order;
import movie.storage.model.Ticket;
import movie.storage.model.dto.OrderResponseDto;
import movie.storage.model.dto.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponseDto convertOrderToDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setUserEmail(order.getUser().getEmail());
        orderResponseDto.setTickets(fillTicketRespList(order));
        return orderResponseDto;
    }

    private List<TicketResponseDto> fillTicketRespList(Order order) {
        List<TicketResponseDto> ticketResponseDtoList = new ArrayList<>();
        for (Ticket ticket : order.getTickets()) {
            TicketResponseDto ticketResponseDto = new TicketResponseDto();
            ticketResponseDto.setId(ticket.getId());
            ticketResponseDto.setMovieTitle(ticket.getMovieSession().getMovie().getTitle());
            ticketResponseDtoList.add(ticketResponseDto);
        }
        return ticketResponseDtoList;
    }
}
