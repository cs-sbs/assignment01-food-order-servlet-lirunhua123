package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/order/*")
public class OrderDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String path = req.getPathInfo();
        if (path == null || path.equals("/")) {
            out.println("Error: order ID is required");
            return;
        }

        String idStr = path.substring(1);
        int orderId;
        try {
            orderId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            out.println("Error: invalid order ID");
            return;
        }

        Order found = null;
        for (Order o : OrderCreateServlet.orders) {
            if (o.getOrderId() == orderId) {
                found = o;
                break;
            }
        }

        if (found == null) {
            out.println("Error: order not found");
            return;
        }

        out.println("Order Detail");
        out.printf("Order ID: %d%n", found.getOrderId());
        out.printf("Customer: %s%n", found.getCustomer());
        out.printf("Food: %s%n", found.getFood());
        out.printf("Quantity: %d%n", found.getQuantity());
    }
}