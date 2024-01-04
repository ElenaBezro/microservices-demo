package startsteps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import startsteps.model.Order;
import startsteps.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.restTemplate = new RestTemplate();
        this.orderRepository = orderRepository;
    }

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Integer id) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return order.get();
    }

    public void createOrder(Integer bookId) {
        String url = "http://localhost:8080/api/books/" + bookId;
        try {
            restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            orderRepository.save(new Order(bookId));
        } catch (HttpClientErrorException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
