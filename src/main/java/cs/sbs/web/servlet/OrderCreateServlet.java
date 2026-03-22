package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderCreateServlet extends HttpServlet {

    private static final List<Order> ORDER_LIST = new ArrayList<>();
    // 静态计数器，生成自增 int 类型 id
    private static int orderIdCounter = 1;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 1. 获取表单参数（去掉多余的 $ 符号）
        String customer = request.getParameter("customer");
        String food = request.getParameter("food");
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));

        // 2. 计算总价
        Double foodPrice = MenuListServlet.getFoodPrice(food);
        Double totalPrice = foodPrice * quantity;

        // 3. 生成订单（解决类型不匹配：用自增 int 作为 id）
        Order order = new Order();
        order.setId(orderIdCounter++); // 自增 int ID，不再用 UUID String
        order.setCustomer(customer);
        order.setFood(food);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);
        order.setCreateTime(java.time.LocalDateTime.now()); // 现在方法名匹配，不会报错

        // 4. 保存订单
        ORDER_LIST.add(order);

        // 5. 输出成功页面
        out.println("<!DOCTYPE html>");
        out.println("<html lang='zh-CN'>");
        out.println("<head><meta charset='UTF-8'><title>下单成功</title></head>");
        out.println("<body>");
        out.println("<h1>✅ 下单提交成功！</h1>");
        out.println("<p>订单ID：" + order.getId() + "</p>");
        out.println("<p>用户名：" + order.getCustomer() + "</p>");
        out.println("<p>菜品：" + order.getFood() + "</p>");
        out.println("<p>数量：" + order.getQuantity() + " 份</p>");
        out.println("<p>总价：¥" + order.getTotalPrice() + "</p>");
        out.println("<p>下单时间：" + order.getCreateTime() + "</p>");
        out.println("<p><a href='order/" + order.getId() + "'>查看订单详情</a></p>");
        out.println("<p><a href='menu'>查看菜单</a> | <a href='order.html'>继续下单</a></p>");
        out.println("</body></html>");
        out.close();
    }

    // 根据ID查询订单（适配 int 类型 id）
    public static Order getOrderById(String orderId) {
        try {
            int id = Integer.parseInt(orderId);
            for (Order order : ORDER_LIST) {
                if (order.getId() == id) {
                    return order;
                }
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }
}