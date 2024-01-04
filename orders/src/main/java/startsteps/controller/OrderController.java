package startsteps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import startsteps.model.Order;
import startsteps.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> findOrders() {
        return orderService.findOrders();
    }

    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable("id") Integer id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public void createOrder(@RequestParam("bookId") Integer bookId) {
        orderService.createOrder(bookId);
    }

}
