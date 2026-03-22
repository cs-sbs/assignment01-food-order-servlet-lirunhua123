package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 订单详情Servlet（注解版）
 * 访问路径：/order/*
 */
@WebServlet("/order/*")  // 通配符映射，匹配/order/xxx路径
public class OrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 1. 从URL获取订单ID（例如/order/123456 → 截取123456）
        String pathInfo = request.getPathInfo();
        String orderId = pathInfo == null ? "" : pathInfo.substring(1);

        // 2. 查询订单
        Order order = OrderCreateServlet.getOrderById(orderId);

        // 3. 输出详情
        out.println("<!DOCTYPE html>");
        out.println("<html lang='zh-CN'>");
        out.println("<head><meta charset='UTF-8'><title>订单详情</title></head>");
        out.println("<body>");

        if (order == null) {
            out.println("<h1>未找到该订单！</h1>");
        } else {
            out.println("<h1>订单详情</h1>");
            out.println("<p>订单ID：" + order.getId() + "</p>");
            out.println("<p>用户名：" + order.getCustomer() + "</p>");
            out.println("<p>菜品：" + order.getFood() + "</p>");
            out.println("<p>数量：" + order.getQuantity() + "份</p>");
            out.println("<p>总价：¥" + order.getTotalPrice() + "</p>");
            out.println("<p>下单时间：" + order.getCreateTime() + "</p>");
        }

        out.println("<p><a href='../menu'>返回菜单</a></p>");
        out.println("</body></html>");
        out.close();
    }
}