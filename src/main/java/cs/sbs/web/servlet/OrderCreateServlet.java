package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/order")
public class OrderCreateServlet extends HttpServlet {
    public static final List<Order> orders = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String customer = req.getParameter("customer");
        String food = req.getParameter("food");
        String quantityStr = req.getParameter("quantity");

        if (customer == null || customer.isBlank() || food == null || food.isBlank()) {
            out.println("Error: customer and food cannot be empty");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            out.println("Error: quantity must be a valid number");
            return;
        }

        Order order = new Order(customer, food, quantity);
        orders.add(order);
        out.printf("Order Created: %d%n", order.getOrderId());
    }
}