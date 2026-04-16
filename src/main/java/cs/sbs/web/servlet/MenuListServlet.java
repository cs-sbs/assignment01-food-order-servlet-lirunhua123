package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/menu")
public class MenuListServlet extends HttpServlet {
    private static final List<MenuItem> menu = new ArrayList<>();

    static {
        menu.add(new MenuItem("Fried Rice", 8));
        menu.add(new MenuItem("Fried Noodles", 9));
        menu.add(new MenuItem("Burger", 10));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String search = req.getParameter("name");
        List<MenuItem> result;

        if (search != null && !search.isBlank()) {
            result = menu.stream()
                    .filter(m -> m.getName().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            result = menu;
        }

        out.println("Menu List:");
        for (int i = 0; i < result.size(); i++) {
            MenuItem item = result.get(i);
            out.printf("%d. %s - $%.0f%n", i + 1, item.getName(), item.getPrice());
        }
    }
}